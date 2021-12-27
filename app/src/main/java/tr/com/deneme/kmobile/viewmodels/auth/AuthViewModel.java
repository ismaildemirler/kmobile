package tr.com.deneme.kmobile.viewmodels.auth;


import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import tr.com.deneme.kmobile.SessionManager;
import tr.com.deneme.kmobile.models.SystemUsers;
import tr.com.deneme.kmobile.repositories.SystemUserRepository;
import tr.com.deneme.kmobile.util.Resource;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";
    private MediatorLiveData<Resource<SystemUsers>> user = new MediatorLiveData<>();

    private SystemUserRepository userRepository;

    SessionManager sessionManager; // @Singleton scoped dependency

    @Inject
    public AuthViewModel(
            SystemUserRepository userRepository,
            SessionManager sessionManager
    ) {
        this.sessionManager =sessionManager;
        this.userRepository = userRepository;
        Log.d(TAG, "AuthViewModel: viewmodel is working...");
    }

    public void searchUserApi(String userName, String password)
    {
        final LiveData<Resource<SystemUsers>> repositorySource =
                userRepository.searchUserApi(userName, password);
        user.addSource(repositorySource, new Observer<Resource<SystemUsers>>() {
            @Override
            public void onChanged(Resource<SystemUsers> userResource) {
                user.setValue(userResource);
            }
        });
        sessionManager.setUser(repositorySource);
    }

    public LiveData<Resource<SystemUsers>> getUser(){
        return user;
    }

    public void logOut() {
        sessionManager.logOut();
    }
}
