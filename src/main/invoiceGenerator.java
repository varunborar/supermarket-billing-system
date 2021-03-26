package main;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.layout.property.TextAlignment;


public class InvoiceGenerator {

    private PdfWriter file;
    private PdfDocument pdfDoc;
    private Document invoice;
    private Company comp;
    private Customer cust;
    private boolean itemAdded;
    private Table itemTable;
    
    private String InvoiceNumber;


    public InvoiceGenerator(String invoicePath) {
        try {
            this.itemAdded = false;

            file = new PdfWriter(invoicePath);
            pdfDoc = new PdfDocument(file);
            invoice = new Document(pdfDoc);

            

            pdfDoc.setDefaultPageSize(PageSize.A4);
        } catch (Exception e) {
            e.getCause();
        }
    }

    public void setInvoiceNumber(String InvoiceNumber)
    {
        this.InvoiceNumber = InvoiceNumber;
    }

    public void setCompany(Company comp)
    {
        this.comp = comp;
    }

    public void setCustomer(Customer cust)
    {
        this.cust = cust;
    }

    public void setUpFile() {

        // Creating Header
        float colWidth[] = { 340f, 220f};

        Table header = new Table(colWidth);

        String Name = comp.getCompanyName();
        String Address = comp.getAddressLine1() + "\n" + comp.getAddressLine2() + "\n" + comp.getCity() + ", "+ comp.getState() + "-"+comp.getZip();
        String Invoice = "GST In: " + comp.getGSTNumber() + "\nInvoice: #" +  this.InvoiceNumber ;
        String Contact = "Phone no. : " + comp.getPhoneNumber() + "\nEmail: " + comp.getEmail();
        header.setBackgroundColor(new DeviceRgb(230, 230, 230));
        header.setBorder(Border.NO_BORDER);

        Cell nameCell = new Cell();
        nameCell.add(new Paragraph(Name).setFontSize(20f).setTextAlignment(TextAlignment.CENTER));
        nameCell.setMargin(20f);
        nameCell.setMarginBottom(0f);
        nameCell.setPadding(12f);
        nameCell.setPaddingBottom(2f);
        nameCell.setBorder(Border.NO_BORDER);
        
        Cell addressCell = new Cell();
        addressCell.add(new Paragraph(Address).setFontSize(10f).setTextAlignment(TextAlignment.LEFT));
        addressCell.setMargin(8f);
        addressCell.setMarginTop(0f);
        addressCell.setPadding(8f);
        addressCell.setPaddingTop(0f);
        addressCell.setPaddingLeft(44f);
        addressCell.setBorder(Border.NO_BORDER);
        
        Cell invoiceCell = new Cell();
        invoiceCell.add(new Paragraph(Invoice).setFontSize(10f).setTextAlignment(TextAlignment.RIGHT));
        invoiceCell.setMargin(8f);
        invoiceCell.setPadding(8f);
        invoiceCell.setBorder(Border.NO_BORDER);
        
        Cell contactCell = new Cell();
        contactCell.add(new Paragraph(Contact).setFontSize(10f).setTextAlignment(TextAlignment.RIGHT));
        contactCell.setMargin(8f);
        contactCell.setPadding(8f);
        contactCell.setBorder(Border.NO_BORDER);


        header.addCell(nameCell);
        header.addCell(invoiceCell);
        header.addCell(addressCell);
        header.addCell(contactCell);


        // Customer Details
        float customerColWidth[] = {280f, 280f};
        Table customer = new Table(customerColWidth);
        customer.setMargin(30f);
        customer.setPadding(14f);

        String cName = "Name: \t" + cust.getname();
        String cPhone = "Phone No: \t " + cust.getContact();
        String cEmail = "Email: \t" + cust.getmail();
        String cMethod = "Method:   \t" + cust.getpaymod();
        
        Cell cNameCell = new Cell();
        cNameCell.add(new Paragraph(cName).setFontSize(10f).setTextAlignment(TextAlignment.LEFT));
        cNameCell.setBorder(Border.NO_BORDER);
        Cell cPhoneCell = new Cell();
        cPhoneCell.add(new Paragraph(cPhone).setFontSize(10f).setTextAlignment(TextAlignment.LEFT));
        cPhoneCell.setBorder(Border.NO_BORDER);
        Cell cEmailCell = new Cell();
        cEmailCell.add(new Paragraph(cEmail).setFontSize(10f).setTextAlignment(TextAlignment.LEFT));
        cEmailCell.setBorder(Border.NO_BORDER);
        Cell cMethodCell = new Cell();
        cMethodCell.add(new Paragraph(cMethod).setFontSize(10f).setTextAlignment(TextAlignment.LEFT));
        cMethodCell.setBorder(Border.NO_BORDER);

        customer.addCell(cNameCell);
        customer.addCell(cPhoneCell);
        customer.addCell(cEmailCell);
        customer.addCell(cMethodCell);


        invoice.add(header);
        invoice.add(customer);
    }

