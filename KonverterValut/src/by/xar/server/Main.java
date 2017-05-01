package by.xar.server;

import javax.swing.JFrame;

public class Main extends JFrame {
	public static void main(String[] args) {		
		new Thread(new ReadKurs()).start();
		new Thread(new Window()).start(); 
		new Thread(new Connector()).start();
				 
	}
}
