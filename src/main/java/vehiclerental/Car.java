package vehiclerental;

import java.time.LocalTime;

public class Car implements Rentable {
    private String plateNumber;
    private LocalTime rentingTime;
    private int price;

    public Car(String plateNumber, int price) {
        this.plateNumber = plateNumber;
        this.price = price;
    }

    @Override
    public int calculateSumPrice(long minutes) {
        return (int) (minutes * price);
    }

    @Override
    public LocalTime getRentingTime() {
        if (rentingTime == null) {
            return null;
        }
        return LocalTime.now();
    }

    @Override
    public void rent(LocalTime time) {
        this.rentingTime = time;
    }

    @Override
    public void closeRent() {
        this.rentingTime = null;
    }
}