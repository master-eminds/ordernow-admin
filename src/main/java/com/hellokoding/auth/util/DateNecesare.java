package com.hellokoding.auth.util;

import com.hellokoding.auth.model.Comanda;
import com.hellokoding.auth.model.ItemComanda;
import com.hellokoding.auth.model.ListaMese;
import com.hellokoding.auth.model.Ospatar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateNecesare {

    private Integer nrOspatariOnline;
    private Integer nrComenziVandute;
    private Integer counterComenziThisWeek;
    private Double totalIncasari;
    private Map<String,Integer> nrComenziThisWeek = new HashMap<>();
    private Map<Integer,String> nrComenziOnMonth;


    public DateNecesare() {
    }

    public DateNecesare dateNecesare(List<Ospatar> listaOspatari, List<ListaMese> masa,List<Comanda> comandas) throws ParseException {
        DateNecesare dateNecesare = new DateNecesare();
        //variabile de care am nevoie
        int counterOspatariOnline = 0;
        int nrComenzi = 0;
        List<Comanda> comandasListThisWeek = new ArrayList<>();
        int counterComenziOnMonth = 0;

        //ospatariOnline
        for(Ospatar ospatar : listaOspatari){
            if(ospatar.getStatus().toLowerCase().equals("online"))
                counterOspatariOnline++;
        }
        dateNecesare.setNrOspatariOnline(counterOspatariOnline);

        //last 7 days
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Calendar cal = Calendar.getInstance();
        // get starting date
        HashMap<Integer,String> saptamana = new HashMap<>();
        for(int i=0;i<6;i++){
            cal.add(Calendar.DAY_OF_YEAR, -6+i);
            String data= sdf.format(cal.getTime());
            saptamana.put(i,data);
            cal.add(Calendar.DAY_OF_YEAR, 6-i);
        }

        //nrComenziVandute
        counterComenziThisWeek = 0;
        totalIncasari=0.0;
        for (String valoare : saptamana.values()){
            int couterDay = 0;
            for (Comanda c : comandas) {
                if (valoare.split(" ")[0].equals(c.getData().split(" ")[0])) {
                    counterComenziThisWeek++;
                    couterDay++;
                    nrComenziThisWeek.put(valoare,couterDay);
                    calculeazaValoareIncasata(c);
                }
            }
        }
        for(ListaMese l : masa){
            nrComenzi += l.getNumarComenzi();
        }

        dateNecesare.setCounterComenziThisWeek(counterComenziThisWeek);
        dateNecesare.setNrComenziThisWeek(nrComenziThisWeek);
        dateNecesare.setNrComenziVandute(nrComenzi);
        return dateNecesare;
    }

    public void calculeazaValoareIncasata(Comanda comanda){
        for(ItemComanda itemComanda :  comanda.getListaItemComanda() ){
            String pretProdus = itemComanda.getProdus().getPret().toString();
            Double pretComanda = itemComanda.getCantitate() * Double.valueOf(pretProdus);
            totalIncasari+=pretComanda;
        }
    }
    public Integer getNrOspatariOnline() {
        return nrOspatariOnline;
    }

    public void setNrOspatariOnline(Integer nrOspatariOnline) {
        this.nrOspatariOnline = nrOspatariOnline;
    }

    public Integer getNrComenziVandute() {
        return nrComenziVandute;
    }

    public void setNrComenziVandute(Integer nrComenziVandute) {
        this.nrComenziVandute = nrComenziVandute;
    }

    public Integer getCounterComenziThisWeek() {
        return counterComenziThisWeek;
    }

    public void setCounterComenziThisWeek(Integer counterComenziThisWeek) {
        this.counterComenziThisWeek = counterComenziThisWeek;
    }

    public Map<String, Integer> getNrComenziThisWeek() {
        return nrComenziThisWeek;
    }

    public void setNrComenziThisWeek(Map<String, Integer> nrComenziThisWeek) {
        this.nrComenziThisWeek = nrComenziThisWeek;
    }

    public Double getTotalIncasari() {
        return totalIncasari;
    }

    public void setTotalIncasari(Double totalIncasari) {
        this.totalIncasari = totalIncasari;
    }

    public Map<Integer, String> getNrComenziOnMonth() {
        return nrComenziOnMonth;
    }

    public void setNrComenziOnMonth(Map<Integer, String> nrComenziOnMonth) {
        this.nrComenziOnMonth = nrComenziOnMonth;
    }
}