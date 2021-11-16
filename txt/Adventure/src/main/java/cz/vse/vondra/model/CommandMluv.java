/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package cz.vse.vondra.model;





/*******************************************************************************
 * Třída implementující příkaz mluv pro mluvení s vybranou postavou.
 *
 * @author  Matyáš Vondra
 * @version 2020-05-31
 */
public class CommandMluv implements ICommand
{
    // Datové atributy
    private final static String NAME = "mluv";
    private GamePlan plan;
    
    /**
     * Konstruktor třídy. Inicializuje datové atributy.
     * 
     * @param plan objekt třídy GamePlan
     */
    public CommandMluv(GamePlan plan)
    {
        this.plan = plan;
    }
    
    /**
     * Metoda vykonání příkazu- provede metodu postavy pro získání proslovu.
     * Podmínkami se kontrolují vstupní parametry a zda hledaná postava existuje.
     * 
     * Také je obsažena podmínka pro kontrolu mluvení s tygrem, která nastaví metodu
     * třídy GamePlan na true.
     * 
     * @param parameters parametry příkazu <i>(očekává se pole s jedním prvkem)</i>
     * @return informace pro hráče, které se vypíšou na konzoli
     */
    @Override
    public String process(String... parameters)
    {
        if (parameters.length == 0) {
            return "Nevím, s kým mám mluvit, musíš zadat název postavy.";
        }
        
        if (parameters.length > 1) {
            return "Tomu nerozumím, neumím mluvit s více postavami současně.";
        }
        
        String jmenoPostavy = parameters[0];
        Area area = plan.getCurrentArea();
        Postava postava = area.getPostavu(jmenoPostavy);
        
        if (postava == null)
        {
            return "Taková postava tu není.";
        }
        
        if (postava.isSmrtici() && plan.getInventar().getZaplnenost() == plan.getInventar().getKapacitaInventare())
        {
            plan.setProhra(true);
        }
        return postava.getProslov();
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
