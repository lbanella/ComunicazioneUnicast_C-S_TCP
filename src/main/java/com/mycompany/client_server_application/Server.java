package com.mycompany.client_server_application;

import java.io.IOException;
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
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Socket attendi() {
        
        try {
            //accept,istaura una connessione
             this.clientSocket= serverSocket.accept();
              //scambio dati
           System.out.println("Connessione Effettuata");
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
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
        throw new UnsupportedOperationException("The method is not implemented yet.");
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
