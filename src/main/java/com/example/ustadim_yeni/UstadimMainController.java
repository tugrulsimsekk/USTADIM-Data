package com.example.ustadim_yeni;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class UstadimMainController {

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    public void initialize() {
        // Başlangıçta hiçbir şey yapmaya gerek yok
    }

    public void setCenter(String fxmlFileName) {
        try {
            String resourcePath = "/com/example/ustadim_yeni/" + fxmlFileName;
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resourcePath));

            Node centerContent = loader.load();
            mainBorderPane.setCenter(centerContent);

            System.out.println("✅ Yüklendi: " + fxmlFileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("❌ Hata: FXML bulunamadı - " + fxmlFileName);
        }
    }
}