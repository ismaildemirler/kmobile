package tr.com.deneme.kmobile.ui.auth;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AlignmentSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.RequestManager;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import tr.com.deneme.kmobile.R;
import tr.com.deneme.kmobile.models.SystemUsers;
import tr.com.deneme.kmobile.ui.main.MainActivity;
import tr.com.deneme.kmobile.util.Constants;
import tr.com.deneme.kmobile.util.Resource;
import tr.com.deneme.kmobile.util.TextHelper;
import tr.com.deneme.kmobile.viewmodels.ViewModelProviderFactory;
import tr.com.deneme.kmobile.viewmodels.auth.AuthViewModel;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener{
    private static final String TAG = "AuthActivity";

    private AuthViewModel authViewModel;

    private EditText userName;
    private EditText password;
    private CheckBox rememberMe;
    private ProgressBar progressBar;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    TextHelper textHelper;

    @Inject
    Drawable logo;

    @Inject
    RequestManager requestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        userName = (EditText) findViewById(R.id.user_name_input);
        password = (EditText) findViewById(R.id.password_input);
        rememberMe = (CheckBox) findViewById(R.id.remember_me_checkbox);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        findViewById(R.id.login_button).setOnClickListener(this);

        authViewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel.class);

        subscribeObservers();
        setLogo();
    }

    protected void subscribeObservers() {
        authViewModel.getUser().observe((LifecycleOwner) this, new Observer<Resource<SystemUsers>>() {
            @Override
            public void onChanged(@Nullable Resource<SystemUsers> userResource) {
                if(userResource != null){
                    switch (userResource.status){
                        case LOADING:{
                            showProgressBar(true);
                            break;
                        }

                        case ERROR:{
                            Toast toast = Toast.makeText(AuthActivity.this,
                                    textHelper.GetCenteredText(Constants.USER_NOT_FOUND),
                                    Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER,0,0);
                            toast.show();
                            showProgressBar(false);
                            break;
                        }

                        case AUTHENTICATED:{
                            Log.d(TAG, "onChanged: cache has been refreshed.");
                            Log.d(TAG, "onChanged: status: SUCCESS, Login: "
                                    + userResource.data.getEmail());
                            showProgressBar(false);
                            onLoginSuccess();
                            break;
                        }
                    }
                }
            }
        });
    }

    public void showProgressBar(boolean visibility){
        progressBar.setVisibility(visibility ? View.VISIBLE : View.INVISIBLE);
    }

    private void onLoginSuccess(){
        Log.d(TAG, "onLoginSuccess: login successful!");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void setLogo(){
        requestManager.load(logo)
                .into((ImageView)findViewById(R.id.login_logo));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button : {
                attemptLogin();
                break;
            }
            default: {
                break;
            }
        }
    }

    private void attemptLogin() {

        if(TextUtils.isEmpty(userName.getText().toString()) ||
                TextUtils.isEmpty(password.getText().toString())){
            return;
        }

        authViewModel.searchUserApi(userName.getText().toString(), password.getText().toString());
    }
}
