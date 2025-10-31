package com.example.ustadim_yeni;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class UstadimMainController {

    // FXML dosyasındaki fx:id="mainBorderPane" ile eşleşir
    @FXML
    private BorderPane mainBorderPane;

    private FooterController footerController;
    @FXML
    public void initialize() {
        // FXML yükleyicileri, fx:include kullanıldığında initialize içinde çalışır.
        // Footer Controller'ı yakalamak için UstadimMainView.fxml'de değişiklik yapmalıyız.
    }

    // Dışarıdan FooterController'ı almak için (ve IpcController'ın kullanması için)
    public FooterController getFooterController() {
        return footerController;
    }
    public void setCenter(String fxmlFileName) {
        try {
            // Kesin FXML yolu: resources/com/example/ustadim_yeni
            String resourcePath = "/com/example/ustadim_yeni/" + fxmlFileName;
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resourcePath));

            Node centerContent = loader.load();
            mainBorderPane.setCenter(centerContent);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Hata: FXML bulunamadı. Yolu kontrol edin.");
        }
    }
}