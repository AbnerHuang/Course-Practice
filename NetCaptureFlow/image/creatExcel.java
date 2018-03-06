import java.io.*;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import jxl.Workbook;
import jxl.write.Number;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class creatExcel {
	
	public void creatExcel(File file,DefaultTableModel tableModel,JTable jTable){

		Label labelS = null;
		
		Number labelN = null;
		
		try{
			
			FileOutputStream os = new FileOutputStream(file);
			
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			
			WritableSheet ws = wwb.createSheet("选择题试题",0);
			
			for(int i = 0;i<tableModel.getColumnCount();i++){
				
				Label label = new Label(i,0,tableModel.getColumnName(i));
				ws.addCell(label);
			}
			for(int j =0 ; j<jTable.getRowCount();j++){
				
				for(int i = 0;i<jTable.getColumnCount();i++){

					if(jTable.getValueAt(j, i).getClass().equals(Integer.class)){
						
						labelN = new Number(i,j+1,(Integer)jTable.getValueAt(j, i));
						
						ws.addCell(labelN);
						
					}else if(jTable.getValueAt(j, i).getClass().equals(String.class)){
						
						labelS = new Label(i,j+1,(String)jTable.getValueAt(j, i));
						
						ws.addCell(labelS);
						
					}
				}
			
			}
//			将内容写到文件中
			wwb.write();
//			 将wwb关闭
			wwb.close();
			
			JOptionPane.showMessageDialog(null,"导出到"+file.getName()+"成功","成功",JOptionPane.INFORMATION_MESSAGE);
				
		}catch(FileNotFoundException fnfe){
			
			System.out.println("文件"+file.getName()+"没有找到");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


}