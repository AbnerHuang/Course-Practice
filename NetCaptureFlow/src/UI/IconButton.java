package UI;

import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class IconButton extends JButton {
	/**
	 * 自定义按钮类，支持自定义默认图标、失效图标、激活图标和tip提示
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
	 * 初始化，设置按钮无边，无焦点渲染，无内容区，各边边距为0
	 */
	private void initialize(){
		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
		this.setFocusable(true);
		this.setMargin(new Insets(0, 0, 0, 0));
	}
	
	/**
	 * 设置鼠标移过、按压、失效的图标与设置按钮提示
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
