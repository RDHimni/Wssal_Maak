package ridaz.wssalmaak.models;

/**
 * Rida Dhimni
 * 25/04/2021
 **/

public class CarModel {

    private String carModel;

    public CarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    @Override
    public String toString() {
        return "CarModel{" +
                "carModel='" + carModel + '\'' +
                '}';
    }
}