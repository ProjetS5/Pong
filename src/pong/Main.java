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
import java.util.Scanner;

import pong.gui.Pong;
import pong.gui.Window;

/**
 * Starting point of the Pong application
 */
public class Main  {
	
	public static int port = 3800;
	public static Socket socketClient;
	public static ServerSocket socketServeur;
	public static boolean turn;
	public static boolean connection;
	
	public static void main(String[] args) {
		/* Création du socket client ou serveur en fonction des paramètres du programme */
		Scanner sc = new Scanner(System.in);
		String str = "A";
		while(!str.equals("E")){
			connection = false;
			System.out.println("Host or client ? (H/C) (E to escape)");
			str = sc.nextLine();
			if(str.equals("H")){
				
				turn = true;
				System.out.println("Port ? (default: " + port + " )");
				if(Integer.parseInt(str = sc.nextLine()) < 65565)
					port = Integer.parseInt(str);
				
				try{
					socketServeur = new ServerSocket(port);
					socketClient = socketServeur.accept();
					System.out.println("Connexion avec : "+socketClient.getInetAddress());
					connection = true;
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			else if(str.equals("C")){
				
				turn = false;
				System.out.println("Port ? (default: " + port + " )");
				if(Integer.parseInt(str = sc.nextLine()) < 65565)
					port = Integer.parseInt(str);
				
				System.out.println("IP ?");
				try{
					socketClient = new Socket(sc.nextLine(), port);
					connection = true;
				}catch(UnknownHostException e){
					e.printStackTrace();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
			
			if(connection){
				
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
				
				Pong pong = new Pong(in, out);
				Window window = new Window(pong);
				window.displayOnscreen();
				
				while(!pong.victory()){
					pong.initiate(turn);
					turn = !turn;
					window.play();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {};
				}
				try{
					in.close();
					out.close();
					socketClient.close();
					if(socketServeur.isBound())
						socketServeur.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		sc.close();
	}
}
