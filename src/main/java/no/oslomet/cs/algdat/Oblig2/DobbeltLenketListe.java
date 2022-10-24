package no.oslomet.cs.algdat.Oblig2;


////////////////// class
////////////////////////
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Objects;
import java.util.*;


//Oppgave 1 (Konstruktør DobbeltLenketListe + metode 'int antall' og 'boolean tom'
public class DobbeltLenketListe<T> implements Liste<T> {
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige;
        private Node neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node hode;          // peker til den første i listen
    private Node hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen


    //privat hjelpemetode for oppg 3
    private Node<T> finnNode(int indeks) {
        Node<T> returnNode;

        if (indeks < antall / 2) {
            returnNode = hode;
            for (int i = 0; i < indeks; i++) returnNode = returnNode.neste;
        } else {
            returnNode = hale;
            for (int i = antall - 1; i > indeks; i--) returnNode = returnNode.forrige;
        }
        return returnNode;
    }

    // hjelpemetode
    private void indeksKontroll(int indeks) {
        if (indeks < 0) {
            throw new IndexOutOfBoundsException("Indeks " + indeks + " er negativ!");
        } else if (indeks >= antall) {
            throw new IndexOutOfBoundsException("Indeks " + indeks + " >= antall(" + antall + ") noder!");
        }
    }
    //Hjelpemetode
    /*public EnkeltLenketListe() // standardkonstruktør
    {
        hode = hale = null; // hode og hale til null
        antall = 0; // ingen verdier - listen er tom
        endringer = 0; // ingen endringer når vi starter
    } -> Programkode 3.3.4 g) --> Delkapittel 3.3 - En lenket liste
    */

    public DobbeltLenketListe() {
        hode = hale = null;
        antall = 0;
        endringer = 0;
    }

    public DobbeltLenketListe(T[] a) { //oppg 1
        this();
        Objects.requireNonNull (a, "Tabellen a er null");
        hode = hale = new Node<>(null); // hode og hale er 0 hvis det er 0 indekser
        for (T verdi : a){
            hale = hale.neste = new Node<>(verdi, hale, null);
            antall++;
        }

        if (antall == 0) hode = hale = null;

        else (hode = hode.neste).forrige = null;
    }

    // hjelpemetode:
    private void fratilKontroll(int tabLengde, int fra, int til) {
        if (fra < 0 || til > tabLengde) {
            throw new IndexOutOfBoundsException();
        }
        if (fra > til) {
            throw new IllegalArgumentException();
        }
    }

    //oppgave 3b:
    public Liste<T> subliste(int fra, int til)
    {
        fratilKontroll(antall, fra, til);

        Liste<T> liste = new DobbeltLenketListe<>();  // ny liste
        Node<T> p = finnNode(fra);

        for (int i = fra; i < til; i++)   // henter verdiene i (fra i til 1++) / 1++ er en positiv opperatør som teller med i
        {
            liste.leggInn(p.verdi);
            p = p.neste;
        }

        return liste;
    }

