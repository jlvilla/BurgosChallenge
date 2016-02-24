import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class LogicGUI extends JFrame implements ActionListener {
	private JPanel[] p;
	private JLabel label;
	private JButton[] b;
	private String uName;
	private String[] bNAI = {"lEqs.png", "lFis.png", "lRNs.png", "Back", "Exit"};
	private GUIEq ge;
	private GUIFibo gf;
	private GUIRom gr;
	private BurgosFrame bf;

public LogicGUI(String name){
	super("Logic");
	uName = name;
	p = new JPanel[4];
	for (int x = 0; x<4; x++)
		p[x] = new JPanel();
	label = new JLabel("Logic", JLabel.CENTER);
	b = new JButton[5];
	for (int x = 0; x<3; x++)
		b[x] = new JButton(new ImageIcon(bNAI[x]));
	for (int x = 3; x<5; x++)
		b[x] = new JButton(bNAI[x]);
	}

public void logicMenu(){
	label.setFont(new Font("Magneto", Font.BOLD, 30));
	label.setForeground(Color.green);

	p[0].setLayout(new GridLayout(0,1,0,1));
	p[1].setLayout(new GridLayout(1,2,12,0));
	p[2].setLayout(new BoxLayout(p[2], BoxLayout.Y_AXIS));
	p[3].setLayout(new CardLayout(10,10));

	for (int x = 0; x<3; x++)
		p[x].setBackground(Color.gray);
	p[3].setBackground(Color.green);

	p[0].add(label);
	for (int x = 0; x<3; x++)
		p[0].add(b[x]);
	
	for (int x = 3; x<5; x++)
		p[1].add(b[x]);
		
	for (int x = 0; x<2; x++)
		p[2].add(p[x]);
	
	p[3].add(p[2]);
	setContentPane(p[3]);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	pack();
	setResizable(false);
	setVisible(true);

	for (int x = 0; x<5; x++)
		b[x].addActionListener(this);
	}

public void actionPerformed(ActionEvent e){
	if (e.getSource()==b[0]){
		ge = new GUIEq(uName);
		ge.equality();
		}
		
	if (e.getSource()==b[1]){
		gf = new GUIFibo(uName);
		gf.fibonacci();
		}
		
	if (e.getSource()==b[2]){
		gr = new GUIRom(uName);
		gr.romanNumerals();
		}

	if (e.getSource()==b[3]){
		bf = new BurgosFrame(uName);
		bf.GUIBurgos();
		}
		
	if (e.getSource()==b[4])
		System.exit(0);
	}
}
		