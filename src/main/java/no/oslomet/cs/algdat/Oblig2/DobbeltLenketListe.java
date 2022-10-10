package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////

import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Objects;


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

    //Hjelpemetode
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

    public DobbeltLenketListe() {
        hode = hale = null;
        antall = 0;
        endringer = 0;
    }

    /*public EnkeltLenketListe() // standardkonstruktør
    {
        hode = hale = null; // hode og hale til null
        antall = 0; // ingen verdier - listen er tom
        endringer = 0; // ingen endringer når vi starter
    } -> Programkode 3.3.4 g) --> Delkapittel 3.3 - En lenket liste
    */

    public DobbeltLenketListe(T[] a) { //oppg 1
        throw new UnsupportedOperationException();
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

//Oppgave 1:
    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        return antall == 0;
    }

    @Override
    public boolean leggInn(T verdi) { //oppg 7 og 2
        throw new UnsupportedOperationException();
    }

    @Override // inspo fra Programkode 3.2.3 b) og 3.3.2 g)-> Delkap 3.2 - En tabellbasert liste
    public void leggInn(int indeks, T verdi) {
        Objects.requireNonNull(verdi, "vi vil ikke ha nullverdier :)"); //"sjekkes null-verdier?"
        indeksKontroll(indeks, true); //"sjekkes indeksen?"

        antall++; //antall økt
        endringer++; //endringer økt
    }

    @Override
    public boolean inneholder(T verdi) {
        return indeksTil(verdi) != -1;
        //  throw new NotImplementedException();
    }

//oppgave 3a)
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

    //oppgave something:
    @Override
    public T oppdater(int indeks, T nyVerdi)
    {
        indeksKontroll(indeks);
        Objects.requireNonNull(nyVerdi, "Ugyldig verdi");

        Node <T> p = finnNode(indeks);
        T gammelVerdi = p.verdi;

        p.verdi = nyVerdi;

        endringer++;
        return gammelVerdi;
    }



    // Oppgave 7/////////////////////////////////////////////////////////////
    @Override
    public boolean fjern(T verdi) {
        //if (verdi == 0){
            //    return false;
      //  }
            //       }
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        indeksKontroll(indeks, false);
        throw new UnsupportedOperationException();
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

    @Override
    public String toString() { //oppg 7, 5
        throw new UnsupportedOperationException();
    }

    public String omvendtString() { //oppg 7, 5
        throw new UnsupportedOperationException();
    }

    // Oppgave 8d)/////////////////////////////////////////////////////////////
    @Override
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() { //FERDIGKODE IKKE ENDRE
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            throw new UnsupportedOperationException();
        }
        public void remove()
        {
            if (!removeOK) throw
                    new IllegalStateException("Ulovlig tilstand!");
            removeOK = false;

        @Override //FERDIGKODET, IKKE ENDRE
        public boolean hasNext() {
            return denne != null;
        }

        @Override //Oppg 8 a)
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

        @Override //Oppg 9 -> kilde: programkode 3.2.5 e)
        public void remove() {
            if (iteratorendringer != endringer) throw
                    new ConcurrentModificationException("");

            if (!fjernOK) throw
                    new IllegalStateException("");

            fjernOK = false;
            //burde implementere fjern her
            iteratorendringer++;
        }
    }

    // class DobbeltLenketListeIterator

    //oppg 10
    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe
