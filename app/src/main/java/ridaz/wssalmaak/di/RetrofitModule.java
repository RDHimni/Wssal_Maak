package ridaz.wssalmaak.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ridaz.wssalmaak.network.ApiService;


/**
 * Rida Dhimni
 * 22/03/2021
 **/

@Module
@InstallIn(SingletonComponent.class)
public class RetrofitModule {

    @Provides
    @Singleton
    public static ApiService provideApiService() {
        return new Retrofit.Builder()
                .baseUrl("http://192.168.1.112/apps/wssal%20maak/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(ApiService.class);

    }
}