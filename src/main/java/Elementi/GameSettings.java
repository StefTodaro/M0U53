/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementi;

import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 *Definizione dei comandi finali del gioco, delle stanze, delle loro stanze 
 * adiacenti e delle personaggi o oggetti che contengono
 * @author Stefano
 */
public class GameSettings extends GameDescription implements java.io.Serializable {

    Conditions con = new Conditions();
    //controlla che sia avvenuto uno spostamento
    private boolean move = false;
    
    /**
     *
     * @return
     */
    public boolean isMove() {
        return move;
    }

    /**
     *
     * @param move
     */
    public void setMove(boolean move) {
        this.move = move;
    }

    /**
     *Definisce i nomi dei comandi e degli oggetti e i loro sinonimi. Inoltre de
     * finisce le stanze e le loro caratteristiche, la loro disposizione e gli 
     * oggetti al loro interno. Inoltre definisce le caratteristiche dei 
     * singoli oggetti
     * @throws Exception
     */
    @Override
    public void init() throws Exception {

        Command nord = new Command(CommandType.NORTH, "nord");
        nord.setAlias(new String[]{"n", "N", "Nord", "NORD"});
        getCommands().add(nord);

        Command inventory = new Command(CommandType.INVENTORY, "inventario");
        inventory.setAlias(new String[]{"inv", "i", "oggetti", "zaino", "borsa", " guarda zaino"});
        getCommands().add(inventory);

        Command commands = new Command(CommandType.COMMAND, "guida");
        commands.setAlias(new String[]{"g", "guide", "info", "tasti", "help", "comandi"});
        getCommands().add(commands);

        Command sud = new Command(CommandType.SOUTH, "sud");
        sud.setAlias(new String[]{"s", "S", "Sud", "SUD"});
        getCommands().add(sud);

        Command est = new Command(CommandType.EAST, "est");
        est.setAlias(new String[]{"e", "E", "Est", "EST"});
        getCommands().add(est);

        Command ovest = new Command(CommandType.WEST, "ovest");
        ovest.setAlias(new String[]{"w", "W", "west", "West", "WEST", "o", "Ovest", "OVEST"});
        getCommands().add(ovest);

        Command up = new Command(CommandType.UP, "up");
        up.setAlias(new String[]{"u", "U", "Up", "UP", "sopra", "Sopra", "SOPRA", "sali", "Sali", "SALI", "salta", "salto"});
        getCommands().add(up);

        Command down = new Command(CommandType.DOWN, "down");
        down.setAlias(new String[]{"d", "sotto", "scendi", "scendo", "calati"});
        getCommands().add(down);

        Command end = new Command(CommandType.END, "end");
        end.setAlias(new String[]{"end", "fine", "esci", "muori", "ammazzati", "ucciditi", "suicidati", "exit"});
        getCommands().add(end);

        Command look = new Command(CommandType.LOOK_AT, "osserva");
        look.setAlias(new String[]{"l", "look", "guarda", "vedi", "trova", "cerca", "descrivi", "guardo", "osserva", "osservo", "cerco", "vedo", "ispeziona", "leggi", "leggo", "cerca", "cerco"});
        getCommands().add(look);

        Command pickup = new Command(CommandType.PICK_UP, "raccogli");
        pickup.setAlias(new String[]{"p", "prendi", "raccolgo", "prendo",});
        getCommands().add(pickup);

        Command drop = new Command(CommandType.DROP, "lascia");
        drop.setAlias(new String[]{"drop", "getta", "lascio", "getto"});
        getCommands().add(drop);

        Command open = new Command(CommandType.OPEN, "apri");
        open.setAlias(new String[]{"spalanca", "sfonda", "apro", "sfondo", "spalanco"});
        getCommands().add(open);

        Command close = new Command(CommandType.CLOSE, "chiudi");
        close.setAlias(new String[]{"chiudo"});
        getCommands().add(close);

        Command use = new Command(CommandType.USE, "usa");
        use.setAlias(new String[]{"utilizza", "metti", "uso", "metto", "utilizza", "utilizzo", "svita", "svito", "sblocco", "sblocca",
            "metti", "inserisci", "metto", "lancio", "lancia", "inserisco", "avvicino", "avvicina", "appiccico", "appiccica", "butta", "butto",
            "inserisci", "inserisco", "interagisco", "interact", "abbraccia", "abbraccio", "spingi", "spingo", "tira", "tiro", "muovi", "muovo", "alza",
            "alzo", "corri", "corro", "mangio", "mangia", "sposta", "sposto", "solleva", "sollevo", "bevo", "bevi", "mordi", "mordo", "rosicchia",
            "rosicchia", "abbassa", "abbasso", "aziono", "aziona", "tocca", "tocco", "avvito", "avvita", "riparo", "ripara", "attiva",
            "attivo", "disattiva", "disattivo", "giro", "gira", "accendo", "accendi", "spengo", "spegni", "indossa", "indossa", "gioca", "gioco",
            "avvia", "avvio", "sparati", "sparami", "lanciati", "lanciami", "togli", "tolgo","interagisci","interagisco"});
        getCommands().add(use);

        Command attack = new Command(CommandType.ATTACK, "attacca");
        attack.setAlias(new String[]{"colpisci", "ammazza", "attacco", "ammazzo", "colpisco", "picchio",
            "picchia", "infilzo", "infilza", "pungo", "pungi", "punzecchia", "punzecchio",});
        getCommands().add(attack);

        Command give = new Command(CommandType.GIVE, "dai");
        give.setAlias(new String[]{"porgi", "dona", "do", "dò", "porgo", "dono", "porgo"});
        getCommands().add(give);

        Command talk = new Command(CommandType.TALK_TO, "parla");
        talk.setAlias(new String[]{"dialoga", "rivolgiti", "parlo", "dialogo", "saluta", "saluto"});
        getCommands().add(talk);

        Command save = new Command(CommandType.SAVE, "salva");
        save.setAlias(new String[]{"save"});
        getCommands().add(save);

        Room angoloM = new Room(1, "Angolo di mamma", "E' il posto preferito di mamma, dice che da questa posizione riesce a tenerci tutti d'occhio. Infatti è qui.", null, null, null, null, null, null);
        Room abbeveratoio = new Room(2, "Davanti all'abbeveratoio", "C'è un semplice abbeveratoio contenente l'acqua di uno strano colore verdognolo. Da quanto non la cambieranno?"
                + "Perfetto per nasconderci qualcosa", null, angoloM, null, null, null, null);
        Room sottoRuota = new Room(3, "Sotto la ruota", "E' l'angolo buio in cui Mortimer sistema tutto ciò che trova. Infatti è proprio lì davanti.Saltando riesci a raggiungere la ruota.", null, abbeveratoio, null, null, null, null);
        Room ruota = new Room(4, "Ruota", "La vecchia ruota rossa, che come al solito viene fatta girare velocissima da Speedy... Cosa?! E' Ferma?! "
                + "Sotto di te vedi Mortimer", null, abbeveratoio, null, null, null, sottoRuota);
        Room nidoEst = new Room(5, "Teca", "A Est si riesce a vedere la mamma seduta sul suo cuscino di trucioli.", null, null, null, angoloM, null, null);
        Room estGouda = new Room(6, "Teca", "A nord riesci a vedere la rampa che porta alla ruota", null, nidoEst, null, abbeveratoio, null, null);
        Room nido = new Room(7, "Nido", "E' il luogo che hai imparato a chiamare casa, così pieno di ricordi e nostalgia, tralasciando il"
                + " fatto che sia un vasetto del gelato con dei fori ai lati... per fortuna questa è la notte della fuga.", null, null, null, nidoEst, null, null);
        Room rampa = new Room(8, "Rampa", "E' la rampa che porta alla ruota, e presto  ti porterà fuori di qui. A est vedi la ruota rossa", null, estGouda, null, ruota, ruota, null);
        Room centro = new Room(9, "Centro  della gabbietta", "C'è Gouda steso per terra che russa", null, nido, null, estGouda, null, null);
        Room piedi = new Room(10, "Ai piedi della rampa", "Sei dietro Gouda,è la zona più pericolosa della gabbietta. Cammini evitando la coda di Gouda", null, centro, null, rampa, null, null);
        Room palla = new Room(11, "Palla", "Qui accanto al nido c'è la tua palla di rametti con cui giocavi sempre da cucciolo.", null, null, null, nido, null, null);
        Room davantiPonte = new Room(12, "Teca", "A nord riesci a vedere il ponticello in legno.", null, palla, null, centro, null, null);
        Room ponte = new Room(13, "Ponte", "E il ponticello in legno sotto cui si nasconde Gouda, anche se ormai ci entra a malapena", null, davantiPonte, null, piedi, null, null);
        Room cancello = new Room(14, "Cancelletto", "Il piano ha funzionato alla perfezione sei arrivato al cancelletto della gabbietta! Ora arrampicati fuori", null, null, null, null, null, ponte);

        Room cugini = new Room(15, "Teca dei cugini", "E' la teca dei tuoi cugini da parte di tuo padre. Non siete mai andati molto d'accordo", null, null, null, null, null, null);
        Room sopraAlbert = new Room(16, "Teca B4UC3", "Leggi la targhetta della teca, che riporta \"B4UC3\". L'entrata della"
                + " teca è uguale a quella della tua. ", null, cugini, null, null, null, null);
        Room tecaAlbert = new Room(17, "Dentro la teca di Albert ", "Con te dentro la stanza noti l'enorme porcellino d'India che vedevi sempre dalla tua teca."
                + " Ti guarda incuriosito.", null, null, null, null, sopraAlbert, null);
        Room sottoVideo = new Room(18, "Sotto il laboratorio problem solving", "Sei sotto il laboratorio di problem solving. Ti "
                + "ricordi che l'entrata si trova verso est", null, sopraAlbert, null, null, null, null);
        Room video = new Room(41, "Laboratorio problem solving", "Qui vengono eseguiti test tramite videogiochi, e tu detieni il record"
                + " di tempo di completamento, ovviamente.", null, null, null, null, null, null);
        Room sudCaffe = new Room(19, "Laboratorio", "Vedi a nord la macchina del caffè e noti qualche macchia per terra.", null, sottoVideo, null, null, null, null);
        Room caffe = new Room(20, "Macchina del caffè", "Sei davanti ad un enorme macchina del caffè,per terra e sulla macchina  noti molte macchie."
                + "Sopra la macchina è attaccato un foglietto di carta.", null, sudCaffe, null, null, null, null);
        Room fuori = new Room(21, "Sopra la gabbia", "Da qui riesci a vedere tutto il laboratorio, e in fondo vedi l'uscita."
                + "  Leggi anche la targhetta della tua teca: \"M0U5E\" ", null, null, null, cugini, null, cancello);
        Room nordTeca = new Room(22, "Davanti la teca", "Il pavimento del labotatorio. Il primo passo verso la libetà", null, fuori, null, sopraAlbert, null, null);
        Room sottoCabina = new Room(23, "Laboratorio", "A nord vedi l'entrata per il testing cognitivo", null, nordTeca, null, sottoVideo, null, null);
        Room cabina = new Room(24, "IngressoLaboratorio Problem Solving", "Da qui si accede ad una delle sale di testing, ma il cancello è chiuso!",
                null, null, null, null, null, sottoCabina);
        Room cognitivo = new Room(25, "Laboratorio test cognitivi", "Salendo sul tavolino noti che l'ultimo test comprendeva una leva, una lampadina"
                + " e dei cavi rosicchiati...anche se non sei convinto mangiarli facesse parte del test."
                + " Noti una piccola porticina in metallo in fondo alla stanza.", null, sottoCabina, null, sudCaffe, null, null);
        Room uscita = new Room(26, "Uscita principale", "E' l'unica uscita da questo posto, devi trovare il modo di aprire quella porta e"
                + ", ATTENZIONE! Una trappola", null, cognitivo, null, caffe, null, null);
        Room westTeca = new Room(27, "Ovest teca", "I dottori quando venivano a prelevarvi per i test si posizionavano qui. "
                + " Che posto inquietante", null, null, null, fuori, null, null);
        Room qi = new Room(28, "Laboratorio testing QI", "Era tanto tempo che non venivi qui. Vedi un tabellone in legno"
                + " con dei fori a forma di cerchi, esagono e saetta. Ci sono anche i pezzi ma sembra mancarne uno.", null, westTeca, null, nordTeca, null, null);
        Room nordQi = new Room(29, "Laboratorio", "C'è una bruciatura nera per terra che ricorda vagamente speedy. Meglio non indagare", null, qi, null, sottoCabina, null, null);
        Room westCognitivo = new Room(30, "Labotatorio", "A est, oltre il muro si trova la stanza dell'inserviente. Non sembra gli siano mai"
                + "andati a genio gli animali. Mi chiedo perchè abbia accettato questo lavoro.", null, nordQi, null, cognitivo, null, null);
        Room estInserviente = new Room(31, "Uscio stanza inserviente", "Sei davanti all'entrata della stanza dell'inserviente, ma la porta è chiusa."
                + " Senti dei rumori provenire da dentro. Davanti a te vedi un attaccapanni e una giacca su cui ti puoi arrampicare",
                null, westCognitivo, null, uscita, null, null);
        Room giacca = new Room(32, "Giacca", "Sei sopra una giacca vecchia che puzza di fumo. Noti che ha una tasca piena. "
                + "Da qui puoi raggiungere il soffitto", null, westCognitivo, null, uscita, null, estInserviente);
        Room soffitto = new Room(33, "Soffitto", "Sei in cima all'attaccapanni, da qui riesci a toccare il soffitto. Davanti vedi"
                + " un disco attaccato al soffitto.", null, westCognitivo, null, uscita, null, giacca);
        Room angoloS = new Room(34, "Angolo sporco", "E' un angolo sporco dove ti è sempre sembrato di vedere qualcosa muoversi, ma non ne sei"
                + " mai stato sicuro. Osservando capisci che si trattava di uno strano scarafaggio.", null, null, null, westTeca, null, null);
        Room acquario = new Room(35, "Acquario", "Un acquario con un acqua limpida che contine un enorme pesce con un occhio solo. In fondo"
                + " alla vasca vedi qualcosa che sembri riconoscere.", null, angoloS, null, qi, null, null);
        Room sottoScrivania = new Room(36, "Sotto la scrivania", "Puoi usare la sedia per salire fin sopra", null, acquario, null, nordQi, null, null);
        Room scrivania = new Room(37, "Scrivania ", "Sei sulla scrivania dove gli scienziati solitamente prendono appunti. Accanto"
                + " a te c'è il diario dove annotano tutto.", null, acquario, null, nordQi, null, sottoScrivania);
        Room ripostiglio = new Room(38, "Ripostiglio", "E' il ripostiglio del laboratorio. Ci sono detergenti vari,pezze e..."
                + "strumenti per caccia e disinfestazioni. Quel tipo ha qualche rotella fuori posto. Qui riesci a"
                + " muoverti tra i vari scaffali", null, null, null, null, null, null);
        Room sottoInserviente = new Room(39, "Stanza dell'inseriviente", "La stanza è piuttosto buia, se non fosse per la lampada"
                + " accessa sul tavolo. Si sente uno strano odore, molto pungente.", null, ripostiglio, null, estInserviente, null, null);
        Room tavoloInserviente = new Room(40, "Tavolino da lavoro", "Scappa, un gatto! Aspetta, ma non si muove. Vedi chei il tavolo è "
                + "disseminato da animali impagliati. Dopo esserti calmato noti un appendi chiavi al muro", null, ripostiglio, null, null, null, sottoInserviente);
        Room maniglia = new Room(42, "Maniglia porta di uscita", "Ti senti soddisfatto di essere arrivato fin qui"
                + ". Ora apri la porta e tira tutta la tua famiglia fuori da questo posto.", null, cognitivo, estInserviente, caffe, null, uscita);

     

        angoloM.setNorth(abbeveratoio);
        angoloM.setWest(nidoEst);
        abbeveratoio.setWest(estGouda);
        abbeveratoio.setNorth(sottoRuota);
        sottoRuota.setWest(rampa);
        sottoRuota.setUp(ruota);
        ruota.setWest(rampa);
        ruota.setSouth(sottoRuota);
        nidoEst.setWest(nido);
        nidoEst.setNorth(estGouda);
        estGouda.setNorth(rampa);
        estGouda.setWest(centro);
        rampa.setWest(piedi);
        rampa.setSouth(estGouda);
        nido.setWest(palla);
        nido.setNorth(centro);
        centro.setNorth(piedi);
        centro.setWest(davantiPonte);
        piedi.setWest(ponte);
        palla.setNorth(davantiPonte);
        davantiPonte.setNorth(ponte);

        cugini.setWest(fuori);
        cugini.setNorth(sopraAlbert);
        sopraAlbert.setWest(nordTeca);
        sopraAlbert.setNorth(sottoVideo);
        sottoVideo.setNorth(sudCaffe);
        sottoVideo.setWest(sottoCabina);
        video.setWest(cabina);
        video.setSouth(sopraAlbert);
        video.setNorth(sudCaffe);
        sudCaffe.setWest(cognitivo);
        sudCaffe.setNorth(caffe);
        caffe.setWest(uscita);
        fuori.setNorth(nordTeca);
        fuori.setWest(westTeca);
        nordTeca.setNorth(sottoCabina);
        nordTeca.setWest(qi);
        sottoCabina.setNorth(cognitivo);
        sottoCabina.setWest(westCognitivo);
        sottoCabina.setUp(cabina);
        cognitivo.setNorth(uscita);
        cognitivo.setWest(westCognitivo);
        uscita.setWest(estInserviente);
        westTeca.setWest(angoloS);
        westTeca.setNorth(qi);
        qi.setWest(acquario);
        qi.setNorth(nordQi);
        nordQi.setNorth(westCognitivo);
        nordQi.setWest(sottoScrivania);
        westCognitivo.setNorth(estInserviente);
        angoloS.setNorth(acquario);
        acquario.setNorth(sottoScrivania);
        sottoScrivania.setUp(scrivania);
        estInserviente.setUp(giacca);
        giacca.setUp(soffitto);
        angoloS.setNorth(acquario);
        ripostiglio.setNorth(sottoInserviente);
        sottoInserviente.setUp(tavoloInserviente);
        
        getRooms().add(ponte);
        getRooms().add(ruota);
        getRooms().add(cancello);
        getRooms().add(fuori);
        getRooms().add(centro);
        getRooms().add(piedi);
        getRooms().add(tecaAlbert);
        getRooms().add(sopraAlbert);
        getRooms().add(caffe);
        getRooms().add(sottoInserviente);
        getRooms().add(estInserviente);
        getRooms().add(giacca);
        getRooms().add(soffitto);
        getRooms().add(angoloS);
        getRooms().add(video);
        getRooms().add(maniglia);

        AdvObject chicco = new AdvObject(1, "chicco di caffè", "Chicco di caffè che hai nascosto con cura da Gouda e Mortimer");
        chicco.setAlias(new String[]{"chicco", "caffe", "caffè", "chicco di caffe"});
        AdvObjectContainer abbeveratoioObj = new AdvObjectContainer(2, "abbeveratoio", "Vedi qualcosa di familiare dentro la canna l'abbeveratoio.");
        abbeveratoioObj.setAlias(new String[]{"abbeveratore", "acqua", "canna"});
        abbeveratoioObj.setOpen(true);
        abbeveratoioObj.setPickupable(false);
        AdvObject ago = new AdvObject(3, "graffetta", "Sei riuscito a recuperarla dal taschino di uno scienziato. L'hai srotolata e "
                + "nascosta nell'abbeveratorio. Sembra anche un po' appuntita.");
        ago.setAlias(new String[]{"graffetta", "clip", "spada","ago"});
        AdvObject cacca = new AdvObject(4, "cacca", "Ricordo di Gouda in fuga.Forse alla fine di questa storia dovresti scusarti. Sembra quasi un chicco di caffè.");
        cacca.setAlias(new String[]{"feci"});
        cacca.setVisible(false);
        AdvObjectContainer pallaObj = new AdvObjectContainer(5, "palla", "All'interno dell'intreccio di rami hai nascosto un elemento un monetina."
                + "Fondamentale per il tuo piano.");
        pallaObj.setAlias(new String[]{"sfera", "palla di rami"});
        pallaObj.setOpen(true);
        pallaObj.setPickupable(false);
        AdvObject moneta = new AdvObject(6, "moneta", "Moneta da 50 centesimi che era caduta ad uno scienziato");
        moneta.setAlias(new String[]{"centesimo", "monetina"});
        AdvObject fakeMoneta = new AdvObject(11, "moneta");
        fakeMoneta.setAlias(new String[]{"centesimo", "monetina"});
        AdvObject ruotaObj = new AdvObject(12, "ruota", "Al centro della ruota c'è una vite a taglio che sembra si possa svitare.");
        ruotaObj.setAlias(new String[]{"bullone", "vite"});
        ruotaObj.setPickupable(false);
        AdvObject coda = new AdvObject(13, "coda", "Sotto la coda di Gouda vedi qualcosa,ma non riesci a vedere bene cosa");
        coda.setAlias(new String[]{"coda", "sedere", "culo", "chiappe", "coda gouda", "culo gouda", "sedere gouda", "chiappe di gouda", "culo di gouda", "sedere di gouda", "chiappe di gouda", "gouda"});
        coda.setPickupable(false);
        AdvObject lucchetto = new AdvObject(16, "lucchetto", "E' in plastica e ha uno sblocco a rotazione. Ha una fessura a taglio al centro che sembra collegata al meccanismo");
        lucchetto.setAlias(new String[]{"fessura", "uscita", "cancelletto", "cancello", "teca"});
        lucchetto.setOpen(false);
        lucchetto.setOpenable(false);
        lucchetto.setPickupable(false);

        AdvObject lucchettoCugini = new AdvObject(17, "lucchetto", "E' un lucchetto con un funzionamento uguale a quello della tua teca. Da fuori sarà"
                + " più semplice da aprire.");
        lucchettoCugini.setAlias(new String[]{"fessura", "uscita", "cancelletto", "cancello", "teca"});
        lucchettoCugini.setOpen(false);
        lucchettoCugini.setOpenable(true);
        lucchettoCugini.setPickupable(false);
        AdvObject lucchettoAlbert = new AdvObject(18, "lucchetto", "E' un lucchetto con un funzionamento uguale a quello della tua teca. Da fuori sarà"
                + " più semplice da aprire.");
        lucchettoAlbert.setAlias(new String[]{"fessura", "uscita", "cancelletto", "cancello", "teca"});
        lucchettoAlbert.setOpen(false);
        lucchettoAlbert.setOpenable(true);
        lucchettoAlbert.setPickupable(false);
        AdvObject telecomando = new AdvObject(19, "telecomando", "telecomando che serve per accendere il monitor.");
        telecomando.setAlias(new String[]{"tasto", "pulsante", "bottone"});
        telecomando.setPickupable(false);
        telecomando.setOpen(false);
        telecomando.setOpenable(true);
        AdvObject monitor = new AdvObject(20, "monitor", "E' direttamente collegato alla console di gioco. Al termine di un combattimeto vinto, "
                + "il monitor emette un motivo molto forte. E' perfetto per attirare l'attenzione. Ora è spenta.");
        monitor.setAlias(new String[]{"schermo", "televisione", "tv", "televisore"});
        monitor.setPickupable(false);
        AdvObject gioco = new AdvObject(21, "gioco", "E' la scatola del tuo gioco preferito \"Fantastic Fantasy VII\". Eri arrivato al boss finale."
                + ". Avvia la partita per giocarlo.");
        gioco.setAlias(new String[]{"videogioco", "Fantastic Finale", "videogame", "ff", "partita"});
        gioco.setPickupable(false);
        AdvObject macchinaCaffe = new AdvObject(22, "macchina del caffè", "Ha molte macchie di caffè dappertutto e ha molte ammacchatture."
                + " Si vede che l'ultimo tranquillante testato non ha funzionato bene.");
        macchinaCaffe.setAlias(new String[]{"distributore", "caffetteria", "macchina caffe", "macchina del caffe", "macchina caffè", "macchina"});
        macchinaCaffe.setPickupable(false);
        AdvObject foglio = new AdvObject(23, "foglio", "foglio di carta attaccato alla macchina del caffè, su cui c'è scritto: \""
                + "ATTENZIONE, NON BAGNARE LA SPINA DELLA MACCHINETTA. IL PROSSIMO PAGA!\"");
        foglio.setAlias(new String[]{"nota", "postit", "foglietto"});
        foglio.setPickupable(false);
        AdvObject spina = new AdvObject(24, "spina", "La spina della macchina del caffè. ha una zona coperta da del nastro adesivo. chissà se "
                + " resisterebbe agli incisivi di un topo.");
        spina.setAlias(new String[]{"cavo", "nastro", "scotch", "presa", "adesivo"});
        spina.setPickupable(false);
        AdvObject specchio = new AdvObject(25, "specchio", "E' uno specchio per testare l'autoriconoscimento, ma tu lo puoi usare per vedere "
                + "quanto sei fico.");
        specchio.setAlias(new String[]{"riflesso"});
        specchio.setPickupable(false);
        specchio.setVisible(false);
        AdvObjectContainer armadio = new AdvObjectContainer(26, "armadio", "E' un normale armadio in miniatura e sai già cosa c'è dentro. Oh si!");
        armadio.setAlias(new String[]{"ante"});
        armadio.setOpen(false);
        armadio.setOpenable(true);
        armadio.setPickupable(false);
        armadio.setVisible(false);
        AdvObject camice = new AdvObject(27, "camice personalizzato", "E' il camice che indossi durante i test. Ha perfino il tuo nome sopra.");
        camice.setAlias(new String[]{"vestito", "abito", "camice", "mio camice"});
        camice.setVisible(false);
        AdvObject cancelloC = new AdvObject(28, "cancello", "Ti chiude la strada verso il laboratorio di problem solving. Sembra essere "
                + "chiuso a chiave.");
        cancelloC.setAlias(new String[]{"porta", "cancelletto", "entrata", "cancello", "lucchetto"});
        cancelloC.setOpenable(false);
        cancelloC.setOpen(false);
        AdvObject leva = new AdvObject(29, "leva", "Osservandoti in torno capisci che azioni la lampada in fondo alla stanza.");
        leva.setAlias(new String[]{"manovella"});
        leva.setPickupable(false);
        AdvObject cavi = new AdvObject(30, "cavi", "Sarebbero collegati alla base su cui poggia la lampada, ma qualcuno ha rosicchiato un passaggio."
                + " Forse potresti ripararlo con un conduttore.");
        cavi.setAlias(new String[]{"spazio", "buco", "cavo"});
        cavi.setPickupable(false);
        AdvObject piattaforma = new AdvObject(31, "piattaforma", "E' la base a cui arriva la corrente. Al momento però è spenta");
        piattaforma.setAlias(new String[]{"base"});
        piattaforma.setPickupable(false);
        AdvObject lampadina = new AdvObject(32, "lampadina", "E' una comune lampadina, però con un po' di sforzo dovresti riuscire a svitarla.");
        lampadina.setAlias(new String[]{"bulbo", "luce", "lampada"});
        lampadina.setPickupable(false);
        AdvObject formaggio2 = new AdvObject(47, "formaggio", "Mickola: Che sorpresa, un altro premio in formaggio...");
        formaggio2.setAlias(new String[]{"esca", "tocco", "svizzero"});
        formaggio2.setVisible(false);
        AdvObject trappola = new AdvObject(33, "trappola per topi", "E' una trappola per topi classica, con un sistema a molla e del formaggio come esca."
                + " Pensavo esistessero solo nei cartoni.");
        trappola.setAlias(new String[]{"trappola"});
        trappola.setOpen(true);
        trappola.setPickupable(false);
        AdvObject formaggio1 = new AdvObject(34, "formaggio", "Mickola: non capisco come possano pensare che questa robaccia possa piacere. Che puzza.");
        formaggio1.setAlias(new String[]{"esca", "tocco", "svizzero"});
        formaggio1.setPickupable(false);
        AdvObject porta = new AdvObject(35, "porta d'uscita", "Il lucchetto è troppo in alto! devi trovare il modo di raggiungere la maniglia.");
        porta.setAlias(new String[]{"uscita", "porta"});
        porta.setPickupable(false);
        AdvObject lucchettoU = new AdvObject(36, "lucchetto", "E' chiuso a chiave, ma ti serve solo la chiave giusta ed è fatta.");
        lucchettoU.setAlias(new String[]{"uscita", "porta"});
        lucchettoU.setPickupable(false);
        lucchettoU.setOpen(false);
        lucchettoU.setOpenable(false);
        AdvObject tabellone = new AdvObject(37, "tabellone", "E' un gioco con forme ad incastro clsssico. Infondo hai un QI di 153, dovresti"
                + " riuscire a completarlo senza problemi. Vero?");
        tabellone.setAlias(new String[]{"gioco", "formina", "giocattolo", "sagoma", "spazio", "incastro", "formine"});
        tabellone.setPickupable(false);
        AdvObject cerchio = new AdvObject(38, "cerchio", "Formina in metallo cilindrica in metallo.");
        cerchio.setAlias(new String[]{"cilindro", "sfera"});
        AdvObject esagono = new AdvObject(39, "esagono", "Formina in metallo a forma di prisma esagonale. ");
        esagono.setAlias(new String[]{"prisma"});
        AdvObject saetta = new AdvObject(40, "saetta", "E' una formina di una saetta. Si trova sul fondo della vasca. Ti intimorisce un po' l'idea"
                + " di recuperarla.");
        saetta.setAlias(new String[]{"fulmine", "tuono"});
        saetta.setPickupable(false);
        AdvObject formaggio3 = new AdvObject(41, "formaggio", "Ha uno strano colore. Da quanto tempo era là ?");
        formaggio3.setAlias(new String[]{"esca", "tocco", "svizzero"});
        formaggio3.setVisible(false);
        AdvObject sigaretta = new AdvObject(42, "sigaretta", "Non credo si dispiacerà se ne prendi una. Devi trovare il modo di accenderla.");
        sigaretta.setAlias(new String[]{"sigarette", "sigaretta"});
        sigaretta.setVisible(false);
        AdvObjectContainer pacchetto = new AdvObjectContainer(43, "pacchetto di sigarette", "Sarà il pacchetto di sigarette dell'inserviente. Non solo quel tipo è brutto, "
                + "puzza anche. E' chiuso");
        pacchetto.setAlias(new String[]{"pacchetto"});
        pacchetto.setOpenable(true);
        pacchetto.setOpen(false);
        pacchetto.setPickupable(false);
        AdvObject rilevatore = new AdvObject(44, "rilevatore di fumo", "L'hai visto in funzione quando quel coniglio prese fuoco: iniziò a suonare una campanella"
                + " e a piovere dal soffitto. Ironico come stessero testando dei materiali contro la calvizie.");
        rilevatore.setAlias(new String[]{"rilevatore", "rilevatore fumo", "disco"});
        rilevatore.setPickupable(false);
        AdvObject acquarioObj = new AdvObject(45, "acquario", "E' arrivato da poco. o hanno ampliato il campo di ricerca, o è solo uno"
                + " spreco di soldi.");
        acquarioObj.setAlias(new String[]{"vasca", "teca", "boccia", "parete", "vetro"});
        acquarioObj.setPickupable(false);
        AdvObject gomma = new AdvObject(56, "gomma da masticare", "Ha un colore scuro, ma non sembra molto masticata. Spero tu non sia troppo schizzinoso.");
        gomma.setAlias(new String[]{"chewingum", "gomma"});
        AdvObject diario = new AdvObject(47, "diario", "Sembra il diario dove vengono presi appunti sulle ricerche:\n"
                + "\"19 Maggio 1973:Sono stati risconstrati sul soggetto Albert, un porcellino d'India, effetti collaterali nell'ultima versione "
                + "dell' MCP95 in seguito al consumo eccessivo di grassi. Si sono osservati comportamenti estremamenti violenti e un "
                + "aumento della forza fisica del soggetto. Ha attaccato il Dr. Bordignon facendolo svenire. Fortunatamente il Dr. Bordignon non mi è "
                + "mai piaciuto. Si sconsiglia di nutrire Albert con noci, noccioline, o formaggio, a meno che non ci sia di turno il Dr. Bordignon.\"");
        diario.setAlias(new String[]{"quaderno", "giornale", "pagina", "pagine"});
        diario.setPickupable(false);
        AdvObject righello = new AdvObject(48, "righello", "E' un righello in metallo di 20 centimetri.");
        righello.setAlias(new String[]{"riga"});
        AdvObject dardo = new AdvObject(49, "dardo", "Quest'uomo ha davvero un ossessione. Dovrebbero fare più attenzione a chi assumono");
        dardo.setAlias(new String[]{"freccia", "freccietta"});
        AdvObjectContainer torcia = new AdvObjectContainer(50, "torcia", "Una grossa torcia elettrica nuova di zecca. Con un po' di sforzo potresti aprirla");
        torcia.setAlias(new String[]{"torcia", "luce"});
        torcia.setOpenable(true);
        torcia.setOpen(false);
        torcia.setPickupable(false);
        AdvObject pile = new AdvObject(57, "pile", "Un paio di pile standard. Sembrano ancora cariche.");
        pile.setAlias(new String[]{"batterie", "pila", "batteria"});
        pile.setVisible(false);
        AdvObject chiaviC = new AdvObject(58, "chiavi", "Non sono le chiavi dell'uscita. Le avrà l'inserviente con sè. Apriranno sicuramente"
                + " qualcos'altro");
        chiaviC.setAlias(new String[]{"mazzo", "chiave"});
        AdvObject chiaviU = new AdvObject(51, "chiavi", "Attaccate alla cintura dell'inserviente. Devono essere sicuramente le chiavi dell'uscita principale!");
        chiaviU.setAlias(new String[]{"mazzo", "chiave"});
        chiaviU.setVisible(false);
        AdvObject catapulta = new AdvObject(60, "catapulta", "Non c'è che dire, hai proprio creato un gioiellino");
        catapulta.setAlias(new String[]{"gioiellino", "invenzione"});
        catapulta.setVisible(false);

        Character mamma = new Character(1, "Mamma", "");
        AdvObject mammaObj = new AdvObject(7, "Mamma", "Sta rammendando il tuo zaino.");
        mammaObj.setAlias(new String[]{"madre", "genitore1"});
        mammaObj.setPickupable(false);
        mamma.getDialogue().put(0, "Mickola. Deciso partire? Va bene. So non poter fermare, ma tu la puoi fare. Prendi, questa tua.");
        mamma.getDialogue().put(1, "Ho tappato qualche buco a tua borsa. Ora va se devi. Ma torna prima di ora di letto!");
        mamma.getDialogue().put(2, "Sono sicura che stasera tu ci farai uscire. Prima di viaggio saluta tuoi fratelli.");

        Character gouda = new Character(2, "Gouda", "");
        AdvObject goudaObj = new AdvObject(8, "Gouda", "Da cucciolo era il più piccolo di tutti. Poi ha scoperto che mangiare è un ottimo rimedio allo stress");
        goudaObj.setAlias(new String[]{"Gouda"});
        goudaObj.setPickupable(false);
        gouda.getDialogue().put(0, "Zzz...Zzz...Ehm. Cosa?! Che Succede?! Umani?! Aiuto mamma!! Ah, sei tu Mickola.Mi hai fatto venire colpo. Ora torno dormire");
        gouda.getDialogue().put(1, "Honk shh. Honk shh...");

        Character mortimer = new Character(3, "Mortimer", "");
        AdvObject mortimerObj = new AdvObject(9, "Mortimer", "Sta Lucidando un suo nuovo oggetto per la sua collezione di spazzatura...aspetta.Quella è la tua moneta!!");
        mortimerObj.setAlias(new String[]{"Mortimer"});
        mortimerObj.setPickupable(false);
        mortimer.getObjects().add(moneta);
        mortimer.getDialogue().put(0, "Piace mio nuovo tesoro? Credo si, visto che tu nascosto. Rivuoi tua moneta vero?");
        mortimer.getDialogue().put(1, "...");
        mortimer.getDialogue().put(2, "Io posso anche dare te, ma solo per scambio. Cosa è seme che hai in zaino?");
        mortimer.getDialogue().put(3, "Io posso anche dare te, ma solo per scambio.Però non so se tu hai niente interessante. ");
        mortimer.getDialogue().put(4, "Rivoglio moneta. Questo seme non luccica come moneta.");
        mortimer.getDialogue().put(5, "Se vuoi posso scambiare di nuovo moneta con seme. Questa non ha odore forte come seme");
        mortimer.getDialogue().put(6, "Vai  fare quello che devi. Io ora impegnato");

        Character speedy = new Character(4, "Speedy", "");
        AdvObject speedyObj = new AdvObject(10, "Speedy", "E' addormentato steso sulla ruota. Non pensavi avresti mai assistito a questa scena. Ma proprio questa notte?!");
        speedyObj.setAlias(new String[]{"Speedy"});
        speedyObj.setPickupable(false);
        speedy.getDialogue().put(0, "Uff. Ciao fratello. Oggi scienziati mi hanno spremuto bene. Troppo stanco anche per correre in ruota. Che tristezza.");
        speedy.getDialogue().put(1, "Correre in ruota il più veloce che può? Scusa fratello, ma oggi scienziati hanno testato mio tempo di apnea. Sono senza energia.");
        speedy.getDialogue().put(2, "Non so cosa tu hai dato, ma sento che oggi posso battere mio record!");

        Character gouda2 = new Character(5, "Gouda", "");
        AdvObject gouda2Obj = new AdvObject(14, "Gouda", "Vedi il grosso sedere di Gouda che sbuca dal ponticello.");
        gouda2Obj.setAlias(new String[]{"Gouda"});
        gouda2.getDialogue().put(0, "Non voglio che scienziati prendono me. Aiuto io non voglio mori...wow! Ho trovato croccantini qui sotto. ");
        gouda2.getDialogue().put(1, "Gnam gnam gnam");
        gouda2Obj.setPickupable(false);
        gouda2Obj.setVisible(false);

        Character speedy2 = new Character(6, "Speedy", "");
        AdvObject speedy2Obj = new AdvObject(15, "Speedy", "E' un po' frastornato dalle montagne russe, ma sembra stare bene");
        speedy2Obj.setAlias(new String[]{"Speedy"});
        speedy2Obj.setPickupable(false);
        speedy2.getDialogue().put(0, "Potrei fare anche altri trenta minuti di apnea ora!");
        speedy2.getDialogue().put(1, "Magari prima riprendo da capogiro.");

        Character pietro = new Character(7, "Pietro", "");
        AdvObject pietroObj = new AdvObject(52, "Pietro", "E' il più grande dei tuoi cugini e ti ricordi del suo caratteraccio.");
        pietroObj.setAlias(new String[]{"cugino", "cugini"});
        pietroObj.setPickupable(false);
        pietroObj.setVisible(false);
        pietro.getDialogue().put(0, "Quindi tu farai scappare noi? Ottima notizia. ALLORA CHIAMA SOLO QUADO FINITO! E' notte fonda e se "
                + " muori noi domani lavoro!  ");
        pietro.getDialogue().put(1, "Finito? No? Allora non disturbare!");
        Character albert = new Character(8, "Albert", "");
        AdvObject albertObj = new AdvObject(53, "Albert", "E' un grosso porcellino d'India che ti guarda con curiosità.");
        albertObj.setAlias(new String[]{"porcellino"});
        albertObj.setPickupable(false);
        albert.getDialogue().put(0, "Piacere. Mi chiamo Albert. Noto che anche tu sia un successo del farmaco MCP95. Mi dispiace per il disordine, ma non "
                + "mi aspettavo ospiti questa notte.");
        albert.getDialogue().put(1, "Quindi questa notte tenterai la fuga. Ammirevole, benchè io non ne senta la necessità, in quanto"
                + " qui dispongo di tutti i confort di cui necessito, ma se ha bisogno di un qualche aiuto fammi un fischio, sarò ben che lieto"
                + " di supportarti.");
        albert.getDialogue().put(2, "Se hai bisogno di una zampa basta dirlo, tranne se si tratta di applicare soluzioni violente. Ripugno"
                + " qualsiasi scelta che implichi la forza bruta.");
        albert.getDialogue().put(3, "Ti ringrazio per la tua ultima offerta, anche se in tutta onestà avverto un leggero capogiro e un certo... nervosismo.");
        albert.getDialogue().put(4, "CHE VUOI?! Perdonami, intendevo: serve niente?");
        albert.getDialogue().put(5, "LEVATI DI TORNO RATTO! ESCI DALLA MIA TECA PRIMA CHE TI USI COME STUZZICADENTI! WRAAA!");

        Character scaramarco = new Character(9, "Scaramarco", "");
        AdvObject scaramarcoObj = new AdvObject(54, "Scaramarco", "Un piccolo scarafaggio che ti guarda stupito, o almeno sembra, non ne sai molto di scarafaggi.");
        scaramarcoObj.setAlias(new String[]{"scarafaggio", "insetto"});
        scaramarcoObj.setPickupable(false);
        scaramarco.getDialogue().put(0, "Ciao. Io Scaramarco. Io vedere sempre voi che fate esperimenti. Io conoscere tutti voi. Tu Mickola! Posso avere autografo?");
        scaramarco.getDialogue().put(1, "Io vivere qui da due mesi. Io stare bene qui,voi fate molti spettacoli. Poco cibo però, posto troppo pulito. "
                + "Poco dolce qui in giro.");
        scaramarco.getDialogue().put(2, "Io fare tutto per zucchero, anche saltare in bocca a pesce qui avanti. Scherzare, o almeno spero. A volte"
                + " non riuscire a controllare.");

        Character inserviente = new Character(10, "Inserviente", "");
        AdvObject inservienteObj = new AdvObject(55, "Inserviente", "Un vecchio scorbutico che ha sempre odiato qualsiasi cavia di questo laboratorio. Non"
                + " aspetta altro che qualcuno tentasse la fuga per dargli la caccia. Vedi le chiavi dell'uscita attaccate alla sua cintura. Devi trovare il"
                + " modo di prenderle!");
        inservienteObj.setAlias(new String[]{"uomo"});
        inservienteObj.setPickupable(false);
        inservienteObj.setVisible(false);
        inserviente.getDialogue().put(0, "Mmh...");
        inserviente.getDialogue().put(1, "Ma perchè devo pulire sempre io in questo posto?!");
        inserviente.getDialogue().put(2, "AH...toglietemelo,toglietemelo!...aiut...");

        Character scaramarco2 = new Character(11, "Scaramarco", "");
        AdvObject scaramarco2Obj = new AdvObject(59, "Scaramarco", "E' troppo impegnato a mangiare la gomma per accorgersi dell'enorme pesce mutante dall'"
                + "altra parte del vetro.");
        scaramarco2Obj.setVisible(false);
        scaramarco2Obj.setAlias(new String[]{"scarafaggio", "insetto"});
        scaramarco2Obj.setPickupable(false);
        scaramarco2.getDialogue().put(0, "Oggi giorno fortunato! Gnam gnam, ho trovato tanto zucchero.");

        Character pesce = new Character(12, "pesce mutante", "");
        AdvObject pesceobj = new AdvObject(46, "pesce mutante", "Non sei uscito molto dal laboratorio, ma sei piuttosto sicuro che non ci siano in natura "
                + "pesci con un occhio solo e 6 pinne. Noti come guarda fisso ad ogni suo movimento lo scarafaggio all'angolo della stanza.");
        pesceobj.setAlias(new String[]{"pesce", "mostro", "mutante"});
        pesce.getDialogue().put(0, "Blblblbl");

        angoloM.getObjects().add(mammaObj);
        angoloM.getCharacters().add(mamma);
        sottoRuota.getCharacters().add(mortimer);
        sottoRuota.getObjects().add(mortimerObj);
        abbeveratoio.getObjects().add(ago);
        abbeveratoio.getObjects().add(abbeveratoioObj);
        abbeveratoioObj.getList().add(ago);
        nido.getObjects().add(chicco);
        palla.getObjects().add(pallaObj);
        palla.getObjects().add(fakeMoneta);
        pallaObj.getList().add(fakeMoneta);
        ponte.getCharacters().add(gouda2);
        ponte.getObjects().add(gouda2Obj);
        centro.getCharacters().add(gouda);
        centro.getObjects().add(goudaObj);
        piedi.getObjects().add(coda);
        piedi.getObjects().add(cacca);
        ruota.getObjects().add(ruotaObj);
        ruota.getCharacters().add(speedy);
        ruota.getObjects().add(speedyObj);
        cancello.getCharacters().add(speedy2);
        cancello.getObjects().add(speedy2Obj);
        cancello.getObjects().add(lucchetto);

        cugini.getObjects().add(lucchettoCugini);
        cugini.getCharacters().add(pietro);
        cugini.getObjects().add(pietroObj);
        sopraAlbert.getObjects().add(lucchettoAlbert);
        tecaAlbert.getCharacters().add(albert);
        tecaAlbert.getObjects().add(albertObj);
        video.getObjects().add(telecomando);
        video.getObjects().add(gioco);
        video.getObjects().add(monitor);
        caffe.getObjects().add(macchinaCaffe);
        caffe.getObjects().add(spina);
        caffe.getObjects().add(foglio);
        caffe.getCharacters().add(inserviente);
        caffe.getObjects().add(inservienteObj);
        caffe.getObjects().add(chiaviU);

        cabina.getObjects().add(armadio);
        armadio.getList().add(camice);
        cabina.getObjects().add(specchio);
        cabina.getObjects().add(camice);
        cabina.getObjects().add(cancelloC);
        cognitivo.getObjects().add(leva);
        cognitivo.getObjects().add(lampadina);
        cognitivo.getObjects().add(cavi);
        cognitivo.getObjects().add(piattaforma);
        cognitivo.getObjects().add(formaggio3);
        uscita.getObjects().add(porta);
        uscita.getObjects().add(trappola);
        uscita.getObjects().add(formaggio1);
        uscita.getObjects().add(catapulta);
        maniglia.getObjects().add(lucchettoU);
        qi.getObjects().add(tabellone);
        qi.getObjects().add(cerchio);
        qi.getObjects().add(esagono);
        qi.getObjects().add(formaggio2);
        giacca.getObjects().add(pacchetto);
        pacchetto.getList().add(sigaretta);
        giacca.getObjects().add(sigaretta);
        soffitto.getObjects().add(rilevatore);
        angoloS.getObjects().add(scaramarcoObj);
        angoloS.getCharacters().add(scaramarco);
        acquario.getObjects().add(acquarioObj);
        acquario.getObjects().add(pesceobj);
        acquario.getCharacters().add(pesce);
        acquario.getObjects().add(saetta);
        acquario.getObjects().add(scaramarco2Obj);
        acquario.getCharacters().add(scaramarco2);
        sottoScrivania.getObjects().add(gomma);
        scrivania.getObjects().add(righello);
        scrivania.getObjects().add(diario);
        ripostiglio.getObjects().add(torcia);
        torcia.getList().add(pile);
        ripostiglio.getObjects().add(pile);
        ripostiglio.getObjects().add(dardo);
        tavoloInserviente.getObjects().add(chiaviC);

        setCurrentRoom(nido);
    }

