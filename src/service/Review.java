package service;

import domain.Exchange;
import domain.Student;
import java.time.LocalDateTime;

public class Review {
    private String ID;
    private Exchange exchange;
    private Student reviewer; //studente recensore
    private Student reviewed; //studente recensito
    private double stars; //recensione in stelle (1-5)
    LocalDateTime createdAt;

    public Review(String ID, Exchange exchange, Student reviewer, Student reviewed, double stars, String comment, LocalDateTime createdAt) {
        this.ID = ID;
        this.exchange = exchange;
        this.reviewer = reviewer;
        this.reviewed = reviewed;
        this.stars = stars;
        this.createdAt = createdAt;
    }

    public String getID() {
        return ID;
    }
}