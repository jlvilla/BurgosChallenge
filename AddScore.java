import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class AddScore extends JFrame implements ActionListener{
	private String userName, menuName;
	private int rightAnswer, totalItems;
	private JLabel labelHeader;
	private JPanel bottomPanel, backgroundPanel;
	private JButton resetScoreBoard, exitButton;
	private JTextArea textArea;
	private File file;
	private FileReader fileReader;
	private PrintWriter printWriter;

public AddScore(String menuName ,String userName, int userRightAnswer, int userTotalAnswered)
{
	super(menuName);
	this.menuName = menuName;
	this.userName = userName;
	labelHeader = new JLabel();
	textArea = new JTextArea();
	bottomPanel = new JPanel(new GridLayout());
	backgroundPanel = new JPanel();
	resetScoreBoard = new JButton("Reset Score");
	exitButton = new JButton("Exit");
	rightAnswer = userRightAnswer;
	totalItems = userTotalAnswered;
	plottingOfScores();
	}

public void plottingOfScores(){
	backgroundPanel.setLayout(new BoxLayout(backgroundPanel,BoxLayout.Y_AXIS));
	labelHeader.setText("Name               Score                   Total");
	textArea.setEditable(false);

	try{
		file = new File(menuName+".txt");

	printWriter = new PrintWriter(new FileWriter(file, true));
	printWriter.println(userName+"\t"+rightAnswer+"\t"+totalItems);
	
	printWriter.close();

	fileReader = new FileReader(file);
	char[] c = new char[(int)file.length()];
	fileReader.read(c);
	String s ="";
	for (char k : c){
		s += ""+k;
		textArea.append(""+k);
		}
	fileReader.close();
	}catch(Exception e){}

	bottomPanel.add(resetScoreBoard);
	bottomPanel.add(exitButton);
	add(BorderLayout.NORTH, labelHeader);
	backgroundPanel.add(textArea);
	backgroundPanel.add(bottomPanel);
	getContentPane().add(backgroundPanel);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setResizable(false);
	pack();
	setVisible(true);

	resetScoreBoard.addActionListener(this);
	exitButton.addActionListener(this);
	}

public void actionPerformed(ActionEvent e){
	if (e.getSource()==resetScoreBoard){
		try{
		file = new File(menuName+".txt");
		printWriter = new PrintWriter(file);
		printWriter.print("");
		printWriter.close();
		textArea.setText("");
		}catch(Exception ex){}
		}
	if (e.getSource()==exitButton)
		System.exit(0);
	}
}