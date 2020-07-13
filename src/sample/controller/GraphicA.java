package sample.controller;

import javafx.scene.chart.XYChart;


public class GraphicA implements Runnable {

    private XYChart.Series<Number, Number> seriesA;

    public GraphicA(XYChart.Series<Number, Number> seriesA) {

        this.seriesA = seriesA;

    }

    @Override
    public void run() {
        int x = 0;
        while (!Thread.interrupted()) {

            seriesA.getData().add(new XYChart.Data(x, graphA(x)));

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                break;
            }

            x++;
        }

    }

    public int graphA(int x) {
        return 2 * x - 5;
    }
}