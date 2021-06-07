package ridaz.wssalmaak.di;

import android.app.Application;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import ridaz.wssalmaak.db.userDao;
import ridaz.wssalmaak.db.userDb;

/**
 * Rida Dhimni
 * 02/04/2021
 **/


@Module
@InstallIn(SingletonComponent.class)
public class DataBaseModule {

    @Provides
    @Singleton
    public static userDb provideDb(Application application) {
        return Room.databaseBuilder(application, userDb.class, "user_DB")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    public static userDao provideDao(userDb userDb) {
        return userDb.userDao();
    }

}