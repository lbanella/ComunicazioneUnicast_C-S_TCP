package com.mycompany.client_server_application;

/**
 *
 * @author Banella Lorenzo
 */

public class MainServer {

    public static void main(String[] args) {
      Server server=new Server(1789);
      server.attendi();
      server.termina();
    }
}
