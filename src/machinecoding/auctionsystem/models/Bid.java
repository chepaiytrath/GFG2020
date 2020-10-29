package machinecoding.auctionsystem.models;

import java.util.Objects;

public class Bid {
    private final User user;
    private final int value;

    public Bid(User user, int value) {
        this.user = user;
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public int getValue() {
        return value;
    }

    public boolean isFromUser(User user) {
        return this.user.equals(user);
    }

    @Override
    public String toString() {
        return String.format("{ user: %s, value: %s }", user, value);
    }

    @Override
    public boolean equals(Object o) {
        boolean equals = false;
        if (o == this) {
            equals = true;
        } else if (o instanceof Bid) {
            Bid bid = (Bid) o;
            equals = Objects.equals(user, bid.user) && value == bid.value;
        }
        return equals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, value);
    }

}