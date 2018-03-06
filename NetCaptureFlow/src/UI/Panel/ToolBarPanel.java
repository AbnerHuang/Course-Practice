package UI.Panel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import UI.AppMainWindow;
import UI.ConstantUI;
import UI.IconButton;

public class ToolBarPanel extends JPanel{
//	private IconButton networkButton;
//	private IconButton statisticButton;
	
	private JLabel captorIcon;
	private JLabel statisticIcon;
	private JLabel captorLabel;
	private JLabel statisticLabel;
	
	private JPanel captorPanel;
	private JPanel statPanel;
	
	public ToolBarPanel() {
		// TODO Auto-generated constructor stub
		initialize();
		addComponent();
		addListener();
//		addButton();
//		addListener();
	}
	
	private void initialize(){
		Dimension preferredSize = new Dimension(ConstantUI.TOOLBAR_WIDTH,
				ConstantUI.TOOLBAR_HEIGHT);
		this.setMaximumSize(preferredSize);
		this.setMinimumSize(preferredSize);
		this.setPreferredSize(preferredSize);
		this.setBackground(ConstantUI.TOOLBAR_BACKGROUND_COLOR);
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
	}
	
	private void addComponent() {
		captorIcon = new JLabel(ConstantUI.CAPTOR_ICON);
		captorLabel = new JLabel("数据包捕捉");
		statisticIcon = new JLabel(ConstantUI.STAT_ICON);
		statisticLabel = new JLabel("数据包分析");
		
		captorLabel.setFont(ConstantUI.FONT_TITLE);
		captorLabel.setForeground(ConstantUI.FONT_COLOR);
		
		statisticLabel.setFont(ConstantUI.FONT_TITLE);
		statisticLabel.setForeground(ConstantUI.FONT_COLOR);
		
		captorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		captorPanel.setBackground(ConstantUI.TOOLBAR_BACKGROUND_COLOR);
		captorPanel.add(captorIcon);
		captorPanel.add(captorLabel);
		
		statPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		statPanel.setBackground(ConstantUI.TOOLBAR_BACKGROUND_COLOR);
		statPanel.add(statisticIcon);
		statPanel.add(statisticLabel);
		
		this.add(captorPanel);
		this.add(statPanel);
		
	}
	
	private void addListener() {
		captorPanel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				captorIcon.setIcon(ConstantUI.CAPTOR_ICON);
				captorLabel.setForeground(ConstantUI.FONT_COLOR);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				captorPanel.setBackground(ConstantUI.FONT_COLOR);
				captorIcon.setIcon(ConstantUI.CAPTOR_ICON_ENABLED);
				captorLabel.setForeground(ConstantUI.MAIN_BACKGROUND_COLOR);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				captorPanel.setBackground(ConstantUI.TOOLBAR_BACKGROUND_COLOR);
				captorIcon.setIcon(ConstantUI.CAPTOR_ICON);
				captorLabel.setForeground(ConstantUI.FONT_COLOR);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				captorPanel.setBackground(ConstantUI.TOOLBAR_ENTER_COLOR);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				captorPanel.setBackground(ConstantUI.FONT_COLOR);
				captorIcon.setIcon(ConstantUI.CAPTOR_ICON_ENABLED);
				captorLabel.setForeground(ConstantUI.MAIN_BACKGROUND_COLOR);
				ContentPanel.getMainPanelCenter().removeAll();
				ContentPanel.getMainPanelCenter().add(ContentPanel.getNetworkPanel());
				ContentPanel.getMainPanelCenter().updateUI();
			}
		});
		
		statPanel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				statPanel.setBackground(ConstantUI.TOOLBAR_BACKGROUND_COLOR);
				statisticIcon.setIcon(ConstantUI.STAT_ICON);
				statisticLabel.setForeground(ConstantUI.FONT_COLOR);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				statPanel.setBackground(ConstantUI.TOOLBAR_ENTER_COLOR);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				statPanel.setBackground(ConstantUI.FONT_COLOR);
				statisticIcon.setIcon(ConstantUI.STAT_ICON_ENABLED);
				statisticLabel.setForeground(ConstantUI.MAIN_BACKGROUND_COLOR);
				ContentPanel.getMainPanelCenter().removeAll();
				ContentPanel.getMainPanelCenter().add(new StatisticsPanel());
				ContentPanel.getMainPanelCenter().updateUI();
			}
		});
	}
	
//	public void addButton() {
//		networkButton = new IconButton(ConstantUI.NETWORK_ICON, ConstantUI.NETWORK_ICON_ENABLED, 
//				ConstantUI.NETWORK_ICON, "网络流量分析");
//		statisticButton = new IconButton(ConstantUI.STATISTICS_ICON, ConstantUI.STATISTICS_ICON_ENABLED, 
//				ConstantUI.STATISTICS_ICON, "统计");
//		this.add(networkButton);
//		this.add(statisticButton);
//	}
//
//	public void addListener() {
//		networkButton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//		
//		statisticButton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//	}
}
