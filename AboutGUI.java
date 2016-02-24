import javax.swing.*;

import java.awt.*;
import java.io.*;

@SuppressWarnings("serial")
class AboutGUI extends JFrame {
	private JPanel textAreaPanel, buttonMenu, backgroundPanel;
	private JTextArea tf;
	private File file;
	private FileReader fileReader;
	private char[] c;

public AboutGUI(){
	super(" About");
	textAreaPanel = new JPanel();
	backgroundPanel = new JPanel();
	buttonMenu = new JPanel(new GridLayout());
	tf = new JTextArea();
	}

public void aboutBurgos(){
	try {
	file = new File("About.txt");
	fileReader = new FileReader(file);
	c = new char[(int)file.length()];
	fileReader.read(c);
	for (char k : c)
		tf.append(""+k);
	fileReader.close();
	}
	catch (Exception e){e.printStackTrace();}

	tf.setEditable(false);


	backgroundPanel.setBackground(Color.orange);

	textAreaPanel.add(tf);
	textAreaPanel.setBackground(Color.orange);
	add(textAreaPanel, BorderLayout.CENTER);
	add(buttonMenu, BorderLayout.SOUTH);
		
	pack();
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setResizable(false);
	setVisible(true);

	}
}