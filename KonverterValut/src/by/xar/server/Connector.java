package by.xar.server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Connector extends ReadKurs implements Runnable {
	
	private static ServerSocket server;
	private static Socket connection;
	private static ObjectInputStream input;
	private static ObjectOutputStream output;
	
	public void run(){	
			try {
				server=new ServerSocket(5678,10);			
			while(true){
				connection=server.accept();
				output=new ObjectOutputStream(connection.getOutputStream());
				input=new ObjectInputStream(connection.getInputStream());	
				while(true){
				try {
				String valutaname=(String) input.readObject();
				double exchsumm=Double.parseDouble((String) input.readObject());
				for(Valuta val:valuta){
					if(valutaname.equals(val.getName())){					
					output.writeObject(val.getKurs()*exchsumm+"");
					}
				}				
				} catch (ClassNotFoundException e) {}
			}
		}			
				} catch (IOException e) {}
	}
	
	public static void sendData(Object obj){
		try {
			output.flush();
			output.writeObject(obj);
		} catch (IOException e) {}
	}	
	
}

