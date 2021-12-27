package tr.com.deneme.kmobile.models.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SystemUserRequest implements Serializable {

    @SerializedName("UserName")
    @Expose
    private String UserName;

    @SerializedName("Password")
    @Expose
    private String Password;

    public SystemUserRequest() {
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public String toString() {
        return "SystemUserRequest{" +
                "UserName='" + UserName + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
