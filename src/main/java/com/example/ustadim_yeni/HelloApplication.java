package com.example.ustadim_yeni; // YENİ PROJE ADINA UYGUN PAKET ADI

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
// PAKET ADINDAKİ DEĞİŞİKLİK BURAYA YANSITILDI

import com.example.ustadim_yeni.util.UpdateChecker;

public class HelloApplication extends Application {

    private static UstadimMainController mainControllerInstance;

    @Override
    public void start(Stage stage) throws IOException {

        // FXML dosyası da yeni pakete taşındığı için, yolu doğru olmalı.
        // FXML yükleme adını USTADIM_YENI'ye göre ayarladım.
        // HelloApplication.java içinde, start metodunun içindeki satır:
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/ustadim_yeni/UstadimMainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);

        stage.setTitle("ÜSTADIM - Hesaplama ve Mevzuat Yönetimi");
        stage.setScene(scene);
        stage.show();
// Güncelleme kontrolü
        UpdateChecker.checkAndUpdateFiles();
        mainControllerInstance = fxmlLoader.getController();
    }

    public static UstadimMainController getMainController() {
        return mainControllerInstance;
    }

    public static void main(String[] args) {
        launch();
    }
}