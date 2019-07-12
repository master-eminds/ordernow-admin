package com.hellokoding.auth.util;

import com.hellokoding.auth.model.Categorie;
import com.hellokoding.auth.model.Meniu;
import com.hellokoding.auth.service.CategorieService;
import com.hellokoding.auth.service.MeniuService;
import com.hellokoding.auth.service.ProdusService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class ThreadMeniuriSiCategorii implements DisposableBean, Runnable {

    private MeniuService meniuService;
    private CategorieService categorieService;
    private ProdusService produsService;
    private Thread thread;
    private volatile boolean someCondition;

    public MeniuService getMeniuService() {
        return meniuService;
    }

    public void setMeniuService(MeniuService meniuService) {
        this.meniuService = meniuService;
    }

    public CategorieService getCategorieService() {
        return categorieService;
    }

    public void setCategorieService(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    public ProdusService getProdusService() {
        return produsService;
    }

    public void setProdusService(ProdusService produsService) {
        this.produsService = produsService;
    }

    public ThreadMeniuriSiCategorii(){
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
            Global.listaMeniuri=meniuService.findAll();
            Global.mapCategoriiByMeniu= new HashMap<>();
            Global.mapProduseByCategorie= new HashMap<>();
            for(Meniu m: Global.listaMeniuri){
                Global.mapCategoriiByMeniu.put(m.getId(), categorieService.findAllByMeniuId(m.getId()));
            }
            List<Categorie> listaCateg=categorieService.findAll();
            for(Categorie c: listaCateg){
                Global.mapProduseByCategorie.put(c.getId(), produsService.findAllByCategorie(c.getId()));
            }
            if(Global.listaMese!=null && Global.mapCategoriiByMeniu.size()!=0 && Global.mapProduseByCategorie.size()!=0)
                someCondition =false;
        }
    }

    @Override
    public void destroy(){
        someCondition = false;
    }

}