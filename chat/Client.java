package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
* Client für einen ganz einfachen Chat
*/
public class Client {
	
	public static String s;
	
/**
* baut die Verbindung zum Server an der angegebenen Adresse auf
* @param ip IP-Adresse des Servers
*/
	public void verbindungAufbauen(String ip){
		InetAddress ipAdresse;
		Socket so = null;
		try {
			ipAdresse = InetAddress.getByName(ip);
			so = new Socket(ipAdresse, Server.ANMELDEPORT);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		try {
			//hier Nachricht senden
			OutputStream aus = so.getOutputStream();
			PrintStream ps = new PrintStream(aus);
			ps.println("Test nachricht vom client");
			ps.flush();
			
			
			//hier Antwort lesen
			InputStream ein = so.getInputStream();
			InputStreamReader isr = new InputStreamReader(ein);
			BufferedReader br = new BufferedReader(isr);
			s = br.readLine();
			
				
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			so.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

/**
* startet den Client
*/
	public static void main(String[] args) {
		Client client = new Client();
		client.verbindungAufbauen("localhost");
	}

}
