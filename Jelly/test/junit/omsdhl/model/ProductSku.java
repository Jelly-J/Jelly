package junit.omsdhl.model;

public class ProductSku
{
    private String sku;//产品编号

    private Integer quantity;//产品的数量

    public String getSku()
    {
        return sku;
    }

    public void setSku(String sku)
    {
        this.sku = sku;
    }

    public Integer getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }
}
