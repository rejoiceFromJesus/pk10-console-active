package com.pk10.active.console.view;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.pk10.active.console.common.util.FreemarkerUtils;

/**
 * Created by Administrator on 2016/8/19 0019.
 */
public class FormPdfview extends AbstractITextPdfView {
	
	 private static final String imageFilePath = "D:b.jpg";
	
    @Override
    protected void buildPdfDocument(Map<String, Object> model,
                                    Document document, PdfWriter writer, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
    	 response.setContentType("application/pdf");
         response.setContentType("application/pdf;charset=utf-8");
       // response.setHeader("Content-disposition", "attachment;filename=" + new Date() +".pdf");
        /* Rectangle rect = new Rectangle(PageSize.A4);
         //page size
         document.setMargins(10, 15, 10, 15);
         PdfWriter instance = PdfWriter.getInstance(document, response.getOutputStream());
         //author
         document.addTitle("yaoyingli.com");
         // 创建字体，设置family，size，style,还可以设置color
         BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
         Font fontChinese = new Font(bfChinese, 8, Font.NORMAL);
         Font titleChinese = new Font(bfChinese, 20, Font.BOLD);
         Font BoldChinese = new Font(bfChinese, 14, Font.BOLD);
         Font subBoldFontChinese = new Font(bfChinese, 8, Font.BOLD);
         //
         document.open();
         Paragraph title = new Paragraph("进货单",BoldChinese);
         title.setAlignment(Element.ALIGN_CENTER);
         title.setLeading(10f);
         title.setSpacingBefore(30f);
         title.setSpacingAfter(5f);
         document.add(title);
         //clum num ,clum whdth
         float[] whdths = {10f,12f,20f,15f,15f,15f,20f,10f,15f};
         PdfPTable table = new PdfPTable(whdths);
         table.setWidthPercentage(100);
         //th
         PdfPCell cell = null;
         for (int colNum = 0; colNum < 3; colNum++) {
             cell = new PdfPCell(new Paragraph(""+colNum,subBoldFontChinese));
             cell.setHorizontalAlignment(Element.ALIGN_CENTER);
             table.addCell(cell);
         }
         //table.getDefaultCell().setPadding(3);
         //tr data
         PdfPCell dataCell = new PdfPCell();
         dataCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                 for (int j=0; j<4;j++){
                 dataCell.setPhrase(new Paragraph(j+"",fontChinese));
                 table.addCell(dataCell);
                 dataCell.setPhrase(new Paragraph(j+"",fontChinese));
                 table.addCell(dataCell);
                 dataCell.setPhrase(new Paragraph(j+"",fontChinese));
                 table.addCell(dataCell);
                 dataCell.setPhrase(new Paragraph(j+"",fontChinese));
                 table.addCell(dataCell);
             }
         //footer
         float[] footwhdths = {30f};
         PdfPTable foottable = new PdfPTable(footwhdths);
         PdfPCell footcell = new PdfPCell(new Paragraph("  yaoyingli.com  |  " + new Date(),subBoldFontChinese));
         footcell.setHorizontalAlignment(Element.ALIGN_LEFT);
         footcell.setBorder(0);
         footcell.setPaddingLeft(-50f);
         foottable.addCell(footcell);
         //end
         document.add(table);
         document.add(foottable);
         
         
         //wartermark
         document.close();*/
         
         //from ftl
         this.parseFromHTml(model, document, writer, request, response);
    }
    
    private static void parseFromHTml(Map<String, Object> model,
            Document document, PdfWriter writer, HttpServletRequest request,
            HttpServletResponse response) throws IOException{
    	document.addHeader("我是页眉", "页眉内容");
    	document.addAuthor("rejoice");
    	document.setPageCount(12);
    	document.addTitle("我是title");
    	String[] paths = request.getRequestURI().split("/");
    	String template = paths[paths.length-1];  
    	//download 
        //response.setHeader("Content-Disposition", "attachment; filename=\"simpleForm.pdf\"");
    	URL fileResource = FormPdfview.class.getResource("/templates/ftl/pdf");
    	model.put("contextPath", request.getContextPath());
        String html = FreemarkerUtils.loadFtlHtml(new File(fileResource.getFile()), template.substring(0,template.lastIndexOf("."))+".ftl", model);
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new StringReader(html));
    }


    /*private static final Font getChineseFont(float size) {
        Font FontChinese = null;
        try {
            BaseFont bfChinese = BaseFont.createFont("STSong-Light",
                    "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            FontChinese = new Font(bfChinese, size, Font.NORMAL);
        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        return FontChinese;
    }*/
}
