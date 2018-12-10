package Boundary;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ShowLable extends JLabel{
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
    
    public ShowLable(int Count){
        setText("");
        if(Count==4){
        	setForeground(Col_Font4); //Ç°¾°É«
        	setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 38));
        }else{
        	setForeground(Col_Font); //Ç°¾°É«
        	setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 16));
        }
        setOpaque(false);//Í¸Ã÷
        setHorizontalAlignment(SwingConstants.RIGHT);
        
    	}
    
    public void setFontB(boolean isBig){
    	if(isBig){
    		setForeground(Col_Font4); //Ç°¾°É«
    		setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 38));
    	}else{
    		setForeground(Col_Font); //Ç°¾°É«
    		setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 16));
    	}
    }
}
