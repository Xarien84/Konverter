package by.xar.server;

import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Window extends JFrame implements Runnable {
		static private Socket connection; // создаём соединение
	static private ObjectOutputStream output; //создаём поток
	static private ObjectInputStream input;	//создаём поток
	String selectedvaluta;
	String[] valutas={
			" ",
			"NON",
			"USD",
			"EUR",
			"RUR"
	};
	public Window(){
		//super(name);
	setLayout(new FlowLayout());
	setSize(300,300);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	setLocationRelativeTo(null);
	 final JTextField t1=new JTextField(10);
	 final JButton b1=new JButton("Send");
	 JComboBox<String> jcb=new JComboBox(valutas);	  
		setVisible(true);
		add(t1);
		add(b1);
		add(jcb);
		setVisible(true);
jcb.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent arg0) {
		
				if(jcb.getSelectedItem().equals("USD")){	
					selectedvaluta=(String)jcb.getSelectedItem();
					//sendData(jcb.getSelectedItem());
				}	
				if(jcb.getSelectedItem().equals("EUR")){
					selectedvaluta=(String)jcb.getSelectedItem();
					//sendData(jcb.getSelectedItem());
				}
				if(jcb.getSelectedItem().equals("RUR")){
					selectedvaluta=(String)jcb.getSelectedItem();
				}
				if(jcb.getSelectedItem().equals("NON")){
					selectedvaluta=(String)jcb.getSelectedItem();
				}			
			}	
		});
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				if(e.getSource()==b1){
				sendData(selectedvaluta);	
				sendData2(t1.getText());
				
			}
			}
		});	
	}

	public void run() {	
		try {			 
			while(true){
				connection=new Socket(InetAddress.getByName("127.0.0.1"),5678 );  
				output=new ObjectOutputStream(connection.getOutputStream());  
				input=new ObjectInputStream(connection.getInputStream());
				try {
					while(true){		
					JOptionPane.showMessageDialog(null, (String)input.readObject()); 
					}
					} catch (HeadlessException e) {	}
				  catch (ClassNotFoundException e) {}
			}
		} catch (UnknownHostException e) {
		} catch (IOException e) {}
	}
	
	private static void sendData(Object obj){ 
		try {
			output.flush(); 
			output.writeObject(obj); 
		} catch (IOException e) {	
		}
	}
	private static void sendData2(Object obj){ 
		try {
			output.flush(); 
			output.writeObject(obj); 
		} catch (IOException e) {	
		}
	}
}

