package com.springinaction.training.mvc;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.springinaction.training.model.Course;


public class CourseListExcelView extends AbstractExcelView {
  
  protected void buildExcelDocument(Map model, HSSFWorkbook wb,
      HttpServletRequest request, HttpServletResponse response) 
      throws Exception {

    Set courses = (Set) model.get("courseList");
    
    HSSFSheet sheet = wb.createSheet("Courses");
    HSSFRow header = sheet.createRow(0);
    header.createCell((short)0).setCellValue("ID");
    header.createCell((short)1).setCellValue("Name");
    header.createCell((short)2).setCellValue("Instructor");
    header.createCell((short)3).setCellValue("Start Date");
    header.createCell((short)4).setCellValue("End Date");
    header.createCell((short)5).setCellValue("Students");

    HSSFCellStyle cellStyle = wb.createCellStyle();
    cellStyle.setDataFormat(
        HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
    
    int rowNum = 1;
    for (Iterator iter = courses.iterator(); iter.hasNext();) {
      Course course = (Course) iter.next();
      
      HSSFRow row = sheet.createRow(rowNum++);
      row.createCell((short)0).setCellValue(
          course.getId().toString());
      row.createCell((short)1).setCellValue(course.getName());
      row.createCell((short)2).setCellValue(
          course.getInstructor().getLastName());
      row.createCell((short)3).setCellValue(course.getStartDate());
      row.getCell((short)3).setCellStyle(cellStyle);
      row.createCell((short)4).setCellValue(course.getEndDate());
      row.getCell((short)4).setCellStyle(cellStyle);
      row.createCell((short)5).setCellValue(
          course.getStudents().size());
    }
    
    HSSFRow row = sheet.createRow(rowNum);
    row.createCell((short)0).setCellValue("TOTAL:");
    String formula = "SUM(F2:F"+rowNum+")";
    row.createCell((short)5).setCellFormula(formula);
  }
}
