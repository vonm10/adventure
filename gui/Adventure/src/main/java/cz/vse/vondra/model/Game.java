package cz.vse.vondra.model;

/**
 * Hlavní třída logiky aplikace. Třída vytváří instanci třídy {@link GamePlan},
 * která inicializuje lokace hry, a vytváří seznam platných příkazů a instance
 * tříd provádějících jednotlivé příkazy.
 *
 * Během hry třída vypisuje uvítací a ukončovací texty a vyhodnocuje jednotlivé
 * příkazy zadané uživatelem.
 *
 * @author Michael Kölling
 * @author Luboš Pavlíček
 * @author Jarmila Pavlíčková
 * @author Jan Říha
 * @version LS 2020
 */
public class Game implements IGame
{
    private ListOfCommands listOfCommands;
    private GamePlan gamePlan;
    
    // Stav hry
    private GameState state;

    /**
     * Konstruktor třídy. Vytvoří hru, inicializuje herní plán udržující
     * aktuální stav hry a seznam platných příkazů.
     */
    public Game()
    {
        state = GameState.GAME_IS_RUNNING;
        gamePlan = new GamePlan();
        listOfCommands = new ListOfCommands();
        
        listOfCommands.addCommand(new CommandHelp(listOfCommands));
        listOfCommands.addCommand(new CommandTerminate(this));
        listOfCommands.addCommand(new CommandMove(gamePlan));
        listOfCommands.addCommand(new CommandPick(gamePlan));
        listOfCommands.addCommand(new CommandInspect(gamePlan));
        listOfCommands.addCommand(new CommandVypis(gamePlan.getInventar()));
        listOfCommands.addCommand(new CommandPoloz(gamePlan));
        listOfCommands.addCommand(new CommandMluv(gamePlan));
        listOfCommands.addCommand(new CommandVloz(gamePlan));
    }

    /**
     * {@inher  itDoc}
     */
    @Override
    public String getPrologue()
    {
        return "Vítejte!\n"
                + "Ztroskotal(a) jsi na opuštěném ostrově. Najdi všechny tři věci na opravu lodi\n"
                + "a můžeš se odsud dostat.\n"
                + "Napište 'napoveda', pokud si nevíte rady, jak hrát dál.\n"
                + "\n"
                + gamePlan.getCurrentArea().getFullDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getEpilogue()
    {
        String epilogue = "Díky, že sis zahrál(a).";

        if (gamePlan.isVictorious()) {
            epilogue = "ZVÍTĚZIL(A) JSI !\n\n" + epilogue;
        }
        
        if (gamePlan.isDefeated())
        {
            epilogue = "SEŽRAL TĚ TYGR!\n\n" + epilogue;
        }

        return epilogue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGameOver()
    {
        if (state != GameState.GAME_IS_RUNNING || state == GameState.PROHRA)
        {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String processCommand(String line)
    {
        String[] words = line.split("[ \t]+");

        String cmdName = words[0];
        String[] cmdParameters = new String[words.length - 1];

        for (int i = 0; i < cmdParameters.length; i++) {
            cmdParameters[i] = words[i + 1];
        }

        String result = null;
        if (listOfCommands.checkCommand(cmdName)) {
            ICommand command = listOfCommands.getCommand(cmdName);
            result = command.process(cmdParameters);
        } else {
            result = "Nechápu, co po mně chceš. Tento příkaz neznám.";
        }

        if (gamePlan.isVictorious()) {
            state = GameState.VYHRA;
        }
        
        if (gamePlan.isDefeated())
        {
            state = GameState.PROHRA;
        }
        
        if (gamePlan.isProhra() == true)
        {
            state = GameState.PROHRA;
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GamePlan getGamePlan()
    {
        return gamePlan;
    }

    /**
     * Metoda nastaví stav hry na ukončený.
     */
    void setGameOver()
    {
        state = GameState.GAME_IS_NOT_RUNNING;
    }

}
