package junit.omsdhl.model;

import java.util.ArrayList;
import java.util.List;

public class ShipmentRequest
{
    private String warehouseCode;//产品所在的仓库代码 (请参考基础数据)

    private String referenceCode;//订单参考号

    private String carrierCode;//运送的渠道代号 (参考GetOrderCarrier API的渠道代码)

    private String insureType;//保险类型代码 (请参考基础数据)

    private String sellCode;//销售交易号

    private String remoteArea;//如果是ODA(偏远地区附加费)，是否走货(Y：下单时，即使订单是ODA单也会提示成功，走货后扣除ODA费用；N：下单时，如果是ODA单，则提示下单失败)

    private String description;//订单描述

    private String platformCode;//销售平台 (请见参考基础数据)

    private Consignee consignee;//收件人

    private List<ProductSku> items;

    public List<ProductSku> getItems()
    {
        if (items == null || items.size() == 0)
        {
            items = new ArrayList<ProductSku>();
        }
        return items;
    }

    public void setItems(List<ProductSku> items)
    {
        this.items = items;
    }

    public Consignee getConsignee()
    {
        return consignee;
    }

    public void setConsignee(Consignee consignee)
    {
        this.consignee = consignee;
    }

    public String getWarehouseCode()
    {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode)
    {
        this.warehouseCode = warehouseCode;
    }

    public String getReferenceCode()
    {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode)
    {
        this.referenceCode = referenceCode;
    }

    public String getCarrierCode()
    {
        return carrierCode;
    }

    public void setCarrierCode(String carrierCode)
    {
        this.carrierCode = carrierCode;
    }

    public String getInsureType()
    {
        return insureType;
    }

    public void setInsureType(String insureType)
    {
        this.insureType = insureType;
    }

    public String getSellCode()
    {
        return sellCode;
    }

    public void setSellCode(String sellCode)
    {
        this.sellCode = sellCode;
    }

    public String getRemoteArea()
    {
        return remoteArea;
    }

    public void setRemoteArea(String remoteArea)
    {
        this.remoteArea = remoteArea;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getPlatformCode()
    {
        return platformCode;
    }

    public void setPlatformCode(String platformCode)
    {
        this.platformCode = platformCode;
    }
}
