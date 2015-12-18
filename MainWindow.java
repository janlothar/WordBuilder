import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JList;
import java.util.List;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.SystemColor;

public class MainWindow {

	private JFrame frame;
	final static String FILE_NAME = "dictionary.txt";
	final static Charset ENCODING = StandardCharsets.UTF_8;
	final static WordBuilder dictionary = new WordBuilder();
	List<String> lines;
	{
		try {
		lines = dictionary.readFile(FILE_NAME);
		} catch(IOException e){}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JTextPane textPaneInput = new JTextPane();
		textPaneInput.setText("Enter letters here");
		panel_1.add(textPaneInput);
		
		DefaultListModel listResults = new DefaultListModel();					//create list that can be updated
		
		JScrollPane scrollPane = new JScrollPane();								//create scroll pane for results
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JList list = new JList(listResults);									//create list viewer with listResults
		scrollPane.setViewportView(list);										//add list to scroll bar
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnBuild = new JButton("Build");
		panel.add(btnBuild);
		
		JButton btnContains = new JButton("Contains");
		btnContains.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String userInput = textPaneInput.getText();
				List<String> results = dictionary.findContains(userInput, lines);
				for (int i=0;i<results.size();i++){
					listResults.addElement(results.get(i));
				}
			}
		});
		panel.add(btnContains);
		btnBuild.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String userInput = textPaneInput.getText();
				List<String> results = dictionary.findAnagram(userInput, lines);
				for (int i=0;i<results.size();i++){
					listResults.addElement(results.get(i));
				}
				
			}
		});
		
		
	}

}
