import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class GUIEq extends JFrame implements ActionListener {
	private JPanel p,p1, p2, p3;
	private JButton startA, giveUp, exit;
	private JLabel tym, numL;
	private JTextField tf, eq1, eq2;
	private int seconds, ave, right, total, leftNum, rightNum;
	private double scored, num;
	private String uName, ans;
	private Thread tre; 

public GUIEq(String name){
	super("Equality");
	uName = name;
	ans = "";
	p = new JPanel();
	p1 = new JPanel();
	p2 = new JPanel();
	p3 = new JPanel(new GridLayout());
	startA = new JButton("Start");
	giveUp = new JButton("Give Up");
	exit = new JButton("Exit");
	tym = new JLabel("Seconds Left: "+seconds, JLabel.CENTER);
	numL = new JLabel();
	eq1 = new JTextField(3);
	tf = new JTextField(1);
	eq2 = new JTextField(3);
	seconds = 60;
	right = 0;
	total = 0;
	ave = 0;
	}

public void equality(){
	tym.setFont(new Font("Comic San ms", Font.PLAIN, 15));
	numL.setFont(new Font("Comic San ms", Font.PLAIN, 12));

	eq1.setEditable(false);
	eq2.setEditable(false);
	tf.setEditable(false);
	
	p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
	p.setBackground(Color.green);
	p1.setBackground(Color.green);
	p2.setBackground(Color.green);
	p3.setBackground(Color.green);

	p1.add(tym);
	p2.add(numL);
	p2.add(eq1);
	p2.add(tf);
	p2.add(eq2);
	p3.add(startA);
	p3.add(exit);
	p.add(p1);
	p.add(p2);
	p.add(p3);
	setContentPane(p);

	tf.addActionListener(this);
	exit.addActionListener(this);
	giveUp.addActionListener(this);
	startA.addActionListener(this);

	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setSize(212,121);
	setResizable(false);
	setVisible(true);
	}

@Override
public void actionPerformed(ActionEvent e){
	if (e.getSource()==startA){
		total = 0;
		right = 0;
		ans = "";
		seconds = 60;
		tf.setEditable(true);
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
					new AddScore("Equality",uName, right, total);
					total = 0;
					right = 0;
					ans = "";
					seconds = 60;
					tym.setText("Seconds Left: "+seconds);
					numL.setText("");
					eq1.setText("");
					eq2.setText("");
					tf.setText("");
					tf.setEditable(false);
					p3.removeAll();
					p3.add(startA);
					p3.add(exit);
				}});

		}

	if (e.getSource()==exit)
		System.exit(0);

	if (e.getSource()==tf){
		try{
		if (tf.getText().equals(ans)){
			System.out.println(ans+"right");
			right++;
			}

		else if (!(tf.getText().equals(ans)))
			System.out.println(ans+"wrong");
		
		if (seconds==0){
			tf.setText("");
			tf.setEditable(false);
			eq1.setText("");
			eq2.setText("");
			scored=right;
			num=total;
			ave = (int)(scored/num*100);
			JOptionPane.showMessageDialog(null,uName+" got "+right+" out of "+
			total+"\nWith an average score of "+ave+"%","Hi "+uName+"!",
			JOptionPane.INFORMATION_MESSAGE);
			numL.setText("");
			new AddScore("Equality",uName, right, total);
			p3.removeAll();
			p3.add(startA);
			p3.add(exit);
			}
		else if (seconds>0){
			quest();
			total++;
			}
		}catch (Exception ex){
			JOptionPane.showMessageDialog(null, "Don't waste your time!\nlogic signs only please\nI'll give you a chance, this mistake will not add to your wrong answers\nbut I'll give you another equation", "Wrong Input", JOptionPane.PLAIN_MESSAGE, new ImageIcon("DWT70.gif"));
			total -= 1;
			quest();
			total++;			
			}
		}
	}

public void quest(){
	tf.requestFocus();
	tf.setText("");
	leftNum = (int)(Math.random()*999);
	rightNum = (int)(Math.random()*999);
	numL.setText(""+(total+1)+".");
	eq1.setText(""+leftNum);
	eq2.setText(""+rightNum);
	if (leftNum>rightNum)
		ans = ">";
	if (leftNum==rightNum)
		ans = "=";
	if (leftNum<rightNum)
		ans = "<";
	}

}