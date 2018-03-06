package Util;

import UI.Panel.InitialPanel;
import jpcap.JpcapCaptor;

public class NetCaptor {
	private JpcapCaptor jpcapCaptor;
	private Thread jpcapThread;
	private volatile boolean canRun;
	
	private NetPacketReceiver netPacketReceiver;
	
	public void capturePacket() {
		netPacketReceiver = new NetPacketReceiver();
		
		if(jpcapCaptor != null)
			jpcapCaptor.close();
		
		try {
		jpcapCaptor = JpcapCaptor.openDevice(InitialPanel.getNetworkInterface(), 2000, 
				InitialPanel.getPromisc(), 20);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(jpcapCaptor != null) {
			canRun = true;
			NetPacketReceiver.setSeqNumber();
			NetPacketReceiver.setPacketNum();
			NetPacketReceiver.setPacketSize();
			startCapture();
		}
	}
	
	private void startCapture() {
		if(jpcapThread != null)
			return;
		jpcapThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(canRun == true)
				{
					jpcapCaptor.processPacket(1, netPacketReceiver);
				}
			}
		});
		
		jpcapThread.start();
	}
	
	public void stopCapture() {
		stopCaptureThread();
	}
	
	public void stopCaptureThread() {
		canRun = false;
		jpcapThread = null;
	}
}
