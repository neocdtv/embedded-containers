/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.embedded.containers.jetty;

import io.neocdtv.embedded.containers.jetty.boundary.HelloWorldResource;
import io.neocdtv.embedded.containers.jetty.boundary.WebSocketServerEndpoint;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.websocket.jsr356.server.ServerContainer;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.jboss.weld.environment.servlet.BeanManagerResourceBindingListener;
import org.jboss.weld.environment.servlet.Listener;

import javax.servlet.ServletException;
import javax.websocket.DeploymentException;

/**
 * WebSocketServerEndpoint
 *
 * @author Krzysztof Wolf (WOLFKR)
 * @since 24.10.2017.
 */

public class Main {
	// TODO: can i use META-INF with web.xml and/or jetty-web.xml?
	public static void main(String[] args) throws Exception {

		Server server = new Server(8080);
		WebAppContext context = configureWebContext(server);

		configureJersey(context);
		configureCdi(context);
		configureWebSocket(context);

		server.start();
		server.join();
	}

	private static WebAppContext configureWebContext(Server server) {
		WebAppContext context = new WebAppContext();
		context.setContextPath("/");
		context.setResourceBase("src/main/resources/static");
		server.setHandler(context);
		return context;
	}

	private static void configureJersey(WebAppContext context) {
		ResourceConfig config = new ResourceConfig();
		// Configure package scanning
		//config.packages("io.neocdtv.embedded.containers.jetty.boundary");

		// configure rest resource manually
		config.register(HelloWorldResource.class);
		ServletHolder servlet = new ServletHolder(new ServletContainer(config));
		context.addServlet(servlet, "/rs/*");
	}

	private static void configureWebSocket(WebAppContext context) throws ServletException, DeploymentException {
		ServerContainer webSockerContainer = WebSocketServerContainerInitializer.configureContext(context);
		webSockerContainer.addEndpoint(WebSocketServerEndpoint.class);
	}

	private static void configureCdi(WebAppContext context) {
		context.addEventListener(new Listener());
		// 18.3.2.2. Binding BeanManager to JNDI, is JDNI by default enabled on jetty?
		context.addEventListener(new BeanManagerResourceBindingListener());
	}
}
