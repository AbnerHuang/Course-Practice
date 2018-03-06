package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class ConstantUI {
	/**
	 * 软件名称
	 */
	public final static String APP_NAME = "NetworkFlow";
	/**
	 * 软件图标
	 */
	public final static Image APP_ICON = Toolkit.getDefaultToolkit().getImage("image/AppIcon.png");
	/**
	 * 主窗口大小
	 */
	public final static int MAIN_WINDOW_X = 0;
	
	public final static int MAIN_WINDOW_Y = 50;
	
	public final static int MAIN_WINDOW_WIDTH = 1900; //600;
	
	public final static int MAIN_WINDOW_HEIGHT = 1030; //450;
	/**
	 * 工具条大小
	 */
	public final static int TOOLBAR_WIDTH = 180;
	
	public final static int TOOLBAR_HEIGHT = MAIN_WINDOW_HEIGHT;
	/**
	 * 背景颜色
	 */
	public final static Color MAIN_BACKGROUND_COLOR = Color.WHITE;
	
	public final static Color TOOLBAR_BACKGROUND_COLOR = new Color(238, 245, 245);
	
	public final static Color FONT_COLOR = new Color(49, 194, 124);
	
	public final static Color TOOLBAR_ENTER_COLOR = new Color(229, 237, 238);
	
	public final static Color TOOLBAR_SELECT_COLOR = new Color(51, 238, 157);
	
	public final static Color CONTROL_BAR_COLOR = new Color(242, 242, 242);
	/**
	 * 字体
	 */
	public final static Font FONT_TITLE = new Font("微软雅黑",0 , 23);
	
	public final static Font FONT_MID = new Font("微软雅黑",0 , 15);

	public final static Font FONT_NORMAL = new Font("微软雅黑",0 , 13);
	//初始化界面图标
	public final static ImageIcon INITIAL_ICON = new ImageIcon("image/initialPanel.png");
	//网络流量默认
	public final static ImageIcon NETWORK_ICON = new ImageIcon("image/networkDisabled.jpg");
	//网络流量 激活
	public final static ImageIcon NETWORK_ICON_ENABLED = new ImageIcon("image/networkEnabled.jpg");
	//统计默认
	public final static ImageIcon STATISTICS_ICON = new ImageIcon("image/statisticsDisabled.jpg");
	//统计激活
	public final static ImageIcon STATISTICS_ICON_ENABLED = new ImageIcon("image/statisticsEnabled.jpg");
	//开始默认
	public final static ImageIcon START_ICON_NORMAL = new ImageIcon("image/startNormal.png");
	//开始激活
	public final static ImageIcon START_ICON_ENABLED = new ImageIcon("image/startEnabled.png");
	//开始失效
	public final static ImageIcon START_ICON_DISABLED = new ImageIcon("image/startDisabled.png");
	//停止默认
	public final static ImageIcon STOP_ICON_NORMAL = new ImageIcon("image/stopNormal.png");
	//停止激活
	public final static ImageIcon STOP_ICON_ENABLED = new ImageIcon("image/stopEnabled.png");
	//停止默认
	public final static ImageIcon STOP_ICON_DISABLED = new ImageIcon("image/stopDisabled.png");
	//捕捉默认
	public final static ImageIcon CAPTOR_ICON = new ImageIcon("image/captorDisabled.png");
	//捕捉激活
	public final static ImageIcon CAPTOR_ICON_ENABLED = new ImageIcon("image/captorEnabled.png");
	//统计图标默认
	public final static ImageIcon STAT_ICON = new ImageIcon("image/statDisabled.png");
	//统计图标激活
	public final static ImageIcon STAT_ICON_ENABLED = new ImageIcon("image/statEnabled.png");
	//返回默认
	public final static ImageIcon BACK_ICON = new ImageIcon("image/backDisabled.png");
	//返回激活
	public final static ImageIcon BACK_ICON_ENABLED = new ImageIcon("image/backEnabled.png");
	//保存默认
	public final static ImageIcon SAVE_ICON = new ImageIcon("image/saveDisabled.png");
	//保存激活
	public final static ImageIcon SAVE_ICON_ENABLED = new ImageIcon("image/saveEnabled.png");
}
