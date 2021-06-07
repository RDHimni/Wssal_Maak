package ridaz.wssalmaak.models;

/**
 * Rida Dhimni
 * 28/04/2021
 **/

public class StopeVille {

    private String VilleName;

    public StopeVille(String villeName) {
        VilleName = villeName;
    }

    public String getVilleName() {
        return VilleName;
    }

    public void setVilleName(String villeName) {
        VilleName = villeName;
    }

    @Override
    public String toString() {
        return "StopeVille{" +
                "VilleName='" + VilleName + '\'' +
                '}';
    }
}