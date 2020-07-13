package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import sample.model.*;


public class Controller {
    private Model model;

    public ObservableList<Point> points = FXCollections.observableArrayList();

    public Thread threadA,
            threadB;


    public Controller(Model model) {
        this.model = model;

    }

    public void setPoints(ObservableList<Point> points) {
        model.setListB(points);
    }

    public ObservableList<Point> getPoints() {
        return model.getListB();
    }

    public void start(XYChart.Series series, XYChart.Series seriesB, ObservableList<Point> points) {

        threadA = new Thread(new GraphicA(series));
        threadB = new Thread(new GraphicB(seriesB, points));

        threadA.start();
        threadB.start();
    }

    public void stop() {

        threadA.interrupt();
        threadB.interrupt();
    }
}
