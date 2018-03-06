package UI.Panel;

import java.awt.Dimension;

import javax.swing.JTabbedPane;

import UI.ConstantUI;

public class StatisticsPanel extends JTabbedPane{
	
	public StatisticsPanel() {
		// TODO Auto-generated constructor stub
		initialize();
	}
	
	private void initialize() {
		Dimension preferredSize = new Dimension(ConstantUI.MAIN_WINDOW_WIDTH - ConstantUI.TOOLBAR_WIDTH - 50, 
				ConstantUI.MAIN_WINDOW_HEIGHT);
		this.setPreferredSize(preferredSize);
		this.setMaximumSize(preferredSize);
		this.setMinimumSize(preferredSize);
		this.setBackground(ConstantUI.MAIN_BACKGROUND_COLOR);
		this.add("����ͳ��", new NumStatisticsPanel());
		this.add("����ͳ��", new DataStatisticsPanel());
	}
}
