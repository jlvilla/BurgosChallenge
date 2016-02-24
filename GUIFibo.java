import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class GUIFibo extends JFrame implements ActionListener {
	private JPanel p ,p1, p2, p3;
	private JButton startA, giveUp, exit;
	private JLabel tym, numL;
	private JTextField[] tf;
	private int[] ans, rNums;
	private int seconds, ave, right, total, rNum1, rNum2, nums, rat, xtra;
	private double scored, num;
	private String uName;
	private Thread tre;

public GUIFibo(String name){
	super("Simple Fibonacci");
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
	tf = new JTextField[5];
	for (int x=0; x<5; x++)
		tf[x] = new JTextField(3);
	}

public void fibonacci(){
	tym.setFont(new Font("Comic San ms", Font.PLAIN, 15));
	numL.setFont(new Font("Comic San ms", Font.PLAIN, 12));

	for (int x=0; x<5; x++)
		tf[x].setEditable(false);
	
	p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
	p.setBackground(Color.green);
	p1.setBackground(Color.green);
	p2.setBackground(Color.green);
	p3.setBackground(Color.green);

	p1.add(tym);
	p2.add(numL);
	for (int x=0; x<5; x++)
		p2.add(tf[x]);
	p3.add(startA);
	p3.add(exit);
	p.add(p1);
	p.add(p2);
	p.add(p3);
	setContentPane(p);


	exit.addActionListener(this);
	giveUp.addActionListener(this);
	startA.addActionListener(this);
	for (int x=0; x<5; x++)
		tf[x].addActionListener(this);

	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setSize(313,121);
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
					new AddScore("Fibonacci",uName, right, total);
					total = 0;
					right = 0;
					ans = new int[5];
					seconds = 60;
					tym.setText("Seconds Left: "+seconds);
					numL.setText("");
					for (int x=0; x<5; x++){
						tf[x].setText("");
						tf[x].setEditable(false);
						}
					p3.removeAll();
					p3.add(startA);
					p3.add(exit);
				}});

		}

	if (e.getSource()==exit)
		System.exit(0);

	xtra=0;
	for (int x=0; x<5; x++){
	if (e.getSource()==tf[x]){
		try{
		if (Integer.parseInt(tf[x].getText())==ans[x]){
			System.out.println(Arrays.toString(ans)+"right");
			right++;
			}

		else if (!(Integer.parseInt(tf[x].getText())==ans[x]))
			System.out.println(Arrays.toString(ans)+"wrong");

		if (seconds==0){
				for (int y=0; y<5; y++){
					tf[y].setText("");
					tf[y].setEditable(false);
					}
				scored=right;
				num=total;
				ave = (int)(scored/num*100);
				JOptionPane.showMessageDialog(null,uName+" got "+right+" out of "+
				total+"\nWith an average score of "+ave+"%","Hi "+uName+"!",
				JOptionPane.INFORMATION_MESSAGE);
				numL.setText("");
				new AddScore("Fibonacci",uName, right, total);
				p3.removeAll();
				p3.add(startA);
				p3.add(exit);
				break;
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
	}

public void quest(){
	for (int f=0; f<5; f++)
		tf[f].setEditable(false);
	numL.setText(""+(total+1)+".");

	rNums = new int[5];
	rNum1 = (int)(Math.random()*10);
	rNum2 = (int)(Math.random()*10);
	nums=0;
	if (rNum1%2==0)
		for (int x=0; x<5; x++){
			nums = rNum1+rNum2;
			rNum1 = rNum2;
			rNum2 = nums;
			rNums[x] += nums;
			}
	else
		for (int x=0; x<5; x++){
			nums = rNum1-rNum2;
			rNum1 = rNum2;
			rNum2 = nums;
			rNums[x] += nums;
			}

	for (int x=0; x<5; x++){
		ans[x] = rNums[x];
		tf[x].setText(""+ans[x]);
		}
	rat = (int)(Math.random()*5);
	tf[rat].setEditable(true);		
	tf[rat].setText("");
	tf[rat].requestFocus();
	}
}