package graph;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Graph_view extends Frame implements ActionListener, WindowListener{
	
	private Button b1 = new Button("LineChart");
    private Button b2 = new Button("BarChart");
	
	public Graph_view(){
		addWindowListener(this);
		setTitle("Graph");
		
	    setLayout(new FlowLayout(FlowLayout.CENTER));
	    add(b1);
	    add(b2);
	    b1.addActionListener(this);
	    b2.addActionListener(this);
	}
	
	public void chart(String c) {
		
		removeAll();
		add(b1);
		add(b2);
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		
		int id,year,ton; 
		String name;
		ResultSet rs;
		
		MySQL mysql = new MySQL();
		
		rs = mysql.selectAll();
		
		try {
			while(rs.next()){
			    id = rs.getInt("id");
			    name = rs.getString("name");
			    year = rs.getInt("year");
			    ton = rs.getInt("ton");
//			    System.out.println("ID：" + id);
//			    System.out.println("name：" + name);
//			    System.out.println("year：" + year);
//			    System.out.println("ton：" + ton);
			    
			    data.addValue(ton, name, String.valueOf(year));
			}  //try catchで囲む
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		data.addValue(300, "USA", "2005");
		data.addValue(500, "USA", "2006");
		data.addValue(120, "USA", "2007");
		
		data.addValue(200, "China", "2005");
		data.addValue(400, "China", "2006");
		data.addValue(320, "China", "2007");
		*/
		
		addWindowListener(this);
		setTitle("Graph");
		
		if(c == "Line") {
			JFreeChart chart = 
			  	      ChartFactory.createLineChart("Import Volume",
			  	                                   "Year",
			  	                                   "Ton",
			  	                                   data,
			  	                                   PlotOrientation.VERTICAL,
			  	                                   true,
			  	                                   false,
			  	                                   false);
	  	    ChartPanel cpanel = new ChartPanel(chart);
	  	    add(cpanel, BorderLayout.CENTER);
		}else if(c == "Bar"){
			JFreeChart chart = 
			  	      ChartFactory.createBarChart("Import Volume",
			  	                                   "Year",
			  	                                   "Ton",
			  	                                   data,
			  	                                   PlotOrientation.VERTICAL,
			  	                                   true,
			  	                                   false,
			  	                                   false);
	  	    ChartPanel cpanel = new ChartPanel(chart);
	  	    add(cpanel, BorderLayout.CENTER);
		}
	}
	
    
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == b1) {
			chart("Line");
		}else if(e.getSource() == b2) {
			chart("Bar");
		}
		setVisible(true);
	}

}
