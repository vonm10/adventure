/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package cz.vse.vondra;


import cz.vse.vondra.model.*;
import org.junit.Test;


import static org.junit.Assert.*;



/*******************************************************************************
 * Testovací třída {@code CommandPolozTest} slouží ke komplexnímu otestování
 * třídy {@link CommandPolozTest}.
 *
 * @author  Matyáš Vondra
 * @version 2020-06-11
 */
public class CommandPolozTest
{
    /**
     * Testovací metoda pro otestování příkazu Polož
     */
    @Test
    public void testPoloz()
    {
        GamePlan plan = new GamePlan();
        Inventar inventar = plan.getInventar();
        
        Item item1 = new Item ("item1", "blabla");
        inventar.vlozitDoInventare(item1);
                
        Area area1 = new Area("hala", "Toto je vstupní hala budovy VŠE na Jižním městě.");
        plan.setCurrentArea(area1);
             
        CommandPoloz prikaz = new CommandPoloz(plan);
        
        prikaz.process();
        assertEquals(1, inventar.getZaplnenost());
        assertEquals(true, inventar.obsahujeVec("item1"));
        
        prikaz.process("a", "a");
        assertEquals(1, inventar.getZaplnenost());
        assertEquals(true, inventar.obsahujeVec("item1"));
        
        prikaz.process("item2");
        assertEquals(1, inventar.getZaplnenost());
        assertEquals(true, inventar.obsahujeVec("item1"));
        
        prikaz.process("item1");
        assertEquals(0, inventar.getZaplnenost());
        assertEquals(false, inventar.obsahujeVec("item1"));
        assertEquals(true, area1.containsItem("item1"));
    }
}
