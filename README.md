# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* Nasiba Sara Ayoubi, S364515, s364515@oslomet.no
* Wardah Fatima Naqvi, s348559, s348559@oslomet.no
* Sara Ul Hassan, s364533, s364533@oslomet.no
* Danial Rafiq Dutt, s341501, s341501@oslomet.no

# Arbeidsfordeling

I oppgaven har vi hatt følgende arbeidsfordeling:
* Nasiba (S364515) har hatt hovedansvar for oppgave: 5 og 7
* Wardah (S348559) har hatt hovedansvar for oppgave: 3 og 4
* Sara (364533) har hatt hovedansvar for oppgave: 1 og 2
* Danial (341501) har hatt hovedansvar for oppgave: 6 og 8
* Vi har i fellesskap løst oppgave: 9 og 10 
    
# Oppgavebeskrivelse

Oppgave 1: I denne oppgaven lagde vi metodene int antall() og boolean tom(). Disse returnerer da både antall, 
og antall lik 0 om det er en tom liste. Etter det satte vi inn konstruktøren. Det ble en del feilmeldingen pga skrivefeil som var lett å rette opp i. 
Konstruktøren kunne ikke testes ferdig vi ikke den ikke til å godkjenne alle tester før oppgave 2 var fullført. Ellers testa og var godkjent. 
La inn riktig metode for som er gitt i oppgaveteksen for null-verdier. Setter inn noder med første som hode og andre som hale.
Slik er rekkefølgen riktig. Passer på at hode.forrige og hale.neste er 0, siden er de første og siste nodene. 

Oppgave 2: Lager metodene tostring og omvendt string. Brukte første metodene i kompendiet men fikk opp flere feilmeldinger og ikke fikk godkjent, 
blant annet at den ikke retunerte noen nullverdi. Dermed brukte vi append funksjonen, koda og testa på andre måter og fikk metoden godkjent. 
Bruker stringbuilder som gitt i oppgaven, endra fra stringjoiner i første commit. I omvent stringen setter vi inn nodene slik at de leses bakveis, 
slik at verdiene leses fra hale til hode og forrige verdi leses. Dermed kan vi se om forrige pekerne er satt riktig. I metoden legginn(T verdi) brukte vi mye av
kildekoden gitt i kompendiet tatt fra delkapittel 3.3, programkode 3.3.2 f. Forklart i java filen hva de ulike funksjonene gjør. 

Oppgave 3: Lagde en privat hjelpemetode finnNode. Denne ble inspirert fra kompendiet, 3.3.3, men har implementert en if-sjekk for å forsikre at letingen skal gå mot høyre om antallet er mindre enn 2 og ellers mot venstre. Har så lagd en ny public metode, T hent, denne har tatt utgangspunkt i finnNode og forteller oss indeks verdien til noden. Den utfører også en indekskontroll. 
Har også lagd en metode som oppdaterer indeks med ny verdi, og godtar ikke null verdier. 
I oppgave 3b har jeg lagd metoden subliste. Denne metoden skal i hovedsak returne en ny liste som baserer seg på dobbeltlenketliste. Jeg ha begrenset denne listen med intervallet fra, til. Gjort dette ved hjelp av en for løkke. Har også lagd en hjelpemetode private void fratilKontrolll (denne er også inspirert fra kompendiet delkapittel 1.2. 

Oppgave 4: Wardah

Oppgave 5: Her har vi en dobbeltlenketliste (ikke enkel), og da går vi "opp et hakk". Derfor tegnet jeg først for meg selv, og fikk en bedre forståelse av hva jeg skulle gjøre. Jeg har skrevet kommentarer på hva jeg har gjort i koden, men enkelt forklart så fulgte jeg bare sjekklistene og baserte meg på de der. 

Oppgave 6: For å løse denne oppgaven la vi til en indekskontroll i T fjern(int indeks) metoden. Deretter bruker vi en if, else if og else statement som fjerner den første noden, så den siste noden og tilslutt den mellomste. I den andre metoden starter vi med å sjekke om "verdi" er lik null. På sammen måte som i den andre metoden starter vi med å fjerne den første noden. Deretter den siste noden og til slutt noden i mellom.

Oppgave 7: I oppgave 7 valgte jeg å bruke metode 1, selv om jeg har hørt metode 2 er raskere. Jeg syntes den første metoden var enklere å utføre, og startet med å jobbe i hode også gå videre mot hale. Jeg brukte pekere (forkortet til bare "p"), 

Oppgave 8: For å løse oppgave 8a har vi brukt if state statement for å sjekke at !hasNext har noen verdier i tabellen.
Deretter en else if statement for å sjekke om iteratorendringer er lik endringer. I oppgave 8b
returnerer vi DobbeltLenketListeIterator. I oppgave  8c endret vi pekere "denne" til finnNode(indeks).
For å løse oppgave 8d la vi inn en sjekk for å se om indeksen er lovlig. Deretterreturnerer vi DobbeltLenketListeIterator.

Oppgave 9: Løser oppgave 9 ved å lage en remove funksjon. Følger kravene gitt i oppgaven med ulovlig tilstand og forskjeller i endringer og iteratorendringer. 
Skrevet i koden forklart med det vi har skrevet. Jeg lagde basically bare en if-sjekk /statement, og passet på at den fylte alle kravene fra oppgaven. 

Oppgave 10: FELLES
