package vehiclerental;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class RentService {
    private Set<Rentable> rentables = new HashSet<>();
    private Set<User> users = new HashSet<>();
    private Map<Rentable, User> actualRenting = new TreeMap<>();

    public void registerUser(User user) {
        if (users.contains(user)) {
            throw new UserNameIsAlreadyTakenException("Username is taken!");
        } else {
            users.add(user);
        }
    }

    public void rent(User user, Rentable rentable, LocalTime time) {
        if (rentable.calculateSumPrice(180) <= user.getBalance() && rentable.getRentingTime() == null) {
            rentable.rent(time);
            actualRenting.put(rentable, user);
        } else {
            throw new IllegalStateException("Renting cannot be initialized!");
        }
    }

    public void addRentable(Rentable rentable) {
        rentables.add(rentable);
    }

    public void closeRent(Rentable rentable, int minutes) {
        if (checkError(rentable, minutes)) {
            throw new IllegalStateException("Renting cannot be closed!");
        }
        actualRenting.get(rentable).minusBalance(rentable.calculateSumPrice(minutes));
        actualRenting.remove(rentable);
        rentable.closeRent();
    }

    public boolean checkError(Rentable rentable, int minutes) {
        return rentable.getRentingTime() == null && minutes > 180;
    }

    public Set<Rentable> getRentables() {
        return rentables;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Map<Rentable, User> getActualRenting() {
        return actualRenting;
    }
}