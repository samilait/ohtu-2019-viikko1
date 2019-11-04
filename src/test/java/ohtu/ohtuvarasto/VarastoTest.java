package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto varasto2;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varasto2 = new Varasto(10, 8);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriEiNegatiivistaTilavuutta() {
        Varasto varasto = new Varasto(-1);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }


    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaVarastolla2OikeaTilavuus() {
        assertEquals(10, varasto2.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktori2EiNegatiivistaTilavuutta() {
        Varasto varasto = new Varasto(-1, 8);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void eiNegatiivistaAlkuSaldoa() {
        Varasto varasto = new Varasto(10, -1);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenLisaysEiMuutaSaldoa() {
        double saldo = varasto.getSaldo();
        varasto.lisaaVarastoon(-8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(saldo, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ylitysEiMuutaSaldoa() {
        double mahtuu = varasto.paljonkoMahtuu();
        double tilavuus = varasto.getTilavuus();
        
        varasto.lisaaVarastoon(mahtuu + 1.0);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(tilavuus, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttaminenPalauttaaNollan() {
        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(0, varasto.otaVarastosta(-2), vertailuTarkkuus);
    }

    @Test
    public void otetaanEnemmanKuinSaldossa() {
        varasto.lisaaVarastoon(8);
        double saldo = varasto.getSaldo();
        assertEquals(saldo, varasto.otaVarastosta(10), vertailuTarkkuus);
    }

    @Test
    public void merkkijono() {
        varasto.lisaaVarastoon(8);
        double tilaa = varasto.getTilavuus() - varasto.getSaldo();
        
        String teksti = "saldo = 8.0, vielä tilaa " + tilaa;
        
        assertEquals(teksti, varasto.toString());
    }

}