    public void addItem(Item i, Integer quantity, int serialNumber)
    {
        if (!this.itemAdded)
        {
            float colWidth[] = {40f, 60f, 240f, 110f,110f};
            this.itemTable = new Table(colWidth);

            itemTable.addCell(new Cell().add(new Paragraph("S. No.").setFontSize(10f).setBold().setTextAlignment(TextAlignment.CENTER)));
            itemTable.addCell(new Cell().add(new Paragraph("Item Code").setFontSize(10f).setBold().setTextAlignment(TextAlignment.CENTER)));
            itemTable.addCell(new Cell().add(new Paragraph("Item Name").setFontSize(10f).setBold().setTextAlignment(TextAlignment.CENTER)));
            itemTable.addCell(new Cell().add(new Paragraph("Quantity").setFontSize(10f).setBold().setTextAlignment(TextAlignment.CENTER)));
            itemTable.addCell(new Cell().add(new Paragraph("Price").setFontSize(10f).setBold().setTextAlignment(TextAlignment.CENTER)));

            this.itemAdded = true;
        }

        //Add for adding item row after creatng item class.
        itemTable.addCell(new Cell().add(new Paragraph(serialNumber + ".").setFontSize(10f).setTextAlignment(TextAlignment.RIGHT)));
        itemTable.addCell(new Cell().add(new Paragraph(i.getItemcode()).setFontSize(10f).setTextAlignment(TextAlignment.RIGHT)));
        itemTable.addCell(new Cell().add(new Paragraph(i.getItemname()).setFontSize(10f).setTextAlignment(TextAlignment.RIGHT)));
        itemTable.addCell(new Cell().add(new Paragraph(quantity + "").setFontSize(10f).setTextAlignment(TextAlignment.RIGHT)));
        itemTable.addCell(new Cell().add(new Paragraph(i.getItemprice()).setFontSize(10f).setTextAlignment(TextAlignment.RIGHT)));
    }

    public void printItemTable()
    {
        invoice.add(this.itemTable);
    }

    public void setFooter(String subTotal, String discount, String total)
    {
        float colWidth[] = {420f, 140f};
        Table footer = new Table(colWidth);
        Cell emptyCell = new Cell();
        emptyCell.add(new Paragraph(""));
        emptyCell.setBorder(Border.NO_BORDER);

        Cell subTotalCell = new Cell();
        subTotalCell.add(new Paragraph("Sub-Total : " + subTotal).setFontSize(10f));
        subTotalCell.setBorder(Border.NO_BORDER);
        Cell discountCell = new Cell();
        discountCell.add(new Paragraph("Discount  : " + discount).setFontSize(10f));
        discountCell.setBorder(Border.NO_BORDER);
        Cell totalCell = new Cell();
        totalCell.add(new Paragraph("Total     : " + total).setFontSize(10f));
        totalCell.setBorder(Border.NO_BORDER);

        footer.addCell(emptyCell);
        footer.addCell(subTotalCell);
        footer.addCell(emptyCell);
        footer.addCell(discountCell);
        footer.addCell(emptyCell);
        footer.addCell(totalCell);
        invoice.add(footer);
        invoice.close();
        
    }

    public static void main(String[] args) {


        // TEST CODE CAN BE REMOVED 

        // InvoiceGenerator inv = new InvoiceGenerator("C:/users/varun/invoice.pdf", new Company(), new Customer(), new String("0001"));
        // inv.setUpFile();
        // inv.addItem(new Item(), 10, 1);
        // inv.printItemTable();
        // inv.setFooter("replace","replace","replace");

        // TEST CODE ENDS
    }
}


