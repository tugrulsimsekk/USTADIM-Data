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
import java.util.List;


public class PekController {

    @FXML private ComboBox<String> hesaplamaTipiCombo;
    @FXML private ComboBox<PrimOranlari> sigortalilikStatuCombo;
    @FXML private ComboBox<Integer> yilCombo;
    @FXML private ComboBox<String> ayCombo;
    @FXML private TextField calismaGunuInput;

    @FXML private ComboBox<KazancTuru> kazancTuruCombo;
    @FXML private TextField kazancTutarInput;
   // @FXML private HBox ekParametreBox;
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

    private ObservableList<KazancKaydi> kazanclar = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Hesaplama Tipi
        hesaplamaTipiCombo.setItems(FXCollections.observableArrayList(
                "Basit Hesaplama",
                "Detaylı Hesaplama"
        ));
        hesaplamaTipiCombo.setValue("Basit Hesaplama");

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

// Hesaplama tipi değiştiğinde kümülatif paneli göster/gizle
        hesaplamaTipiCombo.setOnAction(e -> {
            boolean detayli = "Detaylı Hesaplama".equals(hesaplamaTipiCombo.getValue());
            kumulatifPanel.setVisible(detayli);
            kumulatifPanel.setManaged(detayli);
        });
        // Sil butonu kolonu
        setupSilColumn();
        // Kümülatif panel için ayları oluştur
        createAylikKazancInputs();
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
// Ek parametre kontrolü
            Integer ekParam = null;
            if (ekParametreInput.isVisible()) {
                String ekParamText = ekParametreInput.getText().trim();
                if (!ekParamText.isEmpty()) {
                    ekParam = Integer.parseInt(ekParamText);
                }
            }

            KazancKaydi kayit = new KazancKaydi(secilenTur, tutar, ekParam);
            kazanclar.add(kayit);

            // Temizle
            kazancTutarInput.clear();
            ekParametreInput.clear();

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Lütfen geçerli sayısal değer girin!").showAndWait();
        }
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

    // İç sınıf: Kazanç Kaydı
    public static class KazancKaydi {
        private final KazancTuru kazancTuru;
        private final double tutar;
        private final Integer ekParam;

        public KazancKaydi(KazancTuru kazancTuru, double tutar, Integer ekParam) {
            this.kazancTuru = kazancTuru;
            this.tutar = tutar;
            this.ekParam = ekParam;
        }

        public String getKazancTuruAd() {
            return kazancTuru.ad();
        }

        public double getTutar() {
            return tutar;
        }

        public double getPrimeTabiTutar() {
            // Şimdilik tutar döndür, sonra hesaplayacağız
            return tutar;
        }

        public KazancTuru getKazancTuru() {
            return kazancTuru;
        }

        public Integer getEkParam() {
            return ekParam;
        }
    }
    private void createAylikKazancInputs() {
        String[] aylar = {"Ocak", "Şubat", "Mart", "Nisan", "Mayıs", "Haziran",
                "Temmuz", "Ağustos", "Eylül", "Ekim", "Kasım", "Aralık"};

        for (String ay : aylar) {
            HBox ayBox = new HBox(8);
            ayBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

            Label ayLabel = new Label(ay + ":");
            ayLabel.setMinWidth(70);
            ayLabel.setStyle("-fx-font-weight: bold;");

            TextField ayInput = new TextField();
            ayInput.setPromptText("0.00");
            ayInput.setPrefWidth(150);

            ayBox.getChildren().addAll(ayLabel, ayInput);
            aylikKazanclarBox.getChildren().add(ayBox);
        }
    }
}