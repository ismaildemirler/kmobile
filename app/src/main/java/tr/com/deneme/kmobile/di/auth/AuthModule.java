package tr.com.deneme.kmobile.di.auth;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import tr.com.deneme.kmobile.AppExecutors;
import tr.com.deneme.kmobile.network.auth.AuthApi;
import tr.com.deneme.kmobile.persistence.KosgebDatabase;
import tr.com.deneme.kmobile.persistence.systemuser.SystemUserDao;
import tr.com.deneme.kmobile.repositories.SystemUserRepository;
import tr.com.deneme.kmobile.util.Constants;

@Module
public class AuthModule {

    @AuthScope
    @Provides
    static AuthApi provideAuthApi(@Named(Constants.NAMED_RETROFIT_WITH_AUTH) Retrofit retrofit){
        return retrofit.create(AuthApi.class);
    }

    @AuthScope
    @Provides
    SystemUserDao provideUserDao(KosgebDatabase kosgebDatabase) {
        return kosgebDatabase.getUserDao();
    }

    @AuthScope
    @Provides
    static SystemUserRepository provideUserRepository(AuthApi authApi,
                                                      SystemUserDao userDao,
                                                      AppExecutors appExecutors){
        return new SystemUserRepository(authApi, userDao, appExecutors);
    }
}
