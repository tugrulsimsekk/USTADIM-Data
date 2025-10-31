package com.example.ustadim_yeni.model;

import java.time.LocalDate;

// Artık geleneksel bir Java Class'tır.
public class IpcHesaplamaKaydi {
    private final String kanunMaddeKodu;
    private final String kanunTuru;
    private final LocalDate fiilTarihi;
    private final double brütAsgariUcret;
    private final double hesaplananCeza;
    private final String ozelDetay;

    // KRİTİK EKSİK PARÇA: YAPICI METOT (CONSTRUCTOR)
    public IpcHesaplamaKaydi(String kanunMaddeKodu, String kanunTuru, LocalDate fiilTarihi, double brütAsgariUcret, double hesaplananCeza, String ozelDetay) {
        this.kanunMaddeKodu = kanunMaddeKodu;
        this.kanunTuru = kanunTuru;
        this.fiilTarihi = fiilTarihi;
        this.brütAsgariUcret = brütAsgariUcret;
        this.hesaplananCeza = hesaplananCeza;
        this.ozelDetay = ozelDetay;
    }

    // Tabloya bağlanmak ve Madde/Detay göstermek için GETTER METOTLARI
    public String getKanunMaddeKodu() { return kanunMaddeKodu; }
    public String getKanunTuru() { return kanunTuru; }
    // Tabloda tarih kolonunu doldurmak için bunu kullanıyoruz
    public LocalDate getFiilTarihi() { return fiilTarihi; }
    public double getBrütAsgariUcret() { return brütAsgariUcret; }
    public double getHesaplananCeza() { return hesaplananCeza; }
    public String getOzelDetay() { return ozelDetay; } // Tablo Detay Kolonunu Doldurur

    // Tabloda Tutar kolonu için özel formatlanmış metot
    public String getHesaplananCezaFormatted() {
        return String.format("%.2f TL", hesaplananCeza);
    }
}