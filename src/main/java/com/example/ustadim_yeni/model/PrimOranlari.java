package com.example.ustadim_yeni.model;

public record PrimOranlari(
        String kod,
        String aciklama,
        double uzunVadeli,
        double kisaVadeli,
        double gss,
        double issizlik,
        Double sgdpPrimi,
        Double isKazasi,
        double toplamCalisanPayi,
        double tavanKatsayi,
        double tabanKatsayi,
        String ozelDurum,
        Double aylikSabitOran
) {
    @Override
    public String toString() {
        return aciklama;
    }
}