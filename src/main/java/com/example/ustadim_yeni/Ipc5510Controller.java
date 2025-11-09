package com.example.ustadim_yeni;

import com.example.ustadim_yeni.model.Ceza5510Turu;
import com.example.ustadim_yeni.model.IpcHesaplamaKaydi;
import com.example.ustadim_yeni.util.AsgariUcretVerileri;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.time.LocalDate;
import java.util.List;

import com.example.ustadim_yeni.util.JsonDataLoader;

public class Ipc5510Controller {

    // --- FXML BİLEŞENLERİ ---
    @FXML
    private DatePicker tarihSecici5510;
    @FXML
    private ComboBox<Ceza5510Turu> cezaTuruSecici5510;
    @FXML
    private VBox dinamikGirisAlani5510;

    // --- INITIALIZE ---
    @FXML
    public void initialize() {
        List<Ceza5510Turu> cezalar = JsonDataLoader.loadCezalar();
        cezaTuruSecici5510.setItems(FXCollections.observableArrayList(cezalar));
    }

    // --- AKSİYONLAR ---
    @FXML
    private void cezaSecildi() {
        Ceza5510Turu secilenCeza = cezaTuruSecici5510.getSelectionModel().getSelectedItem();

        if (secilenCeza == null) {
            dinamikGirisAlani5510.getChildren().clear();
            return;
        }

        // --- FORM YÜKLEME MANTIĞI (A.3. BASİTLEŞTİRİLDİ) ---
        switch (secilenCeza.kod()) {
            case "a.1.":
            case "a.2.":
            case "a.3.": // Artık basit formu kullanacak
            case "n.":
            case "c.1.": case "c.2.": case "c.3.": case "c.4.":
            case "m.1.": case "m.2.": case "m.3.": case "m.4.a": case "m.4.b": case "m.4.c":
                loadSayiEsasliFormu(secilenCeza);
                break;
            case "m.4.ç":
                loadM4cFormu(secilenCeza);
                break;
            case "j.":  // YENİ: j'yi buraya taşı
                loadJBendiFormu(secilenCeza);
                break;
            case "d.":
                loadAyEsasliFormu(secilenCeza);
                break;
            case "b.1.": case "b.2.": case "b.3.":
            case "e.1.": case "e.2.": case "e.3.": case "e.4.": case "e.5.":
            case "f.": case "h.": case "ı.1": case "ı.2": case "i.1": case "i.2":

                loadSabitFormu(secilenCeza);
                break;
            case "i.3": case "i.4":  //
                loadIGOFormu(secilenCeza);
                break;
            default:
                dinamikGirisAlani5510.getChildren().clear();
                dinamikGirisAlani5510.getChildren().add(new Label(secilenCeza.aciklama() + " için form yakında eklenecektir."));
        }
    }

    private void loadSayiEsasliFormu(Ceza5510Turu ceza) {
        dinamikGirisAlani5510.getChildren().clear();
        TextField sayiInput = new TextField();
        sayiInput.setPromptText("Sigortalı/Belge Adedi (Örn: 1)");

        Button hesaplaButton = new Button("Cezayı Hesapla (" + ceza.kod() + ")");
        // Hesaplama metodu artık sadece ceza ve sayiStr alacak
        hesaplaButton.setOnAction(e -> hesaplaGenel(ceza, sayiInput.getText()));

        dinamikGirisAlani5510.getChildren().addAll(
                new Label("Gerekli Girişler (Madde " + ceza.kod() + "):"),
                sayiInput,
                hesaplaButton
        );
    }

    // --- LOAD AY ESASLI VE SABİT METOTLARI AYNI KALDI ---

