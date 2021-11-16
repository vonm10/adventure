/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package cz.vse.vondra;



import cz.vse.vondra.model.*;
import org.junit.Test;


import static org.junit.Assert.*;



/*******************************************************************************
 * Testovací třída {@code CommandPickTest} slouží ke komplexnímu otestování
 * třídy {@link CommandPickTest}.
 *
 * @author  Matyáš Vondra
 * @version 2020-06-11
 */
public class CommandPickTest
{
    /**
     * Testovací metoda pro otestování příkazu Vezmi
     */
    @Test
    public void testPick()
    {
        GamePlan plan = new GamePlan();
        Inventar inventar = plan.getInventar();
        
        Item item1 = new Item ("item1", "blabla");
        Item item2 = new Item ("item2", "blabla");
        Item item3 = new Item ("item3", "blabla", false);
        
        Area area1 = new Area("hala", "Toto je vstupní hala budovy VŠE na Jižním městě.");
        plan.setCurrentArea(area1);
        
        area1.addItem(item1);
        area1.addItem(item2);
        area1.addItem(item3);
        
        CommandPick prikaz = new CommandPick(plan);
        
        prikaz.process();        
        assertEquals(0, inventar.getZaplnenost());
        
        prikaz.process("a", "a");        
        assertEquals(0, inventar.getZaplnenost());
        
        prikaz.process("aaa"); 
        assertEquals(0, inventar.getZaplnenost());
        
        prikaz.process("item1");        
        assertEquals(1, inventar.getZaplnenost());
        assertEquals(true, inventar.obsahujeVec("item1"));
        
        prikaz.process("item2");        
        assertEquals(1, inventar.getZaplnenost());
        assertEquals(false, inventar.obsahujeVec("item2"));
        
        prikaz.process("item3");        
        assertEquals(1, inventar.getZaplnenost());
        assertEquals(false, inventar.obsahujeVec("item3"));
    }
}
