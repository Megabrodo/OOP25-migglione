# OOP25-migglione


E-mail dei componenti:   

    lorenzo.zanoni3@studio.unibo.it 

    alessandro.zani3@studio.unibo.it

    anton.abramov@studio.unibo.it

    thomas.giovannini@studio.unibo.it

Obiettivo : Realizzazione di una versione digitale del gioco di carte inventato "Migglione". 

Descrizione: Il gruppo si pone come obiettivo quello di realizzare un'applicazione che prende ispirazione da un gioco di carte inventato, di nome appunto Migglione. Lo scopo del gioco è totalizzare più punti in totale utilizzando carte provenienti dal medesimo mazzo, similmente a giochi come Briscola. Le carte presenti nel mazzo possiedono vari attributi, come forza difesa ecc... Il giocatore di turno, ad inizio del turno stesso, sceglie uno degli attributi, a quel punto tutti i giocatori giocano una delle loro carte dalla mano, nella categoria scelta. Il vincitore del turno è deciso comparando i valori delle carte giocate, colui che detiene la carta con il valore maggiore vince il round e le carte giocate diventano i suoi punti. La partita finisce quando tutte le carte sono state giocate e non sono più presenti carte nel mazzo.

Funzionalità minimali obbligatorie (realizzabili in circa il 70-80% del tempo a disposizione): 

    Creazione del database di carte e attribuzione dei giusti valori

    Metodo di pescata dal mazzo

    Valutazione del punteggio dinamica

    Gestione di una IA semplice

    GUI interattiva e user friendly

Funzionalità opzionali (a completamento del 100% del tempo a disposizione): 

    Modalità extra con reimissione delle carte, la partita finisce quando si raggiunge un limite di punti deciso dal giocatore
    Tutorial opzionale in game che memorizza il suo completamento

    Aggiunta di carte "speciali", cioè che modificano lo stato di altre o presentano effetti particolari

"Challenge" principali: 

    La gestione del turno, quindi: scelta attributo della carta, fase di gioco delle carte di ogni giocatore, comparazione dei valori, decretazione del vincitore del round 

    Valutazione del vincitore finale e metodo di conteggio dei punti
    Creazione di un set di carte "bilanciato" e una GUI interattiva

    Metodo di pescata di una carta e successiva eliminazione di questa dalle carte ancora giocabili e pescabili

    Creazione di un mosquito-player, ossia una IA in grado di giocare contro il vero player in un modo semplice e scontato
    Aggiunta di un semplice tutorial opzionale che memorizza se è già stato fatto oppure no

    Uso di un file per contenere una classifica di tutti i giocatori

