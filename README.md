# SudokuApp

SudokuApp on suunniteltu perinteisen [sudoku](https://en.wikipedia.org/wiki/Sudoku)-pelin pelaamista varten. Sovellus luo ristikot käyttäen rekursiivista backtracking-algoritmiä. SudokuApp on toteutettu käyttäen Java 11:a ja JavaFX:aa.

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

[kayttöohje](https://github.com/osekeranen/ot-harjoitustyo/blob/master/dokumentointi/manual.md)  
[vaatimusmäärittely](https://github.com/osekeranen/ot-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittely.md)  
[työaikakirjanpito](https://github.com/osekeranen/ot-harjoitustyo/blob/master/dokumentointi/tyoaikakirjanpito.md)  
[arkkitehtuuri](https://github.com/osekeranen/ot-harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.md)