    @Override //oppg 1
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        return antall == 0;
    }

    @Override //oppg 2 --> Programkode 3.3.2 f) , Delkap 3.3 - lenket liste
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Vi vil ikke ha nullverdier"); //Nullverider ikke tillat pga oppgaven

        Node<T>p= new Node<>(verdi, hale, null); //listen på forhånd er tom
        hale = tom() ? (hode = p) : (hale.neste = p); //Hale peker endres på ved innleggingen.
        antall++; //antall økt
        endringer++; //endringer økt
        return true; //rett returverdi
    }

    @Override // oppg 5 --> inspo fra Programkode 3.3.2 g)-> Delkap 3.2 - En tabellbasert liste
    public void leggInn(int indeks, T verdi)
    {
        Objects.requireNonNull(verdi, "ingen null-verdier tillatt"); // nullverdier stoppes
        indeksKontroll(indeks, true); //indeks sjekkes

        if (tom()){ //la til en ny if tom for å sjekke om listen er tom
            hode = hale = new Node<T>(verdi, null, null);
        }
        else if (indeks == 0) {
            hode = new Node<T>(verdi, null, hode); //får en verdi foran alle
            hode.neste.forrige = hode;
        }
        else if (indeks == antall) {
            hale = new Node<T>(verdi, hale, null); // får en verdi helt bakerst (hode foran, hale bak)
            hale.forrige.neste = hale;
        }
        else {
            Node<T> p = finnNode(indeks); //trenger finnNode for å returnere eksakt verdi
            p.forrige = p.forrige.neste = new Node <>(verdi, p.forrige, p);
        }

        antall++; // antall økt
        endringer++; //endringer økt
    }

    @Override
    public boolean inneholder(T verdi) { //oppg 4
        return indeksTil(verdi) != -1;
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);
        return finnNode(indeks).verdi;
    }

    //oppgave 4:
    @Override
    public int indeksTil(T verdi)
    {
        if (verdi == null) return -1;

        Node <T> p = hode;

        for (int i = 0; i < antall; i++, p = p.neste)
        {
            if (p.verdi.equals(verdi)) return i;
        }

        return -1;
    }

    @Override
    public T oppdater(int indeks, T nyverdi)
    {
        Objects.requireNonNull(nyverdi, "Ugyldig verdi");
        indeksKontroll(indeks, false);

        Node<T> p = finnNode(indeks);

        T gammelverdi = p.verdi;
        p.verdi = nyverdi;

        endringer++;

        return gammelverdi;
    }

    // Oppgave 6/////////////////////////////////////////////////////////////
    @Override
    public boolean fjern(T verdi) {
        if (verdi == null) {
            return false;
        }
        Node<T> gjeldendeNode = hode;
        //Fjerner første node
        if (verdi.equals(gjeldendeNode.verdi)) {
            if (gjeldendeNode.neste != null) {
                hode = gjeldendeNode.neste;
                hode.forrige = null;
            } else {
                hode = null;
                hale = null;
            }
            antall--;
            endringer++;
            return true;
        }
        //Fjerner siste node
        gjeldendeNode = hale;
        if (verdi.equals(gjeldendeNode.verdi)) {
            hale = gjeldendeNode.forrige;
            hale.neste = null;
            antall--;
            endringer++;
            return true;
        }
        //Fjerner node i mellom
        gjeldendeNode = hode.neste;
        for (; gjeldendeNode != null; gjeldendeNode = gjeldendeNode.neste) {
            if (verdi.equals(gjeldendeNode.verdi)) {
                gjeldendeNode.forrige.neste = gjeldendeNode.neste;
                gjeldendeNode.neste.forrige = gjeldendeNode.forrige;
                antall--;
                endringer++;
                return true;
            }
        }
        return false;
    }

    @Override
    public T fjern(int indeks) {
        indeksKontroll(indeks);
        Node<T> temp;

        if (indeks == 0) {
            temp = hode;
            hode = hode.neste;
            hode.forrige = null;
        } else if (indeks == antall - 1) {
            temp = hale;
            hale = hale.forrige;
            hale.neste = null;
        } else {
            Node<T> p = finnNode(indeks - 1);
            temp = p.neste;
            p.neste = p.neste.neste;
            p.neste.forrige = p;
        }

        antall--;
        endringer++;
        return temp.verdi;
    }

    @Override //Inspirasjon fra Programkode 3.3.3 c) -> Delkapittel 3.3 - En lenket liste
    public void nullstill() { //oppg 7, velger metode 1
        for(Node<T> p = hode; //"start i hode"
            p != null; p = p.neste) {
            p.verdi = null;
            p.forrige = p.neste = null;
        } //nuller alt
        hode = hale = null; //"hode og hale til null"
        antall = 0; //"antall til null"
        endringer++; //"endringer økes"
    }

    @Override // Oppg 2 -->

    public String toString() {
        StringBuilder s = new StringBuilder(); s.append("[");
        if (!tom()){
            s.append(hode.verdi);
            for(Node<T> p = hode.neste; p !=null; p = p.neste) {
                s.append(",").append(" ").append(p.verdi);
            }
        }
        s.append("]");
        return s.toString();
    }

    public String omvendtString() {
        StringBuilder s = new StringBuilder();s.append("[");
        if (!tom()){
            s.append(hale.verdi);
            for (Node<T> p = hale.forrige; p != null; p = p.forrige){
                s.append(",").append(" ").append(p.verdi);
            }
        }
        s.append("]");
        return s.toString();
    }

    // Oppgave 8b)/////////////////////////////////////////////////////////////
    @Override
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator();
    }

    // Oppgave 8d)/////////////////////////////////////////////////////////////
    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks, false);
        return new DobbeltLenketListeIterator(indeks);
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;
        private boolean removeOK;

        private DobbeltLenketListeIterator() { //FERDIGKODE IKKE ENDRE
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        // Oppgave 8c)/////////////////////////////////////////////////////////////
        private DobbeltLenketListeIterator(int indeks) {
            denne = finnNode(indeks);  // noden med oppgitt indeks;
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;

        }

        //Oppg 9 -> inspirasjon: programkode 3.2.5 e)
        public void remove() {
            if (iteratorendringer != endringer) throw
                    new ConcurrentModificationException("endringer og iteratorendringer er forskjellige");

            if (!fjernOK) throw
                    new IllegalStateException("Ulovlig tilstand");

            fjernOK = false;
            Node<T> p = (denne == null ? hale : denne.forrige);

            if (p == hode)
            {
                if (antall == 1) { //hvis den som skal fjernes er eneste verdi, nulles hode og hale
                    hode = hale = null;
                }
                else if (denne.forrige == hode){ //hvis første skal fjernes, skal hode oppdateres
                    hode = hode.neste;
                    hode.forrige = null;
                }
            }
            else if (denne == null){ //hvis siste skal fjernes, skal hale oppdateres
                hale = hale.forrige;
                hale.neste = null;
            }
            else{ //node inni listen --> oppdatere alle pekere
                p.forrige.neste = p.neste;
                p.neste.forrige = p.forrige;
            }

            antall--;
            endringer++;
            iteratorendringer++;
        }

        @Override //FERDIGKODET, IKKE ENDRE
        public boolean hasNext() {
            return denne != null;
        }

        //Oppg 8 a)
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Ingen flere verdier i listen!");
            }
            else if (endringer != iteratorendringer) {
                throw new ConcurrentModificationException("Listen er endret!");
            }

            T tmp = denne.verdi;
            denne = denne.neste;

            fjernOK = true;

            return tmp;        }
    }

public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {

        for(int i = 1; i < liste.antall(); i++) {

            int verdi = c.compare(liste.hent(i-1), liste.hent(i));

            while(verdi > 0 && i >= 1) {

                verdi = c.compare(liste.hent(i-1), liste.hent(i));
                liste.oppdater(i, liste.oppdater(i - 1, liste.hent(i)));
                i--;
            }
        }
    }
} // class DobbeltLenketListe
