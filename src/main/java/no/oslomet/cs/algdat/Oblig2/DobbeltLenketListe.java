package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Objects;
import java.util.*;


//Oppgave 1 (Konstruktør DobbeltLenketListe + metode 'int antall' og 'boolean tom'
public class DobbeltLenketListe<T> implements Liste<T> {
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

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
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
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
        Object.requireNonNull (a, "Tabellen a er null");
        hode = hale = new Node<>(null); // hode og hale er 0 hvis det er 0 indekser
        for (T verdi : a){
            hale = hale.neste = new Node<>(verdi, hale, null);
            antall++;
        }

        if (antall = 0) hode = hale = null;

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
        Objects.requireNonNull(verdi, "vi vil ikke ha nullverdier"); //stoppes null-verdier?

        // noe burde være her

        antall++; //antall økt
        endringer++; //endringer økt
        return true; //rett returverdi
    }

    @Override // oppg 5 --> inspo fra Programkode 3.2.3 b) og 3.3.2 g)-> Delkap 3.2 - En tabellbasert liste
    public void leggInn(int indeks, T verdi)
    {
        Objects.requireNonNull(verdi, "ingen null-verdier tillatt"); // nullverdier stopees
        indeksKontroll(indeks, true); //indeks sjekkes

        /*
        Blir det korrekt hvis listen fra før er tom?
        Blir pekerne (forrige og neste) korrekte i alle noder hvis ny verdi legges først?
        Blir pekerne (forrige og neste) korrekte i alle noder hvis ny verdi legges bakerst?
        Blir pekerne (forrige og neste) korrekte i alle noder hvis ny verdi legges mellom to
        verdier?
        */

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
    public T oppdater(int indeks, T nyVerdi)
    {
        Objects.requireNonNull(nyVerdi, "Ugyldig verdi");

        Node <T> p = finnNode(indeks);
        T gammelVerdi = p.verdi;

        p.verdi = nyVerdi;

        endringer++;
        return gammelVerdi;
    }

    private void indeksKontroll(int indeks) { //WARDAH LET ETTER DENNE
        throw new UnsupportedOperationException();
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

    @Override // Oppg 2 --> Programkode 1.3.14 d) + Programkode 6.1.4 f) (Delkap 6.1 - Hashing)
    public String toString()
    {
        StringJoiner S = new StringJoiner(", ", "[", "]");
        for (Node<T> p = hode; p != null; p = p.neste)
        S.add(p.verdi.toString());
        return S.toString();
    }


    public String omvendtString() {
        StringJoiner S = new StringJoiner(", ", "[", "]");
        for (Node<T> p = hale; p != null; p = p.forrige)
        S.add(p.verdi.toString());
        return S.toString();
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

        //Oppg 9 -> kilde: programkode 3.2.5 e)
        public void remove() {
            if (iteratorendringer != endringer) throw
                    new ConcurrentModificationException("");

            if (!removeOK) throw
                    new IllegalStateException("");

            removeOK = false;
            //burde implementere fjern her
            iteratorendringer++;
        }

        @Override //FERDIGKODET, IKKE ENDRE
        public boolean hasNext() {
            return denne != null;
        }

        //Oppg 8 a)
        public T next() {
            if (!hasNext()) {
                throw new UnsupportedOperationException("Ingen flere verdier i listen!");
            }
            else if (endringer != iteratorendringer) {
                throw new ConcurrentModificationException("Listen er endret!");
            }

            T tmp = denne.verdi;
            denne = denne.neste;

            fjernOK = true;

            return tmp;        }

        /* programkode 3.2.5 e) -> slutten av delkap 3.1 - grensesnittet liste
        public void remove()
        {
            if (iteratorendringer != endringer) throw new
                ConcurrentModificationException("Listen er endret!");
            if (!fjernOK) throw
                new IllegalStateException("Ulovlig tilstand!");
            fjernOK = false; // remove() kan ikke kalles på nytt

            ...
            iteratorendringer++;
        */


    }

    // class DobbeltLenketListeIterator

    //oppg 10
    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe

//noenoenoe