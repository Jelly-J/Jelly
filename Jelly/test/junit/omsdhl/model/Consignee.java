package junit.omsdhl.model;

public class Consignee
{
    private String fullName;//收件人姓名

    private String countryCode;//收件人的国家二字码 (请参考基础数据)

    private String street;//街道

    private String city;//收件人城市

    private String state;//收件人州

    private String postalCode;//邮政编码

    private String email;//收件人的Email

    private String phone;//收件人的联系电话

    private String company;//公司名称

    private String doorplate;//门牌号

    public String getFullName()
    {
        return fullName;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public String getCountryCode()
    {
        return countryCode;
    }

    public void setCountryCode(String countryCode)
    {
        this.countryCode = countryCode;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getPostalCode()
    {
        return postalCode;
    }

    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getCompany()
    {
        return company;
    }

    public void setCompany(String company)
    {
        this.company = company;
    }

    public String getDoorplate()
    {
        return doorplate;
    }

    public void setDoorplate(String doorplate)
    {
        this.doorplate = doorplate;
    }
}
