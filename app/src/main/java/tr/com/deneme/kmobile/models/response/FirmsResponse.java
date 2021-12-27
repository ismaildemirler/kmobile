package tr.com.deneme.kmobile.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import tr.com.deneme.kmobile.models.Firms;

public class FirmsResponse {

    @SerializedName("Count")
    @Expose()
    private int Count;

    @SerializedName("Firms")
    @Expose()
    private List<Firms> Firms;

    @SerializedName("error")
    @Expose()
    private String error;

    public int getCount() {
        return Count;
    }

    public List<Firms> getFirms() {
        return Firms;
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return "FirmsResponse{" +
                "Count=" + Count +
                ", Firms=" + Firms +
                ", error='" + error + '\'' +
                '}';
    }
}
