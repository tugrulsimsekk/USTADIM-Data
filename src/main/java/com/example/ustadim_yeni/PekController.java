package com.example.ustadim_yeni;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.example.ustadim_yeni.model.PrimOranlari;
import com.example.ustadim_yeni.model.KazancTuru;
import com.example.ustadim_yeni.util.JsonDataLoader;
import com.example.ustadim_yeni.model.AylikKazanc;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import com.example.ustadim_yeni.model.KazancKaydi;
import java.util.ArrayList;
import javafx.scene.layout.Region;
import javafx.scene.layout.Priority;

public class PekController {

    @FXML private ComboBox<String> hesaplamaYontemiCombo;
    @FXML private ComboBox<PrimOranlari> sigortalilikStatuCombo;
    @FXML private ComboBox<Integer> yilCombo;
    @FXML private ComboBox<String> ayCombo;
    @FXML private TextField calismaGunuInput;

    @FXML private ComboBox<KazancTuru> kazancTuruCombo;
    @FXML private TextField kazancTutarInput;
    @FXML private Label ekParametreLabel;
    @FXML private TextField ekParametreInput;

    @FXML private TableView<KazancKaydi> kazanclarTable;
    @FXML private TableColumn<KazancKaydi, String> kazancTuruColumn;
    @FXML private TableColumn<KazancKaydi, Double> kazancTutarColumn;
    @FXML private TableColumn<KazancKaydi, Double> primeTabiColumn;
    @FXML private TableColumn<KazancKaydi, Void> silColumn;

    @FXML private VBox sonucPanel;
    @FXML private Label sonucLabel;

    @FXML private VBox kumulatifPanel;
    @FXML private VBox aylikKazanclarBox;

    @FXML private Label toplamLabel;

    private Map<String, AylikKazanc> aylikKazancMap = new HashMap<>();
    private ObservableList<KazancKaydi> kazanclar = FXCollections.observableArrayList();

    @FXML private Label toplamTutarLabel;
    @FXML private Label toplamPrimeTabiLabel;

    @FXML
    public void initialize() {
        // Hesaplama Yöntemi
        hesaplamaYontemiCombo.setItems(FXCollections.observableArrayList(
                "Brüt → Net",
                "Net → Brüt"
        ));
        hesaplamaYontemiCombo.setValue("Brüt → Net");

        // Sigortalılık Statüsü
        List<PrimOranlari> primOranlariList = JsonDataLoader.loadPrimOranlari();
        sigortalilikStatuCombo.setItems(FXCollections.observableArrayList(primOranlariList));
        if (!primOranlariList.isEmpty()) {
            sigortalilikStatuCombo.setValue(primOranlariList.get(0));
        }

        // Yıl
        yilCombo.setItems(FXCollections.observableArrayList(
                2025, 2024, 2023, 2022, 2021, 2020
        ));
        yilCombo.setValue(2025);

        // Ay
        ayCombo.setItems(FXCollections.observableArrayList(
                "Ocak", "Şubat", "Mart", "Nisan", "Mayıs", "Haziran",
                "Temmuz", "Ağustos", "Eylül", "Ekim", "Kasım", "Aralık"
        ));
        ayCombo.setValue("Ocak");

        // Çalışma Günü
        calismaGunuInput.setText("30");

        // Kazanç Türleri
        List<KazancTuru> kazancTurleriList = JsonDataLoader.loadKazancTurleri();
        kazancTuruCombo.setItems(FXCollections.observableArrayList(kazancTurleriList));

        // Kazanç türü değiştiğinde ek parametre göster/gizle
        kazancTuruCombo.setOnAction(e -> updateEkParametre());

        // Tablo kolonları
        kazancTuruColumn.setCellValueFactory(new PropertyValueFactory<>("kazancTuruAd"));
        kazancTutarColumn.setCellValueFactory(new PropertyValueFactory<>("tutar"));
        primeTabiColumn.setCellValueFactory(new PropertyValueFactory<>("primeTabiTutar"));

        kazanclarTable.setItems(kazanclar);

        // Sil butonu kolonu
        setupSilColumn();

    }

    private void updateEkParametre() {
        KazancTuru secilenTur = kazancTuruCombo.getValue();
        if (secilenTur == null) {
            ekParametreLabel.setVisible(false);
            ekParametreLabel.setManaged(false);
            ekParametreInput.setVisible(false);
            ekParametreInput.setManaged(false);
            return;
        }

        if ("YEMEK".equals(secilenTur.hesaplama())) {
            ekParametreLabel.setText("Gün:");
            ekParametreInput.setPromptText("Gün");
            ekParametreLabel.setVisible(true);
            ekParametreLabel.setManaged(true);
            ekParametreInput.setVisible(true);
            ekParametreInput.setManaged(true);
        } else if ("COCUK".equals(secilenTur.hesaplama())) {
            ekParametreLabel.setText("Çocuk:");
            ekParametreInput.setPromptText("Max 2");
            ekParametreLabel.setVisible(true);
            ekParametreLabel.setManaged(true);
            ekParametreInput.setVisible(true);
            ekParametreInput.setManaged(true);
        } else {
            ekParametreLabel.setVisible(false);
            ekParametreLabel.setManaged(false);
            ekParametreInput.setVisible(false);
            ekParametreInput.setManaged(false);
        }
    }

