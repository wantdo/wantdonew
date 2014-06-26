package com.wantdo.test;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;

import com.wantdo.utils.ExcelUtil;

public class ExcelTest {
	
	ExcelUtil excelUtil=new ExcelUtil("d:\\22.xlsx");

	@Test
	public void testGetAllData() {
		excelUtil.getAllData(0);
	}
	
	@Test
	public void testGetRowNum(){
		assertEquals(75, excelUtil.getRowNum(0));
	}
	
	@Test
	public void testGetColumnNum(){
		assertEquals(32, excelUtil.getColumnNum(0));
	}
	
	@Test
	public void getRowData(){
		String[] row=excelUtil.getRowData(0, 0);
		System.out.println(row.length);
		for(int i=0;i<row.length;++i){
			System.out.println(row[i]);
		}
	}
	
	@Test
	public void getColumnData(){
		String[] col=excelUtil.getColumnData(0, 0);
		System.out.println(col.length);
		for(int i=0;i<col.length;++i){
			System.out.println(col[i]);
		}
	}
	
	@Test
	public void getDateValue(){
		InputStream is=null;
		Workbook workbook=null;
		try {
			is=new BufferedInputStream(new FileInputStream("d:\\test.xlsx"));
			workbook=WorkbookFactory.create(is);
			Cell cell=workbook.getSheetAt(0).getRow(25).getCell(0, Row.CREATE_NULL_AS_BLANK);
			int type=cell.getCellType();
			System.out.println(type);
			if (DateUtil.isCellDateFormatted(cell)) {
				Date date=cell.getDateCellValue();
				System.out.println(String.valueOf(date));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
