/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementi;

import java.io.PrintWriter;

/**
 *Definizione della schermata iniziale e dei comandi ammessi al suo interno
 * @author Stefano
 */
public class TitleScreen extends GameSettings {

    /**
     *
     */
    public TitleScreen() {
    }

    /**
     *Definisce i comandi utilizzabile nella schermata principale. 
     *Inoltre imposta la stanza corrispondente alla schermata
     */
    public void init() {
        Command commands = new Command(CommandType.COMMAND, "guida");
        commands.setAlias(new String[]{"g", "guide", "info", "tasti", "help","comandi"});
        getCommands().add(commands);

        Command start = new Command(CommandType.START, "start");
        start.setAlias(new String[]{"inizia", "avvia", "incomincia"});
        getCommands().add(start);

        Command story = new Command(CommandType.STORY, "storia");
        story.setAlias(new String[]{"trama", "storia", "prologo"});
        getCommands().add(story);
        
         Command end = new Command(CommandType.END, "end");
        end.setAlias(new String[]{"end", "fine", "esci", "muori", "ammazzati", "ucciditi", "suicidati", "exit"});
        getCommands().add(end);

        Room titolo = new Room(0, "M0U53", "Benvenuto nell'avventura grafica \"M0U53\". Scrivi \"start\" per incominciare una nuova partita"
                + " o riprendere una partita in corso. Scrivi \"guida\" per avere la lista dei comandi di "
                + "gioco. Scrivi \"storia\" per scroprire la trama del gioco. Scrivi \"esci\" per chiudere il gioco");

        setCurrentRoom(titolo);

    }

    /**
     *Definisce le azioni eseguite dai comandi della schermata
     * @param p risultato della parserizzazione del comando del giocatore
     * @param out oggetto PrintWriter utilizzato per scrivere su un output stream.
     * @return
     */
    public boolean menuOption(ParserOutput p, PrintWriter out) {
        boolean title = true;
        if (p.getCommand() == null) {
            out.println("Scrivi \"start\" per incominciare una nuova partita o riprendere una partita in corso\nScrivi \"guida\" per "
                    + "avere la lista dei comandi di gioco\nScrivi \"storia\" per scroprire la trama del gioco\nScrivi \"esci\" per chiudere il gioco");
        } else {
            if (p.getCommand().getType() == CommandType.COMMAND) {
                out.println("la formulazione dei comandi è solo da esempio, specifica le tue azioni come preferisci.");
                out.println(" ");
                out.println("guida (g,guide,info): per visualizzare la lista dei comandi.");
                out.println("guarda (l,look,osserva): per sapere di più della stanza in cui ti trovi.");
                out.println("guarda oggetto: per osservare  un oggetto nella stanza ed avere la sua descrizione.");
                out.println("inventario (i,inv,zaino): per controllare gli oggetti nel tuo inverntario.");
                out.println("nord (n,north): per muoverti verso nord.");
                out.println("sud (s,south): per muoverti verso sud.");
                out.println("ovest (o,w,west): per muoverti verso ovest.");
                out.println("est (e,east): per muoverti verso est.");
                out.println("sopra (u,up,sali): per muoverti verso l'alto.");
                out.println("sotto (d,down,scendi,giu): per muoverti verso il basso.");
                out.println("parla (t,talk) con personaggio: per parlare con personaggi nella stanza.");
                out.println("prendi (take,afferra,raccogli) oggetto: per prendere oggetti presenti nella stanza.");
                out.println("lascia (d,drop,lascia) oggetto: per lasciare un oggetto nel tuo inventario.");
                out.println("dai (give,dona) oggetto a personaggio: per dare un oggetto dal tuo inventario ad un personaggio.");
                out.println("attacca (attack,colpisci) personaggio con oggetto: per attaccare un personaggio con un oggetto dall'inventario.");
                out.println("apri (open,spalanca) oggetto: per aprire un oggetto contenitore all'interno della stanza.");
                out.println("apri (open,spalanca) oggetto con oggetto: per sbloccare un oggetto con un altro(esempio:chiave).");
                out.println("chiudi (close) oggetto: per chiudere un contenitore.");
                out.println("usa (use,utilizza) oggetto su oggetto/personaggio: per utilizzare un oggetto dal tuo inventario su un oggetto nella stanza.");
                out.println("interagisci (interact) con oggetto: per interagire con un oggetto o un personaggio nella stanza.");
                out.println(" ");
                out.println("Ricorda: alcuni avvenimenti possono modificare la composizione delle stanze ma anche la loro descrizione"
                        + ", come quella di alcuni oggetti. Se non sai come procedere osserva bene gli oggetti per ottenre qualche indizio! ");
            } else if (p.getCommand().getType() == CommandType.STORY) {
                out.println("In questa avventura vestirai i panni di un Topo da laboratorio :Mickola. Mickola è la prima cavia su "
                        + " cui il farmaco MCP95 ha successo. Questo ha comportato un enorme aumento delle capacità cognitive di Mickola"
                        + ", facendogli raggiungere un QI di 153. Ora vieni quasi considerato un miracolo, un eroe dagli scienziati, e non"
                        + " è per niente una brutta sensazione. Ma ora guardiamo i lati negativi di questa storia: tu e la tua famiglia siete"
                        + " rinchiusi in questo laboratorio da generazioni, dove venite sottoposti ad ogni tipo di esperimento e test massacranti."
                        + "  Hai visto molti tuoi parenti uscire dalla tua teca ed eseguire gli ultimi test della loro vita."
                        + " Come se non bastasse l'inserviente, nonchè guardiano di questo laboratorio, ha un insano hobby di impagliare le"
                        + " cavie che meno gli vanno a genio. Ciliegina sulla torta, l'indomani verrai prelevato e analizzato. Cosa c'è "
                        + " che non va? Beh, di solito le analisi vengono fatte a cuore aperto, e i dottori non tendono a ricucire."
                        + " Quindi volente o nolente è la notte della fuga, per te e la tua famiglia. Devi trovare il modo di utilizzare "
                        + " la ruota all'interno della tua teca per uscire da lì dentro e una volta fuori prendere le chiavi dell'inserviente"
                        + " e utilizzarle per aprire l'unica uscita da questo posto. Semplice no? Inizierai l'avventura nel tuo nido, all'"
                        + "interno di una teca, dove vivi con la tua famiglia, soggetti su cui il farmaco MCP95 non ha avuto lo stesso successo."
                        + " Allora che dici? Ci diamo una mossa?");
            } else if (p.getCommand().getType() == CommandType.START) {
                title = false;
            } else if (p.getCommand().getType() == CommandType.END) {
                out.println("fine sessione");
            } 

        }

        return title;
    }

}
