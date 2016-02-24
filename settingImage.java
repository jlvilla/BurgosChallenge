import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.*;

public class settingImage extends JFrame implements ActionListener{
	private JPanel[] p;
	private JLabel[] l;
	private JButton[] b;
	private JTextField[] tf;
	private String[] names = {"", "", "", "", "", "", ""};
	private String[] icons = {"", "", "", "", "", "", ""};

public settingImage(String name){
	super(name);
	setIconImage(Toolkit.getDefaultToolkit().getImage("e1.png"));
	p = new JPanel[3];
	b = new JButton[3];
	l = new JLabel[3];
	tf = new JTextField[3];
	l[0] = new JLabel(new ImageIcon("pic.gif"));
	
}
	
public void doIt(){

	setContentPane(l[0]);
	setResizable(false);
	pack();
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String name = JOptionPane.showInputDialog(null, "Enter name");
settingImage si = new settingImage(name);
si.doIt();
	}

}
