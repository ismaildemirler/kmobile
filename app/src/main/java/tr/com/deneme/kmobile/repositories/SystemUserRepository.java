package tr.com.deneme.kmobile.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import javax.inject.Inject;
import javax.inject.Singleton;

import tr.com.deneme.kmobile.AppExecutors;
import tr.com.deneme.kmobile.models.SystemUsers;
import tr.com.deneme.kmobile.models.response.SystemUserResponse;
import tr.com.deneme.kmobile.network.ApiResponse;
import tr.com.deneme.kmobile.network.auth.AuthApi;
import tr.com.deneme.kmobile.persistence.systemuser.SystemUserDao;
import tr.com.deneme.kmobile.util.Constants;
import tr.com.deneme.kmobile.util.NetworkBoundResource;
import tr.com.deneme.kmobile.util.Resource;

@Singleton
public class SystemUserRepository {
    private static final String TAG = "RecipeRepository";

    private static AuthApi authApi;

    SystemUserDao userDao;
    AppExecutors appExecutors;

    @Inject
    public SystemUserRepository(AuthApi authApi, SystemUserDao userDao, AppExecutors appExecutors)
    {
        this.authApi = authApi;
        this.userDao = userDao;
        this.appExecutors = appExecutors;
    }

    public LiveData<Resource<SystemUsers>> searchUserApi(final String userName, final String password){

        LiveData<Resource<SystemUsers>> source =  new NetworkBoundResource<SystemUsers, SystemUserResponse>(appExecutors){
            @Override
            protected void saveCallResult(@NonNull SystemUserResponse item) {

                // will be null if API key is expired
                if(item.getUser() != null){
                    item.getUser().setTimeStamp((int)(System.currentTimeMillis() / 1000));
                    userDao.insertUser(item.getUser());
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable SystemUsers data) {
                if(data!=null){
                    Log.d(TAG, "shouldFetch: recipe: " + data.toString());
                    int currentTime = (int)(System.currentTimeMillis() / 1000);
                    Log.d(TAG, "shouldFetch: current time: " + currentTime);
                    int lastRefresh = data.getTimeStamp();
                    Log.d(TAG, "shouldFetch: last refresh: " + lastRefresh);
                    Log.d(TAG, "shouldFetch: it's been " + ((currentTime - lastRefresh) / 60 / 60 / 24) +
                            " days since this recipe was refreshed. 30 days must elapse before refreshing. ");
                    if((currentTime - data.getTimeStamp()) >= Constants.FIRM_REFRESH_TIME){
                        Log.d(TAG, "shouldFetch: SHOULD REFRESH RECIPE?! " + true);
                        return true;
                    }
                    Log.d(TAG, "shouldFetch: SHOULD REFRESH RECIPE?! " + false);
                    return false;
                }
                return true;
            }

            @NonNull
            @Override
            protected LiveData<SystemUsers> loadFromDb() {
                return userDao.getUser(userName, password);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<SystemUserResponse>> createCall() {
                return authApi.getSystemUser(userName, password);
            }
        }.getAsLiveData();
        return source;
    }
}
