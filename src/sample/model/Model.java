package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Model {
    private ObservableList<Point> listB;

    public Model() {
        listB = FXCollections.observableArrayList();
    }

    public void setListB(ObservableList<Point> listB) {
        this.listB = listB;
    }

    public ObservableList<Point> getListB() {
        return listB;
    }

    public void addPoint(Point point, ObservableList<Point> list) {
        list.add(point);

    }
}
