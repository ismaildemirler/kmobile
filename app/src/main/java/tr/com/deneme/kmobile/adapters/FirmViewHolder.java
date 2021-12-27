package tr.com.deneme.kmobile.adapters;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;

import tr.com.deneme.kmobile.R;
import tr.com.deneme.kmobile.models.Firms;

public class FirmViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    RequestManager requestManager;
    OnFirmListener onFirmListener;
    private TextView firmName, firstName, lastName;

    public FirmViewHolder(@NonNull View itemView,
                          OnFirmListener onFirmListener,
                          RequestManager requestManager){
        super(itemView);

        this.onFirmListener = onFirmListener;
        this.requestManager = requestManager;

        firmName = itemView.findViewById(R.id.firm_name);
        firstName = itemView.findViewById(R.id.first_name);
        lastName = itemView.findViewById(R.id.last_name);

        itemView.setOnClickListener(this);
    }

    public void onBind(Firms firm){
        firmName.setText(firm.getFirmName());
        firstName.setText(String.valueOf(firm.getFirmId()));
        lastName.setText(firm.getTaxNumber());
    }

    @Override
    public void onClick(View v) {
        onFirmListener.onFirmClick(getAdapterPosition());
    }
}
