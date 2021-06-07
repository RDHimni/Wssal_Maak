package ridaz.wssalmaak.models;

import java.io.Serializable;

/**
 * Rida Dhimni
 * 26/04/2021
 **/

public class Car {

    private Integer CarId;
    private String Matricule1;
    private String Matricule2;
    private String Matricule3;
    private String Model;
    private String Type;
    private String DateExperationAssurance;
    private String Color;
    private User user;

    public Car(Integer carId, String matricule1, String matricule2, String matricule3, String model, String type, String dateExperationAssurance, String color) {
        CarId = carId;
        Matricule1 = matricule1;
        Matricule2 = matricule2;
        Matricule3 = matricule3;
        Model = model;
        Type = type;
        DateExperationAssurance = dateExperationAssurance;
        Color = color;
    }

    public Car(String matricule1, String matricule2, String matricule3, String model, String type, String dateExperationAssurance, String color) {
        Matricule1 = matricule1;
        Matricule2 = matricule2;
        Matricule3 = matricule3;
        Model = model;
        Type = type;
        DateExperationAssurance = dateExperationAssurance;
        Color = color;
    }

    public Car(String matricule1, String matricule2, String matricule3, String model, String type, String dateExperationAssurance, String color, User user) {
        Matricule1 = matricule1;
        Matricule2 = matricule2;
        Matricule3 = matricule3;
        Model = model;
        Type = type;
        DateExperationAssurance = dateExperationAssurance;
        Color = color;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getCarId() {
        return CarId;
    }

    public void setCarId(Integer carId) {
        CarId = carId;
    }

    public String getMatricule1() {
        return Matricule1;
    }

    public void setMatricule1(String matricule1) {
        Matricule1 = matricule1;
    }

    public String getMatricule2() {
        return Matricule2;
    }

    public void setMatricule2(String matricule2) {
        Matricule2 = matricule2;
    }

    public String getMatricule3() {
        return Matricule3;
    }

    public void setMatricule3(String matricule3) {
        Matricule3 = matricule3;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getDateExperationAssurance() {
        return DateExperationAssurance;
    }

    public void setDateExperationAssurance(String dateExperationAssurance) {
        DateExperationAssurance = dateExperationAssurance;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "CarId=" + CarId +
                ", Matricule1='" + Matricule1 + '\'' +
                ", Matricule2='" + Matricule2 + '\'' +
                ", Matricule3='" + Matricule3 + '\'' +
                ", Model='" + Model + '\'' +
                ", Type='" + Type + '\'' +
                ", DateExperationAssurance='" + DateExperationAssurance + '\'' +
                ", Color='" + Color + '\'' +
                ", user=" + user +
                '}';
    }
}