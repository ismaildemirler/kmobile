package tr.com.deneme.kmobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import javax.inject.Inject;
import dagger.android.support.DaggerAppCompatActivity;
import tr.com.deneme.kmobile.models.SystemUsers;
import tr.com.deneme.kmobile.ui.auth.AuthActivity;
import tr.com.deneme.kmobile.util.Resource;
import tr.com.deneme.kmobile.viewmodels.ViewModelProviderFactory;
import tr.com.deneme.kmobile.viewmodels.auth.AuthViewModel;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    private static final String TAG = "DaggerExample";

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    SessionManager sessionManager;

    private AuthViewModel authViewModel;

    public ProgressBar mProgressBar;

    @Override
    public void setContentView(int layoutResID) {

        ConstraintLayout constraintLayout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        FrameLayout frameLayout = constraintLayout.findViewById(R.id.activity_content);
        mProgressBar = constraintLayout.findViewById(R.id.progress_bar);

        getLayoutInflater().inflate(layoutResID, frameLayout, true);
        super.setContentView(constraintLayout);
    }

    public void showProgressBar(boolean visibility){
        mProgressBar.setVisibility(visibility ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        subscribeObservers();
    }

    protected void subscribeObservers(){
        sessionManager.getUser().observe(this, new Observer<Resource<SystemUsers>>() {
            @Override
            public void onChanged(@Nullable Resource<SystemUsers> userResource) {
                if(userResource != null){
                    if(userResource.data != null) {
                        switch (userResource.status) {
                            case LOADING: {
                                Log.d(TAG, "onChanged: BaseActivity: LOADING...");
                                showProgressBar(true);
                                break;
                            }

                            case ERROR: {
                                Log.e(TAG, "onChanged: status: ERROR, Recipe: " + userResource.data.getEmail());
                                Log.e(TAG, "onChanged: ERROR message: " + userResource.message);
                                //showParent();
                                showProgressBar(false);
                                //setRecipeProperties(userResource.data);
                                break;
                            }

                            case AUTHENTICATED: {
                                Log.d(TAG, "onChanged: BaseActivity: AUTHENTICATED... " +
                                        "Authenticated as: " + userResource.data.getEmail());
                                showProgressBar(false);
                                break;
                            }

                            case NOT_AUTHENTICATED: {
                                Log.d(TAG, "onChanged: BaseActivity: NOT AUTHENTICATED. Navigating to Login screen.");
                                showProgressBar(false);
                                navLoginScreen();
                                break;
                            }
                        }
                    }
                    else{
                        navLoginScreen();
                    }
                }
                else{
                    navLoginScreen();
                }
            }
        });
    }

    private void navLoginScreen(){
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
    }
}
