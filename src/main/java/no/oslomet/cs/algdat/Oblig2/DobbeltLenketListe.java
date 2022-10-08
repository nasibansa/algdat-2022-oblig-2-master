package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


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
        throw new UnsupportedOperationException();
    }

    public Liste<T> subliste(int fra, int til) {
        throw new UnsupportedOperationException();
    }

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

    @Override
    public void leggInn(int indeks, T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) { //oppg 4
        throw new UnsupportedOperationException();
    }

    @Override
    public T hent(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indeksTil(T verdi) { //oppg 7
        throw new UnsupportedOperationException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) { //oppg 3
        throw new UnsupportedOperationException();
    }

    // Oppgave 6/////////////////////////////////////////////////////////////
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
