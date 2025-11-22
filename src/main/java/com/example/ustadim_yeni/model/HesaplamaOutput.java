package com.example.ustadim_yeni.model;

public class HesaplamaOutput {
    private double brutUcret;
    private double primeEsasKazanc;

    // SGK Primleri
    private double sgkIsciPayi;
    private double sgkIsverenPayi;
    private double sgkToplam;

    // SGK Detay
    private double sgkKisaVadeli;
    private double sgkUzunVadeli;
    private double sgkGss;
    private double sgkIssizlik;
    private double sgkSgdp;

    // Vergiler
    private double gelirVergisi;
    private double damgaVergisi;

    // Tavan/Taban
    private double tavanTutar;
    private double tabanTutar;
    private boolean tavanAsildi;
    private boolean tabanAltinda;

    // Net
    private double netUcret;

    // Getters & Setters
    public double getBrutUcret() { return brutUcret; }
    public void setBrutUcret(double brutUcret) { this.brutUcret = brutUcret; }

    public double getPrimeEsasKazanc() { return primeEsasKazanc; }
    public void setPrimeEsasKazanc(double primeEsasKazanc) { this.primeEsasKazanc = primeEsasKazanc; }

    public double getSgkIsciPayi() { return sgkIsciPayi; }
    public void setSgkIsciPayi(double sgkIsciPayi) { this.sgkIsciPayi = sgkIsciPayi; }

    public double getSgkIsverenPayi() { return sgkIsverenPayi; }
    public void setSgkIsverenPayi(double sgkIsverenPayi) { this.sgkIsverenPayi = sgkIsverenPayi; }

    public double getSgkToplam() { return sgkToplam; }
    public void setSgkToplam(double sgkToplam) { this.sgkToplam = sgkToplam; }

    public double getSgkKisaVadeli() { return sgkKisaVadeli; }
    public void setSgkKisaVadeli(double sgkKisaVadeli) { this.sgkKisaVadeli = sgkKisaVadeli; }

    public double getSgkUzunVadeli() { return sgkUzunVadeli; }
    public void setSgkUzunVadeli(double sgkUzunVadeli) { this.sgkUzunVadeli = sgkUzunVadeli; }

    public double getSgkGss() { return sgkGss; }
    public void setSgkGss(double sgkGss) { this.sgkGss = sgkGss; }

    public double getSgkIssizlik() { return sgkIssizlik; }
    public void setSgkIssizlik(double sgkIssizlik) { this.sgkIssizlik = sgkIssizlik; }

    public double getSgkSgdp() { return sgkSgdp; }
    public void setSgkSgdp(double sgkSgdp) { this.sgkSgdp = sgkSgdp; }

    public double getGelirVergisi() { return gelirVergisi; }
    public void setGelirVergisi(double gelirVergisi) { this.gelirVergisi = gelirVergisi; }

    public double getDamgaVergisi() { return damgaVergisi; }
    public void setDamgaVergisi(double damgaVergisi) { this.damgaVergisi = damgaVergisi; }

    public double getTavanTutar() { return tavanTutar; }
    public void setTavanTutar(double tavanTutar) { this.tavanTutar = tavanTutar; }

    public double getTabanTutar() { return tabanTutar; }
    public void setTabanTutar(double tabanTutar) { this.tabanTutar = tabanTutar; }

    public boolean isTavanAsildi() { return tavanAsildi; }
    public void setTavanAsildi(boolean tavanAsildi) { this.tavanAsildi = tavanAsildi; }

    public boolean isTabanAltinda() { return tabanAltinda; }
    public void setTabanAltinda(boolean tabanAltinda) { this.tabanAltinda = tabanAltinda; }

    public double getNetUcret() { return netUcret; }
    public void setNetUcret(double netUcret) { this.netUcret = netUcret; }
}