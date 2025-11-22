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
import com.example.ustadim_yeni.model.KazancTuru;
import com.example.ustadim_yeni.model.AsgariUcret;
import java.nio.charset.StandardCharsets;

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

    public static Map<String, Double> loadAsgariUcret(int yil, int ay) {
        Map<String, Double> result = new HashMap<>();

        try {
            InputStream inputStream = JsonDataLoader.class.getResourceAsStream("/asgariucret.json");

            if (inputStream == null) {
                System.out.println("⚠️ asgariucret.json dosyası bulunamadı!");
                return result;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONArray asgariUcretArray = jsonObject.getJSONArray("asgariUcretler");

            // En yakın asgari ücreti bul (geriye doğru)
            double brutAylik = 0;
            for (int i = 0; i < asgariUcretArray.length(); i++) {
                JSONObject ucret = asgariUcretArray.getJSONObject(i);

                int ucretYil = ucret.getInt("yil");
                int ucretAy = ucret.getInt("ay");

                // Eğer aranan tarih, bu asgari ücretin geçerli olduğu tarihten sonra ise
                if (yil > ucretYil || (yil == ucretYil && ay >= ucretAy)) {
                    brutAylik = ucret.getDouble("brut");
                    break;
                }
            }

            if (brutAylik == 0) {
                System.out.println("⚠️ " + yil + "/" + ay + " için asgari ücret bulunamadı!");
                return result;
            }

            // Günlük hesapla
            double brutGunluk = brutAylik / 30.0;

            // SGK ve İşsizlik kesintisi (4/a için %15)
            double sgkKesinti = brutAylik * 0.15;

            // Damga vergisi
            double damgaVergisi = brutAylik * 0.00759;

            // Gelir vergisi matrahı
            double vergiMatrahi = brutAylik - sgkKesinti;

            // Gelir vergisi (basit: %15)
            double gelirVergisi = vergiMatrahi * 0.15;

            // Net aylık
            double netAylik = brutAylik - sgkKesinti - damgaVergisi - gelirVergisi;

            // Net günlük
            double netGunluk = netAylik / 30.0;

            result.put("brutAylik", brutAylik);
            result.put("brutGunluk", brutGunluk);
            result.put("netAylik", netAylik);
            result.put("netGunluk", netGunluk);

            System.out.println("✅ Asgari Ücret: " + brutAylik + " TL (Brüt), " + netAylik + " TL (Net)");

        } catch (Exception e) {
            System.out.println("❌ Hata: " + e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    public static double loadDamgaVergisiOrani(int yil, int ay) {
        try {
            InputStream inputStream = JsonDataLoader.class.getResourceAsStream("/damga-vergisi.json");

            if (inputStream == null) {
                System.out.println("⚠️ damga-vergisi.json dosyası bulunamadı!");
                return 0.00759; // Varsayılan 2025 oranı
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONArray damgaVergisiArray = jsonObject.getJSONArray("damgaVergisi");

            LocalDate arananTarih = LocalDate.of(yil, ay, 1);

            for (int i = 0; i < damgaVergisiArray.length(); i++) {
                JSONObject dv = damgaVergisiArray.getJSONObject(i);

                LocalDate baslangic = LocalDate.parse(dv.getString("baslangic"));
                LocalDate bitis = LocalDate.parse(dv.getString("bitis"));

                if (!arananTarih.isBefore(baslangic) && !arananTarih.isAfter(bitis)) {
                    return dv.getDouble("oran");
                }
            }

            System.out.println("⚠️ " + yil + "/" + ay + " için damga vergisi oranı bulunamadı!");

        } catch (Exception e) {
            System.out.println("❌ Hata: " + e.getMessage());
            e.printStackTrace();
        }

        return 0.00759; // Varsayılan
    }

    public static List<Map<String, Double>> loadGelirVergisiDilimleri(int yil) {
        List<Map<String, Double>> dilimler = new ArrayList<>();

        try {
            InputStream inputStream = JsonDataLoader.class.getResourceAsStream("/gelir-vergisi-dilimleri.json");

            if (inputStream == null) {
                System.out.println("⚠️ gelir-vergisi-dilimleri.json dosyası bulunamadı!");
                return dilimler;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONObject gelirVergisiDilimleri = jsonObject.getJSONObject("gelirVergisiDilimleri");

            if (!gelirVergisiDilimleri.has(String.valueOf(yil))) {
                System.out.println("⚠️ " + yil + " yılı için gelir vergisi dilimleri bulunamadı!");
                return dilimler;
            }

            JSONArray dilimlerArray = gelirVergisiDilimleri.getJSONArray(String.valueOf(yil));

            for (int i = 0; i < dilimlerArray.length(); i++) {
                JSONObject dilim = dilimlerArray.getJSONObject(i);
                Map<String, Double> dilimMap = new HashMap<>();
                dilimMap.put("ust", dilim.getDouble("ust"));
                dilimMap.put("oran", dilim.getDouble("oran"));
                dilimMap.put("sabitVergi", dilim.getDouble("sabitVergi"));
                dilimler.add(dilimMap);
            }

        } catch (Exception e) {
            System.out.println("❌ Hata: " + e.getMessage());
            e.printStackTrace();
        }

        return dilimler;
    }

    public static Map<String, Double> loadIstisnaTutarlari(int yil, int ay) {
        Map<String, Double> result = new HashMap<>();
        result.put("gelirVergisi", 0.0);
        result.put("damgaVergisi", 0.0);

        try {
            InputStream inputStream = JsonDataLoader.class.getResourceAsStream("/istisna-tutarlari.json");

            if (inputStream == null) {
                System.out.println("⚠️ istisna-tutarlari.json dosyası bulunamadı!");
                return result;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONObject istisnaTutarlari = jsonObject.getJSONObject("istisnaTutarlari");

            String yilStr = String.valueOf(yil);
            if (!istisnaTutarlari.has(yilStr)) {
                System.out.println("⚠️ " + yil + " yılı için istisna tutarları bulunamadı!");
                return result;
            }

            JSONObject yilData = istisnaTutarlari.getJSONObject(yilStr);
            String ayStr = String.format("%02d", ay);

            if (yilData.has("gelirVergisi")) {
                JSONObject gelirVergisi = yilData.getJSONObject("gelirVergisi");
                if (gelirVergisi.has(ayStr)) {
                    result.put("gelirVergisi", gelirVergisi.getDouble(ayStr));
                }
            }

            if (yilData.has("damgaVergisi")) {
                JSONObject damgaVergisi = yilData.getJSONObject("damgaVergisi");
                if (damgaVergisi.has(ayStr)) {
                    result.put("damgaVergisi", damgaVergisi.getDouble(ayStr));
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Hata: " + e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    public static List<KazancTuru> loadKazancTurleri() {
        List<KazancTuru> kazancTurleriList = new ArrayList<>();

        try {
            InputStream inputStream = JsonDataLoader.class.getResourceAsStream("/kazanc-turleri.json");

            if (inputStream == null) {
                System.out.println("⚠️ kazanc-turleri.json dosyası bulunamadı!");
                return new ArrayList<>();
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONArray kazancTurleriArray = jsonObject.getJSONArray("kazancTurleri");

            for (int i = 0; i < kazancTurleriArray.length(); i++) {
                JSONObject kt = kazancTurleriArray.getJSONObject(i);

                // primeTabi boolean veya "KISMI" olabilir
                Object primeTabi;
                if (kt.get("primeTabi") instanceof Boolean) {
                    primeTabi = kt.getBoolean("primeTabi");
                } else {
                    primeTabi = kt.getString("primeTabi");
                }

                KazancTuru kazancTuru = new KazancTuru(
                        kt.getString("kod"),
                        kt.getString("ad"),
                        primeTabi,
                        kt.getString("hesaplama"),
                        kt.optString("formul", null),
                        kt.optString("istisnaTuru", null),
                        kt.has("istisnaOrani") ? kt.getDouble("istisnaOrani") : null,
                        kt.has("maxCocuk") ? kt.getInt("maxCocuk") : null
                );
                kazancTurleriList.add(kazancTuru);
            }

        } catch (Exception e) {
            System.out.println("❌ Hata: " + e.getMessage());
            e.printStackTrace();
        }

        return kazancTurleriList;
    }

    public static AsgariUcret getAsgariUcretByYil(int yil) {
        return getAsgariUcretByYilAy(yil, 1); // Ocak ayını varsayılan al
    }

    public static AsgariUcret getAsgariUcretByYilAy(int yil, int ay) {
        List<AsgariUcret> liste = loadAsgariUcretler();

        // Önce tam eşleşme ara (yıl ve ay)
        for (AsgariUcret au : liste) {
            if (au.yil() == yil && au.ay() <= ay) {
                return au;
            }
        }

        // Bulunamazsa sadece yıla bak
        for (AsgariUcret au : liste) {
            if (au.yil() == yil) {
                return au;
            }
        }

        return null;
    }

    public static List<AsgariUcret> loadAsgariUcretler() {
        try {
            InputStream is = JsonDataLoader.class.getResourceAsStream("/asgariucret.json");
            if (is == null) {
                System.err.println("asgariucret.json bulunamadı!");
                return new ArrayList<>();
            }

            String jsonContent = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            JSONObject root = new JSONObject(jsonContent);
            JSONArray jsonArray = root.getJSONArray("asgariUcretler");

            List<AsgariUcret> liste = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                AsgariUcret au = new AsgariUcret(
                        obj.getInt("yil"),
                        obj.getInt("ay"),
                        obj.getDouble("brut")
                );
                liste.add(au);
            }
            return liste;
        } catch (Exception e) {
            System.err.println("Asgari ücret yüklenirken hata: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Prim Oranları Yükleme
    public static List<JSONObject> loadPrimOranlari() {
        try {
            InputStream is = JsonDataLoader.class.getResourceAsStream("/data/prim-oranlari.json");
            if (is == null) {
                System.err.println("prim-oranlari.json bulunamadı!");
                return new ArrayList<>();
            }

            String jsonContent = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            JSONObject root = new JSONObject(jsonContent);
            JSONArray jsonArray = root.getJSONArray("primOranlari");

            List<JSONObject> liste = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                liste.add(jsonArray.getJSONObject(i));
            }
            return liste;
        } catch (Exception e) {
            System.err.println("Prim oranları yüklenirken hata: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static JSONObject getPrimOranByBelgeNo(String belgeNo) {
        List<JSONObject> liste = loadPrimOranlari();
        for (JSONObject obj : liste) {
            if (obj.getString("belgeNo").equals(belgeNo)) {
                return obj;
            }
        }
        return null;
    }

    // Vergi Muafiyetleri Yükleme
    public static List<JSONObject> loadVergiMuafiyetleri() {
        try {
            InputStream is = JsonDataLoader.class.getResourceAsStream("/vergi-muafiyetleri.json");
            if (is == null) {
                System.err.println("vergi-muafiyetleri.json bulunamadı!");
                return new ArrayList<>();
            }

            String jsonContent = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            JSONObject root = new JSONObject(jsonContent);
            JSONArray jsonArray = root.getJSONArray("vergiMuafiyetleri");

            List<JSONObject> liste = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                liste.add(jsonArray.getJSONObject(i));
            }
            return liste;
        } catch (Exception e) {
            System.err.println("Vergi muafiyetleri yüklenirken hata: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static JSONObject getVergiMuafiyetByBelgeNo(String belgeNo) {
        List<JSONObject> liste = loadVergiMuafiyetleri();
        for (JSONObject obj : liste) {
            if (obj.getString("belgeNo").equals(belgeNo)) {
                return obj;
            }
        }
        return null;
    }

    // Damga Vergisi Oranı
    public static double getDamgaVergisiOrani(int yil, int ay) {
        try {
            InputStream is = JsonDataLoader.class.getResourceAsStream("/damgavergisi.json");
            if (is == null) {
                return 0.00759; // Varsayılan
            }

            String jsonContent = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            JSONObject root = new JSONObject(jsonContent);
            JSONArray jsonArray = root.getJSONArray("damgaVergisi");

            String arananTarih = String.format("%d-%02d-01", yil, ay);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String baslangic = obj.getString("baslangic");
                String bitis = obj.getString("bitis");

                if (arananTarih.compareTo(baslangic) >= 0 && arananTarih.compareTo(bitis) <= 0) {
                    return obj.getDouble("oran");
                }
            }

            return 0.00759; // Varsayılan

        } catch (Exception e) {
            System.err.println("Damga vergisi oranı alınırken hata: " + e.getMessage());
            return 0.00759;
        }
    }

    // Gelir Vergisi Dilimleri
    public static List<JSONObject> getGelirVergisiDilimleri(int yil) {
        try {
            InputStream is = JsonDataLoader.class.getResourceAsStream("/gelir-vergisi-dilimleri.json");
            if (is == null) {
                return new ArrayList<>();
            }

            String jsonContent = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            JSONObject root = new JSONObject(jsonContent);
            JSONObject dilimler = root.getJSONObject("gelirVergisiDilimleri");

            if (!dilimler.has(String.valueOf(yil))) {
                return new ArrayList<>();
            }

            JSONArray yilDilimleri = dilimler.getJSONArray(String.valueOf(yil));
            List<JSONObject> liste = new ArrayList<>();

            for (int i = 0; i < yilDilimleri.length(); i++) {
                liste.add(yilDilimleri.getJSONObject(i));
            }

            return liste;

        } catch (Exception e) {
            System.err.println("Gelir vergisi dilimleri alınırken hata: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // İstisna Tutarları
    public static double getGelirVergisiIstisnasi(int yil, int ay) {
        try {
            InputStream is = JsonDataLoader.class.getResourceAsStream("/istisna-tutarlari.json");
            if (is == null) {
                return 0;
            }

            String jsonContent = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            JSONObject root = new JSONObject(jsonContent);
            JSONObject istisnalar = root.getJSONObject("istisnaTutarlari");

            if (!istisnalar.has(String.valueOf(yil))) {
                return 0;
            }

            JSONObject yilIstisnalar = istisnalar.getJSONObject(String.valueOf(yil));
            JSONObject gvIstisnalar = yilIstisnalar.getJSONObject("gelirVergisi");

            String ayStr = String.format("%02d", ay);
            if (gvIstisnalar.has(ayStr)) {
                return gvIstisnalar.getDouble(ayStr);
            }

            return 0;

        } catch (Exception e) {
            System.err.println("Gelir vergisi istisnası alınırken hata: " + e.getMessage());
            return 0;
        }
    }

    public static double getDamgaVergisiIstisnasi(int yil, int ay) {
        try {
            InputStream is = JsonDataLoader.class.getResourceAsStream("/istisna-tutarlari.json");
            if (is == null) {
                return 0;
            }

            String jsonContent = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            JSONObject root = new JSONObject(jsonContent);
            JSONObject istisnalar = root.getJSONObject("istisnaTutarlari");

            if (!istisnalar.has(String.valueOf(yil))) {
                return 0;
            }

            JSONObject yilIstisnalar = istisnalar.getJSONObject(String.valueOf(yil));
            JSONObject dvIstisnalar = yilIstisnalar.getJSONObject("damgaVergisi");

            String ayStr = String.format("%02d", ay);
            if (dvIstisnalar.has(ayStr)) {
                return dvIstisnalar.getDouble(ayStr);
            }

            return 0;

        } catch (Exception e) {
            System.err.println("Damga vergisi istisnası alınırken hata: " + e.getMessage());
            return 0;
        }
    }
}
