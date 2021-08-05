package com.frikiteam.review.domain.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "organizers")
public class Organizer extends User {
    private String description;

    private boolean verified;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
