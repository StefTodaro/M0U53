/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Launcher;

/**
 *
 * @author Stefano
 */
public class Launcher {
    
  public static void main(String[] args) throws ClassNotFoundException, Exception {
            
           // Avvia il server in un nuovo thread
        Thread serverThread = new Thread(() -> {
            try {
                Server.Server.main(args);
                System.out.println("Avviato il server");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Avvia il thread del server
        serverThread.start();
        
        // Attendi qualche secondo per dare tempo al server di iniziare
        Thread.sleep(5000);

        // Ora avvia il client
        System.out.println("Ora si avvia il client");
        Client.Client.main(args);
        
    }
    
}
