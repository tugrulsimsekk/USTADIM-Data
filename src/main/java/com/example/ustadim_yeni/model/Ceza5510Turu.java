package com.example.ustadim_yeni.model;

// Ceza kodu (A1, B3, C1 vb.) ve açıklamasını tutar
public record Ceza5510Turu(String kod, String aciklama) {
    @Override
    public String toString() {
        return kod + " " + aciklama; // ComboBox'ta görünecek format
    }
}