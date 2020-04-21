package com.tecnositaf.centrobackend.controller;

import java.util.ArrayList;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.gmaps.Gmaps;
import org.zkoss.gmaps.Gmarker;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

import com.tecnositaf.centrobackend.dto.DTOAlert;
import com.tecnositaf.centrobackend.service.ZKService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ZKIndexController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Wire
	Listbox alertListbox;

	@Wire
	private Gmaps gmaps;
	@Wire
	Textbox filter;

	@Wire
	Button addAlert;

	@WireVariable
	ZKService zkService;

	private List<DTOAlert> alertList = new ArrayList<>();
	private double lat = 40.730610;
	private double lng = -73.935242;
	private Gmarker marker1;
	private Gmarker marker2;
	private Gmarker marker3;
	private String standardIconUrl = "http://maps.google.com/mapfiles/ms/icons/red-pushpin.png";
	private String postmanUrl = "http://maps.google.com/mapfiles/ms/icons/yellow-dot.png";

	public List<DTOAlert> getAlertList() {
		return alertList;
	}

	public void setAlertList(List<DTOAlert> alertList) {
		this.alertList = alertList;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Gmaps getGmaps() {
		return gmaps;
	}

	public void setGmaps(Gmaps gmaps) {
		this.gmaps = gmaps;
	}

	public Gmarker getMarker1() {
		return marker1;
	}

	public void setMarker1(Gmarker marker1) {
		this.marker1 = marker1;
	}

	public Gmarker getMarker2() {
		return marker2;
	}

	public void setMarker2(Gmarker marker2) {
		this.marker2 = marker2;
	}

	public Gmarker getMarker3() {
		return marker3;
	}

	public void setMarker3(Gmarker marker3) {
		this.marker3 = marker3;
	}

	public String getPostmanUrl() {
		return postmanUrl;
	}

	public String getStandardIconUrl() {
		return standardIconUrl;
	}

	@Init
	public void init() {
		log.info("ZKIndexController: init");
		this.setAlertList(zkService.findAllDTO());
		this.setGmaps(new Gmaps());
		this.getGmaps().setLat(this.getLat());
		this.getGmaps().setLng(this.getLng());
		this.getGmaps().enableBindingAnnotation();

		this.setMarker1(new Gmarker());
		this.getMarker1().setLat(this.getLat());
		this.getMarker1().setLng(this.getLng());
		this.getMarker1().setOpen(true);
		this.getMarker1().setIconImage(this.standardIconUrl);
		this.getMarker1()
				.setContent("id: 'telecamera'<br>" + "type:'telecamera'<br>" + "description: 'telecamera casello'<br>"
						+ "brand: 'sony'<br>" + "lastUpdate: '2020-03-03T09:33:21'<br>"
						+ "registrationTime: '2010-03-03T09:33:21'<br>" + "isActive: true<br>"
						+ "inManteinance: false<br>" + "weight: 15<br>" + "storageYears: 10");

		this.setMarker2(new Gmarker());
		this.getMarker2().setLat(this.getLat() + 0.5);
		this.getMarker2().setLng(this.getLng() + 0.5);
		this.getMarker2().setOpen(true);
		this.getMarker2().setIconImage(this.standardIconUrl);
		this.getMarker2()
				.setContent("id: 'sensore'<br>" + "type: 'telecamera'<br>"
						+ "description: 'telecamera controllo veicolare'<br>" + "brand: 'samsung'<br>"
						+ "lastUpdate: '2020-03-03T09:33:21'<br>" + "registrationTime: '2015-03-03T09:33:21'<br>"
						+ "isActive: true<br>" + "inManteinance: false,\r\n" + "weight: 15<br>" + "storageYears: 5");

		this.setMarker3(new Gmarker());
		this.getMarker3().setLat(this.getLat() + 1);
		this.getMarker3().setLng(this.getLng() + 1);
		this.getMarker3().setOpen(true);
		this.getMarker3().setIconImage(this.standardIconUrl);
		this.getMarker3()
				.setContent("id: 'websocket'<br>" + "type: 'sensore'<br>" + "description: 'sensore incendi'<br>"
						+ "brand: 'IBM'<br>" + "lastUpdate: '2020-03-03T09:34:21'<br>"
						+ "registrationTime: '2017-03-03T09:33:21'<br>" + "isActive: true<br>"
						+ "inManteinance: false<br>" + "weight: 1<br>" + "storageYears: 3<br>");
		
		
		//NON FUNZIONA ANCORA: WEBSOCKET CLIENT SIDE???
		//this.start(Executions.getCurrent().getDesktop());

//		Desktop desktop = Executions.getCurrent().getDesktop();
//		desktop.enableServerPush(true);
//
//		Execution execution = Executions.getCurrent();
//
//		desktop.enableServerPush(false);
//		((DesktopCtrl) desktop).enableServerPush(new WebSocketServerPush());
//		String wsurl = desktop.getWebApp().getAttribute("websocketUrl").toString();
//		boolean secure = ((HttpServletRequest) execution.getNativeRequest()).isSecure();
//		Clients.evalJavaScript(String.format("zWs.start('%s', %s);", wsurl, secure));
	}

	public void start(@ContextParam(ContextType.DESKTOP) Desktop desktop) {
		log.info("start");
		startBackgroundThread(desktop);
	}

	private void startBackgroundThread(Desktop desktop) {
		log.info("startBackgroundThread");		
		new Thread(() -> {
			try {
				activated(desktop, () -> {
					this.updateAlertList();
				});
			} catch (Exception e) {
				log.error("error: ", e);
				return;
			}
		}).start();
	}

	private void activated(Desktop desktop, Runnable task) {
		log.info("activated");
		try {
			Executions.activate(desktop);
			log.info("task run");
			task.run();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			Executions.deactivate(desktop);
		}
	}

	private void updateAlertList() {
		log.info("Messaggio arrivato");
		this.setAlertList(zkService.findAllDTO());
		DTOAlert alert = this.getAlertList().get(this.getAlertList().size() - 1);
		String content;
		if (alert.getDescription().equals("telecamera")) {
			this.getMarker1().setIconImage(this.postmanUrl);
			content = this.getMarker1().getContent();
			this.getMarker1().setContent(
					content + "<hr>CODE: " + alert.getCode().toString() + "<br>DESCRIPTION: " + alert.getDescription());
		} else if (alert.getDescription().equals("sensore")) {
			this.getMarker2().setIconImage(this.postmanUrl);
			content = this.getMarker2().getContent();
			this.getMarker2().setContent(
					content + "<hr>CODE: " + alert.getCode().toString() + "<br>DESCRIPTION: " + alert.getDescription());

		} else if (alert.getDescription().equals("websocket")) {
			this.getMarker3().setIconImage(this.postmanUrl);
			content = this.getMarker3().getContent();
			this.getMarker3().setContent(
					content + "<hr>CODE: " + alert.getCode().toString() + "<br>DESCRIPTION: " + alert.getDescription());
		}
	}

	@Command
	@NotifyChange("alertList")
	public void filterList(@BindingParam("filter") String filter) {
		log.info("search");
		this.setAlertList(zkService.filterList(filter));
	}

	@Command
	@NotifyChange("alertList")
	public void getAlerts() {
		log.info("ZKComposer: getAlerts");
		this.setAlertList(zkService.findAllDTO());
	}

	// @Listen consente di ascoltare l'evento legato ad un component
	// Non serve anche @Wire per usarlo
	@Command
	public void addAlert() {
		log.info("addAlert");
		Executions.sendRedirect("~./zul/add_alert.zul");
	}

	@Command
	public void updateAlert(@BindingParam("alert") DTOAlert alert) {
		log.info("updateAlert");
		Session session = Sessions.getCurrent();
		session.setAttribute("alert", alert);
		Executions.sendRedirect("~./zul/update_alert.zul");
	}

	@Command
	@NotifyChange("alertList")
	public void deleteAlert(@BindingParam("alert") DTOAlert alert) {
		log.info("deleteAlert");
		zkService.delete(alert.getIdAlert());
		this.setAlertList(zkService.findAllDTO());
	}

	@Command
	public void goToDetail(@BindingParam("alert") DTOAlert alert) {
		log.info("detail");
		Session session = Sessions.getCurrent();
		session.setAttribute("detail", alert);
		Executions.sendRedirect("~./zul/details_alert.zul");
	}
}
//Non riesco a far funzionare il mapping
// USA WEBSOCKET
//	@PostMapping("/zk")
//	public void insert(@RequestBody Alert alert) {
//		log.info("ZKIndexController: insert");
//		zkService.insert(alert);
//		log.info("getAlerts");
//		this.getAlerts();
//		String content;
//		if (alert.getDescription().equals("telecamera")) {
//			this.getMarker1().setIconImage(this.postmanUrl);
//			content = this.getMarker1().getContent();
//			this.getMarker1().setContent(
//					content + "CODE: " + alert.getCode().toString() + "<br>DESCRIPTION: " + alert.getDescription());
//		} else if (alert.getDescription().equals("sensore")) {
//			this.getMarker2().setIconImage(this.postmanUrl);
//			content = this.getMarker2().getContent();
//			this.getMarker2().setContent(
//					content + "CODE: " + alert.getCode().toString() + "<br>DESCRIPTION: " + alert.getDescription());
//
//		} else if (alert.getDescription().equals("websocket")) {
//			this.getMarker3().setIconImage(this.postmanUrl);
//			content = this.getMarker3().getContent();
//			this.getMarker3().setContent(
//					content + "CODE: " + alert.getCode().toString() + "<br>DESCRIPTION: " + alert.getDescription());
//		}
//	}

