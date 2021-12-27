package tr.com.deneme.kmobile.di.local;

import android.app.Application;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import tr.com.deneme.kmobile.persistence.KosgebDatabase;
import tr.com.deneme.kmobile.util.Constants;

@Module
public class DbModule {

    @Singleton
    @Provides
    public KosgebDatabase provideKosgebDatabase(Application context) {
        return Room.databaseBuilder(context.getApplicationContext(),
                KosgebDatabase.class, Constants.DATABASE_NAME)
                .build();
    }

    /*
    @Binds
    public abstract RoomDatabase.Builder bindViewModelFactory(KosgebDatabase kosgebDatabase);
    */
}
