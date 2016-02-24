import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class GUIMul extends JFrame implements ActionListener {
	private JPanel p,p1, p2, p3;
	private JButton startA, giveUp, exit;
	private JLabel tym, seq, numL;
	private JTextField tf, eq;
	private int seconds, ans, ave, right, total, leftNum, rightNum;
	private double scored, num;
	private String uName;
	private Thread tre;

public GUIMul(String name){
	super("Multiplication");
	uName = name;
	p = new JPanel();
	p1 = new JPanel();
	p2 = new JPanel();
	p3 = new JPanel(new GridLayout());
	startA = new JButton("Start");
	giveUp = new JButton("Give Up");
	exit = new JButton("Exit");
	seconds = 60;
	ans = 0;
	right = 0;
	total = 0;
	ave = 0;
	tym = new JLabel("Seconds Left: "+seconds, JLabel.CENTER);
	numL = new JLabel();
	seq = new JLabel(" = ");
	eq = new JTextField(4);
	tf = new JTextField(4);
	}

public void multiplication(){
	tym.setFont(new Font("Comic San ms", Font.PLAIN, 15));
	seq.setFont(new Font("Comic San ms", Font.PLAIN, 12));
	numL.setFont(new Font("Comic San ms", Font.PLAIN, 12));

	eq.setEditable(false);
	tf.setEditable(false);
	
	p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
	p.setBackground(Color.yellow);
	p1.setBackground(Color.yellow);
	p2.setBackground(Color.yellow);
	p3.setBackground(Color.yellow);

	p1.add(tym);
	p2.add(numL);
	p2.add(eq);
	p2.add(seq);
	p2.add(tf);
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
		ans = 0;
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
					new AddScore("Multiplication",uName, right, total);
					total = 0;
					right = 0;
					ans = 0;
					seconds = 60;
					tym.setText("Seconds Left: "+seconds);
					numL.setText("");
					eq.setText("");
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
		if (tf.getText().equals(""+ans)){
			System.out.println(ans+"right");
			right++;
			}

		else if (!(tf.getText().equals(""+ans)))
			System.out.println(ans+"wrong");
		
		if (seconds==0){
			tf.setText("");
			tf.setEditable(false);
			eq.setText("");
			scored=right;
			num=total;
			ave = (int)(scored/num*100);
			JOptionPane.showMessageDialog(null,uName+" got "+right+" out of "+
			total+"\nWith an average score of "+ave+"%","Hi "+uName+"!",
			JOptionPane.INFORMATION_MESSAGE);
			new AddScore("Multiplication",uName, right, total);
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
	tf.requestFocus();
	tf.setText("");
	leftNum = (int)(Math.random()*100);
	rightNum = (int)(Math.random()*12);
	numL.setText(""+(total+1)+".");
	eq.setText(""+leftNum+" * "+rightNum);
	ans = leftNum * rightNum;
	}
}