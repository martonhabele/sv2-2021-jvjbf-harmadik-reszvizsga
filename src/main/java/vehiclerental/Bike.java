package vehiclerental;

import java.time.LocalTime;

public class Bike implements Rentable {
    private String bikeId;
    private LocalTime rentingTime;
    private final int PRICE = 15;

    public Bike(String bikeId) {
        this.bikeId = bikeId;
    }

    @Override
    public int calculateSumPrice(long minutes) {
        return (int) (minutes * PRICE);
    }

    @Override
    public LocalTime getRentingTime() {
        if (rentingTime == null) {
            return null;
        }
        return rentingTime;
    }

    @Override
    public void rent(LocalTime time) {
        this.rentingTime = time;
    }

    @Override
    public void closeRent() {
        this.rentingTime = null;
    }

    public String getBikeId() {
        return bikeId;
    }
}