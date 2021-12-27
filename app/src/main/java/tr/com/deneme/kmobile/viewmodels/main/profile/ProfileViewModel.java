package tr.com.deneme.kmobile.viewmodels.main.profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import tr.com.deneme.kmobile.SessionManager;
import tr.com.deneme.kmobile.models.SystemUsers;
import tr.com.deneme.kmobile.util.Resource;

public class ProfileViewModel extends ViewModel {

    private static final String TAG = "DaggerExample";

    SessionManager sessionManager; // @Singleton scoped dependency

    @Inject
    public ProfileViewModel(
            SessionManager sessionManager
    ) {
        this.sessionManager = sessionManager;
        Log.d(TAG, "ProfileViewModel: viewmodel is ready...");
    }

    public LiveData<Resource<SystemUsers>> getUser(){
        return sessionManager.getUser();
    }
}
