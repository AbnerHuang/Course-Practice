package UI.Panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

import UI.ConstantUI;
import jpcap.packet.ARPPacket;
import jpcap.packet.EthernetPacket;
import jpcap.packet.ICMPPacket;
import jpcap.packet.IPPacket;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;
import jpcap.packet.UDPPacket;

public class NetworkPanel extends JPanel{
	
	private ControlBarPanel controlBarPanel = null;
	private JPanel viewPanel;
	private static JTable captorTable = null;
	private static Vector tableData = null , colNames = null;
	private static Vector packetData = null;
	private DefaultTableModel tableModel = null;
	private static JScrollPane scrollPane = null,treeScrollPane = null;
	private JTree packetTree;
	private DefaultMutableTreeNode topNode;
	private DefaultTreeModel packetTreeModel;
	private DefaultTreeCellRenderer packetTreeRender;
	
	public NetworkPanel() {
		// TODO Auto-generated constructor stub
		initialize();
		addComponent();
		addListener();
	}
	
	private void initialize() {
		Dimension preferredSize = new Dimension(ConstantUI.MAIN_WINDOW_WIDTH - ConstantUI.TOOLBAR_WIDTH - 50, 
				ConstantUI.MAIN_WINDOW_HEIGHT);
		this.setPreferredSize(preferredSize);
		this.setMaximumSize(preferredSize);
		this.setMinimumSize(preferredSize);
		this.setBackground(ConstantUI.MAIN_BACKGROUND_COLOR);
		
	}

	private void addComponent() {
		setLayout(null);
		controlBarPanel = new ControlBarPanel();
		controlBarPanel.setBounds(0, 0, ConstantUI.MAIN_WINDOW_WIDTH - ConstantUI.TOOLBAR_WIDTH - 50, 48);
		this.add(controlBarPanel);
		
		packetData = new Vector();
		
		tableData = new Vector();
		
		colNames = new Vector();
		colNames.addElement("No.");
		colNames.add("Source");
		colNames.add("Destination");
		colNames.add("Protocol");
		colNames.add("Length");
		colNames.add("Info");
		
		tableModel = new DefaultTableModel(tableData, colNames);
		
		captorTable = new JTable(tableModel);
		captorTable.setGridColor(ConstantUI.MAIN_BACKGROUND_COLOR);
		captorTable.setFont(ConstantUI.FONT_NORMAL);
		captorTable.getTableHeader().setFont(ConstantUI.FONT_NORMAL);
		captorTable.getTableHeader().setBackground(ConstantUI.FONT_COLOR);
		captorTable.setSelectionBackground(ConstantUI.FONT_COLOR);
		captorTable.setRowHeight(31);
		captorTable.getColumnModel().getColumn(0).setPreferredWidth(80);
		captorTable.getColumnModel().getColumn(1).setPreferredWidth(160);
		captorTable.getColumnModel().getColumn(2).setPreferredWidth(160);
		captorTable.getColumnModel().getColumn(3).setPreferredWidth(160);
		captorTable.getColumnModel().getColumn(4).setPreferredWidth(160);
		captorTable.getColumnModel().getColumn(5).setPreferredWidth(1000);
		
		viewPanel = new JPanel();
		viewPanel.setBounds(0, 98, ConstantUI.MAIN_WINDOW_WIDTH - 300, ConstantUI.MAIN_WINDOW_HEIGHT);
		viewPanel.setBackground(ConstantUI.MAIN_BACKGROUND_COLOR);
		
		scrollPane = new JScrollPane(captorTable);
		scrollPane.getViewport().setBackground(ConstantUI.MAIN_BACKGROUND_COLOR);
		Dimension scrollSize = new Dimension(ConstantUI.MAIN_WINDOW_WIDTH - 400, 
				(ConstantUI.MAIN_WINDOW_HEIGHT - 300)/2);
		scrollPane.getViewport().setMinimumSize(scrollSize);
		scrollPane.getViewport().setMaximumSize(scrollSize);
		scrollPane.getViewport().setPreferredSize(scrollSize);
		viewPanel.add(scrollPane);
		
		
		
		topNode = new DefaultMutableTreeNode("���ݰ�����");
		packetTreeModel = new DefaultTreeModel(topNode);
		packetTree = new JTree(packetTreeModel);
		packetTree.setRowHeight(18);
		packetTreeRender = (DefaultTreeCellRenderer)packetTree.getCellRenderer();
		packetTreeRender.setFont(ConstantUI.FONT_MID);
		packetTreeRender.setBackgroundNonSelectionColor(ConstantUI.TOOLBAR_ENTER_COLOR);
		
		treeScrollPane = new JScrollPane(packetTree);
		treeScrollPane.getViewport().setBackground(ConstantUI.MAIN_BACKGROUND_COLOR);
		treeScrollPane.getViewport().setPreferredSize(scrollSize);
		treeScrollPane.getViewport().setMinimumSize(scrollSize);
		treeScrollPane.getViewport().setMaximumSize(scrollSize);
		viewPanel.add(treeScrollPane);
		
		
		
		
		this.add(viewPanel);
	}
	
