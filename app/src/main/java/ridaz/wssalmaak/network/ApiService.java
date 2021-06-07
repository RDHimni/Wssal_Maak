package ridaz.wssalmaak.network;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import ridaz.wssalmaak.models.RequestModel;
import ridaz.wssalmaak.models.Respanse;

/**
 * Rida Dhimni
 * 22/03/2021
 **/

public interface ApiService {


    @POST("UserInscription.php")
    Observable<Respanse> InsertUserInDb(@Body RequestModel requestModel);

    @FormUrlEncoded
    @POST("UserInscription.php")
    Observable<Respanse> InsertUserInDb2(
            @Field("FirstName") String Name,
            @Field("LastName") String FamilyName,
            @Field("PhoneNumber") String PhoneNumber,
            @Field("Email") String Email,
            @Field("Password") String Password,
            @Field("BeardDate") String BeardDate,
            @Field("Sexion") String Sexion,
            @Field("ProfilePhoto") String ProfilePhoto,
            @Field("TokenFCM") String TokenFCM,
            @Field("DateInscription") String DateInscription

            );

    @FormUrlEncoded
    @POST("UserInscription.php")
    Observable<Respanse> uploadImage(
            @Field("ProfilePhoto") String ProfilePhoto

    );
}
