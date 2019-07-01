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
        //nrComenziOnMonth=new HashMap<>();
        List<Comanda> listaComenzi=new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        Calendar cal=Calendar.getInstance();
        for(int i=1;i<=numarLuni;i++){
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
       /* for(int i=1;i<=numarLuni;i++){

            for(Comanda comanda: comenzi){
                String dataLimita= sdf.format(cal.getTime());
                String lunaNr= dataLimita.split(" ")[0].split("-")[1];
                if(comanda.getData().split("-")[1].equalsIgnoreCase(lunaNr)){
                    String luna = dateFormat.format(cal.getTime()).split("-")[1];

                    if (nrComenziOnMonth.containsKey(luna)) {
                        nrComenziOnMonth.replace(luna, nrComenziOnMonth.get(luna) + 1);
                    } else {
                        nrComenziOnMonth.put(luna, 1);
                    }
                }

            }
               if (!nrComenziOnMonth.containsKey(dateFormat.format(cal.getTime()).split("-")[1])) {
                nrComenziOnMonth.put(dateFormat.format(cal.getTime()).split("-")[1], 0);
            }
            cal.add(Calendar.MONTH, -1);

        }*/


       for(int i=0;i<=numarLuni;i++){
            String dataLimita= sdf.format(cal.getTime());
            int lunaLimita= Integer.parseInt(dataLimita.split(" ")[0].split("-")[1]);
            String lunaNr= dataLimita.split(" ")[0].split("-")[1];
            for (int j=comenzi.size()-1;j>=0;j--) {
                Comanda c = comenzi.get(j);

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
        return nrComenziOnMonth;
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
    public static  int numarComenziUltimaSaptamana(List<Comanda> comenzi){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR,-7);
        Date limita=cal.getTime();
        cal.add(Calendar.DAY_OF_YEAR,+7);
       counterComenziThisWeek=0;
        // get starting date
        for(int i=0;i<7;i++) {
            String data = sdf.format(cal.getTime());
            String zi = data.split(" ")[0];
            for (int j = comenzi.size() - 1; j >= 0; j--) {
                Comanda c = comenzi.get(j);
                if (c.getData().compareTo(sdf.format(limita)) < 0) {
                    j = -1;
                } else {
                    if (zi.equals(c.getData().split(" ")[0])) {
                        counterComenziThisWeek++;
                    }
                }
            }
            cal.add(Calendar.DAY_OF_YEAR, -1);
        }
        return counterComenziThisWeek;
    }
    public static  List<Comanda> listaComenziUltimaSaptamana(List<Comanda> comenzi){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR,-7);
        Date limita=cal.getTime();
        cal.add(Calendar.DAY_OF_YEAR,+7);
        List<Comanda > rezultat= new ArrayList<>();
        // get starting date
        for(int i=0;i<7;i++) {
            String data = sdf.format(cal.getTime());
            String zi = data.split(" ")[0];
            for (int j = comenzi.size() - 1; j >= 0; j--) {
                Comanda c = comenzi.get(j);
                if (c.getData().compareTo(sdf.format(limita)) < 0) {
                    j = -1;
                } else {
                    if (zi.equals(c.getData().split(" ")[0])) {
                        rezultat.add(c);
                    }
                }
            }
            cal.add(Calendar.DAY_OF_YEAR, -1);
        }
        return rezultat;
    }
    public static Map<String ,Integer> calculareNrComenziSaptamana(List<Comanda> comenzi){
        /*//last 7 days
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

        Date limita=cal.getTime();
        cal.add(Calendar.DAY_OF_YEAR,+7);

        for (int i=0;i<7;i++){
            String zi= saptamana.get(i).split(" ")[0];
            for (int j=comenzi.size()-1;j>=0;j--) {
                Comanda c= comenzi.get(j);
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

        return nrComenziThisWeek;

*/
        nrComenziThisWeek=new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MMM-yyyy");

        Calendar cal=Calendar.getInstance();
        for(int i=0;i<7;i++){
            if (!nrComenziThisWeek.containsKey(dateFormat.format(cal.getTime()))) {
                nrComenziThisWeek.put(dateFormat.format(cal.getTime()).substring(0,6), 0);
            }
            cal.add(Calendar.DAY_OF_YEAR, -1);
        }
        for(Comanda comanda: comenzi){
            try {
                Date data= dateFormat.parse(comanda.getData());
                String zi= data.toString().substring(0,6);
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


    /*public static void calculeazaValoareIncasata(Comanda comanda){
        totalIncasariSaptamana=0.0;
        for(ItemComanda itemComanda :  comanda.getListaItemComanda() ){
            String pretProdus = itemComanda.getProdus().getPret().toString();
            Double pretComanda = itemComanda.getCantitate() * Double.valueOf(pretProdus);
            totalIncasariSaptamana +=pretComanda;
        }
    }*/
    public static double calculeazaValoareTotalaIncasata(List<Comanda> comenzi){
       totalIncasari=0.0;
        for(Comanda comanda: comenzi) {
            totalIncasari+=comanda.getValoare();
        }
        return totalIncasari;
    }

    public Integer getCounterComenziThisWeek() {
        return counterComenziThisWeek;
    }

}