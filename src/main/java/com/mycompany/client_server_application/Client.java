package com.mycompany.client_server_application;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Banella Lorenzo
 */

public class Client {

    private String nome;
    private String colore;
    private Socket socket;

    public Client(String nomeDefault, String coloreDefault) {
        this.nome = nomeDefault;
        this.colore = coloreDefault;
    }

    public void connetti(String nomeServer, int portaServer) {
        System.out.println(" ______________________________\n");
        System.out.println("      Client "+nome+" in esecuzione\n");
        System.out.println(" ______________________________\n");
        try {
            this.socket = new Socket(nomeServer, portaServer); 
            //chiudi();
        } catch(UnknownHostException ex){ 
            System.err.println("Host sconosciuto!");
            System.err.println( ex.getMessage());
        } catch (IOException e){
            System.err.println("Errore!");
            System.err.println( e.getMessage());
        }
        
    }

    public void scrivi() {
        throw new UnsupportedOperationException("The method is not implemented yet.");
    }

    public void leggi() {
        throw new UnsupportedOperationException("The method is not implemented yet.");
    }

    public void chiudi() {
            try {
                // Chiudi il socket solo se è stato inizializzato correttamente
                this.socket.close();
                System.out.println("Connessione chiusa.");
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }

}
