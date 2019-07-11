package com.hellokoding.auth.util;

import com.hellokoding.auth.service.MasaService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

@Component
public class ThreadMese implements DisposableBean, Runnable {

    private MasaService masaService;

    private Thread thread;
    private volatile boolean someCondition;


    public MasaService getMasaService() {
        return masaService;
    }

    public void setMasaService(MasaService masaService) {
        this.masaService = masaService;
    }

    public ThreadMese(){
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
            Global.listaMese=masaService.findAllNesterse();

            if(Global.listaMese!=null )
                someCondition =false;
        }
    }

    @Override
    public void destroy(){
        someCondition = false;
    }

}