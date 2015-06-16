/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.ptt.gr1tahsilatuyg.bean;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.chart.PieChartModel;
import tr.gov.ptt.gr1tahsilatuyg.service.TahsilatBorcService;


@ManagedBean(name = "chart")
@RequestScoped
public class TahsilatChartBean {
    
    
   private PieChartModel pieChartModel;
   DefaultPieDataset dataset = new DefaultPieDataset();
   
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
    
    public StreamedContent getJfreeChart() {
        StreamedContent content = null;
        try {
            
            
             chartListe = tahsilatBorcService.chartVerisiGetir();

            for (Object[] chartVeri : chartListe) {

                dataset.setValue(String.valueOf(chartVeri[0]), Double.valueOf(chartVeri[1].toString()));

            }
            
            boolean legend = true, tooltip = true, urls = false;
            JFreeChart chart = ChartFactory.createPieChart("JFreeChart",
                    dataset, legend, tooltip, urls);
            File chartFile = new File("jfreechart");
            int width = 375, height = 300;
            ChartUtilities.saveChartAsPNG(chartFile, chart, width,
                    height);
            content = new DefaultStreamedContent(new FileInputStream(chartFile), "image/png");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return content;
    }
    
    public void temizleGrafik()
    {
        dataset.clear();
        pieChartModel.clear();
        doldurChart();
    }
   
    
}
