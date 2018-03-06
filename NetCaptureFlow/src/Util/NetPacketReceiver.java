package Util;


import java.awt.Color;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.sun.xml.internal.bind.v2.model.core.ID;

import UI.Panel.InitialPanel;
import UI.Panel.NetworkPanel;
import jpcap.NetworkInterfaceAddress;
import jpcap.PacketReceiver;
import jpcap.packet.ARPPacket;
import jpcap.packet.ICMPPacket;
import jpcap.packet.IPPacket;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;

public class NetPacketReceiver implements PacketReceiver{
	private static int seqNumber = 0;
	
	private static int TCPPacketNum = 0;
	private static int UDPPacketNum = 0;
	private static int ARPPacketNum = 0;
	private static int ICMPPacketNum = 0;
	private static int OthersPacketNum = 0;
	
	private InetAddress[] ipArr = new Inet4Address[10];
	
	private static int upLoadPacketSize = 0;
	private static int downLoadPacketSize = 0;
	private static int upLoadIPPacketSize = 0;
	private static int downLoadIPPacketSize = 0;
	private static int upLoadTCPPacketSize = 0;
	private static int downLoadTCPPacketSize = 0;
	private static int upLoadUDPPacketSize = 0;
	private static int downLoadUDPPacketSize = 0;
	
	public NetPacketReceiver() {
		// TODO Auto-generated constructor stub
		try {
		int seqNo = 0;
		Enumeration<NetworkInterface> interfs = NetworkInterface.getNetworkInterfaces();
		while(interfs.hasMoreElements()) {
			NetworkInterface interf = interfs.nextElement();
			Enumeration<InetAddress> address = interf.getInetAddresses();
			while(address.hasMoreElements()) {
				InetAddress inetAddress = address.nextElement();
				if(inetAddress instanceof Inet4Address) {
					ipArr[seqNo] = inetAddress;
					System.out.println(ipArr[seqNo]);
					seqNo++;
				}
			}
		}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public void receivePacket(Packet packet) {
		// TODO Auto-generated method stub
		if(packet instanceof IPPacket || packet instanceof ARPPacket) {
		Packet receivedPacket = packet;
		seqNumber++;
		Vector packetVector = new Vector();
		Vector r = new Vector();
		r.add(seqNumber);
		packetVector.add(seqNumber);
		packetVector.add(packet);
		NetworkPanel.getPacketData().add(packetVector);
		if(receivedPacket instanceof IPPacket) {
			IPPacket ipPacket = (IPPacket)receivedPacket;
			
			r.add(ipPacket.src_ip);
			r.add(ipPacket.dst_ip);
			if(isMatch(ipPacket.src_ip, ipArr)) {
				upLoadIPPacketSize += ipPacket.len;
				upLoadPacketSize += ipPacket.len;
//				System.out.println("successful");
			}else if(isMatch(ipPacket.dst_ip, ipArr)) {
				downLoadIPPacketSize += ipPacket.len;
				downLoadPacketSize += ipPacket.len;
//				System.out.println("successful");
			}
			if(ipPacket.protocol == 6) {
				r.add("TCP");
				TCPPacketNum++;
				if(isMatch(ipPacket.src_ip, ipArr)) {
					upLoadTCPPacketSize+=ipPacket.len;
				}else if(isMatch(ipPacket.dst_ip, ipArr)) {
					downLoadTCPPacketSize+=ipPacket.len;
				}
			}
			else if(ipPacket.protocol == 17) {
				r.add("UDP");
				UDPPacketNum++;
				if(isMatch(ipPacket.src_ip, ipArr)) {
					upLoadUDPPacketSize+=ipPacket.len;
				}else if(isMatch(ipPacket.dst_ip, ipArr)) {
					downLoadUDPPacketSize+=ipPacket.len;
				}

			}
			else if(ipPacket.protocol == 1) {
				r.add("ICMP");
				ICMPPacketNum++;
			}
			else {
				r.add("Others");
				OthersPacketNum++;
			}
			r.add(ipPacket.length);
			r.add(ipPacket.toString());
			
		}else if(receivedPacket instanceof ARPPacket) {
			ARPPacket arpPacket = (ARPPacket)receivedPacket;
			r.add(arpPacket.getSenderProtocolAddress());
			r.add(arpPacket.getTargetProtocolAddress());
			r.add("ARP");
			ARPPacketNum++;
			r.add(arpPacket.len);
			r.add(arpPacket.toString());
			
			
		}NetworkPanel.getTableData().add(r);
			NetworkPanel.getCaptorTable().addNotify();
			}
		else {
				System.out.println(packet.toString());
			}
		}
	
	private boolean isMatch(InetAddress srcIP,InetAddress[] ipArr) {
		int length = ipArr.length;
		for(int i =0;i < length;i++) {
			if(srcIP.equals(ipArr[i]))
			{
//				System.out.println("successful");
				return true;
			}
		}
		return false;
	}
	
	public static void setSeqNumber() {
		seqNumber = 0;
	}
	
	public static void setPacketNum() {
		TCPPacketNum = 0;
		UDPPacketNum = 0;
		ARPPacketNum = 0;
		ICMPPacketNum = 0;
		OthersPacketNum = 0;
	}
	
	public static void setPacketSize() {
		upLoadPacketSize = 0;
		downLoadPacketSize = 0;
		upLoadIPPacketSize = 0;
		downLoadIPPacketSize = 0;
		upLoadTCPPacketSize = 0;
		downLoadTCPPacketSize = 0;
		upLoadUDPPacketSize = 0;
		downLoadUDPPacketSize = 0;
	}
	
	public static int getTCPNum() {
		return NetPacketReceiver.TCPPacketNum;
	}
	public static int getUDPNum() {
		return NetPacketReceiver.UDPPacketNum;
	}
	public static int getICMPNum() {
		return NetPacketReceiver.ICMPPacketNum;
	}
	public static int getARPNum() {
		return NetPacketReceiver.ARPPacketNum;
	}
	public static int getOthers() {
		return NetPacketReceiver.OthersPacketNum;
	}
	public static int getUpLoadPacket() {
		return NetPacketReceiver.upLoadPacketSize;
	}
	public static int getUpLoadIPPacket() {
		return NetPacketReceiver.upLoadIPPacketSize;
	}
	public static int getUpLoadTCPPacket() {
		return NetPacketReceiver.upLoadTCPPacketSize;
	}
	public static int getUpLoadUDPPacket() {
		return NetPacketReceiver.upLoadUDPPacketSize;
	}
	public static int getDownLoadPacket() {
		return NetPacketReceiver.downLoadPacketSize;
	}
	public static int getDownLoadIPPacket() {
		return NetPacketReceiver.downLoadIPPacketSize;
	}
	public static int getDownLoadTCPPacket() {
		return NetPacketReceiver.downLoadTCPPacketSize;
	}
	public static int getDownLoadUDPPacket() {
		return NetPacketReceiver.downLoadUDPPacketSize;
	}
}
