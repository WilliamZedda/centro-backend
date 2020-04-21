package com.tecnositaf.centrobackend.configuration;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.annotation.ToServerCommand;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.sys.Storage;

@ToServerCommand("update")
public class ZKClient {
 
    private Integer count;
 
    @Init
    public void init(@ContextParam(ContextType.DESKTOP) Desktop desktop) {
        count = 100;
        syncToStorage(desktop);
    }
 
    @Command
    @NotifyChange("count")
    public void cmd(@ContextParam(ContextType.DESKTOP) Desktop desktop) {
        count++;
        syncToStorage(desktop);
    }
 
    @Command("update")
    @NotifyChange("count")
    public void doUpdate(@ContextParam(ContextType.DESKTOP) Desktop desktop) {
        count = desktop.<Integer>getStorage().getItem("count");
    }
 
    private void syncToStorage(Desktop desktop) {
        Storage desktopStorage = desktop.getStorage();
        desktopStorage.setItem("count", count);
    }
    public Integer getCount() {
        return count;
    }
}