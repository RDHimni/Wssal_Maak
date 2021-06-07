package ridaz.wssalmaak.ui.profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ridaz.wssalmaak.models.User;
import ridaz.wssalmaak.repo.Repository;

@HiltViewModel
public class ProfileViewModel extends ViewModel {

    private static final String TAG = "ProfileViewModel";
    private Repository repository;
    private LiveData<User> UserRoom = null;



    @Inject
    public ProfileViewModel(Repository repository) {
        this.repository = repository;
    }

    /** Room*/

    public void SelectUserFromRoom(){
        Log.d(TAG, "rida SelectUserFromRoom: ");
        UserRoom = repository.getUserFromRoom();
    }

    public LiveData<User> getUserRoom() {
        Log.d(TAG, "rida getUserRoom: ");
        return UserRoom;
    }

}