package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import sample.model.Model;
import sample.model.Point;

import java.math.BigDecimal;
import java.math.RoundingMode;


import static java.lang.StrictMath.pow;

public class GraphicB implements Runnable {
    private XYChart.Series<Number, Number> seriesB;

    private ObservableList<Point> point;


    public Model model = new Model();

    public GraphicB(XYChart.Series<Number, Number> seriesB, ObservableList<Point> point) {
        this.point = point;
        this.seriesB = seriesB;

    }


    public void setPoint(ObservableList<Point> point) {
        model.setListB(point);
    }

    public ObservableList<Point> getPoint() {
        return point;
    }


    @Override
    public void run() {

        for (double x = 0; x < 1.75; x += 0.01) {

            seriesB.getData().add(new XYChart.Data(x, func(x)));

            model.addPoint(new Point(round(x, 4), func(x)), point);


            try {
                Thread.sleep(100);
            } catch (Exception e) {
                break;
            }
        }

    }

    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public double func(double x) {


        double F = 0;
        double value = 1;
        int k = 0;

        while (value >= 0.01) {
            value = (pow(-1, k) * pow(x, (2 * k) + 1)) / (factorial(2 * k + 1));
            F += value;
            k++;
        }
        return round(F, 4);

    }


    private double factorial(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }
        return result;
    }
}