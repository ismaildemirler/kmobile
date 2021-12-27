package tr.com.deneme.kmobile.di;

import android.app.Application;
import javax.inject.Singleton;
import dagger.BindsInstance;
import dagger.Component;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import tr.com.deneme.kmobile.AppExecutors;
import tr.com.deneme.kmobile.BaseApplication;
import tr.com.deneme.kmobile.SessionManager;
import tr.com.deneme.kmobile.di.local.DbModule;
import tr.com.deneme.kmobile.di.network.NetworkModule;

@Singleton
@Component(
        modules = {
                //CallAdapterFactoryModule.class,
                AndroidSupportInjectionModule.class,
                ActivityBuildersModule.class,
                AppModule.class,
                ViewModelFactoryModule.class,
                NetworkModule.class,
                DbModule.class
        }
)
public interface AppComponent extends AndroidInjector<BaseApplication> {

    SessionManager sessionManager();
    AppExecutors appExecutors();

    @Component.Builder
    interface Builder{

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}