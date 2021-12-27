package tr.com.deneme.kmobile.ui.main.profile;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import javax.inject.Inject;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import dagger.android.support.DaggerFragment;
import tr.com.deneme.kmobile.R;
import tr.com.deneme.kmobile.models.SystemUsers;
import tr.com.deneme.kmobile.models.request.FirmsRequest;
import tr.com.deneme.kmobile.util.Constants;
import tr.com.deneme.kmobile.util.Resource;
import tr.com.deneme.kmobile.viewmodels.ViewModelProviderFactory;
import tr.com.deneme.kmobile.viewmodels.main.profile.ProfileViewModel;

public class ProfileFragment extends DaggerFragment implements View.OnClickListener {

    private static final String TAG = "ProfileFragment";

    private ProfileViewModel profileViewModel;
    private TextView email, username, headerUserName, headerEmail;
    Button btnShowFirmFilterPopup;
    Dialog myDialog;


    @Inject
    ViewModelProviderFactory providerFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: ProfileFragment. " + this);

        myDialog = new Dialog(view.getContext());
        profileViewModel = ViewModelProviders.of(this, providerFactory).get(ProfileViewModel.class);

        //headerEmail = view.findViewById(R.id.txtHeaderEmail);
        //headerUserName = view.findViewById(R.id.txtHeaderUserName);

        email = view.findViewById(R.id.txtEmail);
        username = view.findViewById(R.id.txtUserName);

        view.findViewById(R.id.btnShowFirmFilterPopup).setOnClickListener(this);

        subscribeObservers();
    }

    private void subscribeObservers(){
        profileViewModel.getUser().removeObservers(getViewLifecycleOwner());
        profileViewModel.getUser().observe(getViewLifecycleOwner(), new Observer<Resource<SystemUsers>>() {
            @Override
            public void onChanged(Resource<SystemUsers> userAuthResource) {
                if(userAuthResource != null){
                    switch (userAuthResource.status){
                        case AUTHENTICATED:{
                            Log.d(TAG, "onChanged: ProfileFragment: AUTHENTICATED... " +
                                    "Authenticated as: " + userAuthResource.data.getEmail());
                            setUserDetails(userAuthResource.data);
                            break;
                        }

                        case ERROR:{
                            Log.d(TAG, "onChanged: ProfileFragment: ERROR...");
                            setErrorDetails(userAuthResource.message);
                            break;
                        }
                    }
                }
            }
        });
    }

    private void setErrorDetails(String message){
        email.setText(message);
        username.setText("error");
    }

    private void setUserDetails(SystemUsers user){
        email.setText(user.getEmail());
        username.setText(user.getUserName());
        //headerEmail.setText(user.getEmail());
        //headerUserName.setText(user.getUserName());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnShowFirmFilterPopup:
                ShowFirmFilterPopup(v);
                break;
            default:
                break;
        }
    }

    public void ShowFirmFilterPopup(View v) {
        TextView txtclose;
        Button btnfilter;
        myDialog.setContentView(R.layout.custompopup);
        txtclose = myDialog.findViewById(R.id.txtclose);
        txtclose.setText("X");
        btnfilter = myDialog.findViewById(R.id.btnfilter);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        final String txtFirmId, txtFirmName, txtTaxNumber, txtProgramType, txtProgramState;
        txtFirmId = ((EditText)myDialog.findViewById(R.id.firm_id_input)).getText().toString();
        txtFirmName = ((EditText)myDialog.findViewById(R.id.firm_name_input)).getText().toString();
        txtTaxNumber = ((EditText)myDialog.findViewById(R.id.tax_number_input)).getText().toString();
        txtProgramType = ((EditText)myDialog.findViewById(R.id.program_type_select)).getText().toString();
        txtProgramState = ((EditText)myDialog.findViewById(R.id.program_state_select)).getText().toString();

        btnfilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myDialog.dismiss();

                int firmId = 0;
                if(!txtFirmId.matches("")){
                    firmId = Integer.parseInt(txtFirmId);
                }
                int programType = 0;
                if(!txtProgramType.matches("")){
                    programType = Integer.parseInt(txtProgramType);
                }
                int programState = 0;
                if(!txtProgramState.matches("")){
                    programState = Integer.parseInt(txtProgramState);
                }

                FirmsRequest request = new FirmsRequest();
                //request.setFirmId(firmId);
                request.setFirmName("YILMAZ");
                request.setPersonnelId("587");
                request.setTaxNumber(txtTaxNumber);
                request.setProgramType(programType);
                request.setProgramState(programState);
                request.setPageIndex(0);
                request.setPageSize(10);

                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.FIRMS_REQUEST_BUNDLE, request);
                Navigation.findNavController(getActivity(),
                        R.id.nav_host_fragment).navigate(R.id.firmsScreen, bundle);

            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
}
