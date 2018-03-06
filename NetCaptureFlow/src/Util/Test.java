package Util;
import java.io.UnsupportedEncodingException;

import jpcap.*;
import jpcap.packet.*;

public class Test {
	public static void main(String[] args) {
		System.out.println(System.getProperty("java.library.path"));
		while(true) {
		try {
			final NetworkInterface[] devices = JpcapCaptor.getDeviceList();
			for(int i = 0;i<devices.length;i++) {
				NetworkInterface networkInterface = devices[i];
				JpcapCaptor jpcap = JpcapCaptor.openDevice(networkInterface, 2000,
						true, 20);
				System.out.println(i +""+devices[i].description);
				startCapThread(jpcap);
				
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		}
	}
	
	public static void startCapThread(final JpcapCaptor jpcapCaptor) {
		JpcapCaptor jp = jpcapCaptor;
		java.lang.Runnable runnable = new Runnable() {
			public void run() {
				jpcapCaptor.loopPacket(-1, new TestPacketReceiver());
			}
		};
		new Thread(runnable).start();
	}
}

class TestPacketReceiver implements PacketReceiver{

	@Override
	public void receivePacket(Packet arg0) {
		// TODO Auto-generated method stub
		if(arg0 instanceof TCPPacket) {
			TCPPacket tcpPacket = (TCPPacket)arg0;
			System.out.println(tcpPacket.dst_ip);
			System.out.println(tcpPacket.protocol);
		}
		else if(arg0 instanceof UDPPacket) {
			UDPPacket udpPacket = (UDPPacket)arg0;
			System.out.println(udpPacket.dst_ip);
			System.out.println(udpPacket.protocol);
		}
		else if(arg0 instanceof ICMPPacket) {
			ICMPPacket icmpPacket = (ICMPPacket)arg0;
			System.out.println(icmpPacket.len);
			System.out.println(icmpPacket.protocol);
		}
		else if(arg0 instanceof ARPPacket) {
			try {
				System.out.println("ARP" + new String(arg0.header,"GBK"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
