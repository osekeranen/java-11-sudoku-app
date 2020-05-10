# Arkkitehtuurikuvaus

## Rakenne

Koodin pakkausrakenne on seuraava:

![Sudokusovelluksen pakkauskaavio](images/sudoku-pakkauskaavio.png)  

Pakkaus *sudokuapp.ui* sisältää JavaFX:llä toteutetun graafisen käyttöliittymän, *sudokuapp.logic* sovelluslogiikan ja *sudokuapp.dao* tietojen pysyväistallenuksen.

## Käyttöliittymä

Sovellus sisältää viisi näkymää:

* päävalikko  
* ennätyspisteet  
* vaikeusasteen valinta  
* peli  
* pelin pisteiden tallennus  

Jokainen näistä on toteutettu omana Scene-olionaan.

## Sovelluslogiikka

Sovelluslogiikka on toteutettuna pakkauksen *sudokuapp.logic* luokilla.

## Tietojen pysyväistallennus

Pakkauksen *sudokuapp.dao* luokka FileHiscoreDao vastaa tietojen pysyväistallennuksesta.
