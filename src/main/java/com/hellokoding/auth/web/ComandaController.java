package com.hellokoding.auth.web;

import com.hellokoding.auth.model.Comanda;
import com.hellokoding.auth.service.ComandaService;
import com.hellokoding.auth.service.SecurityService;
import com.hellokoding.auth.util.DateNecesare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Controller
public class ComandaController {
    @Autowired
    private ComandaService comandaService;

    @Autowired
    private SecurityService securityService;

    private List<Comanda> comenzi;


    private String dateChartUS(List<Comanda> comenzi1){

        Map<String,Integer> comenziPeZile= DateNecesare.calculareNrComenziSaptamana(comenzi1);

        SimpleDateFormat format= new SimpleDateFormat("dd-MMM-yyyy");
        StringBuilder stringZile=new StringBuilder();
        StringBuilder stringNumarcomenziZile=new StringBuilder();
        Calendar cal= Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR,-7);

        for(int i=0;i<7;i++){
            String zi= format.format(cal.getTime());
            if(comenziPeZile.containsKey(zi.substring(0,6))){
                stringZile.append(zi.substring(0,6)).append(",");
                stringNumarcomenziZile.append(comenziPeZile.get(zi)).append(",");
            }
            cal.add(Calendar.DAY_OF_YEAR,+1);
        }

        return stringZile.toString().substring(0,stringZile.length()-1).concat(";").concat(stringNumarcomenziZile.toString().substring(0,stringNumarcomenziZile.length()-1));
    }
    @RequestMapping(value = "/statisticiComenziUltimaSaptamana", method = RequestMethod.GET)
    public ModelAndView getStatistici() throws ParseException {
        ModelAndView model = new ModelAndView("statisticiComenziUltimaSaptamana");
        comenzi=comandaService.findAll();
        List<Comanda> comenziUS= DateNecesare.listaComenziUltimaSaptamana(comenzi);
        comenziUS.sort(Comanda::compareTo);
        int counter= comenziUS.size();
        String date= dateChartUS(comenziUS);
        model.addObject("dateChart", date);
        model.addObject("counterThisWeek", counter);
        model.addObject("listaComenzi", comenziUS);

        return model;
    }
    @RequestMapping(value = "/statisticiComenziUltimeleLuni", method = RequestMethod.GET)
    public ModelAndView getStatisticiUL() throws ParseException {
        ModelAndView model = new ModelAndView("statisticiComenziUltimeleLuni");
        comenzi=comandaService.findAll();
        int numarLuni=3;
        List<Comanda> comenziUL= DateNecesare.listaComenziUltimeleLuni(comenzi,numarLuni);
        comenziUL.sort(Comanda::compareTo);
        String date= dateChartUL(comenziUL, numarLuni);
        model.addObject("dateChart", date);
        model.addObject("listaComenzi", comenziUL);
        model.addObject("numarComenzi", comenziUL.size());
        return model;
    }

    private String dateChartUL(List<Comanda> comenziUL, int numarLuni) {
        Map<String, Integer> comenziPeLuna= DateNecesare.calculareNrComenziLunar(comenziUL,numarLuni);
        StringBuilder stringLuni=new StringBuilder();
        StringBuilder stringNumarcomenzi=new StringBuilder();
        String[] luni={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        for(String luna:luni){
            if(comenziPeLuna.containsKey(luna)){
                stringLuni.append(luna).append(",");
                stringNumarcomenzi.append(comenziPeLuna.get(luna)).append(",");
            }
        }

        return stringLuni.toString().substring(0,stringLuni.length()-1).concat(";").concat(stringNumarcomenzi.toString().substring(0,stringNumarcomenzi.length()-1));

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
}
