package test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 
 * 接口测试
 * 
 * 以下代码只是为了方便开发者测试而提供的样例代码，开发者可以根据自己的业务需要，按照技术文档编写，并非一定要使用该代码。
 * 该代码仅供学习和研究信信客接口使用，只是提供一个参考。
 * 
 * @author chenfan
 * @version 1.0, 2015/10/07
 *
 */
public class Infomation {

	// 转码
	public String encode(String input) throws Exception {
		return URLEncoder.encode(input, "UTF-8");
	}

	// 发起 POST 请求
	public void post() throws Exception {

		// 参数拼装
		StringBuffer param = new StringBuffer();
		param.append("").append(encode("dev_id")).append("=").append(encode("sdded54uu9374b2it62e3e35271ec6eu"));
		param.append("&").append(encode("sign")).append("=").append(encode("se0vna60e4dd453e870ww67mwd6ee0vc"));
		param.append("&rec_num=13800138000");

		// 打开连接
		URL url = new URL("http://www.xinxinke.com/api/account");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestMethod("POST");
		conn.setUseCaches(false);
		conn.connect();
System.out.println(param.toString());
		// 输出参数
		DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
		dos.writeBytes(param.toString());
		dos.flush();
		dos.close();

		// 读取响应
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		String line = br.readLine();
		br.close();

		// 关闭连接
		conn.disconnect();

		System.out.println(line);
	}

	public static void main(String[] args) throws Exception {
		Infomation t = new Infomation();
		t.post();
	}

}