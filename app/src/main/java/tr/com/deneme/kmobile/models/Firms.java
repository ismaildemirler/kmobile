package tr.com.deneme.kmobile.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

import retrofit2.http.GET;

@Entity(tableName = "Firms")
public class Firms implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int FirmId;

    @ColumnInfo(name = "SystemUserId")
    private int SystemUserId;

    @ColumnInfo(name = "FirmProductionTypeId")
    private int FirmProductionTypeId;

    @ColumnInfo(name = "FirmStatuId")
    private int FirmStatuId;

    @ColumnInfo(name = "FirmAssociationTypeId")
    private int FirmAssociationTypeId;

    @ColumnInfo(name = "FirmAssociationNumber")
    private String FirmAssociationNumber;

    @ColumnInfo(name = "TownId")
    private int TownId;

    @ColumnInfo(name = "TaxBrunchId")
    private int TaxBrunchId;

    @ColumnInfo(name = "FirmLocationTypeId")
    private int FirmLocationTypeId;

    @ColumnInfo(name = "FirmName")
    private String FirmName;

    @ColumnInfo(name = "TaxNumber")
    private String TaxNumber;

    @ColumnInfo(name = "AuthorizedPerson")
    private String AuthorizedPerson;

    @ColumnInfo(name = "SSKNumber")
    private String SSKNumber;

    @ColumnInfo(name = "TicariSicilNumber")
    private int TicariSicilNumber;

    @ColumnInfo(name = "FoundationYear")
    private int FoundationYear;

    @ColumnInfo(name = "ActivatedYear")
    private int ActivatedYear;

    @ColumnInfo(name = "Adress")
    private String Adress;

    @ColumnInfo(name = "PostalCode")
    private String PostalCode;

    @ColumnInfo(name = "Telephone")
    private String Telephone;

    @ColumnInfo(name = "Fax")
    private String Fax;

    @ColumnInfo(name = "MobilePhone")
    private String MobilePhone;

    @ColumnInfo(name = "Email")
    private String Email;

    @ColumnInfo(name = "WebSite")
    private String WebSite;

    @ColumnInfo(name = "ApplicationDate")
    private Date ApplicationDate;

    @ColumnInfo(name = "UpdateDate")
    private Date UpdateDate;

    @ColumnInfo(name = "UnitId")
    private int UnitId;

    @ColumnInfo(name = "PersonnelId")
    private int PersonnelId;

    @ColumnInfo(name = "IsEmailValid")
    private Boolean IsEmailValid;

    @ColumnInfo(name = "IsMailRequest")
    private Boolean IsMailRequest;

    @ColumnInfo(name = "WorkStartDate")
    private Date WorkStartDate;

    @ColumnInfo(name = "KEPMail")
    private String KEPMail;

    @ColumnInfo(name = "IsSMSRequest")
    private Boolean IsSMSRequest;

    @ColumnInfo(name = "TimeStamp")
    public int TimeStamp;

    public Firms() {
    }

    public Firms(int firmId, int systemUserId, int firmProductionTypeId, int firmStatuId,
                 int firmAssociationTypeId, String firmAssociationNumber, int townId, int taxBrunchId,
                 int firmLocationTypeId, String firmName, String taxNumber, String authorizedPerson,
                 String SSKNumber, int ticariSicilNumber, int foundationYear, int activatedYear,
                 String adress, String postalCode, String telephone, String fax, String mobilePhone,
                 String email, String webSite, Date applicationDate, Date updateDate, int unitId,
                 int personnelId, Boolean isEmailValid, Boolean isMailRequest, Date workStartDate,
                 String KEPMail, Boolean isSMSRequest, int timeStamp) {
        FirmId = firmId;
        SystemUserId = systemUserId;
        FirmProductionTypeId = firmProductionTypeId;
        FirmStatuId = firmStatuId;
        FirmAssociationTypeId = firmAssociationTypeId;
        FirmAssociationNumber = firmAssociationNumber;
        TownId = townId;
        TaxBrunchId = taxBrunchId;
        FirmLocationTypeId = firmLocationTypeId;
        FirmName = firmName;
        TaxNumber = taxNumber;
        AuthorizedPerson = authorizedPerson;
        this.SSKNumber = SSKNumber;
        TicariSicilNumber = ticariSicilNumber;
        FoundationYear = foundationYear;
        ActivatedYear = activatedYear;
        Adress = adress;
        PostalCode = postalCode;
        Telephone = telephone;
        Fax = fax;
        MobilePhone = mobilePhone;
        Email = email;
        WebSite = webSite;
        ApplicationDate = applicationDate;
        UpdateDate = updateDate;
        UnitId = unitId;
        PersonnelId = personnelId;
        IsEmailValid = isEmailValid;
        IsMailRequest = isMailRequest;
        WorkStartDate = workStartDate;
        this.KEPMail = KEPMail;
        IsSMSRequest = isSMSRequest;
        TimeStamp = timeStamp;
    }

    protected Firms(Parcel in) {
        FirmId = in.readInt();
        SystemUserId = in.readInt();
        FirmProductionTypeId = in.readInt();
        FirmStatuId = in.readInt();
        FirmAssociationTypeId = in.readInt();
        FirmAssociationNumber = in.readString();
        TownId = in.readInt();
        TaxBrunchId = in.readInt();
        FirmLocationTypeId = in.readInt();
        FirmName = in.readString();
        TaxNumber = in.readString();
        AuthorizedPerson = in.readString();
        SSKNumber = in.readString();
        TicariSicilNumber = in.readInt();
        FoundationYear = in.readInt();
        ActivatedYear = in.readInt();
        Adress = in.readString();
        PostalCode = in.readString();
        Telephone = in.readString();
        Fax = in.readString();
        MobilePhone = in.readString();
        Email = in.readString();
        WebSite = in.readString();
        UnitId = in.readInt();
        PersonnelId = in.readInt();
        IsEmailValid = in.readByte() != 0;
        IsMailRequest = in.readByte() != 0;
        KEPMail = in.readString();
        IsSMSRequest = in.readByte() != 0;
        TimeStamp = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(FirmId);
        dest.writeInt(SystemUserId);
        dest.writeInt(FirmProductionTypeId);
        dest.writeInt(FirmStatuId);
        dest.writeInt(FirmAssociationTypeId);
        dest.writeString(FirmAssociationNumber);
        dest.writeInt(TownId);
        dest.writeInt(TaxBrunchId);
        dest.writeInt(FirmLocationTypeId);
        dest.writeString(FirmName);
        dest.writeString(TaxNumber);
        dest.writeString(AuthorizedPerson);
        dest.writeString(SSKNumber);
        dest.writeInt(TicariSicilNumber);
        dest.writeInt(FoundationYear);
        dest.writeInt(ActivatedYear);
        dest.writeString(Adress);
        dest.writeString(PostalCode);
        dest.writeString(Telephone);
        dest.writeString(Fax);
        dest.writeString(MobilePhone);
        dest.writeString(Email);
        dest.writeString(WebSite);
        dest.writeInt(UnitId);
        dest.writeInt(PersonnelId);
        dest.writeByte((byte) (IsEmailValid ? 1 : 0));
        dest.writeByte((byte) (IsMailRequest ? 1 : 0));
        dest.writeString(KEPMail);
        dest.writeByte((byte) (IsSMSRequest ? 1 : 0));
        dest.writeInt(TimeStamp);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Firms> CREATOR = new Creator<Firms>() {
        @Override
        public Firms createFromParcel(Parcel in) {
            return new Firms(in);
        }

        @Override
        public Firms[] newArray(int size) {
            return new Firms[size];
        }
    };

    public int getFirmId() {
        return FirmId;
    }

    public void setFirmId(int firmId) {
        FirmId = firmId;
    }

    public int getSystemUserId() {
        return SystemUserId;
    }

    public void setSystemUserId(int systemUserId) {
        SystemUserId = systemUserId;
    }

    public int getFirmProductionTypeId() {
        return FirmProductionTypeId;
    }

    public void setFirmProductionTypeId(int firmProductionTypeId) {
        FirmProductionTypeId = firmProductionTypeId;
    }

    public int getFirmStatuId() {
        return FirmStatuId;
    }

    public void setFirmStatuId(int firmStatuId) {
        FirmStatuId = firmStatuId;
    }

    public int getFirmAssociationTypeId() {
        return FirmAssociationTypeId;
    }

    public void setFirmAssociationTypeId(int firmAssociationTypeId) {
        FirmAssociationTypeId = firmAssociationTypeId;
    }

    public String getFirmAssociationNumber() {
        return FirmAssociationNumber;
    }

    public void setFirmAssociationNumber(String firmAssociationNumber) {
        FirmAssociationNumber = firmAssociationNumber;
    }

    public int getTownId() {
        return TownId;
    }

    public void setTownId(int townId) {
        TownId = townId;
    }

    public int getTaxBrunchId() {
        return TaxBrunchId;
    }

    public void setTaxBrunchId(int taxBrunchId) {
        TaxBrunchId = taxBrunchId;
    }

    public int getFirmLocationTypeId() {
        return FirmLocationTypeId;
    }

    public void setFirmLocationTypeId(int firmLocationTypeId) {
        FirmLocationTypeId = firmLocationTypeId;
    }

    public String getFirmName() {
        return FirmName;
    }

    public void setFirmName(String firmName) {
        FirmName = firmName;
    }

    public String getTaxNumber() {
        return TaxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        TaxNumber = taxNumber;
    }

    public String getAuthorizedPerson() {
        return AuthorizedPerson;
    }

    public void setAuthorizedPerson(String authorizedPerson) {
        AuthorizedPerson = authorizedPerson;
    }

    public String getSSKNumber() {
        return SSKNumber;
    }

    public void setSSKNumber(String SSKNumber) {
        this.SSKNumber = SSKNumber;
    }

    public int getTicariSicilNumber() {
        return TicariSicilNumber;
    }

    public void setTicariSicilNumber(int ticariSicilNumber) {
        TicariSicilNumber = ticariSicilNumber;
    }

    public int getFoundationYear() {
        return FoundationYear;
    }

    public void setFoundationYear(int foundationYear) {
        FoundationYear = foundationYear;
    }

    public int getActivatedYear() {
        return ActivatedYear;
    }

    public void setActivatedYear(int activatedYear) {
        ActivatedYear = activatedYear;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String fax) {
        Fax = fax;
    }

    public String getMobilePhone() {
        return MobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        MobilePhone = mobilePhone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getWebSite() {
        return WebSite;
    }

    public void setWebSite(String webSite) {
        WebSite = webSite;
    }

    public Date getApplicationDate() {
        return ApplicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        ApplicationDate = applicationDate;
    }

    public Date getUpdateDate() {
        return UpdateDate;
    }

    public void setUpdateDate(Date updateDate) {
        UpdateDate = updateDate;
    }

    public int getUnitId() {
        return UnitId;
    }

    public void setUnitId(int unitId) {
        UnitId = unitId;
    }

    public int getPersonnelId() {
        return PersonnelId;
    }

    public void setPersonnelId(int personnelId) {
        PersonnelId = personnelId;
    }

    public Boolean isIsEmailValid() {
        return IsEmailValid;
    }

    public void setIsEmailValid(Boolean IsEmailValid) {
        IsEmailValid = IsEmailValid;
    }

    public Boolean isIsMailRequest() {
        return IsMailRequest;
    }

    public void setIsMailRequest(Boolean IsMailRequest) {
        IsMailRequest = IsMailRequest;
    }

    public Date getWorkStartDate() {
        return WorkStartDate;
    }

    public void setWorkStartDate(Date workStartDate) {
        WorkStartDate = workStartDate;
    }

    public String getKEPMail() {
        return KEPMail;
    }

    public void setKEPMail(String KEPMail) {
        this.KEPMail = KEPMail;
    }

    public Boolean isIsSMSRequest() {
        return IsSMSRequest;
    }

    public void setIsSMSRequest(Boolean IsSMSRequest) {
        IsSMSRequest = IsSMSRequest;
    }

    public int getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(int timeStamp) {
        TimeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "Firms{" +
                "FirmId=" + FirmId +
                ", SystemUserId=" + SystemUserId +
                ", FirmProductionTypeId=" + FirmProductionTypeId +
                ", FirmStatuId=" + FirmStatuId +
                ", FirmAssociationTypeId=" + FirmAssociationTypeId +
                ", FirmAssociationNumber='" + FirmAssociationNumber + '\'' +
                ", TownId=" + TownId +
                ", TaxBrunchId=" + TaxBrunchId +
                ", FirmLocationTypeId=" + FirmLocationTypeId +
                ", FirmName='" + FirmName + '\'' +
                ", TaxNumber='" + TaxNumber + '\'' +
                ", AuthorizedPerson='" + AuthorizedPerson + '\'' +
                ", SSKNumber='" + SSKNumber + '\'' +
                ", TicariSicilNumber=" + TicariSicilNumber +
                ", FoundationYear=" + FoundationYear +
                ", ActivatedYear=" + ActivatedYear +
                ", Adress='" + Adress + '\'' +
                ", PostalCode='" + PostalCode + '\'' +
                ", Telephone='" + Telephone + '\'' +
                ", Fax='" + Fax + '\'' +
                ", MobilePhone='" + MobilePhone + '\'' +
                ", Email='" + Email + '\'' +
                ", WebSite='" + WebSite + '\'' +
                ", ApplicationDate=" + ApplicationDate +
                ", UpdateDate=" + UpdateDate +
                ", UnitId=" + UnitId +
                ", PersonnelId=" + PersonnelId +
                ", IsEmailValid=" + IsEmailValid +
                ", IsMailRequest=" + IsMailRequest +
                ", WorkStartDate=" + WorkStartDate +
                ", KEPMail='" + KEPMail + '\'' +
                ", IsSMSRequest=" + IsSMSRequest +
                ", TimeStamp=" + TimeStamp +
                '}';
    }
}