    private void setupSilColumn() {
        silColumn.setCellFactory(param -> new TableCell<>() {
            private final Button silBtn = new Button("Sil");

            {
                silBtn.setOnAction(event -> {
                    KazancKaydi kayit = getTableView().getItems().get(getIndex());
                    kazanclar.remove(kayit);
                    updateToplam();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(silBtn);
                }
            }
        });
    }

    @FXML
    private void kazancEkle() {
        try {
            KazancTuru secilenTur = kazancTuruCombo.getValue();
            if (secilenTur == null) {
                new Alert(Alert.AlertType.WARNING, "Lütfen kazanç türü seçin!").showAndWait();
                return;
            }

            String tutarText = kazancTutarInput.getText().trim();
            if (tutarText.isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Lütfen tutar girin!").showAndWait();
                return;
            }

            double tutar = Double.parseDouble(tutarText);

            // Ek parametre kontrolü
            Integer ekParam = null;
            if (ekParametreInput.isVisible()) {
                String ekParamText = ekParametreInput.getText().trim();
                if (!ekParamText.isEmpty()) {
                    ekParam = Integer.parseInt(ekParamText);
                }
            }

            int yil = yilCombo.getValue();
            int calismaGunu = Integer.parseInt(calismaGunuInput.getText());
            KazancKaydi kayit = new KazancKaydi(secilenTur, tutar, ekParam, yil, calismaGunu);
            kazanclar.add(kayit);

            // Temizle
            kazancTutarInput.clear();
            ekParametreInput.clear();

            // Toplam güncelle
            updateToplam();

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Lütfen geçerli sayısal değer girin!").showAndWait();
        }
    }

    private void updateToplam() {
        double toplamTutar = kazanclar.stream()
                .mapToDouble(KazancKaydi::getTutar)
                .sum();

        double toplamPrimeTabi = kazanclar.stream()
                .mapToDouble(KazancKaydi::getPrimeTabiTutar)
                .sum();

        toplamTutarLabel.setText(String.format("%.2f TL", toplamTutar));
        toplamPrimeTabiLabel.setText(String.format("%.2f TL", toplamPrimeTabi));
    }

    @FXML
    private void kazanclariAylaraEkle() {
        try {
            if (kazanclar.isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Eklenecek kazanç yok!").showAndWait();
                return;
            }

            int yil = yilCombo.getValue();
            int ay = ayCombo.getSelectionModel().getSelectedIndex() + 1;
            String ayKey = yil + "-" + String.format("%02d", ay);

            // UYARI: Daha önce ekleme yapıldı mı?
            if (aylikKazancMap.containsKey(ayKey)) {
                Alert uyari = new Alert(Alert.AlertType.CONFIRMATION);
                uyari.setTitle("Dikkat!");
                uyari.setHeaderText(ayCombo.getValue() + " " + yil + " için daha önce kazanç eklediniz!");
                uyari.setContentText("Mevcut kazançların üzerine eklemek istiyor musunuz?");

                if (uyari.showAndWait().get() != ButtonType.OK) {
                    return;
                }
            }

            // Aylık kazanç oluştur veya varsa getir
            AylikKazanc aylikKazanc = aylikKazancMap.computeIfAbsent(ayKey,
                    k -> new AylikKazanc(yil, ay));

            // Kazançları ekle
            for (KazancKaydi kayit : kazanclar) {
                aylikKazanc.addKazanc(kayit);
            }

            // Prime esas toplamı hesapla (şimdilik basit toplam)
// Prime esas toplamı hesapla - TÜM kazançlardan
            double toplamPrimeEsas = aylikKazanc.getKazanclar().stream()
                    .mapToDouble(KazancKaydi::getPrimeTabiTutar)
                    .sum();
            aylikKazanc.setToplamPrimeEsas(toplamPrimeEsas);

            // Sağ paneli güncelle
            updateKumulatifPanel();

            // Kazanç listesini temizle
            kazanclar.clear();
            updateToplam();

            new Alert(Alert.AlertType.INFORMATION,
                    aylikKazanc.getAyAdi() + " " + yil + " için kazançlar kaydedildi!\n" +
                            "Prime Esas Toplam: " + String.format("%.2f TL", toplamPrimeEsas)
            ).showAndWait();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Hata: " + e.getMessage()).showAndWait();
            e.printStackTrace();
        }
    }

    private void updateKumulatifPanel() {
        // Tüm ayları temizle
        aylikKazanclarBox.getChildren().clear();

        // Ayları sırala (Yıl-Ay formatında)
        List<Map.Entry<String, AylikKazanc>> sortedList = new ArrayList<>(aylikKazancMap.entrySet());
        sortedList.sort(Map.Entry.comparingByKey());

        // Her ay için kart oluştur
        for (Map.Entry<String, AylikKazanc> entry : sortedList) {
            AylikKazanc aylikKazanc = entry.getValue();

// AY KARTI - TEK SATIRDA
            HBox ayKarti = new HBox(15);
            ayKarti.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
            ayKarti.setStyle("-fx-background-color: #F5F5F5; -fx-padding: 8; -fx-border-color: #ddd; -fx-border-width: 1; -fx-border-radius: 3; -fx-background-radius: 3;");

            // Başlık
            Label baslik = new Label(aylikKazanc.getAyAdi() + " " + aylikKazanc.getYil());
            baslik.setStyle("-fx-font-weight: bold; -fx-font-size: 12; -fx-min-width: 100;");

            // Toplam Kazanç
            double toplamKazanc = aylikKazanc.getKazanclar().stream()
                    .mapToDouble(KazancKaydi::getTutar)
                    .sum();
            Label toplamKazancLabel = new Label("Toplam Kazanç: " + String.format("%.2f TL", toplamKazanc));
            toplamKazancLabel.setStyle("-fx-font-size: 11;");

            // Prime Esas Kazanç
            Label primeEsasLabel = new Label("Prime Esas Kazanç: " + String.format("%.2f TL", aylikKazanc.getToplamPrimeEsas()));
            primeEsasLabel.setStyle("-fx-font-size: 11; -fx-font-weight: bold; -fx-text-fill: #4CAF50;");

            // Spacer
            Region spacer = new Region();
            HBox.setHgrow(spacer, javafx.scene.layout.Priority.ALWAYS);

            // Detay butonu
            Button detayBtn = new Button("📋 Detay");
            detayBtn.setStyle("-fx-font-size: 10; -fx-padding: 3 8;");
            detayBtn.setOnAction(e -> showAyDetay(aylikKazanc));

            // Silme butonu
            Button silBtn = new Button("🗑️ Sil");
            silBtn.setStyle("-fx-font-size: 10; -fx-padding: 3 8; -fx-background-color: #ffcccc; -fx-text-fill: red;");
            silBtn.setOnAction(e -> {
                Alert onay = new Alert(Alert.AlertType.CONFIRMATION);
                onay.setTitle("Silme Onayı");
                onay.setHeaderText(aylikKazanc.getAyAdi() + " " + aylikKazanc.getYil() + " ayını silmek istediğinize emin misiniz?");
                onay.setContentText("Bu işlem geri alınamaz!");

                if (onay.showAndWait().get() == ButtonType.OK) {
                    aylikKazancMap.remove(entry.getKey());
                    updateKumulatifPanel();
                }
            });

            // Kartı tamamla
            ayKarti.getChildren().addAll(baslik, toplamKazancLabel, primeEsasLabel, spacer, detayBtn, silBtn);
            aylikKazanclarBox.getChildren().add(ayKarti);
        }
    }

    private void showAyDetay(AylikKazanc aylikKazanc) {
        StringBuilder detay = new StringBuilder();
        detay.append(aylikKazanc.getAyAdi()).append(" ").append(aylikKazanc.getYil()).append(" - KAZANÇ DETAYI\n\n");

        for (KazancKaydi kayit : aylikKazanc.getKazanclar()) {
            detay.append("• ").append(kayit.getKazancTuruAd()).append("\n");
            detay.append("  Tutar: ").append(String.format("%.2f TL", kayit.getTutar())).append("\n");
            detay.append("  Prime Tabi: ").append(String.format("%.2f TL", kayit.getPrimeTabiTutar())).append("\n\n");
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ay Detayı");
        alert.setHeaderText(null);
        alert.setContentText(detay.toString());
        alert.showAndWait();
    }

    @FXML
    private void hesaplaPek() {
        new Alert(Alert.AlertType.INFORMATION, "Hesaplama yapılacak! (Henüz tamamlanmadı)").showAndWait();
    }

    @FXML
    private void goToAnasayfa(ActionEvent event) {
        UstadimMainController mainController = HelloApplication.getMainController();
        if (mainController != null) {
            mainController.setCenter("AnasayfaView.fxml");
        }
    }

}