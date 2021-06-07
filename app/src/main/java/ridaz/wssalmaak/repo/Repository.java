package ridaz.wssalmaak.repo;

import androidx.lifecycle.LiveData;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import ridaz.wssalmaak.db.userDao;
import ridaz.wssalmaak.models.RequestModel;
import ridaz.wssalmaak.models.Respanse;
import ridaz.wssalmaak.models.User;
import ridaz.wssalmaak.network.ApiService;

/**
 * Rida Dhimni
 * 22/03/2021
 **/

public class Repository {

    private ApiService apiService;
    private userDao userDao;

    @Inject
    public Repository(ApiService apiService, ridaz.wssalmaak.db.userDao userDao) {
        this.apiService = apiService;
        this.userDao = userDao;
    }



    //Room

   public void  insertUserInRoom(User user){
        userDao.insertUserInRoom(user);
    }

    public LiveData<User> getUserFromRoom(){
        return userDao.getUserFromRoom();
    }

    //functions here

    public Observable<Respanse> InsertUserInDb(RequestModel requestModel) {
        return apiService.InsertUserInDb(requestModel);
    }

    public Observable<Respanse> InsertUserInDb2(
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
        return apiService.InsertUserInDb2(
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
        );
    }

    public Observable<Respanse> uploadImage(String ProfilePhoto) {
        return apiService.uploadImage(ProfilePhoto);
    }

}