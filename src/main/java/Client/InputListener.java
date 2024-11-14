/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *Thread utilizzato per attendere un comando dal giocatore 
 * @author Stefano
 */
class InputListener extends Thread {
    JTextField input;
    boolean exit=false;
    
    

    public InputListener(JTextField input) {
        this.input = input;
    }

    @Override
    public void run() {
        while(!exit){
            input.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent event){     
                    exit=true;
                }
            });
            
            try {
                Thread.sleep(350);
            } catch (InterruptedException ex) {
                Logger.getLogger(InputListener.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
        exit=false;
    }
}
