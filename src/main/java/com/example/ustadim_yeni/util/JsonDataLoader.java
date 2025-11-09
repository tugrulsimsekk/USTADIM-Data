package com.example.ustadim_yeni.util;

import com.example.ustadim_yeni.model.Ceza5510Turu;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonDataLoader {

    public static List<Ceza5510Turu> loadCezalar() {
        List<Ceza5510Turu> cezalar = new ArrayList<>();

        try {
            InputStream inputStream = JsonDataLoader.class.getResourceAsStream("/cezalar.json");

            if (inputStream == null) {
                System.out.println("⚠️ cezalar.json dosyası bulunamadı!");
                return new ArrayList<>();
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONArray cezalarArray = jsonObject.getJSONArray("cezalar");

            for (int i = 0; i < cezalarArray.length(); i++) {
                JSONObject ceza = cezalarArray.getJSONObject(i);

                Ceza5510Turu cezaTuru = new Ceza5510Turu(
                        ceza.getString("kod"),
                        ceza.getString("kanunYeri"),
                        ceza.getString("aciklama"),
                        ceza.getString("kriterAciklama"),
                        ceza.getDouble("katsayi"),
                        ceza.getDouble("altSinirKatsayi"),
                        ceza.getDouble("ustSinirKatsayi")
                );
                cezalar.add(cezaTuru);
            }

        } catch (Exception e) {
            System.out.println("❌ Hata: " + e.getMessage());
            e.printStackTrace();
        }

        return cezalar;
    }

    public static Map<String, Double> loadCezalar6331(LocalDate tarih, String sorumluk,
                                                      String tehlikeSinifi, String calisanSayisiAraligi) {
        Map<String, Double> result = new HashMap<>();

        try {
            InputStream inputStream = JsonDataLoader.class.getResourceAsStream("/cezalar6331.json");

            if (inputStream == null) {
                System.out.println("⚠️ cezalar6331.json dosyası bulunamadı!");
                return result;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONArray cezalarArray = jsonObject.getJSONArray("cezalar6331");

            double bestTutar = 0;
            LocalDate bestTarih = LocalDate.of(2000, 1, 1);

            for (int i = 0; i < cezalarArray.length(); i++) {
                JSONObject ceza = cezalarArray.getJSONObject(i);

                String cezaTarihStr = ceza.getString("tarih");
                LocalDate cezaTarih = LocalDate.parse(cezaTarihStr);
                String cezaSorumluk = ceza.getString("sorumluk");
                String cezaTehlike = ceza.getString("tehlikeSinifi");
                String cezaCalisanlar = ceza.getString("calisanSayisiAraligi");
                double tutar = ceza.getDouble("tutar");

                // Tüm koşullar eşleşirse ve tarih geçerli ise
                if (tarih.compareTo(cezaTarih) >= 0 &&
                        cezaSorumluk.equalsIgnoreCase(sorumluk) &&
                        cezaTehlike.equalsIgnoreCase(tehlikeSinifi) &&
                        cezaCalisanlar.equalsIgnoreCase(calisanSayisiAraligi)) {

                    // En yakın ve en yüksek tutarı al
                    if (cezaTarih.compareTo(bestTarih) > 0) {
                        bestTarih = cezaTarih;
                        bestTutar = tutar;
                    }
                }
            }

            if (bestTutar > 0) {
                result.put("tutar", bestTutar);
                result.put("madde", 26.0);
                System.out.println("✅ Ceza bulundu: " + bestTutar + " TL");
                return result;
            }

            System.out.println("⚠️ Seçilen kriterler için ceza bulunmadı!");

        } catch (Exception e) {
            System.out.println("❌ Hata: " + e.getMessage());
            e.printStackTrace();
        }

        return result;
    }
}