package com.example.ustadim_yeni;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;

public class AnasayfaController {

    // İPC Hesaplama Ekranına Geçiş
// AnasayfaController.java içinde:
    @FXML
    private void goToIpc(ActionEvent event) {
        UstadimMainController mainController = HelloApplication.getMainController();
        if (mainController != null) {
            // FXML dosya adını doğru verip vermediğinizi kontrol etmek için konsola yazdırın
            System.out.println("Gidilecek FXML: IpcMainView.fxml");
            mainController.setCenter("IpcMainView.fxml");
        }
    }

    // PEK Hesaplama Ekranına Geçiş
    @FXML
    private void goToPek(ActionEvent event) {
        UstadimMainController mainController = HelloApplication.getMainController();
        if (mainController != null) {
            // Dosya adının TAM olarak 'PekMainView.fxml' olduğundan emin olun
            mainController.setCenter("PekMainView.fxml");
        }
    }
    // Mevzuat Ekranına Geçiş
    @FXML
    private void goToMevzuat(ActionEvent event) {
        UstadimMainController mainController = HelloApplication.getMainController();
        if (mainController != null) {
            // Dosya adının TAM olarak 'MevzuatView.fxml' olduğundan emin olun
            mainController.setCenter("MevzuatView.fxml");
        }
    }
}