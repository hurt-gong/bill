package cn.yu2.baomihua.core.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.yu2.baomihua.constant.ManageConstant;

public class POIUtil {

	private static Logger logger = LoggerFactory.getLogger(POIUtil.class);


	/**
	 * 根据输入流创建工作本
	 * @param inputStream 输入流
	 * @return new HSSFWorkbook()
	 */
	public static HSSFWorkbook getWorkbook( InputStream inputStream ) throws IOException {
		HSSFWorkbook workBook = new HSSFWorkbook(inputStream);
		return workBook;
	}


	public static HSSFSheet getSheet( InputStream inputStream, Integer index ) throws IOException {
		HSSFWorkbook workbook = getWorkbook(inputStream);
		HSSFSheet sheet = getSheet(workbook, index);
		return sheet;
	}


	/**
	 * 获取工作本工作表
	 * @param workbook HSSFWorkbook
	 * @param index 工作表索引
	 * @return HSSFSheet
	 */
	public static HSSFSheet getSheet( HSSFWorkbook workbook, Integer index ) {
		HSSFSheet sheet = workbook.getSheetAt(index);
		return sheet;
	}


	public static String getCellValue( Cell cell ) {
		String value = null;
		if ( cell == null || cell.getCellStyle() == null ) return null;
		switch ( cell.getCellType() ) {
			case Cell.CELL_TYPE_BLANK :
				break;
			case Cell.CELL_TYPE_STRING :
				value = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_NUMERIC :
				if ( HSSFDateUtil.isCellDateFormatted(cell) ) {
					SimpleDateFormat sdf = null;
					if ( cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm") ) {
						sdf = new SimpleDateFormat("HH:mm");
					} else {
						sdf = new SimpleDateFormat("yyyy-MM-dd");
					}
					Date date = cell.getDateCellValue();
					value = sdf.format(date);
				} else if ( cell.getCellStyle().getDataFormat() == 14
						|| cell.getCellStyle().getDataFormat() == 31 || cell.getCellStyle().getDataFormat() == 57
						|| cell.getCellStyle().getDataFormat() == 58 ) {
					/*yyyy-MM-dd--14,yyyy年m月d日--31,yyyy年m月--57,m月d日--58,HH:mm--20,h时mm分  --32 */
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					double val = cell.getNumericCellValue();
					Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(val);
					value = sdf.format(date);
				} else {
					/**
					double val = cell.getNumericCellValue();  
					CellStyle style = cell.getCellStyle();  
					DecimalFormat format = new DecimalFormat();  
					String temp = style.getDataFormatString();  
					// 单元格设置成常规  
					if (temp.equals("General")) {  
					    format.applyPattern("#");  
					}  
					value = format.format(value);  
					*/
					DecimalFormat df = new DecimalFormat("0");
					value = df.format(cell.getNumericCellValue());
				}
				break;
			case Cell.CELL_TYPE_ERROR :
				logger.info("POIUtil.getCellValue()-->CELL_TYPE_ERROR");
				break;
			default:
				logger.info("POIUtil.getCellValue()-->CELL_TYPE_MISMATCH");
				break;
		}
		return value;
	}


