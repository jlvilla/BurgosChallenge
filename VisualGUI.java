import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class VisualGUI extends JFrame implements ActionListener {
	private JPanel[] p;
	private JLabel label;
	private JButton[] b;
	private String uName;
	private String[] bNAI = {"vAsc.png", "vDes.png", "vOcc.png", "vEO.png", "Back", "Exit"};
	private GUIAsc ga;
	private GUIDesc gd;
	private GUIOcc go;
	private GUIEoO ge;
	private BurgosFrame bf;

public VisualGUI(String name){
	super("Visual");
	uName = name;
	p = new JPanel[4];
	for (int x = 0; x<4; x++)
		p[x] = new JPanel();
	label = new JLabel("Visual", JLabel.CENTER);
	b = new JButton[6];
	for (int x = 0; x<4; x++)
		b[x] = new JButton(new ImageIcon(bNAI[x]));
	for (int x = 4; x<6; x++)
		b[x] = new JButton(bNAI[x]);
	}

public void visualMenu(){
	label.setFont(new Font("Courier New", Font.BOLD, 30));

	p[0].setLayout(new GridLayout(0,1,0,1));
	p[1].setLayout(new GridLayout(1,2,12,0));
	p[2].setLayout(new BoxLayout(p[2], BoxLayout.Y_AXIS));
	p[3].setLayout(new CardLayout(10,10));

	for (int x = 0; x<3; x++)
		p[x].setBackground(Color.white);
	p[3].setBackground(Color.black);

	p[0].add(label);
	for (int x = 0; x<4; x++)
		p[0].add(b[x]);
	
	for (int x = 4; x<6; x++)
		p[1].add(b[x]);
		
	for (int x = 0; x<2; x++)
		p[2].add(p[x]);
	
	p[3].add(p[2]);
	setContentPane(p[3]);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	pack();
	setResizable(false);
	setVisible(true);

	for (int x = 0; x<6; x++)
		b[x].addActionListener(this);
	}

public void actionPerformed(ActionEvent e){
	if (e.getSource()==b[0]){
		ga = new GUIAsc(uName);
		ga.ascending();
		}
		
	if (e.getSource()==b[1]){
		gd = new GUIDesc(uName);
		gd.descending();
		}
		
	if (e.getSource()==b[2]){
		go = new GUIOcc(uName);
		go.occurrence();
		}

	if (e.getSource()==b[3]){
		ge = new GUIEoO(uName);
		ge.evenorodd();
		}

	if (e.getSource()==b[4]){
		bf = new BurgosFrame(uName);
		bf.GUIBurgos();
		}
		
	if (e.getSource()==b[5])
		System.exit(0);
	}
}
		