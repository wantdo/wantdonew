package com.wantdo.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class ExcelUtil {
	
	private Workbook workbook=null;
	private List<String[]> dataList=new ArrayList<String[]>();

	public ExcelUtil() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExcelUtil(String path){
		InputStream is=null;
		try {
			is=new BufferedInputStream(new FileInputStream(path));
			workbook=WorkbookFactory.create(is);
		} catch (Exception e) {
			// TODO: handle exception
		}
		getAllData(0);
	}
	
	public ExcelUtil(File file){
		InputStream is=null;
		try {
			is=new BufferedInputStream(new FileInputStream(file));
			workbook=WorkbookFactory.create(is);
		} catch (Exception e) {
			// TODO: handle exception
		}
		getAllData(0);
	}
	
	/**
	 * 
	* @Title: getAllData 
	* @Description: 取excel所有数据
	* @param @param sheetIndex
	* @param @return    设定文件 
	* @return List<String[]>    返回类型 
	* @throws
	 */
	public List<String[]> getAllData(int sheetIndex){
		int columnNum=0;
		Sheet sheet=workbook.getSheetAt(sheetIndex);
		if (sheet.getRow(0)!=null) {
			columnNum=sheet.getRow(0).getLastCellNum()-sheet.getRow(0).getFirstCellNum();
		}
		if (columnNum>0) {
			for(Row row:sheet){
				String[] singleRow=new String[columnNum];
				int n=0;
				for(int i=0;i<columnNum;++i){
					Cell cell=row.getCell(i, Row.CREATE_NULL_AS_BLANK);
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BLANK:
						singleRow[n]="";
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						singleRow[n]=Boolean.toString(cell.getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							singleRow[n]=new SimpleDateFormat("yyyy_MM_dd").format(cell.getDateCellValue());
						}else {
							cell.setCellType(Cell.CELL_TYPE_STRING);
							String temp=cell.getStringCellValue();
							if (temp.indexOf(".")>-1) {
								singleRow[n]=String.valueOf(new Double(temp)).trim();
							}else {
								singleRow[n]=temp.trim();
							}
						}
						break;
					case Cell.CELL_TYPE_STRING:
						singleRow[n]=cell.getStringCellValue().trim();
						break;
					case Cell.CELL_TYPE_ERROR:
						singleRow[n]="";
						break;
					case Cell.CELL_TYPE_FORMULA:
						cell.setCellType(cell.CELL_TYPE_STRING);
						singleRow[n]=cell.getStringCellValue();
						if (singleRow[n]!=null) {
							singleRow[n]=singleRow[n].replaceAll("#N/A", "").trim();
						}
						break;
					default:
						singleRow[n]="";
						break;
					}
					++n;
				}
				if ("".equals(singleRow[0])) {
					continue;
				}
				dataList.add(singleRow);
			}
		}
		return dataList;
	}
	
	/**
	 * 
	* @Title: getRowNum 
	* @Description: 返回Excel行数，实际行数要加1
	* @param @param sheetIndex
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int getRowNum(int sheetIndex){
		/*
		Sheet sheet=workbook.getSheetAt(sheetIndex);
		return sheet.getLastRowNum();
		*/
		return dataList.size(); 
	}
	
	/**
	 * 
	* @Title: getColumnNum 
	* @Description: 返回Excel列数，实际列数要加1
	* @param @param sheetIndex
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int getColumnNum(int sheetIndex){
		Sheet sheet=workbook.getSheetAt(sheetIndex);
		Row row=sheet.getRow(0);
		if (row!=null&&row.getLastCellNum()>0) {
			return row.getLastCellNum();
		}else
			return 0;
	}
	
	/**
	 * 
	* @Title: getRowData 
	* @Description: 获取某一行数据
	* @param @param sheetIndex
	* @param @param rowIndex
	* @param @return    设定文件 
	* @return String[]    返回类型 
	* @throws
	 */
	public String[] getRowData(int sheetIndex,int rowIndex){
		if (rowIndex>this.getRowNum(sheetIndex)) {
			return null;
		}else {
			return dataList.get(rowIndex);
		}
	}
	
	/**
	 * 
	* @Title: getColumnData 
	* @Description: 获取某一列数据
	* @param @param sheetIndex
	* @param @param colIndex
	* @param @return    设定文件 
	* @return String[]    返回类型 
	* @throws
	 */
	public String[] getColumnData(int sheetIndex,int colIndex){
		String[] dataArray=null;
		if (colIndex>getColumnNum(sheetIndex)) {
			return null;
		}else {
			if (dataList!=null&&dataList.size()>0) {
				dataArray=new String[getRowNum(sheetIndex)+1];
				int index=0;
				for(String[] rowData:dataList){
					if (rowData!=null) {
						dataArray[index++]=rowData[colIndex];
					}
				}
			}
			return dataArray;
		}
	}
	
	public List<String[]> getRowAndColData(int sheetIndex,int[] colArray,int startRow,int endRow){
		List<String[]> list=new ArrayList<String[]>();
		String[] dataArray=null;
		for(int i=0;i<colArray.length;++i){
			if (colArray[i]>getColumnNum(sheetIndex)) {
				return null;
			}
		}
		if (startRow>endRow||startRow>getRowNum(sheetIndex)
				||endRow>getRowNum(sheetIndex)) {
			return null;
		}
		if (dataList!=null&&dataList.size()>0) {
			for(int row=startRow;row<=endRow;++row){
				int index=0;
				dataArray=new String[colArray.length];
				for(int col=0;col<colArray.length;++col){
					dataArray[index++]=dataList.get(row)[colArray[col]];
				}
				list.add(dataArray);
			}
		}
		return list;
	}
	
	//获取表头样式
	public HSSFCellStyle getHeaderStyle(HSSFWorkbook workbook){
		//生成一个样式
        HSSFCellStyle style=workbook.createCellStyle();
       //设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
       //生成一个字体
        HSSFFont font=workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short)12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
       //把字体应用到当前的样式
        style.setFont(font);
        return style;
	}
	
	//获取内容样式
	public HSSFCellStyle getContentStyle(HSSFWorkbook workbook){
		//生成一个样式
		HSSFCellStyle style=workbook.createCellStyle();
		//设置这些样式
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		return style;
	}
	
	//获取快递状态样式
	public HSSFCellStyle getExpContentStyle(HSSFWorkbook workbook){
		//生成一个样式
		HSSFCellStyle style=workbook.createCellStyle();
		//设置这些样式
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		return style;
	}
	
	public void writeExpExcel(List<String[]> list,String[] format,File excelFile){
		HSSFWorkbook hWorkbook=new HSSFWorkbook();
		HSSFSheet sheet=hWorkbook.createSheet("sheet1");
		for(int i=0;i<format.length;++i){
			if (i!=format.length-1) {
				sheet.setColumnWidth(i, 3500);
			}else {
				sheet.setColumnWidth(i, 20000);
			}
		}
		
		//获取第一行
		HSSFRow row=sheet.createRow(0);
		HSSFCellStyle headerStyle=getHeaderStyle(hWorkbook);
		for(int i=0;i<format.length;++i){
			HSSFCell headerCell=row.createCell(i);
			headerCell.setCellValue(format[i]);
			headerCell.setCellStyle(headerStyle);
		}
		//将list中的数据添加到excel中
		HSSFCellStyle contentStyle=getContentStyle(hWorkbook);
		HSSFCellStyle expContentStyle=getExpContentStyle(hWorkbook);
		for(int j=0;j<list.size();++j){
			HSSFRow row2=sheet.createRow(j+1);
			String[] temp=list.get(j);
			for(int k=0;k<temp.length;++k){
				HSSFCell contentCell=row2.createCell(k);
				contentCell.setCellValue(temp[k]);
				if (k!=temp.length-1) {
					contentCell.setCellStyle(contentStyle);
				}else
					contentCell.setCellStyle(expContentStyle);
			}
			
		}
		try {
			FileOutputStream os=new FileOutputStream(excelFile);
			hWorkbook.write(os);
			os.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
