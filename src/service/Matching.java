package service;

import java.util.Map;

import domain.Offer;

public final class Matching {
    public static void findOneWayMatches(String ID, Map<String, Request> requests, Map<String, Offer> offers) {
        for (Map.Entry<String, Request> entry : requests.entrySet()) {
            System.out.println("Chiave: " + entry.getKey() + ", Valore: " + entry.getValue());
            Request r = entry.getValue();
            
        }
    }
}