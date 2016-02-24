import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Arrays;

class GUIOcc extends JFrame implements ActionListener{
	private JPanel[] p;
	private JButton[] b;
	private JTextField[] tf;
	private JLabel[] l;
	private int seconds, right, total, ave, ans, occur, f;
	private double scored, num;
	private String[] bNames = {"   Start   ", "   Exit   ", "Give Up"};
	private int[] ni;
	private String uName, s;
	private Thread tre;

public GUIOcc(String name){
	super("Occurence of a Number");
	uName = name;
	ans = 0;
	seconds = 60;
	right = 0;
	total = 0;
	ave = 0;
	occur = 0;
	p = new JPanel[5];
	b = new JButton[3];
	tf = new JTextField[2];
	l = new JLabel[2];
	for(int x=0; x<5; x++){
		p[x] = new JPanel();
		if (x<3)
			b[x] = new JButton(bNames[x]);
		}
	tf[0] = new JTextField(22);
	tf[1] = new JTextField();
	l[0] = new JLabel("Seconds Left: "+seconds, JLabel.CENTER);
	l[1] = new JLabel("",JLabel.CENTER);
	}

public void occurrence(){
	l[0].setFont(new Font("Comic San ms", Font.PLAIN, 15));
	l[1].setFont(new Font("comic San ms", Font.PLAIN, 12));

	for (int x=0; x<2; x++)
		tf[x].setEditable(false);

	p[1].setLayout(new GridLayout(3,4));
	p[2].setLayout(new FlowLayout());
	p[3].setLayout(new BoxLayout(p[3], BoxLayout.Y_AXIS));
	p[4].setLayout(new CardLayout(10,10));

	for (int x=0; x<4;x++){
		p[x].setBackground(Color.white);
		if (x<2)
			l[x].setForeground(Color.gray);
		if (x<3)
			b[x].setForeground(Color.gray);
		}
	p[4].setBackground(Color.black);


	p[0].add(l[0]);
	p[1].add(tf[0]);
	p[1].add(l[1]);
	p[1].add(tf[1]);

	for (int x=0; x<2; x++)
		p[2].add(b[x]);

	for (int x=0; x<3; x++){
		p[3].add(p[x]);
		b[x].addActionListener(this);
		if (x==1)
			tf[x].addActionListener(this);
		}

	p[4].add(p[3]);
	setContentPane(p[4]);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	pack();
	setResizable(false);
	setVisible(true);
	}

public void actionPerformed(ActionEvent e){
	if (e.getSource()==b[0]){
		ans = 0;
		seconds = 60;
		right = 0;
		total = 0;
		ave = 0;
		occur = 0;
		tf[1].setEditable(true);
		quest();
		total++;
		p[2].removeAll();
		p[2].add(b[2]);
		p[2].add(b[1]);
		tre = new Thread(){
			public void run(){
				for(int x=60; x>=0; x--){
					l[0].setText("Seconds Left: "+x);
					seconds = x;
					try{Thread.sleep(1000);}
					catch(Exception e){}
					}
				}};	
		tre.start();
		b[2].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				tre.stop();
				new AddScore("Occurrence",uName, right, total);
				total = 0;
				right = 0;
				ans = 0;
				seconds = 0;
				occur = 0;
				l[0].setText("Seconds Left: 60");
				l[1].setText("");
				for (int x=0; x<2; x++){
					tf[x].setText("");
					tf[x].setEditable(false);
					}
				p[2].removeAll();
				p[2].add(b[0]);
				p[2].add(b[1]);
			}});
		}
	if (e.getSource()==b[1])
		System.exit(0);

	if (e.getSource()==tf[1]){
		try{
		if (Integer.parseInt(tf[1].getText())==ans){
			right++;
			System.out.println(occur+"="+ans+" right");
			}

		else if (!(Integer.parseInt(tf[1].getText())==ans))
			System.out.println(occur+"="+ans+" wrong");

		if (seconds==0){
			for(int x=0; x<2; x++){
				tf[x].setText("");
				tf[x].setEditable(false);
				}
			l[1].setText("");
			scored=right;
			num=total;
			ave = (int)(scored/num*100);
			JOptionPane.showMessageDialog(null,uName+" got "+right+" out of "+total+"\nWith an average score of "+ave+"%", "Hi "+uName+"!", JOptionPane.INFORMATION_MESSAGE);
			new AddScore("Occurrence",uName, right, total);
			p[2].removeAll();
			p[2].add(b[0]);
			p[2].add(b[1]);
			}
		else if (seconds>0){
			quest();
			total++;
			}
		}catch (Exception ex){
			JOptionPane.showMessageDialog(null, "Don't waste your time!\nNumbers only please\nI'll give you a chance, this mistake will not add to your wrong answers\nbut I'll give you another equation", "Wrong Input", JOptionPane.PLAIN_MESSAGE, new ImageIcon("DWT70.gif"));
			total -= 1;
			quest();
			total++;			
			}
		}
	}

public void quest(){
	s="";
	ans = 0;
	occur = 0;
	tf[1].setText("");
	tf[1].requestFocus();

	ni = new int[15];

	f = (int)(Math.random()*10);
	occur = f;

	for (int x=0; x<15; x++){
		ni[x] = (int)(Math.random()*10);
		if (ni[x]==f)
			ans++;
		}
	for (int i : ni)
		s += "   "+i;
	tf[0].setText(s);
	
	l[1].setText(""+(total+1)+". Occurence of "+f+": ");
	}

}