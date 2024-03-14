package com.mycompany.client_server_application;

/**
 *
 * @author Banella Lorenzo
 */

public class MainClient {

    public static void main(String[] args) {
        Client client= new Client("Lorenzo","Rosso");
        client.connetti("127.0.0.1",1789);
        client.chiudi();
    }
    
    
    
}
