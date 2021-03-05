package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("VolTex");
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(400);
        primaryStage.setMaxWidth(1200);
        primaryStage.setMaxHeight(900);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        //grid.setStyle("-fx-background-color: #2e3534; -fx-text-fill: white;");

        Text scenetitle = new Text("VolText");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        TextArea userTextArea = new TextArea();

        double prefWidth = 1000;
        double prefHeight = 600;
        userTextArea.setPrefSize(prefWidth, prefHeight);
        //userTextArea.setStyle(" -fx-highlight-fill: lightgrey; -fx-highlight-text-fill: black; -fx-text-fill: wheat; ");
        //userTextArea.setMaxSize(3*prefWidth, 3*prefHeight);


        grid.add(userTextArea, 1, 1);

        Button btnApri = new Button("Apri");
        btnApri.setPrefSize(75,50);
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.CENTER);
        hbBtn.getChildren().add(btnApri);
        grid.add(hbBtn, 1, 4);

        Button btnSalva = new Button("Salva");
        btnSalva.setPrefSize(75,50);
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.CENTER);
        hbBtn2.getChildren().add(btnSalva);
        grid.add(hbBtn2, 1, 5);

        Button btnCrea = new Button("Crea PDF");
        btnCrea.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-border-color: darkgreen; -fx-border-width: 2px; -fx-font-size: 1em;");
        btnCrea.setPrefSize(100,75);
        HBox hbBtn3 = new HBox(10);
        hbBtn3.setAlignment(Pos.CENTER);
        hbBtn3.getChildren().add(btnCrea);
        grid.add(hbBtn3, 1, 6);

        TextArea consoleTextArea = new TextArea();
        consoleTextArea.setPrefSize(prefWidth, 250);
        consoleTextArea.setEditable(false);
        consoleTextArea.setStyle(" -fx-highlight-fill: lightgrey; -fx-highlight-text-fill: black; -fx-text-fill: wheat; ");
        consoleTextArea.setText("La bella la va al fosso,ravanei remulass \n barbabietole spinass \n daghel al terun.. DAGHEL AL TERUUNNN!!");
        grid.add(consoleTextArea, 1, 7);

        btnApri.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {

                JFileChooser fileChooser = new JFileChooser();
                //fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();

                    try {

                        Scanner myReader = new Scanner(selectedFile);
                        userTextArea.setText("");
                        while (myReader.hasNextLine()) {

                            userTextArea.appendText(myReader.nextLine() + System.lineSeparator());
                        }
                        myReader.close();

                        msg("File aperto", consoleTextArea, false);

                    } catch (IOException exc) {
                        msg("Errore: " + exc.getMessage(), consoleTextArea, true);
                        exc.printStackTrace();
                    }

                }
                else
                {
                    msg("File non valido!", consoleTextArea, true);
                    //JOptionPane.showMessageDialog(null,"File non valido!");
                }

            }
        });


        btnSalva.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {

                if(userTextArea.getText().trim().equals(""))
                {
                    msg("Grammatica vuota!", consoleTextArea, true);
                    return;
                }

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Salva");

                int userSelection = fileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    try {
                        FileWriter myWriter = new FileWriter(fileToSave);
                        myWriter.write(userTextArea.getText());
                        myWriter.close();
                        //JOptionPane.showMessageDialog(null,"File salvato");
                        msg("File salvato", consoleTextArea, false);
                    } catch (IOException exc) {
                        //JOptionPane.showMessageDialog(null,"Errore: " + exc.getMessage());
                        msg("Errore: " + exc.getMessage(), consoleTextArea, true);
                        exc.printStackTrace();
                    }

                }
                else
                {
                    msg("Selezione non valida", consoleTextArea, true);
                }
            }
        });


        btnCrea.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {

                msg("Creando il PDF", consoleTextArea, false);
                String grammatica = userTextArea.getText();


                //Some actions


            }
        });

        Scene scene = new Scene(grid, 700, 700);
        primaryStage.setScene(scene);
        primaryStage.show();

        Region region = ( Region ) consoleTextArea.lookup( ".content" );
        region.setStyle( "-fx-background-color: #272326;" );

//        Region region2 = ( Region ) userTextArea.lookup( ".content" );
//        region2.setStyle( "-fx-background-color: #272326;" );

    }


    public static void main(String[] args) {
        launch(args);
    }


    public void msg(String s, TextArea a, boolean bad)
    {
        a.setText(s);
    }
}
