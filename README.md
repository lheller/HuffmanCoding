HuffmanCoding
=============


To compile: 

		javac HuffmanGUI.java
		
To run: 

		javac HuffmanGUI


This program creates a Huffman code for a text message (http://en.wikipedia.org/wiki/Huffman_coding).
Input text into the alotted area and click the "Build Huffman Tree" after which:
	1) Character frequencies for each character in the message are computed 
	2) A Huffman Tree is created where:
	 	Each leaf node displays the character and its frequency in the node.
	 	Each interior node displays a "-" and the combined frequencies of its children.
	 	Each Huffman code word is displayed below each leaf node of the tree.
			*A binary 0 is used for a right child and a binary 1 is used for a left child.*

For each character present in the text message, the character, frequency, ascii code, and Huffman coding are printed in an area to the right of the Huffman Tree.
The binary digits of the encoded message using the Huffman code are displayed in Grey at the bottom of the screen.


*Restart program to get the enoding for another message after running the program once.*
