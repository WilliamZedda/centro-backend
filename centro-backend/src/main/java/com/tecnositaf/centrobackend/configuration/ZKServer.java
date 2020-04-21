package com.tecnositaf.centrobackend.configuration;

import java.io.IOException;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.zk.ui.http.ZKWebSocket;
import org.zkoss.zk.ui.sys.Storage;

@ServerEndpoint(value = "/zk/", configurator = ZKWebSocket.class)
public class ZKServer {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
		log.info("Websocket is connecting");
		ZKWebSocket.initZkDesktop(session, config);
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		Storage storage = ZKWebSocket.getDesktopStorage(session);
        if ("receive".equals(message)) {
            Integer count = storage.getItem("count");
            try {
                session.getBasicRemote().sendText("Received..." + count);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                storage.setItem("count", Integer.parseInt(message));
                session.getBasicRemote().sendText("Sent..." + message);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
		log.info("Receive from client: " + message);
		String replyMessage = "echo " + message;
		log.info("send to client " + replyMessage);
	}

	@OnClose
	public void onClose(Session session) {
		log.info("closing websocket");
	}

	@OnError
	public void onError(Throwable t) {
		log.error("error: ", t);

	}

}