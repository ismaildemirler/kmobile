package tr.com.deneme.kmobile.viewmodels.main.firms;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import tr.com.deneme.kmobile.SessionManager;
import tr.com.deneme.kmobile.models.Firms;
import tr.com.deneme.kmobile.models.SystemUsers;
import tr.com.deneme.kmobile.models.request.FirmsRequest;
import tr.com.deneme.kmobile.network.main.MainApi;
import tr.com.deneme.kmobile.repositories.FirmRepository;
import tr.com.deneme.kmobile.util.Constants;
import tr.com.deneme.kmobile.util.Resource;

public class FirmsViewModel extends ViewModel {

    private static final String TAG = "FirmsViewModel";

    private MediatorLiveData<Resource<List<Firms>>> firms = new MediatorLiveData<>();
    private MediatorLiveData<List<Firms>> dbFirms = new MediatorLiveData<>();

    //Query Extras
    private FirmsRequest firmsRequest;
    private int pageNumber;
    private boolean isQueryExhausted;
    private boolean isPerformingQuery;

    private final MainApi mainApi;

    SessionManager sessionManager; // @Singleton scoped dependency

    FirmRepository firmRepository;

    @Inject
    public FirmsViewModel(
            MainApi mainApi,
            FirmRepository firmRepository,
            SessionManager sessionManager
    ) {
        this.sessionManager = sessionManager;
        this.firmRepository = firmRepository;
        this.mainApi = mainApi;
        Log.d(TAG, "FirmsViewModel: viewmodel is working...");
    }

    public int getPageNumber(){
        return pageNumber;
    }

    public LiveData<Resource<List<Firms>>> getFirms(){
        return firms;
    }

    public void searchFirmsApi(FirmsRequest request)
    {
        if(!isPerformingQuery){
            pageNumber = 0;
            firmsRequest = request;
            isQueryExhausted = false;
            executeSearch(request);
        }
    }

    public void searchNextPage(){
        if(!isQueryExhausted && !isPerformingQuery){
            pageNumber++;
            firmsRequest.setPageIndex(pageNumber);
            executeSearch(firmsRequest);
        }
    }

    public void searchPreviousPage(){
        pageNumber--;
        firmsRequest.setPageIndex(pageNumber);
        final LiveData<List<Firms>> repositorySource =
                firmRepository.searchPreviousPage(firmsRequest);
        firms.addSource(repositorySource, new Observer<List<Firms>>() {
            @Override
            public void onChanged(List<Firms> firms) {
                if(firms!=null){
                    isPerformingQuery = false;
                    dbFirms.setValue(firms);
                }
                else{
                    dbFirms.removeSource(repositorySource);
                }
            }
        });
    }

    private void executeSearch(final FirmsRequest request){
        isPerformingQuery = true;
        final LiveData<Resource<List<Firms>>> repositorySource =
                firmRepository.searchFirmsApi(request);
        firms.addSource(repositorySource, new Observer<Resource<List<Firms>>>() {
            @Override
            public void onChanged(Resource<List<Firms>> listResource) {
                if(listResource!=null){
                    firms.setValue(listResource);
                    if(listResource.status == Resource.Status.SUCCESS){
                        isPerformingQuery = false;
                        if(listResource.data!=null){
                            if(listResource.data.size()<request.getPageSize()){
                                Log.d(TAG, "onChanged: Gösterilecek Sonuç Kalmamıştır.");
                                firms.setValue(new Resource<List<Firms>>(
                                        Resource.Status.ERROR,
                                        listResource.data,
                                        Constants.QUERY_EXHAUSTED_MESSAGE
                                ));
                                isQueryExhausted = true;
                            }
                        }
                        firms.removeSource(repositorySource);
                    }
                    else if(listResource.status == Resource.Status.ERROR){
                        isPerformingQuery = false;
                        firms.removeSource(repositorySource);
                    }
                }
                else{
                    firms.removeSource(repositorySource);
                }
            }
        });
    }

    public LiveData<Resource<SystemUsers>> getUser(){
        return sessionManager.getUser();
    }
}
