package instrument;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import models.Person;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CreatePDFfilesClass {
    public void createDocumentOfTransakcia(String terminal, Person person, float summ) throws IOException, DocumentException {
        float komisia = (float) (summ * 0.025);

        var doc = new Document();
        String filePath = "F:/terminal/transactions/transaction" + generatePwd() + ".pdf";
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(filePath));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        doc.open();

        BaseFont bf = BaseFont.createFont("F:\\terminal\\font\\times.ttf", "cp1251", BaseFont.EMBEDDED);
        Font caption = new Font(bf, 20, Font.BOLD, new CMYKColor(0, 255, 255,17));


        Paragraph title1 = new Paragraph("ТРАНЗАКЦІЯ", caption);
        title1.setAlignment(Element.ALIGN_CENTER);

        Chapter chapter1 = new Chapter(title1, 1);
        chapter1.setNumberDepth(0);

        Font paragraphFont = new Font(bf, 14);

        PdfPTable t = new PdfPTable(3);

        t.setSpacingBefore(25);
        t.setSpacingAfter(25);

        PdfPCell c1 = new PdfPCell(Phrase.getInstance(""));
        t.addCell(c1);
        PdfPCell c2 = new PdfPCell(new Phrase(String.valueOf(new Paragraph("ПІБ")), paragraphFont));
        t.addCell(c2);
        PdfPCell c3 = new PdfPCell(new Phrase(String.valueOf(new Paragraph("Номер рахунку")), paragraphFont));
        t.addCell(c3);
        t.addCell(new Phrase(String.valueOf(new Paragraph("Платник")), paragraphFont));
        t.addCell(new Phrase(String.valueOf(new Paragraph(terminal)), paragraphFont));
        t.addCell(new Phrase(String.valueOf(new Paragraph(terminal)), paragraphFont));
        t.addCell(new Phrase(String.valueOf(new Paragraph("Отримувач")), paragraphFont));
        t.addCell( new Phrase(String.valueOf(new Paragraph(person.surname + " " + person.name.substring(0, 1).toUpperCase() + "." + person.fathername.substring(0, 1).toUpperCase() + ".")), paragraphFont));
        t.addCell(person.getNumberCode());


        PdfPTable sum = new PdfPTable(2);

        sum.setSpacingBefore(25);
        sum.setSpacingAfter(25);

        PdfPCell t1 = new PdfPCell(new Phrase(String.valueOf(new Paragraph("Сума")), paragraphFont));
        sum.addCell(t1);
        PdfPCell t2 = new PdfPCell(new Phrase(String.valueOf(new Paragraph("Комісія")), paragraphFont));
        sum.addCell(t2);

        sum.addCell(new Phrase(String.valueOf(summ)));
        sum.addCell(new Phrase(String.valueOf(komisia)));

        sum.addCell(new Phrase(String.valueOf(new Paragraph("Загальна сума")), paragraphFont));
        sum.addCell(new Phrase(String.valueOf(summ + (summ * 0.025))));

        Paragraph date = new Paragraph(getDateInTransaction(), paragraphFont);

        Section section1 = chapter1.addSection("");
        section1.add(t);

        section1.add(sum);

        section1.add(date);
        doc.add(chapter1);

        doc.close();
    }

    private String getDateInTransaction() {
        SimpleDateFormat dnt = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss  ");
        Date date = new Date();

        return dnt.format(date);
    }


    private String generatePwd() {
        String charsCaps = "abcdefghijklmnopqrstuvwxyz";
        String nums = "0123456789";
        String passSymbols = charsCaps + nums;
        Random rnd = new Random();

        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sb.append(passSymbols.charAt(rnd.nextInt(passSymbols.length())));
        }
        return sb.toString();
    }

    public void createDocumentOfTransakciaForService(Person person, String service, float summ) throws DocumentException, IOException {
        float komisia = (float) (summ * 0.025);
        var doc = new Document();
        String filePath = "F:/terminal/transactions/transaction" + generatePwd() + ".pdf";
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(filePath));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        doc.open();

        BaseFont bf = BaseFont.createFont("F:\\terminal\\font\\times.ttf", "cp1251", BaseFont.EMBEDDED);
        Font caption = new Font(bf, 20, Font.BOLD, new CMYKColor(0, 255, 255,17));


        Paragraph title1 = new Paragraph("ТРАНЗАКЦІЯ (Термінал №1)", caption);
        title1.setAlignment(Element.ALIGN_CENTER);

        Chapter chapter1 = new Chapter(title1, 1);
        chapter1.setNumberDepth(0);

        Font paragraphFont = new Font(bf, 14);

        PdfPTable t = new PdfPTable(3);

        t.setSpacingBefore(25);
        t.setSpacingAfter(25);

        PdfPCell c1 = new PdfPCell(Phrase.getInstance(""));
        t.addCell(c1);
        PdfPCell c2 = new PdfPCell(new Phrase(String.valueOf(new Paragraph("ПІБ")), paragraphFont));
        t.addCell(c2);
        PdfPCell c3 = new PdfPCell(new Phrase(String.valueOf(new Paragraph("Номер рахунку")), paragraphFont));
        t.addCell(c3);
        t.addCell(new Phrase(String.valueOf(new Paragraph("Платник")), paragraphFont));
        t.addCell(new Phrase(String.valueOf(new Paragraph(person.surname + " " + person.name.substring(0, 1).toUpperCase() + "." + person.fathername.substring(0, 1).toUpperCase() + ".")), paragraphFont));
        t.addCell(person.getNumberCode());

        Paragraph titleService = new Paragraph("Оплата " + service.toLowerCase() + ".", paragraphFont);


        PdfPTable sum = new PdfPTable(2);

        sum.setSpacingBefore(25);
        sum.setSpacingAfter(25);

        PdfPCell t1 = new PdfPCell(new Phrase(String.valueOf(new Paragraph("Сума")), paragraphFont));
        sum.addCell(t1);
        PdfPCell t2 = new PdfPCell(new Phrase(String.valueOf(new Paragraph("Комісія")), paragraphFont));
        sum.addCell(t2);

        sum.addCell(new Phrase(String.valueOf(summ)));
        sum.addCell(new Phrase(String.valueOf(komisia)));

        sum.addCell(new Phrase(String.valueOf(new Paragraph("Загальна сума")), paragraphFont));
        sum.addCell(new Phrase(String.valueOf(summ + (summ * 0.025))));

        Paragraph date = new Paragraph(getDateInTransaction(), paragraphFont);

        Section section1 = chapter1.addSection("");
        section1.add(t);
        section1.add(sum);
        section1.add(date);
        section1.add(titleService);

        doc.add(chapter1);

        doc.close();
    }
}
