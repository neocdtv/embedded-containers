/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.embedded.containers.grizzly;

import javax.ws.rs.GET;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author wolfkr
 */
@Path("res")
public class ExampleResource {
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response get() {
        return Response.ok("getted").build();
    }
    
    @DELETE 
    @Produces(MediaType.TEXT_PLAIN)
    public Response delete(final String payload) {
        System.out.println("XXX payload: " + payload);
        return Response.ok("deleted").build();
    }
}