package com.liukang.tirrger.view;



import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;

public interface PdfExportService {
     void make(Map<String,Object> model, Document document, PdfWriter pdfWriter,
               HttpServletRequest request);
}
