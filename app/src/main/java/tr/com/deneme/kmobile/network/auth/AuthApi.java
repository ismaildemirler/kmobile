package tr.com.deneme.kmobile.network.auth;

import androidx.lifecycle.LiveData;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import tr.com.deneme.kmobile.di.auth.AuthScope;
import tr.com.deneme.kmobile.models.response.SystemUserResponse;
import tr.com.deneme.kmobile.network.ApiResponse;

@AuthScope
public interface AuthApi {

    @FormUrlEncoded
    @POST("/api/user/GetSystemUser")
    LiveData<ApiResponse<SystemUserResponse>> getSystemUser(@Field("UserName") String UserName,
                                                            @Field("Password") String Password);
}
