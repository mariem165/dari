package ManagedBeans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.chartistjsf.model.chart.AspectRatio;
import org.chartistjsf.model.chart.Axis;
import org.chartistjsf.model.chart.AxisType;
import org.chartistjsf.model.chart.BarChartModel;
import org.chartistjsf.model.chart.BarChartSeries;
import org.chartistjsf.model.chart.ChartSeries;
import org.chartistjsf.model.chart.LineChartModel;
import org.chartistjsf.model.chart.LineChartSeries;
import org.chartistjsf.model.chart.PieChartModel;
import org.primefaces.event.ItemSelectEvent;

import Services.StatService;
import tn.esprit.dari.entities.Meubles;
import tn.esprit.dari.entities.Statistics;

@ManagedBean(name="linebean")
public class ChartBackBean {
	@EJB
	StatService Ss;
	public static List<Meubles> M;
	public static List<Integer> Inn; 
	public List<Meubles>M1;
	public List<Integer> Inn2;
	private BarChartModel barChartModel;
	


	public List<Integer> getInn2() {
		return ChartBackBean.Inn;
	}

	public void setInn2(List<Integer> inn2) {
		Inn2 = inn2;
	}

	public static List<Integer> getInn() {
		return Inn;
	}

	public static void setInn(List<Integer> inn) {
		Inn = inn;
	}

	public static List<Meubles> getM() {
		return M;
	}

	public static void setM(List<Meubles> m) {
		M = m;
	}



	public List<Meubles> getM1() {
		return ChartBackBean.M;
	}

	public void setM1(List<Meubles> m1) {
		M1 = m1;
	}



	private LineChartModel lineChartModel;
    private List <String> DatesList;
    
    public List<String> getDatesList() {
    	return DatesList;
	}

	public void setDatesList(List<String> datesList) {
		DatesList = datesList;
	}
	
	
	public String setdatalists ()
	{
		List <Statistics> l= new ArrayList<Statistics>();
    	l=Ss.ShowStat();
    	/////line chart
    	List <String> dd= new ArrayList<String>();
    	List <String> DADA= new ArrayList<String>();
    	List<Integer>Numb = new ArrayList<Integer>();
    	if (l.size()!=0)
    		for (Statistics s : l) {
    		dd.add(s.getDate());
    	}
    	else dd.add("none");
    	
    	for (String Dates : dd) {
    		if(!DADA.contains(Dates)) {
    		DADA.add(Dates);
    		
    		}
    		
    	}
    	for (String h: DADA) {
        	Numb.add(Collections.frequency(dd,h));}
    	
    	//bar chart
    	List <String> dd1= new ArrayList<String>();
    	List <String> DADA2= new ArrayList<String>();
    	List<Integer>Numb2 = new ArrayList<Integer>();
    	
    	if (l.size()!=0)
    		for (Statistics s : l) {
    		dd1.add(s.getLocation());
    	}
    	else dd.add("none");
    	for (String Dates2 : dd1) {
    		if(!DADA2.contains(Dates2)) {
    		DADA2.add(Dates2);
    		
    		}
    		
    	}
    	for (String h2: DADA2) {
        	Numb2.add(Collections.frequency(dd1,h2));}
    	
    	///Pie chart
    	List <String> dd3= new ArrayList<String>();
    	List <String> DADA3= new ArrayList<String>();
    	List<Integer>Numb3 = new ArrayList<Integer>();
    	
    	if (l.size()!=0)
    		for (Statistics s : l) {
    		dd3.add(s.getIp());
    	}
    	else dd3.add("none");
    	for (String Dates3 : dd3) {
    		if(!DADA3.contains(Dates3)) {
    		DADA3.add(Dates3);
    		
    		}
    		
    	}
    	for (String h3: DADA3) {
        	Numb3.add(Collections.frequency(dd3,h3));}
    	
    	///table visits 
    	
    	List <Meubles> dd4= new ArrayList<Meubles>();
    	List <Meubles> DADA4= new ArrayList<Meubles>();
    	List <Meubles> DADA5= new ArrayList<Meubles>();
    	List<Integer>Numb4 = new ArrayList<Integer>();
    	
    	if (l.size()!=0)
    		for (Statistics s : l) {
    			
    			
    			
    		dd4.add(s.getMeubles());
    	}
    	else dd3.add("none");
    	for (Meubles Dates4 : dd4) {
    		Meubles DDD =new Meubles();
    		DDD.setTitle(Dates4.getTitle());
    		DDD.setId(Dates4.getId());
    		DDD.setDescription(Dates4.getDescription());
    		DDD.setPrix(Dates4.getPrix());
    		DDD.setImage(Dates4.getImage());
    		DDD.setUser(null);
    		System.out.println(DADA4.contains(DDD));
    		System.out.println("___________________");
    		int test=0;
    		
    		for(Meubles Meuuuu: DADA4) {   			
    			if (Meuuuu.getId()==DDD.getId())test=1;
    		}
    		
    		
    		if(test==0) {
    		DADA4.add(DDD);
    		
    		}
    		
    	}
    	/*for (Meubles ppo: DADA4)
    	System.out.println(ppo.getId());*/
    	for (int y=0; y<=4; y++) DADA5.add(DADA4.get(y));
    	for (Meubles h4: DADA5) {
        	Numb4.add(Collections.frequency(dd4,h4));}
    	
    	ChartBackBean.M=DADA5;
    	ChartBackBean.Inn=Numb4;
    	//// execution
    	
		createLineModel(DADA,Numb);
		createBarModel(DADA3,Numb3);
		createPieChart(DADA2,Numb2);
		return "dashboard";
		
	}

//line
    public void createLineModel(List <String> DADA,List<Integer>Numb) {
    	
    	
    	int a=20;
        lineChartModel = new LineChartModel();
        lineChartModel.setAspectRatio(AspectRatio.GOLDEN_SECTION);

        

        LineChartSeries lineChartSeries1 = new LineChartSeries();
        lineChartSeries1.setName("Series 1");
        
      	

    	for (String K:DADA) lineChartModel.addLabel(K);
    	
		for (int i:Numb)lineChartSeries1.set(i);       	
        Axis xAxis = lineChartModel.getAxis(AxisType.X);
        lineChartModel.addSeries(lineChartSeries1);
        lineChartModel.setAnimateAdvanced(true);
        lineChartModel.setShowTooltip(true);
    }
    
    
    //bar
    public void createBarModel(List <String> DADA,List<Integer>Numb) {

    	barChartModel = new BarChartModel();
    	barChartModel.setAspectRatio(AspectRatio.GOLDEN_SECTION);

    	for(String h:DADA)barChartModel.addLabel(h);
    	
    	BarChartSeries series1 = new BarChartSeries();
    	series1.setName("First Series");

    	for(int i:Numb)series1.set(i);
    	
    	


    	Axis xAxis = barChartModel.getAxis(AxisType.X);
    	xAxis.setShowGrid(false);

    	barChartModel.addSeries(series1);
    	

    	barChartModel.setShowTooltip(true);
    	barChartModel.setSeriesBarDistance(15);
    	barChartModel.setAnimateAdvanced(true);

    	}

