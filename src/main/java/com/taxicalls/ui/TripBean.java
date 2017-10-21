package com.taxicalls.ui;

import com.taxicalls.ui.model.Trip;
import com.taxicalls.utils.ServiceRegistry;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;

@Model
public class TripBean {

    @Inject
    private ServiceRegistry serviceRegistry;

    public List<Trip> getTrips() {
        return ClientBuilder.newClient()
                .target(serviceRegistry.discoverServiceURI("TripService")).path("trips")
                .request().get(new GenericType<List<Trip>>() {
                });
    }

}
