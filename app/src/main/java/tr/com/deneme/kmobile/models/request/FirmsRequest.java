package tr.com.deneme.kmobile.models.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FirmsRequest implements Serializable {

    @SerializedName("FirmId")
    @Expose
    private int FirmId;

    @SerializedName("PersonnelId")
    @Expose
    private String PersonnelId;

    @SerializedName("TaxNumber")
    @Expose
    private String TaxNumber;

    @SerializedName("FirmName")
    @Expose
    private String FirmName;

    @SerializedName("ProgramState")
    @Expose
    private int ProgramState;

    @SerializedName("ProgramType")
    @Expose
    private int ProgramType;

    @SerializedName("PageIndex")
    @Expose
    private int PageIndex;

    @SerializedName("PageSize")
    @Expose
    private int PageSize;

    public FirmsRequest() {
    }

    public FirmsRequest(int firmId, String taxNumber, String firmName,
                        int programState, int programType, String personnelId,
                        int pageIndex, int pageSize) {
        FirmId = firmId;
        TaxNumber = taxNumber;
        FirmName = firmName;
        ProgramState = programState;
        ProgramType = programType;
        PersonnelId = personnelId;
        PageIndex = pageIndex;
        PageSize = pageSize;
    }

    public int getFirmId() {
        return FirmId;
    }

    public void setFirmId(int firmId) {
        FirmId = firmId;
    }

    public String getPersonnelId() {
        return PersonnelId;
    }

    public void setPersonnelId(String personnelId) {
        PersonnelId = personnelId;
    }

    public String getTaxNumber() {
        return TaxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        TaxNumber = taxNumber;
    }

    public String getFirmName() {
        return FirmName;
    }

    public void setFirmName(String firmName) {
        FirmName = firmName;
    }

    public int getProgramState() {
        return ProgramState;
    }

    public void setProgramState(int programState) {
        ProgramState = programState;
    }

    public int getProgramType() {
        return ProgramType;
    }

    public void setProgramType(int programType) {
        ProgramType = programType;
    }

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int pageIndex) {
        PageIndex = pageIndex;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }

    @Override
    public String toString() {
        return "FirmsRequest{" +
                "FirmId=" + FirmId +
                ", TaxNumber='" + TaxNumber + '\'' +
                ", FirmName='" + FirmName + '\'' +
                ", ProgramState=" + ProgramState +
                ", ProgramType=" + ProgramType +
                ", PersonnelId=" + PersonnelId +
                ", PageIndex=" + PageIndex +
                ", PageSize=" + PageSize +
                '}';
    }
}
