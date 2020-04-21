# SudokuApp

Sovellus on suunniteltu perinteisen sudoku-pelin pelaamista varten.

## Sudokun luomisesta

SudokuApp luo tällä hetkellä sudokunsa luomalla ensin ylimmän rivin, jossa on numerot 1-9 satunnaisessa järjestyksessä. Tämän jälkeen se luo uuden rivin joka käytännössä on sama rivi kuin ylempi rivi, mutta numeroita on siirretty joko yksi tai kolme paikkaa oikealle. Lopuksi rivejä ja sarakkeita sekoitetaan. Etuna tässä on että algoritmi oli helppo luoda ja sovellus pystyy luomaan sudokuja nopeasti. Tämä luo hieman ennalta arvattavia sudokuja, joten tavoitteenani on luoda rekursiivinen sudokunluontialgoritmi. Kuitenkin kyseinen algoritmi ei varsinaisesti kuulu kurssin aihepiiriin, joten algoritmin kehittely jäänee kesälle tai aivan loppukurssille, mikäli aikaa riittää.

## Komennot

Käynnistäminen: `mvn compile exec:java -Dexec.mainClass=sudokuapp.Main`  
Testit: `mvn test`  
Checkstyletarkistus: `mvn jxr:jxr checkstyle:checkstyle`  
Testikattavuus: `mvn test jacoco:report`  

## Dokumentaatio

[vaatimusmäärittely](https://github.com/osekeranen/ot-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittely.md)  
[työaikakirjanpito](https://github.com/osekeranen/ot-harjoitustyo/blob/master/dokumentointi/tyoaikakirjanpito.md)  
[luokkakaavio](https://github.com/osekeranen/ot-harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.md)
