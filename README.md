# SudokuApp

SudokuApp on suunniteltu perinteisen sudoku-pelin pelaamista varten. Sovellus on toteutettu käyttäen Java 11:a ja JavaFX:aa.
## Sudokun luomisesta

SudokuApp luo tällä hetkellä sudokunsa luomalla ensin ylimmän rivin, jossa on numerot 1-9 satunnaisessa järjestyksessä. Tämän jälkeen se luo uuden rivin joka käytännössä on sama rivi kuin ylempi rivi, mutta numeroita on siirretty joko yksi tai kolme paikkaa oikealle. Lopuksi rivejä ja sarakkeita sekoitetaan. Etuna tässä on että algoritmi oli helppo luoda ja sovellus pystyy luomaan sudokuja nopeasti. Tämä luo kuitenkin hieman ennalta arvattavia sudokuja, joten tavoitteenani on luoda rekursiivinen sudokunluontialgoritmi. Kuitenkin kyseinen algoritmi ei varsinaisesti kuulu kurssin aihepiiriin, joten algoritmin kehittely jäänee kesälle tai aivan loppukurssille, mikäli aikaa riittää.

## Releaset

[v1.1](https://github.com/osekeranen/ot-harjoitustyo/releases/tag/v1.1) *viimeisin*  
[v1.0](https://github.com/osekeranen/ot-harjoitustyo/releases/tag/v1.0)  

## Komennot

Java-projektin kansioon pääsee komennolla `cd SudokuApp`

Käynnistäminen: `mvn compile exec:java -Dexec.mainClass=sudokuapp.Main`  
jar-tiedosto: `mvn package`  
Testit: `mvn test`  
Testikattavuus: `mvn test jacoco:report`  
Checkstyletarkistus: `mvn jxr:jxr checkstyle:checkstyle`  
Javadoc: `mvn javadoc:javadoc`  
HUOM! Javadocin luominen vaatii että JAVA_HOME on asetettu.  

## Dokumentaatio

[kayttöohje](https://github.com/osekeranen/ot-harjoitustyo/blob/master/dokumentointi/kayttoohje.md)  
[vaatimusmäärittely](https://github.com/osekeranen/ot-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittely.md)  
[työaikakirjanpito](https://github.com/osekeranen/ot-harjoitustyo/blob/master/dokumentointi/tyoaikakirjanpito.md)  
[arkkitehtuuri](https://github.com/osekeranen/ot-harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.md)
