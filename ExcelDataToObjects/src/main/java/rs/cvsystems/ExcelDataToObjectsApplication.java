package rs.cvsystems;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import rs.cvsystems.Employee;

@SpringBootApplication
public class ExcelDataToObjectsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExcelDataToObjectsApplication.class, args);

		List excelToList = new ArrayList();
		int rowTotal = 0;
		int columnTotal = 0;
		try {

			FileInputStream file = new FileInputStream(new File("C:\\Users\\user\\Desktop\\emps.xlsx"));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			rowTotal = sheet.getLastRowNum();
			columnTotal = sheet.getRow(0).getLastCellNum();
			if ((rowTotal > 0) || (sheet.getPhysicalNumberOfRows() > 0)) {
				rowTotal++;
			}

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			
			System.out.println("Printed excel table");
			
			while (rowIterator.hasNext()) {

				Row row = rowIterator.next();
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();
					
					// Check the cell type and format accordingly
					switch (cell.getCellType()) {

					case Cell.CELL_TYPE_NUMERIC:
						excelToList.add(cell.getNumericCellValue());
						System.out.print(cell.getNumericCellValue() + " ");
						break;
					case Cell.CELL_TYPE_STRING:
						excelToList.add(cell.getStringCellValue());
						System.out.print(cell.getStringCellValue() + " ");
						break;
					}
				}
				System.out.println("");
			}
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

//		System.out.println("Rows " + rowTotal);
//		System.out.println("Columns " + columnTotal);
//		System.out.println(excelToList);

		int from = 0;
		int to = columnTotal;

		Employee emp = new Employee();
		List<Employee> emps = new ArrayList<Employee>();
		
		for (int i = from; i < excelToList.size(); i = i + columnTotal) {
//			System.out.println(excelToList.subList(i, to));
			emp = new Employee(excelToList.subList(i, to).get(0).toString(), excelToList.subList(i, to).get(1).toString(),
					excelToList.subList(i, to).get(2).toString());
			emps.add(emp);
			to = to + columnTotal;

		}
		emps.remove(0); // Remove attribute names from list (They can be used to name table columns in entities

		System.out.println("List of objects: " + emps);
	}
}
