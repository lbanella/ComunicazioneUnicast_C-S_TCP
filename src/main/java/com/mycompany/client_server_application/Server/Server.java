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
    private boolean connessione;
    private String messaggioDaInviare;
    private String messaggioRicevuto;


    public Server(int porta) {
        this.porta=porta;
        this.scanner = new Scanner(System.in);
        try {
            this.serverSocket=new ServerSocket(this.porta);
            System.out.println("Il server è in ascolto sulla porta : "+this.porta);          
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
            clientSocket = serverSocket.accept(); // Il metodo bloccante accept() aspetta finché non arriva una richiesta
            System.out.println("Connessione stabilita con il client");
            output = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));   
            connessione = true;
            
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
        return clientSocket;
    }
    public void comunica(){
        while(!serverSocket.isClosed()){
            this.clientSocket=attendi();
            while(connessione){
                leggi();
                scrivi();
            }
            if(!serverSocket.isClosed() && !connessione){
                System.out.println("Vuoi spegnere il server ");
                boolean spegniServer = scanner.nextBoolean();
                scanner.nextLine();
                if(spegniServer){
                    chiudi();
                    termina();
                }
            }
                
        }

    }

    public void scrivi() {
        if(connessione){
            System.err.println("Scrivi :");
            this.messaggioDaInviare = scanner.nextLine();
            System.out.println("messaggioDaInviare inviato al client  : "+messaggioDaInviare);
            try {
                output.write(messaggioDaInviare);
                output.newLine();
                output.flush(); 
                if(messaggioDaInviare.equalsIgnoreCase("chiudi")){
                    connessione=false;
                }
                
            } catch (IOException ex) {
                System.err.println( ex.getMessage());
            }
        }
    }

    public void leggi() {
        if(connessione){
            try {
                messaggioRicevuto = input.readLine(); 
                System.out.println("Messaggio  inviato dal client: " + messaggioRicevuto); 
                if(messaggioRicevuto.equalsIgnoreCase("chiudi")){
                    connessione=false;
                }
            }
            catch (NullPointerException ex) {
                System.err.println(ex.getMessage());
           }
             catch (IOException ex) {
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
                //close
                serverSocket.close();
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
