package com.example.ustadim_yeni.service;

import com.example.ustadim_yeni.model.*;
import com.example.ustadim_yeni.util.JsonDataLoader;
import org.json.JSONObject;

import java.util.List;

public class UcretHesaplayici {

    public static HesaplamaOutput hesapla(HesaplamaInput input) {
        if ("BRUT_NET".equals(input.getHesaplamaModu())) {
            return bruttenNete(input);
        } else if ("NET_BRUT".equals(input.getHesaplamaModu())) {
            return nettenBrute(input);
        }
        throw new IllegalArgumentException("Geçersiz hesaplama modu: " + input.getHesaplamaModu());
    }

    /**
     * BRÜT → NET Hesaplama
     */
    private static HesaplamaOutput bruttenNete(HesaplamaInput input) {
        HesaplamaOutput output = new HesaplamaOutput();

        double brutUcret = input.getTutar();
        output.setBrutUcret(brutUcret);

        // 1. Tavan/Taban Hesapla
        hesaplaTavanTaban(input, output);

        // 2. Prime Esas Kazanç Belirle
        double primeEsasKazanc = Math.min(brutUcret, output.getTavanTutar());
        primeEsasKazanc = Math.max(primeEsasKazanc, output.getTabanTutar());
        output.setPrimeEsasKazanc(primeEsasKazanc);

        // 3. SGK Primi Hesapla
        hesaplaSgkPrimleri(input, output);

        // 4. Gelir Vergisi Hesapla
        hesaplaGelirVergisi(input, output);

        // 5. Damga Vergisi Hesapla
        hesaplaDamgaVergisi(input, output);

        // 6. Net Ücret Hesapla
        double netUcret = brutUcret
                - output.getSgkIsciPayi()
                - output.getGelirVergisi()
                - output.getDamgaVergisi();
        output.setNetUcret(netUcret);

        return output;
    }

    /**
     * NET → BRÜT Hesaplama (Iteratif)
     */
    private static HesaplamaOutput nettenBrute(HesaplamaInput input) {
        double hedefNet = input.getTutar();
        double tahminiBrut = hedefNet * 1.5; // Başlangıç tahmini

        // İteratif hesaplama
        for (int i = 0; i < 20; i++) {
            HesaplamaInput testInput = new HesaplamaInput(
                    input.getSigortalılikStatusu(),
                    input.getYil(),
                    input.getAy(),
                    "BRUT_NET",
                    tahminiBrut,
                    input.getCalismaGunu()
            );

            HesaplamaOutput testOutput = bruttenNete(testInput);
            double bulunanNet = testOutput.getNetUcret();

            // Yeterince yakınsa bitir
            if (Math.abs(bulunanNet - hedefNet) < 0.01) {
                return testOutput;
            }

            // Tahmini güncelle
            double fark = hedefNet - bulunanNet;
            tahminiBrut += fark;
        }

        // Son hesaplama
        HesaplamaInput finalInput = new HesaplamaInput(
                input.getSigortalılikStatusu(),
                input.getYil(),
                input.getAy(),
                "BRUT_NET",
                tahminiBrut,
                input.getCalismaGunu()
        );

        return bruttenNete(finalInput);
    }

    /**
     * Tavan/Taban Hesaplama
     */
    private static void hesaplaTavanTaban(HesaplamaInput input, HesaplamaOutput output) {
        // Asgari ücreti al
        AsgariUcret au = JsonDataLoader.getAsgariUcretByYilAy(input.getYil(), input.getAy());
        if (au == null) {
            throw new RuntimeException("Asgari ücret bulunamadı: " + input.getYil() + "/" + input.getAy());
        }

        // Prim oranlarını al
        JSONObject primOran = JsonDataLoader.getPrimOranByBelgeNo(input.getSigortalılikStatusu());

        double tavanKatsayi = 7.5; // Varsayılan
        double tabanKatsayi = 1.0;

        if (primOran != null) {
            tavanKatsayi = primOran.optDouble("tavanKatsayi", 7.5);
            tabanKatsayi = primOran.optDouble("tabanKatsayi", 1.0);
        }

        double gunlukAU = au.brutAylikTutar() / 30.0;
        double tavanGunluk = gunlukAU * tavanKatsayi;
        double tabanGunluk = gunlukAU * tabanKatsayi;

        double tavanAylik = tavanGunluk * 30;
        double tabanAylik = tabanGunluk * input.getCalismaGunu();

        output.setTavanTutar(tavanAylik);
        output.setTabanTutar(tabanAylik);
        output.setTavanAsildi(input.getTutar() > tavanAylik);
        output.setTabanAltinda(input.getTutar() < tabanAylik);
    }

