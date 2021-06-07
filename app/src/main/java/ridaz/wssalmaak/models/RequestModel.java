package ridaz.wssalmaak.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Rida Dhimni
 * 10/04/2021
 **/

public class RequestModel {


    @SerializedName("FirstName")
    @Expose
    public  String FirstName;

    @SerializedName("LastName")
    @Expose
    public  String LastName;


    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }
}