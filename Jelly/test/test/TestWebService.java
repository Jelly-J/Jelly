package test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.spi.http.HttpHandler;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;

import com.sun.net.httpserver.HttpsParameters;

import model.WebServiceRequest;

public class TestWebService {

	public static final String GET_URL = "http://localhost:8080/welcome1";

	public static final String POST_URL = "http://localhost:8080/omniv4/webservice/aa";

	public static void main(String[] args) throws Exception {
		urlPost();
		// URL postUrl = new URL(POST_URL);
		// // 根据拼凑的URL，打开连接，URL.openConnection函数会根据URL的类型，
		// // 返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection
		// HttpURLConnection connection = (HttpURLConnection)
		// postUrl.openConnection();
		//
		// WebServiceRequest<String> webServiceRequest = new
		// WebServiceRequest<>();
		// webServiceRequest.setPassword("aa");
		// webServiceRequest.setUserName("aa");
		// webServiceRequest.setData("aa");
		//
		// // 设置是否向connection输出，因为这个是post请求，参数要放在
		// // http正文内，因此需要设为true
		// connection.setDoOutput(true);
		// // Read from the connection. Default is true.
		// connection.setDoInput(true);
		// // Set the post method. Default is GET
		// connection.setRequestMethod("POST");
		// // Post cannot use caches
		// // Post 请求不能使用缓存
		// connection.setUseCaches(false);
		// // This method takes effects to
		// // every instances of this class.
		// // URLConnection.setFollowRedirects是static函数，作用于所有的URLConnection对象。
		// // connection.setFollowRedirects(true);
		//
		// // This methods only
		// // takes effacts to this
		// // instance.
		// // URLConnection.setInstanceFollowRedirects是成员函数，仅作用于当前函数
		// connection.setInstanceFollowRedirects(true);
		// // Set the content type to urlencoded,
		// // because we will write
		// // some URL-encoded content to the
		// // connection. Settings above must be set before connect!
		// // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
		// // 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
		// // 进行编码
		// connection.setRequestProperty("Content-Type",
		// "application/x-www-form-urlencoded");
		// // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
		// // 要注意的是connection.getOutputStream会隐含的进行connect。
		// connection.connect();
		// DataOutputStream out = new
		// DataOutputStream(connection.getOutputStream());
		//
		// // The URL-encoded contend
		// // 正文，正文内容其实跟get的URL中'?'后的参数字符串一致
		// // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写道流里面
		// out.writeBytes("req" + webServiceRequest);
		// out.flush();
		// out.close(); // flush and close
		// BufferedReader reader = new BufferedReader(new
		// InputStreamReader(connection.getInputStream()));
		// String line;
		// System.out.println("=============================");
		// System.out.println("Contents of post request");
		// System.out.println("=============================");
		// while ((line = reader.readLine()) != null) {
		// System.out.println(line);
		// }
		// System.out.println("=============================");
		// System.out.println("Contents of post request ends");
		// System.out.println("=============================");
		// reader.close();
		// connection.disconnect();
	}

	public static void urlPost() {

		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost
		HttpPost httppost = new HttpPost(POST_URL);
		try {
			
			httppost.setHeader(HttpHeaders.ACCEPT, "application/json");
			httppost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

			WebServiceRequest<String> webServiceRequest = new WebServiceRequest<String>();
			webServiceRequest.setPassword("aa");
			webServiceRequest.setUserName("aa");
			webServiceRequest.setData("aa");

			StringEntity uefEntity = new StringEntity("11111111111111111");
			httppost.setEntity(uefEntity);
			System.out.println("executing request " + httppost.getURI());
			CloseableHttpResponse response = httpclient.execute(httppost);
			
			try {
				HttpEntity entity1 = response.getEntity();
				String str = EntityUtils.toString(entity1);
				ObjectMapper mapper = new ObjectMapper();
				String stt = mapper.readValue(str, String.class);
				
				
				if (entity1 != null) {
					System.out.println("--------------------------------------");
					System.out.println("Response content: " + EntityUtils.toString(entity1, "UTF-8"));
					System.out.println("--------------------------------------");
				}
			}catch(Exception e){
				e.printStackTrace();
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
