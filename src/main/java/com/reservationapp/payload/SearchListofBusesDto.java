package com.reservationapp.payload;

import javax.persistence.Column;
import lombok.Data;

@Data
public class SearchListofBusesDto {
    private Long busId;
    private int availableSeats;
    private double price;
    private int totalSeats;
    private String busNumber;
    private String busType;

    private Long routeId;

    private String fromLocation;
    private String toLocation;
    private String fromDate;
    private String toDate;
    private String totalDuration;
    private String fromTime;
    private String toTime;
}
