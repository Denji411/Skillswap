package domain;

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

    public String getID() {
        return ID;
    }
}