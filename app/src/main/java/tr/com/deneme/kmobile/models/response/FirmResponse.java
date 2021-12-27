package tr.com.deneme.kmobile.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FirmResponse {

    @SerializedName("Firms")
    @Expose()
    private tr.com.deneme.kmobile.models.Firms Firms;

    @SerializedName("error")
    @Expose()
    private String error;

    public tr.com.deneme.kmobile.models.Firms getFirm() {
        return Firms;
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return "FirmResponse{" +
                "Firms=" + Firms +
                ", error='" + error + '\'' +
                '}';
    }
}
