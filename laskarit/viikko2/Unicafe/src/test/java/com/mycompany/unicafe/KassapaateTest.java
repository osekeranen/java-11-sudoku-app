/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author okeranen
 */
public class KassapaateTest {
    
    Kassapaate kassa;
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
    }
    
    //alkutilanne
    
    @Test
    public void luodunKassapaatteenRahamaaraOikea() {
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void aluksiEiMyytyjaLounaita() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullinenMaksuKateisellaRiittava() {
        assertEquals(260, kassa.syoEdullisesti(500));
        assertEquals(100240, kassa.kassassaRahaa());
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullinenMaksuKateisellaEiRiittava() {
        assertEquals(100, kassa.syoEdullisesti(100));
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukasMaksuKateisellaRiittava() {
        assertEquals(100, kassa.syoMaukkaasti(500));
        assertEquals(100400, kassa.kassassaRahaa());
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maukasMaksuKateisellaEiRiittava() {
        assertEquals(100, kassa.syoMaukkaasti(100));
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullinenMaksuKortiltaRiittava() {
        Maksukortti kortti = new Maksukortti(500);
        assertEquals(true, kassa.syoEdullisesti(kortti));
        assertEquals(260, kortti.saldo());
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullinenMaksuKortiltaEiRiittava() {
        Maksukortti kortti = new Maksukortti(100);
        assertEquals(false, kassa.syoEdullisesti(kortti));
        assertEquals(100, kortti.saldo());
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukasMaksuKortiltaRiittava() {
        Maksukortti kortti = new Maksukortti(500);
        assertEquals(true, kassa.syoMaukkaasti(kortti));
        assertEquals(100, kortti.saldo());
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maukasMaksuKortiltaEiRiittava() {
        Maksukortti kortti = new Maksukortti(100);
        assertEquals(false, kassa.syoMaukkaasti(kortti));
        assertEquals(100, kortti.saldo());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortinLatausOnnistuu() {
        Maksukortti kortti = new Maksukortti(100);
        kassa.lataaRahaaKortille(kortti, 400);
        assertEquals(500, kortti.saldo());
        assertEquals(100400, kassa.kassassaRahaa());
    }
    
    @Test
    public void kortinNegatiivinenLatausEpaonnistuu() {
        Maksukortti kortti = new Maksukortti(500);
        kassa.lataaRahaaKortille(kortti, -400);
        assertEquals(500, kortti.saldo());
        assertEquals(100000, kassa.kassassaRahaa());
    }
}
