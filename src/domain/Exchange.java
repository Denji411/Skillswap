package domain;

import java.time.LocalDateTime;
import java.util.regex.Pattern;
import resources.Status;

public class Exchange {
    private String ID;
    private Offer offer;
    private Request request;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime closedAt;

    private static final Pattern ID_PATTERN =
           Pattern.compile("^[E]\\d+$");
    
    public Exchange(String ID, Offer offer, Request request, Status status, LocalDateTime createdAt, LocalDateTime closedAt) {
        if (!isValidID(ID)) throw new IllegalArgumentException("Id non valido");

        this.ID = ID;
        this.offer = offer;
        this.request = request;
        this.status = status;
        this.createdAt = createdAt;
        this.closedAt = closedAt;
    }

    private boolean isValidID(String ID) {
        return ID != null && ID_PATTERN.matcher(ID).matches();
    }

    public String getID() {
        return ID;
    }
}