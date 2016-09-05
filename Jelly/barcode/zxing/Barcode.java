package zxing;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class Barcode {

	public void zxingBarCode(HttpServletResponse response) {

		int width = 300;
		int height = 300;

		String format = "png";
		String content = "https://www.baidu.com";

		HashMap<EncodeHintType, Object> hashMap = new HashMap<EncodeHintType, Object>();
		hashMap.put(EncodeHintType.CHARACTER_SET, "utf-8");
		hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
		hashMap.put(EncodeHintType.MARGIN, 1);
		OutputStream stream = null;
		try {

			BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height,
					hashMap);

			Path file = new File("D:/cao.png").toPath();

			stream = response.getOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix, format, stream);

			MatrixToImageWriter.writeToPath(bitMatrix, format, file);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.flush();
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
