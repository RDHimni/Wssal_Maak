package ridaz.wssalmaak.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Rida Dhimni
 * 31/03/2021
 **/

@Entity(tableName = "user_table")
public class User {


    @PrimaryKey(autoGenerate = true)
    @SerializedName("idUser")
    @Expose
    private int idUser;

    @SerializedName("FirstName")
    @Expose
    private String FirstName;

    @SerializedName("LastName")
    @Expose
    private String LastName;

    @SerializedName("PhoneNumber")
    @Expose
    private String PhoneNumber;

    @SerializedName("Email")
    @Expose
    private String Email;

    @SerializedName("Password")
    @Expose
    private String Password;

    @SerializedName("Sexion")
    @Expose
    private String Sexion;

    @SerializedName("BeardDate")
    @Expose
    private String BeardDate;

    @SerializedName("ProfilePhotoUrl")
    @Expose
    private String ProfilePhotoUrl;

    @SerializedName("CartIdentiteFrontUrl")
    @Expose
    private String CartIdentiteFrontUrl;

    @SerializedName("CartIdentiteBackUrl")
    @Expose
    private String CartIdentiteBackUrl;

    @SerializedName("PassportUrl")
    @Expose
    private String PassportUrl;
    @SerializedName("rate")
    @Expose
    private double Rate;

    @SerializedName("TokenFCM")
    @Expose
    private String TokenFCM;

    @SerializedName("DateInscription")
    @Expose
    private String DateInscription;

    /**
     * """""""""""""""""""
     */
    @SerializedName("status")
    @Expose
    private int Status;

    @SerializedName("avis")
    @Expose
    private int Avis;

    @SerializedName("emailvalide")
    @Expose
    private boolean EmailValide;

    @SerializedName("phonevalide")
    @Expose
    private boolean PhoneValide;

    @SerializedName("identityvalide")
    @Expose
    private boolean IdentityValide;

    public User() {
    }

    public User(String firstName, String lastName, String phoneNumber, String email, String password, String sexion, String beardDate, String profilePhotoUrl, String cartIdentiteFrontUrl, String cartIdentiteBackUrl, String passportUrl, double rate, String tokenFCM, String dateInscription, int status, int avis, boolean emailValide, boolean phoneValide, boolean identityValide) {
        FirstName = firstName;
        LastName = lastName;
        PhoneNumber = phoneNumber;
        Email = email;
        Password = password;
        Sexion = sexion;
        BeardDate = beardDate;
        ProfilePhotoUrl = profilePhotoUrl;
        CartIdentiteFrontUrl = cartIdentiteFrontUrl;
        CartIdentiteBackUrl = cartIdentiteBackUrl;
        PassportUrl = passportUrl;
        Rate = rate;
        TokenFCM = tokenFCM;
        DateInscription = dateInscription;
        Status = status;
        Avis = avis;
        EmailValide = emailValide;
        PhoneValide = phoneValide;
        IdentityValide = identityValide;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

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

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getSexion() {
        return Sexion;
    }

    public void setSexion(String sexion) {
        Sexion = sexion;
    }

    public String getBeardDate() {
        return BeardDate;
    }

    public void setBeardDate(String beardDate) {
        BeardDate = beardDate;
    }

    public String getProfilePhotoUrl() {
        return ProfilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        ProfilePhotoUrl = profilePhotoUrl;
    }

    public String getCartIdentiteFrontUrl() {
        return CartIdentiteFrontUrl;
    }

    public void setCartIdentiteFrontUrl(String cartIdentiteFrontUrl) {
        CartIdentiteFrontUrl = cartIdentiteFrontUrl;
    }

    public String getCartIdentiteBackUrl() {
        return CartIdentiteBackUrl;
    }

    public void setCartIdentiteBackUrl(String cartIdentiteBackUrl) {
        CartIdentiteBackUrl = cartIdentiteBackUrl;
    }

    public String getPassportUrl() {
        return PassportUrl;
    }

    public void setPassportUrl(String passportUrl) {
        PassportUrl = passportUrl;
    }

    public double getRate() {
        return Rate;
    }

    public void setRate(double rate) {
        Rate = rate;
    }

    public String getTokenFCM() {
        return TokenFCM;
    }

    public void setTokenFCM(String tokenFCM) {
        TokenFCM = tokenFCM;
    }

    public String getDateInscription() {
        return DateInscription;
    }

    public void setDateInscription(String dateInscription) {
        DateInscription = dateInscription;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public int getAvis() {
        return Avis;
    }

    public void setAvis(int avis) {
        Avis = avis;
    }

    public boolean isEmailValide() {
        return EmailValide;
    }

    public void setEmailValide(boolean emailValide) {
        EmailValide = emailValide;
    }

    public boolean isPhoneValide() {
        return PhoneValide;
    }

    public void setPhoneValide(boolean phoneValide) {
        PhoneValide = phoneValide;
    }

    public boolean isIdentityValide() {
        return IdentityValide;
    }

    public void setIdentityValide(boolean identityValide) {
        IdentityValide = identityValide;
    }
}
