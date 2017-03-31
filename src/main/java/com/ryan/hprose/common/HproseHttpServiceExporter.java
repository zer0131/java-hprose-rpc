package com.ryan.hprose.common;


import hprose.common.FilterHandler;
import hprose.common.HproseFilter;
import hprose.common.InvokeHandler;
import hprose.io.HproseMode;
import hprose.server.HproseHttpService;
import hprose.server.HproseServiceEvent;
import hprose.server.HttpContext;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.remoting.support.RemoteExporter;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HproseHttpServiceExporter extends RemoteExporter implements InitializingBean, HttpRequestHandler {
    private HproseHttpService httpService;
    private boolean crossDomain = true;
    private boolean get = true;
    private boolean p3p = true;
    private boolean debug = true;
    private HproseServiceEvent event = null;
    private HproseMode mode = HproseMode.MemberMode;
    private HproseFilter filter = null;
    private InvokeHandler invokeHandler = null;
    private FilterHandler beforeFilterHandler = null;
    private FilterHandler afterFilterHandler = null;

    public void afterPropertiesSet() throws Exception {
        checkService();
        checkServiceInterface();
        Object service = getService();
        Class cls = getServiceInterface();
        httpService = new HproseHttpService();
        httpService.add(service, cls);
        httpService.setCrossDomainEnabled(crossDomain);
        httpService.setGetEnabled(get);
        httpService.setP3pEnabled(p3p);
        httpService.setDebugEnabled(debug);
        httpService.setEvent(event);
        httpService.setMode(mode);
        httpService.setFilter(filter);
        httpService.use(invokeHandler);
        httpService.beforeFilter.use(beforeFilterHandler);
        httpService.afterFilter.use(afterFilterHandler);
    }

    public void setCrossDomainEnabled(boolean value) {
        crossDomain = value;
    }

    public void setGetEnabled(boolean value) {
        get = value;
    }

    public void setP3pEnabled(boolean value) {
        p3p = value;
    }

    public void setDebugEnabled(boolean value) {
        debug = value;
    }

    public void setEvent(HproseServiceEvent value) {
        event = value;
    }

    public void setMode(HproseMode value) {
        mode = value;
    }

    public void setFilter(HproseFilter value) {
        filter = value;
    }

    public void setInvokeHandler(InvokeHandler value) {
        invokeHandler = value;
    }

    public void setBeforeFilterHandler(FilterHandler value) {
        beforeFilterHandler = value;
    }

    public void setAfterFilterHandler(FilterHandler value) {
        afterFilterHandler = value;
    }

    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        httpService.handle(new HttpContext(httpService, request, response, null, null));
    }
}
