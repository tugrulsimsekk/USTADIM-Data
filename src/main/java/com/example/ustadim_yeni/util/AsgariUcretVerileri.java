package com.example.ustadim_yeni.util;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AsgariUcretVerileri {

    // Key: Yıl-Ay (YYYY-MM) formatında tarih, Value: Brüt Asgari Ücret
    private static final Map<String, Double> BRUT_ASGARI_UCRETLER;

    static {
        Map<String, Double> map = new HashMap<>();

        // 2024 Yılı (Örn: İkinci dönem 07-12)
        map.put("2024-07", 20002.50); // Temmuz 2024
        map.put("2024-01", 20002.50); // Ocak 2024

        // 2023 Yılı (Örn: İkinci dönem 07-12)
        map.put("2023-07", 13414.50); // Temmuz 2023
        map.put("2023-01", 10008.00); // Ocak 2023

        // 2022 Yılı
        map.put("2022-07", 6471.00);  // Temmuz 2022
        map.put("2022-01", 5004.00);  // Ocak 2022

        // 2021 Yılı
        map.put("2021-01", 3577.50);  // Ocak 2021

        // 2020 Yılı
        map.put("2020-01", 2943.00);

        // 2019 Yılı
        map.put("2019-01", 2558.40);

        map.put("2018-01", 2029.50);


        map.put("2017-01", 1777.50);

        map.put("2016-01", 1647.00);


        // 2015 Yılı
        map.put("2015-07", 1273.50);
        map.put("2015-01", 1201.50);

        // 2014 Yılı
        map.put("2014-07", 1134.00);
        map.put("2014-01", 1071.00);

        // 2013 Yılı
        map.put("2013-07", 1021.50);
        map.put("2013-01", 978.60);

        // 2012 Yılı
        map.put("2012-07", 940.50);
        map.put("2012-01", 886.50);

        // 2011 Yılı
        map.put("2011-07", 837.00);
        map.put("2011-01", 796.50);

        // 2010 Yılı
        map.put("2010-07", 760.50);
        map.put("2010-01", 729.00);

        // 2009 Yılı
        map.put("2009-07", 693.00);
        map.put("2009-01", 666.00);

        // 2008 Yılı (İstenen başlangıç)
        map.put("2008-07", 638.70);
        map.put("2008-01", 608.40);

        // 2007 Yılı
        map.put("2007-07", 585.00);
        map.put("2007-01", 562.50);

        // 2006 Yılı
        map.put("2006-01", 531.00);

        BRUT_ASGARI_UCRETLER = Collections.unmodifiableMap(map);
    }

    /**
     * Fiil tarihine göre geçerli brüt asgari ücreti döndürür.
     * Geçerli ayın bilgisi yoksa, o yılın ilk geçerli dönemini bulmaya çalışır.
     */
    public static double getUcretByTarih(LocalDate fiilTarihi) {
        if (fiilTarihi == null) {
            return 0.0;
        }

        int yil = fiilTarihi.getYear();
        int ay = fiilTarihi.getMonthValue();

        // 1. Tam Eşleşmeyi Dene (YYYY-MM)
        String key = String.format("%d-%02d", yil, ay);
        if (BRUT_ASGARI_UCRETLER.containsKey(key)) {
            return BRUT_ASGARI_UCRETLER.get(key);
        }

        // 2. Eğer tam ay yoksa, o yılın en yakın (en yüksek) ücretini bul
        for (int i = 12; i >= 1; i--) {
            String yakinKey = String.format("%d-%02d", yil, i);
            if (BRUT_ASGARI_UCRETLER.containsKey(yakinKey) && ay >= i) {
                return BRUT_ASGARI_UCRETLER.get(yakinKey);
            }
        }

        // 3. Bulamazsa, en azından bir önceki yılın sonunu dene
        for (int i = 12; i >= 1; i--) {
            String oncekiYilKey = String.format("%d-%02d", yil - 1, i);
            if (BRUT_ASGARI_UCRETLER.containsKey(oncekiYilKey)) {
                return BRUT_ASGARI_UCRETLER.get(oncekiYilKey);
            }
        }


        // Hiçbir şey bulunamazsa varsayılan
        return 0.0;
    }
}