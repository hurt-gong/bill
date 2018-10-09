package cn.yu2.baomihua.web.controller.manage.distric;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.baomidou.framework.controller.AjaxResult;
import com.baomidou.mybatisplus.plugins.Page;

import cn.yu2.baomihua.annotation.Permission;
import cn.yu2.baomihua.constant.ManageConstant;
import cn.yu2.baomihua.constant.PermiConstant;
import cn.yu2.baomihua.core.common.util.POIUtil;
import cn.yu2.baomihua.core.common.util.ValidateUtil;
import cn.yu2.baomihua.manage.dto.ResponseDTO;
import cn.yu2.baomihua.manage.entity.User;
import cn.yu2.baomihua.manage.enums.ResponseHeadCodeEnum;
import cn.yu2.baomihua.manage.enums.StatusEnum;
import cn.yu2.baomihua.manage.enums.UserTypeEnum;
import cn.yu2.baomihua.manage.module.IStaffModule;
import cn.yu2.baomihua.web.controller.BaseController;

/**
 * 区人员管理
 * @author lizhixiao
 * @version 1.0 
 * @date 2016-06-13
 */
@Controller
@RequestMapping("/manage/distric/staff/")
public class StaffController extends BaseController {

	@Resource(name = "staffModuleImpl")
	private IStaffModule staffModuleImpl;


	/**
	 * 区人员管理主页面
	 */
	@Permission(PermiConstant.DISTRIC_USER)
	@RequestMapping("list")
	public String list( Model model, String name ) {
		if ( null != name ) {
			name = "".equals(name.trim()) ? null : name.trim();
		}
		User user = new User();
		user.setName(name);
		user.setType(UserTypeEnum.TEACHER.key());
		user.setStatus(StatusEnum.ON.key());
		user.setSchoolId(getCurrentSchoolId());
		Page<User> page = getPage(10);
		Page<User> userList = staffModuleImpl.getUserList(page, user).getBody();
		model.addAttribute("userList", userList);
		model.addAttribute("name", name);
		return "manage/distric/staff/list";

	}


	/**
	 * 添加区人员
	 */
	@ResponseBody
	@RequestMapping("addUser")
	public String addUser( Model model, User user ) {
		ResponseDTO response = validateUser(user);
		if ( ResponseHeadCodeEnum.SUCCESS.key() != response.getCode() ) {
			return callback(new AjaxResult(false, response.getMassage()));
		}
		user.setType(UserTypeEnum.TEACHER.key());
		user.setStatus(StatusEnum.ON.key());
		user.setSchoolId(getCurrentSchoolId());
		user.setCrUserId(getCurrentUserId());
		user.setCrTime(new Date());
		Boolean flag = staffModuleImpl.addUser(user).getBody();
		return callbackSuccess(flag);
	}


	/**
	 * 更新区人员
	 */
	@ResponseBody
	@RequestMapping("updateUser")
	public String updateUser( Model model, User user ) {
		ResponseDTO response = validateUser(user);
		if ( ResponseHeadCodeEnum.SUCCESS.key() != response.getCode() ) {
			callback(new AjaxResult(false, response.getMassage()));
		}
		Boolean flag = staffModuleImpl.updateUserById(user).getBody();
		return callbackSuccess(flag);
	}


	/**
	 * 删除区人员
	 */
	@ResponseBody
	@RequestMapping("delUserById")
	public String delUserById( @RequestParam("id") Long id ) {
		Boolean flag = staffModuleImpl.delUserById(id).getBody();
		return callbackSuccess(flag);
	}


	/**
	 * 批量删除区人员
	 */
	@ResponseBody
	@RequestMapping("delUserList")
	public String delUserList( @RequestParam("idList[]") List<Long> idList ) {
		int num = staffModuleImpl.delUserList(idList).getBody();
		return callbackSuccess("删除记录条数" + num);
	}


	/**
	 * excel导入区人员
	 */
	@ResponseBody
	@RequestMapping("/importStaff")
	public String importStaff( HttpServletRequest request ) {
		ResponseDTO response = null;
		InputStream is = null;
		XSSFWorkbook xssf = null;
		HSSFWorkbook hssf = null;
		try {
			// 输出
			CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			resolver.setDefaultEncoding("utf-8");
			MultipartHttpServletRequest mRequest = resolver.resolveMultipart(request);
			Iterator<String> it = mRequest.getFileNames();
			while ( it.hasNext() ) {
				String name = it.next();
				CommonsMultipartFile file = (CommonsMultipartFile) mRequest.getFile(name);// 获取文件名字
				String fileName = file.getOriginalFilename();
				is = file.getInputStream();
				if ( fileName.endsWith(".xls") ) {
					hssf = new HSSFWorkbook(is);
				} else {
					xssf = new XSSFWorkbook(is);
				}
			}
			if ( null != hssf ) {
				HSSFSheet hssfSheet = hssf.getSheetAt(0);
				response = readeStaffWithExcel(null, hssfSheet, hssfSheet.getLastRowNum());
			} else {
				XSSFSheet xssfSheet = xssf.getSheetAt(0);
				response = readeStaffWithExcel(xssfSheet, null, xssfSheet.getLastRowNum());
			}
			if ( response.getCode() != ResponseHeadCodeEnum.SUCCESS.key() ) {
				return callbackFail(response.getMassage());
			}
			return callbackSuccess("导入成功");
		} catch ( Exception e ) {
			logger.error(":error", e);
			return callbackFail("导入失败");
		} finally {
			try {
				if ( xssf != null ) {
					xssf.close();
				} else if ( hssf != null ) {
					hssf.close();
				}
			} catch ( IOException e ) {
				e.printStackTrace();
			}
		}

	}


