package tr.com.deneme.kmobile.di.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.concurrent.TimeUnit;
import javax.inject.Named;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import tr.com.deneme.kmobile.SessionManager;
import tr.com.deneme.kmobile.util.Constants;
import tr.com.deneme.kmobile.util.CustomAuthenticator;
import tr.com.deneme.kmobile.util.LiveDataCallAdapterFactory;

@Module
public class NetworkModule {

    @Singleton
    @Provides
    static Gson provideGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
    }

    @Singleton
    @Provides
    @Named(Constants.NAMED_OKHTTP_CIENT_WITHOUT_AUTH)
    static OkHttpClient provideOkHttpClientWithoutAuth() {
        return new OkHttpClient.Builder()
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();
    }

    @Singleton
    @Provides
    @Named(Constants.NAMED_RETROFIT_WITHOUT_AUTH)
    static Retrofit provideRetrofitWithoutAuth(
            @Named(Constants.NAMED_OKHTTP_CIENT_WITHOUT_AUTH)OkHttpClient okHttpClient,
            Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }

    @Singleton
    @Provides
    static CustomAuthenticator provideCustomAuthenticator(SessionManager sessionManager,
                                                          @Named(Constants.NAMED_RETROFIT_WITHOUT_AUTH)Retrofit retrofit){
        return new CustomAuthenticator(sessionManager, retrofit);
    }

    @Singleton
    @Provides
    @Named(Constants.NAMED_OKHTTP_CIENT_WITH_AUTH)
    static OkHttpClient provideOkHttpClientWithAuth(CustomAuthenticator authenticator) {
        return new OkHttpClient.Builder()
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .authenticator(authenticator)
                .build();
    }

    @Singleton
    @Provides
    @Named(Constants.NAMED_RETROFIT_WITH_AUTH)
    static Retrofit provideRetrofitWithAuth(
            @Named(Constants.NAMED_OKHTTP_CIENT_WITH_AUTH) OkHttpClient okHttpClient,
            Gson gson){
            //LiveDataCallAdapterFactory liveDataCallAdapterFactory) {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .client(okHttpClient)
                .build();
    }
}
