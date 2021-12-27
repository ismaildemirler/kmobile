package tr.com.deneme.kmobile.di.main;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import tr.com.deneme.kmobile.ui.main.firms.FirmsFragment;
import tr.com.deneme.kmobile.ui.main.profile.ProfileFragment;

@Module
public abstract class MainFragmentsBuilderModule {

    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();

    @ContributesAndroidInjector
    abstract FirmsFragment contributeFirmsFragment();
}