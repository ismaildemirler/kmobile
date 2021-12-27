package tr.com.deneme.kmobile.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

@Entity(tableName = "SystemUsers")
public class SystemUsers implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int SystemUserId;

    @ColumnInfo(name = "UserStateId")
    public int UserStateId;

    @ColumnInfo(name = "FirstName")
    public String FirstName;

    @ColumnInfo(name = "LastName")
    public String LastName;

    @ColumnInfo(name = "UserName")
    public String UserName;

    @ColumnInfo(name = "Password")
    public String Password;

    @ColumnInfo(name = "Email")
    public String Email;

    @ColumnInfo(name = "CreationDate")
    public Date CreationDate;

    @ColumnInfo(name = "UpdateDate")
    public Date UpdateDate;

    @ColumnInfo(name = "LastLoginDate")
    public Date LastLoginDate;

    @ColumnInfo(name = "PersonnelId")
    public int PersonnelId;

    @ColumnInfo(name = "PersonnelType")
    public String PersonnelType;

    @ColumnInfo(name = "UnitId")
    public int UnitId;

    @ColumnInfo(name = "TcKimlikNo")
    public String TcKimlikNo;

    @ColumnInfo(name = "Telephone")
    public String Telephone;

    @ColumnInfo(name = "SicilNo")
    public String SicilNo;

    @ColumnInfo(name = "MobilePhone")
    public String MobilePhone;

    @ColumnInfo(name = "PersonnelTypeId")
    public int PersonnelTypeId;

    @ColumnInfo(name = "TimeStamp")
    public int TimeStamp;

    public SystemUsers() {
    }

    public SystemUsers(int systemUserId, int userStateId, String firstName, String lastName,
                       String userName, String password, String email, Date creationDate,
                       Date updateDate, Date lastLoginDate, int personnelId, String personnelType,
                       int unitId, String tcKimlikNo, String telephone, String sicilNo, String mobilePhone,
                       int personnelTypeId, int timeStamp) {
        SystemUserId = systemUserId;
        UserStateId = userStateId;
        FirstName = firstName;
        LastName = lastName;
        UserName = userName;
        Password = password;
        Email = email;
        CreationDate = creationDate;
        UpdateDate = updateDate;
        LastLoginDate = lastLoginDate;
        PersonnelId = personnelId;
        PersonnelType = personnelType;
        UnitId = unitId;
        TcKimlikNo = tcKimlikNo;
        Telephone = telephone;
        SicilNo = sicilNo;
        MobilePhone = mobilePhone;
        PersonnelTypeId = personnelTypeId;
        TimeStamp = timeStamp;
    }

    protected SystemUsers(Parcel in) {
        SystemUserId = in.readInt();
        UserStateId = in.readInt();
        FirstName = in.readString();
        LastName = in.readString();
        UserName = in.readString();
        Password = in.readString();
        Email = in.readString();
        PersonnelId = in.readInt();
        PersonnelType = in.readString();
        UnitId = in.readInt();
        TcKimlikNo = in.readString();
        Telephone = in.readString();
        SicilNo = in.readString();
        MobilePhone = in.readString();
        PersonnelTypeId = in.readInt();
        TimeStamp = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(SystemUserId);
        dest.writeInt(UserStateId);
        dest.writeString(FirstName);
        dest.writeString(LastName);
        dest.writeString(UserName);
        dest.writeString(Password);
        dest.writeString(Email);
        dest.writeInt(PersonnelId);
        dest.writeString(PersonnelType);
        dest.writeInt(UnitId);
        dest.writeString(TcKimlikNo);
        dest.writeString(Telephone);
        dest.writeString(SicilNo);
        dest.writeString(MobilePhone);
        dest.writeInt(PersonnelTypeId);
        dest.writeInt(TimeStamp);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SystemUsers> CREATOR = new Creator<SystemUsers>() {
        @Override
        public SystemUsers createFromParcel(Parcel in) {
            return new SystemUsers(in);
        }

        @Override
        public SystemUsers[] newArray(int size) {
            return new SystemUsers[size];
        }
    };

    public int getSystemUserId() {
        return SystemUserId;
    }

    public void setSystemUserId(int systemUserId) {
        SystemUserId = systemUserId;
    }

    public int getUserStateId() {
        return UserStateId;
    }

    public void setUserStateId(int userStateId) {
        UserStateId = userStateId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Date getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(Date creationDate) {
        CreationDate = creationDate;
    }

    public Date getUpdateDate() {
        return UpdateDate;
    }

    public void setUpdateDate(Date updateDate) {
        UpdateDate = updateDate;
    }

    public Date getLastLoginDate() {
        return LastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        LastLoginDate = lastLoginDate;
    }

    public int getPersonnelId() {
        return PersonnelId;
    }

    public void setPersonnelId(int personnelId) {
        PersonnelId = personnelId;
    }

    public String getPersonnelType() {
        return PersonnelType;
    }

    public void setPersonnelType(String personnelType) {
        PersonnelType = personnelType;
    }

    public int getUnitId() {
        return UnitId;
    }

    public void setUnitId(int unitId) {
        UnitId = unitId;
    }

    public String getTcKimlikNo() {
        return TcKimlikNo;
    }

    public void setTcKimlikNo(String tcKimlikNo) {
        TcKimlikNo = tcKimlikNo;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getSicilNo() {
        return SicilNo;
    }

    public void setSicilNo(String sicilNo) {
        SicilNo = sicilNo;
    }

    public String getMobilePhone() {
        return MobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        MobilePhone = mobilePhone;
    }

    public int getPersonnelTypeId() {
        return PersonnelTypeId;
    }

    public void setPersonnelTypeId(int personnelTypeId) {
        PersonnelTypeId = personnelTypeId;
    }

    public int getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(int timeStamp) {
        TimeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "SystemUsers{" +
                "SystemUserId=" + SystemUserId +
                ", UserStateId=" + UserStateId +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", UserName='" + UserName + '\'' +
                ", Password='" + Password + '\'' +
                ", Email='" + Email + '\'' +
                ", CreationDate=" + CreationDate +
                ", UpdateDate=" + UpdateDate +
                ", LastLoginDate=" + LastLoginDate +
                ", PersonnelId=" + PersonnelId +
                ", PersonnelType='" + PersonnelType + '\'' +
                ", UnitId=" + UnitId +
                ", TcKimlikNo='" + TcKimlikNo + '\'' +
                ", Telephone='" + Telephone + '\'' +
                ", SicilNo='" + SicilNo + '\'' +
                ", MobilePhone='" + MobilePhone + '\'' +
                ", PersonnelTypeId=" + PersonnelTypeId +
                ", TimeStamp=" + TimeStamp +
                '}';
    }
}


