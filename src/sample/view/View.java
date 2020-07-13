package sample.view;


import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import sample.controller.Controller;
import sample.controller.GraphicB;
import sample.model.Point;


public class View {

    Controller controller;
    private Scene scene;
    private Button stop;
    private Button start;
    private Button doZoomOut;
    private Button doZoomIn;
    GraphicB gr;

    private double mouseOnChartX;
    private double mouseOnChartY;
    XYChart.Series series = new XYChart.Series();
    XYChart.Series seriesB = new XYChart.Series();

    private Stage stage;
    private GridPane root;

    final NumberAxis xAxis = new NumberAxis("x", -0.5, 5, 0.25);
    final NumberAxis yAxis = new NumberAxis("y", -5.5, 4, 0.25);
    LineChart lineChart = lineChart();


    public void window() {

        root = new GridPane();

        stop = new Button("Stop");
        start = new Button("Start");

        doZoomOut = new Button("-");
        doZoomIn = new Button("+");
        stop.setOnAction(ae -> {
                    tableView().getItems().clear();
                    controller.stop();
                }

        );
        start.setOnAction(ae ->

                controller.start(series, seriesB, controller.getPoints())

        );
        doZoomIn.setOnAction(e -> doZoomIn());
        doZoomOut.setOnAction(e -> doZoomOut());


        root.add(tableView(), 0, 0);
        root.add(lineChart, 1, 0);
        root.add(start, 0, 1);
        root.add(doZoomIn, 2, 1);
        root.add(doZoomOut, 2, 2);
        root.add(stop, 0, 2);
        setChartPressAndDragEvents();
        scene = new Scene(root);
    }


    public LineChart lineChart() {


        xAxis.setLabel("x");
        yAxis.setLabel("y");
        final LineChart<Number, Number> lineChart =
                new LineChart<Number, Number>(xAxis, yAxis);
        lineChart.setTitle("Graphic");

        series.setName("A");
        seriesB.setName("B");


        lineChart.getData().addAll(series, seriesB);

        return lineChart;
    }

    public View(Controller controller) {
        this.controller = controller;
        window();

        stage = new Stage();
        stage.setWidth(1000);
        stage.setHeight(500);
        stage.setTitle("Lab3");
        stage.setScene(scene);

    }


    public Stage getStage() {
        return stage;
    }


    public TableView tableView() {

        TableView<Point> table;
        table = new TableView<>();
        gr = new GraphicB(seriesB, controller.getPoints());
        table.setItems(gr.getPoint());

        TableColumn<Point, Double> x = new TableColumn("x"),
                y = new TableColumn("y");
        x.setCellValueFactory(new PropertyValueFactory("x"));
        y.setCellValueFactory(new PropertyValueFactory("y"));
        x.setMinWidth(200);
        y.setMinWidth(200);


        table.setMinHeight(200);
        table.setMaxWidth(400);
        table.getColumns().setAll(
                x, y);

        return table;
    }


    private int zoom = 0;

    private void doZoomIn() {
        if (zoom < 4) {
            xAxis.setLowerBound(xAxis.getLowerBound() + xAxis.getTickUnit());
            xAxis.setUpperBound(xAxis.getUpperBound() - xAxis.getTickUnit());
            yAxis.setLowerBound(yAxis.getLowerBound() + yAxis.getTickUnit());
            yAxis.setUpperBound(yAxis.getUpperBound() - yAxis.getTickUnit());
            zoom += 0.5;
        }
    }

    private void doZoomOut() {
        xAxis.setLowerBound(xAxis.getLowerBound() - xAxis.getTickUnit());
        xAxis.setUpperBound(xAxis.getUpperBound() + xAxis.getTickUnit());
        yAxis.setLowerBound(yAxis.getLowerBound() - yAxis.getTickUnit());
        yAxis.setUpperBound(yAxis.getUpperBound() + yAxis.getTickUnit());
        zoom -= 0.5;
    }

    private void setChartPressAndDragEvents() {

        lineChart.setOnMousePressed(mouseEvent -> {
            mouseOnChartX = mouseEvent.getX();
            mouseOnChartY = mouseEvent.getY();
        });

        lineChart.setOnMouseDragged(mouseEvent -> {

            xAxis.setLowerBound(xAxis.getLowerBound() + (mouseOnChartX - mouseEvent.getX()) / 4);
            xAxis.setUpperBound(xAxis.getUpperBound() + (mouseOnChartX - mouseEvent.getX()) / 4);
            mouseOnChartX = mouseEvent.getX();

            yAxis.setLowerBound(yAxis.getLowerBound() + (mouseEvent.getY() - mouseOnChartY) * 0.3);
            yAxis.setUpperBound(yAxis.getUpperBound() + (mouseEvent.getY() - mouseOnChartY) * 0.3);
            mouseOnChartY = mouseEvent.getY();

        });

    }


}
