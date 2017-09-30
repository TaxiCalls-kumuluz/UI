package com.taxicalls.ui;

import com.taxicalls.ui.model.Route;
import com.taxicalls.utils.ServiceRegistry;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;

@Model
public class RoutesBean {

    @Inject
    private ServiceRegistry serviceRegistry;

    public List<Route> getAllRoutes() {
        return ClientBuilder.newClient()
                .target(serviceRegistry.discoverServiceURI("RoutesService")).path("routes")
                .request().get(new GenericType<List<Route>>() {
                });
    }

}
