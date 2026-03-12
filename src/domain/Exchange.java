package domain;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import resources.Status;

public class Exchange {
    private String ID;
    private Offer offer;
    private Request request;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime closedAt;
    
    public Exchange(String ID, Offer offer, Request request, Status status, LocalDateTime createdAt, LocalDateTime closedAt) {
        this.ID = ID;
        this.offer = offer;
        this.request = request;
        this.status = status;
        this.createdAt = createdAt;
        this.closedAt = closedAt;
    }

    public Status parseStatus(String s) throws IllegalArgumentException {
        return Status.valueOf(s);
    }

    public LocalDateTime parseLocalDate(String s) throws DateTimeException {
        return LocalDateTime.parse(s);
    }

    public String getID() {
        return ID;
    }
}