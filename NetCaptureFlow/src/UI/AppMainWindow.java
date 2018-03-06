package UI;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import UI.Panel.ContentPanel;
import UI.Panel.InitialPanel;
import UI.Panel.NetworkPanel;
import UI.Panel.StatisticsPanel;
import UI.Panel.ToolBarPanel;
/**
 * ����������
 * @author �D
 *
 */
public class AppMainWindow extends JFrame{
	private static JPanel mainPanel;
	
	private static InitialPanel initialPanel;
	private static ContentPanel contentPanel;
	
	public AppMainWindow() {
		// TODO Auto-generated constructor stub
		getContentPane().setBackground(ConstantUI.MAIN_BACKGROUND_COLOR);
		initialize();
	}
	
	public void initialize() {
//		��ʼ����������ʼ��ϵͳ����������ɫ�����ֵȲ�������ɸ��������ĳ�ʼ��
		try {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		����ϵͳUI����
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		this.setIconImage(ConstantUI.APP_ICON);
		this.setBackground(ConstantUI.MAIN_BACKGROUND_COLOR);
		this.setBounds(ConstantUI.MAIN_WINDOW_X, ConstantUI.MAIN_WINDOW_Y, 
				ConstantUI.MAIN_WINDOW_WIDTH, ConstantUI.MAIN_WINDOW_HEIGHT);
		this.setTitle(ConstantUI.APP_NAME);
//		���ò���
		
		mainPanel = new JPanel(true);
		mainPanel.setBackground(ConstantUI.MAIN_BACKGROUND_COLOR);
		
		initialPanel = new InitialPanel();
		contentPanel = new ContentPanel();
		
		mainPanel.add(initialPanel);
		this.add(mainPanel);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static JPanel getMainPanel() {
//		���س������Ҫ����
		return AppMainWindow.mainPanel;
	}
	
	public static InitialPanel getInitialPanel() {
		return AppMainWindow.initialPanel;
	}
	
	public static ContentPanel getContentPanel() {
		return AppMainWindow.contentPanel;
	}
	
	public static void main(String[] args) {
		new AppMainWindow();
	}
}
