package com.example.ustadim_yeni;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import java.io.IOException;

public class IpcController {

    // FXML'deki dinamik içerik alanını yakalar
    @FXML
    private StackPane ipcContentPane;

    public FooterController footerController;

    // Gerekli değil ama FXML Loader'ın hata vermemesi için
    @FXML
    public void initialize() {
        // Uygulama açıldığında varsayılan olarak 5510'u yükleyebiliriz.
        // loadKanun5510(null);
    }
    public void setFooterController(FooterController controller) {
        this.footerController = controller;
    }
    // 5510 Sayılı Kanun Ekranını Yükleme Metodu
    @FXML
    private void loadKanun5510(ActionEvent event) {
        // setContent metodunu çağıracağız
        setContent("Ipc5510View.fxml");
    }

    // 6331 Sayılı Kanun Ekranını Yükleme Metodu
    @FXML
    private void loadKanun6331(ActionEvent event) {
        // setContent metodunu çağıracağız
        setContent("Ipc6331View.fxml");
    }

    // StackPane içeriğini değiştiren genel metot
    private void setContent(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ustadim_yeni/" + fxmlFileName));
            Node content = loader.load();
            ipcContentPane.getChildren().setAll(content); // Mevcut içeriği yeni ile değiştir
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("İPC Ekran Yükleme Hatası: " + fxmlFileName);
        }
    }

    // Ana Sayfaya Geri Dönüş
    @FXML
    private void goToAnasayfa(ActionEvent event) {
        UstadimMainController mainController = HelloApplication.getMainController();
        if (mainController != null) {
            mainController.setCenter("AnasayfaView.fxml");
        }
    }
}