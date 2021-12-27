package tr.com.deneme.kmobile.network.token;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import tr.com.deneme.kmobile.models.response.TokenResponse;

public interface TokenApi {

    @FormUrlEncoded
    @Headers( {"content-type: application/x-www-form-urlencoded;charset=UTF-8", })
    @POST("token")
    Call<TokenResponse> getToken(@Field("grant_type") String grant_type,
                                       @Field("username") String username,
                                       @Field("password") String password);
}