    /**
     * SGK Primleri Hesaplama
     */
    private static void hesaplaSgkPrimleri(HesaplamaInput input, HesaplamaOutput output) {
        double primeEsas = output.getPrimeEsasKazanc();

        // Prim oranlarını JSON'dan al
        JSONObject primOran = JsonDataLoader.getPrimOranByBelgeNo(input.getSigortalılikStatusu());

        if (primOran == null) {
            System.err.println("Prim oranı bulunamadı: " + input.getSigortalılikStatusu());
            output.setSgkIsciPayi(0);
            output.setSgkIsverenPayi(0);
            output.setSgkToplam(0);
            return;
        }

        JSONObject sigortalıHisse = primOran.getJSONObject("sigortalıHissesi");
        JSONObject isverenHisse = primOran.getJSONObject("isverenHissesi");

        // İşçi payı oranları
        double kisaVadeliIsci = sigortalıHisse.optDouble("kisaVadeli", 0.0);
        double uzunVadeliIsci = sigortalıHisse.optDouble("uzunVadeli", 0.0);
        double gssIsci = sigortalıHisse.optDouble("gss", 0.0);
        double issizlikIsci = sigortalıHisse.optDouble("issizlik", 0.0);
        double sgdpIsci = sigortalıHisse.optDouble("sgdpPrimi", 0.0);

        // İşveren payı oranları
        double kisaVadeliIsveren = isverenHisse.optDouble("kisaVadeli", 0.0);
        double uzunVadeliIsveren = isverenHisse.optDouble("uzunVadeli", 0.0);
        double gssIsveren = isverenHisse.optDouble("gss", 0.0);
        double issizlikIsveren = isverenHisse.optDouble("issizlik", 0.0);
        double sgdpIsveren = isverenHisse.optDouble("sgdpPrimi", 0.0);

        // Detay hesaplama
        output.setSgkKisaVadeli(primeEsas * (kisaVadeliIsci + kisaVadeliIsveren));
        output.setSgkUzunVadeli(primeEsas * (uzunVadeliIsci + uzunVadeliIsveren));
        output.setSgkGss(primeEsas * (gssIsci + gssIsveren));
        output.setSgkIssizlik(primeEsas * (issizlikIsci + issizlikIsveren));
        output.setSgkSgdp(primeEsas * (sgdpIsci + sgdpIsveren));

        // Toplam hesaplama
        double isciPayi = primeEsas * (kisaVadeliIsci + uzunVadeliIsci + gssIsci + issizlikIsci + sgdpIsci);
        double isverenPayi = primeEsas * (kisaVadeliIsveren + uzunVadeliIsveren + gssIsveren + issizlikIsveren + sgdpIsveren);

        output.setSgkIsciPayi(isciPayi);
        output.setSgkIsverenPayi(isverenPayi);
        output.setSgkToplam(isciPayi + isverenPayi);
    }

    /**
     * Gelir Vergisi Hesaplama
     */
    private static void hesaplaGelirVergisi(HesaplamaInput input, HesaplamaOutput output) {
        // Vergi muafiyeti kontrolü
        JSONObject vergiMuafiyet = JsonDataLoader.getVergiMuafiyetByBelgeNo(input.getSigortalılikStatusu());

        if (vergiMuafiyet != null) {
            JSONObject gvMuafiyet = vergiMuafiyet.getJSONObject("gelirVergisiMuafiyeti");

            // Yıl bazlı muafiyet kontrolü (örn: 5/g için 2023+)
            if (gvMuafiyet.has(String.valueOf(input.getYil()))) {
                JSONObject yilMuafiyet = gvMuafiyet.getJSONObject(String.valueOf(input.getYil()));
                if (yilMuafiyet.getBoolean("muaf")) {
                    output.setGelirVergisi(0);
                    return;
                }
            }

            // Genel muafiyet kontrolü
            if (gvMuafiyet.has("muaf") && gvMuafiyet.getBoolean("muaf")) {
                output.setGelirVergisi(0);
                return;
            }
        }

        // Matrah hesaplama
        double matrah = output.getBrutUcret() - output.getSgkIsciPayi();

        // Gelir vergisi dilimleri
        List<JSONObject> dilimler = JsonDataLoader.getGelirVergisiDilimleri(input.getYil());

        double vergi = 0;
        double kalanMatrah = matrah;
        double oncekiUst = 0;

        for (JSONObject dilim : dilimler) {
            double ust = dilim.getDouble("ust");
            double oran = dilim.getDouble("oran");
            double sabitVergi = dilim.getDouble("sabitVergi");

            if (kalanMatrah <= 0) break;

            if (matrah <= ust) {
                // Son dilim
                vergi = (matrah - oncekiUst) * oran + sabitVergi;
                break;
            } else if (matrah > ust && kalanMatrah > 0) {
                // Bir sonraki dilime geç
                oncekiUst = ust;
            }
        }

        // Gelir vergisi istisnası
        double istisna = JsonDataLoader.getGelirVergisiIstisnasi(input.getYil(), input.getAy());
        vergi = Math.max(0, vergi - istisna);

        output.setGelirVergisi(vergi);
    }

    /**
     * Damga Vergisi Hesaplama
     */
    private static void hesaplaDamgaVergisi(HesaplamaInput input, HesaplamaOutput output) {
        // Vergi muafiyeti kontrolü
        JSONObject vergiMuafiyet = JsonDataLoader.getVergiMuafiyetByBelgeNo(input.getSigortalılikStatusu());

        if (vergiMuafiyet != null) {
            JSONObject dvMuafiyet = vergiMuafiyet.getJSONObject("damgaVergisiMuafiyeti");
            if (dvMuafiyet.getBoolean("muaf")) {
                output.setDamgaVergisi(0);
                return;
            }
        }

        // Damga vergisi oranı
        double oran = JsonDataLoader.getDamgaVergisiOrani(input.getYil(), input.getAy());
        double damga = output.getBrutUcret() * oran;

        // Damga vergisi istisnası
        double istisna = JsonDataLoader.getDamgaVergisiIstisnasi(input.getYil(), input.getAy());
        damga = Math.max(0, damga - istisna);

        output.setDamgaVergisi(damga);
    }
}