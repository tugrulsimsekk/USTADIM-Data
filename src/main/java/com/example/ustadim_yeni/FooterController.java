package com.example.ustadim_yeni;

import com.example.ustadim_yeni.model.IpcHesaplamaKaydi;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.util.Callback;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FooterController {

    // Statik erişim için
    private static FooterController instance;


    @FXML
    private TableColumn<IpcHesaplamaKaydi, String> ipcKolonKanun;
    @FXML
    private TableView<IpcHesaplamaKaydi> ipcTableView;
    // Kolonlar doğru tipte tanımlanmalıdır
    @FXML
    private TableColumn<IpcHesaplamaKaydi, String> ipcKolonMadde;
    @FXML
    private TableColumn<IpcHesaplamaKaydi, String> ipcKolonDetay;
    @FXML
    private TableColumn<IpcHesaplamaKaydi, String> ipcKolonTarih;
    @FXML
    private TableColumn<IpcHesaplamaKaydi, String> ipcKolonTutar;
    @FXML
    private TableColumn<IpcHesaplamaKaydi, Void> ipcKolonAksiyon; // Aksiyon Void tipindedir
    @FXML
    private Label ipcGenelToplamLabel;

    private ObservableList<IpcHesaplamaKaydi> ipcKayitlari = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        instance = this;
        ipcTableView.setItems(ipcKayitlari);

        // Kolonları modeldeki alanlara bağlama
        ipcKolonKanun.setCellValueFactory(new PropertyValueFactory<>("kanunTuru"));
        ipcKolonMadde.setCellValueFactory(new PropertyValueFactory<>("kanunMaddeKodu"));
        ipcKolonDetay.setCellValueFactory(new PropertyValueFactory<>("ozelDetay"));
        ipcKolonTutar.setCellValueFactory(new PropertyValueFactory<>("hesaplananCezaFormatted"));

        // TARİH KOLONUNU DÜZELTME
        ipcKolonTarih.setCellValueFactory(data -> {
            LocalDate tarih = data.getValue().getFiilTarihi();
            if (tarih == null) {
                return new SimpleStringProperty("-");
            }
            return new SimpleStringProperty(tarih.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        });

        // Silme Butonu Kolonu Tanımı
        Callback<TableColumn<IpcHesaplamaKaydi, Void>, TableCell<IpcHesaplamaKaydi, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<IpcHesaplamaKaydi, Void> call(final TableColumn<IpcHesaplamaKaydi, Void> param) {
                final TableCell<IpcHesaplamaKaydi, Void> cell = new TableCell<>() {

                    private final Button btn = new Button("Sil");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            IpcHesaplamaKaydi data = getTableView().getItems().get(getIndex());
                            ipcKayitlari.remove(data);
                            updateIpcGenelToplam();
                            // Alert'i kaldırdık, çok sıkıcı oluyor
                        });
                        btn.setStyle("-fx-background-color: #ffcccc; -fx-text-fill: red; -fx-padding: 3 6;");
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        ipcKolonAksiyon.setCellFactory(cellFactory);
        ipcTableView.setPlaceholder(new Label("Henüz ceza kaydı eklenmemiştir."));
        updateIpcGenelToplam();
    }

    public static FooterController getInstance() {
        return instance;
    }

    public void addIpcKaydi(IpcHesaplamaKaydi kayit) {
        ipcKayitlari.add(kayit);
        updateIpcGenelToplam();
    }

    private void updateIpcGenelToplam() {
        double toplam = ipcKayitlari.stream()
                // DÜZELTİLDİ: 'hesaplananCeza()' yerine 'getHesaplananCeza()'
                .mapToDouble(IpcHesaplamaKaydi::getHesaplananCeza)
                .sum();
        ipcGenelToplamLabel.setText(String.format("%.2f TL", toplam));
    }

    @FXML
    private void temizleIpcTablosu(ActionEvent event) {
        ipcKayitlari.clear();
        updateIpcGenelToplam();
    }

    @FXML
    private void temizlePekTablosu(ActionEvent event) {
        // PEK temizleme mantığı buraya gelecek
    }
    // ===== NAVIGATION METHODS =====

    @FXML
    private void goToAnasayfa(ActionEvent event) {
        UstadimMainController mainController = HelloApplication.getMainController();
        if (mainController != null) {
            mainController.setCenter("AnasayfaView.fxml");
        }
    }

    @FXML
    private void goToIpc5510(ActionEvent event) {
        UstadimMainController mainController = HelloApplication.getMainController();
        if (mainController != null) {
            mainController.setCenter("Ipc5510MainView.fxml");
        }
    }

    @FXML
    private void goToIpc6331(ActionEvent event) {
        UstadimMainController mainController = HelloApplication.getMainController();
        if (mainController != null) {
            mainController.setCenter("Ipc6331MainView.fxml");
        }
    }

    @FXML
    private void goToMevzuat(ActionEvent event) {
        UstadimMainController mainController = HelloApplication.getMainController();
        if (mainController != null) {
            mainController.setCenter("MevzuatView.fxml");
        }
    }

    @FXML
    private void goToPek(ActionEvent event) {
        UstadimMainController mainController = HelloApplication.getMainController();
        if (mainController != null) {
            mainController.setCenter("PekMainView.fxml");
        }
    }

    @FXML
    private void goToIpc(ActionEvent event) {
        UstadimMainController mainController = HelloApplication.getMainController();
        if (mainController != null) {
            mainController.setCenter("IpcView.fxml");
        }
    }
}