package tr.com.deneme.kmobile.di;

import androidx.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import tr.com.deneme.kmobile.viewmodels.ViewModelProviderFactory;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory modelProviderFactory);

    /* RetrofitBuilderModule class oluşturularak bu kod yazılabilir. Bunu yazarsan activityde injectli
    kullanırsın
    @Binds
    public abstract Retrofit.Builder bindRetrofitBuilder(RetrofitBuilder retrofitBuilder);
    */
}
