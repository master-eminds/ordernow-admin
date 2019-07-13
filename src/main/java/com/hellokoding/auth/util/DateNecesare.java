package com.hellokoding.auth.util;

import com.hellokoding.auth.model.Comanda;
import com.hellokoding.auth.model.Ospatar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateNecesare {

    private Integer nrOspatariOnline;
    private Integer nrComenziVandute;
    private static Integer counterComenziThisWeek;
    private static Double totalIncasariSaptamana;
    private static Double totalIncasari;
    private static Map<String,Integer> nrComenziThisWeek = new HashMap<>();
    private static Map<String,Integer> nrComenziOnMonth;


    public DateNecesare() {
    }
    public static  List<Comanda> listaComenziUltimeleLuni(List<Comanda> comenzi, int numarLuni){
        List<Comanda> listaComenzi=new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.MONTH,-1);
        for(int i=0;i<numarLuni;i++){
            String dataLimita= sdf.format(cal.getTime());
            int lunaLimita= Integer.parseInt(dataLimita.split(" ")[0].split("-")[1]);
            String lunaNr= dataLimita.split(" ")[0].split("-")[1];
            for (int j=comenzi.size()-1;j>=0;j--) {
                Comanda c = comenzi.get(j);
                if (lunaLimita>Integer.parseInt(c.getData().split(" ")[0].split("-")[1])) {
                    j = -1;
                } else {
                    if (lunaNr.equalsIgnoreCase(c.getData().split(" ")[0].split("-")[1])) {
                       listaComenzi.add(c);
                    }
                }
            }
            cal.add(Calendar.MONTH, -1);

        }
        return listaComenzi;
    }

    public static Map<String ,Integer> calculareNrComenziLunar(List<Comanda> comenzi, int numarLuni){
        nrComenziOnMonth=new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MMM-yyyy");

        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.MONTH,-1);

        for(int i=0;i<numarLuni;i++){
           boolean gasit=false;
           String dataLimita= sdf.format(cal.getTime());
           String lunaNr= dataLimita.split(" ")[0].split("-")[1];
           int lunaLimita= Integer.parseInt(lunaNr);
            for (int j=comenzi.size()-1;j>=0;j--) {
                Comanda c = comenzi.get(j);

                if (lunaLimita>Integer.parseInt(c.getData().split(" ")[0].split("-")[1])) {
                    j = -1;
                } else {
                    if (lunaNr.equalsIgnoreCase(c.getData().split(" ")[0].split("-")[1])) {
                        gasit=true;
                        String luna = dateFormat.format(cal.getTime()).split("-")[1];
                        if (nrComenziOnMonth.containsKey(luna)) {
                            nrComenziOnMonth.replace(luna, nrComenziOnMonth.get(luna) + 1);
                        } else {
                            nrComenziOnMonth.put(luna, 1);
                        }
                    }

                }
            }
           if (!gasit) {
               nrComenziOnMonth.put(dateFormat.format(cal.getTime()).split("-")[1], 0);
           }
            cal.add(Calendar.MONTH, -1);

        }
        return nrComenziOnMonth;
    }
    public static Map<String ,Double> calculareIncasariComenziTotal(List<Comanda> comenzi){
        Map<String ,Double> incasariTotale=new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

        for(Comanda comanda: comenzi) {
            try {
                Date dataVechi= sdf.parse(comanda.getData());
                String dataNou=  dateFormat.format(dataVechi).split("-")[1];
                if (incasariTotale.containsKey(dataNou)) {
                    incasariTotale.replace(dataNou, incasariTotale.get(dataNou) + comanda.getValoare());
                } else {
                    incasariTotale.put(dataNou, comanda.getValoare());
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        return incasariTotale;
    }
    public static int calculareNrOspatariOnline(List<Ospatar> listaOspatari){
        int counterOspatariOnline = 0;

        //ospatariOnline
        for(Ospatar ospatar : listaOspatari){
            if(ospatar.getStatus().toLowerCase().equals("online"))
                counterOspatariOnline++;
        }
        return counterOspatariOnline;
    }

    public static  List<Comanda> listaComenziUltimaSaptamana(List<Comanda> comenzi){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR,-8);
        Date limita=cal.getTime();
        String dataLimita= sdf.format(limita);

        cal.add(Calendar.DAY_OF_YEAR,+8);
        List<Comanda > rezultat= new ArrayList<>();
        // get starting date
        for(int i=0;i<8;i++) {
            String data = sdf.format(cal.getTime());
            String zi = data.split(" ")[0];
            for (int j = comenzi.size() - 1; j >= 0; j--) {
                Comanda c = comenzi.get(j);
                try {
                    if (sdf.parse(c.getData()).compareTo(sdf.parse(dataLimita)) < 0) {
                        j = -1;
                    } else {
                        if (zi.equals(c.getData().split(" ")[0])) {
                            rezultat.add(c);
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            cal.add(Calendar.DAY_OF_YEAR, -1);
        }
        return rezultat;
    }
    public static Map<String ,Integer> calculareNrComenziSaptamana(List<Comanda> comenzi){

        nrComenziThisWeek=new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MMM-yyyy");

        Calendar cal=Calendar.getInstance();
        for(int i=0;i<8;i++){
            if (!nrComenziThisWeek.containsKey(dateFormat.format(cal.getTime()))) {
                nrComenziThisWeek.put(dateFormat.format(cal.getTime()).substring(0,6), 0);
            }
            cal.add(Calendar.DAY_OF_YEAR, -1);
        }
        for(Comanda comanda: comenzi){
            try {
                Date dataFV= sdf.parse(comanda.getData());
                String data= dateFormat.format(dataFV);
                String zi= data.substring(0,6);
                if (nrComenziThisWeek.containsKey(zi)) {
                    nrComenziThisWeek.replace(zi, nrComenziThisWeek.get(zi) + 1);
                } else {
                    nrComenziThisWeek.put(zi, 1);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return nrComenziThisWeek;
    }


    public static double calculeazaValoareTotalaIncasata(List<Comanda> comenzi){
       totalIncasari=0.0;

        for(Comanda comanda: comenzi) {
            totalIncasari+=comanda.getValoare();
        }
        return totalIncasari;
    }




}