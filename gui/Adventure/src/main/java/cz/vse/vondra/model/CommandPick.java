package cz.vse.vondra.model;

/**
 * Třída implementující příkaz Pick pro sebrání předmětu.
 * 
 * @author Jan Říha
 * @author Matyáš Vondra
 * @version 2020-05-31
 */
public class CommandPick implements ICommand
{
    private static final String NAME = "vezmi";
    
    private GamePlan plan;
    
    // Vytvoření inventáře
    private Inventar inventar; 
    
    
    /**
     * Konstruktor třídy. Inicializuje datové atributy.
     * 
     * @param plan objekt třídy GamePlan
     * @param inventar objekt třídy Inventar
     */
    public CommandPick(GamePlan plan)
    {
        this.plan = plan;
        inventar = plan.getInventar();
    }
    
    /**
     * Metoda vykonání příkazu- pokusí se sebrat předmět z aktuální lokace.
     * Podmínkami ověří počet parametrů, zda aktualní lokace předmět obsahuje,
     * jestli je pohyblivý a také jestli je v inventáři místo.
     * Pokud je vše splněno, vykoná se metoda pro přidání předmětu do
     * inventáře a vymazání z lokace.
     * 
     * 
     * @param parameters parametry příkazu <i>(očekává se pole s jedním prvkem)</i>
     * @return informace pro hráče, které se vypíšou na konzoli
     */
    @Override
    public String process(String... parameters)
    {
        if (parameters.length == 0) {
            return "Nevím, co mám sebrat, musíš zadat název předmětu.";
        }
        
        if (parameters.length > 1) {
            return "Tomu nerozumím, neumím sebrat více předmětů současně.";
        }
        
        String itemName = parameters[0];
        Area area = plan.getCurrentArea();
        
        if (!area.containsItem(itemName)) {
            return "Předmět '" + itemName + "' tady není.";
        }
        
        Item item = area.getItem(itemName);
        
        if (!item.isMoveable()) {
            return "Předmět '" + itemName + "' fakt neuneseš.";
        }
        
        // Vložení předmětu do inventáře
        if(inventar.getZaplnenost() < inventar.getKapacitaInventare())
        {
            inventar.vlozitDoInventare(item);    
            area.removeItem(itemName);
                    
            return "Sebral(a) jsi předmět '" + itemName + "' a uložil jsi ho do inventáře.";
        }
        return "Víc toho už neuneseš.";
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
