package tr.com.deneme.kmobile.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.util.ViewPreloadSizeProvider;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import tr.com.deneme.kmobile.R;
import tr.com.deneme.kmobile.di.main.MainScope;
import tr.com.deneme.kmobile.models.Firms;
import tr.com.deneme.kmobile.util.Constants;

@MainScope
public class FirmsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int FIRM_TYPE = 1;
    private static final int LOADING_TYPE = 2;
    private static final int EXHAUSTED_TYPE = 3;

    @Inject
    RequestManager requestManager;

    private OnFirmListener mOnFirmListener;
    private List<Firms> mFirms;

    public FirmsRecyclerAdapter(OnFirmListener onFirmListener) {
        mOnFirmListener = onFirmListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType){

            case FIRM_TYPE:{
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_firm_list_item, parent, false);
                return new FirmViewHolder(view, mOnFirmListener, requestManager);
            }

            case LOADING_TYPE:{
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_loading_list_item, parent, false);
                return new LoadingViewHolder(view);
            }

            case EXHAUSTED_TYPE:{
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_search_exhausted, parent, false);
                return new SearchExhaustedViewHolder(view);
            }

            default:{
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_firm_list_item, parent, false);
                return new FirmViewHolder(view, mOnFirmListener, requestManager);
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        int itemViewType = getItemViewType(position);
        if(itemViewType == FIRM_TYPE){
            ((FirmViewHolder)viewHolder).onBind(mFirms.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(mFirms.get(position).getFirmName().equals(Constants.LOADING_MESAGE)){
            return LOADING_TYPE;
        }
        else if(mFirms.get(position).getFirmName().equals(Constants.EXHAUSTED_MESAGE)){
            return EXHAUSTED_TYPE;
        }
        else{
            return FIRM_TYPE;
        }
    }

    public void displayOnlyLoading(){
        clearFirmList();
        Firms firm = new Firms();
        firm.setFirmName(Constants.LOADING_MESAGE);
        mFirms.add(firm);
        notifyDataSetChanged();
    }

    private void clearFirmList(){
        if(mFirms == null){
            mFirms = new ArrayList<>();
        }
        else{
            mFirms.clear();
        }
        notifyDataSetChanged();
    }

    public void setQueryExhausted(){
        hideLoading();
        Firms exhaustedFirm = new Firms();
        exhaustedFirm.setFirmName(Constants.EXHAUSTED_MESAGE);
        mFirms.add(exhaustedFirm);
        notifyDataSetChanged();
    }

    public void hideLoading(){
        if(isLoading()){
            if(mFirms.get(0).getFirmName().equals(Constants.LOADING_MESAGE)){
                mFirms.remove(0);
            }
            else if(mFirms.get(mFirms.size() - 1).equals(Constants.LOADING_MESAGE)){
                mFirms.remove(mFirms.size() - 1);
            }
            notifyDataSetChanged();
        }
    }

    public void displayLoading(){
        if(mFirms == null){
            mFirms = new ArrayList<>();
        }
        if(!isLoading()){
            Firms firm = new Firms();
            firm.setFirmName(Constants.LOADING_MESAGE);
            mFirms.add(firm);
            notifyDataSetChanged();
        }
    }

    private boolean isLoading(){
        if(mFirms != null){
            if(mFirms.size() > 0){
                if(mFirms.get(mFirms.size() - 1).getFirmName().equals(Constants.LOADING_MESAGE)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int getItemCount() {
        if(mFirms != null){
            return mFirms.size();
        }
        return 0;
    }

    public void setFirms(List<Firms> firms){
        mFirms = firms;
        notifyDataSetChanged();
    }

    public Firms getSelectedFirm(int position){
        if(mFirms != null){
            if(mFirms.size() > 0){
                return mFirms.get(position);
            }
        }
        return null;
    }
}