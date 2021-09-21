package org.launchcode.codingevents.models;

import javax.validation.constraints.*;
import java.util.Objects;

/**
 * Created by Chris Bay
 */
public class Event {

    private int id;
    private static int nextId = 1;

    @NotBlank(message = "Name is required.")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters.")
    private String name;

    @Size(max = 500, message = "Description too long!")
    private String description;

    @NotBlank(message = "Must include location.")
    private String location;


    @Min(value = 1, message = "You must enter an attendee number greater than 0.")
    private int numAttendees;

    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email. Try again.")
    private String contactEmail;


    private boolean registrationRequired = true;

    private EventType type;

    public Event(String name, String description, String location, int numAttendees, String contactEmail,
                 boolean registrationRequired, EventType type) {
        this();
        this.name = name;
        this.description = description;
        this.location = location;
        this.numAttendees = numAttendees;
        this.contactEmail = contactEmail;
        this.registrationRequired = registrationRequired;
        this.type = type;

    }
    public Event() {
        this.id = nextId;
        nextId++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public int getNumAttendees() { return numAttendees; }

    public void setNumAttendees(int numAttendees) { this.numAttendees = numAttendees; }

    public boolean isRegistrationRequired() { return registrationRequired; }

    public void setRegistrationRequired(boolean registrationRequired) { this.registrationRequired = registrationRequired; }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public int getId() {
        return id;
    }

    public EventType getType() { return type; }

    public void setType(EventType type) { this.type = type; }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