    /**
     *Definisce le azioni che vengono eseguite da ogni comando. Le azioni 
     * possono essere modificate dalle condizioni
     * @param p risultato della parserizzazione del comando dell'utente objects
     * @param out oggetto PrintWriter utilizzato per scrivere su un output stream.
     */
    @Override
    public void nextMove(ParserOutput p, PrintWriter out) {
        if (p.getCommand() == null) {
            out.println("Non ho capito cosa vuoi fare! Prova con un altro comando.");
        } else {
            boolean noroom = false;

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
            } else if (p.getCommand().getType() == CommandType.NORTH) {
                if (getCurrentRoom().getNorth() != null) {
                    setCurrentRoom(getCurrentRoom().getNorth());
                    setMove(true);
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.SOUTH) {
                if (getCurrentRoom().getSouth() != null) {
                    setCurrentRoom(getCurrentRoom().getSouth());
                    setMove(true);
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.EAST) {
                if (getCurrentRoom().getEast() != null) {
                    setCurrentRoom(getCurrentRoom().getEast());
                    setMove(true);
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.WEST) {
                if (getCurrentRoom().getWest() != null) {
                    setCurrentRoom(getCurrentRoom().getWest());
                    setMove(true);
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.UP) {
                if (getCurrentRoom().getUp() != null) {
                    setCurrentRoom(getCurrentRoom().getUp());
                    setMove(true);
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.DOWN) {
                if (getCurrentRoom().getDown() != null) {
                    setCurrentRoom(getCurrentRoom().getDown());
                    setMove(true);
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.INVENTORY) {
                if (con.isZaino()) {
                    if (!getInventory().isEmpty()) {
                        out.println("Nel tuo inventario ci sono:");

                        getInventory().forEach(o -> {
                            out.println(o.getName() + ": " + o.getDescription() + ".");
                        });
                    } else {
                        out.println("Non ci sono oggetti nel tuo inventario.");
                    }
                } else if (!con.isZaino()) {
                    out.println("Mickola: Devo ancora recuperare il mio zaino. L'avrà spostato Mamma come al solito");
                }
            } else if (p.getCommand().getType() == CommandType.LOOK_AT) {

                if (p.getObject() != null) {
                    out.println(p.getObject().getDescription());
                    if (p.getObject() instanceof AdvObjectContainer) {
                        containerLook((AdvObjectContainer) p.getObject(), out);
                    }
                }
                if (getCurrentRoom().getObjects().isEmpty()) {
                    out.println("Non vedi niente di interessante.");
                    getLook( out);
                } else if (p.getObject() == null) {
                    out.println("Nella stanza vedi:");
                    getCurrentRoom().getObjects().stream().filter(o -> (o.isVisible())).forEachOrdered(o -> {
                        out.println(o.getName());
                    });

                    getLook(out);
                }

            } else if (p.getCommand().getType() == CommandType.PICK_UP) {
                if (p.getObject() != null && p.getObject().isVisible()) {
                    getCurrentRoom().getCharacters().stream().filter(c -> (c.getName().equals(p.getObject().getName()))).forEachOrdered(_item -> {
                        out.println("Mickola:Non penso starebbe fermo nel mio zaino.");
                    });
                    pickUpEvent(p.getObject(), getCurrentRoom().getObjects(), getInventory(), out);
                } else if (p.getObject() == null || !p.getObject().isVisible()) {
                    out.println("Non penso di averlo visto in questa stanza.");
                }

            } else if (p.getCommand().getType() == CommandType.DROP) {
                if (p.getInvObject() != null) {
                    getInventory().remove(p.getInvObject());
                    getCurrentRoom().getObjects().add(p.getInvObject());
                    out.println("Hai lasciato: " + p.getInvObject().getName());
                } else {
                    out.println("Per lasciare qualcosa dovresti averla prima!");
                }

            } else if (p.getCommand().getType() == CommandType.OPEN) {
                con.setCharacter(false);
                if (p.getObject() != null && p.getInvObject() != null) {
                    MatchingObject(getCurrentRoom().getCharacters(), c -> c.getName().equals(p.getObject().getName()),
                            c -> {
                                out.println("Ti servirebbe un bisturi per quello");
                                con.setCharacter(true);
                            });
                    if (!con.isCharacter()) {
                        objectEvent(p.getObject(), p.getInvObject(), getCurrentRoom().getObjects(), getInventory(), out);
                    }
                }
                if (p.getObject() != null && p.getInvObject() == null) {
                    try {
                        MatchingObject(getCurrentRoom().getCharacters(), c -> c.getName().equals(p.getObject().getName()),
                                c -> {
                                    out.println("Ti servirebbe un bisturi per quello");
                                    con.setCharacter(true);
                                });

                        if (p.getObject().isOpen()) {
                            out.println("Non penso si possa aprire più di così...");
                        }
                        if (p.getObject().isOpenable() && !p.getObject().isOpen()) {
                            if (p.getObject() instanceof AdvObjectContainer) {
                                containerOpen((AdvObjectContainer) p.getObject(), getCurrentRoom().getObjects(), out);
                            } else {
                                interactEvent(getCurrentRoom().getObjects(), p.getObject(), out);
                            }
                        }
                        if (!p.getObject().isOpenable() && !con.isCharacter()) {
                            out.println("Non si apre così facilmete. Forse devi usare qualcosa, come una chiave o chessò io.");
                        }
                    } catch (NullPointerException e) {
                        openErrorHandling(p.getObject(), p.getInvObject(), out);
                    }
                }
                if (p.getObject() == null) {
                    out.println("Dovresti aprire un varco dimensionale invece se vuoi aprire qualcosa che non è qui!");
                }
            } else if (p.getCommand().getType() == CommandType.CLOSE) {
                if (p.getObject() != null) {
                    if (p.getObject().isOpenable() && p.getObject().isOpen()) {
                        if (p.getObject() instanceof AdvObjectContainer) {
                            containerClose((AdvObjectContainer) p.getObject(), getCurrentRoom().getObjects());
                        } else {
                            interactEvent(getCurrentRoom().getObjects(), p.getObject(), out);
                        }
                        out.println("Hai chiuso " + p.getObject().getName());

                    } else if (!p.getObject().isOpen()) {
                        out.println("Non è compito tuo assicurarti che le cose siano ben chiuse.");
                    }
                } else {
                    out.println("Non è nella stanza...vuoi fare il mimo?");
                }
            } else if (p.getCommand().getType() == CommandType.TALK_TO) {
                if (p.getObject() != null && p.getObject().isVisible()) {
                    MatchingObject(getCurrentRoom().getCharacters(), c -> c.getName().equals(p.getObject().getName()),
                            c -> {
                                out.println(c.getName() + ": " + c.getDialogue().get(c.getDialogueId()));
                                talkEvent(p.getObject(), getCurrentRoom().getObjects(), c, getInventory(), out);
                            });
                } else {
                    out.println("E' preoccupante voler parlare con chi non esiste.");
                }
            } else if (p.getCommand().getType() == CommandType.GIVE) {
                if (p.getObject() != null && p.getInvObject() != null) {
                    MatchingObject(getCurrentRoom().getCharacters(), c -> c.getName().equals(p.getObject().getName()),
                            c -> {
                                giveEvent(p.getObject(), c, p.getInvObject(), getInventory(), out);
                            });
                } else if (p.getObject() == null) {
                    out.println("Se vuoi darlo a qualcuno che non esiste puoi sempre lasciarlo a terra.");
                } else if (p.getInvObject() == null) {
                    out.println("Non credo apprezzerebbe ricevere un bel niente.");
                }
            } else if (p.getCommand().getType() == CommandType.USE) {
                try {
                    if (p.getInvObject() != null) {
                        objectEvent(p.getObject(), p.getInvObject(), getCurrentRoom().getObjects(), getInventory(), out);
                    } else {
                        interactEvent(getCurrentRoom().getObjects(), p.getObject(), out);
                    }

                } catch (NullPointerException e) {
                    if (p.getInvObject() == null) {
                        out.println("Non ce l'hai nell'inventario");
                    }
                    if (p.getObject() == null) {
                        out.println("Con cosa vuoi interagire?");
                    }
                }

            } else if (p.getCommand().getType() == CommandType.ATTACK) {
                try {
                    if (p.getObject() != null && p.getInvObject() != null) {
                        MatchingObject(getCurrentRoom().getCharacters(), c -> c.getName().equals(p.getObject().getName()),
                                c -> {
                                    attackEvent(p.getObject(), p.getInvObject(), getCurrentRoom().getObjects(), getInventory(), out);
                                });
                    } else if (p.getInvObject() == null) {
                        out.println("Ti dirò, non hai proprio l'aspetto del pugile.Forse dovresti provare con un oggetto che possiedi"
                                + " per avere qualche possibilità in più.");
                    }
                } catch (NullPointerException np) {
                    if (p.getObject() == null) {
                        out.println("E' meglio che non abbia colpito nessuno.");

                    } else if (p.getInvObject() == null) {
                        out.println("Ti dirò, non hai proprio l'aspetto del pugile.Forse dovresti provare con un oggetto che possiedi"
                                + " per avere qualche possibilità in più.");
                    }
                }
            } else if (p.getCommand().getType() == CommandType.END) {
                out.println("fine sessione");
            } else if (p.getCommand().getType() == CommandType.SAVE) {
                out.println("salva");
            }

            if (noroom) {
                out.println("Da quella parte non si può andare.");
            } else if (move) {
                //en.roomLabel.setText(getCurrentRoom().getName());
                out.println("room");
                out.println(getCurrentRoom().getName());
                out.println(getCurrentRoom().getDescription());
                setMove(false);

            }
        }
    }

    /**
     *
     * @param <T>
     * @param objects
     * @param predicate
     * @param action
     */
    public <T> void MatchingObject(List<T> objects, Predicate<T> predicate, Consumer<T> action) {
        objects.stream()
                .filter(predicate)
                .findFirst()
                .ifPresent(action);
    }

    
    public void giveEvent(AdvObject o, Character c, AdvObject i, List<AdvObject> inventory, PrintWriter out) {
        if (c.getId() == 1) {
            out.println("Mamma: Un regalo? Ma questo era già dentro teca... come accettato.");
        }
        if (c.getId() == 2 && i.getId() == 1) {
            out.println("Mickola: Se lo avvicinassi al suo muso mangerebbe anche la mia mano. Però questo chicco potrbbe tornare utile.");
        } else if (c.getId() == 2 && i.getId() != 1) {
            out.println("Se ne accorgerebbe solo se fosse cibo");
        }

        if (c.getId() == 5) {
            out.println("Gouda: No, io troppa paura per uscire! Se cibo lascia qua fuori.");
        }
        if (c.getId() == 3 && (i.getId() != 1 && i.getId() != 6 && i.getId() != 4)) {
            out.println("Mortimer: Non piace. Questa non va bene per scambio. Trova altro");
        } else if (c.getName().equals("Mortimer") && i.getId() == 1) {
            out.println("Mortimer: Questo seme non ho mai visto. E che odore forte ha. Va bene tieni tua moneta.");
            inventory.remove(i);
            inventory.addAll(c.getObjects());
            c.getObjects().removeAll(c.getObjects());
            c.getObjects().add(i);
            o.setDescription("Dopo aver annusato un po e lucidato il chicco ora non sembra più esaltato dello scambio.");
            c.setDialogueId(4);
        } else if (c.getId() == 3 && i.getId() == 6) {
            out.println("Mortimer: Così scintillante, e così pesante.Si si, riprendi tuo stupido seme.");
            inventory.remove(i);
            inventory.addAll(c.getObjects());
            c.getObjects().removeAll(c.getObjects());
            c.getObjects().add(i);
            o.setDescription("Dopo aver riammirato la moneta guarda nel tuo zaino. Sembra stia cercando il chicco.");
            c.setDialogueId(5);
        } else if (c.getId() == 3 && i.getId() == 4) {
            if (c.getDialogueId() == 5) {
                out.println("Mortimer: Ma...sniff...non ha stesso odore. Cosa hai fatto a seme? Ora è perfetto per mia collezione. Tieni pure tua brutta moneta.");
            } else {
                out.println("Mortimer: Sniff...sniff. Cosa è questo questo odore? Questo deve entrare in collezione! Tieni tua moneta e va via.");
            }
            inventory.remove(i);
            inventory.addAll(c.getObjects());
            c.getObjects().removeAll(c.getObjects());
            c.getObjects().add(i);
            o.setDescription("Mortimer si gode il suo pezzo di collezione mentre decide dove metterlo. "
                    + "E' prorpio vero che la spazzatura di un topo è il tesoro di un altro.");
            c.setDialogueId(6);

        }

        if (c.getId() == 4 && i.getId() == 1) {
            con.setCorre(true);
            out.println("Speedy:Cosa è questo. Sniff sniff. WOW. Che odore. Posso mangiare? Crunch crunch");
            out.println("Speedy:Sento molto meglio, molto più carico.Siiiii!!");
            out.println("Speedy inizia a correre nella ruota così velocemente che appena riesci a vedere le sue zampe");
            o.setDescription("Sta correndo senza sosta. Spero che non abbia ripercussioni sul suo cuore in futuro...");
            c.setDialogueId(2);
            inventory.remove(i);

            if (con.isCorre() && con.isSvitata()) {
                modifyRooms(out);
            }
        }

        if (c.getId() == 4 && i.getId() != 1) {
            out.println("Speedy: Grazie fratello di regalo, ma ora non saprei cosa farci.");
        }

        if (c.getId() == 6) {
            out.println("Speedy cerca di afferrare l'oggetto, ma barcolla troppo e si sdraia nella ruota");
        }

        if (c.getId() == 9 && i.getId() == 56) {
            out.println("Mickola: Sarebbe più utile fargliela guadagnare.");
            out.println("Mickola si gira e guarda l'acquario.");
        } else if (c.getId() == 9 && i.getId() != 56) {
            out.println("Scaramarco: Solo perchè io scarafaggio allora piacere spazzatura? Io essere offeso.");
        }

        if (c.getId() == 12 && i.getId() == 56) {
            objectEvent(o, i, getCurrentRoom().getObjects(), inventory, out);
        } else if (c.getId() == 12 && i.getId() != 56) {
            out.println("Meglio che non accetti, il suo unico modo di ringraziarti sarebbe mangiarti!");
        }

        if (c.getId() == 8 && i.getName().equals("formaggio")) {
            switch (con.getFuria()) {
                case 0:
                    inventory.remove(i);
                    con.addFuria();
                    out.println("Albert: Un tocco di formaggio?. Grazie per il pensiero, anche se non dovrei accettare. Tu mi tenti.");
                    out.println("Albert prende il pezzo di formaggio e lo mangia elegantemente");
                    c.setDialogueId(3);
                    break;
                case 1:
                    inventory.remove(i);
                    con.addFuria();
                    out.println("Albert: Un altro tocco di formaggio. A dir la verità non dovrei accettare, il formaggio mi fa un brutto"
                            + " effetto, ma come puoi ben vedere sono un golosone.");
                    out.println("Albert prende il pezzo di formaggio con una certa foga e lo mangia molto più rumorosamente di prima");
                    o.setDescription("Albert ha gli occhi spalancati, ha come dei tick e fa versi molto poco eleganti.");
                    c.setDialogueId(4);
                    break;
                case 2:
                    inventory.remove(i);
                    con.addFuria();
                    out.println("Albert: ANCORA FORMAGGIO?");
                    out.println("Albert ti strappa il formaggio dalle zampe e lo divora.");
                    out.println("Albert inizia a fare versi che assomigliano più a quelli di un lupo che di un porcellino d'India. Si gira verso di te"
                            + " con uno sguardo così spaventoso che corri fuori dalla sua teca");
                    setCurrentRoom(getCurrentRoom().getUp());
                    setMove(true);
                    getCurrentRoom().setDown(null);
                    out.println("Mickola: Oh santo provolone! Me la sono vista brutta. Ora sono sicuro che Albert attaccherà senza pietà"
                            + " qualsiasi cosa si avvicini a lui.");
                    out.println(" ");
                    break;
                default:
                    break;
            }

        } else if (c.getId() == 8 && !i.getName().equals("formaggio")) {
            if (con.getFuria() < 2) {
                out.println("Albert: grazie per il pensiero ma piace definirmi un minimalista.");
            } else {
                out.println("Albert: NON ME NE FACCIO NIENTE DELLA TUA SPAZZATURA. Oh scusami, volevo dire, no grazie. Eheh");
            }
        } else if (c.getId() == 10 && !con.isSvenuto()) {
            out.println("Molto carino da parte tua fargli un regalo, sicuramente ti ringrazierà facendoti uscire dentro un sacco dell'immondizia...");
        } else if (c.getId() == 10 && con.isSvenuto()) {
            out.println("In queste condizioni non credo se ne farà molto.");
        } else if (c.getId() == 7) {
            out.println("Pietro: Per me? Non dovevi.\nTi lancia addosso l'oggetto. Riesci a prenderlo al volo "
                    + " per un soffio.\nPietro: Muoviti farci scappare e smettila di svegliare noi!");
        } else if (c.getId() == 11) {
            out.println("Mickola: Non credo desideri nient'altro al momento.");
        }
    }

    public void interactEvent(List<AdvObject> objects, AdvObject o, PrintWriter out) {
        if (o.getId() == 8) {
            out.println("Più spingi più vieni inglobato dalla sua...\"pelliccia\" ");

        } else if (o.getId() == 7) {
            out.println("Mamma: Non abbracciavi da quando eri  cucciolo. Ricordati di portare souvenir quando torni.");
        } else if (o.getId() == 13) {
            out.println("La smuovi un po', ma è troppo pesante. Non riesci a vedere cosa c'è sotto.");
        } else if (o.getId() == 2) {
            out.println("L'acqua ha uno strano sapore metallico. ");
        } else if (o.getId() == 12 && !con.isCorre()) {
            out.println("Dovresti correre molto più velocemente per smuoverla ma la corsa non è il tuo forte.");

        } else if (o.getId() == 12 && con.isCorre()) {
            out.println("Mickola: sarebbe interessante scoprire come si sente un calzino in lavatrice, ma non mi pare il momento adatto.");

        } else if (o.getId() == 9) {
            out.println("Allunghi le zampe verso l'oggetto che tiene fra le sue, ma Mortimer te le colpisce con la usa coda.");
            out.println("Mortimer: Non provare");

        } else if (o.getId() == 10 && !con.isCorre()) {
            out.println("Cerchi di scuoterlo un po', ma Speedy cade a terra quasi svenuto");

        } else if (o.getId() == 10 && con.corre) {
            out.println("Mickola: Corri Speedy, corri!");
            out.println("Speedy alza il pollice mentre continua a correre con lo sguardo fisso in avanti.");

        } else if (o.getId() == 17 && !o.isOpen()) {
            o.setOpen(true);
            objects.forEach(obj -> {
                obj.setVisible(true);
            });
            objects.remove(o);
            out.println("Apri l'entrata della teca  e intravedi alcuni dei tuoi cugini che si svegliano, mentre dall'entrata esce tuo cugino Pietro"
                    + " con i baffi tutti arraffati dal sonno. Ma perchè proprio lui?!");
        } else if (o.getId() == 18 && !o.isOpen()) {
            o.setOpen(true);
            for (Room r : getRooms()) {
                if (r.getId() == 17) {
                    getCurrentRoom().setDown(r);
                    break;
                }
            }
            objects.remove(o);
            out.println("Apri l'entrata della teca e vedi il porcellino d'India che da dentro la teca ti saluta con la zampa.(Aggiunta \" Teca di Albert"
                    + " verso sotto\").");
        } else if (o.getId() == 24) {
            if (!con.isSpina()) {
                con.setSpina(true);
                out.println("Rosicchi il nastro che avvolge il cavo.Una parte del cavo ora è scoperta.");
                o.setDescription("Il cavo è scoperto. Non capisco se l'hai fatto per un qualche motivo o per puro vandalismo.");
            } else if (con.isSpina()) {
                out.println("Rosicchi il cavo scoperto e vieni colpito da una scossa che ti attraversa i denti. La cosa come al solito ti diverte"
                        + " ma è meglio non esagerare.");
            }
        } else if (o.getId() == 29) {
            if (!con.isRiparati() && !con.isAbbassata()) {
                out.println("Abbassi la leva ed inizia ad uscire qualche scintilla dai cavi. Non succede nient'altro. Quando la lasci la leva si rialza."
                        + " Sarà a causa del malfunzionamento");
            } else if (con.isRiparati() && !con.isAbbassata() && !con.isSvitataL()) {
                out.println("Abbassi la leva e vedi la lampada in fondo alla stanza accendersi");

                objects.stream().filter(obj -> (obj.getId() == 31)).forEachOrdered(obj -> {
                    obj.setDescription("Senti come si sia molto riscaldata. Se non vuoi una zampa ben cotta ti consiglio di non avvicinarti troppo.");
                });
                con.setAbbassata(true);
            } else if (con.isRiparati() && !con.isAbbassata() && con.isSvitataL()) {
                out.println("Senti la corrente che inizia a fluire all'interno dei cavi. Senti anche un certo calore provenire dalla piattaforma.");

                objects.stream().filter(obj -> (obj.getId() == 31)).forEachOrdered(obj -> {
                    obj.setDescription("Senti come si sia molto riscaldata Se non vuoi una zampa ben cotta ti consiglio di non avvicinarti troppo.");
                });
                con.setAbbassata(true);
            } else if (con.isAbbassata()) {
                out.println("Hai rialzato la leva.");
                con.setAbbassata(false);
                if (con.isRiparati()) {

                    MatchingObject(objects, obj -> obj.getId() == 31,
                            obj -> {
                                obj.setDescription("E' la base a cui arriva la corrente. Al momento però è spenta");
                            });
                    if (!con.isSvitataL()) {
                        out.println("La lampada si spegne");
                    }
                }
            }
            if (con.isRiparati() && con.isAbbassata() && !con.isPremio()) {
                out.println("Lo sportellino di metallo si solleva rivelando un premio che non avresti mai potuto immaginare. O forse si.");
                con.setPremio(true);
                objects.stream().filter(obj -> (!obj.isVisible())).forEachOrdered(obj -> {
                    obj.setVisible(true);
                });
            }
        } else if (o.getId() == 30 && con.isRiparati() && con.isAbbassata()) {
            out.println("Metti le zampe sull'ago e senti una scossa che ti sbalza indietro. Basta giocare con l'elettricità.");
        } else if (o.getId() == 32) {
            if (con.isSvitataL()) {
                out.println("riavviti la  lampadina nel suo alloggio");
                o.setDescription("E' una comune lampadina, la puoi riavvittare nel suo alloggio.");
                con.setSvitataL(false);
            } else if (!con.isSvitataL()) {
                out.println("hai svitato la lampadina dal suo alloggio");
                o.setDescription("E' una comune lampadina. Ci stai prendendo gusto a svitarla e avvitarla?");
                con.setSvitataL(true);
            }
        } else if (con.isScattata() && o.getId() == 33) {
            if (!o.isOpen()) {
                out.println("Hai rimesso la trappola in posizione. Ora è di nuovo pronta per scattare.");
                o.setOpen(true);
            } else if (o.isOpen()) {
                out.println("Fai azionare la trappola che fa un suono spaventoso. Controlli di avere ancora la coda, per sicurezza.");
                o.setOpen(false);
            }
        } else if (!con.isScattata() && o.getId() == 33) {
            out.println("Meglio farla scattare prima di toccarla, per essere sicuro di uscire da qui tutto d'un pezzo.");
        } else if (o.getId() == 47) {
            con.setPagina((byte) (con.getPagina() + 1));
            out.println("Giri le pagine del diario.");
            if (con.getPagina() == 2) {
                o.setDescription("Sembra il diario dove vengono presi appunti sulle ricerche:\n"
                        + "\"17 Maggio 1973: Il soggetto Gouda, durante l'ultimo test cognitivo mostra evidenti segni di stress ed in preda "
                        + "al nervosismo danneggia irreparabilmente i cavi del test di utilizzo di macchinari rosicchiandoli ed ingurgitandoli."
                        + "Dato il gran numero di test sostenuti e il quantitativo di materiale da laboratorio ingerito non comprendo come il soggetto"
                        + " goda di una buona salute. E' un fenomeno al quanto misterioso.\"");
            } else if (con.getPagina() == 3) {
                o.setDescription("Sembra il diario dove vengono presi appunti sulle ricerche:\n"
                        + "\"12 Maggio 1973Il soggetto Pietro durante l'ultimo test del QI, nonostante mostri di conoscere la corrispondenza tra le sagome e i pezzi,"
                        + " prende il pezzo a forma di saetta e lo lancia dentro la vasca del pesce geneticamente modificato. Nessuno ha il coraggio di recuperarlo.\"");
            }
            if (con.getPagina() > 3) {
                con.setPagina((byte) 1);
                o.setDescription("Sembra il diario dove vengono presi appunti sulle ricerche:\n"
                        + "\"19 Maggio 1973:Sono stati risconstrati sul soggetto Albert, un porcellino d'India, effetti collaterali nell'ultima versione "
                        + "dell' MCP95 in seguito al consumo eccessivo di grassi. Si sono osservati comportamenti estremamenti violenti e un "
                        + "aumento della forza fisica del soggetto. ha attaccato il Dr. Bordignon facendolo svenire. Fortunatamente il Dr. Bordignon non mi è"
                        + " mai piaciuto. Si sconsiglia di nutrire Albert con noci, noccioline, o formaggio, a meno che non ci sia di turno il Dr. Bordignon.\"");
            }
        } else if (o.getId() == 50 && !o.isOpen()) {
            if (!con.isAccesaTorcia()) {
                con.setAccesaTorcia(true);
                out.println("La torcia emana una luce pittosto fioca, ma almeno ora sai che è carica");
            } else if (con.isAccesaTorcia()) {
                con.setAccesaTorcia(false);
                out.println("Spegni la torcia");
            }

        } else if (o.getId() == 27) {
            pickUpEvent(o, objects, getInventory(), out);
        } else if (o.getId() == 25) {
            out.println("Ogni posa che fai davanti allo specchio ti fa sentire sempre più fico.");
        } else if ((o.getId() == 19 || o.getId() == 20) && !con.conPile) {
            out.println("Premi il tasto di accensione dal telecomando, ma il monitor rimane spento. Controllando il telecomando scopri che non ha batterie.");
        } else if ((o.getId() == 19 || o.getId() == 20) && con.conPile) {
            if (!con.isMonitorAcceso()) {
                out.println("Lo schermo del monitor si accende e vedi la schermata iniziale di \"Fantastic Fantasy VII\". Ora puoi avviare la partita");
                con.setMonitorAcceso(true);
            } else if (con.isMonitorAcceso()) {
                out.println("Spegni il monitor. Non lasciarti fermare dal rage quit!");
                con.setMonitorAcceso(false);
            }
        } else if (o.getId() == 21) {
            if (!con.isMonitorAcceso() && !con.isSvenuto()) {
                out.println("Capisco che sei un esperto, ma giocare con lo schermo spento è una sfida persione per te.");
            } else {
                // modificare in ogni caso
                out.println("Avvii la partità del gioco e subito ti ritrovi a dover affrontare il terribile boss finale: Sephibot. Il combattimento"
                        + " è difficile, ma usi tutte le abilità e latua esperienza nel gioco per sconffigere Sephibot con un ultimo colpo finale."
                        + " Alla fine ne esci vincitore.");
                out.println("Il monitor emette la musica per la vittoria dello scontro al massimo volume.");
                out.println("Inserviente: Ma cosa diavolo è questa musica?!");
                out.println("L'inserviente si gira e ti vede. Inizia a correre nella tua direzione");
                out.println("Inserviente:Tu! Finalmente ti becco, ahahah! Fra tutte queste bestiacce tu sei quella che ho sempre detestato"
                        + " di più!");

                if (con.getFuria() == 3) {
                    out.println("L'inserviente ti afferra");
                    out.println("Inserviente: Ti ho preso rattaccio. Ora è il momento di fare una disinfestazione");
                    out.println("Proprio quando pensi sia tutto finito vedi qualcosa saltare sulla faccia dell'inserviente. E Albert, che "
                            + "ormai senza controllo, attacca l'inserviente con graffi e morsi.");
                    out.println("Inserviente: Ahia! Ouch! Che diavolo succede?!");
                    out.println("L'inserviente cercando di liberarsi da Albert ti lascia andare,facendoti caderesopra la sua teca. "
                            + "Albert continua ad mordere senza tregua, mentre l'inserviente cerca in tutti i modi di fuggire, quando correndo "
                            + "scivola sulla macchia di caffè che stava pulendo e cade per terra svenendo,poggiato alla macchinetta"
                            + ". Albert, ancora fuori di sè torna nella sua teca dove si chiude dentro, facendo un ultimo "
                            + "ruggito, che non sapevi i porcellini d'India sapessero fare.");
                    out.println("Mickola: Non mi aspettavo ci sarebbe stato tanto sangue, ma almeno ha funzionato! ");
                    out.println("Ora puoi recuperare le chiavi dall'inserviente per poi fuggire. La libertà e ad un passo di distanza!");
                    con.setSvenuto(true);
                    setMove(true);
                    setCurrentRoom(getCurrentRoom().getSouth());
                    
                     MatchingObject(getRooms(), r->r.getId() ==20,
                            r-> {
                                MatchingObject(r.getObjects(), obj -> obj.getId() == 55,
                            obj -> {
                                obj.setDescription("Ha preso una bella botta. Se l'è meritato in fondo.");
                            });
                            });
                     
                      MatchingObject(getRooms(), r->r.getId() ==20,
                            r-> {
                                MatchingObject(r.getObjects(), obj -> obj.getId() == 51,
                            obj -> {
                                obj.setVisible(true);
                            });
                            });
                } else {
                     out.println("L'inserviente riesce ad afferrarti e con la presa ben salda ti porta nella sua stanza da lavoro. Guarda il lato positivo"
                + ", sarai un pezzo da collezione d'eccezione!");
                      out.println("Il piano era quasi perfetto, se solo ci fosse stato qualcuno ad aiutarti un quel momento. ");
                    out.println("game over");
                }
            }
        } else if (o.getId() == 60) {
            out.println("Usi te stesso come cavia da test della catapulta, tanto sei abituato. Vieni sparato molto sopra la maniglia della porta, per "
                    + "poi cadere a terra rovinosamente non riuscendo ad afferrare la maniglia. Hai un fortissimo mal di testa, ma almeno hai"
                    + " scoperto che la catapulta ha qualche carenza di precisione.");
        } else {
            out.println("Non succede niente");
        }
    }

    public void objectEvent(AdvObject o, AdvObject i, List<AdvObject> objects, List<AdvObject> inventory, PrintWriter out) {

        if (o.getId() == 2) {
            o.setDescription("Mickola: Per fortuna nessuno si è strozzato con l'ago...");
        } else if (o.getId() == 12 && i.getId() == 6 && !con.isSvitata()) {
            con.setSvitata(true);
            out.println("Così dovrebbe andare. Ora servirebbe dare una bella scossa per farla andare giù dalla rampa");

            if (con.isCorre() && con.isSvitata()) {
                modifyRooms(out);
                
            } else if (con.isSvitata()) {
                out.println("Non penso si possa allentare più di così.");
            }
        } else if (o.getId() == 16 && i.getId() == 6) {
            out.println("Inserendo la moneta nella fessura del lucchetto e girandola sblocchi il cancelletto.( Ora puoi andare \"Fuori dalla gabbia\""
                    + " verso sopra).");
            getCurrentRoom().getObjects().remove(o);
             MatchingObject(getRooms(), r->r.getId() ==21,
                            r-> {
                            getCurrentRoom().setUp(r);
                            });
        } else if ((o.getId() == 17 || o.getId() == 18) && i.getId() == 6) {
            interactEvent(objects, o, out);
        } else if ((o.getId() == 8 && i.getId() == 3)) {
           
            out.println("Gouda: MAMMAAAAA!!!");
            out.println("Gouda corre con una velocità sorprendete sotto il ponte dove si nasconde, lanciandosi sotto, lasciando fuori la coda e "
                    + "sollevandolo");
            out.println("A giudicare dall'odore che viene da nord, Gouda si è spaventato più del previsto");
            objects.remove(o);
            
            getRooms().stream().map(r -> {
                if (r.getId()==9) {
                    r.setDescription("Si vede ancora la grossa sagoma di Gouda sui trucioli. Ora si sente uno strano odore da nord.");
                }
                return r;
            }).map(r -> {
                if (r.getId()==13 || r.getId()==10) {
                    r.getObjects().forEach(ob -> {
                        if (ob.isVisible()) {
                            ob.setVisible(false);
                        } else {
                            ob.setVisible(true);
                        }
                    });
                }
                return r;
            }).filter(r -> (r.getId()==10)).forEachOrdered(r -> {
                r.setDescription("Sembra che gouda prima di scappare abbia lasciato un regalino. Che odoraccio");
            });
            
            
        } else if (o.getId() == 37 && (i.getId() >= 38 && i.getId() <= 40)) {
            if (con.getFormine() < 3) {
                con.addFormine();
                inventory.remove(i);
                out.println("Inserisci " + i.getName() + " nello spazio corretto.");
            }
            if (con.getFormine() == 3) {
                out.println("Senti un click provenire dal tabellone e lo vedi che si alza meccanicamente, scoprendo uno scaffale con un premio. E' del"
                        + " formaggio...ovvio.");
                getCurrentRoom().setDescription("Era tanto tempo che non venivi qui. Vedi un tabellone in legno"
                        + " con dei fori a forma di cerchi, esagono e saetta. Hai risolto il test senza alcuno sforzo. Circa.");
                
                objects.stream().filter(ob -> (!ob.isVisible())).forEachOrdered(ob -> {
                    ob.setVisible(true);
                });
            }
        } else if (o.getId() == 30 && i.getId() == 3) {
            out.println("Prendi una piccola scossa che ti fa drizzare la coda. Almeno sai che adesso il cavo è riparato");
            o.setDescription("Ora la corrente dovrebbe fluire fino alla piattaforma. Chissà se prenderai altre scosse.");
            inventory.remove(i);
            con.setRiparati(true);
        } else if ((o.getId() == 34 || o.getId() == 33) && !con.isScattata() && (i.getId() == 3 || i.getId() == 48 || i.getId() == 49)) {
            out.println("Fai cadere il formaggio dalla trappola facendola attivare. Osservandola in azione ti chiedi se  quel sistema a molle"
                    + "possa essere utile in altri modi.");
            con.setScattata(true);
            
            objects.stream().map(obj -> {
                if (obj.getId() == 33) {
                    obj.setDescription("Osservandola pensi di poter usare il meccanismo per costruire una catapulta. "
                            + " Ti manca però un cucchiaio o un asse...");
                    obj.setOpen(false);
                    obj.setOpenable(true);
                }
                return obj;
            }).filter(obj -> (obj.getId() == 34)).forEachOrdered(obj -> {
                obj.setPickupable(true);
            });
        } else if (con.isAbbassata() && con.isSvitataL() && i.getId() == 42 && o.getId() == 31) {
            out.println("Inserisci la sigaretta all'interno della piattaforma. La corrente sviluppa energia a sufficienza che accende la sigaretta!"
                    + "Però che puzza");
            i.setDescription("Sei riuscito ad accenderla! Anche se portarla nello zaino non è l'idea più sicura");
            i.setName("sigaretta(accesa)");
            con.setAccesa(true);
        } else if (con.isAbbassata() && !con.isSvitataL() && i.getId() == 42 && (o.getId() == 31 || o.getId() == 32)) {
            out.println("La lampadina non è abbastanza calda. Forse bisogna andare più in profondità.");
        } else if (con.isAccesa() && !con.isSpina() && i.getId() == 42 && o.getId() == 44) {
            out.println("Inizia a suonare la campanella d'allarme e gli erogatori iniziano a rilasciare acqua. Dopo qualche istante l'inserviente "
                    + "spalanca la porta \nInserviente: Che succede ora?!\nCorre verso l'interrutore del sistema antincedio vicino la macchinetta"
                    + " del caffè e lo interrompe. \nInserviente: Questo posto è da demolire! Farò finta di niente fino a domani.\nL'inserviente torna"
                    + "dentro la sua stanza chiudendo la porta.");
            out.println("Mickola: Ci sono quasi! Ho bisogno di un diversivo che lo impegni per più tempo.");
        } else if (con.isAccesa() && con.isSpina() && i.getId() == 42 && o.getId() == 44) {
            out.println("Inizia a suonare la campanella d'allarme e gli erogatori iniziano a rilasciare acqua. Escono delle scintille dalla spina"
                    + " della macchina del caffè, e inizia a sparare caffè all'impazzata tutto intorno. Dopo qualche istante l'inserviente "
                    + "spalanca la porta \n Inserviente: Che succede ora?! \nCorre verso l'interrutore del sistema antincedio vicino la macchinetta"
                    + " del caffè e lo interrompe. \nInserviente: Maledizione! Non mi posso permette le riparazioni di questo dannato affare. \nTorna nella "
                    + "sua stanza ed esce poco dopo con mocho e secchio lasciando la porta della sua stanza aperta. (Puoi raggiungere la stanza dell'inserviente"
                    + " verso ovest). Inizia a pulire per terra dicendo cose che non mi sembra il caso che tu impari. "
                    + "\n La sigaretta si bagna. Non sembra si possa più riaccendere.(sigaretta è stata rimossa dall'inventario)");
            inventory.remove(i);
            
            getRooms().stream().map(r -> {
                if (r.getId() >= 31 && r.getId() <= 33) {
                    
                    getRooms().stream().filter(r1 -> (r1.getId() == 39)).forEachOrdered(r1 -> {
                        r.setWest(r1);
                    });
                }
                return r;
            }).map(r -> {
                if (r.getId() == 20) {
                    r.getObjects().stream().filter(obj -> (!obj.isVisible())).filter(obj -> !(obj.getId() == 51)).forEachOrdered(obj -> {
                        obj.setVisible(true);
                    });
                }
                return r;
            }).filter(r -> (r.getId() == 31)).forEachOrdered(r -> {
                r.setDescription("Sei davanti alla stanza dell'inserviente. La porta ora è aperta.");
            });
            
        } else if (o.getId() == 22 && i.getId() == 6) {
            if (con.isSpina() && con.isFumo()) {
                out.println("Non è il caso di prendersi un caffè proprio davanti a quel pazzo!");
            } else {
                out.println("La macchina prepara un caffè, schizzando qua e là. C'è più caffè fuori che dentro il bicchiere");
                inventory.remove(i);
            }

        } else if (!con.isDistratto() && (o.getId() == 45 || o.getId() == 46) && i.getId() == 56) {
            con.setDistratto(true);
            inventory.remove(i);
            out.println("Lanci la gomma in acqua, ma poco prima che tocchi senti una voce.");
            out.println("Scaramarco: NOOOOOOOOO!");
            out.println("Scaramarco si muove così veloce che corre sull'acqua. Riesce ad afferrare al volo la gomma, ma proprio in quell'istante"
                    + " sotto i piedi di Scaramarco emerge il mostro che tenta di mangiarlo.");
            out.println("Scaramarco: AIUTO!");
            out.println("Dopo essersi rincorsi per tutto l'acquario, Scaramarco riesce a saltare fuori dall'acqua, appiciccandosi dall'altra parte del"
                    + " vetro dell'acquario, dove inizia a mangiare la gomma, mentre il pesce mutante continua a sbattere contro il vetro cercando di"
                    + " raggiungere lo scarafaggio.");
            out.println("Mickola: Che scena! Ora il pesce è distratto, immergersi dovrebbe essere sicuro...spero");

            objects.stream().map(obj -> {
                if (!obj.isVisible()) {
                    obj.setVisible(true);
                }
                return obj;
            }).filter(obj -> (obj.getId() == 40)).forEachOrdered(obj -> {
                obj.setPickupable(true);
            });
            
             MatchingObject(getRooms(), r->r.getId() ==34,
                            r-> {
                            r.getObjects().clear();
                    r.setDescription("E' un angolo sporco dove ti è sempre sembrato di vedere qualcosa muoversi. Ora sai come si trattasse di Scaramarco.");
                            });
        } else if (o.getId() == 28 && i.getId() == 58) {
            out.println("Usando la chiave riesci ad aprire il cancello della stanza!(Aggiunto \"Laboratorio problem solving\" a est).");
            getCurrentRoom().setDescription("Sei nella cabina che precede la stanza di testig di problem solving.");
            out.println(getCurrentRoom().getDescription());
            objects.remove(o);
            inventory.remove(i);
            objects.stream().filter(obj -> (!obj.isVisible())).filter(obj -> !(obj.getId() == 27)).forEachOrdered(obj -> {
                obj.setVisible(true);
            });
             MatchingObject(getRooms(), r->r.getId() ==41,
                            r-> {
                            getCurrentRoom().setEast(r);
                            });
             
        } else if (o.getId() == 19 && i.getId() == 57) {

            inventory.remove(i);
            con.setConPile(true);
            out.println("Inserisci le pile dentro il telecomando.");
            out.println("Mickola: ora dovrebbe funzionare.");
        } else if (o.getId() == 33 && con.isScattata() && i.getId() == 48) {
            out.println(("Grandioso! Hai costruito una catapulta di tutto punto. Ora dovresti riuscire a raggiungere la maniglia sopra di te."
                    + " Sarebbe meglio effettuare qualche test prima, ma non c'è tempo. (Ora puoi raggiungere la \"Maniglia\" verso sopra)."));
            objects.stream().map(obj -> {
                if (!obj.isVisible()) {
                    obj.setVisible(true);
                }
                return obj;
            }).filter(obj -> (obj.getId() == 48 || obj.getId() == 33)).forEachOrdered(obj -> {
                obj.setVisible(false);
            });

            
             MatchingObject(getRooms(), r->r.getId() ==42,
                            r-> {
                             getCurrentRoom().setUp(r);
                            });
            getCurrentRoom().setDescription("E' l'unica uscita da questo posto, devi trovare il modo di aprire quella porta e"
                    + ", ATTENZIONE! La tua catapulta.");
            
        } else if (o.getId() == 36 && i.getId() == 51) {
            out.println("Apri la porta d'uscita. Ce l'hai fatta! Torni alla tua teca e chiami la tua famiglia. Escono tutti dalla teca,"
                    + " tranne Gouda, che ha bisogno di una mano da parte di tutti, inaspettatamente anche Pietro aiuta nel disincastrarlo."
                    + " Pietro e gli altri cugini non ti ringraziano esplicitamente, ma noti che ora ti guardano con uno sguardo diverso."
                    + " Tutti escono dall'entrata principale, tu rimani dietro e prima di uscire guardi il laboratorio. Vedi dalla sua teca"
                    + " Albert ,che ormai ha riacquistato il controllo, salutarti come la prima volta che vi siete incotrati. Raggiungi "
                    + "la tua famiglia fuori il laboratorio, e iniziate a correre verso un piccolo bosco non molto lontano."
                    + " Una volta sotto gli alberi vi fermate a riposare. A quel punto incominciano tutti a festeggiare e a lanciarti per"
                    + " aria. Tua madre si avvicina e ti accarezza la testa.");
            out.println("Mamma: Sapevo avresti fatta.");
            out.println("Sei riuscito a fuggire dal laboratorio! Hai completato \"M0U53\". Complimenti!");
            out.println("Premi invio per continuare");
            out.println("menu");
        } else {
            out.println("Non sembra funzionare");
        }
    }

    public void pickUpEvent(AdvObject o, List<AdvObject> objects, List<AdvObject> inventory, PrintWriter out) {
        if (o.isPickupable()) {
            if (o.getId() == 11) {
                out.println("Metti la zampa dentro un foro fra i rami che formano la palla, ma non senti la moneta che avevi nascosto");
                out.println("Mickola: Oh no! Quella moneta è fondamentale per la riuscita del piano. Dove sarà finita?");
                objects.remove(o);
            } else {
                if (con.isZaino()) {
                    inventory.add(o);
                    objects.remove(o);
                    if (o.getId() == 40) {
                        o.setDescription("Formina di una saetta in metallo. Ha un forte odore di pesce.");
                    } else if (o.getId() == 57 && con.isAccesaTorcia()) {
                        out.println("La torcia si spegne.");
                    }

                    out.println("Hai raccolto: " + o.getName() + " .");
                    out.println(" ");
                    out.println(o.getName() + ": " + o.getDescription());

                    if (o.getId() == 27) {
                        out.println("Indossi il tuo camice con fierezza. Ora sei pronto per una fuga con stile.");
                        inventory.remove(o);
                    } else if (o.getId() == 51) {
                        o.setDescription("Non c'è modo di sbagliarsi. C'è un solo posto dove puoi utilizzare queste chiavi!");
                    }

                    //controlla che l'oggetto da prendere sia dentro un contenitore per eliminarlo dal suo interno
                    objects.stream().filter(obj -> (obj instanceof AdvObjectContainer && obj.isOpen())).filter(obj -> (((AdvObjectContainer) obj).getList().contains(o) && !((AdvObjectContainer) obj).getList().isEmpty())).forEachOrdered(obj -> {
                        ((AdvObjectContainer) obj).getList().remove(o);
                    });
                } else if (!con.isZaino()) {
                    out.println("Mickola prende l'oggetto e lo mette dove di solito ha il suo zaino, ma l'oggetto cade a terra.");
                    out.println("Mickola: Ah già. Devo ancora recuperare il mio zaino. L'avrà spostato mamma come al solito.");
                }
            }
        } else if (!o.isPickupable()) {
            if (o.getId() == 34) {
                out.println("Ci vuoi cascare davvero così facilmente?");
            } else if (o.getId() == 40) {
                out.println(" Poco prima di raggiungere la saetta il pesce ti nota e nuota a tutta velocità nella la tua direzione. Nuoti il più veloce che puoi verso la superficie"
                        + " , e proprio quando sta per assaggiare il tuo fondoschiena, riesci a balzare fuori dall'acqua come un delfino."
                        + " Sei sul bordo dell'acquario bagnato e con il fiatone. Sei ancor più bianco di prima. Vedi il pesce tornare ad osservare"
                        + " lo scarafaggio.");
                out.println("Mickola:Anf...Anf...devo trovare un modo per...distrarlo. Pare...pare gli piaccia quello scarafaggio...");

            } else {
                out.println("Non puoi raccoglierlo.");
            }
        }
    }

    public void getLook(Room r, PrintWriter out) {
        out.println(" ");
        if (r.getNorth() != null) {
            out.println("A nord vedi: " + r.getNorth().getName());
        }

        if (r.getSouth() != null) {
            out.println("A sud vedi: " + r.getSouth().getName());
        }

        if (r.getWest() != null) {
            out.println("A ovest vedi:" + r.getWest().getName());
        }

        if (r.getEast() != null) {
            out.println("A est vedi: " + r.getEast().getName());
        }

        if (r.getUp() != null) {
            out.println("Sopra vedi: " + r.getUp().getName());
        }

        if (r.getDown() != null) {
            out.println("Sotto vedi: " + r.getDown().getName());
        }
    }

    public void attackEvent(AdvObject o, AdvObject i, List<AdvObject> objects, List<AdvObject> inventory, PrintWriter out) {
        if(o.getId()==8 && i.getId()==3){
        objectEvent(o, i, objects, inventory, out);
        }
    }

    public void containerOpen(AdvObjectContainer c, List<AdvObject> o, PrintWriter out) {
        if (c.isOpenable() && !c.isOpen()) {
            c.setOpen(true);
            if (!c.getList().isEmpty()) {
                c.getList().forEach(con -> {
                    o.stream().filter(ob -> (con.equals(ob))).forEachOrdered(ob -> {
                        con.setVisible(true);
                        ob.setVisible(true);
                    });
                });
            }
            out.println("Hai aperto " + c.getName() + ".");

            if (c.getId() == 43) {
                c.setDescription("Sarà il pacchetto di sigarette dell'inserviente. Non solo quel tipo è brutto, "
                        + "puzza anche. E' chiuso.");
            }
        } else if (c.isOpen()) {
            out.println("E' già aperto");
        }
    }

    public void containerClose(AdvObjectContainer c, List<AdvObject> o) {
        c.setOpen(false);
        c.getList().forEach(con -> {
            o.stream().filter(ob -> (con.equals(ob))).forEachOrdered(ob -> {
                con.setVisible(false);
                ob.setVisible(false);
            });
        });
    }


    public void modifyRooms(PrintWriter out) {
         out.println("La ruota iniza a vibrare sempre più violentemente, fino a sfilarsi dal suo alloggio.");
                out.println("Tutto quel movimento fa perdere l'equilibrio a speedy, facendo girare insieme alla ruota.");
                out.println("La ruota inizia a rotolare giù per la rampa, fino a quando raggiunge il ponte saltandoci sopra e incastrandosi tra "
                        + "il ponte e la parete delle gabbietta.");
                out.println("Speedy: Forse ho esagerato...di nuovo!");
                getCurrentRoom().getObjects().removeAll(getCurrentRoom().getObjects());
                out.println("Hai appena costruito la tua torre verso la libertà.");
                
                
            for (Room r : getRooms()) {
                if (r.getId()==13) {
                    r.setDescription("Il piano è andato esattamente come ti aspettavi...più o meno.Ora puoi arrampicarti fin sopra e raggiungere il cancelletto! ");
                    for (Room r1 : getRooms()) {
                        if (r1.getName().equals("Cancelletto")) {
                            r.setUp(r1);
                            break;
                        }
                    }
                }
                if (r.getId()==4) {
                    r.setDescription("E' rimasto solo il perno della ruota e in lontanaza ad est vedi Speedy rimasto dentro la ruota. Speriamo stia bene");
                }
            }
    }
    
    public void talkEvent(AdvObject o, List<AdvObject> objects, Character c, List<AdvObject> inventory, PrintWriter out) {
        if (c.getId() == 1 && c.getDialogueId() < 2) {
            con.setZaino(true);
            out.println("Hai ricevuto il tuo zaino. Ora puoi raccogliere gli oggetti che troverai nelle stanze");
            c.setDialogueId(c.getDialogueId() + 1);
            out.println(c.getName() + ": " + c.getDialogue().get(c.getDialogueId()));
            c.setDialogueId(c.getDialogueId() + 1);
            o.setDescription("Ha uno sguardo preoccupato ma pieno di speranza. Questa notte non puoi fallire!");
        } else if (c.getId() == 2 && c.getDialogueId() == 0) {
            c.setDialogueId(1);
        } else if (c.getId() == 2 && c.getDialogueId() == 1) {
            out.println("Mickola: Non penso sarà così facile svegliarlo.");
        } else if (c.getId() == 3 && c.getDialogueId() == 1) {
            c.setDialogueId(3);
            for (AdvObject ob : inventory) {
                if (ob.getId() == 1) {
                    c.setDialogueId(2);
                    break;
                }
            }
            out.println(c.getName() + ": " + c.getDialogue().get(c.getDialogueId()));
            if (inventory.isEmpty()) {
                c.setDialogueId(3);
                out.println(c.getName() + ": " + c.getDialogue().get(c.getDialogueId()));
            }
            c.setDialogueId(1);
        } else if (c.getId() == 3 && c.getDialogueId() == 0) {
            c.setDialogueId(1);
        } else if (c.getId() == 6) {
            c.setDialogueId(1);
        } else if (c.getId() == 4) {
            c.setDialogueId(1);
        } else if (c.getId() == 8 && c.getDialogueId() < 2) {
            c.setDialogueId(c.getDialogueId() + 1);
        } else if (c.getId() == 9 && c.getDialogueId() < 2) {
            c.setDialogueId(c.getDialogueId() + 1);
        } else if (c.getId() == 12) {
            out.println("Mickola: non mi sembra proprio un gran chiaccherone.");
        } else if (c.getId() == 10 && !con.isSvenuto()) {
            c.setDialogueId(1);
            out.println(c.getName() + ": " + c.getDialogue().get(c.getDialogueId()));
            out.println("Mickola:Penso non ci possa essere alcuna conversazione che non termini con la mia morte.");
        } else if (c.getId() == 10 && con.isSvenuto()) {
            c.setDialogueId(2);
            out.println(c.getName() + ": " + c.getDialogue().get(c.getDialogueId()));
        }

        if (c.getId() == 7 && c.getDialogueId() == 0) {
            c.setDialogueId(1);
        }

    }


     
    
    /**
     *Fornisce, quando possibile, la stanza raggiungibile proseguendo nella 
     * direzione specificata
     * @param r stanza attuale
     * @param out oggetto PrintWriter utilizzato per scrivere su un output stream.
     */
    public void getLook(PrintWriter out) {
        out.println(" ");
        if (getCurrentRoom().getNorth() != null) {
            out.println("A nord vedi: " + getCurrentRoom().getNorth().getName());
        }

        if (getCurrentRoom().getSouth() != null) {
            out.println("A sud vedi: " + getCurrentRoom().getSouth().getName());
        }

        if (getCurrentRoom().getWest() != null) {
            out.println("A ovest vedi:" + getCurrentRoom().getWest().getName());
        }

        if (getCurrentRoom().getEast() != null) {
            out.println("A est vedi: " + getCurrentRoom().getEast().getName());
        }

        if (getCurrentRoom().getUp() != null) {
            out.println("Sopra vedi: " + getCurrentRoom().getUp().getName());
        }

        if (getCurrentRoom().getDown() != null) {
            out.println("Sotto vedi: " + getCurrentRoom().getDown().getName());
        }
    }
    
    
    /**
     *Fornisce tutti gli oggetti contenuti all'interno dell'oggetto contenitore
     * @param o oggetto selezionato
     * @param out oggetto PrintWriter utilizzato per scrivere su un output stream.
     */
  public void containerLook(AdvObjectContainer o, PrintWriter out) {
        if (!o.getList().isEmpty() && o.isOpen()) {
            out.println("Contiene: ");
            o.getList().stream().filter(ob -> (ob.isVisible())).forEachOrdered(ob -> {
                out.println(ob.getName());
            });
        } else if (o.getList().isEmpty() && o.isOpen()) {
            out.println("E' vuoto!");
        }
    }


    /**
     *Gestisce gli errori nel caso dell'utilizzo di un oggetto  non presente
     * nell'inventario o su un oggetto presente nella stanza
     * @param o oggetto selezionato
     * @param i oggetto nell'inventario
     * @param out oggetto PrintWriter utilizzato per scrivere su un output stream.
     */

    public void useErrorHandling(AdvObject o, AdvObject i, PrintWriter out) {

        if (i == null) {
            out.println("Non ce l'hai nell'invetario");
        } else if (o == null) {
            out.println("Dovresti usarlo su qualcosa");
        }
    }

    /**
     *Gestisce gli errori nel caso in cui non venga specificato l'oggetto che 
     * si vuole aprire e l'oggetto utilizzato per apire oppure nel caso in cui
     * non venga specificato solo l'oggetto che si vuole utilizzare
     * @param o oggetto selezionato
     * @param i oggetto nell'inventario c
     * @param out oggetto PrintWriter utilizzato per scrivere su un output stream.
     */
    public void openErrorHandling(AdvObject o, AdvObject i, PrintWriter out) {
        if (o == null && i == null) {
            out.println("Vorresti aprire cosa con che?");
        }
        if (i == null && !o.isOpenable()) {
            out.println("Con cosa vorresti aprirlo dato che è chiuso?");
        }
    }

}
