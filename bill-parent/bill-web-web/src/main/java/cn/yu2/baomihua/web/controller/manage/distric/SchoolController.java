package cn.yu2.baomihua.web.controller.manage.distric;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.annotation.Permission;
import cn.yu2.baomihua.constant.PermiConstant;
import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.dto.SchoolAdminDTO;
import cn.yu2.baomihua.manage.dto.SchoolDTO;
import cn.yu2.baomihua.manage.entity.SchoolAdmin;
import cn.yu2.baomihua.manage.entity.User;
import cn.yu2.baomihua.manage.module.ISchoolModule;
import cn.yu2.baomihua.manage.module.IUserModule;
import cn.yu2.baomihua.web.controller.BaseController;

@Controller
@RequestMapping("/manage/distric/school/")
public class SchoolController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(SchoolController.class); 

	@Autowired
	private ISchoolModule schoolModule;

	@Autowired
	private IUserModule userModule;

	/**
	 * 学校列表
	 */
	@Permission(PermiConstant.DISTRIC_SCHOOL)
	@RequestMapping("list")
	public String list( Model model, String name ) {
		//Page<SchoolDTO> page = new Page<>(1, 10);
		Page<SchoolDTO> page = getPage(5);
		Response<Page<SchoolDTO>> resp = schoolModule.getSchoolByName(page, name);
		logger.warn(this.getClass().getName()+ ",name:"+name);
		model.addAttribute("name", name);
		model.addAttribute("page", resp.getBody());
		return "manage/distric/school/list";
	}
	
	/**
	 * 删除管理员
	 */
	@ResponseBody
	@RequestMapping("del")
	public String del(@RequestParam("id")Long id) {
		
		return callbackSuccess(schoolModule.deleteAdmin(id));
	}

	/**
	 * 管理员弹出
	 */
	@ResponseBody
	@RequestMapping("users")
	public String getUsers( Model model, @RequestParam("schoolId" ) Long schoolId) {
		Map<Character, List<User>> teacherTreeMap = userModule.getTeacherTreeMap(schoolId).getBody();
		Set<Long> adminIds = schoolModule.getAdminIds(schoolId).getBody();
		model.addAttribute("teacherTreeMap", teacherTreeMap);
		model.addAttribute("adminIds", adminIds);
		model.addAttribute("schoolId", schoolId);
		String html = mail.getHtmltext("manage/distric/school/ajax_user.vm", model);
		return callbackSuccess(html);
	}
	
	/**
	 * 管理员修改
	 */
	@ResponseBody
	@RequestMapping("update")
	public String updateAdmins(Model model,@RequestParam("schoolId")Long schoolId,@RequestParam(value="userIds[]", required = false) List<Long> userIds) {
		List<SchoolAdmin> sas = null;
		schoolModule.deleteAdmins(schoolId);
		if(userIds !=null && !userIds.isEmpty()) {
			sas = new ArrayList<>();
			for(Long userId : userIds) {
				SchoolAdmin sa = new SchoolAdmin();
				sa.setSchoolId(schoolId);
				sa.setUserId(userId);
				sas.add(sa);
			}
			schoolModule.insertAdmins(sas);
		}
		List<SchoolAdminDTO> admins = schoolModule.getAdmins(schoolId).getBody();
		model.addAttribute("admins", admins);
		String html = mail.getHtmltext("manage/distric/school/ajax_admin.vm", model);
		return callbackSuccess(html);
	}
	/**
	 * 导出学校
	 * @param request
	 * @param response
	 */
	@Permission(PermiConstant.DISTRIC_SCHOOL)
	@SuppressWarnings("deprecation")
	@RequestMapping("/schoolExport")
	public void teacherExport( HttpServletRequest request, HttpServletResponse response ,String name ) {
		Page<SchoolDTO> page = getPage(10000);
		Page<SchoolDTO> schoolPage = schoolModule.getSchoolByName(page, name).getBody();
		try {
			HSSFWorkbook wb = new HSSFWorkbook();// 创建一个工作薄
			HSSFSheet sheet = wb.createSheet("学校");// 创建一个sheet
			// 设置style1的样式，此样式运用在第二行
			HSSFCellStyle style1 = wb.createCellStyle();// cell样式
			// 设置单元格背景色，设置单元格背景色以下两句必须同时设置
			style1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);// 设置填充样式
			style1.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);// 设置填充色
			// 设置单元格上、下、左、右的边框线
			style1.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
			style1.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
			style1.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
			style1.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
			HSSFFont font1 = wb.createFont();// 创建一个字体对象
			font1.setBoldweight((short) 10);// 设置字体的宽度
			font1.setFontHeightInPoints((short) 10);// 设置字体的高度
			font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
			style1.setFont(font1);// 设置style1的字体
			style1.setWrapText(true);// 设置自动换行
			style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 设置单元格字体显示居中（左右方向）
			//合并行列
			style1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 设置单元格字体显示居中(上下方向)
			/*CellRangeAddress range = new CellRangeAddress(0, 0, 0, 1);
			sheet.addMergedRegion(range);*/
			//创建Excel的头
			HSSFRow row = sheet.createRow(0);
			// 创建一个cell对象(列) 格子单元
			HSSFCell cell = row.createCell(0);
			cell.setCellValue("学校名称");
			cell.setCellStyle(style1);
			HSSFCell cell1 = row.createCell(1);
			cell1.setCellValue("学校地址");
			cell1.setCellStyle(style1);
			sheet.setColumnWidth(0, (short) 5000);
			sheet.setColumnWidth(1, (short) 8000);
			for(int i=1;i<=8;i++){
				if(i%2==1){
					HSSFCell headerCell =row.createCell(i+1);
					headerCell.setCellValue("管理员");
					headerCell.setCellStyle(style1);
					sheet.setColumnWidth(i+1, (short) 3500);
				}else{
					HSSFCell headerCell =row.createCell(i+1);
					headerCell.setCellValue("用户名");
					headerCell.setCellStyle(style1);
					sheet.setColumnWidth(i+1, (short) 3500);
				}
				
				
			}
			List<SchoolDTO> list=schoolPage.getRecords();
			if(list!=null && !list.isEmpty()){
				for(int i=0;i<list.size();i++){
					HSSFRow rows = sheet.createRow(i+1);
					HSSFCell headerCell = rows.createCell(0);
					headerCell.setCellValue(list.get(i).getName());
					HSSFCell headerCell1 = rows.createCell(1);
					headerCell1.setCellValue(list.get(i).getAddress());
					List<SchoolAdminDTO> admins=list.get(i).getAdmins();
					int k=0;
					for(int j=0;j<admins.size();j++){
						HSSFCell headerCelln = rows.createCell(k+2);
						headerCelln.setCellValue(admins.get(j).getName());
						HSSFCell headerCelln1 = rows.createCell(k+3);
						headerCelln1.setCellValue(admins.get(j).getLoginName());
						k=k+2;
						
					}
				}
			}
			
			// 设置单元格的编码
			/*HSSFRow row1 = sheet.createRow(1);
			String[] header = new String[ ] { "班级", "入学年份" };*/
			//创建列
			/*Calendar cal = Calendar.getInstance();
			int nowYear = cal.get(Calendar.YEAR);
			sheet.addValidationData(setDataValidation(
				new String[ ] { String.valueOf(nowYear - 1), String.valueOf(nowYear) }, 2, 600, 1, 1));
			for ( int i = 0 ; i < header.length ; i++ ) {
				HSSFCell headerCell = row1.createCell(i);
				headerCell.setCellValue(header[i]);
				headerCell.setCellStyle(style1);
			}
			//设置列宽
			for ( int m = 0 ; m < 2 ; m++ ) {
				sheet.setColumnWidth(m, (short) 5500);
			}*/
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename=school.xls");
			OutputStream out = response.getOutputStream();
			wb.write(out);
			out.close();
		} catch ( FileNotFoundException e ) {
			logger.error(e.getMessage(), e);

		} catch ( IOException e ) {

			logger.error(e.getMessage(), e);

		}
	}
	public HSSFDataValidation setDataValidation( String[] textList, int firstRow, int endRow, int firstCol,
			int endCol ) {
		// 加载下拉列表内容  
		DVConstraint constraint = DVConstraint.createExplicitListConstraint(textList);
		// DVConstraint constraint = new DVConstraint();  
		constraint.setExplicitListValues(textList);

		// 设置数据有效性加载在哪个单元格上。  
		// 四个参数分别是：起始行、终止行、起始列、终止列  
		CellRangeAddressList regions = new CellRangeAddressList((short) firstRow, (short) endRow, (short) firstCol,
				(short) endCol);

		// 数据有效性对象  
		HSSFDataValidation data_validation = new HSSFDataValidation(regions, constraint);

		return data_validation;
	}

}
