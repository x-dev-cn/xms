package cn.xdev.core.util.office.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;

public class ExcelReader {

	private Workbook workbook = null;
	private Sheet sheet = null;
	private Row row = null;

	public ExcelReader() {
	}

	public ExcelReader(FileInputStream input) {
		try {
//			this.workbook = new SXSSFWorkbook(128);// POI3.8最新的API，解决问题的关键。
			this.workbook = new HSSFWorkbook(input);
			this.sheet = workbook.getSheetAt(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getRowCount() {
		return this.sheet.getLastRowNum();
	}
	
	public void getRow(int index) {
		this.row = this.sheet.getRow(index);
	}
	
	public int getCellCount() {
		return this.row.getLastCellNum();
	}
	
	/**
	 * 获取单元格的值
	 * 
	 * @param index
	 *            列号
	 */
	public String getCell(int index) {
		Cell cell = this.row.getCell((short) index);
		DecimalFormat df = new DecimalFormat("#0.00");
		String strExcelCell = "";
		if (cell != null) { // add this condition
			// judge
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_FORMULA:
//				strExcelCell = "FORMULA ";
				try {
					double d = cell.getNumericCellValue();
					strExcelCell = df.format(d);
				} catch (IllegalStateException e) {
					strExcelCell = String
							.valueOf(cell.getRichStringCellValue());
				}
				break;
			case Cell.CELL_TYPE_NUMERIC: 
				double d = cell.getNumericCellValue();
				strExcelCell = df.format(d);
				break;
			case Cell.CELL_TYPE_STRING:
				strExcelCell = String.valueOf(cell.getRichStringCellValue());//cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_BLANK:
				strExcelCell = "";
				break;
			default:
				strExcelCell = "";
				break;
			}
		}
		return strExcelCell;
	}
}
