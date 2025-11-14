package com.example.ustadim_yeni.model;

public record KazancTuru(
        String kod,
        String ad,
        Object primeTabi, // boolean veya "KISMI"
        String hesaplama,
        String formul,
        String istisnaTuru,
        Double istisnaOrani,
        Integer maxCocuk
) {
    @Override
    public String toString() {
        return ad;
    }

    public boolean isPrimeTabiTam() {
        return primeTabi instanceof Boolean && (Boolean) primeTabi;
    }

    public boolean isPrimeTabiKismi() {
        return "KISMI".equals(primeTabi);
    }

    public boolean isPrimeTabiDegil() {
        return primeTabi instanceof Boolean && !(Boolean) primeTabi;
    }
}