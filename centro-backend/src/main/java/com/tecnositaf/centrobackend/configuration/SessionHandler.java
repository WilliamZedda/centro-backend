package com.tecnositaf.centrobackend.configuration;

import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import com.tecnositaf.centrobackend.dto.DTOAlert;
import com.tecnositaf.centrobackend.model.Alert;

public class SessionHandler extends StompSessionHandlerAdapter {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
		log.info("New session established : " + session.getSessionId());
		session.subscribe("/topic/alert", this);
		log.info("Subscribed to /topic/messages");
		session.send("/app/hello", "{\"name\":\"Client\"}".getBytes());
		log.info("Message sent to websocket server");
	}

	@Override
	public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload,
			Throwable exception) {
		log.error("Errore: ", exception);
	}

	@Override
	public Type getPayloadType(StompHeaders headers) {
		return DTOAlert.class;
	}

	@Override
	public void handleFrame(StompHeaders headers, Object payload) {
		DTOAlert alertDTO = (DTOAlert) payload;
		log.info("Received : " + alertDTO.getDescription());
	}

	private DTOAlert getSampleMessage() {
		DTOAlert msg = new DTOAlert();
		msg.setDescription("ciao");
		return msg;
	}
}