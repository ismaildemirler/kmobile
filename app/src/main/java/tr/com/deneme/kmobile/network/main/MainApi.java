package tr.com.deneme.kmobile.network.main;

import androidx.lifecycle.LiveData;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import tr.com.deneme.kmobile.di.auth.AuthScope;
import tr.com.deneme.kmobile.models.response.FirmsResponse;
import tr.com.deneme.kmobile.network.ApiResponse;

@AuthScope
public interface MainApi {

    @FormUrlEncoded
    @POST("/api/firm/GetFirmByAPIRequest")
    LiveData<ApiResponse<FirmsResponse>> getFirmListBySearchParams(
                                            @Field("FirmId") int FirmId,
                                            @Field("TaxNumber") String TaxNumber,
                                            @Field("FirmName") String FirmName,
                                            @Field("ProgramState") int ProgramState,
                                            @Field("ProgramType") int ProgramType,
                                            @Field("PersonnelId") String PersonnelId,
                                            @Field("PageIndex") int PageIndex,
                                            @Field("PageSize") int PageSize);
}
