import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
class BurgosFrame extends JFrame implements ActionListener{
	private JPanel[] p; 
	private JButton[] b;
	private JLabel bC;
	private static String uName;
	private MathGUI mg;
	private LogicGUI lg;
	private VisualGUI vg;
	private AboutGUI about;

public BurgosFrame(String uName){
	super("Hi "+uName+"! Welcome to");
	setIconImage(new ImageIcon("iconB.png").getImage());
        p = new JPanel[5];
	for (int x=0; x<5; x++)
		p[x] = new JPanel();
	b = new JButton[5];
	b[0] = new JButton("Math", new ImageIcon("MSign.gif"));
	b[1] = new JButton("Logic", new ImageIcon("Gear.gif"));
	b[2] = new JButton("Visual", new ImageIcon("Skeleton.gif"));
	b[3] = new JButton("About");
	b[4] = new JButton("Exit");
	bC = new JLabel(new ImageIcon("BurgosChallengeTM.gif"), JLabel.CENTER);
	}	

public void GUIBurgos(){
	for (int x=0; x<5; x++){
		b[x].setHorizontalTextPosition(SwingConstants.CENTER);
		b[x].setVerticalTextPosition(SwingConstants.TOP);
		b[x].setForeground(Color.blue);
		b[x].setPressedIcon(new ImageIcon("icon.png"));
		}

	b[0].setRolloverIcon(new ImageIcon("MathSign.jpg"));
	b[1].setRolloverIcon(new ImageIcon("LogicSign.jpg"));
	b[2].setRolloverIcon(new ImageIcon("VisualSign.jpg"));
	bC.setFont(new Font("Algerian", Font.PLAIN, 30));
	bC.setForeground(Color.blue);

	p[1].setLayout(new FlowLayout());
	p[2].setLayout(new GridLayout(1,2,55,0));
	p[3].setLayout(new BoxLayout(p[3], BoxLayout.Y_AXIS));
	p[4].setLayout(new CardLayout(10,10));
	
	p[0].add(bC);
	for (int x=0; x<3; x++)
		p[1].add(b[x]);
	for (int x=3; x<5; x++)
		p[2].add(b[x]);
	for (int x=0; x<3; x++){
		p[x].setBackground(Color.cyan);
		p[3].add(p[x]);
		}
	p[4].setBackground(Color.blue);
	p[4].add(p[3]);
	
	for (int x=0; x<5; x++)
		b[x].addActionListener(this);

	setContentPane(p[4]);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setResizable(false);
	pack();
	setLocationRelativeTo(null);
	setVisible(true);
	}

public void actionPerformed(ActionEvent e){
	if (e.getSource()==b[0]){
		mg = new MathGUI(uName);
		mg.mathMenu();
		}
	if (e.getSource()==b[1]){
		lg = new LogicGUI(uName);
		lg.logicMenu();
		}
	if (e.getSource()==b[2]){
		vg = new VisualGUI(uName);	
		vg.visualMenu();
		}
	if (e.getSource()==b[3]){
		about = new AboutGUI();
		about.aboutBurgos();
		}
	if (e.getSource()==b[4])
		System.exit(0);
		
	}
	
public  static void callName(){
	uName = JOptionPane.showInputDialog(null, "Enter your Nickname","Welcome to Burgos Challenge", JOptionPane.WARNING_MESSAGE);
	while (uName.equals(""))
		callName();
	}
		
public static void main(String[]args){
	try{
	callName();
	BurgosFrame burgos = new BurgosFrame(uName);
	
	if (uName.equals(JOptionPane.CANCEL_OPTION))
		System.exit(0);
	if (uName.equalsIgnoreCase("Burgos"))
		JOptionPane.showMessageDialog(null,
		"Welcome Master "+uName+"\nYou are 100% right in all challenges\nso goodbye =D",
		"The Lord of the Game",JOptionPane.WARNING_MESSAGE, new ImageIcon("Ibrowse.gif"));
	else
		burgos.GUIBurgos();
	}catch(Exception e){}
	
	}
} 