package ridaz.wssalmaak.models;

import java.io.Serializable;

/**
 * Rida Dhimni
 * 28/04/2021
 **/

public class Offer {

    private Integer OfferId;
    private String VilleDepart;
    private String AdresseDepart;
    private String LatitudeMapAdresseDepart;
    private String LongitudeMapAdresseDepart;
    private String VilleArriver;
    private String LatitudeMapVilleArriver;
    private String LongitudeMapVilleArriver;
    private String DateDepart;
    private String DateArriver;
    private String TempsDepart;
    private String TempsArriver;
    private String TrajetParKm;
    private Car Car;
    private User Livreur;
    private Integer status;
    private double NumberKilos;
    private double PrixParKilos;
    private String StopeVilles;
    private int PaymentType;

    public Offer() {
    }


    public Offer(String villeDepart, String adresseDepart, String latitudeMapAdresseDepart, String longitudeMapAdresseDepart, String villeArriver, String latitudeMapVilleArriver, String longitudeMapVilleArriver, String dateDepart, String dateArriver, String tempsDepart, String tempsArriver, String trajetParKm, ridaz.wssalmaak.models.Car car, User livreur, Integer status, double numberKilos, double prixParKilos, String stopeVilles, int paymentType) {
        VilleDepart = villeDepart;
        AdresseDepart = adresseDepart;
        LatitudeMapAdresseDepart = latitudeMapAdresseDepart;
        LongitudeMapAdresseDepart = longitudeMapAdresseDepart;
        VilleArriver = villeArriver;
        LatitudeMapVilleArriver = latitudeMapVilleArriver;
        LongitudeMapVilleArriver = longitudeMapVilleArriver;
        DateDepart = dateDepart;
        DateArriver = dateArriver;
        TempsDepart = tempsDepart;
        TempsArriver = tempsArriver;
        TrajetParKm = trajetParKm;
        Car = car;
        Livreur = livreur;
        this.status = status;
        NumberKilos = numberKilos;
        PrixParKilos = prixParKilos;
        StopeVilles = stopeVilles;
        PaymentType = paymentType;
    }


    public Integer getOfferId() {
        return OfferId;
    }

    public void setOfferId(Integer offerId) {
        OfferId = offerId;
    }

    public String getVilleDepart() {
        return VilleDepart;
    }

    public void setVilleDepart(String villeDepart) {
        VilleDepart = villeDepart;
    }

    public String getAdresseDepart() {
        return AdresseDepart;
    }

    public void setAdresseDepart(String adresseDepart) {
        AdresseDepart = adresseDepart;
    }

    public String getLatitudeMapAdresseDepart() {
        return LatitudeMapAdresseDepart;
    }

    public void setLatitudeMapAdresseDepart(String latitudeMapAdresseDepart) {
        LatitudeMapAdresseDepart = latitudeMapAdresseDepart;
    }

    public String getLongitudeMapAdresseDepart() {
        return LongitudeMapAdresseDepart;
    }

    public void setLongitudeMapAdresseDepart(String longitudeMapAdresseDepart) {
        LongitudeMapAdresseDepart = longitudeMapAdresseDepart;
    }

    public String getVilleArriver() {
        return VilleArriver;
    }

    public void setVilleArriver(String villeArriver) {
        VilleArriver = villeArriver;
    }

    public String getLatitudeMapVilleArriver() {
        return LatitudeMapVilleArriver;
    }

    public void setLatitudeMapVilleArriver(String latitudeMapVilleArriver) {
        LatitudeMapVilleArriver = latitudeMapVilleArriver;
    }

    public String getLongitudeMapVilleArriver() {
        return LongitudeMapVilleArriver;
    }

    public void setLongitudeMapVilleArriver(String longitudeMapVilleArriver) {
        LongitudeMapVilleArriver = longitudeMapVilleArriver;
    }

    public String getDateDepart() {
        return DateDepart;
    }

    public void setDateDepart(String dateDepart) {
        DateDepart = dateDepart;
    }

    public String getDateArriver() {
        return DateArriver;
    }

    public void setDateArriver(String dateArriver) {
        DateArriver = dateArriver;
    }

    public String getTempsDepart() {
        return TempsDepart;
    }

    public void setTempsDepart(String tempsDepart) {
        TempsDepart = tempsDepart;
    }

    public String getTempsArriver() {
        return TempsArriver;
    }

    public void setTempsArriver(String tempsArriver) {
        TempsArriver = tempsArriver;
    }

    public String getTrajetParKm() {
        return TrajetParKm;
    }

    public void setTrajetParKm(String trajetParKm) {
        TrajetParKm = trajetParKm;
    }

    public ridaz.wssalmaak.models.Car getCar() {
        return Car;
    }

    public void setCar(ridaz.wssalmaak.models.Car car) {
        Car = car;
    }

    public User getLivreur() {
        return Livreur;
    }

    public void setLivreur(User livreur) {
        Livreur = livreur;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public double getNumberKilos() {
        return NumberKilos;
    }

    public void setNumberKilos(double numberKilos) {
        NumberKilos = numberKilos;
    }

    public double getPrixParKilos() {
        return PrixParKilos;
    }

    public void setPrixParKilos(double prixParKilos) {
        PrixParKilos = prixParKilos;
    }

    public String getStopeVilles() {
        return StopeVilles;
    }

    public void setStopeVilles(String stopeVilles) {
        StopeVilles = stopeVilles;
    }

    public int getPaymentType() {
        return PaymentType;
    }

    public void setPaymentType(int paymentType) {
        PaymentType = paymentType;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "OfferId=" + OfferId +
                ", VilleDepart='" + VilleDepart + '\'' +
                ", AdresseDepart='" + AdresseDepart + '\'' +
                ", LatitudeMapAdresseDepart='" + LatitudeMapAdresseDepart + '\'' +
                ", LongitudeMapAdresseDepart='" + LongitudeMapAdresseDepart + '\'' +
                ", VilleArriver='" + VilleArriver + '\'' +
                ", LatitudeMapVilleArriver='" + LatitudeMapVilleArriver + '\'' +
                ", LongitudeMapVilleArriver='" + LongitudeMapVilleArriver + '\'' +
                ", DateDepart='" + DateDepart + '\'' +
                ", DateArriver='" + DateArriver + '\'' +
                ", TempsDepart='" + TempsDepart + '\'' +
                ", TempsArriver='" + TempsArriver + '\'' +
                ", TrajetParKm='" + TrajetParKm + '\'' +
                ", Car=" + Car +
                ", Livreur=" + Livreur +
                ", status=" + status +
                ", NumberKilos=" + NumberKilos +
                ", PrixParKilos=" + PrixParKilos +
                ", StopeVilles='" + StopeVilles + '\'' +
                ", PaymentType=" + PaymentType +
                '}';
    }
}