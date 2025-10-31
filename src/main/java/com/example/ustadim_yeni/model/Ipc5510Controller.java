package com.example.ustadim_yeni;

import com.example.ustadim_yeni.model.Ceza5510Turu;
import com.example.ustadim_yeni.model.IpcHesaplamaKaydi;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import java.util.Arrays;
import java.util.List;


// Ipc5510Controller.java dosyasının üst kısmına, diğer import'lardan sonra ekleyin

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import java.time.LocalDate; // LocalDate sınıfı için
import com.example.ustadim_yeni.util.AsgariUcretVerileri; // Yardımcı sınıfa erişim için

public class Ipc5510Controller {

    private IpcController parentController;
    public void setParentController(IpcController controller) {
        this.parentController = controller;
    }
    @FXML
    private DatePicker tarihSecici5510;

    @FXML
    private ComboBox<Ceza5510Turu> cezaTuruSecici5510;


    // FXML yüklendiğinde çalışacak metot
    @FXML
    public void initialize() {
        // 1. ComboBox'a veri kaynağını (ObservableList) bağla
        cezaTuruSecici5510.setItems(FXCollections.observableArrayList(getCezaListesi()));

    }
// Ipc5510Controller.java içinde, initialize metodundan sonra bu kodları ekleyin:

    @FXML
    private VBox dinamikGirisAlani5510; // FXML'den yakaladık

    // Kanun maddesi seçildiğinde çalışacak metot
    @FXML
    private void cezaSecildi() {
        Ceza5510Turu secilenCeza = cezaTuruSecici5510.getSelectionModel().getSelectedItem();

        if (secilenCeza == null) {
            dinamikGirisAlani5510.getChildren().clear();
            return;
        }

        // Örnek: A.1. maddesine özel formu yükleme
        if (secilenCeza.kod().equals("a.1.")) {
            loadA1Formu();
        } else {
            // Diğer cezalar seçilirse şimdilik boşaltalım
            dinamikGirisAlani5510.getChildren().clear();
            dinamikGirisAlani5510.getChildren().add(new Label(secilenCeza.aciklama() + " için giriş formu yakında eklenecektir."));
        }
    }

    private void loadA1Formu() {
        // Formu temizle
        dinamikGirisAlani5510.getChildren().clear();

        // 1. Sigortalı Sayısı Girişi
        TextField sigortaliSayisiInput = new TextField();
        sigortaliSayisiInput.setPromptText("Süresinde bildirilmeyen sigortalı sayısı (Örn: 1)");

        // 2. Ceza Hesapla Butonu
        Button hesaplaButton = new Button("Ceza Miktarını Hesapla");
        hesaplaButton.setOnAction(e -> hesaplaA1(sigortaliSayisiInput.getText()));

        // Alanları panele ekle
        dinamikGirisAlani5510.getChildren().addAll(
                new Label("Gerekli Girişler:"),
                sigortaliSayisiInput,
                hesaplaButton
        );
    }

// Ipc5510Controller.java içinde:

// Ipc5510Controller.java içinde:

    private void hesaplaA1(String sigortaliSayisiStr) {
        LocalDate fiilTarihi = tarihSecici5510.getValue();

        // 1. Giriş Kontrolleri
        if (fiilTarihi == null) {
            new Alert(Alert.AlertType.ERROR, "Lütfen fiilin işlendiği tarihi seçiniz.").showAndWait();
            return;
        }

        double brütAsgariUcret = AsgariUcretVerileri.getUcretByTarih(fiilTarihi);
        if (brütAsgariUcret <= 0.0) {
            new Alert(Alert.AlertType.WARNING, "Seçilen tarih için Asgari Ücret bilgisi bulunamadı. Lütfen veri listesini güncelleyin.").showAndWait();
            return;
        }

        try {
            int sigortaliSayisi = Integer.parseInt(sigortaliSayisiStr);
            double cezaKatsayisi = 2.0;
            double toplamCeza = sigortaliSayisi * brütAsgariUcret * cezaKatsayisi;

            // 2. Yeni Kaydı Oluşturma
            IpcHesaplamaKaydi yeniKayit = new IpcHesaplamaKaydi(
                    cezaTuruSecici5510.getValue().kod(), // DÜZELTİLDİ: 'kod()' yerine 'getKod()'
                    "5510",
                    fiilTarihi,
                    brütAsgariUcret,
                    toplamCeza,
                    "Bildirilmeyen Sigortalı: " + sigortaliSayisi
            );


            // 3. Footer'a Statik Erişim ve Kaydı Ekleme (Tek ve Doğru Yol)
            FooterController footer = FooterController.getInstance();

            if (footer != null) {
                footer.addIpcKaydi(yeniKayit);

                // Başarılı Ekleme ve Hesaplama Sonucunu Tek Bir Alert'te Göster
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Ceza Hesaplama Başarılı");
                successAlert.setHeaderText("Kanun 5510, Madde " + yeniKayit.getKanunMaddeKodu());
                successAlert.setContentText(String.format(
                        "Hesaplanan Ceza Kaydı Tabloya Eklendi.\n" +
                                "Kullanılan Brüt AU (%s): %.2f TL\n" +
                                "Toplam Ceza Miktarı: %.2f TL",
                        fiilTarihi.getYear(), brütAsgariUcret, toplamCeza));
                successAlert.showAndWait();

            } else {
                // Footer bağlantısı kurulamadığında kullanıcıya hata vermeyi durduruyoruz.
                System.err.println("Hata: FooterController henüz initialize edilmedi. Kayıt eklenemedi.");
            }

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Lütfen geçerli bir sayı giriniz.").showAndWait();
        }

    }
    // Ceza türü listesini oluşturan metot
    // Ipc5510Controller.java içinde:

