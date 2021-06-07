package ridaz.wssalmaak.db;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import ridaz.wssalmaak.models.User;

/**
 * Rida Dhimni
 * 02/04/2021
 **/

@Database(entities = User.class,version = 1,exportSchema = false)

public abstract class userDb extends RoomDatabase {

    public abstract userDao userDao();
}