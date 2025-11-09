package com.example.ustadim_yeni.util;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AsgariUcretVerileri {

    private static final Map<String, Double> BRUT_ASGARI_UCRETLER;

    static {
        Map<String, Double> map = new HashMap<>();
        map.put("2025-01", 26005.50);
        map.put("2024-07", 20002.50);
        map.put("2024-01", 20002.50);
        map.put("2023-07", 13414.50);
        map.put("2023-01", 10008.00);
        map.put("2022-07", 6471.00);
        map.put("2022-01", 5004.00);
        map.put("2021-01", 3577.50);
        map.put("2020-01", 2943.00);
        map.put("2019-01", 2558.40);
        map.put("2018-01", 2029.50);
        map.put("2017-01", 1777.50);
        map.put("2016-01", 1647.00);
        map.put("2015-07", 1273.50);
        map.put("2015-01", 1201.50);
        map.put("2014-07", 1134.00);
        map.put("2014-01", 1071.00);
        map.put("2013-07", 1021.50);
        map.put("2013-01", 978.60);
        map.put("2012-07", 940.50);
        map.put("2012-01", 886.50);
        map.put("2011-07", 837.00);
        map.put("2011-01", 796.50);
        map.put("2010-07", 760.50);
        map.put("2010-01", 729.00);
        map.put("2009-07", 693.00);
        map.put("2009-01", 666.00);
        map.put("2008-07", 638.70);
        map.put("2008-01", 608.40);
        map.put("2007-07", 585.00);
        map.put("2007-01", 562.50);
        map.put("2006-01", 531.00);

        BRUT_ASGARI_UCRETLER = Collections.unmodifiableMap(map);
    }

    public static double getUcretByTarih(LocalDate fiilTarihi) {
        if (fiilTarihi == null) {
            return 0.0;
        }

        int yil = fiilTarihi.getYear();
        int ay = fiilTarihi.getMonthValue();

        String key = String.format("%d-%02d", yil, ay);
        if (BRUT_ASGARI_UCRETLER.containsKey(key)) {
            return BRUT_ASGARI_UCRETLER.get(key);
        }

        for (int i = 12; i >= 1; i--) {
            String yakinKey = String.format("%d-%02d", yil, i);
            if (BRUT_ASGARI_UCRETLER.containsKey(yakinKey) && ay >= i) {
                return BRUT_ASGARI_UCRETLER.get(yakinKey);
            }
        }

        int maxYil = 0;
        for (String k : BRUT_ASGARI_UCRETLER.keySet()) {
            int ky = Integer.parseInt(k.split("-")[0]);
            if (ky > maxYil) {
                maxYil = ky;
            }
        }

        if (yil > maxYil) {
            return -1.0;
        }

        return -1.0;
    }
}