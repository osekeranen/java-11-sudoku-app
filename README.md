# SudokuApp

SudokuApp on suunniteltu perinteisen [sudoku](https://en.wikipedia.org/wiki/Sudoku)-pelin pelaamista varten. Sovellus luo ristikot käyttäen rekursiivista backtracking-algoritmiä. SudokuApp on toteutettu käyttäen Java 11:a ja JavaFX:aa. Sovellus on tehty osana Helsingin yliopiston kurssia ohjelmistotekniikka.

## Dokumentaatio

[Kayttöohje](https://github.com/osekeranen/sudoku/blob/master/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/osekeranen/sudoku/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/osekeranen/sudoku/blob/master/dokumentaatio/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/osekeranen/sudoku/blob/master/dokumentaatio/testaus.md)

[Tuntikirjanpito](https://github.com/osekeranen/sudoku/blob/master/dokumentaatio/tuntikirjanpito.md)

## Releaset

[v1.1](https://github.com/osekeranen/sudoku/releases/tag/v1.1) *viimeisin*

[v1.0](https://github.com/osekeranen/sudoku/releases/tag/v1.0)

## Komentorivikomennot

### Käynnistäminen

Sovellus käynnistetään komennolla

```
mvn compile exec:java -Dexec.mainClass=sudokuapp.Main
```

### Jarin generointi

Suoritettava JAR luodaan komennolla

```
mvn package
```

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto target/site/jacoco/index.html

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

### Checkstyle

Tyyli tarkistetaan komennolla

```
mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto target/site/checkstyle.html
