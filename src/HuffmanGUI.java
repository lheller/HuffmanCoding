/**
 * HuffmanGUI.java
 * @author LindseyHeller-- LWH2115
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.text.JTextComponent;


public class HuffmanGUI extends JFrame
{
	private JTextArea inputArea, outputArea, frequencyArea;
	private JScrollPane scrollpane;
	private HuffmanDisplayPanel panel;

	public HuffmanGUI()
	{
		inputArea = new JTextArea(2,1);
		outputArea = new JTextArea(2,1);
		outputArea.setLineWrap(true);
		outputArea.setWrapStyleWord(true);

		setSize(1250, 1250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
		JLabel inputLabel = new JLabel("Enter Text Message Here: ");
		inputLabel.setForeground(Color.BLUE);
		inputPanel.add(inputLabel);
		inputPanel.add(inputArea);
		
		JButton actionButton = new JButton("Build Huffman Tree");
		actionButton.setForeground(Color.PINK);
		actionButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event) 
			{
				String inputText = inputArea.getText();
				HuffmanTree theTree = new HuffmanTree(inputText);
				
				Font thatfont = new Font("Cambria", Font.BOLD, 12);
				frequencyArea.setFont(thatfont);
				frequencyArea.setText(theTree.getOutput());
				
				panel = new HuffmanDisplayPanel(theTree);
				panel.setPreferredSize(new Dimension(300, 300));
				scrollpane = new JScrollPane(panel);
				getContentPane().add(scrollpane, BorderLayout.CENTER);
				
				Font thisfont = new Font("Cambria", Font.BOLD, 16);
				outputArea.setFont(thisfont);
				outputArea.setText(theTree.returnHuffman());
			}
		});

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(2,1));
		topPanel.add(inputPanel);
		topPanel.add(actionButton);
		
		
		JPanel outputPanel = new JPanel();
		outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.X_AXIS));
		JLabel outputLabel = new JLabel("Binary Digits of Encoded Message: ");
		outputLabel.setForeground(Color.GRAY);
		outputArea.setForeground(Color.GRAY);
		outputPanel.add(outputLabel);
		outputPanel.add(outputArea);
		getContentPane().add(outputPanel, BorderLayout.SOUTH);

		frequencyArea = new JTextArea();
		getContentPane().add(frequencyArea, BorderLayout.WEST);
		getContentPane().add(topPanel, BorderLayout.NORTH);
		setVisible(true);
	}

	public static void main(String[] args)
	{
		HuffmanGUI newHuffman = new HuffmanGUI();
	}
}




