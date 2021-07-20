package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
* Server eines ganz einfachen Chats
*/
public class Server {
/**
* Port, unter dem der Server auf eingehenden Verbindungen wartet
*/
	public static int ANMELDEPORT = 7782;
	
	public String s;
	
/**
* startet den Server und wartet auf eingehende Verbindungen vom Client
*/
	private void verbindungAnnehmen(){
		ServerSocket seso = null;
		
		Socket so = null;
		
		try {
			seso = new ServerSocket(ANMELDEPORT);
			System.out.println("warte auf client");
			so = seso.accept();
		} catch (IOException e) {
				e.printStackTrace();		
		} 
			
		try{
			//hier eingehende Nachricht lesen
			InputStream ein = so.getInputStream();
			InputStreamReader isr = new InputStreamReader(ein);
			BufferedReader br = new BufferedReader(isr);
			s = br.readLine();
		
			
			//hier Antwort senden
			OutputStream aus = so.getOutputStream(); 
			PrintStream ps = new PrintStream(aus);
			ps.println("Hallo client ich habe deine nachricht erhalten");
		
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		try {
			seso.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
/**
* startet den Server
*/
	public static void main(String[] args){
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Client.main(args);
				
			}
		});
		t.start();
		
		Server server = new Server();
		server.verbindungAnnehmen();
		System.out.println("verbunden");
		
	
		System.out.println(server.s);
		System.out.println(Client.s);
		System.out.println("done");
	}

}
