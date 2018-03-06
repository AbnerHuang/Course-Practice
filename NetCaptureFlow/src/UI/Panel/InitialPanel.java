package UI.Panel;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import UI.AppMainWindow;
import UI.ConstantUI;
import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;

public class InitialPanel extends JPanel{
	private NetworkInterface[] devices;
	private InterfacePanel[] panels;
	private static JRadioButton promiscButton;
	private static boolean isPromisc = false;
	private int num;
	
	private static NetworkInterface selectedDevice;
	
	public InitialPanel() {
		// TODO Auto-generated constructor stub
		initialize();
		addListener();
	}
	
	private void initialize() {
		
		this.setBackground(ConstantUI.MAIN_BACKGROUND_COLOR);
		this.setLayout(new GridLayout(6,1));
		
		this.add(new JLabel(ConstantUI.INITIAL_ICON));
		
		promiscButton = new JRadioButton("  工  作  为  混  淆  模  式  ?");
		promiscButton.setBackground(ConstantUI.MAIN_BACKGROUND_COLOR);
		promiscButton.setForeground(ConstantUI.FONT_COLOR);
		promiscButton.setFont(ConstantUI.FONT_TITLE);
		this.add(promiscButton);
		
		devices = JpcapCaptor.getDeviceList();
		num = devices.length;
		panels = new InterfacePanel[num];
		
		for(int i = 0;i < num;i++) {
			panels[i] = new InterfacePanel(devices[i]);
			panels[i].setBackground(ConstantUI.MAIN_BACKGROUND_COLOR);
			this.add(panels[i]);
		}
	}
	
	private void addListener() {
		for(int i = 0;i < num;i++) {
			InterfacePanel currentPanel = panels[i];
			currentPanel.addMouseListener(new MouseListener() {
				
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
					currentPanel.setBackground(ConstantUI.MAIN_BACKGROUND_COLOR);
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					currentPanel.setBackground(ConstantUI.TOOLBAR_ENTER_COLOR);
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					selectedDevice = currentPanel.getDevice();
					AppMainWindow.getMainPanel().removeAll();
					AppMainWindow.getMainPanel().add(AppMainWindow.getContentPanel());
					AppMainWindow.getMainPanel().updateUI();
				}
			});
		}
		
		promiscButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				isPromisc = promiscButton.isSelected();
			}
		});
		
	}
	
	public static NetworkInterface getNetworkInterface() {
		return InitialPanel.selectedDevice;
	}
	
	public static boolean getPromisc() {
		if(isPromisc == true) {
			System.out.println("true");
		}else {
			System.out.println("false");
		}
		return isPromisc;
	}
}
