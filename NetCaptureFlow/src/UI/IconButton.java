package UI;

import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class IconButton extends JButton {
	/**
	 * �Զ��尴ť�֧࣬���Զ���Ĭ��ͼ�ꡢʧЧͼ�ꡢ����ͼ���tip��ʾ
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon enabledIcon,disabledIcon;
	private String tip;
	
	public IconButton(ImageIcon normalIcon,ImageIcon enabledIcon,ImageIcon disabledIcon,String tip) {
		super(normalIcon);
		
		this.disabledIcon = disabledIcon;
		this.enabledIcon = enabledIcon;
		this.tip = tip;
		
		// TODO Auto-generated constructor stub
		initialize();
		setUp();
	}
	
	/**
	 * ��ʼ�������ð�ť�ޱߣ��޽�����Ⱦ���������������߱߾�Ϊ0
	 */
	private void initialize(){
		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
		this.setFocusable(true);
		this.setMargin(new Insets(0, 0, 0, 0));
	}
	
	/**
	 * ��������ƹ�����ѹ��ʧЧ��ͼ�������ð�ť��ʾ
	 */
	private void setUp(){
		this.setRolloverIcon(enabledIcon);
		this.setPressedIcon(enabledIcon);
		this.setDisabledIcon(disabledIcon);
		
		if(!tip.equals("")){
			this.setToolTipText(tip);
		}
	}
}
