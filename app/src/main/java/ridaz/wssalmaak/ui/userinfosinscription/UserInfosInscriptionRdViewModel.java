package ridaz.wssalmaak.ui.userinfosinscription;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.http.Field;
import ridaz.wssalmaak.models.RequestModel;
import ridaz.wssalmaak.models.Respanse;
import ridaz.wssalmaak.models.User;
import ridaz.wssalmaak.repo.Repository;

@HiltViewModel
public class UserInfosInscriptionRdViewModel extends ViewModel {

    private static final String TAG = "UserInfosInscriptionRdV";
    private Repository repository;
    private MutableLiveData<Respanse> result = new MutableLiveData<>();

    private LiveData<User> UserRoom = null;



    @Inject
    public UserInfosInscriptionRdViewModel(Repository repository) {
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


    /**
     * Api
     */


    public void InsertUserInDb2(
            String Name,
            String FamilyName,
            String PhoneNumber,
            String Email,
            String Password,
            String BeardDate,
            String Sexion,
            String ProfilePhoto,
            String TokenFCM,
            String DateInscription
    ) {

        repository.InsertUserInDb2(
                Name,
                FamilyName,
                PhoneNumber,
                Email,
                Password,
                BeardDate,
                Sexion,
                ProfilePhoto,
                TokenFCM,
                DateInscription
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        res -> result.setValue(res),
                        err -> Log.d(TAG, "InsertUserInDb: " + err)
                );

    }



    public MutableLiveData<Respanse> getResult() {
        return result;
    }

}