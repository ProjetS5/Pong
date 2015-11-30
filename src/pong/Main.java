package pong;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import pong.gui.Pong;
import pong.gui.Window;

/**
 * Starting point of the Pong application
 */
public class Main  {
	
	public static final int port = 3678;
	public static Socket socketClient;
	public static ServerSocket socketServeur;
	public static boolean host;
	
	public static void main(String[] args) {
		/* Création du socket client ou serveur en fonction des paramètres du programme */
		if(args.length > 0)
			try{
				socketClient = new Socket(args[0], port);
			}catch(UnknownHostException e){
				e.printStackTrace();
			}catch(IOException e){
				e.printStackTrace();
			}
		else{
			try{
				socketServeur = new ServerSocket(port);
				socketClient = socketServeur.accept();
				System.out.println("Connexion avec : "+socketClient.getInetAddress());
				host = true;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		/* Mise en place du Reader et du Writer */
		BufferedReader in = null;
		PrintWriter out = null;
		try{
	        in = new BufferedReader(
	          new InputStreamReader(socketClient.getInputStream()));
	        out = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(socketClient.getOutputStream())), 
                         true);
		}catch(IOException e){
			e.printStackTrace();
		}
        
		Pong pong = new Pong();
		Window window = new Window(pong);
		if(!host)
			pong.f1(in, out);
		window.displayOnscreen(in, out);
	}
}
