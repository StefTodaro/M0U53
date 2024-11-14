/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementi;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *Schermata di game over che permette al giocatore di scegliere se uscire dal 
 * gioco o continuare 
 * @author Stefano
 */
public class GameOver extends GameSettings {

    static boolean go = true;

    /**
     *
     */
    public GameOver() {
    }

    final List<String> conferma = new ArrayList(List.of("si", "ok", "accetto", "k", "yes", "y", "continua","vai"));
    final List<String> rifiuto = new ArrayList(List.of("no", "rifiuto", "esci", "chiudi", "spegni"));

    /**
     *Esegue azioni differenti in base all'input dell'utente. In caso di input
     *non riconosciuto viene richiesta conferma
     * @param risp risposta di input dell'utente
     * @param out oggetto PrintWriter utilizzato per scrivere su un output stream.
     * @return booleano che conferma che è stata effettuata una scelta
     */
    public boolean gameOverChoice(String risp, PrintWriter out) {
        go=true;
        if (conferma.contains(risp) && risp!=null) {
            go = false;
            out.println("Lo schermo del monitor si accende e vedi la schermata iniziale di \"Fantastic Fantasy VII\". Ora puoi avviare la partita");
        } else if (rifiuto.contains(risp) && risp!=null) {
            go = false;
            out.println("fine sessione");
        } else {
            out.println("Vuoi ricominciare al momento prima di giocare a \"Fantastic Fantasy\" o vuoi uscire dal gioco?(continua/esci)");
        }
        return go;
    }

}
