package org.swiftsms.desktop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.URL;

public class ApplicationGUI extends Application {

    private ClassPathXmlApplicationContext context;

    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception{
        context = new ClassPathXmlApplicationContext("beans.xml");

        final URL resource = ClassLoader.getSystemClassLoader().getResource("FXML/main.fxml");
        final Callback<Class<?>, Object> factory = new SpringLoader(context);
        final Parent root = FXMLLoader.load(resource, null, null, factory);

        primaryStage.setScene(new Scene(root, 900, 550));
        primaryStage.setTitle("SwiftSMS");
        primaryStage.setMinHeight(550);
        primaryStage.setMinWidth(900);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        context.close();
    }
}
