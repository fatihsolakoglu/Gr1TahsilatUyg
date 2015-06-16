/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.ptt.gr1tahsilatuyg.bean;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.chart.PieChartModel;
import tr.gov.ptt.gr1tahsilatuyg.service.TahsilatBorcService;


@ManagedBean(name = "chart")
@RequestScoped
public class TahsilatChartBean {
    
    
   private PieChartModel pieChartModel;
   
   @EJB
   private TahsilatBorcService tahsilatBorcService;
   
   private List<Object[]> chartListe;

    public List<Object[]> getChartListe() {
        return chartListe;
    }

    public void setChartListe(List<Object[]> chartListe) {
        this.chartListe = chartListe;
    }
   
   

    public TahsilatChartBean() {
        pieChartModel=new PieChartModel();
        pieChartModel.setLegendPosition("ne");
        pieChartModel.setShowDataLabels(true);
        pieChartModel.setTitle("Kurum Borç Grafiği");
        
    }
   
   @PostConstruct
    public void doldurChart()
   {
      chartListe=tahsilatBorcService.chartVerisiGetir();
       
       for (Object[] chartVeri : chartListe) {
           
         pieChartModel.set(String.valueOf(chartVeri[0]),Double.valueOf(chartVeri[1].toString()));
           
       }

   }

    public PieChartModel getPieChartModel() {
        return pieChartModel;
    }

    public void setPieChartModel(PieChartModel pieChartModel) {
        this.pieChartModel = pieChartModel;
    }
   
    
}
