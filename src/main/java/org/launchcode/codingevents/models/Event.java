package org.launchcode.codingevents.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

public class Event {
    //field
    private int id;
    private static int nextId = 1; //creates static counter(belongs to class)

    @NotBlank(message= "Name is required.")
    @Size(min = 3, max=50, message="Name must be between 3 and 50 characters.")
    private String name;

    @Size(max=500, message = "Description too long.") //allows for description to be blank with no min
    private String description;
    @NotBlank(message = "Must include e-mail.")
    @Email(message = "Invalid email.  Try again.")
    private String contactEmail;

    //constructor

    public Event(String name, String description, String contactEmail) {
        this();
        this.name = name;
        this.description = description;
        this.contactEmail = contactEmail;
    }

    public Event() {
        this.id = nextId;
        nextId++;
    }; //creates empty event object so we can pass it to the view.  Empty event object will have
    // event fields already attached.

    //getters and setters
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

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
