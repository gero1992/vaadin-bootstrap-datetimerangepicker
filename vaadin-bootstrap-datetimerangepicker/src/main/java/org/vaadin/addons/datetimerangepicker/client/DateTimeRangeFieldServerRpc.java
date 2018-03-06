package org.vaadin.addons.datetimerangepicker.client;

import java.util.Date;

import com.vaadin.shared.communication.ServerRpc;

// ServerRpc is used to pass events from client to server
public interface DateTimeRangeFieldServerRpc extends ServerRpc {

    public void valueChanged(Date from, Date to);

}