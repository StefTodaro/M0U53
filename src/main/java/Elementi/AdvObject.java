/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementi;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *Definizione degli attributi degli oggetti presenti all'interno delle stanze 
 * @author Stefano
 */
public class AdvObject implements java.io.Serializable {

    private final int id;

    private String name;

    private String description;

    private Set<String> alias;

    private boolean visible = true;

    private boolean openable = false;

    private boolean pickupable = true;

    private boolean open = false;

    /**
     *
     * @param id
     */
    public AdvObject(int id) {
        this.id = id;
    }

    /**
     *
     * @param id
     * @param name
     */
    public AdvObject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     *
     * @param id
     * @param name
     * @param description
     */
    public AdvObject(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /**
     *
     * @param id
     * @param name
     * @param description
     * @param alias
     */
    public AdvObject(int id, String name, String description, Set<String> alias) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.alias = alias;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public boolean isOpenable() {
        return openable;
    }

    /**
     *
     * @param openable
     */
    public void setOpenable(boolean openable) {
        this.openable = openable;
    }

    /**
     *
     * @return
     */
    public boolean isPickupable() {
        return pickupable;
    }

    /**
     *
     * @param pickupable
     */
    public void setPickupable(boolean pickupable) {
        this.pickupable = pickupable;
    }

    /**
     *
     * @return
     */
    public boolean isOpen() {
        return open;
    }

    /**
     *
     * @param open
     */
    public void setOpen(boolean open) {
        this.open = open;
    }
    /**
     *
     * @return
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     *
     * @param visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     *
     * @return
     */
    public Set<String> getAlias() {
        return alias;
    }

    /**
     *
     * @param alias
     */
    public void setAlias(Set<String> alias) {
        this.alias = alias;
    }

    /**
     *
     * @param alias
     */
    public void setAlias(String[] alias) {
        this.alias = new HashSet<>(Arrays.asList(alias));
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
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
        final AdvObject other = (AdvObject) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
