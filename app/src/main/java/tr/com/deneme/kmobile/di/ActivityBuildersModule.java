package tr.com.deneme.kmobile.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import tr.com.deneme.kmobile.di.auth.AuthModule;
import tr.com.deneme.kmobile.di.auth.AuthScope;
import tr.com.deneme.kmobile.di.auth.AuthViewModelsModule;
import tr.com.deneme.kmobile.di.main.MainFragmentsBuilderModule;
import tr.com.deneme.kmobile.di.main.MainModule;
import tr.com.deneme.kmobile.di.main.MainScope;
import tr.com.deneme.kmobile.di.main.MainViewModelsModule;
import tr.com.deneme.kmobile.ui.auth.AuthActivity;
import tr.com.deneme.kmobile.ui.main.MainActivity;

@Module
public abstract class ActivityBuildersModule {

    @AuthScope
    @ContributesAndroidInjector(
            modules = {
                    AuthViewModelsModule.class,
                    AuthModule.class
            }
    )
    abstract AuthActivity contributeAuthActivity();

    @MainScope
    @ContributesAndroidInjector(
            modules = {
                    MainViewModelsModule.class,
                    MainFragmentsBuilderModule.class,
                    MainModule.class
            }
    )
    abstract MainActivity contributeMainActivity();

}
