package cz.vse.vondra.model;

/**
 * Třída představující aktuální stav hry. Veškeré informace o stavu hry
 * <i>(mapa lokací, inventář, vlastnosti hlavní postavy, informace o plnění
 * úkolů apod.)</i> by měly být uložené zde v podobě datových atributů.
 * <p>
 * Třída existuje především pro usnadnění potenciální implementace ukládání
 * a načítání hry. Pro uložení rozehrané hry do souboru by mělo stačit uložit
 * údaje z objektu této třídy <i>(např. pomocí serializace objektu)</i>. Pro
 * načtení uložené hry ze souboru by mělo stačit vytvořit objekt této třídy
 * a vhodným způsobem ho předat instanci třídy {@link Game}.
 *
 * @author Michael Kölling
 * @author Luboš Pavlíček
 * @author Jarmila Pavlíčková
 * @author Jan Říha
 * @author Matyáš Vondra
 * @version LS 2020 - 2020-05-31
 *
 * @see <a href="https://java.vse.cz/4it101/AdvSoubory">Postup pro implementaci ukládání a načítání hry na předmětové wiki</a>
 * @see java.io.Serializable
 */
public class GamePlan
{ 
    private Area currentArea;
    private Area finalArea;

    // Vytvoření inventáře
    private Inventar inventar;
    private Inventar lod;
    
    // Oblasti a předměty potřebné pro celou třídu
    private Area liany = new Area("liány","V zelených liánách vidíš pohupovat se drzou opici.");
    private Item banan = new Item("banán", "Banán, oblíbená pochoutka všech opic.");
    private Item veslo = new Item("veslo", "Veslo, které potřebuješ abys odplul.");
    
    private boolean mluvenoSTygrem = false;
    /**
     * Konstruktor třídy. Pomocí metody {@link #prepareWorldMap() prepareWorldMap}
     * vytvoří jednotlivé lokace a propojí je pomocí východů.
     */
    public GamePlan()
    {
        prepareWorldMap();
        inventar = new Inventar(1);
        lod = new Inventar(3);
    }
    
    /**
     * Metoda vrací inventář. Využito v tříde Game.
     * 
     * @return inventar
     */
    public Inventar getInventar()
    {
        return inventar;
    }
    
    /**
     * Metoda vrací inventář lodi. Využito v tříde Game.
     * 
     * @return lod
     */
    public Inventar getLod()
    {
        return lod;
    }

    /**
     * Metoda vytváří jednotlivé lokace a propojuje je pomocí východů.
     */
    private void prepareWorldMap()
    {
        // Vytvoříme jednotlivé lokace
        Area ztroskotanaLod = new Area("ztroskotaná_loď","Tady jsi ztroskotal. Můžeš zde loď opravit.");
        Area plaz = new Area("pláž", "Rozlehlá písečná pláž. Vidíš zde začátek džungle");
        Area dzungle = new Area("džungle","Kolem tebe se ozývají zvuky divokých zvířat. Můžeš pokračovat dál.");
        Area hlubokaDzungle = new Area("hluboká_džungle","Moc toho ve tmě nevidíš. Slyšíš ale vrčení tygra.");
        Area mytina = new Area("mýtina","Slunečná mýtina na kraji lesa.");
        Area skala = new Area("skála","Nehostinné skály.");
        
        // Nastavíme průchody mezi lokacemi (sousední lokace)
        ztroskotanaLod.addExit(plaz);
        plaz.addExit(ztroskotanaLod);

        plaz.addExit(dzungle);
        dzungle.addExit(plaz);

        dzungle.addExit(hlubokaDzungle);
        hlubokaDzungle.addExit(dzungle);

        plaz.addExit(mytina);
        mytina.addExit(plaz);
        
        mytina.addExit(liany);
        liany.addExit(mytina);
        
        mytina.addExit(skala);
        skala.addExit(mytina);
        
        // Přidáme předměty do lokací
        Item rum = new Item("rum", "Láhev vyzrálého rumu.");        
        Item lodicka = new Item("loď", "Tvoje ztroskotaná loď", false);
        Item spadlyStrom = new Item("spadlý_strom", "Strom vyvrácený větrem.", false);
        Item balvan = new Item("balvan", "Težký, vodou a větrem ošlehaný balvan.", false);
        Item kormidlo = new Item("kormidlo", "Kormidlo, které potřebuješ abys odplul.");
        Item plachta = new Item("plachta", "Plachta, kterou potřebuješ abys odplul.");
        
        ztroskotanaLod.addItem(lodicka);
        ztroskotanaLod.addItem(rum);
        skala.addItem(kormidlo);
        skala.addItem(balvan);
        mytina.addItem(spadlyStrom);
        dzungle.addItem(banan);
        hlubokaDzungle.addItem(plachta);
        
        // Přidání postav
        Postava opice = new Postava("opice", "Dám ti veslo pouze výměnou za banán!",false);
        Postava tygr = new Postava("tygr", "Tvoje chyba, žes mě dráždil.",true);
        
        liany.addPostavu(opice);
        hlubokaDzungle.addPostavu(tygr);

        // Hru začneme v domečku
        currentArea = ztroskotanaLod;
        
        // finalArea pro kontrolu vkládání předmětů do lodi
        finalArea = ztroskotanaLod;        
    }

    /**
     * Metoda vrací odkaz na aktuální lokaci, ve které se hráč právě nachází.
     *
     * @return aktuální lokace
     */
    public Area getCurrentArea()
    {
        return currentArea;
    }
    
    /**
     * Metoda vrací odkaz na finální lokaci.
     *
     * @return finální lokace
     */
    public Area getFinalArea()
    {
        return finalArea;
    }

    /**
     * Metoda nastaví aktuální lokaci, používá ji příkaz {@link CommandMove}
     * při přechodu mezi lokacemi.
     *
     * @param area lokace, která bude nastavena jako aktuální
     */
    public void setCurrentArea(Area area)
    {
        currentArea = area;
    }
    
    /**
     * Metoda ověřující, zda jsou splněny podmínky pro výhru.
     * 
     * @return true nebo false
     */
    public boolean isVictorious()
    {
        if (lod.obsahujeVec("plachta") && lod.obsahujeVec("veslo") && lod.obsahujeVec("kormidlo"))
        {
            return true;
        }
        return false;
    }
    
     /**
     * Metoda, která nastavuje příznak podle vstupujícího parametru.
     * 
     * @param mluveno true nebo false
     */    
    public void setMluvenoSTygrem(boolean mluveno)
    {
        mluvenoSTygrem = mluveno;
    }
    
    /**
     * Metoda vrací příznak, zda již bylo s tygrem mluveno.
     * 
     * @return true nebo false
     */
    public boolean getMluvenoSTygrem()
    {
        return mluvenoSTygrem;
    }
    
    
    /**
     * Metoda ověřuje, zda bylo s tygrem mluveno. Pokud ano, hráč je poražen.
     * 
     * @return true nebo false
     */
    public boolean isDefeated()
    {
        if (mluvenoSTygrem)
        {
            return true;
        }
        return false;
    }
    
    /**
     * Metoda provede výměnu předmětů s opicí. Smaže předmět banan a hráči přidá do inventáře veslo.
     */
    public void vymenaSOpici()
    {
        liany.removeItem(banan.getName());
        inventar.vlozitDoInventare(veslo);
    }
    
    
    private boolean prohra;
    public void setProhra(boolean stav)
    {
        prohra = stav;
    }
    
    public boolean isProhra()
    {
        if (prohra == true)
        {
            return true;
        }
        return false;
    }
}
