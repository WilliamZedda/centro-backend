package com.tecnositaf.centrobackend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tecnositaf.centrobackend.dto.DTOAlert;
import com.tecnositaf.centrobackend.enumeration.Errors;
import com.tecnositaf.centrobackend.response.Response;
import com.tecnositaf.centrobackend.service.AlertService;
import com.tecnositaf.centrobackend.utilities.CheckUtilities;

@RestController
@RequestMapping("/websocket/alerts/response")
public class WebSocketController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SimpMessagingTemplate template;

	// Vecchio stile
//	@Autowired
//	WebSocketController(SimpMessagingTemplate template) {
//		this.template = template;
//	}

	@Autowired
	private AlertService alertService;

	@PostMapping()
	public ResponseEntity<Response> insertWithResponse(@RequestBody DTOAlert alert) {
		log.info("addAlertByWebSocket");
		if (!CheckUtilities.checkDTOAlertInsert(alert))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new Response(-1, Errors.FAILURE, ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));

		alertService.insert(alert.toAlert());

		// List<Alert> alertList = alertService.findAll();
		this.template.convertAndSend("/topic/alert", alert);

		return ResponseEntity.status(HttpStatus.OK).body(null);
		/*
		 * .body(new GetAlertsResponse(0, Errors.SUCCESS,
				ServletUriComponentsBuilder.fromCurrentRequest().toUriString(), alertList, alertList.size()));
		 * */
	}

	@PostMapping("/no")
	public void insertNoResponse(@RequestBody DTOAlert alert) {
		log.info("addAlertByWebSocketNoResponse");
		if (CheckUtilities.checkDTOAlert(alert)) {
			alertService.insert(alert.toAlert());
			this.template.convertAndSend("/topic/alert", alert);
		 }
	}

	// Reception from FE (FE->BE)
//	@MessageMapping("/alert")
//	public void nome() {
//		
//	}
//	// riceve su questo canale
//	@MessageMapping("/ciao")
//	public void greeting(HelloMessage message)throws Exception {
//		Thread.sleep(1000);
//		//converte e spedisce su questo canale
//		this.template.convertAndSend("/topic/alert", message);
//		  log.info(message.getName());
//	}

	// Sending to FE (BE->FE)
//	public void sendToFe() {
//		this.template.convertAndSend("/topic/alert");
//	}
//	
}
