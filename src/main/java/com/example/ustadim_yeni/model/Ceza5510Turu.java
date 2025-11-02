package com.example.ustadim_yeni.model;

// Excel verilerini tam olarak temsil eden sınıf


// Bu, Excel'deki yeni sütunları içeren tam modeldir.
public record Ceza5510Turu(
        String kod,              // a.1., b.3., vb.
        String kanundakiYeri,    // Madde 102 (a) gibi
        String aciklama,         // Uzun ceza açıklaması
        String formKriteri,      // "Sigortalı sayısı kadar", "Her bir ay için" gibi
        double katsayi,          // Uygulanacak temel AU kat sayısı
        double altSinirKatsayi,  // AU alt sınırı
        double ustSinirKatsayi   // AU üst sınırı
) {
    @Override
    public String toString() {
        return kod + " - " + aciklama;
    }
}