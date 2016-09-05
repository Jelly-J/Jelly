package junit.omsdhl.model;

import java.util.ArrayList;
import java.util.List;

public class OmsDhlData
{
    private String ack;
    private String documentCode;
    private String trackNumber;
    private List<OmsDhlErrors> errors;

    public String getAck()
    {
        return ack;
    }

    public void setAck(String ack)
    {
        this.ack = ack;
    }

    public String getDocumentCode()
    {
        return documentCode;
    }

    public void setDocumentCode(String documentCode)
    {
        this.documentCode = documentCode;
    }

    public String getTrackNumber()
    {
        return trackNumber;
    }

    public void setTrackNumber(String trackNumber)
    {
        this.trackNumber = trackNumber;
    }

    public List<OmsDhlErrors> getErrors()
    {
        if (errors == null || errors.size() == 0)
        {
            errors = new ArrayList<OmsDhlErrors>();
        }
        return errors;
    }

    public void setErrors(List<OmsDhlErrors> errors)
    {
        this.errors = errors;
    }

}
