package by.xar.server;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ReadKurs implements Runnable{
	String[] valutaname;
	public static ArrayList<Valuta> valuta=new ArrayList<>();	
	public void run() {

		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("C:/kurs.txt")));
			String lines;
			while((lines=br.readLine())!=null){
				String str[]=lines.split(",");
				for(int i=0;i<str.length;i++){
					String name=str[i].split("=")[0];
					final double kurs=Double.parseDouble(str[i].split("=")[1]);
					valuta.add(new Valuta(name,kurs));		
				}			
			}br.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Τΰιλ νε νΰδιεν");
		}	
	}
}