    	public BarChartModel getBarChartModel() {
    	return barChartModel;
    	}

    	public void setBarChartModel(BarChartModel barChartModel) {
    	this.barChartModel = barChartModel;
    	}
    
    

    public void itemSelect(ItemSelectEvent event) {

       FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected", "Item Value: "+ 
((ChartSeries) lineChartModel.getSeries().get(event.getSeriesIndex())).getData().get(event.getItemIndex())
                + ", Series name:" +
 ((ChartSeries) lineChartModel.getSeries().get(event.getSeriesIndex())).getName());

        FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(), msg);
    }
    public LineChartModel getLineModel() {
        return lineChartModel;
    }
    public void setLineModel(LineChartModel lineModel) {
        this.lineChartModel = lineModel;
    }
    public void barItemSelect(ItemSelectEvent event) {

    	FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected", "Item Value: "
    	+ ((ChartSeries) barChartModel.getSeries().get(event.getSeriesIndex())).getData().get(
    	event.getItemIndex()) + ", Series name:"
    	+ ((ChartSeries) barChartModel.getSeries().get(event.getSeriesIndex())).getName());

    	FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(), msg);
    	}
    
    
    
    ///Pie
    

    private PieChartModel pieChartModel;


    public void createPieChart(List <String> DADA,List<Integer>Numb) {
        pieChartModel = new PieChartModel();

        for(String h:DADA)pieChartModel.addLabel(h);
        

        for(int i:Numb) pieChartModel.set(i);
        

        pieChartModel.setShowTooltip(true);
    }

    public void pieItemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected", "Item Value: "
                + pieChartModel.getData().get(event.getItemIndex()));

        FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(), msg);
    }

    public PieChartModel getPieChartModel() {
        return pieChartModel;
    }

    public void setPieChartModel(PieChartModel pieChartModel) {
        this.pieChartModel = pieChartModel;
    }

    
 

	
    
}