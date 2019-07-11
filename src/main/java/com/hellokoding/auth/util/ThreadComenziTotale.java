package com.hellokoding.auth.util;

import com.hellokoding.auth.service.ComandaService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

@Component
public class ThreadComenziTotale implements DisposableBean, Runnable {

    private ComandaService comandaService;

    private Thread thread;
    private volatile boolean someCondition;

    public ComandaService getComandaService() {
        return comandaService;
    }

    public void setComandaService(ComandaService comandaService) {
        this.comandaService = comandaService;
    }

    public ThreadComenziTotale(){
        this.thread = new Thread(this);
        this.thread.start();

    }

    public boolean isSomeCondition() {
        return someCondition;
    }

    public void setSomeCondition(boolean someCondition) {
        this.someCondition = someCondition;
    }

    @Override
    public void run(){
        while(someCondition){
            Global.listaComenzi=comandaService.findAll();

            if(Global.listaComenzi!=null )
                someCondition =false;
        }
    }

    @Override
    public void destroy(){
        someCondition = false;
    }

}