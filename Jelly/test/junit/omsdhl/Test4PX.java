package junit.omsdhl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHeaders;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import junit.omsdhl.model.Consignee;
import junit.omsdhl.model.ProductSku;
import junit.omsdhl.model.ShipmentRequest;

public class Test4PX
{
    private String customerId = "200068";
    // private String token = "token=3RdnlNWof+mxvNDY4ze/ta1OsZSj/dWchDaw9O9VtUFZxrFD4H4wFokkVh/FSfefD454De";
    private String token = "&token=3RdnlNWof+mxvNDY4ze/ta1OsZSj/dWchDaw9O9VtUFZxrFD4H4wFokkVh/FSfefD454De";
    private String api = "http://www.eukuai.com:9092/api/service/woms/order/";
    private String createGenerateDedhl = "createGenerateDedhl?customerId=" + customerId;
    private String getOrderCarrier = "getOrderCarrier?customerId=" + customerId;
    private String cancelDelivery = "cancelDeliveryOrder?customerId=" + customerId;
    private final String JSON = "application/json";

    private final String label = "http://82.160.17.218:9090/DHLDE/label/00340434153993758578.pdf";

    @Test
    public void test()
    {

        ObjectMapper mapper = new ObjectMapper();
        HttpClientHelper httpClient = new HttpClientHelper();
        try
        {

            header(httpClient);

            //            String url1 = api + getOrderCarrier + "&token=" + token;
            //            System.out.println(url1);
            //            String str = "{\"warehouseCode\":\"PLWH\"}";
            //            System.out.println(str);
            //            String doHttpPost1 = httpClient.doHttpPost(url1, str);
            //            System.out.println(doHttpPost1);

            System.out.println("---------------------------------------------------------------------");

            String url = api + createGenerateDedhl + token;
            System.out.println(url);
            String strEntity = mapper.writeValueAsString(getRequest());
            System.out.println(strEntity);
            String doHttpPost = httpClient.doHttpPost(url, strEntity);
            System.out.println(doHttpPost);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //    @Test
    public void cancel()
    {
        try
        {
            HttpClientHelper httpClient = new HttpClientHelper();
            header(httpClient);
            String url = api + cancelDelivery + token;
            System.out.println(url);
            String str = "{\"orderCode\":\"200068-160706-2019\"}";
            System.out.println(str);
            String doHttpPost = httpClient.doHttpPost(url, str);
            System.out.println(doHttpPost);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void header(HttpClientHelper httpClient)
    {
        Map<String, Map<String, Object>> map = new HashMap<String, Map<String, Object>>();
        Map<String, Object> value = new HashMap<String, Object>();
        value.put(HttpHeaders.ACCEPT, JSON);
        value.put(HttpHeaders.CONTENT_TYPE, JSON);
        map.put("head", value);
        httpClient.setMap(map);
    }

    public ShipmentRequest getRequest1()
    {

        ShipmentRequest re = new ShipmentRequest();
        Consignee consignee = new Consignee();
        ProductSku sku = new ProductSku();
        ProductSku sku1 = new ProductSku();
        List<ProductSku> items = new ArrayList<ProductSku>();
        items.add(sku);
        items.add(sku1);
        re.setItems(items);
        re.setConsignee(consignee);

        re.setWarehouseCode("PLWH");
        re.setReferenceCode("ORD0012690265");
        re.setCarrierCode("DEDHL");
        re.setInsureType("NI");
        re.setRemoteArea("Y");
        consignee.setCity("Stuttgart");
        //        re.setCompany("");
        //        re.setDescription("");
        //        re.setEmail("");
        consignee.setCountryCode("DE");
        consignee.setDoorplate("1");
        consignee.setFullName("Jel");
        consignee.setPostalCode("70173");
        consignee.setStreet("Marktpl.");
        //        re.setPhone("");
        //        re.setPlatformCode("");
        sku.setQuantity(10);
        sku.setSku("SKU000002");

        sku1.setQuantity(5);
        sku1.setSku("SKU000003");
        //        re.setSellCode("");
        //        re.setState("");

        return re;
    }

    public ShipmentRequest getRequest()
    {

        ShipmentRequest re = new ShipmentRequest();
        Consignee consignee = new Consignee();
        ProductSku sku = new ProductSku();
        ProductSku sku1 = new ProductSku();
        List<ProductSku> items = new ArrayList<ProductSku>();
        items.add(sku);
        items.add(sku1);
        re.setItems(items);
        re.setConsignee(consignee);

        re.setWarehouseCode("PLWH");
        re.setReferenceCode("ORD0112690266");
        re.setCarrierCode("DEDHL");
        re.setInsureType("NI");
        re.setRemoteArea("Y");
        consignee.setCity("Rastede");
//        consignee.setCompany("41128629");
        //        re.setDescription("");
        //        re.setEmail("");
        consignee.setCountryCode("DE");
        consignee.setDoorplate("107");
        consignee.setFullName("Florian Weyhers");
        consignee.setPostalCode("26180");
        consignee.setStreet("41128629 Packstation");
        //        re.setPhone("");
        //        re.setPlatformCode("");
        sku.setQuantity(10);
        sku.setSku("SKU000002");

                sku1.setQuantity(5);
                sku1.setSku("SKU000003");
        //        re.setSellCode("");
        //        re.setState("");

        return re;
    }

}
