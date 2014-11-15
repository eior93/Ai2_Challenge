package main;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

public class AnagramUI {

	/**
	 * Creates the UI for Anagram Finder
	 * @param angrmFndr Anagram Finder backend
	 */
	public AnagramUI(AnagramFinder angrmFndr){

		JFrame frame = new JFrame("Anagram Finder");
		JPanel panel = new JPanel();
		frame.add(panel);
		frame.setLocation(100, 100);
		panel.setLayout(new BorderLayout());

		JTextField input = new JTextField(40);

		JTextArea output = new JTextArea(8,10);
		output.setWrapStyleWord(true);
		output.setLineWrap(true);
		panel.add(input, BorderLayout.NORTH);
		panel.add(output, BorderLayout.SOUTH);

		output.setEditable(false);
		output.setFont(new Font("Verdana", Font.PLAIN, 50));

		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
		frame.repaint();

		TimeListener tlistener = new TimeListener(input, output, angrmFndr);
		InputListener ilistener = new InputListener(tlistener);
		input.addKeyListener(ilistener);
	}

}



class InputListener implements KeyListener {
	TimeListener _timeListener;
	public InputListener(TimeListener timeListener){
		_timeListener = timeListener;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		_timeListener._timer.start(); 
	}
	@Override
	public void keyPressed(KeyEvent e) {
	}
	@Override
	public void keyReleased(KeyEvent e) {		
	}
}

class TimeListener implements ActionListener {
	Timer _timer;
	JTextArea _output;
	JTextField _input;
	AnagramFinder _angrmFndr;

	public TimeListener(JTextField input, JTextArea output, AnagramFinder angrmFndr){
		_timer = new Timer(500, this);
		_output = output;
		_input = input;
		_angrmFndr = angrmFndr;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		_output.setText(AnagramFinderMain.getAnagrams(_input.getText(), _angrmFndr));
	}
}





