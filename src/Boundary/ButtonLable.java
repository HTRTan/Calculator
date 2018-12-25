package Boundary;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ButtonLable extends JLabel{
	/**
	 * 按钮类
	 */
	private static final long serialVersionUID = -2701473560168944199L;
	Color Col1=new Color(243,243,245);
    Color Col2=new Color(232,232,235);
    Color Col3=new Color(221,221,225);
    Color Col4=new Color(250,250,252);
    Color Col5=new Color(255,255,255);
    Color Col_Font=new Color(143,143,143);
    Color Col_Font2=new Color(102,102,102);
    Color Col_Font3=new Color(161,161,161);
    Color Col_Font4=new Color(11,11,11);
    Color Col_RED=new Color(188,47,46);
    Color Col_Blue=new Color(26,90,153);
    
    private String BLName;
   public ButtonLable(String LableName ,int Count){
	   BLName = LableName;
       setText(BLName);
       setFont(new java.awt.Font("微软雅黑", 0, 16));
       setBackground(Col4); //背景色
       if(LableName.equals("AC")){
    	   setForeground(Col_RED); //前景色
       }else{
    	   setForeground(Col_Font4); //前景色
       }
       
       setOpaque(true);
       setHorizontalAlignment(SwingConstants.CENTER);
       addMouseListener(new MouseListener() {
	
		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO 松开
			setBackground(Col4); //背景色
			repaint();
		}
		
		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO 按下
			setBackground(Col3); //背景色
			repaint();
		}
		
		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO 自动生成的方法存根
			
		}
		
		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO 自动生成的方法存根
			
		}
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO 自动生成的方法存根
			
		}
	});
       
   	}
   
   public String getText(){
	   return BLName;
   }
}
