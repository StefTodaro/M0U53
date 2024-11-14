/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;


import Elementi.GameOver;
import Elementi.GameSettings;
import Elementi.Parser;
import Elementi.ParserOutput;
import Elementi.TitleScreen;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stefano
 */
public class Server {

    private GameSettings game;
    private GameOver go;
    private TitleScreen title;
    private String command;
    private final Parser parser;
    File load = new File("M0U5E.sav");

    String room;
    String message;
    //determina che si sia entrati in game over
    boolean gameover = false;
    //determina che il gioco è nella schermata principale
    boolean start = true;

    /**
     *
     * @return
     */
    public boolean isGameover() {
        return gameover;
    }

    /**
     *
     * @param gameover
     */
    public void setGameover(boolean gameover) {
        this.gameover = gameover;
    }

    /**
     *
     * @return
     */
    public boolean isStart() {
        return start;
    }

    /**
     *
     * @param start
     */
    public void setStart(boolean start) {
        this.start = start;
    }

    /**
     *
     * @param game
     * @param go
     * @param title
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Server(GameSettings game, GameOver go, TitleScreen title) throws FileNotFoundException, IOException, ClassNotFoundException {
        this.game = game;
        this.go = go;
        this.title = title;
        try {
            this.title.init();
        } catch (Exception ex) {
            System.err.println(ex);
        }

        parser = new Parser();

    }

    /**
     *Nel caso di esistenza di un file di salvataggio carica  le informazioni
     * in modo che il gioco continui dall'ultimo salvataggio
     * @param game impostazioni del gioco
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void loadGame(GameSettings game) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(load);
        ObjectInputStream ois = new ObjectInputStream(fis);
        this.game = (GameSettings) ois.readObject();

    }

    /**
     *Invia al server le informazioni della stanza attuale 
     * @param out oggetto PrintWriter utilizzato per scrivere su un output stream.
     */
    public void clientSender(PrintWriter out) {
        if (start) {
            out.println(title.getCurrentRoom().getName());
            out.println(title.getCurrentRoom().getDescription());
        } else {
            out.println(game.getCurrentRoom().getName());
            out.println(game.getCurrentRoom().getDescription());
        }
    }

    /**
     *Esegue azioni differenti nei casi in cui il gioco sia nell schermata 
     * iniziale, sia in game over o sia in normale esecuzione. Inoltre richiama
     * la parserizzazione dei comandi inviati dall'utente per poi richiamare
     * i comandi
     * @param out oggetto PrintWriter utilizzato per scrivere su un output stream.
     * @param command stringa inviata dall'utente
     * @throws FileNotFoundException
     * @throws IOException
     * @throws Exception
     */
    public void run(PrintWriter out, String command) throws FileNotFoundException, IOException, Exception {
        if (command != null) {
            if (!gameover) {

                if (start) {
                    ParserOutput p = parser.parse(command, title.getCommands(), title.getCurrentRoom().getObjects(), title.getCurrentRoom().getCharacters(), title.getInventory());
                    start = title.menuOption(p, out);

                    if (!start) {
                        if (load.exists()) {
                            loadGame(game);
                        } else {
                            this.game.init();
                        }
                        out.println("start");
                        clientSender(out);
                    }
                } else {
                    ParserOutput p = parser.parse(command, game.getCommands(), game.getCurrentRoom().getObjects(), game.getCurrentRoom().getCharacters(), game.getInventory());
                    game.nextMove(p, out);
                }
            } else {
                gameover = go.gameOverChoice(command, out);
            }

        }

    }

    /**
     *
     * @param args
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException, Exception {
        ServerSocket s = new ServerSocket(6666);
        try {
            // si blocca fino a quando non c’è una connessione
            Socket socket = null;
            // connessione accettata
            try {
                socket = s.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                // Flush automatico con PrintWriter:
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                Server engine = new Server(new GameSettings(), new GameOver(), new TitleScreen());
                engine.clientSender(out);
                    

                while (true) {
                      
                   
                    engine.command = in.readLine();

                    if (engine.command.equals("game over")) {
                        engine.setGameover(true);
                    }
                    
                    if(engine.command.equals("menu")){
                        engine.setStart(true);
                        engine.clientSender(out);
                         if(engine.load.delete()){
                             System.out.println("File deleted");
                         }else{
                            System.out.println("error in deleting file"); 
                         }
                    }
                     

                    if (engine.command.equals("fine sessione")) {
                        break;
                    }
                    
                    if (engine.command.equals("salvataggio")) {
                        FileOutputStream fos = null;
                        try {
                            fos = new FileOutputStream("M0U5E.sav");
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        ObjectOutputStream oos = null;
                        try {
                            oos = new ObjectOutputStream(fos);
                        } catch (IOException ex) {
                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            oos.writeObject(engine.game);
                        } catch (IOException ex) {
                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            oos.flush();
                        } catch (IOException ex) {
                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            oos.close();
                        } catch (IOException ex) {
                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        out.println("Progressi salvati\n");
                        out.println("stop");
                        continue;
                    }
                    engine.run(out, engine.command);
                    out.println("stop");

                }
                // chiude sempre i due socket...
            } finally {
                socket.close();
            }
        } finally {
            s.close();//ServerSocket
        }
    }
}
