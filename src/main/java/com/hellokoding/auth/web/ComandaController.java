package com.hellokoding.auth.web;

import com.hellokoding.auth.model.Comanda;
import com.hellokoding.auth.service.ComandaService;
import com.hellokoding.auth.service.ProdusService;
import com.hellokoding.auth.service.SecurityService;
import com.hellokoding.auth.util.CountProdus;
import com.hellokoding.auth.util.DateNecesare;
import com.hellokoding.auth.util.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ComandaController {
    @Autowired
    private ComandaService comandaService;
    @Autowired
    private ProdusService produsService;

    @Autowired
    private SecurityService securityService;

    private List<Comanda> comenzi;



    @RequestMapping(value = "/statisticiComenziUltimaSaptamana", method = RequestMethod.GET)
    public ModelAndView getStatistici() throws ParseException {
        ModelAndView model = new ModelAndView("statisticiComenziUltimaSaptamana");
        Global.listaComenziUltimaSaptamana.sort(Comanda::compareTo);
        int counter= Global.listaComenziUltimaSaptamana.size();
        if(Global.dateChartComenziUS==null||Global.dateChartComenziUS.isEmpty()||Global.dateChartComenziUS.trim().length()==0){
            Global.dateChartComenziUS= dateChartUS(Global.listaComenziUltimaSaptamana);
        }
        model.addObject("dateChart", Global.dateChartComenziUS);
        model.addObject("counterThisWeek", counter);
        model.addObject("listaComenzi", Global.listaComenziUltimaSaptamana);

        return model;
    }
    @RequestMapping(value = "/statisticiComenziUltimeleLuni", method = RequestMethod.GET)
    public ModelAndView getStatisticiUL() throws ParseException {
        ModelAndView model = new ModelAndView("statisticiComenziUltimeleLuni");
        int numarLuni=4;
        Global.listaComenziUltimeleLuni.sort(Comanda::compareTo);
        if(Global.dateChartComenziUL==null||Global.dateChartComenziUL.isEmpty()||Global.dateChartComenziUL.trim().length()==0){
            Global.dateChartComenziUL= dateChartUL(Global.listaComenziUltimeleLuni,numarLuni);
        }
        model.addObject("dateChart", Global.dateChartComenziUL);
        model.addObject("listaComenzi", Global.listaComenziUltimeleLuni);
        model.addObject("numarComenzi", Global.listaComenziUltimeleLuni.size());
        return model;
    }
    @RequestMapping(value = "/statisticiComenziUltimeleLuni/{numarLuni}", method = RequestMethod.GET)
    public ModelAndView getStatisticiULNumar(@PathVariable("numarLuni") int numarLuni) throws ParseException {
        ModelAndView model = new ModelAndView("statisticiComenziUltimeleLuni");
        Global.listaComenziUltimeleLuni.sort(Comanda::compareTo);
        List<Comanda> comenziNr=DateNecesare.listaComenziUltimeleLuni(Global.listaComenziUltimeleLuni,numarLuni);
        comenziNr.sort(Comanda::compareTo);
        String date= dateChartULNr(comenziNr,numarLuni);

        model.addObject("dateChart", date);
        model.addObject("listaComenzi", comenziNr);
        model.addObject("numarComenzi", comenziNr.size());
        model.addObject("numarLuni",numarLuni);
        return model;
    }
    @RequestMapping(value = "/statisticiComenziTotal", method = RequestMethod.GET)
    public ModelAndView statisticiTotalComenzi() throws ParseException {
        ModelAndView model = new ModelAndView("statisticiComenziTotal");
       if(Global.listaComenzi!=null && Global.listaComenzi.size()!=0){
           comenzi=Global.listaComenzi;
       }
       else{
           comenzi=comandaService.findAll();
       }

        if(Global.valoriPeLuna==null||Global.valoriPeLuna.size()==0) {
            Global.valoriPeLuna = DateNecesare.calculareIncasariComenziTotal(comenzi);
        }
        if(Global.dateChartComenziTotal==null||Global.dateChartComenziTotal.isEmpty()||Global.dateChartComenziTotal.trim().length()==0){
           Global.dateChartComenziTotal= dateChartIncasari(Global.valoriPeLuna);
        }
        if(Global.dateChartCeleMaiComandateProd==null||Global.dateChartCeleMaiComandateProd.isEmpty()||Global.dateChartCeleMaiComandateProd.trim().length()==0){
            if(Global.produseComandate==null ||Global.produseComandate.size()==0){
                Global.produseComandate= produsService.numarProduseComandate();
            }
            Global.dateChartCeleMaiComandateProd= dateChartCounterProduse(Global.produseComandate);
        }
        model.addObject("totalIncasari", Global.valoareTotala);
        model.addObject("dateChartIncasariTotale", Global.dateChartComenziTotal);
        model.addObject("dateChartProduse", Global.dateChartCeleMaiComandateProd);

        return model;
    }
    @RequestMapping(value = "/vizualizareProduse/{idComanda}", method = RequestMethod.GET)
    public ModelAndView getComenzi(@PathVariable Long idComanda) throws ParseException {
        ModelAndView model = new ModelAndView("vizualizareProduse");

        Comanda comanda= comandaService.findById(idComanda);
        model.addObject("valoareTotala",comanda.getValoare());
        model.addObject("listaProduse",comanda.getListaItemComanda());
        model.addObject("masa",comanda.getMasa().getId());
        return model;
    }

    @RequestMapping(value = "/vizualizareProduseUltimaSaptamana/{idComanda}", method = RequestMethod.GET)
    public ModelAndView getProduseUS(@PathVariable Long idComanda) throws ParseException {
        ModelAndView model = new ModelAndView("vizualizareProduseUltimaSaptamana");

        Comanda comanda= comandaService.findById(idComanda);
        model.addObject("valoareTotala",comanda.getValoare());
        model.addObject("listaProduse",comanda.getListaItemComanda());
        return model;
    }
    @RequestMapping(value = "/vizualizareProduseUltimeleLuni/{idComanda}", method = RequestMethod.GET)
    public ModelAndView getProduseUL(@PathVariable Long idComanda) throws ParseException {
        ModelAndView model = new ModelAndView("vizualizareProduseUltimeleLuni");

        Comanda comanda= comandaService.findById(idComanda);
        model.addObject("valoareTotala",comanda.getValoare());
        model.addObject("listaProduse",comanda.getListaItemComanda());
        return model;
    }
    private String dateChartUS(List<Comanda> comenzi1){

        if(Global.comenziPeZile==null || Global.comenziPeZile.size()==0) {
           Global.comenziPeZile = DateNecesare.calculareNrComenziSaptamana(comenzi1);
        }
        SimpleDateFormat format= new SimpleDateFormat("dd-MMM-yyyy");
        StringBuilder stringZile=new StringBuilder();
        StringBuilder stringNumarcomenziZile=new StringBuilder();
        Calendar cal= Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR,-8);

        for(int i=0;i<8;i++){
            String zi= format.format(cal.getTime());
            if(Global.comenziPeZile.containsKey(zi.substring(0,6))){
                stringZile.append(zi.substring(0,6)).append(",");
                stringNumarcomenziZile.append(Global.comenziPeZile
                        .get(zi.substring(0,6))).append(",");
            }
            cal.add(Calendar.DAY_OF_YEAR,+1);
        }

        return stringZile.toString().substring(0,stringZile.length()-1).concat(";").concat(stringNumarcomenziZile.toString().substring(0,stringNumarcomenziZile.length()-1));
    }
    private String dateChartCounterProduse(List<CountProdus> produseComandate) {
        StringBuilder stringId=new StringBuilder();
        StringBuilder stringNumar=new StringBuilder();
        for(CountProdus produs: produseComandate){
                stringId.append(produs.getDenumire()).append("-");
                stringNumar.append(produs.getCantitate_totala()).append("-");
            }

        return stringId.toString().substring(0,stringId.length()-1).concat(";").concat(stringNumar.toString().substring(0,stringNumar.length()-1));

    }

    private String dateChartIncasari(Map<String, Double> incasariComenziTotal) {
        StringBuilder stringLuni=new StringBuilder();
        StringBuilder stringIncasari=new StringBuilder();
        String[] luni={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        for(String luna:luni){
            if(incasariComenziTotal.containsKey(luna)){
                stringLuni.append(luna).append("-");
                stringIncasari.append(incasariComenziTotal.get(luna)).append("-");
            }

        }

        return stringLuni.toString().substring(0,stringLuni.length()-1).concat(";").concat(stringIncasari.toString().substring(0,stringIncasari.length()-1));

    }

    private String dateChartUL(List<Comanda> comenziUL, int numarLuni) {
        if(Global.comenziPeLuna==null||Global.comenziPeLuna.size()==0){
            Global.comenziPeLuna=DateNecesare.calculareNrComenziLunar(comenziUL,numarLuni);
        }

        StringBuilder stringLuni=new StringBuilder();
        StringBuilder stringNumarcomenzi=new StringBuilder();
        String[] luni={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        for(String luna:luni){
            if(Global.comenziPeLuna.containsKey(luna)){
                stringLuni.append(luna).append(",");
                stringNumarcomenzi.append(Global.comenziPeLuna.get(luna)).append(",");
            }
        }

        return stringLuni.toString().substring(0,stringLuni.length()-1).concat(";").concat(stringNumarcomenzi.toString().substring(0,stringNumarcomenzi.length()-1));
    }
    private String dateChartULNr(List<Comanda> comenziUL, int numarLuni) {

        Map<String,Integer> mapLuni=new HashMap<>();
        mapLuni=DateNecesare.calculareNrComenziLunar(comenziUL,numarLuni);
        StringBuilder stringLuni=new StringBuilder();
        StringBuilder stringNumarcomenzi=new StringBuilder();
        String[] luni={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        for(String luna:luni){
            if(mapLuni.containsKey(luna)){
                stringLuni.append(luna).append(",");
                stringNumarcomenzi.append(mapLuni.get(luna)).append(",");
            }
        }

        return stringLuni.toString().substring(0,stringLuni.length()-1).concat(";").concat(stringNumarcomenzi.toString().substring(0,stringNumarcomenzi.length()-1));
    }

}
