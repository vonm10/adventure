package cz.vse.vondra.main;

import cz.vse.vondra.MainController;
import cz.vse.vondra.model.Game;
import cz.vse.vondra.model.IGame;
import cz.vse.vondra.textui.TextUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * Hlavní třída určená pro spuštění hry. Obsahuje pouze statickou metodu
 * {@linkplain #main(String[]) main}, která vytvoří instance logiky hry
 * a uživatelského rozhraní, propojí je a zahájí hru.
 *
 * @author Jarmila Pavlíčková
 * @author Jan Říha
 * @version LS 2020
 */
public final class Start extends Application
{
    /**
     * Metoda pro spuštění celé aplikace.
     *
     * @param args parametry aplikace z příkazového řádku
     */
    public static void main(String[] args)
    {
       /* IGame game = new Game();
        TextUI textUI = new TextUI(game);
        
        if (args.length == 0) {
            textUI.play();
        } else {
            textUI.play(args[0]);
        } */

        List<String> vstup = Arrays.asList(args);


        if(vstup.contains("text")) {
            IGame hra = new Game();
            TextUI ui = new TextUI(hra);
            ui.play();
        } else {
            launch();
        }
    }

    //private Start() {}

    @Override
    public void start(Stage primaryStage) throws Exception {
        IGame hra = new Game();

        System.out.println("Startuji");

        primaryStage.setTitle("Trosečník");

        primaryStage.show();

        FXMLLoader loader = new FXMLLoader();
        InputStream stream = getClass().getClassLoader().getResourceAsStream("scene.fxml");
        Parent root = loader.load(stream);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);

        MainController controller = loader.getController();
        controller.init(hra);


    }

}
