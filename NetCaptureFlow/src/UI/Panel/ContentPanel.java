package UI.Panel;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import UI.ConstantUI;

public class ContentPanel extends JPanel {
	private JPanel mainPanel;
	private static JPanel mainPanelCenter;
	
	private ToolBarPanel toolBarPanel;
	private static NetworkPanel networkPanel;
	private static StatisticsPanel statisticsPanel;
	
	public ContentPanel() {
		// TODO Auto-generated constructor stub
		initialize();
	}
	
	private void initialize() {
		try {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		this.setLayout(new BorderLayout());
		this.setBackground(ConstantUI.MAIN_BACKGROUND_COLOR);
		this.setBounds(ConstantUI.MAIN_WINDOW_X, ConstantUI.MAIN_WINDOW_Y, 
				ConstantUI.MAIN_WINDOW_WIDTH, ConstantUI.MAIN_WINDOW_HEIGHT);
		
		mainPanel = new JPanel(true);
		mainPanel.setBackground(ConstantUI.MAIN_BACKGROUND_COLOR);
		mainPanel.setLayout(new BorderLayout());
		
		toolBarPanel = new ToolBarPanel();
		
		mainPanel.add(toolBarPanel,BorderLayout.WEST);
		
		networkPanel = new NetworkPanel();
		statisticsPanel = new StatisticsPanel();
		mainPanelCenter = new JPanel(true);
		
		mainPanelCenter.setBackground(ConstantUI.MAIN_BACKGROUND_COLOR);
		mainPanelCenter.add(networkPanel);
		
		mainPanel.add(mainPanelCenter, BorderLayout.CENTER);
		
		
		
		this.add(mainPanel);
		this.setVisible(true);
	}
	
	public static JPanel getMainPanelCenter() {
		return ContentPanel.mainPanelCenter;
	}
	
	public static NetworkPanel getNetworkPanel() {
		return ContentPanel.networkPanel;
	}
	
	public static StatisticsPanel getStatisticsPanel() {
		return ContentPanel.statisticsPanel;
	}
}
