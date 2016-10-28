/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package template;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mikkoeim
 
public class TemplateTest {
    
    public TemplateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetTeksti() {
        Template template = new Template("Hello Bulevardi");
        assertEquals("Hello Bulevardi", template.getTeksti());
    }
    
    @Test
    public void yksiMuuttuja() {
        Template template = new Template("Terve, ${nimi}");
        template.korvaa("nimi", "Iivari");
        assertEquals("Terve, Iivari", template.evaluoi());
    }
    
    @Test
    public void uusiArvo() {
        Template template = new Template("Terve, ${nimi}");
        template.korvaa("nimi", "Olga");
        assertEquals("Terve, Olga", template.evaluoi());
    } 
    
    @Test
    public void uusiTemplate() {
        Template template = new Template("Soon nääs moro, ${nimi}");
        template.korvaa("nimi", "Olga");
        assertEquals("Soon nääs moro, Olga", template.evaluoi());
    }
    
    @Test
    public void montaMuuttujaa() {
        Template template = new Template("${yy}, ${kaa}, ${koo}");
        template.korvaa("yy", "1");
        template.korvaa("kaa", "2");
        template.korvaa("koo", "3");
        assertEquals("1, 2, 3", template.evaluoi());
    }
    
    @Test
    public void tuntematonMuuttuja() {
        Template template = new Template("Terve, ${nimi} ${sukunimi}");
        template.korvaa("nimi", "Aukusti");
        template.korvaa("olematon", "Tätä ei käytetä");
        assertEquals("Terve, Aukusti ${sukunimi}", template.evaluoi());
    } 
}**/
public class TemplateTest {

    private Template template;

    @Before
    public void setup() {
        template = new Template("${yy}, ${kaa}, ${koo}, ${nee}");
        template.korvaa("yy", "1");
        template.korvaa("kaa", "2");
        template.korvaa("koo", "3");
    }

    @Test (expected = PuuttuvaArvoException.class)
    public void montaMuuttujaa() {
        assertEquals("1, 2, 3, ${nee}", template.evaluoi());
    }

    @Test 
    public void tuntematonMuuttuja() {
        template.korvaa("nee", "4");
        template.korvaa("olematon", "Tätä ei käytetä");
        assertEquals("1, 2, 3, 4", template.evaluoi());
    }
    
    @Test
    public void puuttuvastaPoikkeus() {
        try {
            new Template("Terve, ${olematon}").evaluoi();
            fail("evaluoi()-metodin tulisi heittää poikkeus, jos "
                    + "muuttujalle ei ole asetettu arvoa.");
        } catch (PuuttuvaArvoException expected) {
            assertEquals("Muuttujalla ${olematon} ei ole arvoa.", expected.getMessage()); 
        }
    }
}
