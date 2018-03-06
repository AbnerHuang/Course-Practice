package UI.Panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
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

public class DataStatisticsPanel extends JPanel{
	private JLabel upLoadSumLabel;
	private JLabel downLoadSumLabel;
	private JLabel upLoadSum;
	private JLabel downloadSum;
	private JPanel northPanel;
	private JPanel centerPanel;
	private JPanel pieUploadPanel;
	private JPanel pieDownloadPanel;
	private JPanel barUploadPanel;
	private JPanel barDownloadPanel;
	private DefaultPieDataset defaultDownloadPieDataset;
	private DefaultPieDataset defaultUploadPieDataset;
	private DefaultCategoryDataset defaultDownloadCategoryDataset;
	private DefaultCategoryDataset defaultUploadCategoryDataset;
	
	public DataStatisticsPanel() {
		// TODO Auto-generated constructor stub
		initialize();
		addChart();
	}
	
	private void initialize() {
		this.setBackground(ConstantUI.MAIN_BACKGROUND_COLOR);
		this.setLayout(new BorderLayout());
		
		northPanel = new JPanel();
		northPanel.setBackground(ConstantUI.MAIN_BACKGROUND_COLOR);
		upLoadSumLabel = new JLabel("上行总流量");
		upLoadSum = new JLabel(String.valueOf(NetPacketReceiver.getUpLoadIPPacket())+"字节");
		northPanel.add(upLoadSumLabel);
		northPanel.add(upLoadSum);
		downLoadSumLabel = new JLabel("下行总流量");
		downloadSum = new JLabel(String.valueOf(NetPacketReceiver.getDownLoadPacket())+"字节");
		northPanel.add(downLoadSumLabel);
		northPanel.add(downloadSum);
		this.add(northPanel, BorderLayout.NORTH);
		
		centerPanel = new JPanel();
		centerPanel.setBackground(ConstantUI.MAIN_BACKGROUND_COLOR);
		centerPanel.setLayout(new GridLayout(2, 2));
		
		defaultDownloadPieDataset = new DefaultPieDataset();
		defaultDownloadPieDataset.setValue("Others", NetPacketReceiver.getDownLoadPacket() - 
				NetPacketReceiver.getDownLoadIPPacket());
		defaultDownloadPieDataset.setValue("TCP", NetPacketReceiver.getDownLoadTCPPacket());
		defaultDownloadPieDataset.setValue("UDP", NetPacketReceiver.getDownLoadUDPPacket());
		defaultDownloadPieDataset.setValue("IP中的Others", NetPacketReceiver.getDownLoadIPPacket() - 
				NetPacketReceiver.getDownLoadTCPPacket() - NetPacketReceiver.getDownLoadUDPPacket());
		
		defaultUploadPieDataset = new DefaultPieDataset();
		defaultUploadPieDataset.setValue("Others", NetPacketReceiver.getUpLoadPacket() - 
				NetPacketReceiver.getUpLoadIPPacket());
		defaultUploadPieDataset.setValue("TCP", NetPacketReceiver.getUpLoadTCPPacket());
		defaultUploadPieDataset.setValue("UDP", NetPacketReceiver.getUpLoadUDPPacket());
		defaultUploadPieDataset.setValue("IP中的Others", NetPacketReceiver.getUpLoadIPPacket() - 
				NetPacketReceiver.getUpLoadTCPPacket() - NetPacketReceiver.getUpLoadUDPPacket());
		
		defaultDownloadCategoryDataset = new DefaultCategoryDataset();
		defaultDownloadCategoryDataset.addValue(NetPacketReceiver.getDownLoadPacket() - 
				NetPacketReceiver.getDownLoadIPPacket(),"Others","");
		defaultDownloadCategoryDataset.addValue( NetPacketReceiver.getDownLoadTCPPacket(),"TCP","");
		defaultDownloadCategoryDataset.addValue( NetPacketReceiver.getDownLoadUDPPacket(),"UDP","");
		defaultDownloadCategoryDataset.addValue( NetPacketReceiver.getDownLoadIPPacket() - 
				NetPacketReceiver.getDownLoadTCPPacket() - NetPacketReceiver.getDownLoadUDPPacket(),"IP中的Others","");
		
		defaultUploadCategoryDataset = new DefaultCategoryDataset();
		defaultUploadCategoryDataset.addValue(NetPacketReceiver.getUpLoadPacket() - 
				NetPacketReceiver.getUpLoadIPPacket(),"Others","");
		defaultUploadCategoryDataset.addValue( NetPacketReceiver.getUpLoadTCPPacket(),"TCP","");
		defaultUploadCategoryDataset.addValue( NetPacketReceiver.getUpLoadUDPPacket(),"UDP","");
		defaultUploadCategoryDataset.addValue( NetPacketReceiver.getUpLoadIPPacket() - 
				NetPacketReceiver.getUpLoadTCPPacket() - NetPacketReceiver.getUpLoadUDPPacket(),"IP中的Others","");
	}
	
