/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package cz.vse.vondra.model;





/*******************************************************************************
 * Třída implementující příkaz výpis pro vypsání obsahu inventáře.
 *
 * @author  Matyáš Vondra
 * @version 2020-05-31
 */
public class CommandVypis implements ICommand
{
    // Datové atributy
    private final static String NAME = "vypis";
    private Inventar inventar;
    
    /**
     * Konstruktor třídy. Inicializuje datové atributy.
     * 
     * @param inventar objekt třídy Inventar
     */
    public CommandVypis(Inventar inventar)
    {
        this.inventar = inventar;
    }
    
    /**
     * Metoda vykonání příkazu- provede metodu inventáře pro výpis obsahu.
     * 
     * @param parameters parametry příkazu <i>(očekává se pole s jedním prvkem)</i>
     * @return informace pro hráče, které se vypíšou na konzoli
     */
    @Override
    public String process(String... parameters)
    {
        return inventar.vypisInventare();
    }
    
    /**
     * Metoda vrací název příkazu tj.&nbsp; slovo {@value NAME}.
     *
     * @return název příkazu
     */
    @Override
    public String getName()
    {
        return NAME;
    }
}
