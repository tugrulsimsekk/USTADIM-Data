package com.example.ustadim_yeni;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import com.example.ustadim_yeni.util.JsonDataLoader;
import com.example.ustadim_yeni.model.IpcHesaplamaKaydi;
import java.time.LocalDate;
import java.util.Map;

public class Ipc6331Controller {

    @FXML
    private DatePicker tarihSecici6331;
    @FXML
    private ComboBox<String> sorumluSecici;
    @FXML
    private ComboBox<String> tehlikeSinifiSecici;
    @FXML
    private ComboBox<String> calisanSayisiSecici;
    @FXML
    private VBox dinamikGirisAlani6331;

    @FXML
    public void initialize() {
        sorumluSecici.setItems(FXCollections.observableArrayList("İşveren", "SHS"));
        tehlikeSinifiSecici.setItems(FXCollections.observableArrayList("AZ TEHLİKELİ", "TEHLİKELİ", "ÇOK TEHLİKELİ"));
        calisanSayisiSecici.setItems(FXCollections.observableArrayList("10'DAN AZ", "10-49 ARASI", "50'DEN FAZLA"));
    }

    @FXML
    private void hesaplaCeza6331() {
        LocalDate tarih = tarihSecici6331.getValue();
        String sorumluk = sorumluSecici.getValue();
        String tehlike = tehlikeSinifiSecici.getValue();
        String calisanlar = calisanSayisiSecici.getValue();

        if (tarih == null || sorumluk == null || tehlike == null || calisanlar == null) {
            new Alert(Alert.AlertType.ERROR, "Lütfen tüm alanları doldurunuz.").showAndWait();
            return;
        }

        Map<String, Double> ceza = JsonDataLoader.loadCezalar6331(tarih, sorumluk, tehlike, calisanlar);

        if (ceza.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Seçilen kriterler için ceza bulunamadı.").showAndWait();
        } else {
            double tutar = ceza.get("tutar");

            // Tabloya ekle
            IpcHesaplamaKaydi yeniKayit = new IpcHesaplamaKaydi(
                    "26",
                    "6331",
                    tarih,
                    tutar,
                    tutar,
                    sorumluk + " - " + tehlike + " - " + calisanlar
            );

            FooterController footer = FooterController.getInstance();
            if (footer != null) {
                footer.addIpcKaydi(yeniKayit);
                new Alert(Alert.AlertType.INFORMATION,
                        String.format("6331 Cezası Eklendi: %.2f TL", tutar)).showAndWait();
            }
        }
    }
}