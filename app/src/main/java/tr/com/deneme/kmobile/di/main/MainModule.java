package tr.com.deneme.kmobile.di.main;

import com.bumptech.glide.util.ViewPreloadSizeProvider;

import javax.inject.Named;

import dagger.Binds;
import dagger.BindsInstance;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import tr.com.deneme.kmobile.AppExecutors;
import tr.com.deneme.kmobile.network.main.MainApi;
import tr.com.deneme.kmobile.persistence.firm.FirmDao;
import tr.com.deneme.kmobile.persistence.KosgebDatabase;
import tr.com.deneme.kmobile.persistence.firm.FirmDataSource;
import tr.com.deneme.kmobile.persistence.firm.IFirmDataSource;
import tr.com.deneme.kmobile.repositories.FirmRepository;
import tr.com.deneme.kmobile.util.Constants;

@Module
public abstract class MainModule {

    @MainScope
    @Provides
    static ViewPreloadSizeProvider<String> providePreloadSizeProvider(){
        return new ViewPreloadSizeProvider<>();
    }

    /*
    @Binds
    abstract OnFirmListener provideOnFimListener(FirmsFragment firmsFragment);

    @MainScope
    @Provides
    static RecyclerView.Adapter<RecyclerView.ViewHolder> providesAdapter(OnFirmListener listener) {
        return new FirmsRecyclerAdapter(listener);
    }

    /*
    @MainScope
    @Provides
    static RecyclerView.Adapter<RecyclerView.ViewHolder> providesFirmAdapter(FirmsRecyclerAdapter adapter){
        return adapter;
    }

    static FirmsRecyclerAdapter provideFirmsAdapter(){
        return new FirmsRecyclerAdapter( );
    }
    */

    @MainScope
    @Provides
    static IFirmDataSource provideFirmDataSource(FirmDao firmDao){
        return new FirmDataSource(firmDao);
    }

    @MainScope
    @Provides
    static MainApi provideMainApi(@Named(Constants.NAMED_RETROFIT_WITH_AUTH)Retrofit retrofit){
        return retrofit.create(MainApi.class);
    }

    @MainScope
    @Provides
    static FirmDao provideKosgebDao(KosgebDatabase kosgebDatabase) {
        return kosgebDatabase.getKosgebDao();
    }

    @MainScope
    @Provides
    static FirmRepository provideFirmRepository(MainApi mainApi, FirmDataSource dataSource, AppExecutors appExecutors){
        return new FirmRepository(mainApi, dataSource, appExecutors);
    }
}
