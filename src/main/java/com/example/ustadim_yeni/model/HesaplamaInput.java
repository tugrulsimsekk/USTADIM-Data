package com.example.ustadim_yeni.model;

public class HesaplamaInput {
    private String sigortalılikStatusu; // Belge No: "1", "2", "21", vb.
    private int yil;
    private int ay;
    private String hesaplamaModu; // "BRUT_NET" veya "NET_BRUT"
    private double tutar;
    private int calismaGunu;

    public HesaplamaInput(String sigortalılikStatusu, int yil, int ay,
                          String hesaplamaModu, double tutar, int calismaGunu) {
        this.sigortalılikStatusu = sigortalılikStatusu;
        this.yil = yil;
        this.ay = ay;
        this.hesaplamaModu = hesaplamaModu;
        this.tutar = tutar;
        this.calismaGunu = calismaGunu;
    }

    // Getters
    public String getSigortalılikStatusu() { return sigortalılikStatusu; }
    public int getYil() { return yil; }
    public int getAy() { return ay; }
    public String getHesaplamaModu() { return hesaplamaModu; }
    public double getTutar() { return tutar; }
    public int getCalismaGunu() { return calismaGunu; }
}