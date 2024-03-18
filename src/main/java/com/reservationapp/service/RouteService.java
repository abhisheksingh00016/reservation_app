package com.reservationapp.service;

import com.reservationapp.Exception.ResourceNotFound;
import com.reservationapp.entity.Bus;
import com.reservationapp.entity.Route;
import com.reservationapp.repository.BusRepository;
import com.reservationapp.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class RouteService {
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private BusRepository busRepository;
    public Route createRoute(long busId, Route route){
        Bus bus = busRepository.findById(busId).orElseThrow(
                () -> new ResourceNotFound("bus not found by id" + busId)
        );
        Route route1 = routeRepository.findByBusId(route.getId());
        if(route1==null){
            routeRepository.save(route);
            return route;
        }
        if(route1!=null){
            throw new ResourceNotFound("route was already added");
        }
        return null;
    }
}
