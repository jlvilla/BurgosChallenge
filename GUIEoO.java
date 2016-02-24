import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIEoO extends JFrame implements ActionListener {

	private JButton start, giveUp, exit;
	private JTextField tf, at;
	private JLabel tym, qn;
	private JPanel p, p1, p2;
	private int total, seconds, right, wrong, ave;
	private double scored, num;
	private String ans, uName;
	private Thread tre;
	
	
	public GUIEoO(String name){
		super("Even or Odd");
		uName = name;
		
		start = new JButton("Start");
		giveUp = new JButton("Give Up");
		exit = new JButton("Exit");
		tf = new JTextField(3);
		at = new JTextField(3);
		seconds = 60;
		ans = "";
		right = 0;
		total = 0;
		ave = 0;
		tym = new JLabel("Seconds Left: "+seconds, JLabel.CENTER);
		qn = new JLabel();
		p = new JPanel();
		p1 = new JPanel();
		p2 = new JPanel(new GridLayout());
		}

	public void evenorodd(){
		tym.setFont(new Font("Comic San ms", Font.PLAIN, 15));
		tym.setForeground(Color.white);
		qn.setFont(new Font("Comic San ms", Font.PLAIN, 12));

		tf.setEditable(false);
		at.setEditable(false);

		p.setBackground(Color.black);
		p1.setBackground(Color.white);
		p2.setBackground(Color.black);

		start.addActionListener(this);
		giveUp.addActionListener(this);
		exit.addActionListener(this);
		at.addActionListener(this);
				
		p.add(tym);
		p1.add(qn);
		p1.add(tf);
		p1.add(at);
		p2.add(start);
		p2.add(exit);
		
		add(BorderLayout.NORTH, p);
		add(BorderLayout.CENTER, p1);
		add(BorderLayout.SOUTH, p2);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(212,121);
		setResizable(false);
		setVisible(true);
		
	}

	public void actionPerformed(ActionEvent a) {
		if(a.getSource() == start)
		{
			seconds = 0;
			total  = 0;
			right = 0;
			wrong = 0;
			ave = 0;
			ans = "";
			at.setEditable(true);
			quest();
			total++;
			p2.removeAll();
			p2.add(giveUp);
			p2.add(exit);
			tre = new Thread(){
				public void run(){
					for(int x = 60; x >=0; x--)
					{
						tym.setText("Seconds Left: " + x);
						seconds = x;
						try{
							Thread.sleep(1000);
						}
						catch(Exception exc){}
					}
					}};
			tre.start();
			giveUp.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent a)
				{
					tre.stop();
					new AddScore("Even or Odd",uName, right, total);
					total = 0;
					right = 0;
					ans = "";
					seconds = 60;
					tf.setText("");
					at.setText("");	
					qn.setText("");
					tym.setText("Seconds Left: "+seconds);
					tf.setEditable(false);
					at.setEditable(false);
					p2.removeAll();
					p2.add(start);
					p2.add(exit);
					
				}
				
			});
	

		}
		
		if(a.getSource() == at){
			try{
				if(at.getText().equalsIgnoreCase(ans))
				{
					System.out.println(ans + " right");
					right++;
					at.setText("");
				}
				else 
				{
					System.out.println(ans + " wrong");
					at.setText("");
				}
				if(seconds == 0){
					tf.setText("");
					tf.setEditable(false);
					at.setText("");
					at.setEditable(false);
					scored = right;
					num = total;
					ave = (int)(scored / num * 100);
					JOptionPane.showMessageDialog(null, uName+ " got " + right + " out of " + total + "\nwith an average score of " + ave + "%", "Hi" + uName + "!",JOptionPane.INFORMATION_MESSAGE );
					new AddScore("Even or Odd",uName, right, total);
					p2.removeAll();
					p2.add(start);
					p2.add(exit);
				}
				else if(seconds > 0){
					quest();
					total++;
					}
				}
				catch(Exception exc){
					JOptionPane.showMessageDialog(null, "Numbers not allowed! \n Answer Even or Odd only ");
					at.setText("");
					total--;
					quest();
					total++;
					}
				}
				
			}

	public void quest(){
		at.requestFocus();
		tf.setText("");
		tf.setText("");
		at.setText("");
		qn.setText("" + (int)(total+1) + ".");
		int num = (int)(Math.random() * 100);
		   
		   if(num%2 == 0)
			   ans="Even";
		   else
			   ans="Odd";
		   
		 tf.setText(""+num);
		   
		
		
	}
}
