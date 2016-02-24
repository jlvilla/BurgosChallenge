import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

class GUIAsc extends JFrame implements ActionListener {
	private JPanel p,p1, p2, p3;
	private JButton startA, giveUp, exit;
	private JLabel tym, seq, numL;
	private JTextField eq, a1, a2,a3,a4,a5;
	private int seconds, ave, right, total, nums;
	private double scored, num;
	private int[] ans, ar;
	private String uName, sNum;
	private Thread tre;

public GUIAsc(String name){
	super("Ascending");
	uName = name;
	p = new JPanel();
	p1 = new JPanel();
	p2 = new JPanel();
	p3 = new JPanel(new GridLayout());
	startA = new JButton("Start");
	giveUp = new JButton("Give Up");
	exit = new JButton("Exit");
	seconds = 60;
	ans = new int[5];
	right = 0;
	total = 0;
	ave = 0;
	tym = new JLabel("Seconds Left: "+seconds, JLabel.CENTER);
	numL = new JLabel();
	seq = new JLabel(" = ");
	eq = new JTextField(10);
	a1 = new JTextField(2);
	a2 = new JTextField(2);
	a3 = new JTextField(2);
	a4 = new JTextField(2);
	a5 = new JTextField(2);
	}

public void ascending(){
	tym.setFont(new Font("Comic San ms", Font.PLAIN, 15));
	tym.setForeground(Color.white);
	seq.setFont(new Font("Comic San ms", Font.PLAIN, 12));
	numL.setFont(new Font("Comic San ms", Font.PLAIN, 12));

	eq.setEditable(false);
	a1.setEditable(false);
	a2.setEditable(false);
	a3.setEditable(false);
	a4.setEditable(false);
	a5.setEditable(false);
	
	p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
	p.setBackground(Color.white);
	p1.setBackground(Color.black);
	p2.setBackground(Color.white);
	p3.setBackground(Color.black);

	p1.add(tym);
	p2.add(numL);
	p2.add(eq);
	p2.add(seq);
	p2.add(a1);
	p2.add(a2);
	p2.add(a3);
	p2.add(a4);
	p2.add(a5);
	p3.add(startA);
	p3.add(exit);
	p.add(p1);
	p.add(p2);
	p.add(p3);
	setContentPane(p);

	a1.addActionListener(this);
	a2.addActionListener(this);
	a3.addActionListener(this);
	a4.addActionListener(this);
	a5.addActionListener(this);
	exit.addActionListener(this);
	giveUp.addActionListener(this);
	startA.addActionListener(this);

	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setSize(333,121);
	setResizable(false);
	setVisible(true);
	}

@Override
public void actionPerformed(ActionEvent e){
	if (e.getSource()==startA){
		total = 0;
		right = 0;
		ans = new int[5];
		seconds = 60;
		a1.setEditable(true);
		a2.setEditable(true);
		a3.setEditable(true);
		a4.setEditable(true);
		a5.setEditable(true);
		quest();
		total++;
		p3.removeAll();
		p3.add(giveUp);
		p3.add(exit);
		tre = new Thread(){
			public void run(){
				for(int x=60; x>=0; x--){
					tym.setText("Seconds Left: "+x);
					seconds = x;
					try{Thread.sleep(1000);}
					catch(Exception e){}
					}
				}};	
			tre.start();
			giveUp.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					tre.stop();
					new AddScore("Ascending",uName, right, total);
					total = 0;
					right = 0;
					ans = new int[5];
					seconds = 60;
					eq.setText("");
					tym.setText("Seconds Left: "+seconds);
					a1.setEditable(false);
					a2.setEditable(false);
					a3.setEditable(false);
					a4.setEditable(false);
					a5.setEditable(false);
					p3.removeAll();
					p3.add(startA);
					p3.add(exit);
				}});

		}

	if (e.getSource()==exit)
		System.exit(0);

	if (e.getSource()==a1 || e.getSource()==a2 || e.getSource()==a3 || e.getSource()==a4 || e.getSource()==a5){
		try{
		if (Integer.parseInt(a1.getText())==ans[0] || Integer.parseInt(a2.getText())==ans[1] || Integer.parseInt(a3.getText())==ans[2] || Integer.parseInt(a4.getText())==ans[3] || Integer.parseInt(a5.getText())==ans[4]){
			System.out.println(Arrays.toString(ans)+"right");
			right++;
			}

		else if (!(Integer.parseInt(a1.getText())==ans[0] || Integer.parseInt(a2.getText())==ans[1] || Integer.parseInt(a3.getText())==ans[2] || Integer.parseInt(a4.getText())==ans[3] || Integer.parseInt(a5.getText())==ans[4]))
			System.out.println(Arrays.toString(ans)+"wrong");

		if (seconds==0){
			eq.setText("");
			a1.setText("");
			a2.setText("");
			a3.setText("");
			a4.setText("");
			a5.setText("");
			a1.setEditable(false);
			a2.setEditable(false);
			a3.setEditable(false);
			a4.setEditable(false);
			a5.setEditable(false);
			eq.setText("");
			scored=right;
			num=total;
			ave = (int)(scored/num*100);
			JOptionPane.showMessageDialog(null,uName+" got "+right+" out of "+
			total+"\nWith an average score of "+ave+"%","Hi "+uName+"!",
			JOptionPane.INFORMATION_MESSAGE);
			new AddScore("Ascending",uName, right, total);
			numL.setText("");
			p3.removeAll();
			p3.add(startA);
			p3.add(exit);
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
	a1.requestFocus();
	a1.setText("");
	a2.setText("");
	a3.setText("");
	a4.setText("");
	a5.setText("");
	ar = new int[5];
	for (int pl=0; pl<5; pl++){
		nums = (int)(Math.random()*100);
		ar[pl] = nums;
	}	

	sNum = Arrays.toString(ar);
	numL.setText(""+(total+1)+".");
	eq.setText(""+sNum);

	for(int o=ar.length-1; o>1; o--)
		for(int i=0; i<o; i++)
			if( ar[i] > ar[i+1] ){
				int temp = ar[i];
				ar[i] = ar[i+1];
				ar[i+1] = temp;
			}

	for (int y=0; y<5; y++)
		ans[y] = ar[y];
	}
}