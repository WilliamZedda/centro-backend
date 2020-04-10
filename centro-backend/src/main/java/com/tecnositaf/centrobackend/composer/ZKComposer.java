package com.tecnositaf.centrobackend.composer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.zkoss.gmaps.Gmaps;
import org.zkoss.gmaps.Gmarker;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;

import com.tecnositaf.centrobackend.model.Alert;
import com.tecnositaf.centrobackend.service.ZKService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
@RestController
public class ZKComposer extends SelectorComposer<Component>{

	private static final long serialVersionUID = 1L;

	@Wire
	Listbox alertListbox;

	@Wire
	Textbox search;

	@WireVariable
	private ZKService zkService;

	@Wire
	Gmaps gmaps;

	double lat = 40.730610;
	double lng = -73.935242;
	Gmarker marker1 = new Gmarker();
	Gmarker marker2 = new Gmarker();
	Gmarker marker3 = new Gmarker();

	String standardIconUrl = "http://maps.google.com/mapfiles/ms/icons/red-pushpin.png";
	String postmanUrl = "http://maps.google.com/mapfiles/ms/icons/yellow-dot.png";

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private ListModelList<Alert> alertListModel;

	public void setAlertListModel(ListModelList<Alert> alertListModel) {
		this.alertListModel = alertListModel;
	}

	public ListModelList<Alert> getAlertListModel() {
		return this.alertListModel;
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		log.info("ZKComposer: doAfterCompose");
		super.doAfterCompose(comp);
		alertListbox.setModel(zkService.findAllModel());
		gmaps.setLat(lat);
		gmaps.setLng(lng);
		this.marker1.setLat(lat);
		this.marker1.setLng(lng);
		this.marker1.setOpen(true);
		this.marker1.setIconImage(this.standardIconUrl);
		this.marker1.setContent("id: 'telecamera'<br>" + "type:'telecamera'<br>"
				+ "description: 'telecamera casello'<br>" + "brand: 'sony'<br>"
				+ "lastUpdate: '2020-03-03T09:33:21'<br>" + "registrationTime: '2010-03-03T09:33:21'<br>"
				+ "isActive: true<br>" + "inManteinance: false<br>" + "weight: 15<br>" + "storageYears: 10");
		this.marker1.setParent(gmaps);

		this.marker2.setLat(lat + 0.5);
		this.marker2.setLng(lng + 1);
		this.marker2.setOpen(true);
		this.marker2.setIconImage(this.standardIconUrl);
		this.marker2.setContent("id: 'sensore'<br>" + "type: 'telecamera'<br>"
				+ "description: 'telecamera controllo veicolare'<br>" + "brand: 'samsung'<br>"
				+ "lastUpdate: '2020-03-03T09:33:21'<br>" + "registrationTime: '2015-03-03T09:33:21'<br>"
				+ "isActive: true<br>" + "inManteinance: false,\r\n" + "weight: 15<br>" + "storageYears: 5");
		this.marker2.setParent(gmaps);

		this.marker3.setLat(lat + 1);
		this.marker3.setLng(lng + 2);
		this.marker3.setOpen(true);
		this.marker3.setIconImage(this.standardIconUrl);
		this.marker3.setContent("id: 'websocket'<br>" + "type: 'sensore'<br>" + "description: 'sensore incendi'<br>"
				+ "brand: 'IBM'<br>" + "lastUpdate: '2020-03-03T09:34:21'<br>"
				+ "registrationTime: '2017-03-03T09:33:21'<br>" + "isActive: true<br>" + "inManteinance: false<br>"
				+ "weight: 1<br>" + "storageYears: 3<br>");
		this.marker3.setParent(gmaps);

	}

	@Listen("onClick=#searchBtn")
	public void search() {
		log.info("search");
		alertListbox.setModel(zkService.filterListModel(this.search.getValue().toLowerCase()));
	}

	@Listen("onClick=#refresh")
	public void getAlerts() {
		log.info("ZKComposer: getAlerts");
		alertListbox.setModel(zkService.findAllModel());
	}

