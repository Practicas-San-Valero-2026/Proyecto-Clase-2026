package com.svalero.tienda.tienda;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("tienda.fxml"));
        fxmlLoader.setController(new AppController());
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Tienda");
        stage.setScene(scene);
        stage.show();
    }
}
