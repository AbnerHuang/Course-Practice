package UI.Panel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import UI.ConstantUI;
import jpcap.NetworkInterface;

public class InterfacePanel extends JPanel {
	private NetworkInterface device;
	private JLabel jLabel;
	
	public InterfacePanel(NetworkInterface device) {
		// TODO Auto-generated constructor stub
		this.device = device;
		
		this.setBackground(ConstantUI.MAIN_BACKGROUND_COLOR);
		
		jLabel = new JLabel(device.description);
		jLabel.setFont(ConstantUI.FONT_TITLE);
		jLabel.setForeground(ConstantUI.FONT_COLOR);
		this.add(jLabel);
	}
	
	public NetworkInterface getDevice() {
		System.out.println(device.description);
		return this.device;
	}
}