	private void addListener() {
		captorTable.addMouseListener(new MouseListener() {
			
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
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				topNode.removeAllChildren();
				Vector packetVector = (Vector)packetData.get(captorTable.getSelectedRow());
				Packet refreshPacket = (Packet)packetVector.get(1);
				refereshJtree(refreshPacket);
				packetTree.updateUI();
			}
		});
	}
	
	public static Vector getTableData() {
		return NetworkPanel.tableData;
	}
	
	public static JTable getCaptorTable() {
		return NetworkPanel.captorTable;
	}
	
	public static Vector getPacketData() {
		return NetworkPanel.packetData;
	}
	
	private void refereshJtree(Packet packet) {
		System.out.println(packet.toString());
		DefaultMutableTreeNode ethernetNode = new DefaultMutableTreeNode("Ethernet");
		ethernetNode.add(new DefaultMutableTreeNode("��׽����"+packet.caplen));
		ethernetNode.add(new DefaultMutableTreeNode("���ݣ�"+new String(packet.data)));
		ethernetNode.add(new DefaultMutableTreeNode("��̫֡��ͷ:"+new String(packet.header)));
		ethernetNode.add(new DefaultMutableTreeNode("ʱ���(��):"+packet.sec));
		ethernetNode.add(new DefaultMutableTreeNode("���ݰ�������"+packet.toString()));
		topNode.add(ethernetNode);
		
		if(packet instanceof IPPacket) {
			IPPacket ipPacket = (IPPacket)packet;
			DefaultMutableTreeNode ipNode = new DefaultMutableTreeNode("IP");
			ipNode.add(new DefaultMutableTreeNode("�汾�� "+ipPacket.protocol));
			ipNode.add(new DefaultMutableTreeNode("�ܳ���:" + ipPacket.caplen));
			ipNode.add(new DefaultMutableTreeNode("�����ʶ:" + ipPacket.ident));
			ipNode.add(new DefaultMutableTreeNode("RF: "+ipPacket.rsv_frag));
			ipNode.add(new DefaultMutableTreeNode("DF: "+ipPacket.dont_frag));
			ipNode.add(new DefaultMutableTreeNode("MF: "+ipPacket.more_frag));
			ipNode.add(new DefaultMutableTreeNode("Э������:"+ipPacket.protocol));
			ipNode.add(new DefaultMutableTreeNode("Դ��ַ�� "+ipPacket.src_ip));
			ipNode.add(new DefaultMutableTreeNode("Ŀ�ĵ�ַ: "+ipPacket.dst_ip));
			
			topNode.add(ipNode);
			switch (ipPacket.protocol) {
			case 17:
				if(ipPacket instanceof ICMPPacket) {
					ICMPPacket icmpPacket = (ICMPPacket)ipPacket;
					topNode.add(dealICMPPacket(icmpPacket));
				}else if(ipPacket instanceof UDPPacket) {
					UDPPacket udpPacket = (UDPPacket)ipPacket;
					topNode.add(dealUDPPacket(udpPacket));
				}
				break;

			case 6:
				TCPPacket tcpPacket = (TCPPacket)ipPacket;
				topNode.add(dealTCPPacket(tcpPacket));
				break;
			
			default:
				break;
			}
			
		}
		else if(packet instanceof ARPPacket) {
			ARPPacket arpPacket = (ARPPacket)packet;
			DefaultMutableTreeNode arpNode = new DefaultMutableTreeNode("ARP");
			arpNode.add(new DefaultMutableTreeNode("Ӳ������: "+arpPacket.hardtype));
			arpNode.add(new DefaultMutableTreeNode("Э������: "+arpPacket.prototype));
			arpNode.add(new DefaultMutableTreeNode("Ӳ����ַ����: "+arpPacket.hlen));
			arpNode.add(new DefaultMutableTreeNode("Э���ַ����: "+arpPacket.plen));
			switch (arpPacket.operation) {
			case 1:
				arpNode.add(new DefaultMutableTreeNode("������: "+arpPacket.operation+" (ARP����)"));
				break;
			case 2:
				arpNode.add(new DefaultMutableTreeNode("������: "+arpPacket.operation+" (ARPӦ��)"));
				break;
			case 3:
				arpNode.add(new DefaultMutableTreeNode("������: "+arpPacket.operation+" (RARP����)"));
				break;
			case 4:
				arpNode.add(new DefaultMutableTreeNode("������: "+arpPacket.operation+" (RARPӦ��)"));
				break;
			case 8:
				arpNode.add(new DefaultMutableTreeNode("������: "+arpPacket.operation+" (Identify peer����)"));
				break;
			case 9:
				arpNode.add(new DefaultMutableTreeNode("������: "+arpPacket.operation+" (Identify peerӦ��)"));
				break;

			default:
				arpNode.add(new DefaultMutableTreeNode("������: "+arpPacket.operation+" (UNKOWN)"));
				break;
			}
			arpNode.add(new DefaultMutableTreeNode("������: "+arpPacket.operation));
			arpNode.add(new DefaultMutableTreeNode("���Ͷ���̫��ַ : " + arpPacket.getSenderHardwareAddress()));
			arpNode.add(new DefaultMutableTreeNode("���Ͷ�IP��ַ : " + arpPacket.getSenderProtocolAddress()));
			arpNode.add(new DefaultMutableTreeNode("Ŀ�Ķ���̫��ַ : " + arpPacket.getTargetHardwareAddress()));
			arpNode.add(new DefaultMutableTreeNode("Ŀ�Ķ�IP��ַ : " + arpPacket.getTargetProtocolAddress()));
			
			topNode.add(arpNode);
			
		}

		packetTreeRender = (DefaultTreeCellRenderer)packetTree.getCellRenderer();
		packetTreeRender.setFont(ConstantUI.FONT_MID);
		packetTreeRender.setBackgroundNonSelectionColor(ConstantUI.TOOLBAR_ENTER_COLOR);
		packetTreeModel.reload();
		packetTree.updateUI();
	}
	
	private DefaultMutableTreeNode dealICMPPacket(ICMPPacket icmpPacket) {
		DefaultMutableTreeNode icmpNode = new DefaultMutableTreeNode("ICMP");
		icmpNode.add(new DefaultMutableTreeNode("���ݰ����ͣ� "+icmpPacket.type));
		icmpNode.add(new DefaultMutableTreeNode("���ݰ����룺 "+icmpPacket.code));
		
		return icmpNode;
	}
	
	private DefaultMutableTreeNode dealTCPPacket(TCPPacket tcpPacket) {
		DefaultMutableTreeNode tcpNode = new DefaultMutableTreeNode("TCP");
		tcpNode.add(new DefaultMutableTreeNode("Դ�˿ڣ� "+ tcpPacket.src_port));
		tcpNode.add(new DefaultMutableTreeNode("Ŀ�Ķ˿ڣ� "+tcpPacket.dst_port));
		tcpNode.add(new DefaultMutableTreeNode("���к�:" + tcpPacket.sequence));
		tcpNode.add(new DefaultMutableTreeNode("URG��־: " + tcpPacket.urg));
		tcpNode.add(new DefaultMutableTreeNode("ACK��־:" +tcpPacket.ack));
		tcpNode.add(new DefaultMutableTreeNode("PSH��־��" +tcpPacket.psh));
		tcpNode.add(new DefaultMutableTreeNode("RST��־: "+tcpPacket.rst));
		tcpNode.add(new DefaultMutableTreeNode("SYN��־�� "+tcpPacket.syn));
		tcpNode.add(new DefaultMutableTreeNode("FIN��־:" + tcpPacket.fin));
		tcpNode.add(new DefaultMutableTreeNode("Windows��С: "+tcpPacket.window));
		
		return tcpNode;
	}
	
	private DefaultMutableTreeNode dealUDPPacket(UDPPacket udpPacket) {
		DefaultMutableTreeNode udpNode = new DefaultMutableTreeNode("UDP");
		udpNode.add(new DefaultMutableTreeNode("Դ�˿�: "+udpPacket.src_port));
		udpNode.add(new DefaultMutableTreeNode("Ŀ�Ķ˿�:"+udpPacket.dst_port));
		udpNode.add(new DefaultMutableTreeNode("UDP����: "+udpPacket.caplen));
		
		return udpNode;
	}

}
