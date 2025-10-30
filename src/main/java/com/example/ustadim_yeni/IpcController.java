package com.example.ustadim_yeni;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;

public class IpcController {

    @FXML
    private void goToAnasayfa(ActionEvent event) {
        UstadimMainController mainController = HelloApplication.getMainController();
        if (mainController != null) {
            // Dosya adının TAM olarak 'AnasayfaView.fxml' olduğundan emin olun
            mainController.setCenter("AnasayfaView.fxml");
        }
    }
}