/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package cz.vse.vondra;



import cz.vse.vondra.model.CommandTerminate;
import cz.vse.vondra.model.Game;
import org.junit.Test;


import static org.junit.Assert.*;



/*******************************************************************************
 * Testovací třída {@code CommandTerminateTest} slouží ke komplexnímu otestování
 * třídy {@link CommandTerminateTest}.
 *
 * @author  Matyáš Vondra
 * @version 2020-06-11
 */
public class CommandTerminateTest
{
    /**
     * Testovací metoda pro otestování příkazu Konec
     */
    @Test
    public void testTerminate()
    {
        Game game = new Game();
        CommandTerminate prikaz = new CommandTerminate(game);
        
        prikaz.process("a");
        assertEquals(false, game.isGameOver());
        
        prikaz.process();
        assertEquals(true, game.isGameOver());
    }
}


