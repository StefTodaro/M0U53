/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 *Definizione degli attributi dei personaggi presenti nelle stanze. Possono
 * avere oggetti e hanno un insieme di possibili dialoghi
 * @author Stefano
 */
public class Character extends AdvObject implements java.io.Serializable {

    private final Map<Integer, String> dialogue = new HashMap<>();
    private final List<AdvObject> objects = new ArrayList<>();
    //valore per muoversi all'interno della map dei dialoghi
    Integer dialogueId = 0;

    /**
     *
     * @param id
     */
    public Character(int id) {
        super(id);
    }

    /**
     *
     * @param id
     * @param name
     * @param description
     */
    public Character(int id, String name, String description) {
        super(id, name, description);
    }

    /**
     *
     * @param id
     * @param name
     * @param description
     * @param alias
     */
    public Character(int id, String name, String description, Set<String> alias) {
        super(id, name, description, alias);
    }

    /**
     *
     * @return
     */
    public Map<Integer, String> getDialogue() {
        return dialogue;
    }

    /**
     *
     * @return
     */
    public Integer getDialogueId() {
        return dialogueId;
    }

    /**
     *
     * @param dialogueId
     */
    public void setDialogueId(Integer dialogueId) {
        this.dialogueId = dialogueId;
    }

    /**
     *
     * @return
     */
    public List<AdvObject> getObjects() {
        return objects;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.dialogue);
        return hash;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Character other = (Character) obj;
        if (!Objects.equals(this.dialogue, other.dialogue)) {
            return false;
        }
        return true;
    }

}
