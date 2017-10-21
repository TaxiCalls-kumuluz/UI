package com.taxicalls.ui;

import com.taxicalls.ui.model.Driver;
import com.taxicalls.ui.model.Passenger;
import com.taxicalls.ui.model.Trip;
import com.taxicalls.utils.ServiceRegistry;
import java.util.ArrayList;
import java.util.Collection;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

@Model
public class PassengerBean {

    @Inject
    private ServiceRegistry serviceRegistry;

    private final Trip trip;
    private Collection<Driver> availableDrivers;

    public PassengerBean() {
        this.trip = new Trip();
        this.availableDrivers = new ArrayList<>();
    }

    public Trip getTrip() {
        return trip;
    }

    public Collection<Driver> getAvailableDrivers() {
        return availableDrivers;
    }

    public List<Passenger> getPassengers() {
        return ClientBuilder.newClient()
                .target(serviceRegistry.discoverServiceURI("PassengerService"))
                .path("passengers")
                .request()
                .get(new GenericType<List<Passenger>>() {
                });
    }

    public Collection<Driver> fetchAvailableDrivers() {
        Collection<Driver> availableDrivers = ClientBuilder.newClient()
                .target(serviceRegistry.discoverServiceURI("PassengerService"))
                .path("drivers").path("available")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(this.trip, MediaType.APPLICATION_JSON), new GenericType<Collection<Driver>>() {
                });
        this.availableDrivers = availableDrivers;
        return availableDrivers;
    }

}
