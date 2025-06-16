package com.example;

// Importa las clases necesarias para una aplicación JavaFX
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    // Escena principal de la aplicación (se puede reutilizar al cambiar vistas)
    public static Scene scene;

    // Método que se ejecuta al iniciar la aplicación
    @Override
    public void start(Stage stage) throws IOException {
        // Carga la vista "primary.fxml" como la escena inicial con tamaño 640x480
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene); // Asocia la escena al escenario (ventana)
        stage.show();          // Muestra la ventana
    }

    // Cambia el contenido de la escena principal a otro FXML
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    // Carga un archivo FXML desde los recursos y devuelve su raíz
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    // Método principal que lanza la aplicación JavaFX
    public static void main(String[] args) {
        launch();
    }

}
