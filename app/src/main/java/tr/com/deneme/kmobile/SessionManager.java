package tr.com.deneme.kmobile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import javax.inject.Inject;
import javax.inject.Singleton;
import tr.com.deneme.kmobile.models.SystemUsers;
import tr.com.deneme.kmobile.util.Constants;
import tr.com.deneme.kmobile.util.Resource;
import tr.com.deneme.kmobile.util.SharedPrefsHelper;

@Singleton
public class SessionManager {

    private static final String TAG = "SessionManager";

    @Inject
    SharedPrefsHelper sharedPrefsHelper;

    private MediatorLiveData<Resource<SystemUsers>> cachedUser = new MediatorLiveData<>();

    @Inject
    public SessionManager() {
    }

    public void setUser(final LiveData<Resource<SystemUsers>> source) {
        if(cachedUser != null){
            cachedUser.setValue(Resource.loading((SystemUsers)null));
            cachedUser.addSource(source, new Observer<Resource<SystemUsers>>() {
                @Override
                public void onChanged(Resource<SystemUsers> userAuthResource) {
                    cachedUser.setValue(userAuthResource);
                    cachedUser.removeSource(source);
                }
            });
        }
    }

    public LiveData<Resource<SystemUsers>> getUser(){
        return cachedUser;
    }

    public void setToken(String token){
        sharedPrefsHelper.put(Constants.SHARED_PREFS_ACCESS_TOKEN, token);
    }

    public String getToken(){
        return sharedPrefsHelper.get(Constants.SHARED_PREFS_ACCESS_TOKEN, null);
    }

    public void deleteToken(){
        sharedPrefsHelper.deleteSavedData(Constants.SHARED_PREFS_ACCESS_TOKEN);
    }

    public void logOut() {
        deleteToken();
        cachedUser.setValue(Resource.<SystemUsers>logout());
    }
}
