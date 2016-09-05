package test;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;

public class ClientTest {
	private String url = "http://www.eukuai.com:9092/api/service/woms/order/createGenerateDedhl?customerId=200068&token=3RdnlNWof+mxvNDY4ze/ta1OsZSj/dWchDaw9O9VtUFZxrFD4H4wFokkVh/FSfefD454De";

	
	@Test
	public void testUserTokenJson() throws Exception {
		String strJson = "{\"warehouseCode\":\"PLWH\",\"referenceCode\":\"ORD0012690255\",\"carrierCode\":\"DEDHL\",\"insureType\":\"NI\",\"sellCode\":null,\"remoteArea\":\"Y\",\"description\":null,\"platformCode\":null,\"consignee\":{\"fullName\":\"Jel\","
				+ "\"countryCode\":\"DE\",\"street\":\"Marktpl.\",\"city\":null,\"state\":null,\"postalCode\":\"70173\",\"email\":null,\"phone\":null,\"company\":null,\"doorplate\":\"1\"},\"items\":[{\"sku\":\"SKU000002\",\"quantity\":10},{\"sku\":\"SKU000003\",\"quantity\":5}]}";
		System.out.println(url);
		System.out.println(strJson);
		String strResult = requestPut(url, strJson, "application/json");
		Assert.assertTrue(strResult.contains("\"errorCode\":\"0\""));
	}

//	//@Test
//	public void testUserTokenXml() throws Exception {
//		String strUrl = url + "?format=xml";
//		String strXml = "<userInfo><password>123456</password><userName>test</userName></userInfo>";
//		String strResult = requestPut(strUrl, strXml, "application/xml");
//		Assert.assertTrue(strResult.contains("<errorCode>0</errorCode>"));
//	}

	// Put Sample，post方法只需要修改HttpPut为HttpPost就可以了
	public String requestPut(String urlPath, String strReq, String mydiaType) throws Exception {
		HttpPut httpPut = new HttpPut(urlPath);
		StringEntity stringEntity = null;
		stringEntity = new StringEntity(strReq, "UTF-8");
		String result = sendRequest(httpPut, stringEntity, "application/json");
		System.out.println("返回结果为:" + result);
		return result;
	}

	public String sendRequest(HttpEntityEnclosingRequestBase httpRequest, HttpEntity stringEntity, String mediaType)
			throws Exception {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		httpRequest.setHeader(HttpHeaders.ACCEPT, mediaType);
		httpRequest.setHeader(HttpHeaders.CONTENT_TYPE, mediaType);
		try {
			httpRequest.setEntity(stringEntity);
			ResponseHandler<String> handler = new ResponseHandler<String>() {
				public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
					HttpEntity httpEntity = null;
					httpEntity = response.getEntity();
					if (httpEntity != null) {
						return EntityUtils.toString(httpEntity, "UTF-8");
					} else {
						return null;
					}
				}
			};
			String result = httpClient.execute(httpRequest, handler);
			return result;
		} catch (Exception e) {
			throw new Exception("failure", e);
		}
	}
}
