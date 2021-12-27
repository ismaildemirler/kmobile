package tr.com.deneme.kmobile.di.main;

import androidx.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import tr.com.deneme.kmobile.di.ViewModelKey;
import tr.com.deneme.kmobile.viewmodels.main.firms.FirmsViewModel;
import tr.com.deneme.kmobile.viewmodels.main.profile.ProfileViewModel;

@Module
public abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindProfileViewModel (ProfileViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(FirmsViewModel.class)
    public abstract ViewModel bindFirmsViewModel (FirmsViewModel viewModel);
}
