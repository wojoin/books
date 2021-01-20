package com.springinaction.training.mvc;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import com.springinaction.training.model.Course;


public class CourseListPdfView extends AbstractPdfView {
  
  protected Document getDocument() {
    // Return a letter-sized, landscape page
    return new Document(PageSize.A4.rotate());
  }
  
  protected void buildPdfDocument(Map model, Document pdfDoc, 
      PdfWriter writer, HttpServletRequest request, 
      HttpServletResponse response) throws Exception {

    Table courseTable = new Table(5);
    courseTable.setWidth(90);
    courseTable.setBorderWidth(1);
    
    courseTable.addCell("ID");
    courseTable.addCell("Name");
    courseTable.addCell("Instructor");
    courseTable.addCell("Start Date");
    courseTable.addCell("EndDate");
    
    Set courseList = (Set) model.get("courseList");
    for (Iterator iter = courseList.iterator(); iter.hasNext();) {
      Course course = (Course) iter.next();
      
      courseTable.addCell(course.getId().toString());
      courseTable.addCell(course.getName());
      courseTable.addCell(course.getInstructor().getLastName());
      courseTable.addCell(course.getStartDate().toString());
      courseTable.addCell(course.getEndDate().toString());
    }
    
    pdfDoc.add(courseTable);
  }
}
