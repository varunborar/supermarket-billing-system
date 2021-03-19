package main;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.kernel.geom.PageSize;

public class invoiceGenerator {
    
    private PdfWriter file;
    private PdfDocument pdfDoc;
    private Document invoice;

    public invoiceGenerator(String invoicePath)
    {
        try{
        file = new PdfWriter(invoicePath);

        }
        catch(Exception e)
        {
            e.getCause();
        }
    }
}
