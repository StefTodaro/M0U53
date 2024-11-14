/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 *
 * @author Stefano
 */
public class Client {

    static BufferedReader in;
    static PrintWriter out;
    static JTextArea messageArea;
    static JFrame frame;
    static JTextField input;
    static JLabel roomLabel;

    boolean exit = false;

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }
    
    

    /**
     *
     */
    public Client() {
    }

    /**
     *Imposta la schermata di gioco tramite java swing
     */
    public static void setGUI() {

        frame = new JFrame("M0U53");
        frame.setSize(1100, 601);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.BLACK);
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15)); // Imposta i margini

        messageArea = new JTextArea();
        messageArea.setEditable(false);
        messageArea.setLineWrap(true);
        messageArea.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        messageArea.setForeground(Color.GREEN);
        messageArea.setBackground(Color.BLACK);
        messageArea.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 15));

        JScrollPane scroll = new JScrollPane(messageArea);
        scroll.getVerticalScrollBar().setBackground(Color.BLACK);
        scroll.setPreferredSize(new Dimension(800, 450));
        scroll.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 60));
        scroll.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
        contentPanel.add(scroll, BorderLayout.CENTER);

        roomLabel = new JLabel("Stanza");
        roomLabel.setForeground(Color.GREEN);
        roomLabel.setBackground(Color.WHITE);
        roomLabel.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        contentPanel.add(roomLabel, BorderLayout.NORTH);

        input = new JTextField();
        input.setText("");
        input.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        input.setForeground(Color.GREEN);
        input.setBackground(Color.BLACK);
        input.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        contentPanel.add(input, BorderLayout.SOUTH);

        frame.add(contentPanel, BorderLayout.CENTER);

        frame.setVisible(true);
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
     frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent we) { 
       int result = JOptionPane.showConfirmDialog(frame,
            "I progressi non salvati verranno persi. Sicuro di voler uscire?", "",
            JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION){
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        out.println("fine sessione");
        }
        else if (result == JOptionPane.NO_OPTION)
          frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      }
    });

  }
    

    /**
     * Modifica la la schermata quando avviene un cambio di stanza nel gioco.
     * Modifica l'intestazione della schermata inserendo il nome della stanza
     * raggiunta e fa comparire a schermo la descrizione della stanza
     * @param in Buffer di lettura delle informazioni provenienti dal Server
     * @throws IOException
     */
    public void updateGUI(BufferedReader in) throws IOException {
        String room = in.readLine();
        String description = in.readLine();
        roomLabel.setText(room);
        messageArea.append(room + "\n");
        messageArea.append("—————————————————————————————————————————————————————————————————————————————\n");
        messageArea.append(description + "\n");
        messageArea.append("—————————————————————————————————————————————————————————————————————————————\n");
    }

    /**
     *Fa comparire a schermo tutte le stringhe provenienti dal Server e inoltre
     * contiene  comportamenti alternativi per messaggi particolari.
     * stop: interrompe la stampa
     * fine sessione: imposta la variabile exit true, facendo terminare il programma
     * salva: invia al server la stringa per avviare il salvataggio su file
     * room: richiama il metodo per l'aggiornamento della schermata
     * game over: invia la stringa al server per avviare la schermata di game over
     * start:effettua un clear della schermata e aggiorna la schermata con le 
     * informazioni della stanza attuale
     * menu: invia la stringa al server per effettuare un ritorno alla schermata 
     * iniziale
     * @param in
     * @throws IOException
     */public void appendText(BufferedReader in) throws IOException {
        String text = "begin";
        while (true) {
            text = in.readLine();
            if (text.equals("stop")) {
                break;
            }

            if (text.equals("fine sessione")) {
               frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                continue;
            }

            if (text.equals("salva")) {
                out.println("salvataggio");

                text = in.readLine();
                if (text.equals("stop")) {
                    continue;
                }
            }

            if (text.equals("room")) {
                updateGUI(in);
                continue;
            }

            if (text.equals("game over")) {
                out.println("game over");
                 text = in.readLine();
                continue;
            }
            
            if (text.equals("start")) {
                messageArea.setText("");
                updateGUI(in); 
                continue;
            }
            if (text.equals("menu")) {
                out.println("menu");
                text = in.readLine();
                 InputListener inputListener = new InputListener(input);
                 input.setText("");
               inputListener.run();
                 messageArea.setText("");
                 updateGUI(in);
                break;

            }

            if (text.equals("")) {
                continue;
            }

            messageArea.append(text + "\n");

        }
        messageArea.append("—————————————————————————————————————————————————————————————————————————————\n");
        messageArea.setCaretPosition(messageArea.getText().length());

    }

    /**
     *
     * @param args
     * @throws UnknownHostException
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {

        InetAddress addr = InetAddress.getByName("localhost");
        Socket socket = new Socket(addr, 6666);
        setGUI();

        try {
            Client client = new Client();
            client.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Flush automatico con PrintWriter:
            client.out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

            client.updateGUI(client.in);
            InputListener inputListener = new InputListener(input);
            inputListener.start();

            // Avvia il thread
            while (!client.exit) {

                inputListener.run();
                
                if(input.getText().equals("")){
                    continue;
                }

                messageArea.append(input.getText() + "\n");

                client.out.println(input.getText());

                messageArea.append("—————————————————————————————————————————————————————————————————————————————\n");
                client.appendText(client.in);
                input.setText("");
            }
        } finally {
            messageArea.append("Addio!\n");
            socket.close();
            
        }
    }

}
