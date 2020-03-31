package com.tecnositaf.centrobackend.composer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

import com.tecnositaf.centrobackend.configuration.PageConfig;
import com.tecnositaf.centrobackend.model.Page;
import com.tecnositaf.centrobackend.service.ZKService;
import com.tecnositaf.centrobackend.utilities.Common;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ZKComposer extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;

	/*
	 * @Wire Component id;
	 */
	@Wire
	Listbox alertListbox;
	
	@Wire
	Textbox search;

	@Wire
	Button addAlert;
	
	@WireVariable
	ZKService zkService;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		log.info("ZKComposer: doAfterCompose");
		super.doAfterCompose(comp);
		alertListbox.setModel(zkService.findAll());
		for(Page page: PageConfig.getPages()) {
			//https://github.com/zkoss/zkessentials/blob/master/src/main/java/org/zkoss/essentials/chapter6/pagebased/SidebarPageConfigPagebasedImpl.java
			//https://github.com/zkoss/zkessentials/blob/master/src/main/java/org/zkoss/essentials/chapter6/pagebased/SidebarPagebasedController.java
			//https://github.com/zkoss/zkessentials/tree/master/src/main/java/org/zkoss/essentials/chapter6/pagebased
			//https://github.com/zkoss/zkessentials/tree/master/src/main/java/org/zkoss/essentials/services
			//https://www.zkoss.org/wiki/ZK_Essentials/Chapter_7:_Navigation_and_Templating
			//http://books.zkoss.org/zkessentials-book/master/navigation_and_template/page_based.html
			
		}
		EventListener<Event> actionListener = 
	}

	
	@Command("filterList")
	public void filterList(@BindingParam("filter") String filter) {
		log.info("ZKComposer: filterList");
//		String filter = this.search.getValue();
		if (!Common.idNullOrBlank(filter)) {
			alertListbox.setModel(zkService.filterList(filter.toLowerCase()));
		}
		else {
			alertListbox.setModel(zkService.findAll());
		}
	}

	public void getAlerts() {
		log.info("ZKComposer: getAlerts");
		alertListbox.setModel(zkService.findAll());
	}
	// @Listen consente di ascoltare l'evento legato ad un component
	// Non serve anche @Wire per usarlo

	@Listen("onClick = #addAlert")
	public void testing() {
		log.info("addAlert");
		String url = Executions.getCurrent().getDesktop().getRequestPath();
		log.info(url);
		Executions.sendRedirect("add_alert");
	}
}
