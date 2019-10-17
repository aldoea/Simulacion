package sample.Vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.MainForm;
import sample.pseudoaleatorios;

public class Histograma {
    private BorderPane histoParentContainer;
    private Stage histStage;
    private Scene mainScene;
    private Stage regresarStage;

    public Histograma(Stage stage) {
        histStage = stage;
        regresarStage = stage;
        CrearUI();
    }

    public void CrearUI() {
        histoParentContainer = new BorderPane();
        histoParentContainer.setTop(crearHeader());
        histoParentContainer.setCenter(crearBody());
        histoParentContainer.setBottom(crearFooter());
        mainScene = new Scene(histoParentContainer);
        mainScene.getStylesheets().add(getClass().getResource("../CSS/bs3.css").toExternalForm());
        histStage.setScene(mainScene);
        histStage.setMaximized(true);
        histStage.show();
    }

    private HBox crearHeader() {
        HBox headerHbox = new HBox();
        headerHbox.setId("header-content");
        HBox regresarBox, tituloBox;
        // Boton regresar
        Button regresarBtn = new Button();
        regresarBtn.getStyleClass().add("btn-regresar");
        regresarBtn.setOnAction(event -> Regresar());
        regresarBox = new HBox();
        regresarBox.getChildren().addAll(regresarBtn);
        regresarBox.setId("back-button-box");
        // Titulo
        Label tituloMain = new Label("Datos generados e histograma");
        tituloMain.setId("main-header-label");
        tituloBox = new HBox();
        tituloBox.getChildren().add(tituloMain);
        tituloBox.setId("title-box");
        headerHbox.getChildren().addAll(regresarBox, tituloBox);
        return headerHbox;
    }

    private HBox crearBody() {
        HBox bodyHbox = new HBox();
        VBox tableBox = new VBox();
        VBox histBox = new VBox();
        Label tableTitle = new Label("Datos generados");
        Label histoTitle = new Label("Histograma");
        tableBox.getChildren().addAll(tableTitle, crearTabla());
        histBox.getChildren().add(histoTitle);
        bodyHbox.getChildren().addAll(tableBox, histBox);
        return bodyHbox;
    }

    private TableView crearTabla() {
        //  Periodo  Xi      X        Ri
        TableView numeros = new TableView();
        numeros.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Integer, pseudoaleatorios> periodoCol = new TableColumn<>("Periodo");
        periodoCol.setCellValueFactory(new PropertyValueFactory<>("periodo"));

        TableColumn<String, pseudoaleatorios> xiCol = new TableColumn<>("Xi");
        xiCol.setCellValueFactory(new PropertyValueFactory<>("xi"));

        TableColumn<Integer, pseudoaleatorios> xCol = new TableColumn<>("X");
        xCol.setCellValueFactory(new PropertyValueFactory<>("x"));

        TableColumn<Double, pseudoaleatorios> riCol = new TableColumn<>("Ri");
        riCol.setCellValueFactory(new PropertyValueFactory<>("ri"));


        numeros.getColumns().addAll(periodoCol, xiCol, xCol, riCol);
        numeros.getItems().add(new pseudoaleatorios(0, "X0", 99, 0.1566));
        return numeros;
    }

    private HBox crearFooter() {
        HBox footerHBox = new HBox();
        Button pruebaMedia, pruebaVarianza, pruebaUniformidad, pruebaIndependencia;
        pruebaMedia = new Button("Prueba de Medias");
        pruebaVarianza = new Button("Prueba de Varianza");
        pruebaUniformidad = new Button("Prueba de Uniformidad");
        pruebaIndependencia = new Button("Prueba de independencia");
        footerHBox.getChildren().addAll(pruebaMedia, pruebaVarianza, pruebaUniformidad, pruebaIndependencia);
        return footerHBox;
    }

    private void Regresar() {
        new MainForm(regresarStage);
    }
}

