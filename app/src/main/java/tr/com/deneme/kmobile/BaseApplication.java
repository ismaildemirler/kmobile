package tr.com.deneme.kmobile;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import tr.com.deneme.kmobile.di.DaggerAppComponent;

public class BaseApplication extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
