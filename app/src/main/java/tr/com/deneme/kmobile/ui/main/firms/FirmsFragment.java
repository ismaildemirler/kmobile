package tr.com.deneme.kmobile.ui.main.firms;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import dagger.android.support.DaggerFragment;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import tr.com.deneme.kmobile.R;
import tr.com.deneme.kmobile.adapters.FirmsRecyclerAdapter;
import tr.com.deneme.kmobile.adapters.OnFirmListener;
import tr.com.deneme.kmobile.models.Firms;
import tr.com.deneme.kmobile.models.SystemUsers;
import tr.com.deneme.kmobile.models.request.FirmsRequest;
import tr.com.deneme.kmobile.util.Constants;
import tr.com.deneme.kmobile.util.Resource;
import tr.com.deneme.kmobile.util.RxSearchObservable;
import tr.com.deneme.kmobile.util.VerticalSpacingItemDecoration;
import tr.com.deneme.kmobile.viewmodels.ViewModelProviderFactory;
import tr.com.deneme.kmobile.viewmodels.main.firms.FirmsViewModel;

public class FirmsFragment extends DaggerFragment implements OnFirmListener {

    private static final String TAG = "FirmsFragment";

    private FirmsViewModel firmsViewModel;
    private RecyclerView mRecyclerView;
    private SearchView mSearchView;
    boolean clicked = false;

    SystemUsers systemUsers = new SystemUsers();
    FirmsRecyclerAdapter mAdapter;
    private CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    ViewModelProviderFactory providerFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_firms, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: FirmsFragment. " + this);
        mRecyclerView = view.findViewById(R.id.firms_list);
        firmsViewModel = ViewModelProviders.of(this, providerFactory).get(FirmsViewModel.class);

        SubscribeUserObserver();
        initFirmList();
        initRecyclerView();
        SubscribeFirmsObserver();


        setActionBarTitle("Firma Listesi");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_search:
                mSearchView = (SearchView) item.getActionView();
                initSearchView();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void initSearchView(){
        mSearchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        mSearchView.setQueryHint("En az 3 karakter giriniz...");
        RxSearchObservable.fromView(mSearchView)
                .debounce(1000, TimeUnit.MILLISECONDS)
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String text) {
                        if (text.isEmpty()) {
                            if(clicked){
                                return true;
                            }
                            return false;
                        } else {
                            return true;
                        }
                    }
                })
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        searchFirmsApi(s);
                    }
                });
    }

    public void SubscribeFirmsObserver(){
        firmsViewModel.getFirms().removeObservers(getViewLifecycleOwner());
        firmsViewModel.getFirms().observe(getViewLifecycleOwner(), new Observer<Resource<List<Firms>>>() {
            @Override
            public void onChanged(Resource<List<Firms>> listResource) {
                if(listResource!=null){
                    if(listResource.data!=null){
                        switch (listResource.status){
                            case LOADING:{
                                if(firmsViewModel.getPageNumber() > 1){
                                    mAdapter.displayLoading();
                                }
                                else{
                                    mAdapter.displayOnlyLoading();
                                }
                                break;
                            }

                            case ERROR:{
                                Log.e(TAG, "onChanged: cannot refresh the cache." );
                                Log.e(TAG, "onChanged: ERROR message: " + listResource.message );
                                Log.e(TAG, "onChanged: status: ERROR, #recipes: " + listResource.data.size());
                                mAdapter.hideLoading();
                                if(listResource.data.size() > 0) {
                                    mAdapter.setFirms(listResource.data);
                                }
                                Toast.makeText(getContext(), listResource.message, Toast.LENGTH_SHORT).show();

                                if(listResource.message.equals(Constants.EXHAUSTED_MESAGE)){
                                    mAdapter.setQueryExhausted();
                                }
                                break;
                            }

                            case SUCCESS:{
                                Log.d(TAG, "onChanged: cache has been refreshed.");
                                Log.d(TAG, "onChanged: status: SUCCESS, #Recipes: " + listResource.data.size());
                                mAdapter.hideLoading();
                                mAdapter.setFirms(listResource.data);
                                break;
                            }
                        }
                    }
                }
            }
        });
    }

    public void SubscribeUserObserver(){
        firmsViewModel.getUser().removeObservers(getViewLifecycleOwner());
        firmsViewModel.getUser().observe(getViewLifecycleOwner(), new Observer<Resource<SystemUsers>>() {
            @Override
            public void onChanged(Resource<SystemUsers> systemUsersResource) {
                if(systemUsersResource!=null){
                    if(systemUsersResource.data!=null){
                        switch (systemUsersResource.status){
                            case AUTHENTICATED:{
                                /*
                                mRecyclerView.smoothScrollToPosition(0);
                                FirmsRequest request = new FirmsRequest();
                                request.setPageIndex(0);
                                request.setPageSize(10);
                                request.setPersonnelId(String.valueOf(systemUsersResource.data.getPersonnelId()));
                                firmsViewModel.searchFirmsApi(request);
                                                                 */
                                break;
                            }

                            case ERROR:{
                                Log.d(TAG, "onChanged: FirmsFragment: ERROR...");
                                break;
                            }
                        }
                    }
                }
            }
        });
    }

    public void initFirmList(){

        mRecyclerView.smoothScrollToPosition(0);
        FirmsRequest request = new FirmsRequest();
        request.setPageIndex(0);
        request.setPageSize(10);
        //request.setPersonnelId(String.valueOf(systemUsers.getPersonnelId()));
        request.setPersonnelId("587");
        firmsViewModel.searchFirmsApi(request);
    }

    public void setActionBarTitle(String title) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
    }

    private void searchFirmsApi(String query){
        clicked = true;
        mRecyclerView.smoothScrollToPosition(0);
        FirmsRequest request = new FirmsRequest();
        request.setPageIndex(0);
        request.setPageSize(10);
        //request.setPersonnelId(String.valueOf(systemUsers.getPersonnelId()));
        request.setPersonnelId("587");
        request.setFirmName(query);
        firmsViewModel.searchFirmsApi(request);
        mSearchView.clearFocus();
    }

    private void initRecyclerView(){
        mAdapter = new FirmsRecyclerAdapter(this);
        VerticalSpacingItemDecoration itemDecorator = new VerticalSpacingItemDecoration(30);
        mRecyclerView.addItemDecoration(itemDecorator);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(!mRecyclerView.canScrollVertically(1)){
                    firmsViewModel.searchNextPage();
                }
            }
        });

        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onFirmClick(int position) {
        /*
        Intent intent = new Intent(this, RecipeActivity.class);
        intent.putExtra("recipe", mAdapter.getSelectedRecipe(position));
        startActivity(intent);
        */
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposables.clear(); // clear disposables
    }
}
