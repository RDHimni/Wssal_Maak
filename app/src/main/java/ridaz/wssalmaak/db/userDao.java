package ridaz.wssalmaak.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import ridaz.wssalmaak.models.User;

/**
 * Rida Dhimni
 * 02/04/2021
 **/

@Dao
public interface userDao {

    @Insert
    void  insertUserInRoom(User user);

    @Query("delete from user_table where idUser = :idUser")
    void  deleteUserFromRoom(Integer idUser);

    @Query("select * from user_table")
    LiveData <User> getUserFromRoom();

}