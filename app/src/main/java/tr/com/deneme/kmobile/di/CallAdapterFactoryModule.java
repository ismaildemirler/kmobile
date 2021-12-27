package tr.com.deneme.kmobile.di;

import android.widget.Adapter;

import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import retrofit2.CallAdapter;

@Module
public abstract class CallAdapterFactoryModule {

    /*
    @Binds
    public abstract CallAdapter.Factory bindCallAdapterFactory(LiveDataCallAdapterFactory liveDataCallAdapterFactory);

    @Binds
    abstract CallAdapter.Factory<? extends Adapter>
        bindSyncServiceInjectorFactory(LiveDataCallAdapterFactory liveDataCallAdapterFactory);




    @Singleton
    @Provides
    public KosgebDatabase provideKosgebDatabase(Application context) {
        return Room.databaseBuilder(context.getApplicationContext(),
                KosgebDatabase.class, Constants.DATABASE_NAME)
                .build();
    }
    */
}
