/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementi;

/**
 *Tipologie dei comandi presenti in gioco
 * @author Stefano
 */
public enum CommandType implements java.io.Serializable{
    END, COMMAND,INVENTORY, NORTH, SOUTH, EAST, WEST, UP,DOWN, OPEN, CLOSE, PUSH, PULL, DROP, WALK_TO, PICK_UP, TALK_TO, GIVE, USE, LOOK_AT, ATTACK, INTERACT, SAVE, START,STORY;

    
}
