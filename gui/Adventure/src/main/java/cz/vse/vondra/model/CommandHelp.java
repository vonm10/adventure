package cz.vse.vondra.model;

/**
 * Třída implementující příkaz pro zobrazení nápovědy ke hře.
 *
 * @author Jarmila Pavlíčková
 * @author Luboš Pavlíček
 * @author Jan Říha
 * @version LS 2020
 */
public class CommandHelp implements ICommand
{
    private static final String NAME = "napoveda";

    private ListOfCommands listOfCommands;

    /**
     * Konstruktor třídy.
     *
     * @param listOfCommands odkaz na seznam příkazů, které je možné ve hře použít
     */
    public CommandHelp(ListOfCommands listOfCommands)
    {
        this.listOfCommands = listOfCommands;
    }

    /**
     * Metoda vrací obecnou nápovědu ke hře. Nyní vypisuje vcelku primitivní
     * zprávu o herním příběhu a seznam dostupných příkazů, které může hráč
     * používat.
     *
     * @param parameters parametry příkazu <i>(aktuálně se ignorují)</i>
     * @return nápověda, která se vypíše na konzoli
     */
    @Override
    public String process(String... parameters)
    {
        return "Tvým úkolem je opravit svou loď a dostat se z ostrova.\n"
                + "Toho docílíš sesbíráním předmětů: veslo, kormidlo\n"
                + "a plachta, a vložením těchto předmětů do lodi.\n"
                + "\n"
                + "Ve hře můžeš používat tyto příkazy:\n"
                + listOfCommands.getNames();
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
