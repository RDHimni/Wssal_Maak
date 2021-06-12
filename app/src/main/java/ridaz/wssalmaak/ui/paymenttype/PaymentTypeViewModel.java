package ridaz.wssalmaak.ui.paymenttype;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ridaz.wssalmaak.models.User;
import ridaz.wssalmaak.repo.Repository;

/**
 * Rida Dhimni
 * 10/06/2021
 **/

@HiltViewModel
public class PaymentTypeViewModel extends ViewModel {

    private static final String TAG = "PaymentTypeViewModel";
    private Repository repository;
    private LiveData<User> UserRoom = null;



    @Inject
    public PaymentTypeViewModel(Repository repository) {
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