package com.hellokoding.auth.util;

import com.hellokoding.auth.model.Produs;
import com.hellokoding.auth.model.Review;
import com.hellokoding.auth.service.ProdusService;
import com.hellokoding.auth.service.ReviewService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.List;

@Component
public class ThreadStatisticiProduse implements DisposableBean, Runnable {

    private ProdusService produsService;
    private ReviewService reviewService;

    private Thread thread;
    private volatile boolean someCondition;

    public ReviewService getReviewService() {
        return reviewService;
    }

    public void setReviewService(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    public ProdusService getProdusService() {
        return produsService;
    }

    public void setProdusService(ProdusService produsService) {
        this.produsService = produsService;
    }

    public ThreadStatisticiProduse(){
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
            Global.listaProduse=produsService.findAll();
            Global.dateChartReviewProduse= dateChartProduse(Global.listaProduse);

            if(Global.listaProduse!=null && Global.dateChartReviewProduse!=null)
                someCondition =false;


        }
    }
    private String dateChartProduse (List<Produs> produses){
        int counterLow = 0;
        int counterHigh = 0;
        for(Produs p: produses) {
            if(Global.reviewProduse==null||Global.reviewProduse.size()==0||!Global.reviewProduse.containsKey(p.getId())){
                Global.reviewProduse.put(p.getId(),reviewService.findByIdProdus(p.getId()));
            }
            float sum = 0;
            if (Global.reviewProduse.get(p.getId()) != null && Global.reviewProduse.get(p.getId()).size()!=0 && !Global.reviewProduse.get(p.getId()).isEmpty() ) {
                for (Review review : Global.reviewProduse.get(p.getId())) {
                    sum += review.getNota();
                }
                double medie = sum / Global.reviewProduse.get(p.getId()).size();
                DecimalFormat df = new DecimalFormat("####0.00");
                if (medie <= 3) counterLow++;
                else counterHigh++;
                Global.noteProduse.put(p.getId(), Double.valueOf(df.format(medie)));
            }
        }

        return counterLow+";"+counterHigh;
    }

    @Override
    public void destroy(){
        someCondition = false;
    }

}