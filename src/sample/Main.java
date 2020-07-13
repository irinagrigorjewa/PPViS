package sample;

import javafx.application.Application;

import javafx.stage.Stage;


import sample.controller.Controller;
import sample.model.Model;
import sample.view.View;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Model model = new Model();
        Controller controller = new Controller(model);
        View view = new View(controller);

        primaryStage = view.getStage();
        primaryStage.show();

    }


    public static void main(String[] args) {

        launch(args);
    }
}
