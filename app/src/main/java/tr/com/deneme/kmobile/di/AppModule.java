package tr.com.deneme.kmobile.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Looper;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Handler;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import tr.com.deneme.kmobile.R;
import tr.com.deneme.kmobile.util.SharedPrefsHelper;
import tr.com.deneme.kmobile.util.TextHelper;

@Module
public abstract class AppModule {

    @Binds
    abstract Context bindContext(Application application);

    @Singleton
    @Provides
    static TextHelper provideTextHelper(){
        return new TextHelper();
    }

    @Singleton
    @Provides
    static SharedPreferences provideSharePreferences(Application application){
        return application.getSharedPreferences("APP_PREFERENCES", Context.MODE_PRIVATE);
    }

    @Singleton
    @Provides
    static SharedPrefsHelper provideSharePreferencesHelper(SharedPreferences sharedPreferences){
        return new SharedPrefsHelper(sharedPreferences);
    }

    @Singleton
    @Provides
    static RequestOptions provideRequestOptions(){
        return RequestOptions
                .placeholderOf(R.drawable.white_background)
                .error(R.drawable.white_background);
    }

    @Singleton
    @Provides
    static RequestManager provideGlideInstance(Application application, RequestOptions requestOptions){
        return Glide.with(application)
                .setDefaultRequestOptions(requestOptions);
    }

    @Singleton
    @Provides
    static Drawable provideAppDrawable(Application application){
        return ContextCompat.getDrawable(application, R.drawable.logo);
    }

    @Singleton
    @Provides
    static Executor provideDiskIOExecutor(){
        return Executors.newSingleThreadExecutor();
    }

    /*
    @Singleton
    @Provides
    static RecyclerView.ItemDecoration provideVerticalSpacingItemDecoration(int spaceHeight)
    {
        return new VerticalSpacingItemDecoration(spaceHeight);
    }
    */
}
