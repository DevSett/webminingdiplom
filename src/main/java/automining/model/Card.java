package automining.model;

public class Card {


    private int id;
    private int rigId;
    private double speed;
    private int temperature;
    private int fanSpeed;

    public Card(int id, int statisticId, double speed, int temperature, int fanSpeed) {
        this.id = id;
        this.rigId = statisticId;
        this.speed = speed;
        this.temperature = temperature;
        this.fanSpeed = fanSpeed;
    }

    public Card() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getFanSpeed() {
        return fanSpeed;
    }

    public void setFanSpeed(int fanSpeed) {
        this.fanSpeed = fanSpeed;
    }

    public int getRigId() {
        return rigId;
    }

    public void setRigId(int rigId) {
        this.rigId = rigId;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", rigId=" +rigId +
                ", speed=" + speed +
                ", temperature=" + temperature +
                ", fanSpeed=" + fanSpeed +
                '}';
    }
}
