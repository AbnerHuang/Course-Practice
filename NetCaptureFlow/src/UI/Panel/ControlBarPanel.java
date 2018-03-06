package UI.Panel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import UI.AppMainWindow;
import UI.ConstantUI;
import UI.IconButton;
import Util.NetCaptor;
import Util.NetPacketReceiver;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ControlBarPanel extends JPanel{
	private IconButton startButton;
	private IconButton stopButton;
	private IconButton saveButton;
	private IconButton backButton;
	private NetCaptor netCaptor;
	
	public ControlBarPanel() {
		// TODO Auto-generated constructor stub
		initialize();
		addComponent();
		addListener();
	}
	
	private void initialize() {
		Dimension preferredSize = new Dimension(ConstantUI.MAIN_WINDOW_WIDTH - ConstantUI.TOOLBAR_WIDTH,
				48);
		this.setPreferredSize(preferredSize);
		this.setMinimumSize(preferredSize);
		this.setMaximumSize(preferredSize);
		this.setBackground(ConstantUI.FONT_COLOR);
		this.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
		
		netCaptor = new NetCaptor();
	}
	
	private void addComponent() {
		startButton = new IconButton(ConstantUI.START_ICON_NORMAL, ConstantUI.START_ICON_ENABLED, 
				ConstantUI.START_ICON_DISABLED,"开始");
		stopButton = new IconButton(ConstantUI.STOP_ICON_NORMAL, ConstantUI.STOP_ICON_ENABLED,
				ConstantUI.STOP_ICON_DISABLED, "停止");
		saveButton = new IconButton(ConstantUI.SAVE_ICON, ConstantUI.SAVE_ICON_ENABLED, 
				ConstantUI.SAVE_ICON, "保存");
		backButton = new IconButton(ConstantUI.BACK_ICON, ConstantUI.BACK_ICON_ENABLED,
				ConstantUI.BACK_ICON, "返回");
		
		
		this.add(startButton);
		this.add(stopButton);
		this.add(saveButton);
		this.add(backButton);
	}
	
	private void addListener() {
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				startButton.setIcon(ConstantUI.START_ICON_ENABLED);
				startButton.setEnabled(false);
				stopButton.setEnabled(true);
				stopButton.setIcon(ConstantUI.STOP_ICON_NORMAL);
				backButton.setIcon(ConstantUI.BACK_ICON);
				NetworkPanel.getTableData().removeAllElements();
				NetworkPanel.getPacketData().removeAllElements();
				NetworkPanel.getCaptorTable().addNotify();
				netCaptor.capturePacket();
			}
		});
		
		stopButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				stopButton.setIcon(ConstantUI.STOP_ICON_ENABLED);
				stopButton.setEnabled(false);
				startButton.setEnabled(true);
				startButton.setIcon(ConstantUI.START_ICON_NORMAL);
				backButton.setIcon(ConstantUI.BACK_ICON);
				netCaptor.stopCapture();
			}
		});
		
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser dirChooser = new JFileChooser();
				FileNameExtensionFilter fileNameExtensionFilter = 
						new FileNameExtensionFilter("excel文件(*.xls & *.xlsx)", "xls","xlsx");
				dirChooser.setFileFilter(fileNameExtensionFilter);
				File file = null;
				String fileName = null;
				Label labelS = null;
				Number labelN = null;
				int select = dirChooser.showSaveDialog(null);
				if(select == JFileChooser.APPROVE_OPTION) {
					file = dirChooser.getSelectedFile();
				}
				fileName = dirChooser.getName(file);
				if(fileName == null || fileName.trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "文件名为空！");
				}
				if(fileName.indexOf(".xls") == -1 && fileName.indexOf(".xlsx") == -1) {
					fileName = fileName+".xls";
				}
				file = dirChooser.getCurrentDirectory();
				
				String path = file.getPath() + "/" +fileName;
				file = new File(path);
				
				if(file.exists()) {
					int i = JOptionPane.showConfirmDialog(null, "该文件已经存在，确定要覆盖吗？");     
	                if(i == JOptionPane.YES_OPTION);
	                else   return ; 
				}
				try {
					FileOutputStream fileOutputStream = new FileOutputStream(file);
					WritableWorkbook workbook = Workbook.createWorkbook(fileOutputStream);
					WritableSheet writableSheet = workbook.createSheet("捕获数据包信息", 0);
					JTable workbookTable = NetworkPanel.getCaptorTable();
					DefaultTableModel workbookModel = (DefaultTableModel) NetworkPanel.getCaptorTable().getModel();
					for(int i = 0;i < workbookModel.getColumnCount();i++) {
						Label label = new Label(i, 0, workbookModel.getColumnName(i));
						writableSheet.addCell(label);
					}
					for(int j = 0;j<workbookTable.getRowCount();j++) {
						for(int i = 0;i < workbookTable.getColumnCount();i++) {
							System.out.println(workbookTable.getRowCount());
							labelS = new Label(i,j+1,workbookTable.getValueAt(j, i).toString());
							writableSheet.addCell(labelS);
								
						}
						
					}
					workbook.write();
					workbook.close();
					JOptionPane.showMessageDialog(null,"导出到"+file.getName()+"成功","成功",JOptionPane.INFORMATION_MESSAGE);
				}catch (Exception ex) {
					// TODO: handle exception
					ex.printStackTrace();
				}
			}
		});
		
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				backButton.setIcon(ConstantUI.BACK_ICON_ENABLED);
				startButton.setIcon(ConstantUI.START_ICON_NORMAL);
				stopButton.setIcon(ConstantUI.STOP_ICON_NORMAL);
				AppMainWindow.getMainPanel().removeAll();
				AppMainWindow.getMainPanel().add(AppMainWindow.getInitialPanel());
				backButton.setIcon(ConstantUI.BACK_ICON);
				AppMainWindow.getMainPanel().updateUI();
			}
		});
	}
	
}
