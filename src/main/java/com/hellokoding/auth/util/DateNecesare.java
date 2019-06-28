package com.hellokoding.auth.util;

import com.hellokoding.auth.model.Comanda;
import com.hellokoding.auth.model.ItemComanda;
import com.hellokoding.auth.model.Ospatar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateNecesare {

    private Integer nrOspatariOnline;
    private Integer nrComenziVandute;
    private Integer counterComenziThisWeek;
    private Double totalIncasariSaptamana;
    private Double totalIncasari;
    private Map<String,Integer> nrComenziThisWeek = new HashMap<>();
    private Map<String,Integer> nrComenziOnMonth;


    public DateNecesare() {
    }

    public DateNecesare dateNecesare(List<Ospatar> listaOspatari, List<Comanda> comandas) throws ParseException {
        DateNecesare dateNecesare = new DateNecesare();
        //variabile de care am nevoie
        int counterOspatariOnline = 0;
        int nrComenzi = 0;
        nrComenziOnMonth=new HashMap<>();

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
        for(int i=0;i<7;i++){
            String data= sdf.format(cal.getTime());
            saptamana.put(i,data);
            cal.add(Calendar.DAY_OF_YEAR, -1);
        }

        //nrComenziVandute
        counterComenziThisWeek = 0;
        totalIncasariSaptamana =0.0;
        totalIncasari=0.0;
        Date limita=cal.getTime();
        cal.add(Calendar.DAY_OF_YEAR,+7);

        for (int i=0;i<7;i++){
            int couterDay = 0;
            String zi= saptamana.get(i).split(" ")[0];
            for (int j=comandas.size()-1;j>=0;j--) {
                Comanda c= comandas.get(j);
                if(c.getData().compareTo(sdf.format(limita)) < 0){
                    j=-1;
                }
                else {
                    if (zi.equals(c.getData().split(" ")[0])) {
                        counterComenziThisWeek++;
                        if (nrComenziThisWeek.containsKey(zi)) {
                            nrComenziThisWeek.put(zi, nrComenziThisWeek.get(zi) + 1);
                        } else {
                            nrComenziThisWeek.put(zi, 1);
                        }

                    }
                }
            }
            if(!nrComenziThisWeek.containsKey(zi)){
                nrComenziThisWeek.put(zi,0);
            }
        }
       nrComenzi=comandas.size();
        calculeazaValoareTotalaIncasata(comandas);

        //  comandas.sort(Comanda::compareTo);
        // get starting date
        SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MMM-yyyy");

        for(int i=1;i<=4;i++){
            String dataLimita= sdf.format(cal.getTime());
            int lunaLimita= Integer.parseInt(dataLimita.split(" ")[0].split("-")[1]);
            String lunaNr= dataLimita.split(" ")[0].split("-")[1];
            for (int j=comandas.size()-1;j>=0;j--) {
                Comanda c = comandas.get(j);

                if (lunaLimita>Integer.parseInt(c.getData().split(" ")[0].split("-")[1])) {
                    j = -1;
                } else {
                    if (lunaNr.equalsIgnoreCase(c.getData().split(" ")[0].split("-")[1])) {
                        String luna = dateFormat.format(cal.getTime()).split("-")[1];
                        if (nrComenziOnMonth.containsKey(luna)) {
                            nrComenziOnMonth.replace(luna, nrComenziOnMonth.get(luna) + 1);
                        } else {
                            nrComenziOnMonth.put(luna, 1);
                        }
                    }
                    if (!nrComenziOnMonth.containsKey(dateFormat.format(cal.getTime()).split("-")[1])) {
                        nrComenziOnMonth.put(dateFormat.format(cal.getTime()).split("-")[1], 0);
                    }
                }
            }
            cal.add(Calendar.MONTH, -1);

        }


        dateNecesare.setNrComenziOnMonth(nrComenziOnMonth);
        dateNecesare.setCounterComenziThisWeek(counterComenziThisWeek);
        dateNecesare.setNrComenziThisWeek(nrComenziThisWeek);
        dateNecesare.setNrComenziVandute(nrComenzi);
        return dateNecesare;
    }

    public void calculeazaValoareIncasata(Comanda comanda){
        for(ItemComanda itemComanda :  comanda.getListaItemComanda() ){
            String pretProdus = itemComanda.getProdus().getPret().toString();
            Double pretComanda = itemComanda.getCantitate() * Double.valueOf(pretProdus);
            totalIncasariSaptamana +=pretComanda;
        }
    }
    public void calculeazaValoareTotalaIncasata(List<Comanda> comenzi){
        for(Comanda comanda: comenzi) {
            for (ItemComanda itemComanda : comanda.getListaItemComanda()) {
                String pretProdus = itemComanda.getProdus().getPret().toString();
                Double pretComanda = itemComanda.getCantitate() * Double.valueOf(pretProdus);
                totalIncasari += pretComanda;
            }
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

    public Double getTotalIncasariSaptamana() {
        return totalIncasariSaptamana;
    }

    public void setTotalIncasariSaptamana(Double totalIncasariSaptamana) {
        this.totalIncasariSaptamana = totalIncasariSaptamana;
    }

    public Map< String,Integer> getNrComenziOnMonth() {
        return nrComenziOnMonth;
    }

    public void setNrComenziOnMonth(Map<String,Integer> nrComenziOnMonth) {
        this.nrComenziOnMonth = nrComenziOnMonth;
    }

    public Double getTotalIncasari() {
        return totalIncasari;
    }

    public void setTotalIncasari(Double totalIncasari) {
        this.totalIncasari = totalIncasari;
    }
}