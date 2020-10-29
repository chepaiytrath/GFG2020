package machinecoding.auctionsystem.models;

import java.util.Objects;

public class Item {
    private final String id;
    private final String name;
    private final String description;

    public Item(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("{ id: %s, name: %s, description: %s }", id, name, description);
    }

    @Override
    public boolean equals(Object o) {
        boolean equals = false;
        if (o == this) {
            equals = true;
        } else if (o instanceof Item) {
            Item item = (Item) o;
            equals = Objects.equals(id, item.id) && Objects.equals(name, item.name) && Objects.equals(description, item.description);
        }
        return equals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}