	// @Listen consente di ascoltare l'evento legato ad un component
	// Non serve anche @Wire per usarlo
	@Listen("onClick = #addAlert")
	public void addAlert() {
		log.info("addAlert");
		Executions.sendRedirect("~./zul/add_alert.zul");
	}

	@Listen("onUpdateClick=#alertListbox")
	public void updateAlert(ForwardEvent event) {
		log.info("updateAlert");
		Button btn = (Button) event.getOrigin().getTarget();
		Listitem litem = (Listitem) btn.getParent().getParent().getParent();
		Alert alert = (Alert) litem.getValue();
		Session session = Sessions.getCurrent();
		session.setAttribute("alert", alert);
		Executions.sendRedirect("~./zul/update_alert.zul");
	}

	@Listen("onDeleteClick=#alertListbox")
	public void deleteAlert(ForwardEvent event) {
		log.info("deleteAlert");
		Button btn = (Button) event.getOrigin().getTarget();
		Listitem litem = (Listitem) btn.getParent().getParent().getParent();
		Alert alert = (Alert) litem.getValue();
		zkService.delete(alert.getIdAlert());
		alertListbox.setModel(zkService.findAllModel());
	}
	
	@Listen("onDetailClick=#alertListbox")
	public void goToDetail(ForwardEvent event) {
		log.info("detail");
		Div div=(Div) event.getOrigin().getTarget();
		Listitem litem = (Listitem) div.getParent().getParent().getParent();
		Alert alert=(Alert) litem.getValue();
		Session session=Sessions.getCurrent();
		session.setAttribute("detail", alert);
		Executions.sendRedirect("~./zul/detail.zul");
	}
	
	
//	@Autowired
//	ZKService zkServiceRest;
	//DENTRO POST NON PUOI USARE SERVICE CON WIREVARIABLE O CHIAMARE FUNZIONI CHE LO FANNO PER TE
	//Non si può usare neanche setModel
//	@PostMapping("/zk")
//	public void insert(@RequestBody Alert alert, Component comp) throws Exception {
//		log.info("ZKComposer: insert");
//		zkServiceRest.insert(alert);
//		//this.marker1.setIconImage(this.postmanUrl);
//		//this.doAfterCompose(this.alertListbox);
//		//alertListbox.setModel((ListModel<Alert>) zkServiceRest.findAll());
//		
//	}
}
		// String content;
//		log.info("alert: " + alert.getDescription());
//		log.info("icon: " + this.marker1.getIconImage() + " " + this.standardIconUrl);
//		// this.marker1.setIconImage(this.postmanUrl);
//		this.marker1.setIconImage(null);
//		log.info("icon: " + this.marker1.getIconImage() + " " + this.postmanUrl);
//		log.info("errore dopo icon");
////		if(alert.getIdDeviceFk().equals("telecamera")) {
//			this.marker1.setIconImage(this.postmanUrl);
//			content=this.marker1.getContent();
//			this.marker1.setContent(content + "<br>"+alert.getDescription()+"<br>" +Integer.toString(alert.getCode()));
//		}
//		else if(alert.getIdDeviceFk().equals("sensore")) {
//			this.marker2.setIconImage(this.postmanUrl);
//			content=this.marker2.getContent();
//			this.marker2.setContent(content + "<br>"+alert.getDescription()+"<br>" +Integer.toString(alert.getCode()));
//
//		}
//		else if(alert.getIdDeviceFk().equals("websocket")) {
//			this.marker3.setIconImage(this.postmanUrl);
//			content=this.marker3.getContent();
//			this.marker3.setContent(content + "<br>"+alert.getDescription()+"<br>" +Integer.toString(alert.getCode()));
//
//		}

//	@Listen("onClick = #toggleInfo") 
//    public void onToggleInfo() {
//        if (info.isOpen()) {
//            marker.setOpen(true);
//            info.setOpen(false);
//        } else {
//            marker.setOpen(false);
//            info.setOpen(true);
//        }
//    }   
