package com.example.ustadim_yeni.model;

public record AsgariUcret(
        int yil,
        int ay,
        double brutAylikTutar
) {
    @Override
    public String toString() {
        return yil + "/" + ay + " - Brüt: " + brutAylikTutar;
    }

    public double tavanGunluk() {
        // Tavan = Brüt × 7.5 / 30
        return (brutAylikTutar * 7.5) / 30.0;
    }

    public double tabanGunluk() {
        // Taban = Brüt / 30
        return brutAylikTutar / 30.0;
    }
}