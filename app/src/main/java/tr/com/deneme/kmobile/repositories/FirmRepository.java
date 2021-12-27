package tr.com.deneme.kmobile.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.sqlite.db.SimpleSQLiteQuery;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import tr.com.deneme.kmobile.AppExecutors;
import tr.com.deneme.kmobile.models.Firms;
import tr.com.deneme.kmobile.models.request.FirmsRequest;
import tr.com.deneme.kmobile.models.response.FirmsResponse;
import tr.com.deneme.kmobile.network.ApiResponse;
import tr.com.deneme.kmobile.network.main.MainApi;
import tr.com.deneme.kmobile.persistence.firm.FirmDataSource;
import tr.com.deneme.kmobile.util.NetworkBoundResource;
import tr.com.deneme.kmobile.util.Resource;

public class FirmRepository {
    private static final String TAG = "FirmRepository";

    private static MainApi mainApi;

    FirmDataSource dataSource;
    AppExecutors appExecutors;

    @Inject
    public FirmRepository (MainApi mainApi, FirmDataSource dataSource, AppExecutors appExecutors){
        this.mainApi = mainApi;
        this.dataSource = dataSource;
        this.appExecutors = appExecutors;
    }

    public LiveData<Resource<List<Firms>>> searchFirmsApi(final FirmsRequest request){
        return new NetworkBoundResource<List<Firms>, FirmsResponse>(appExecutors){

            @Override
            protected void saveCallResult(@NonNull FirmsResponse item) {
                if(item.getFirms() != null){ // recipe list will be null if the api key is expired

                    Firms[] firms = new Firms[item.getFirms().size()];
                    //timestamp bakılacak,
                    int index = 0;
                    for(long rowid: dataSource.insertFirms((Firms[]) (item.getFirms().toArray(firms)))){
                        if(rowid == -1){
                            Log.d(TAG, "saveCallResult: CONFLICT... This recipe is already in the cache");
                            // if the recipe already exists... I don't want to set the ingredients or timestamp b/c
                            // they will be erased

                            dataSource.updateFirm(firms[index]);
                        }
                        index++;
                        if(index == firms.length){
                            break;
                        }
                    }
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Firms> data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<Firms>> loadFromDb() {
                return dataSource.buildCustomQueryByRequest(request);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<FirmsResponse>> createCall() {
                return mainApi.getFirmListBySearchParams(request.getFirmId(), request.getTaxNumber(),
                        request.getFirmName(), request.getProgramState(), request.getProgramType(),
                        request.getPersonnelId(), request.getPageIndex(), request.getPageSize());
            }
        }.getAsLiveData();
    }

    public LiveData<List<Firms>> searchPreviousPage(FirmsRequest request) {
        return dataSource.buildCustomQueryByRequest(request);
    }

    /*
    public LiveData<Resource<Firms>> searchFirmApi(final String recipeId){
        return new NetworkBoundResource<Firms, FirmResponse>(appExecutors){
            @Override
            protected void saveCallResult(@NonNull FirmResponse item) {

                // will be null if API key is expired
                if(item.getFirm() != null){
                    item.getFirm().setTimeStamp((int)(System.currentTimeMillis() / 1000));
                    kosgebDao.insertFirm(item.getFirm());
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable Firms data) {
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

            @NonNull
            @Override
            protected LiveData<Firms> loadFromDb() {
                return kosgebDao.getRecipe(recipeId);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<FirmResponse>> createCall() {
                return ServiceGenerator.getRecipeApi().getRecipe(
                        Constants.API_KEY,
                        recipeId
                );
            }
        }.getAsLiveData();
    }
    */
}
