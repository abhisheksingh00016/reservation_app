package com.reservationapp.controller;

import com.reservationapp.entity.Bus;
import com.reservationapp.entity.Route;
import com.reservationapp.entity.SubRoute;
import com.reservationapp.payload.BusDto;
import com.reservationapp.payload.SearchListofBusesDto;
import com.reservationapp.repository.BusRepository;
import com.reservationapp.repository.RouteRepository;
import com.reservationapp.repository.SubRouteRepository;
import com.reservationapp.service.BusService;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bus")
public class BusController {

    @Autowired
    private BusService busService;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private SubRouteRepository subRouteRepository;

    @Autowired
    private BusRepository busRepository;


    //http://localhost:8080/api/v1/bus/add
    @PostMapping("/add")
    public ResponseEntity<Bus> addBus(@RequestBody BusDto busDto) throws ParseException {

        Bus bus = busService.addBus(busDto);
        return new ResponseEntity<>(bus, HttpStatus.CREATED);
    }
    //http://localhost:8080/api/v1/bus/add?fromLocation=&toLocation=&fromDate
    @GetMapping
    public List<SearchListofBusesDto> getAllBuses(@RequestParam String fromLocation,
                                 @RequestParam String toLocation,
                                 @RequestParam String fromDate){
        List<Route> routes = routeRepository.findByFromLocationAndToLocationAndFromDate(fromLocation, toLocation, fromDate);
        List<SubRoute> subRoutes = subRouteRepository.findByFromLocationAndToLocationAndFromDate(fromLocation, toLocation, fromDate);

        List<SearchListofBusesDto> buses = new ArrayList<>();
        if (routes!=null) {
            for (Route route : routes) {
                Bus bus = busRepository.findById(route.getBusId()).get();
                SearchListofBusesDto searchListofBusesDto = mapToSearchListOfBusesDto(bus, route);
                buses.add(searchListofBusesDto);
            }
            return buses;
        }
        if (subRoutes!=null) {
            for (SubRoute route : subRoutes) {
                Bus bus = busRepository.findById(route.getBusId()).get();
                SearchListofBusesDto searchListofBusesDto = mapToSearchListOfBusesDto(bus, route);
                buses.add(searchListofBusesDto);
            }
            return buses;
        }

        return null;

    }

    SearchListofBusesDto mapToSearchListOfBusesDto(Bus bus, Route route){
        SearchListofBusesDto searchListofBusesDto = new SearchListofBusesDto();
        searchListofBusesDto.setBusId(bus.getId());
        searchListofBusesDto.setBusNumber(bus.getBusNumber());
        searchListofBusesDto.setPrice(bus.getPrice());
        searchListofBusesDto.setBusType(bus.getBusType());
        searchListofBusesDto.setTotalSeats(bus.getTotalSeats());
        searchListofBusesDto.setAvailableSeats(bus.getAvailableSeats());
        searchListofBusesDto.setFromLocation(route.getFromLocation());
        searchListofBusesDto.setToLocation(route.getToLocation());
        searchListofBusesDto.setFromDate(route.getFromDate());
        searchListofBusesDto.setToDate(route.getToDate());
        searchListofBusesDto.setFromTime(route.getFromTime());
        searchListofBusesDto.setToTime(route.getToTime());
        return searchListofBusesDto;
    }
    SearchListofBusesDto mapToSearchListOfBusesDto(Bus bus, SubRoute route){
        SearchListofBusesDto searchListofBusesDto = new SearchListofBusesDto();
        searchListofBusesDto.setBusId(bus.getId());
        searchListofBusesDto.setBusNumber(bus.getBusNumber());
        searchListofBusesDto.setPrice(bus.getPrice());
        searchListofBusesDto.setBusType(bus.getBusType());
        searchListofBusesDto.setTotalSeats(bus.getTotalSeats());
        searchListofBusesDto.setAvailableSeats(bus.getAvailableSeats());
        searchListofBusesDto.setFromLocation(route.getFromLocation());
        searchListofBusesDto.setToLocation(route.getToLocation());
        searchListofBusesDto.setFromDate(route.getFromDate());
        searchListofBusesDto.setToDate(route.getToDate());
        searchListofBusesDto.setFromTime(route.getFromTime());
        searchListofBusesDto.setToTime(route.getToTime());
        return searchListofBusesDto;
    }
    }

