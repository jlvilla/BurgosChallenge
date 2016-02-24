import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MathGUI extends JFrame implements ActionListener {
	private JPanel[] p;
	private JLabel label;
	private JButton[] b;
	private String uName;
	private String[] bNAI = {"mAdds.png", "mSubs.png", "mMuls.png", "mDivs.png", "Back", "Exit"};
	private GUIAdd ap;
	private GUISub up;
	private GUIMul mp;
	private GUIDiv dp;
	private BurgosFrame bf;

public MathGUI(String name){
	super("Math");
	uName = name;
	p = new JPanel[4];
	for (int x = 0; x<4; x++)
		p[x] = new JPanel();
	label = new JLabel("Mathematics", JLabel.CENTER);
	b = new JButton[6];
	for (int x = 0; x<4; x++)
		b[x] = new JButton(new ImageIcon(bNAI[x]));
	for (int x = 4; x<6; x++)
		b[x] = new JButton(bNAI[x]);
	}

public void mathMenu(){
	label.setFont(new Font("Algerian", Font.BOLD, 30));
	label.setForeground(Color.red);

	p[0].setLayout(new GridLayout(0,1,0,1));
	p[1].setLayout(new GridLayout(1,2,12,0));
	p[2].setLayout(new BoxLayout(p[2], BoxLayout.Y_AXIS));
	p[3].setLayout(new CardLayout(10,10));

	for (int x = 0; x<3; x++)
		p[x].setBackground(Color.orange);
	p[3].setBackground(Color.red);

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
		ap = new GUIAdd(uName);
		ap.addition();
		}
		
	if (e.getSource()==b[1]){
		up = new GUISub(uName);
		up.subtraction();
		}
		
	if (e.getSource()==b[2]){
		mp = new GUIMul(uName);
		mp.multiplication();
		}

	if (e.getSource()==b[3]){
		dp = new GUIDiv(uName);
		dp.division();
		}

	if (e.getSource()==b[4]){
		bf = new BurgosFrame(uName);
		bf.GUIBurgos();
		}
		
	if (e.getSource()==b[5])
		System.exit(0);
	}
}
		