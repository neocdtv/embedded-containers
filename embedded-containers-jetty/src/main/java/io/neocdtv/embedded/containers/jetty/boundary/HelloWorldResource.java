package io.neocdtv.embedded.containers.jetty.boundary;

import org.jboss.weld.manager.BeanManagerImpl;

import javax.enterprise.inject.spi.BeanManager;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.stream.Collectors;

/**
 * HelloWorldResource
 *
 * @author Krzysztof Wolf (WOLFKR)
 * @since 24.10.2017.
 */
@Path("hello-world")
public class HelloWorldResource {

	@Inject
	BeanManagerImpl beanManager;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String helloWorld() {
		return "Jetty, Jersey and CDI " + getManagedClasses();
	}

	private String getManagedClasses() {
		return beanManager.getBeans().stream().map(bean -> bean.getBeanClass().getName()).collect(Collectors.joining(",\n"));
	}
}
