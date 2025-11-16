package com.example.ustadim_yeni.model;

import java.util.ArrayList;
import java.util.List;
import com.example.ustadim_yeni.model.KazancKaydi;

public class AylikKazanc {
    private final int yil;
    private final int ay;
    private final List<KazancKaydi> kazanclar;
    private double toplamPrimeEsas;

    public AylikKazanc(int yil, int ay) {
        this.yil = yil;
        this.ay = ay;
        this.kazanclar = new ArrayList<>();
        this.toplamPrimeEsas = 0;
    }

    public void addKazanc(KazancKaydi kayit) {
        kazanclar.add(kayit);
    }

    public int getYil() {
        return yil;
    }

    public int getAy() {
        return ay;
    }

    public List<KazancKaydi> getKazanclar() {
        return kazanclar;
    }

    public double getToplamPrimeEsas() {
        return toplamPrimeEsas;
    }

    public void setToplamPrimeEsas(double toplamPrimeEsas) {
        this.toplamPrimeEsas = toplamPrimeEsas;
    }

    public String getAyAdi() {
        String[] aylar = {"Ocak", "Şubat", "Mart", "Nisan", "Mayıs", "Haziran",
                "Temmuz", "Ağustos", "Eylül", "Ekim", "Kasım", "Aralık"};
        return aylar[ay - 1];
    }
}