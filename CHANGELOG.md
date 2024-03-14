# Changelog

Questo file contiene tutte le modifiche al progetto  [ComunicazioneUnicast_C/S_TCP](https://github.com/lbanella/ComunicazioneUnicast_C-S_TCP.git)

## [Unreleased] - 12 Marzo 2024

### Added

- Creazione del progetto Java Maven sull'IDEE NetBeans
- Creazione della    [repository](https://github.com/lbanella/ComunicazioneUnicast_C-S_TCP.git)
- Creato file [README](https://github.com/lbanella/ComunicazioneUnicast_C-S_TCP/blob/master/README.md)
- Caricata classe [Client.java](https://github.com/lbanella/ComunicazioneUnicast_C-S_TCP/blob/master/src/main/java/com/mycompany/client_server_application/Client.java)
- Caricata classe [MainClient.java](https://github.com/lbanella/ComunicazioneUnicast_C-S_TCP/blob/master/src/main/java/com/mycompany/client_server_application/MainClient.java)
- Caricata classe [Server.java](https://github.com/lbanella/ComunicazioneUnicast_C-S_TCP/blob/master/src/main/java/com/mycompany/client_server_application/Server.java)
- Caricata classe [MainServer.java](https://github.com/lbanella/ComunicazioneUnicast_C-S_TCP/blob/master/src/main/java/com/mycompany/client_server_application/MainServer.java)

## [Unreleased] - 14 Marzo 2024

### Added
- Creato file [CHANGELOG](https://github.com/lbanella/ComunicazioneUnicast_C-S_TCP/blob/master/CHANGELOG.md)
 
#### Classe Client
- aggiunto attributo private Socket socket;

#### Classe d'avvio MainClient
- aggiunte nel metodo main :
    - metodo costrotture di Client per realizzare un `istanza` della classe.
    - metodo `conneti` di Client
    - metodo `chiudi` di Client


#### Classe d'avvio MainServer
- aggiunte nel metodo main :
    - metodo costrotture di Server per realizzare un `istanza` della classe.
    - metodo : `attendi` di Server
    - metodo : `termina` di Server

### Changed

#### Classe Client
- realizzato metodo costruttore :  `Client(String nomeDefault, String coloreDefault)`
- realizzato metodo `connetti(String nomeServer,int portaServer)`
- realizzato metodo : `chiudi()`



#### Classe Server
- realizzato metodo costruttore :  `Server(int porta)`
- realizzato metodo `attendi()`
- realizzato metodo : `termina()`