    private void loadAyEsasliFormu(Ceza5510Turu ceza) {
        dinamikGirisAlani5510.getChildren().clear();
        TextField aySayisiInput = new TextField();
        aySayisiInput.setPromptText("Ay Sayısı (Örn: 5 ay için)");

        Button hesaplaButton = new Button("Cezayı Hesapla (" + ceza.kod() + ")");
        hesaplaButton.setOnAction(e -> hesaplaGenel(ceza, aySayisiInput.getText()));

        dinamikGirisAlani5510.getChildren().addAll(
                new Label("Gerekli Girişler (Ay Esaslı):"),
                aySayisiInput,
                hesaplaButton
        );
    }

    private void loadSabitFormu(Ceza5510Turu ceza) {
        dinamikGirisAlani5510.getChildren().clear();

        Button hesaplaButton = new Button("Cezayı Hesapla (" + ceza.kod() + ")");
        // Sabit olduğu için girişi 1 kabul ediyoruz
        hesaplaButton.setOnAction(e -> hesaplaGenel(ceza, "1"));

        dinamikGirisAlani5510.getChildren().addAll(
                new Label("Bu ceza (" + ceza.aciklama() + ") sabit AU katıdır. Hesaplamak için tıklayın:"),
                hesaplaButton
        );
    }
    private void loadJBendiFormu(Ceza5510Turu ceza) {
        dinamikGirisAlani5510.getChildren().clear();

        TextField sigortaliSayisi = new TextField();
        sigortaliSayisi.setPromptText("Sigortalı Sayısı");

        ComboBox<String> defterTuru = new ComboBox<>();
        defterTuru.setItems(FXCollections.observableArrayList(
                "Bilanço Esası (12 AU)",
                "Diğer Defterler (6 AU)",
                "Defter Tutmayan (3 AU)"
        ));
        defterTuru.setPromptText("Defter Türünü Seçin");

        Button hesaplaBtn = new Button("Hesapla (j)");
        hesaplaBtn.setOnAction(e -> hesaplaJBendi(ceza, sigortaliSayisi.getText(), defterTuru.getValue()));

        dinamikGirisAlani5510.getChildren().addAll(
                new Label("j Bendi - İşten Ayrılış Bildirgesi (Üst Sınır: e maddesi)"),
                sigortaliSayisi,
                defterTuru,
                hesaplaBtn
        );
    }

    private void hesaplaJBendi(Ceza5510Turu ceza, String sayiStr, String defterTuru) {
        LocalDate fiilTarihi = tarihSecici5510.getValue();

        if (fiilTarihi == null) {
            new Alert(Alert.AlertType.ERROR, "Lütfen tarihi seçiniz.").showAndWait();
            return;
        }

        if (defterTuru == null || defterTuru.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Lütfen defter türünü seçiniz.").showAndWait();
            return;
        }

        double brütAsgariUcret = AsgariUcretVerileri.getUcretByTarih(fiilTarihi);
        if (brütAsgariUcret < 0) {
            new Alert(Alert.AlertType.WARNING,
                    "⚠️ Seçilen tarih için henüz asgari ücret verisi oluşturulmamıştır.").showAndWait();
            return;
        }

        try {
            int sigortaliSayisi = Integer.parseInt(sayiStr);

            // Temel hesaplama: 0.1 × AU × Sigortalı sayısı
            double temelCeza = sigortaliSayisi * brütAsgariUcret * 0.1;

            // Üst sınır: e maddesine göre
            double ustSinir = switch(defterTuru) {
                case "Bilanço Esası (12 AU)" -> 12.0 * brütAsgariUcret;
                case "Diğer Defterler (6 AU)" -> 6.0 * brütAsgariUcret;
                case "Defter Tutmayan (3 AU)" -> 3.0 * brütAsgariUcret;
                default -> 12.0 * brütAsgariUcret;
            };

            double toplamCeza = Math.min(temelCeza, ustSinir);

            IpcHesaplamaKaydi yeniKayit = new IpcHesaplamaKaydi(
                    ceza.kod(),
                    "5510",
                    fiilTarihi,
                    brütAsgariUcret,
                    toplamCeza,
                    "Sigortalı: " + sigortaliSayisi + ", Üst Sınır: " + defterTuru
            );

            FooterController footer = FooterController.getInstance();
            if (footer != null) {
                footer.addIpcKaydi(yeniKayit);
                new Alert(Alert.AlertType.INFORMATION,
                        String.format("j Bendi Eklendi: %.2f TL (Üst Sınır: %.2f TL)", toplamCeza, ustSinir))
                        .showAndWait();
            }

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Lütfen geçerli bir sayı giriniz.").showAndWait();
        }
    }

