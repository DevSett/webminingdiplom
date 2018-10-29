package automining.model;

import java.sql.Date;

public class Rig {

    private int id;
    private int userId;
    private String name;
    private double averageSpeed;
    private int averageTemperature;
    private int numberVideoCards;
    private String lastUpdate;

    public Rig(int id, int userId, String name, double averageSpeed, int averageTemperature, int numberVideoCards, String lastUpdate) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.averageSpeed = averageSpeed;
        this.averageTemperature = averageTemperature;
        this.numberVideoCards = numberVideoCards;
        this.lastUpdate = lastUpdate;
    }

    public Rig() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public int getAverageTemperature() {
        return averageTemperature;
    }

    public void setAverageTemperature(int averageTemperature) {
        this.averageTemperature = averageTemperature;
    }

    public int getNumberVideoCards() {
        return numberVideoCards;
    }

    public void setNumberVideoCards(int numberVideoCards) {
        this.numberVideoCards = numberVideoCards;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }


    @Override
    public String toString() {
        return "Statistic{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", averageSpeed=" + averageSpeed +
                ", averageTemperature=" + averageTemperature +
                ", numberVideoCards=" + numberVideoCards +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
