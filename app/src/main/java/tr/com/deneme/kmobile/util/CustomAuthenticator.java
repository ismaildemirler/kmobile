package tr.com.deneme.kmobile.util;

import androidx.annotation.Nullable;
import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Call;
import retrofit2.Retrofit;
import tr.com.deneme.kmobile.SessionManager;
import tr.com.deneme.kmobile.models.response.TokenResponse;
import tr.com.deneme.kmobile.network.token.TokenApi;

@Singleton
public class CustomAuthenticator implements Authenticator {

    private Retrofit retrofit;
    private SessionManager sessionManager;

    @Inject
    public CustomAuthenticator(SessionManager sessionManager, Retrofit retrofit){
        this.sessionManager = sessionManager;
        this.retrofit = retrofit;
    }

    @Nullable
    @Override
    public Request authenticate(Route route, final Response response) throws IOException {

        if(responseCount(response) >= 3){
            return null;
        }

        String token = sessionManager.getToken();
        if(token == null) {
            TokenApi tokenApi = retrofit.create(TokenApi.class);
            Call<TokenResponse> call = tokenApi.getToken(
                    Constants.GRANT_TYPE, Constants.USERNAME, Constants.PASSWORD);
            retrofit2.Response<TokenResponse> res = call.execute();

            if (res.isSuccessful()) {
                TokenResponse newToken = res.body();
                sessionManager.setToken(newToken.getAccess_token());
                return response.request().newBuilder()
                        .header(
                                "Authorization",
                                "Bearer " + res.body().getAccess_token()
                        ).build();
            } else {
                return null;
            }
        }
        else{
            return response.request().newBuilder()
                    .header(
                            "Authorization",
                            "Bearer " + sessionManager.getToken()
                    ).build();
        }
    }

    private int responseCount(Response response) {
        int result = 1;
        while ((response = response.priorResponse()) != null) {
            result++;
        }
        return result;
    }
}