    private void loadIGOFormu(Ceza5510Turu ceza) {
        dinamikGirisAlani5510.getChildren().clear();

        TextField sigortaliSayisi = new TextField();
        sigortaliSayisi.setPromptText("İGÖ alan sigortalı sayısı");

        Button hesaplaBtn = new Button("Hesapla (" + ceza.kod() + ")");
        hesaplaBtn.setOnAction(e -> hesaplaGenel(ceza, sigortaliSayisi.getText()));

        dinamikGirisAlani5510.getChildren().addAll(
                new Label(ceza.aciklama() + " - Sigortalı başına"),
                sigortaliSayisi,
                hesaplaBtn
        );
    }



    private void loadM4cFormu(Ceza5510Turu ceza) {
        dinamikGirisAlani5510.getChildren().clear();

        TextField eksikKazancInput = new TextField();
        eksikKazancInput.setPromptText("Eksik Bildirilen Prime Esas Kazanç (TL)");

        Button hesaplaBtn = new Button("Hesapla (m.4.ç)");
        hesaplaBtn.setOnAction(e -> hesaplaM4c(ceza, eksikKazancInput.getText()));

        dinamikGirisAlani5510.getChildren().addAll(
                new Label("m.4.ç - Prime Esas Kazanç Eksikliği"),
                new Label("Eksik bildirilen tutarı giriniz (Alt: AU/10, Üst: AU×2):"),
                eksikKazancInput,
                hesaplaBtn
        );
    }

