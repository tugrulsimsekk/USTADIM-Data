package com.example.ustadim_yeni.model;

import com.example.ustadim_yeni.util.JsonDataLoader;

public class KazancKaydi {
    private final KazancTuru kazancTuru;
    private final double tutar;
    private final Integer ekParam;
    private final int yil;
    private final int calismaGunu;

    public KazancKaydi(KazancTuru kazancTuru, double tutar, Integer ekParam, int yil, int calismaGunu) {
        this.kazancTuru = kazancTuru;
        this.tutar = tutar;
        this.ekParam = ekParam;
        this.yil = yil;
        this.calismaGunu = calismaGunu;
    }

    public String getKazancTuruAd() {
        return kazancTuru.ad();
    }

    public double getTutar() {
        return tutar;
    }

    public double getPrimeTabiTutar() {
        KazancTuru tur = getKazancTuru();

        // Prime tabi değilse 0
        if (tur.isPrimeTabiDegil()) {
            return 0.0;
        }

        // Tam prime tabi
        if (tur.isPrimeTabiTam()) {
            return tutar;
        }

        // Kısmi prime tabi - istisna hesapla
        if (tur.isPrimeTabiKismi()) {
            // Asgari ücreti getir
            AsgariUcret au = JsonDataLoader.getAsgariUcretByYil(yil);
            if (au == null) {
                return tutar;
            }

            double istisna = hesaplaIstisna(au);
            double primeTabi = tutar - istisna;

            return Math.max(0, primeTabi); // Negatif olamaz
        }

        return tutar;
    }

    private double hesaplaIstisna(AsgariUcret au) {
        KazancTuru tur = getKazancTuru();

        if (tur.istisnaOrani() == null) {
            return 0;
        }

        String istisnaTuru = tur.istisnaTuru();
        double oran = tur.istisnaOrani();

        if ("GUNLUK".equals(istisnaTuru)) {
            // YEMEK: Günlük AU × %6 × Fiilen Çalışılan Gün
            double gunlukAU = au.brutAylikTutar() / 30.0;

            // ekParam = yemek verilen gün sayısı (kullanıcı girdi)
            int yemekGunu = (ekParam != null && ekParam > 0) ? ekParam : calismaGunu;

            double istisna = gunlukAU * oran * yemekGunu;

            System.out.println("🍽️ YEMEK İSTİSNA HESABI:");
            System.out.println("  Günlük AU: " + gunlukAU);
            System.out.println("  Oran: %" + (oran * 100));
            System.out.println("  Yemek Günü: " + yemekGunu);
            System.out.println("  İstisna: " + istisna);

            return istisna;
        }
        else if ("AYLIK".equals(istisnaTuru)) {
            // ÇOCUK/AİLE/SAĞLIK: Aylık AU × Oran
            if ("COCUK".equals(tur.hesaplama())) {
                int cocukSayisi = (ekParam != null) ? Math.min(ekParam, 2) : 0;
                return au.brutAylikTutar() * oran * cocukSayisi;
            }
            return au.brutAylikTutar() * oran;
        }

        return 0;
    }

    public KazancTuru getKazancTuru() {
        return kazancTuru;
    }

    public Integer getEkParam() {
        return ekParam;
    }

    public int getYil() {
        return yil;
    }

    public int getCalismaGunu() {
        return calismaGunu;
    }
}