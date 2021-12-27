package tr.com.deneme.kmobile.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import tr.com.deneme.kmobile.models.SystemUsers;

public class SystemUserResponse {

    @SerializedName("SystemUsers")
    @Expose()
    private SystemUsers SystemUsers;

    @SerializedName("error")
    @Expose()
    private String error;

    public SystemUsers getUser() {
        return SystemUsers;
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return "SystemUserResponse{" +
                "User=" + SystemUsers +
                ", error='" + error + '\'' +
                '}';
    }
}
