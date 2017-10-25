package io.neocdtv.embedded.containers.jetty.boundary;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * WebSocketServerEndpoint
 *
 * @author Krzysztof Wolf (WOLFKR)
 * @since 24.10.2017.
 */

@ApplicationScoped
@ServerEndpoint(value = "/ws")
public class WebSocketServerEndpoint {

	@OnOpen
	public void onOpen(final Session session, final EndpointConfig config) {
		System.out.println("adding session " + session.getId());
		session.setMaxIdleTimeout(0); // TODO: why do i need this
	}

	@OnClose
	public void onClose(final Session session, CloseReason closeReason) {
		System.out.println("removing session " + session.getId());
	}

	@OnError
	public void onError(Session session, Throwable t) {
		System.out.println("error " + t.getMessage());
	}

	public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
		System.out.println("on app startup");
	}
}
