/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package cz.vse.vondra;



import cz.vse.vondra.model.Inventar;
import cz.vse.vondra.model.Item;
import org.junit.Test;


import static org.junit.Assert.*;



/*******************************************************************************
 * Testovací třída {@code InventarTest} slouží ke komplexnímu otestování
 * třídy {@link InventarTest}.
 *
 * @author  Matyáš Vondra
 * @version 2020-06-11
 */
public class InventarTest
{
    private Item item1;
    private Item item2;
    private Inventar inventar;
    
    /**
     * Testovací metoda pro otestování inventáře
     */
    @Test
    public void testInvetare()
    {
        item1=new Item("item1", "blabla");
        item2=new Item("item2", "blabla");
        
        inventar = new Inventar(2);
        
        inventar.vlozitDoInventare(item1);
        assertTrue(inventar.obsahujeVec("item1"));
        assertEquals(item1, inventar.getVec("item1"));
        
        inventar.vlozitDoInventare(item2);
        assertTrue(inventar.obsahujeVec("item2"));
        assertEquals(item2, inventar.getVec("item2"));
        
        inventar.vyndatZInventare("item1");
        assertFalse(inventar.obsahujeVec("item1"));
        assertEquals(null, inventar.getVec("item1"));
        
        inventar.smazatVec("item2");
        assertFalse(inventar.obsahujeVec("item2"));
        assertEquals(null, inventar.getVec("item2"));
        
        assertEquals("V inventáři nemáš nic.", inventar.vypisInventare());
        inventar.vlozitDoInventare(item1);
        inventar.vlozitDoInventare(item2);
        assertEquals("V inventáři máš: item2item1",inventar.vypisInventare());
    }

}
