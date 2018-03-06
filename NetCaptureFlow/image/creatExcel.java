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
			
			WritableSheet ws = wwb.createSheet("ѡ��������",0);
			
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
//			������д���ļ���
			wwb.write();
//			 ��wwb�ر�
			wwb.close();
			
			JOptionPane.showMessageDialog(null,"������"+file.getName()+"�ɹ�","�ɹ�",JOptionPane.INFORMATION_MESSAGE);
				
		}catch(FileNotFoundException fnfe){
			
			System.out.println("�ļ�"+file.getName()+"û���ҵ�");
			
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