package org.launchcode.codingevents.models;

public enum EventType {
    CONFERENCE ("Conference"),
    MEETUP ("Meetup"),
    WORKSHOP ("Workshop"),
    SOCIAL ("Social");

    private final String displayName;

    EventType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {  //only a getter because displayName is final
        return displayName;
    }
}