	private void addChart() {
		JFreeChart uploadPieChart = ChartFactory.createPieChart3D("上行数据流量饼状图", defaultUploadPieDataset);
		uploadPieChart.getTitle().setFont(ConstantUI.FONT_TITLE);
		PiePlot uploadPiePlot = (PiePlot)uploadPieChart.getPlot();
		uploadPiePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})"));
		uploadPiePlot.setLabelFont(ConstantUI.FONT_NORMAL);
		uploadPieChart.getLegend().setItemFont(ConstantUI.FONT_NORMAL);
		ChartPanel uploadPiePanel = new ChartPanel(uploadPieChart);
		pieUploadPanel = new JPanel(true);
		pieUploadPanel.add(uploadPiePanel);

		JFreeChart downloadPieChart = ChartFactory.createPieChart3D("下载数据流量饼状图", defaultDownloadPieDataset);
		downloadPieChart.getTitle().setFont(ConstantUI.FONT_TITLE);
		PiePlot downloadPiePlot = (PiePlot)downloadPieChart.getPlot();
		downloadPiePlot.setLabelFont(ConstantUI.FONT_NORMAL);
		downloadPieChart.getLegend().setItemFont(ConstantUI.FONT_NORMAL);
		downloadPiePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})"));
		ChartPanel downloadPiePanel = new ChartPanel(downloadPieChart);
		pieDownloadPanel = new JPanel(true);
		pieDownloadPanel.add(downloadPiePanel);
		
		JFreeChart uploadBarChart = ChartFactory.createBarChart("上行数据流量柱状图", "数据包种类", 
				"数据包流量", defaultUploadCategoryDataset);
		
		uploadBarChart.getTitle().setFont(ConstantUI.FONT_NORMAL);
		uploadBarChart.getLegend().setItemFont(ConstantUI.FONT_NORMAL);
		
		CategoryPlot uploadCategoryPlot = (CategoryPlot)uploadBarChart.getPlot();
		CategoryAxis uploadAxis = uploadCategoryPlot.getDomainAxis();
		uploadAxis.setLabelFont(ConstantUI.FONT_NORMAL);
		uploadAxis.setTickLabelFont(ConstantUI.FONT_NORMAL);
		
		ValueAxis uploadRangeAxis = uploadCategoryPlot.getRangeAxis();
		uploadRangeAxis.setLabelFont(ConstantUI.FONT_NORMAL);
		
		BarRenderer uploadBarRender = (BarRenderer)uploadCategoryPlot.getRenderer();
		uploadBarRender.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		uploadBarRender.setBaseItemLabelFont(ConstantUI.FONT_NORMAL);
		uploadBarRender.setBaseItemLabelsVisible(true);
		
		ChartPanel uploadBarPanel = new ChartPanel(uploadBarChart);
		barUploadPanel = new JPanel(true);
		barUploadPanel.add(uploadBarPanel);
		
		JFreeChart downloadBarChart = ChartFactory.createBarChart("下载数据流量柱状图", "数据包种类",
				"数据包流量", defaultDownloadCategoryDataset);
		
		downloadBarChart.getTitle().setFont(ConstantUI.FONT_NORMAL);
		downloadBarChart.getLegend().setItemFont(ConstantUI.FONT_NORMAL);
		
		CategoryPlot downloadCategoryPlot = (CategoryPlot)downloadBarChart.getPlot();
		CategoryAxis downloadAxis = downloadCategoryPlot.getDomainAxis();
		downloadAxis.setLabelFont(ConstantUI.FONT_NORMAL);
		downloadAxis.setTickLabelFont(ConstantUI.FONT_NORMAL);
		
		ValueAxis downloadRangeAxis = downloadCategoryPlot.getRangeAxis();
		downloadRangeAxis.setLabelFont(ConstantUI.FONT_NORMAL);
		
		BarRenderer downloadBarRender = (BarRenderer)downloadCategoryPlot.getRenderer();
		downloadBarRender.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		downloadBarRender.setBaseItemLabelFont(ConstantUI.FONT_NORMAL);
		downloadBarRender.setBaseItemLabelsVisible(true);
		
		ChartPanel downloadBarPanel = new ChartPanel(downloadBarChart);
		barDownloadPanel = new JPanel(true);
		barDownloadPanel.add(downloadBarPanel);
		
		centerPanel.add(pieUploadPanel);
		centerPanel.add(pieDownloadPanel);
		centerPanel.add(barUploadPanel);
		centerPanel.add(barDownloadPanel);
		
		this.add(centerPanel,BorderLayout.CENTER);
	}
}