//usa uno script per creare un websocket

//@NotifyChange("alertList")
//@OnMessage
//public void updateList(Session session, Alert alert) {
//	log.info("Messaggio arrivato");
//}
//	AlertDTO alertDTO = new AlertDTO();
//	alertDTO.setIdAlert(alert.getIdAlert());
//	alertDTO.setIdDeviceFk(alert.getIdDeviceFk());
//	alertDTO.setDescription(alert.getDescription());
//	alertDTO.setCode(alert.getCode());
//	alertDTO.setTimestamp(Date.from(alert.getTimestamp().atZone(ZoneId.systemDefault()).toInstant()));
//	alertDTO.setStorageYears(alert.getStorageYears());
//	this.getAlertList().add(alertDTO);
//	String content;
//	if (alert.getDescription().equals("telecamera")) {
//		this.getMarker1().setIconImage(this.postmanUrl);
//		content = this.getMarker1().getContent();
//		this.getMarker1().setContent(
//				content + "<hr>CODE: " + alert.getCode().toString() + "<br>DESCRIPTION: " + alert.getDescription());
//	} else if (alert.getDescription().equals("sensore")) {
//		this.getMarker2().setIconImage(this.postmanUrl);
//		content = this.getMarker2().getContent();
//		this.getMarker2().setContent(
//				content + "<hr>CODE: " + alert.getCode().toString() + "<br>DESCRIPTION: " + alert.getDescription());
//
//	} else if (alert.getDescription().equals("websocket")) {
//		this.getMarker3().setIconImage(this.postmanUrl);
//		content = this.getMarker3().getContent();
//		this.getMarker3().setContent(
//				content + "<hr>CODE: " + alert.getCode().toString() + "<br>DESCRIPTION: " + alert.getDescription());
//	}
