package com.taxicalls.ui;

import com.taxicalls.ui.model.Passenger;
import com.taxicalls.ui.model.Route;
import com.taxicalls.utils.ServiceRegistry;
import java.util.ArrayList;

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

    private final Route route;
    private List<Route> availableRoutes;

    public PassengerBean() {
        this.route = new Route();
        this.availableRoutes = new ArrayList<>();
    }

    public Route getRoute() {
        return route;
    }

    public List<Route> getAvailableRoutes() {
        return availableRoutes;
    }

    public List<Passenger> getPassengers() {
        return ClientBuilder.newClient()
                .target(serviceRegistry.discoverServiceURI("PassengerService"))
                .path("passenger")
                .request()
                .get(new GenericType<List<Passenger>>() {
                });
    }

    public List<Route> trip() {
        List<Route> routes = ClientBuilder.newClient()
                .target(serviceRegistry.discoverServiceURI("PassengerService"))
                .path("passenger").path("trip")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(this.route, MediaType.APPLICATION_JSON), new GenericType<List<Route>>() {
                });
        this.availableRoutes = routes;
        return routes;
    }

}
