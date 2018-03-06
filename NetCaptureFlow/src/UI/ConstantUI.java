package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class ConstantUI {
	/**
	 * �������
	 */
	public final static String APP_NAME = "NetworkFlow";
	/**
	 * ���ͼ��
	 */
	public final static Image APP_ICON = Toolkit.getDefaultToolkit().getImage("image/AppIcon.png");
	/**
	 * �����ڴ�С
	 */
	public final static int MAIN_WINDOW_X = 0;
	
	public final static int MAIN_WINDOW_Y = 50;
	
	public final static int MAIN_WINDOW_WIDTH = 1900; //600;
	
	public final static int MAIN_WINDOW_HEIGHT = 1030; //450;
	/**
	 * ��������С
	 */
	public final static int TOOLBAR_WIDTH = 180;
	
	public final static int TOOLBAR_HEIGHT = MAIN_WINDOW_HEIGHT;
	/**
	 * ������ɫ
	 */
	public final static Color MAIN_BACKGROUND_COLOR = Color.WHITE;
	
	public final static Color TOOLBAR_BACKGROUND_COLOR = new Color(238, 245, 245);
	
	public final static Color FONT_COLOR = new Color(49, 194, 124);
	
	public final static Color TOOLBAR_ENTER_COLOR = new Color(229, 237, 238);
	
	public final static Color TOOLBAR_SELECT_COLOR = new Color(51, 238, 157);
	
	public final static Color CONTROL_BAR_COLOR = new Color(242, 242, 242);
	/**
	 * ����
	 */
	public final static Font FONT_TITLE = new Font("΢���ź�",0 , 23);
	
	public final static Font FONT_MID = new Font("΢���ź�",0 , 15);

	public final static Font FONT_NORMAL = new Font("΢���ź�",0 , 13);
	//��ʼ������ͼ��
	public final static ImageIcon INITIAL_ICON = new ImageIcon("image/initialPanel.png");
	//��������Ĭ��
	public final static ImageIcon NETWORK_ICON = new ImageIcon("image/networkDisabled.jpg");
	//�������� ����
	public final static ImageIcon NETWORK_ICON_ENABLED = new ImageIcon("image/networkEnabled.jpg");
	//ͳ��Ĭ��
	public final static ImageIcon STATISTICS_ICON = new ImageIcon("image/statisticsDisabled.jpg");
	//ͳ�Ƽ���
	public final static ImageIcon STATISTICS_ICON_ENABLED = new ImageIcon("image/statisticsEnabled.jpg");
	//��ʼĬ��
	public final static ImageIcon START_ICON_NORMAL = new ImageIcon("image/startNormal.png");
	//��ʼ����
	public final static ImageIcon START_ICON_ENABLED = new ImageIcon("image/startEnabled.png");
	//��ʼʧЧ
	public final static ImageIcon START_ICON_DISABLED = new ImageIcon("image/startDisabled.png");
	//ֹͣĬ��
	public final static ImageIcon STOP_ICON_NORMAL = new ImageIcon("image/stopNormal.png");
	//ֹͣ����
	public final static ImageIcon STOP_ICON_ENABLED = new ImageIcon("image/stopEnabled.png");
	//ֹͣĬ��
	public final static ImageIcon STOP_ICON_DISABLED = new ImageIcon("image/stopDisabled.png");
	//��׽Ĭ��
	public final static ImageIcon CAPTOR_ICON = new ImageIcon("image/captorDisabled.png");
	//��׽����
	public final static ImageIcon CAPTOR_ICON_ENABLED = new ImageIcon("image/captorEnabled.png");
	//ͳ��ͼ��Ĭ��
	public final static ImageIcon STAT_ICON = new ImageIcon("image/statDisabled.png");
	//ͳ��ͼ�꼤��
	public final static ImageIcon STAT_ICON_ENABLED = new ImageIcon("image/statEnabled.png");
	//����Ĭ��
	public final static ImageIcon BACK_ICON = new ImageIcon("image/backDisabled.png");
	//���ؼ���
	public final static ImageIcon BACK_ICON_ENABLED = new ImageIcon("image/backEnabled.png");
	//����Ĭ��
	public final static ImageIcon SAVE_ICON = new ImageIcon("image/saveDisabled.png");
	//���漤��
	public final static ImageIcon SAVE_ICON_ENABLED = new ImageIcon("image/saveEnabled.png");
}