	/**封装response,设置头信息和中文文件名*/
	public static HttpServletResponse excelResponse( HttpServletRequest request, HttpServletResponse response,
			String fileName ) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("applicationnd.ms-excel");
		try {
			String agent = request.getHeader("USER-AGENT");
			if ( null != agent && -1 != agent.indexOf("MSIE") || null != agent && -1 != agent.indexOf("Trident") ) {// ie  
				fileName = java.net.URLEncoder.encode(fileName, "UTF8");
			} else if ( null != agent && -1 != agent.indexOf("Mozilla") ) {// 火狐,chrome等  
				if(fileName.getBytes().equals("UTF-8")){
				}else{
					fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
				}
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		response.setHeader("Content-Disposition", "attachment;fileName=" + fileName + ManageConstant.EXCEL_EXTENTSION);
		return response;
	}


	/**创建工作本*/
	public static HSSFWorkbook createWorkbook() {
		return new HSSFWorkbook();
	}


	/**
	 * 创建工作表
	 * @param workbook 工作簿
	 * @param sheetName sheet页名
	 * @return HSSFSheet
	 */
	public static HSSFSheet createSheet( HSSFWorkbook workbook, String sheetName, String[] sheetTitle ) {
		HSSFSheet sheet = workbook.createSheet(sheetName);
		HSSFCellStyle titleStyle = POIUtil.getTitleStyle(workbook);
		POIUtil.writeTitle(0, sheet, sheetTitle, titleStyle);
		return sheet;
	}


	/**
	 * 写sheet标题
	 * @param sheet
	 * @param titles 表头内容
	 */
	public static void writeTitle( Integer rowNum, HSSFSheet sheet, String[] titles, HSSFCellStyle style ) {
		HSSFRow row = sheet.createRow(rowNum);
		int width = 20; //默认单元格宽度
		for ( int i = 0 ; i < titles.length ; i++ ) {
			if ( titles[i].length() > 20 && titles[i].length() < 60 ) {
				width = titles[i].length();
			}
			sheet.setColumnWidth(i, width * 256);
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(titles[i]);
			cell.setCellStyle(style);
			width = 20;
		}
	}


	/**
	 * 写单元格
	 * @param rowNum 行号
	 * @param sheet
	 */
	public static void writeCellValue( HSSFSheet sheet, Integer rowNum, Integer cellNum, String value ) {
		HSSFRow row = sheet.getRow(rowNum);
		if ( row == null ) row = sheet.createRow(rowNum);
		HSSFCell cell = row.getCell(cellNum);
		if ( cell == null ) cell = row.createCell(cellNum);
		cell.setCellValue(value);
	}


	/**
	 * 标题行样式
	 * @param workbook
	 * @return
	 */
	public static HSSFCellStyle getTitleStyle( HSSFWorkbook workbook ) {
		HSSFCellStyle style = workbook.createCellStyle();
		// 边框
		style.setBorderTop(HSSFCellStyle.BORDER_THIN); // 上边框
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 左边框
		style.setBorderRight(HSSFCellStyle.BORDER_THIN); // 右边框
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 设置单元格字体居中
		HSSFFont font = workbook.createFont(); // 设置字体
		font.setFontHeightInPoints((short) 16);// 设置字体大小
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		font.setFontHeightInPoints((short) 12);
		style.setFont(font);
		return style;
	}


	/**
	 * 通过验证实现下拉列表
	 * @param sheet sheet页
	 * @param listOfValues 内容
	 * @param firstRow 起始行
	 * @param lastRow 结束行
	 * @param firstColumn 启示列
	 * @param lastColumn 结束列
	 * @return
	 */
	public static HSSFSheet addDropDownListHSSFDV( HSSFSheet sheet, String[] listOfValues, Integer firstRow,
			Integer lastRow, Integer firstColumn, Integer lastColumn ) {
		DataValidationHelper dvHelper = sheet.getDataValidationHelper();
		DataValidationConstraint dvConstraint = dvHelper.createExplicitListConstraint(listOfValues);
		CellRangeAddressList addressList = new CellRangeAddressList(firstRow, lastRow, firstColumn, lastColumn);
		DataValidation validation = dvHelper.createValidation(dvConstraint, addressList);
		sheet.addValidationData(validation);
		return sheet;
	}


	/**
	 * 通过验证实现下拉列表：
	 * 解决
	 * 	Exception in thread "main" java.lang.IllegalArgumentException: String literals in formulas can't be bigger than 255 characters ASCII
	 * 通过引用另一个sheet页的数据  
	 * tips：
	 * 	createFormulaListConstraint("hidden"):hidden页数据列与sheet页数据列一样
	 * 	createFormulaListConstraint("'hidden'!$A$1:$A$"+listOfValues.length):hidden页数据为0与sheet页数据列不一样
	 * @param sheet sheet页
	 * @param listOfValues 内容
	 * @param firstRow 起始行
	 * @param lastRow 结束行
	 * @param firstColumn 起始列
	 * @param lastColumn 结束列
	 * @param hiddenColumn 隐藏sheet页中的列
	 * @return
	 */
	public static HSSFSheet addDropDownListHSSFDV( HSSFWorkbook workbook, HSSFSheet sheet, String[] listOfValues,
			Integer firstRow, Integer lastRow, Integer firstColumn, Integer lastColumn, Integer hiddenColumn ) {
		HSSFSheet hidden = workbook.createSheet("hidden");
		workbook.setSheetHidden(1, true);
		for ( int i = 0 , length = listOfValues.length ; i < length ; i++ ) {
			writeCellValue(hidden, i, hiddenColumn, listOfValues[i]);
		}
		Name namedCell = workbook.createName();
		namedCell.setNameName("hidden");
		namedCell.setRefersToFormula("hidden!A1:A" + listOfValues.length);
		//		DVConstraint constraint = DVConstraint.createFormulaListConstraint("hidden");//listOfValues数据选中后下一个cell中消失
		DVConstraint constraint = DVConstraint.createFormulaListConstraint("'hidden'!$A$1:$A$" + listOfValues.length);//listOfValues数据选中后下一个cell不消失
		CellRangeAddressList addressList = new CellRangeAddressList(firstRow, lastRow, firstColumn, lastColumn);
		HSSFDataValidation validation = new HSSFDataValidation(addressList, constraint);
		sheet.addValidationData(validation);
		return sheet;
	}


	/**
	 * 联系方式验证： 8位座机+11位手机
	 * @param sheet
	 * @param firstRow
	 * @param lastRow
	 * @param firstColumn
	 * @param lastColumn
	 * @return
	 */
	public static HSSFSheet addPhoneHSSFDV( HSSFSheet sheet, Integer firstRow, Integer lastRow, Integer firstColumn,
			Integer lastColumn ) {
		addIntegerHSSFDV(sheet, firstRow, lastRow, firstColumn, lastColumn, DVConstraint.ValidationType.INTEGER,
			DVConstraint.OperatorType.BETWEEN, "11111111", "19999999999");
		return sheet;
	}


	/**
	 * 手机号码验证： 11位手机
	 * @param sheet
	 * @param firstRow
	 * @param lastRow
	 * @param firstColumn
	 * @param lastColumn
	 * @return
	 */
	public static HSSFSheet addMobileHSSFDV( HSSFSheet sheet, Integer firstRow, Integer lastRow, Integer firstColumn,
			Integer lastColumn ) {
		addIntegerHSSFDV(sheet, firstRow, lastRow, firstColumn, lastColumn, DVConstraint.ValidationType.INTEGER,
			DVConstraint.OperatorType.BETWEEN, "13000000000", "19999999999");
		return sheet;
	}


	/**
	 * 日期验证：范围 2015-1-1 至 2999-12-31，格式 YYYY-MM-DD
	 * @param sheet
	 * @param firstRow
	 * @param lastRow
	 * @param firstColumn
	 * @param lastColumn
	 * @return
	 */
	public static HSSFSheet addDataHSSFDV( HSSFSheet sheet, Integer firstRow, Integer lastRow, Integer firstColumn,
			Integer lastColumn ) {
		DVConstraint dvConstraint = DVConstraint.createDateConstraint(DataValidationConstraint.OperatorType.BETWEEN,
			"2015-1-1", "2999-12-31", "YYYY-MM-DD");
		CellRangeAddressList addressList = new CellRangeAddressList(firstRow, lastRow, firstColumn, lastColumn);
		HSSFDataValidation validation = new HSSFDataValidation(addressList, dvConstraint);
		sheet.addValidationData(validation);

		return sheet;
	}


	/**
	 * To obtain a validation that would check the value entered was, for example, an integer between 10 and 100, use the
	 * DVConstraint.createNumericConstraint(int, int, String, String) factory method. 
	 * dvConstraint = DVConstraint.createNumericConstraint(DVConstraint.ValidationType.INTEGER,DVConstraint.OperatorType.BETWEEN, "10", "100");
	 * 
	 * @param sheet sheet页
	 * @param listOfValues 内容
	 * @param firstRow 起始行
	 * @param lastRow 结束行
	 * @param firstColumn 启示列
	 * @param lastColumn 结束列
	 * @param validationType 验证类型
	 * @param comparisonOperator 比较运算符
	 * @param expr1 表达式
	 * @param expr2 表达式
	 * @return
	 */
	private static HSSFSheet addIntegerHSSFDV( HSSFSheet sheet, Integer firstRow, Integer lastRow, Integer firstColumn,
			Integer lastColumn, Integer validationType, Integer comparisonOperator, String expr1, String expr2 ) {
		DVConstraint dvConstraint = DVConstraint.createNumericConstraint(validationType, comparisonOperator, expr1,
			expr2);
		CellRangeAddressList addressList = new CellRangeAddressList(firstRow, lastRow, firstColumn, lastColumn);
		HSSFDataValidation validation = new HSSFDataValidation(addressList, dvConstraint);
		sheet.addValidationData(validation);
		return sheet;
	}


}
