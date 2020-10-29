package machinecoding.auctionsystem.models;

import java.util.Objects;

public class User {
    private final String id;
    private final String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("{ id: %s, name: %s }", id, name);
    }


    @Override
    public boolean equals(Object o) {
        boolean equals = false;
        if (o == this) {
            equals = true;
        } else if (o instanceof User) {
            User user = (User) o;
            equals = Objects.equals(id, user.id) && Objects.equals(name, user.name);
        }
        return equals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}