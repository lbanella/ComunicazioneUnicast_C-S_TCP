package com.mycompany.client_server_application.Client;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


/**
 *
 * @author Banella Lorenzo
 */

 public class Client {

    private String nome;
    private String colore;
    private Socket socket;
    private BufferedReader input;
    private BufferedWriter output;
    private Scanner scanner;
    private static final String RESET = "\u001B[0m";
    private boolean connessione;
    private String messaggiopuoRicevere;
    private String mMessaggioDaInviare;
    private boolean puoRicevere;
    private boolean puoInviare;

    public Client(String nomeDefault, String coloreDefault) {
        this.nome = nomeDefault;
        this.colore = coloreDefault;
        this.scanner = new Scanner(System.in);
    }

    public void connetti(String nomeServer, int portaServer) {
        System.out.println(colore+"Client " + nome + " in esecuzione"+RESET);
        try {
            this.socket = new Socket(nomeServer, portaServer);
            output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(colore+"Connessione avvenuta con il server"+RESET);
            this.connessione=true;
            puoInviare = true; // Resetta il flag per il prossimo invio
        } catch (ConnectException ex) {
            System.err.println("Il server non è in ascolto!");
            System.err.println(ex.getMessage());
        } catch (UnknownHostException ex) {
            System.err.println("Host sconosciuto!");
            System.err.println(ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Errore!");
            System.err.println(ex.getMessage());
        }
    }

    public void comunica() {
        while (connessione) {
            scrivi();
            leggi();
        }
    }

    public void scrivi() {
        if (connessione && puoInviare) {
            System.out.println(colore+"Scrivi :"+RESET);
            String messaggio = scanner.nextLine();
            System.out.println(colore+"Messaggio puoInviare al server  : "+messaggio+RESET);
            try {
                output.write(messaggio);
                output.newLine();
                output.flush();
                if (messaggio.equalsIgnoreCase("chiudi")) {
                    chiudi();
                }else{
                    puoRicevere = true; // Imposta il flag dopo la ricezione del messaggio
                    puoInviare = false; // Resetta il flag per il prossimo invio
                }
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    public void leggi() {
        if (connessione && puoRicevere) {
            try {
                messaggiopuoRicevere = input.readLine();
                System.out.println(colore+"Messaggio puoInviare dal server : "+messaggiopuoRicevere+RESET);
                if(messaggiopuoRicevere == null){
                    chiudi();
                }else if(messaggiopuoRicevere.equalsIgnoreCase("chiudi") ){
                    chiudi();
                }else{
                    puoRicevere = false; // Imposta il flag dopo la ricezione del messaggio
                    puoInviare = true; // Resetta il flag per il prossimo invio
                }
                
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    public void chiudi() {
        try {
                puoRicevere = false; // Imposta il flag dopo la ricezione del messaggio
                puoInviare = false; // Resetta il flag per il prossimo invio
                connessione=false;
                socket.close();
                System.out.println("Connessione chiusa.");
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}