    private List<Ceza5510Turu> getCezaListesi() {
        return Arrays.asList(
                // a. Sigortalı İşe Giriş Bildirgesi
                new Ceza5510Turu("a.1.", "Sigortalı işe giriş bildirgesinin süresinde verilmemesi"),
                new Ceza5510Turu("a.2.", "Sigortalı işe giriş bildirgesinin süresinde verilmemesi (Müfettiş tespiti)"),
                new Ceza5510Turu("a.3.", "Sigortalı işe giriş bildirgesinin süresinde verilmemesinin 1 yıl içinde tekrarı"),

                // b. İşyeri Tescil Bildirgesi
                new Ceza5510Turu("b.1.", "İş yeri tescil bildirgesinin geç verilmesi (Kamu ve Bilanço esası)"),
                new Ceza5510Turu("b.2.", "İş yeri tescil bildirgesinin geç verilmesi (Diğer defterler)"),
                new Ceza5510Turu("b.3.", "İş yeri tescil bildirgesinin geç verilmesi (Defter tutmak zorunda olmayanlar)"),

                // c. Aylık Prim ve Hizmet Belgesi (APHB / M&PHB)
                new Ceza5510Turu("c.1.", "Aylık prim ve hizmet belgesi, muhtasar ve prim hizmet beyannamesi, eksik gün nedenleri ile bu nedenleri ispatlayan belgeler, otuz günden az çalıştığını gösteren bilgi ve belgelerin verilmemesi (Asıl)"),
                new Ceza5510Turu("c.2.", "Aylık prim ve hizmet belgesi, muhtasar ve prim hizmet beyannamesi, eksik gün nedenleri ile bu nedenleri ispatlayan belgeler, otuz günden az çalıştığını gösteren bilgi ve belgelerin verilmemesi (Ek)"),
                new Ceza5510Turu("c.3.", "Aylık prim ve hizmet belgesi, muhtasar ve prim hizmet beyannamesi, eksik gün nedenleri ile bu nedenleri ispatlayan belgeler, otuz günden az çalıştığını gösteren bilgi ve belgelerin verilmemesi  (Resen Düzenleme)"),
                new Ceza5510Turu("c.4.", "Aylık prim ve hizmet belgesi, muhtasar ve prim hizmet beyannamesi, eksik gün nedenleri ile bu nedenleri ispatlayan belgeler, otuz günden az çalıştığını gösteren bilgi ve belgelerin verilmemesi  (Müfettiş Tespiti)"),

                // d. Eksik İşçilik
                new Ceza5510Turu("d.", "Eksik işçilik tutarının mal edildiği her bir ay için"),

                // e. Defter ve Belge İbrazı/Geçersizliği
                new Ceza5510Turu("e.1.", "Kayıt/belge ibraz edilmemesi (Bilanço esası)"),
                new Ceza5510Turu("e.2.", "Kayıt/belge ibraz edilmemesi (Diğer defterler)"),
                new Ceza5510Turu("e.3.", "Kayıt/belge ibraz edilmemesi (Defter tutmak zorunda olmayanlar)"),
                new Ceza5510Turu("e.4.", "Defter geçersizliği"),
                new Ceza5510Turu("e.5.", "Bordro geçersizliği"),

                // f. Asgari İşçilik
                new Ceza5510Turu("f.", "Asgari işçilik uygulaması ve uzlaşma"),

                new Ceza5510Turu("h.", "Ticaret sicili memurlukları ile kurum ve kuruluşlar"),

                new Ceza5510Turu("ı.1", "Görevini yerine getirene engel olma"),

                new Ceza5510Turu("ı.2","Görevini yerine getirene cebir ve tehdit yapılması"),

                new Ceza5510Turu("i.1", "100’üncü madde kapsamında istenen belgenin ibraz edilmemesi"),

                new Ceza5510Turu("i.2", "100’üncü madde kapsamında istenen belgenin geç ibraz edilmesi"),

                new Ceza5510Turu("i.3", "İş göremezlik ödeneği için belgenin Kurum'a geç verilmesi"),

                new Ceza5510Turu("i.4", "İş göremezlik ödeneği için belgenin Kurum'a verilmemesi"),

                // j. İşten Ayrılış
                new Ceza5510Turu("j.", "Sigortalı işten ayrılış bildirgesinin verilmemesi veya geç verilmesi"),

                // m. Muhtasar ve APHB (Günü ve Primi Eksik)
                new Ceza5510Turu("m.1.", "Muhtasar ve APHB: Günü ve primi eksik (Asıl)"),
                new Ceza5510Turu("m.2.", "Muhtasar ve APHB: Günü ve primi eksik – (Ek)"),
                new Ceza5510Turu("m.3.", "Muhtasar ve APHB: Günü ve primi eksik – (Resen Düzenleme)"),
                new Ceza5510Turu("m.4.a", "Muhtasar ve APHB: Müfettiş tespiti – Bilanço esası"),
                new Ceza5510Turu("m.4.b", "Muhtasar ve APHB: Müfettiş tespiti – Diğer defterler"),
                new Ceza5510Turu("m.4.c", "Muhtasar ve APHB: Müfettiş tespiti – Defter tutmak zorunda olmayanlar"),
                new Ceza5510Turu("m.4.ç", "Muhtasar ve APHB: Müfettiş tespiti – Günü tam ama primi eksik"),

                // n. Diğer
                new Ceza5510Turu("n.", "Meslek kodlarının hatalı/yanlış bildirilmesi")
        );
    }
}