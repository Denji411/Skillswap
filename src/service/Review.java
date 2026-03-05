package service;

import domain.Exchange;
import domain.Student;

public class Review {
    private Exchange exchange;
    private Student reviewer; //studente recensore
    private Student reviewed; //studente recensito
    private double stars; //recensione in stelle (1-5)

    public Review(Exchange exchange, Student reviewer, Student reviewed) {
        this.exchange = exchange;
        this.reviewer = reviewer;
        this.reviewed = reviewed;
    }
}