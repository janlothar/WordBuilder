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
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.border.BevelBorder;

public class MainWindow {

	private JFrame frame;
	private static WordBuilder dictionary;{
	try {
		dictionary = new WordBuilder();
	}catch(IOException e) {}}

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
		frame.setBounds(100, 100, 290, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		frame.getContentPane().add(panel_1, BorderLayout.NORTH);
		
		JTextPane textPaneInput = new JTextPane();
		textPaneInput.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				textPaneInput.selectAll();										//highlight all text on focus
			}
		});
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JTextPane txtpnSpacesCountAs = new JTextPane();
		txtpnSpacesCountAs.setBackground(Color.LIGHT_GRAY);
		txtpnSpacesCountAs.setEditable(false);
		txtpnSpacesCountAs.setText("wildcards > spaces");
		panel_1.add(txtpnSpacesCountAs);
		textPaneInput.setText("Enter letters here");
		panel_1.add(textPaneInput);
		
		DefaultListModel listResults = new DefaultListModel();					//create list that can be updated
		
		JScrollPane scrollPane = new JScrollPane();								//create scroll pane for results
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JList list = new JList(listResults);									//create list viewer with listResults
		scrollPane.setViewportView(list);										//add list to scroll bar
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnBuild = new JButton("Build");
		btnBuild.setToolTipText("Find words made up of only letters in the text box");
		panel.add(btnBuild);
		
		JButton btnContains = new JButton("Contains");
		btnContains.setToolTipText("Find words that use all of the letters in the text box");
		btnContains.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listResults.clear();
				String userInput = textPaneInput.getText();
				List<String> results = dictionary.findContains(userInput);
				for (int i=0;i<results.size();i++){
					listResults.addElement(results.get(i));
				}
			}
		});
		panel.add(btnContains);
		
		JButton btnAnagram = new JButton("Anagram");
		btnAnagram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listResults.clear();
				String userInput = textPaneInput.getText();
				List<String> results = dictionary.findAnagram(userInput);
				for (int i=0;i<results.size();i++){
					listResults.addElement(results.get(i));
				}
			}
		});
		btnAnagram.setToolTipText("Find words containing exactly whats in the text box");
		panel.add(btnAnagram);
		btnBuild.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listResults.clear();
				String userInput = textPaneInput.getText();
				List<String> results = dictionary.findBuild(userInput);
				for (int i=0;i<results.size();i++){
					listResults.addElement(results.get(i));
				}
				
			}
		});
		
		
	}

}
