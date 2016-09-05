package test;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.Barcode128;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

public class TestPdf {

	public static void main(String[] args) throws FileNotFoundException, DocumentException {

		Document document = new Document(PageSize.A4, 10, 10, 10, 10);
		PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(new File("d:/123456.pdf")));
		document.open();
		PdfContentByte arg0 = new PdfContentByte(pdfWriter);
		Barcode128 barcode128 = new Barcode128();
		// Color arg2 = new Color(255, 255, 255);
		barcode128.setCode("123456789");
		barcode128.setAltText("123456789");
		Image image = barcode128.createImageWithBarcode(arg0, null, null);

		Table table = new Table(4, 5);
		table.setBorderColor(new Color(255, 255, 255));
		table.setWidth(99f);
		Cell cell = new Cell();
		cell.setBorderColor(new Color(255, 255, 255));
		cell.add(image);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 居中
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		for (int i = 0; i < 100; i++) {
			table.addCell(cell);
		}
		document.add(table);
		document.close();
	}

}