    private void hesaplaM4c(Ceza5510Turu ceza, String eksikKazancStr) {
        LocalDate fiilTarihi = tarihSecici5510.getValue();

        if (fiilTarihi == null) {
            new Alert(Alert.AlertType.ERROR, "Lütfen tarihi seçiniz.").showAndWait();
            return;
        }

        double brütAsgariUcret = AsgariUcretVerileri.getUcretByTarih(fiilTarihi);
        if (brütAsgariUcret <= 0.0) {
            new Alert(Alert.AlertType.WARNING, "Seçilen tarih için AU bilgisi yok.").showAndWait();
            return;
        }

        // Tarih kontrolü
        LocalDate kritikTarih = LocalDate.of(2016, 7, 15);
        if (fiilTarihi.isBefore(kritikTarih)) {
            new Alert(Alert.AlertType.ERROR,
                    "UYARI: 15/7/2016 öncesi fiiller için 'm' bendi yerine 'c' bendi uygulanır!").showAndWait();
            return;
        }

        try {
            double eksikKazancTutari = Double.parseDouble(eksikKazancStr);

            if (eksikKazancTutari <= 0) {
                new Alert(Alert.AlertType.ERROR, "Eksik kazanç tutarı 0'dan büyük olmalıdır.").showAndWait();
                return;
            }

            // ✅ DOĞRU: Eksik kazanç tutarı direkt ceza tutarıdır
            double hesaplananCeza = eksikKazancTutari;

            // ✅ Sınırlar: AU/10 ile AU×2
            double altSinir = brütAsgariUcret / 10.0;      // AU/10
            double ustSinir = brütAsgariUcret * 2.0;       // AU×2

            double toplamCeza = Math.min(Math.max(hesaplananCeza, altSinir), ustSinir);

            IpcHesaplamaKaydi yeniKayit = new IpcHesaplamaKaydi(
                    ceza.kod(),
                    "5510",
                    fiilTarihi,
                    brütAsgariUcret,
                    toplamCeza,
                    "Eksik Kazanç: " + String.format("%.2f TL", eksikKazancTutari)
            );

            FooterController footer = FooterController.getInstance();
            if (footer != null) {
                footer.addIpcKaydi(yeniKayit);
                new Alert(Alert.AlertType.INFORMATION,
                        String.format("m.4.ç Eklendi: %.2f TL\n" +
                                        "Eksik Kazanç: %.2f TL\n" +
                                        "Sınırlar: %.2f - %.2f TL (AU/10 - AU×2)",
                                toplamCeza, eksikKazancTutari, altSinir, ustSinir))
                        .showAndWait();
            }

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Lütfen geçerli bir tutar giriniz.").showAndWait();
        }
    }
    // --- GENEL HESAPLAMA MANTIĞI (Tek Metot) ---
    private void hesaplaGenel(Ceza5510Turu ceza, String sayiStr) {
        LocalDate fiilTarihi = tarihSecici5510.getValue();

        if (fiilTarihi == null) {
            new Alert(Alert.AlertType.ERROR, "Lütfen tarihi seçiniz.").showAndWait();
            return;
        }

        double brütAsgariUcret = AsgariUcretVerileri.getUcretByTarih(fiilTarihi);
        if (brütAsgariUcret <= 0.0) {
            new Alert(Alert.AlertType.WARNING, "Seçilen tarih için AU bilgisi yok.").showAndWait();
            return;
        }
        // TARİH KONTROLÜ: c ve m bentleri için
        LocalDate kritikTarih = LocalDate.of(2016, 7, 15);

        if (ceza.kod().startsWith("c.") && fiilTarihi.isAfter(kritikTarih)) {
            new Alert(Alert.AlertType.ERROR,
                    "UYARI: 15/07/2016 sonrası fiiller için 'c' bendi yerine 'm' bendi uygulanır!\n" +
                            "Lütfen ilgili 'm' bendini seçiniz.").showAndWait();
            return;
        }

        if (ceza.kod().startsWith("m.") && fiilTarihi.isBefore(kritikTarih)) {
            new Alert(Alert.AlertType.ERROR,
                    "UYARI: 15/07/2016 öncesi fiiller için 'm' bendi yerine 'c' bendi uygulanır!\n" +
                            "Lütfen ilgili 'c' bendini seçiniz.").showAndWait();
            return;
        }

        try {
            int sayiGiris = Integer.parseInt(sayiStr);
            double temelCezaKatsayi = ceza.katsayi(); // Katsayı modelden geliyor

            // TEMEL HESAPLAMA (Giriş sayısı * AU * Katsayı)
            double hesaplananTutar = sayiGiris * brütAsgariUcret * temelCezaKatsayi;

            // ALT/ÜST SINIR UYGULAMASI
            double altSinir = brütAsgariUcret * ceza.altSinirKatsayi();
            double ustSinir = brütAsgariUcret * ceza.ustSinirKatsayi();

            // Sonuç, Alt ve Üst Sınır arasında kalmalıdır.
            double toplamCeza = Math.min(Math.max(hesaplananTutar, altSinir), ustSinir);


            // 2. Yeni Kaydı Oluşturma
            IpcHesaplamaKaydi yeniKayit = new IpcHesaplamaKaydi(
                    ceza.kod(),
                    "5510",
                    fiilTarihi,
                    brütAsgariUcret,
                    toplamCeza,
                    "Adet: " + sayiGiris + " (" + ceza.formKriteri() + ")" // Detay bilgisini güncelledik
            );

            // Footer'a Ekleme
            FooterController footer = FooterController.getInstance();

            if (footer != null) {
                footer.addIpcKaydi(yeniKayit);
                new Alert(Alert.AlertType.INFORMATION, String.format("Ceza Kaydı Eklendi: %.2f TL", toplamCeza)).showAndWait();
            }

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Lütfen geçerli bir sayı giriniz.").showAndWait();
        }
    }
}