	/**
	 * 导出区人员excel
	 */
	@Permission(PermiConstant.DISTRIC_USER)
	@RequestMapping("/exportStaff")
	public String exportStaff( HttpServletRequest request, HttpServletResponse response ) {
		response = POIUtil.excelResponse(request, response, ManageConstant.DISTRIC_STAFF_EXCEL);
		try {
			HSSFWorkbook workbook = POIUtil.createWorkbook();
			HSSFSheet sheet = POIUtil.createSheet(workbook, ManageConstant.DISTRIC_STAFF_EXCEL,
				ManageConstant.DISTRIC_STAFF_EXCEL_TITLES);
			User user = new User();
			user.setType(UserTypeEnum.TEACHER.key());
			user.setStatus(StatusEnum.ON.key());
			user.setSchoolId(getCurrentSchoolId());
			List<User> userList = staffModuleImpl.getUserList(user).getBody();

			writeStaffWithExcel(sheet, userList);
			OutputStream out = response.getOutputStream();
			workbook.write(out);
			workbook.close();
			out.close();
		} catch ( IOException e ) {
			e.printStackTrace();
		}
		return null;
	}


	/**从excel读取人员信息
	 * @return */
	private ResponseDTO readeStaffWithExcel( XSSFSheet xssfSheet, HSSFSheet hssfSheet, int excelRow ) {
		ResponseDTO response = null;
		List<User> userList = new ArrayList<User>();
		for ( int rowNum = 0 ; rowNum < excelRow ; rowNum++ ) {
			User user = new User();
			if ( null != xssfSheet ) {
				XSSFRow row = xssfSheet.getRow(rowNum);
				user.setName(POIUtil.getCellValue(row.getCell(1)));
				String value2 = POIUtil.getCellValue(row.getCell(2));
				if ( ("男".equals(value2)) || ("女".equals(value2)) ) {
					user.setGender(value2.equals("男") ? 1 : 2);
				}
				if ( null != row.getCell(3) ) {
					user.setIdCardNo(POIUtil.getCellValue(row.getCell(3)));
				}
				if ( null != row.getCell(4) ) {
					user.setMobile(POIUtil.getCellValue(row.getCell(4)));
				}
			} else {
				HSSFRow row = hssfSheet.getRow(rowNum + 1);
				user.setName(POIUtil.getCellValue(row.getCell(1)));
				String value2 = POIUtil.getCellValue(row.getCell(2));
				if ( ("男".equals(value2)) || ("女".equals(value2)) ) {
					user.setGender(value2.equals("男") ? 1 : 2);
				}
				if ( null != row.getCell(3) ) {
					user.setIdCardNo(POIUtil.getCellValue(row.getCell(3)));
				}
				if ( null != row.getCell(4) ) {
					user.setMobile(POIUtil.getCellValue(row.getCell(4)));
				}
			}
			response = validateUser(user);
			if ( response.getCode() != ResponseHeadCodeEnum.SUCCESS.key() ) {
				response.setMassage("数据第" + rowNum + "行" + response.getMassage());
				return response;
			}
			user.setType(UserTypeEnum.TEACHER.key());
			user.setStatus(StatusEnum.ON.key());
			user.setSchoolId(getCurrentSchoolId());
			user.setCrUserId(getCurrentUserId());
			user.setCrTime(new Date());
			userList.add(user);
		}
		Boolean result = staffModuleImpl.importUserList(userList).getBody();
		if ( result ) {
			response.setCode(ResponseHeadCodeEnum.SUCCESS.key());
		}
		return response;
	}


	/**人员信息写入excel*/
	private ResponseDTO writeStaffWithExcel( HSSFSheet sheet, List<User> userList ) {
		Integer row = 1;
		for ( User user : userList ) {
			Map<Integer, String> map = new HashMap<Integer, String>();
			map.put(0, row.toString());
			map.put(1, null == user.getName() ? "" : user.getName());
			map.put(2, null == user.getGender() ? "" : (1 == user.getGender() ? "男" : "女"));
			map.put(3, null == user.getIdCardNo() ? "" : user.getIdCardNo());
			map.put(4, null == user.getMobile() ? "" : user.getMobile());
			for ( Entry<Integer, String> entry : map.entrySet() ) {
				POIUtil.writeCellValue(sheet, row, entry.getKey(), String.valueOf(entry.getValue()));
			}
			row++;
		}
		return null;
	}


	/**校验用户信息*/
	private ResponseDTO validateUser( User user ) {
		ResponseDTO response = new ResponseDTO();
		response.setCode(ResponseHeadCodeEnum.ERROR.key());
		if ( null == user.getName() || user.getName().length() < 2 ) {
			response.setMassage("姓名格式错误");
			return response;
		} else if ( null == user.getIdCardNo() || user.getIdCardNo().length() < 15 ) {
			response.setMassage("身份证格式错误");
			return response;
		}
		if ( null == user.getMobile() || user.getMobile().length() < 11 ) {
			response.setMassage("手机号码格式错误");
			return response;
		}
		if ( null == user.getGender() ) {
			response.setMassage("用户性别格式错误");
			return response;
		} else {
			response = ValidateUtil.validateIDCard(user.getIdCardNo());
			if ( ResponseHeadCodeEnum.SUCCESS.key() != response.getCode() ) {
				return response;
			}
			response = ValidateUtil.validateMobil(user.getMobile());
			if ( ResponseHeadCodeEnum.SUCCESS.key() != response.getCode() ) {
				return response;
			}
		}
		response.setCode(ResponseHeadCodeEnum.SUCCESS.key());
		return response;
	}
}
