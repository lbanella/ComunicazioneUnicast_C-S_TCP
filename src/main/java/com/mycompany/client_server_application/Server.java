package com.mycompany.client_server_application;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Banella Lorenzo
 */

public class Server {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private int porta;

    public Server(int porta) {
        this.porta=porta;
        try {
            
            this.serverSocket=new ServerSocket(this.porta);
            System.out.println("Il server è in ascolto sulla porta : "+this.porta);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Errore in fase di apertura e ascolto ");
        }
    }

    public Socket attendi() {
        
        try {
            if(serverSocket != null){
                //accept,istaura una connessione
             this.clientSocket= serverSocket.accept();
            System.out.println("Connessione stabilita con il Client");
            }
            
        }catch(BindException ex){
            System.err.println("Porta già in uso");
        }
        catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Errore nella fase di connessione con il client");
        }
        return clientSocket;
    }

    public void scrivi() {
        throw new UnsupportedOperationException("The method is not implemented yet.");
    }

    public void leggi() {
        throw new UnsupportedOperationException("The method is not implemented yet.");
    }

    public void chiudi() {
        try {
                // Chiudo il socket 
                this.clientSocket.close();
                System.out.println("Connessione chiusa.");
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public void termina() {
        try {
            //close
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
