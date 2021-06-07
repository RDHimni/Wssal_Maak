package ridaz.wssalmaak.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ridaz.wssalmaak.models.Respanse;
import ridaz.wssalmaak.models.User;
import ridaz.wssalmaak.repo.Repository;

/**
 * Rida Dhimni
 * 12/04/2021
 **/

@HiltViewModel
public class MainViewModel extends ViewModel {


    private static final String TAG = "MainViewModel";
    private Repository repository;
    private LiveData<User> UserRoom = null;


    @Inject
    public MainViewModel(Repository repository) {
        this.repository = repository;
    }

    /** Room*/

    void  insertUserInRoom(User user){
        Log.d(TAG, "rida insertUserInRoom: ");
        repository.insertUserInRoom(user);

    }

    public void SelectUserFromRoom(){
        Log.d(TAG, "rida SelectUserFromRoom: ");
        UserRoom = repository.getUserFromRoom();
    }

    public LiveData<User> getUserRoom() {
        Log.d(TAG, "rida getUserRoom: ");
        return UserRoom;
    }

}