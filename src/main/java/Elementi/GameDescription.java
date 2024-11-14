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
 *Definizioni degli elementi fondamentali dell'avventura
 * @author Stefano
 */
public abstract class GameDescription implements java.io.Serializable {

    private final List<Room> rooms = new ArrayList<>();

    private final List<Command> commands = new ArrayList<>();

    private final List<AdvObject> inventory = new ArrayList<>();

    private Room currentRoom;

    /**
     *
     * @return
     */
    public List<Room> getRooms() {
        return rooms;
    }

    /**
     *
     * @return
     */
    public List<Command> getCommands() {
        return commands;
    }

    /**
     *
     * @return
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     *
     * @param currentRoom
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     *
     * @return
     */
    public List<AdvObject> getInventory() {
        return inventory;
    }

    /**
     *Metodo astratto per l'inizializzazione di oggetti GameDescription 
     * @throws Exception
     */
    protected abstract void init() throws Exception;

    /**
     *Metodo astratto per la definizione per l'azione dei comandi
     * @param p risultato del parser, ovvero il comando dell'utente
     * @param out oggetto PrintWriter utilizzato per scrivere su un output stream.
     */
    protected abstract void nextMove(ParserOutput p, PrintWriter out);

}
