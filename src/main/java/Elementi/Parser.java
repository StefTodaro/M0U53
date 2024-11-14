/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementi;

import java.util.ArrayList;
import java.util.List;

/**
 *Parserizza il comando dell'utente e su tale base fornisce gli oggetti utili 
 * per l'esecuzione delle azioni richieste
 * @author Stefano
 */
public class Parser implements java.io.Serializable {

    int i = 0;
    String cmd;
    String[] tokens;
    private String obName;

    final List<String> connettivi = new ArrayList(List.of("di", "a", "ad", "con", "per", "su", "da", "il", "lo", "la", "i", "contro",
            "sopra", "sotto", "dentro", "fra", "in", "tra", "nella", "dentro"));

    private int checkForCommand(String token, List<Command> commands) {
        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i).getName().equals(token) || commands.get(i).getAlias().contains(token)) {
                return i;
            }
        }

        return -1;
    }

    private int checkForObject(String token, List<AdvObject> obejcts) {
        for (int i = 0; i < obejcts.size(); i++) {
            obName = obejcts.get(i).getName().toLowerCase();

            if (obName.equals(token) || ((obejcts.get(i).getAlias().contains(token)))) {
                return i;

            }
        }
        return -1;
    }

    /**
     *Scansione del comando dell'utente e traduzione delle sue varie parti
     * @param command stringa inserita dall'utente
     * @param commands lista dei comandi
     * @param objects oggetti presenti nella stanza
     * @param character pesonaggi presenti nella stanza
     * @param inventory inventario del giocatore
     * @return ParserOutput
     */
    public ParserOutput parse(String command, List<Command> commands, List<AdvObject> objects, List<Character> character, List<AdvObject> inventory) {
        i = 0;
        cmd = command.toLowerCase().trim();
        tokens = cmd.split("\\s+");
        if (tokens.length > 0) {
            int ic = checkForCommand(tokens[0], commands);

            if (ic > -1) {
                i++;
                if (tokens.length > 1) {
                    int io = -1;

                    saltaConnettivi();

                    io = checkForObject(tokens[i], objects);

                    int ioinv = -1;

                    if (io < 0 || commands.get(ic).getType() == CommandType.DROP) {
                        saltaConnettivi();
                        ioinv = checkForObject(tokens[i], inventory);
                    }

                    if (commands.get(ic).getType() == CommandType.GIVE) {

                        saltaConnettivi();

                        ioinv = checkForObject(tokens[i], inventory);
                        scorriStringa();

                        saltaConnettivi();

                        io = checkForObject(tokens[i], objects);
                    }

                    if (commands.get(ic).getType() == CommandType.USE) {
                        saltaConnettivi();

                        ioinv = checkForObject(tokens[i], inventory);
                        if (ioinv == -1) {
                            io = checkForObject(tokens[i], objects);
                        }

                        scorriStringa();

                        saltaConnettivi();

                        if (io == -1) {
                            io = checkForObject(tokens[i], objects);
                        } else {
                            ioinv = checkForObject(tokens[i], inventory);
                        }
                    }

                    if (commands.get(ic).getType() == CommandType.ATTACK || commands.get(ic).getType() == CommandType.OPEN) {
                        saltaConnettivi();

                        io = checkForObject(tokens[i], objects);
                        if (tokens.length > 2) {
                            scorriStringa();
                            saltaConnettivi();

                            ioinv = checkForObject(tokens[i], inventory);
                        }
                    }

                    if (io > -1 && ioinv > -1) {
                        return new ParserOutput(commands.get(ic), objects.get(io), inventory.get(ioinv));
                    } else if (io > -1) {
                        return new ParserOutput(commands.get(ic), objects.get(io), null);
                    } else if (ioinv > -1) {
                        return new ParserOutput(commands.get(ic), null, inventory.get(ioinv));
                    } else {
                        return new ParserOutput(commands.get(ic), null, null);

                    }
                } else {
                    return new ParserOutput(commands.get(ic), null);
                }
            } else {
                return new ParserOutput(null, null);
            }
        } else {
            return null;
        }
    }
    /**
     * Scorre la stringa finchè contiene dei connettivi
     */

    private void saltaConnettivi() {
        try {
            while (connettivi.contains(tokens[i])) {
                scorriStringa();
                if (i == tokens.length - 1) {
                    break;
                }
            }
        } catch (NullPointerException e) {
            System.err.println(e);
        }
    }

    /**
     * scorre la stringa quando richiamato
     */
    private void scorriStringa() {
        if (i < tokens.length - 1) {
            i++;
        }
    }

}
