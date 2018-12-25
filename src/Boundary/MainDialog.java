/**
 * 
 */
package Boundary;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

/**
 * @author HTRTan
 * @version 1.0.0
 */
public class MainDialog extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5758577267899490557L;
	Color Col1=new Color(243,243,245);
    Color Col2=new Color(232,232,235);
    Color Col3=new Color(221,221,225);
    Color Col4=new Color(250,250,252);
    Color Col5=new Color(255,255,255);
    Color Col_Font=new Color(143,143,143);
    Color Col_Font2=new Color(102,102,102);
    Color Col_Font3=new Color(161,161,161);
    Color Col_RED=new Color(188,47,46);
    Color Col_Blue=new Color(26,90,153);
    
    JPanel JP_Back = null;
    JPanel JP_Button = null;
    JPanel JP_Show = null;
    
    int InputCount = 0;
    String Res_String;
    String Inp_String;
    int Symbol_int;
    String Symbol_Str[]={"+","-","×","÷","="};
    boolean isInterrupt;
    boolean isEqual;
    String ButtonName[] = {"AC","<-","%","÷","7","8","9","×","4","5","6","-","1","2","3","+"," ","0",".","="};
    ShowLable ShowLableNew[];
	/**
	 * @param args
	 * 实例化窗体
	 */
	public static void main(String[] args) {
		MainDialog frame=  new MainDialog();
        frame.setVisible(true);
        frame.setBackground(Color.RED);
        frame.setLocationRelativeTo(null);
	}
	
	/**
	 * 窗体初始化
	 */
	public  MainDialog() {
		setTitle("计算器");
		setMinimumSize(new Dimension(300,550));
		setMaximumSize(new Dimension(300,550));
    	setBounds(0, 0, 300,550); 
    	setResizable(false);
    	InitGlobalFont(new Font("微软雅黑", Font.PLAIN ,12));
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	//最底层区域(可有可无)
    	JP_Back= new JPanel();
    	JP_Back.setBackground(Col2);
    	JP_Back.setLayout(null);//绝对布局
    	add(JP_Back);
    	//初始化
    	initView_Show();
    	initView_Bottom();
    	initCal();
	}
	
	/**
	 * 初始化参数
	 */
	public void initCal(){
		InputCount = 0;
		Symbol_int=-1;
		Res_String = "0";
		Inp_String = "0";
		ShowLableNew[4].setText(Inp_String);
		isInterrupt = false;
		isEqual = false;
		setShowState(isEqual);
		for(int SCount = 0;SCount<ShowLableNew.length;SCount++){
			if(SCount!=4){
				ShowLableNew[SCount].setVisible(false);
				ShowLableNew[SCount].setText("");
			}
		}
		
	}
	
	/**
	 * 初始化按键区域
	 */
	public void initView_Bottom(){
		//实例化按键区域
		JP_Button= new JPanel();
		JP_Button.setBackground(Col2);
		JP_Button.setLayout(new GridLayout(5, 4,2,2));//网格布局
		JP_Button.setBounds(0, 200, 300, 320);
    	JP_Back.add(JP_Button);
    	//创建按钮
    	ButtonLable ButtonLableNew[] = new ButtonLable[ButtonName.length];
    	for(int BCount = 0;BCount<ButtonLableNew.length;BCount++){
    		ButtonLableNew[BCount] = new ButtonLable(ButtonName[BCount],BCount);
    		setMouseListener(ButtonLableNew[BCount]);
    		JP_Button.add(ButtonLableNew[BCount]);
    	}
	}
	
	private void setMouseListener(ButtonLable TemButtonLable){
		TemButtonLable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO 自动生成的方法存根
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO 自动生成的方法存根
					if((isNumeric(TemButtonLable.getText()) || (".".equals(TemButtonLable.getText())&& Inp_String.indexOf(".")==-1)) && !isEqual){
						ShowLableNew[5].setVisible(true);
						String TemRes_String = "";
						if((Inp_String.length()==1 && "0".equals(Inp_String)) && !".".equals(TemButtonLable.getText())){
							Inp_String=TemButtonLable.getText();
						}else{
							Inp_String+=TemButtonLable.getText();
						}
						if(InputCount==0){
							ShowLableNew[4].setText(Inp_String);
							TemRes_String = calculateRes(Res_String,Inp_String,0);
						}else{
							ShowLableNew[4].setText(Symbol_Str[Symbol_int]+" "+Inp_String);
							TemRes_String = calculateRes(Res_String,Inp_String,Symbol_int);
						}
						if(!isInterrupt){
							ShowLableNew[5].setText("= "+TemRes_String);
						}
					}else if("AC".equals(TemButtonLable.getText())){
						initCal();
					}else if("+".equals(TemButtonLable.getText())){
						if(!isInterrupt){
							calculateMouseEvent(0);
						}
					}else if("-".equals(TemButtonLable.getText())){
						if(!isInterrupt){
							calculateMouseEvent(1);
						}
					}else if("×".equals(TemButtonLable.getText())){
						if(!isInterrupt){
							calculateMouseEvent(2);
						}
					}else if("÷".equals(TemButtonLable.getText())){
						if(!isInterrupt){
							calculateMouseEvent(3);
						}
					}else if("<-".equals(TemButtonLable.getText())){
						if(isEqual){
							isEqual=false;
							setShowState(isEqual);
						}
						String TemRes_String = "";
						if(InputCount==0){
							ShowLableNew[4].setText(Inp_String);
							TemRes_String = calculateRes(Res_String,Inp_String,0);
						}else{
							Inp_String=Inp_String.length()>1?Inp_String.substring(0, Inp_String.length()-1):"";
							ShowLableNew[4].setText(Symbol_Str[Symbol_int]+" "+Inp_String);
							TemRes_String = calculateRes(Res_String,Inp_String==""?"0":Inp_String,Symbol_int);
						}
						if(!isInterrupt){
							ShowLableNew[5].setText("= "+TemRes_String);
						}
					}else if("%".equals(TemButtonLable.getText())){
						if(!(isInterrupt || Inp_String.equals(""))){
							ShowLableNew[5].setVisible(true);
							Inp_String = calculateRes("0.01",Inp_String,2);
							String TemRes_String = "";
							if(InputCount==0){
								ShowLableNew[4].setText(Inp_String);
								TemRes_String = calculateRes(Res_String,Inp_String,0);
							}else{
								ShowLableNew[4].setText(Symbol_Str[Symbol_int]+" "+Inp_String);
								TemRes_String = calculateRes(Res_String,Inp_String,Symbol_int);
							}
							ShowLableNew[5].setText("= "+TemRes_String);
						}
					}else if("=".equals(TemButtonLable.getText())){
						if(ShowLableNew[5].isVisible() && !"".equals(Inp_String) && !isEqual){
							isEqual=true;
							setShowState(isEqual);
						}
					}
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO 自动生成的方法存根
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO 自动生成的方法存根
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO 自动生成的方法存根
			}
		});
		
	}
	
	/**
	 * @TemRes 前结果
	 * @TemInp 输入的数
	 * @Type_Sy 计算类型（加减乘除）
	 * @return 输出结果
	 */
	public String calculateRes(String TemRes,String TemInp,int Type_Sy){
		BigDecimal BDec_Res = new BigDecimal(TemRes);
		BigDecimal BDec_Inp = new BigDecimal(TemInp);
		if(Type_Sy==0){
			BDec_Res=BDec_Res.add(BDec_Inp).setScale(10,BigDecimal.ROUND_HALF_UP);
		}else if(Type_Sy==1){
			BDec_Res=BDec_Res.subtract(BDec_Inp).setScale(10,BigDecimal.ROUND_HALF_UP);
		}else if(Type_Sy==2){
			BDec_Res=BDec_Res.multiply(BDec_Inp).setScale(10,BigDecimal.ROUND_HALF_UP);
		}else if(Type_Sy==3){
			while ((TemInp.substring(TemInp.length()-1).equals("0")) && TemInp.length()>1) {
				TemInp=TemInp.substring(0,TemInp.length()-1);
			}
			if(TemInp.equals("0") || TemInp.equals("0.")){
				isInterrupt = true;
				ShowLableNew[5].setText("不能除以0");
				return TemRes;
			}else{
				isInterrupt = false;
				BDec_Res=BDec_Res.divide(BDec_Inp,10,BigDecimal.ROUND_HALF_UP);
			}
		}
		String TemRes_New = BDec_Res.toPlainString();
		while ((TemRes_New.substring(TemRes_New.length()-1).equals("0") || TemRes_New.substring(TemRes_New.length()-1).equals("."))&& TemRes_New.indexOf(".")>0) {
			TemRes_New=TemRes_New.substring(0,TemRes_New.length()-1);
		}
		return TemRes_New;
	}
	
	/**
	 *设置后两个显示框显示状态
	 */
	public void setShowState(boolean TemisEqual){
		if(TemisEqual){
			ShowLableNew[4].setBounds(7,26*4,256, 26);
			ShowLableNew[4].setFontB(false);
			ShowLableNew[5].setBounds(7,26*5,256, 52);
			ShowLableNew[5].setFontB(true);
		}else{
			ShowLableNew[4].setBounds(7,26*4,256, 52);
			ShowLableNew[4].setFontB(true);
			ShowLableNew[5].setBounds(7,26*5+26,256, 26);
			ShowLableNew[5].setFontB(false);
		}
	}
	
	/**
	 *显示数值计算
	 */
	public void calculateMouseEvent(int Type_Sy){
		if("".equals(Inp_String)){
			Symbol_int=Type_Sy;
		}else{
			if(isEqual){
				isEqual=false;
				setShowState(isEqual);
			}
			if(InputCount==0){
				Res_String = calculateRes(Res_String,Inp_String,0);
			}else{
				Res_String = calculateRes(Res_String,Inp_String,Symbol_int);
			}
			InputCount+=1;
			String TemText="";
			for(int Count =0 ;Count <(InputCount<=3?InputCount:3);Count++){
				ShowLableNew[3-Count].setVisible(true);
				if(Count==0){
					TemText=ShowLableNew[3-Count].getText();
					ShowLableNew[3-Count].setText((Symbol_int==-1?" ":Symbol_Str[Symbol_int])+Inp_String);
				}else{
					String TemText1=ShowLableNew[3-Count].getText();
					ShowLableNew[3-Count].setText(TemText);
					TemText = TemText1;
				}
			}
			Symbol_int=Type_Sy;
			Inp_String="";
		}
		ShowLableNew[4].setText(Symbol_Str[Symbol_int]+" "+Inp_String);
	}
	
	/**
	 * 初始化显示区域
	 */
 	public void initView_Show(){
		//实例化显示区域
    	JP_Show= new JPanel();
    	JP_Show.setBackground(Col2);
    	JP_Show.setLayout(null);
    	JP_Show.setBounds(10, 9, 280, 182);
    	JP_Back.add(JP_Show);
    	ShowLableNew = new ShowLable[6];
    	for(int SCount = 0;SCount<ShowLableNew.length;SCount++){
    		ShowLableNew[SCount] = new ShowLable(SCount);
    		if(SCount==4){
    			ShowLableNew[SCount].setBounds(7, 26*SCount, 256, 52);
    			ShowLableNew[SCount].setText("0");
    		}else if(SCount>4){
    			ShowLableNew[SCount].setBounds(7, 26*SCount+26, 256, 26);
    			ShowLableNew[SCount].setVisible(false);
    		}else{
    			ShowLableNew[SCount].setBounds(7, 26*SCount, 256, 26);
    			ShowLableNew[SCount].setVisible(false);
    		}
    		JP_Show.add(ShowLableNew[SCount]);
    	}
    	
	}

	/**
	 * @font 更改字体（大小、颜色、字体）
	 */
	private void InitGlobalFont(Font font) { 
		FontUIResource fontRes = new FontUIResource(font);
		for (Enumeration<Object> keys = UIManager.getDefaults().keys(); 
				keys.hasMoreElements(); ) { 
			Object key = keys.nextElement(); 
			Object value = UIManager.get(key); 
			if (value instanceof FontUIResource) { 
				UIManager.put(key, fontRes); 
		}	 
		}
	}

	/**
	 * 判断是否数字
	 */
	public static boolean isNumeric(String str){
	    Pattern pattern = Pattern.compile("[0-9]*");
	    return pattern.matcher(str).matches();   
	}
}
