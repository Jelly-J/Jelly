package test;

import java.awt.Color;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.Barcode128;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

public abstract class Helper {

	public static String generatePdfA4(List<String> codeList, String imgPath) {
		if (codeList == null || codeList.size() == 0) {
			return null;
		}
		for (String barcode : codeList) {
			if (barcode.split(",").length > 25) {
				return null;
			}
		}
		String fileName = new Date().getTime() + ".pdf";
		String pdfPath = imgPath + fileName;
		int columns = 3;
		int rows = 8;
		float padding = 8.2f;
		float spacing = 8f;
		float width = 99f;
		float fontSize = 8.5f;
		BarcodeGeneratePDF(codeList, pdfPath, columns, rows, padding, spacing, width, fontSize);
		return fileName;
	}

	public static void BarcodeGeneratePDF(List<String> codeList, String pdfPath, int columns, int rows, float padding,
			float spacing, float width, float fontSize) {
		Document document = new Document(PageSize.A4, 0, 0, 0, 0);
		try {
			PdfWriter arg = PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
			document.open();
			document.newPage();
			Table table = new Table(columns, rows);
			table.setWidth(width);
			// table.setBorderWidth(Element.ALIGN_CENTER);//测试用
			table.setBorderColor(new Color(255, 255, 255));
			table.setPadding(padding);
			table.setSpacing(spacing);

			for (String barcode : codeList) {
				String barcodes[] = barcode.split(",");
				String code = barcodes[0];
				String num1 = barcodes[0];
				String num2 = barcodes[1];

				Paragraph bunum1 = getParagraph(fontSize, num1);
				Paragraph bunum2 = getParagraph(fontSize, num2);
				com.lowagie.text.Image image = getBarcodeImage(arg, code);
				Cell cell = getCell(bunum1, bunum2, image);

				table.addCell(cell);
			}
			document.add(table);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			document.close();
		}
	}

	public static Cell getCell(Paragraph bunum1, Paragraph bunum2, com.lowagie.text.Image image) {
		Cell cell = new Cell();
		cell.add(image);
		cell.add(bunum1);
		cell.add(bunum2);
		cell.setBorderColor(new Color(255, 255, 255));//设置颜色为白色
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 居中
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		return cell;
	}

	public static Paragraph getParagraph(float fontSize, String num1) {
		// 拼文字
		Font toFont = FontFactory.getFont(FontFactory.COURIER);
		toFont.setSize(fontSize);
		Paragraph bunum = new Paragraph();
		Chunk chunk1 = new Chunk(num1);
		chunk1.setFont(toFont);
		bunum.add(chunk1);
		return bunum;
	}

	public static com.lowagie.text.Image getBarcodeImage(PdfWriter arg, String code) {
		//////////// 获取条码图片
		com.lowagie.text.pdf.Barcode128 barcode128 = new Barcode128();
		PdfContentByte arg0 = new PdfContentByte(arg);
		Color arg1 = new Color(0, 0, 0);
		Color arg2 = new Color(255, 255, 255);
		barcode128.setCode(code);
		barcode128.setAltText(code);
		com.lowagie.text.Image image = barcode128.createImageWithBarcode(arg0, arg1, arg2);
		///////////////
		return image;
	}

}
