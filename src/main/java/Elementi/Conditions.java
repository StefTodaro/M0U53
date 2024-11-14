/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementi;

import java.io.Serializable;

/**
 *Condizioni che modificano le interazioni con gli oggetti e che permettono il
 * proseguimento dell'avventura
 * @author Stefano
 */
public class Conditions implements Serializable {
    //controlla che l'ggettopersonaggio
    boolean character = false;
    //controlla che il giocatore abbia con se lo zaino
    boolean zaino = false;
    //controlla che la vite della ruota si svitata
    boolean svitata = false;
    //controlla che Speedy stia correndo
    boolean corre = false;
    //controlla che la spina sia stata rosicchiata
    boolean spina = false;
    //controlla il numero di formine inserite nel tabellone
    byte formine = 0;
    //controlla che il rilevatore di fumo sia stato messo in funzione
    boolean fumo = false;
    //controlla che la leva sia stata abbassata
    boolean abbassata = false;
    //controlla che i cavi siano stati riparati
    boolean riparati = false;
    //controlla che la lampadina sia stata svitata
    boolean svitataL = false;
    //controlla che il premio sia stato riscattato nel laboratorio cognitivo
    boolean premio = false;
    //controlla che la trappola sia stata fatta scattare
    boolean scattata = false;
    //controlla che la sigaretta sia stata accesa
    boolean accesa = false;
    //controlla la pagina attuale del diario
    byte pagina = 1;
    //controlla che il pesce sia distratto
    boolean distratto = false;
    //controlla che la torcia sia stata accesa
    boolean accesaTorcia = false;
    //controlla quanti pezzi di formaggi sono stati dati ad Albert
    byte furia = 0;
    //controlla che il telecomando contenga pile
    boolean conPile = false;
    //controlla che il monitor sia acceso
    boolean monitorAcceso = false;
    //controlla che l'inserviente sia svenuto
    boolean svenuto = false;

    /**
     *
     */
    public Conditions() {
    }

    /**
     *
     * @return
     */
    public boolean isZaino() {
        return zaino;
    }

    /**
     *
     * @param zaino
     */
    public void setZaino(boolean zaino) {
        this.zaino = zaino;
    }

    /**
     *
     * @return
     */
    public boolean isSvitata() {
        return svitata;
    }

    /**
     *
     * @param svitata
     */
    public void setSvitata(boolean svitata) {
        this.svitata = svitata;
    }

    /**
     *
     * @return
     */
    public boolean isCorre() {
        return corre;
    }

    /**
     *
     * @param corre
     */
    public void setCorre(boolean corre) {
        this.corre = corre;
    }

    /**
     *
     * @return
     */
    public boolean isCharacter() {
        return character;
    }

    /**
     *
     * @param character
     */
    public void setCharacter(boolean character) {
        this.character = character;
    }
    
    /**
     *
     * @return
     */
    public boolean isSpina() {
        return spina;
    }

    /**
     *
     * @param spina
     */
    public void setSpina(boolean spina) {
        this.spina = spina;
    }

    /**
     *
     * @return
     */
    public byte getFormine() {
        return formine;
    }

    /**
     *
     */
    public void addFormine() {
        this.formine += 1;
    }

    /**
     *
     * @return
     */
    public boolean isFumo() {
        return fumo;
    }

    /**
     *
     * @param fumo
     */
    public void setFumo(boolean fumo) {
        this.fumo = fumo;
    }

    /**
     *
     * @return
     */
    public boolean isAbbassata() {
        return abbassata;
    }

    /**
     *
     * @param abbassata
     */
    public void setAbbassata(boolean abbassata) {
        this.abbassata = abbassata;
    }

    /**
     *
     * @return
     */
    public boolean isRiparati() {
        return riparati;
    }

    /**
     *
     * @param riparati
     */
    public void setRiparati(boolean riparati) {
        this.riparati = riparati;
    }

    /**
     *
     * @return
     */
    public boolean isSvitataL() {
        return svitataL;
    }

    /**
     *
     * @param svitataL
     */
    public void setSvitataL(boolean svitataL) {
        this.svitataL = svitataL;
    }

    /**
     *
     * @return
     */
    public boolean isPremio() {
        return premio;
    }

    /**
     *
     * @param premio
     */
    public void setPremio(boolean premio) {
        this.premio = premio;
    }

    /**
     *
     * @return
     */
    public boolean isScattata() {
        return scattata;
    }

    /**
     *
     * @param scattata
     */
    public void setScattata(boolean scattata) {
        this.scattata = scattata;
    }

    /**
     *
     * @return
     */
    public boolean isAccesa() {
        return accesa;
    }

    /**
     *
     * @param accesa
     */
    public void setAccesa(boolean accesa) {
        this.accesa = accesa;
    }

    /**
     *
     * @return
     */
    public byte getPagina() {
        return pagina;
    }

    /**
     *
     * @param pagina
     */
    public void setPagina(byte pagina) {
        this.pagina = pagina;
    }

    /**
     *
     * @return
     */
    public boolean isDistratto() {
        return distratto;
    }

    /**
     *
     * @param distratto
     */
    public void setDistratto(boolean distratto) {
        this.distratto = distratto;
    }

    /**
     *
     * @return
     */
    public boolean isAccesaTorcia() {
        return accesaTorcia;
    }

    /**
     *
     * @param accesaTorcia
     */
    public void setAccesaTorcia(boolean accesaTorcia) {
        this.accesaTorcia = accesaTorcia;
    }

    /**
     *
     * @return
     */
    public byte getFuria() {
        return furia;
    }

    /**
     *
     */
    public void addFuria() {
        this.furia += 1;
    }

    /**
     *
     * @return
     */
    public boolean isConPile() {
        return conPile;
    }

    /**
     *
     * @param conPile
     */
    public void setConPile(boolean conPile) {
        this.conPile = conPile;
    }

    /**
     *
     * @return
     */
    public boolean isMonitorAcceso() {
        return monitorAcceso;
    }

    /**
     *
     * @param monitorAcceso
     */
    public void setMonitorAcceso(boolean monitorAcceso) {
        this.monitorAcceso = monitorAcceso;
    }

    /**
     *
     * @return
     */
    public boolean isSvenuto() {
        return svenuto;
    }

    /**
     *
     * @param svenuto
     */
    public void setSvenuto(boolean svenuto) {
        this.svenuto = svenuto;
    }
}
