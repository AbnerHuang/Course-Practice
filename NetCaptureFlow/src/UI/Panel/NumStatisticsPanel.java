package UI.Panel;

import java.awt.GridLayout;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import UI.ConstantUI;
import Util.NetPacketReceiver;
import javafx.scene.chart.PieChart;

public class NumStatisticsPanel extends JPanel {
	private JPanel piePanel;
	private JPanel barPanel;
	private DefaultPieDataset defaultPieDataset;
	private DefaultCategoryDataset defaultCategoryDataset;
	
	public NumStatisticsPanel() {
		// TODO Auto-generated constructor stub
		initialize();
		addChart();
	}
	
	private void initialize() {
		this.setBackground(ConstantUI.MAIN_BACKGROUND_COLOR);
		this.setLayout(new GridLayout(2, 1));
		
		defaultPieDataset = new DefaultPieDataset();
		defaultPieDataset.setValue("TCP", NetPacketReceiver.getTCPNum());
		defaultPieDataset.setValue("UDP", NetPacketReceiver.getUDPNum());
		defaultPieDataset.setValue("ICMP", NetPacketReceiver.getICMPNum());
		defaultPieDataset.setValue("ARP", NetPacketReceiver.getARPNum());
		defaultPieDataset.setValue("Others", NetPacketReceiver.getOthers());
		
		defaultCategoryDataset = new DefaultCategoryDataset();
		defaultCategoryDataset.setValue(NetPacketReceiver.getTCPNum(),  "TCP","");
		defaultCategoryDataset.setValue(NetPacketReceiver.getUDPNum(),  "UDP","");
		defaultCategoryDataset.setValue(NetPacketReceiver.getICMPNum(),  "ICMP","");
		defaultCategoryDataset.setValue(NetPacketReceiver.getARPNum(),  "ARP","");
		defaultCategoryDataset.setValue(NetPacketReceiver.getOthers(),  "Others","");		
	}
	
	private void addChart() {
		JFreeChart pieChart = ChartFactory.createPieChart3D("流量包统计", defaultPieDataset);
		pieChart.getTitle().setFont(ConstantUI.FONT_TITLE);
		
		PiePlot piePlot = (PiePlot)pieChart.getPlot();
		piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})"));
		
		ChartPanel pieChartPanel = new ChartPanel(pieChart);
		piePanel = new JPanel(true);
		piePanel.setBackground(ConstantUI.MAIN_BACKGROUND_COLOR);
		piePanel.add(pieChartPanel);
		
		JFreeChart barChart = ChartFactory.createBarChart("流量包统计", "数据包种类",
				"数据包个数", defaultCategoryDataset);
		
		barChart.getTitle().setFont(ConstantUI.FONT_TITLE);
		barChart.getLegend().setItemFont(ConstantUI.FONT_NORMAL);
		
		CategoryPlot plot = barChart.getCategoryPlot();
		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setLabelFont(ConstantUI.FONT_NORMAL);
		domainAxis.setTickLabelFont(ConstantUI.FONT_NORMAL);
		ValueAxis rangeAxis = plot.getRangeAxis();
		rangeAxis.setLabelFont(ConstantUI.FONT_NORMAL);
		
		BarRenderer barRenderer = (BarRenderer)plot.getRenderer();
		barRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		barRenderer.setBaseItemLabelsVisible(true);
		
		barPanel = new JPanel(true);
		barPanel.setBackground(ConstantUI.MAIN_BACKGROUND_COLOR);
		ChartPanel barChartPanel = new ChartPanel(barChart);
		barPanel.add(barChartPanel);
		
		this.add(piePanel);
		this.add(barPanel);
	}
}
