package cz.vse.vondra;

import cz.vse.vondra.main.Start;
import cz.vse.vondra.model.Area;
import cz.vse.vondra.model.IGame;
import cz.vse.vondra.model.Item;
import cz.vse.vondra.model.Postava;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

public class MainController {
    private IGame hra;

    public Label locationName;
    public Label locationDescription;
    public Label background;

    public VBox exits;
    public VBox items;
    public VBox inventory;
    public VBox postavy;

    public Button btnNewGame;
    public Button btnHelp;
    public Button btnExit;

    public TextArea TextOutput;
    public TextField TextInput;

    public StackPane stack;


    public void init(IGame hra)
    {
        this.hra = hra;

        btnNewGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Start start = new Start();
                Stage primaryStage = new Stage();

                try {
                    start.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnHelp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File napoveda = new File("README.HTML");
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.open(napoveda);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });

        update();
    }

    private void update()
    {
        String location = getAktualniProstor().getName();
        locationName.setText(location);

        String description = getAktualniProstor().getDescription();
        locationDescription.setText(description);

        updateExits();
        updateItems();
        updateInventory();
        updatePostavy();

        if (hra.isGameOver()) {
            Platform.exit();
        }

        InputStream stream = getClass().getClassLoader().getResourceAsStream(location + ".jpg");
        Image img = new Image(stream);
        ImageView imageView = new ImageView(img);

        imageView.setFitHeight(360);
        imageView.setFitWidth(540);
        background.setGraphic(imageView);

    }

    private void updateExits()
    {
        Collection<Area> exitList = getAktualniProstor().getExits();
        exits.getChildren().clear();

        for (Area area : exitList)
        {
            String exitName = area.getName();
            Label exitLabel = new Label(exitName);

            exitLabel.setCursor(Cursor.HAND);

            InputStream stream = getClass().getClassLoader().getResourceAsStream(exitName + ".jpg");
            Image img = new Image(stream);
            ImageView imageView = new ImageView(img);

            imageView.setFitHeight(40);
            imageView.setFitWidth(60);
            exitLabel.setGraphic(imageView);

            exitLabel.setCursor(Cursor.HAND);
            exitLabel.setOnMouseClicked(event -> {
                executeCommand("jdi "+exitName);
            });

            exits.getChildren().add(exitLabel);
        }

    }

    private void updateItems()
    {
        Collection<Item> itemList = getAktualniProstor().getItems().values();
        items.getChildren().clear();

        for (Item item : itemList) {
            String itemName = item.getName();
            Label itemLabel = new Label(itemName);


            InputStream stream = getClass().getClassLoader().getResourceAsStream(itemName + ".jpg");
            Image img = new Image(stream);
            ImageView imageView = new ImageView(img);

            imageView.setFitHeight(40);
            imageView.setFitWidth(60);
            itemLabel.setGraphic(imageView);

            if(item.isMoveable())
            {
                itemLabel.setCursor(Cursor.HAND);

                itemLabel.setOnMouseClicked(event -> {
                    executeCommand("vezmi " + itemName);
                });
            } else {
                Tooltip nonMoveableHint = new Tooltip("Předmět '" + itemName + "' fakt neuneseš.");
                itemLabel.setTooltip(nonMoveableHint);
            }
            items.getChildren().add(itemLabel);
        }
    }

    private void updateInventory()
    {
        Collection<Item> itemList = hra.getGamePlan().getInventar().getItems();
        inventory.getChildren().clear();

        for (Item item : itemList) {
            String itemName = item.getName();
            Label itemLabel = new Label(itemName);


            InputStream stream = getClass().getClassLoader().getResourceAsStream(itemName + ".jpg");
            Image img = new Image(stream);
            ImageView imageView = new ImageView(img);

            imageView.setFitHeight(40);
            imageView.setFitWidth(60);
            itemLabel.setGraphic(imageView);

            itemLabel.setCursor(Cursor.HAND);

            if ((item.getName().equals("veslo") || item.getName().equals("kormidlo") || item.getName().equals("plachta")) & hra.getGamePlan().getCurrentArea().equals(hra.getGamePlan().getFinalArea()))
            {
                    itemLabel.setOnMouseClicked(event -> {
                        executeCommand("vloz " + itemName);
                    });
            } else {
                itemLabel.setOnMouseClicked(event -> {
                    executeCommand("poloz " + itemName);
                });
            }

            inventory.getChildren().add(itemLabel);
        }
    }

    private void updatePostavy()
    {
        Collection<Postava> postavaList = getAktualniProstor().getPostavy().values();
        postavy.getChildren().clear();

        for (Postava postava : postavaList) {
            String postavaName = postava.getJmeno();
            Label postavaLabel = new Label(postavaName);


            InputStream stream = getClass().getClassLoader().getResourceAsStream(postavaName + ".jpg");
            Image img = new Image(stream);
            ImageView imageView = new ImageView(img);

            imageView.setFitHeight(40);
            imageView.setFitWidth(60);
            postavaLabel.setGraphic(imageView);

            postavaLabel.setCursor(Cursor.HAND);

            postavaLabel.setOnMouseClicked(event -> {
                executeCommand("mluv "+postavaName); });

            postavy.getChildren().add(postavaLabel);
        }
    }

    private void executeCommand(String command) {
        String vysledek = hra.processCommand(command);
        TextOutput.appendText(vysledek + "\n\n");
        update();
    }

    private Area getAktualniProstor() {
        return hra.getGamePlan().getCurrentArea();
    }

    public void onInputKeyPressed(KeyEvent keyEvent)
    {
        if(keyEvent.getCode() == KeyCode.ENTER) {
            executeCommand(TextInput.getText());
            TextInput.setText("");
        }
    }
}
