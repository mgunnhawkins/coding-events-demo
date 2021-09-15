package org.launchcode.codingevents.models;

import java.util.Objects;

public class Event {
    //field
    private int id;
    private static int nextId = 1; //creates static counter(belongs to class)
    private String name;
    private String description;

    //constructor
    public Event(String name, String description) {
        this.name = name;
        this.description = description;
        this.id = nextId;
        nextId++;
    }

    //getters and setters
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public int getId() { return id; }

    //toString method
    @Override
    public String toString() { return name; }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Event))
            return false;
        Event event = (Event) o;
        return getId() == event.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
