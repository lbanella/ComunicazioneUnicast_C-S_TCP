package com.mycompany.client_server_application.Server;

import java.io.*;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;


/**
 *
 * @author Banella Lorenzo
 */

public class Server {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private int porta;
    private BufferedReader input;
    private BufferedWriter output;
    private Scanner scanner;
    

    public Server(int porta) {
        this.porta=porta;
        this.scanner = new Scanner(System.in);
        try {
            this.serverSocket=new ServerSocket(this.porta);
            System.out.println("Il server è in ascolto sulla porta : "+this.porta);
            this.clientSocket=attendi();
            if(clientSocket != null){
                System.out.println("Connessione stabilita con il Client");
                output = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));   
            }
             
        }
        catch(BindException ex){
            System.err.println("Porta gia in uso");
            System.err.println(ex.getMessage());
        }
        
        catch (IOException ex) {
            System.err.println("Errore nella fase di connessione con il client");
            System.err.println(ex.getMessage());
        }
    }

    public Socket attendi() {
        try {
            //accept,istaura una connessione
            return serverSocket.accept(); 
        }
        catch (NullPointerException ex) {
            System.err.println("Errore nella fase di connessione con il client");
            System.err.println(ex.getMessage());
            return null;
        } 
        catch (SocketException ex) {
            System.err.println("Errore durante l'attesa della connessione: " + ex.getMessage());
            System.err.println(ex.getMessage());
            return null;
            
        } 
        catch (IOException ex) {
            System.err.println("Errore durante l'attesa della connessione");
            System.err.println(ex.getMessage());
            return null;
        }
    }
    public void comunica(){
        while(!serverSocket.isClosed() && !clientSocket.isClosed() ){
            leggi();
            scrivi();
        }
    }

    public void scrivi() {
        if(!serverSocket.isClosed() && !clientSocket.isClosed()){

        
            System.err.println("Scrivi :");
            String messaggio = scanner.nextLine();
            System.out.println("Messaggio inviato al client  : "+messaggio);
            try {
                output.write(messaggio);
                output.newLine();
                output.flush(); 
                if(messaggio == null){
                    chiudi();
                    termina();
                }else if(messaggio.equalsIgnoreCase("chiudi") ){
                    chiudi();
                    termina();
                }
                
            } catch (IOException ex) {
                System.err.println( ex.getMessage());
            }
        }
    }

    public void leggi() {
        if(!serverSocket.isClosed() && !clientSocket.isClosed()){
            String messaggioRicevuto=null;
            try {
                messaggioRicevuto = input.readLine(); //assegna alla variabile il messaggio ricevuto dal client
                System.out.println("Messaggio inviato dal client: " + messaggioRicevuto); 
                if(messaggioRicevuto == null){
                    chiudi();
                    termina();  
                }else if(messaggioRicevuto.equalsIgnoreCase("chiudi") ){
                    chiudi();
                    termina();  
                }
            } catch (IOException ex) {
                 System.err.println(ex.getMessage());
            }
        }
       
    }

    public void chiudi() {
        try {
            if(serverSocket != null){
                // Chiudo il socket 
                this.clientSocket.close();
                System.out.println("Connessione chiusa.");
            }
            } catch (IOException ex) {
                System.err.println("Errore");
                System.err.println(ex.getMessage());
            }
    }

    public void termina() {
        try {
            if(serverSocket != null && clientSocket.isClosed()){
                //close
                serverSocket.close();
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
