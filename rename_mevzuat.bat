@echo off
chcp 65001 > nul
set ROK=C:\Users\CREATOR\Dropbox\PC\Desktop\MEVZUAT
echo Klasorler yeniden adlandiriliyor...

if exist "%ROK%\cumhurbaşkanlığı kararnamesi" (
    ren "%ROK%\cumhurbaşkanlığı kararnamesi" "cbk"
    echo   OK: cumhurbaskanligi kararnamesi ^> cbk
) else (
    echo   ZATEN YOK VEYA DEGISTIRILDI: cumhurbaskanligi kararnamesi
)
if exist "%ROK%\yönetmelik" (
    ren "%ROK%\yönetmelik" "yonetmelik"
    echo   OK: yonetmelik ^> yonetmelik
) else (
    echo   ZATEN YOK VEYA DEGISTIRILDI: yonetmelik
)
if exist "%ROK%\tüzük" (
    ren "%ROK%\tüzük" "tuzuk"
    echo   OK: tuzuk ^> tuzuk
) else (
    echo   ZATEN YOK VEYA DEGISTIRILDI: tuzuk
)
if exist "%ROK%\usul ve esaslar" (
    ren "%ROK%\usul ve esaslar" "usul-esaslar"
    echo   OK: usul ve esaslar ^> usul-esaslar
) else (
    echo   ZATEN YOK VEYA DEGISTIRILDI: usul ve esaslar
)
if exist "%ROK%\tebliğ" (
    ren "%ROK%\tebliğ" "teblig"
    echo   OK: teblig ^> teblig
) else (
    echo   ZATEN YOK VEYA DEGISTIRILDI: teblig
)
if exist "%ROK%\yönerge" (
    ren "%ROK%\yönerge" "yonerge"
    echo   OK: yonerge ^> yonerge
) else (
    echo   ZATEN YOK VEYA DEGISTIRILDI: yonerge
)
if exist "%ROK%\iç emir" (
    ren "%ROK%\iç emir" "ic-emir"
    echo   OK: ic emir ^> ic-emir
) else (
    echo   ZATEN YOK VEYA DEGISTIRILDI: ic emir
)
if exist "%ROK%\genel yazı-talimat" (
    ren "%ROK%\genel yazı-talimat" "genel-yazi"
    echo   OK: genel yazi-talimat ^> genel-yazi
) else (
    echo   ZATEN YOK VEYA DEGISTIRILDI: genel yazi-talimat
)

echo.
echo PDF dosyalari yeniden adlandiriliyor...

if exist "%ROK%\kanun\4562.pdf" (
    ren "%ROK%\kanun\4562.pdf" "4562_2.pdf"
    echo   OK: 4562.pdf ^> 4562_2.pdf
) else (
    echo   BULUNAMADI: kanun\4562.pdf
)
if exist "%ROK%\kanun\2022.pdf" (
    ren "%ROK%\kanun\2022.pdf" "2022_2.pdf"
    echo   OK: 2022.pdf ^> 2022_2.pdf
) else (
    echo   BULUNAMADI: kanun\2022.pdf
)
if exist "%ROK%\yonetmelik\20112226.pdf" (
    ren "%ROK%\yonetmelik\20112226.pdf" "20112226_2.pdf"
    echo   OK: 20112226.pdf ^> 20112226_2.pdf
) else (
    echo   BULUNAMADI: yonetmelik\20112226.pdf
)
if exist "%ROK%\yonetmelik\SOSYAL_GUVENLIK_KURUMU_HIZMET_ICI_EGITIM_YONETMELIGI.pdf" (
    ren "%ROK%\yonetmelik\SOSYAL_GUVENLIK_KURUMU_HIZMET_ICI_EGITIM_YONETMELIGI.pdf" "191.pdf"
    echo   OK: SOSYAL_GUVENLIK_KURUMU_HIZMET_ICI_EGITIM_YONETMELIGI.pdf ^> 191.pdf
) else (
    echo   BULUNAMADI: yonetmelik\SOSYAL_GUVENLIK_KURUMU_HIZMET_ICI_EGITIM_YONETMELIGI.pdf
)
if exist "%ROK%\yonetmelik\424.pdf" (
    ren "%ROK%\yonetmelik\424.pdf" "2016_424.pdf"
    echo   OK: 424.pdf ^> 2016_424.pdf
) else (
    echo   BULUNAMADI: yonetmelik\424.pdf
)
if exist "%ROK%\tuzuk\ISCI_SAGLIGI_VE_IS_GUVENLIGI_TUZUGU.pdf" (
    ren "%ROK%\tuzuk\ISCI_SAGLIGI_VE_IS_GUVENLIGI_TUZUGU.pdf" "ISCI_SAGLIGI_VE_IS_GUVENLIGI.pdf"
    echo   OK: ISCI_SAGLIGI_VE_IS_GUVENLIGI_TUZUGU.pdf ^> ISCI_SAGLIGI_VE_IS_GUVENLIGI.pdf
) else (
    echo   BULUNAMADI: tuzuk\ISCI_SAGLIGI_VE_IS_GUVENLIGI_TUZUGU.pdf
)
if exist "%ROK%\tuzuk\YAPI_ISLERINDE_ISCI_SAGLIGI_VE_IS_GUVENLIGI_TUZUGU.pdf" (
    ren "%ROK%\tuzuk\YAPI_ISLERINDE_ISCI_SAGLIGI_VE_IS_GUVENLIGI_TUZUGU.pdf" "YAPI_ISLERINDE_ISCI_SAGLIGI_VE.pdf"
    echo   OK: YAPI_ISLERINDE_ISCI_SAGLIGI_VE_IS_GUVENLIGI_TUZUGU.pdf ^> YAPI_ISLERINDE_ISCI_SAGLIGI_VE.pdf
) else (
    echo   BULUNAMADI: tuzuk\YAPI_ISLERINDE_ISCI_SAGLIGI_VE_IS_GUVENLIGI_TUZUGU.pdf
)
if exist "%ROK%\genelge\2013.11.pdf" (
    ren "%ROK%\genelge\2013.11.pdf" "2013_11.pdf"
    echo   OK: 2013.11.pdf ^> 2013_11.pdf
) else (
    echo   BULUNAMADI: genelge\2013.11.pdf
)
if exist "%ROK%\genelge\2026.1.pdf" (
    ren "%ROK%\genelge\2026.1.pdf" "2026_1.pdf"
    echo   OK: 2026.1.pdf ^> 2026_1.pdf
) else (
    echo   BULUNAMADI: genelge\2026.1.pdf
)
if exist "%ROK%\genelge\2026.2.pdf" (
    ren "%ROK%\genelge\2026.2.pdf" "2026_2.pdf"
    echo   OK: 2026.2.pdf ^> 2026_2.pdf
) else (
    echo   BULUNAMADI: genelge\2026.2.pdf
)
if exist "%ROK%\genelge\2025.9.pdf" (
    ren "%ROK%\genelge\2025.9.pdf" "2025_9.pdf"
    echo   OK: 2025.9.pdf ^> 2025_9.pdf
) else (
    echo   BULUNAMADI: genelge\2025.9.pdf
)
if exist "%ROK%\genelge\2024.17.pdf" (
    ren "%ROK%\genelge\2024.17.pdf" "2024_17.pdf"
    echo   OK: 2024.17.pdf ^> 2024_17.pdf
) else (
    echo   BULUNAMADI: genelge\2024.17.pdf
)
if exist "%ROK%\genelge\2025.6.pdf" (
    ren "%ROK%\genelge\2025.6.pdf" "2025_6.pdf"
    echo   OK: 2025.6.pdf ^> 2025_6.pdf
) else (
    echo   BULUNAMADI: genelge\2025.6.pdf
)
if exist "%ROK%\genelge\2024.13.pdf" (
    ren "%ROK%\genelge\2024.13.pdf" "2024_13.pdf"
    echo   OK: 2024.13.pdf ^> 2024_13.pdf
) else (
    echo   BULUNAMADI: genelge\2024.13.pdf
)
if exist "%ROK%\genelge\2024.8.pdf" (
    ren "%ROK%\genelge\2024.8.pdf" "2024_8.pdf"
    echo   OK: 2024.8.pdf ^> 2024_8.pdf
) else (
    echo   BULUNAMADI: genelge\2024.8.pdf
)
if exist "%ROK%\genelge\2011.13.pdf" (
    ren "%ROK%\genelge\2011.13.pdf" "2011_13.pdf"
    echo   OK: 2011.13.pdf ^> 2011_13.pdf
) else (
    echo   BULUNAMADI: genelge\2011.13.pdf
)
if exist "%ROK%\genelge\2020.12.pdf" (
    ren "%ROK%\genelge\2020.12.pdf" "2020_12.pdf"
    echo   OK: 2020.12.pdf ^> 2020_12.pdf
) else (
    echo   BULUNAMADI: genelge\2020.12.pdf
)
if exist "%ROK%\genelge\2020.8.pdf" (
    ren "%ROK%\genelge\2020.8.pdf" "2020_8.pdf"
    echo   OK: 2020.8.pdf ^> 2020_8.pdf
) else (
    echo   BULUNAMADI: genelge\2020.8.pdf
)
if exist "%ROK%\genelge\2019.17.pdf" (
    ren "%ROK%\genelge\2019.17.pdf" "2019_17.pdf"
    echo   OK: 2019.17.pdf ^> 2019_17.pdf
) else (
    echo   BULUNAMADI: genelge\2019.17.pdf
)
if exist "%ROK%\genelge\2019.16.pdf" (
    ren "%ROK%\genelge\2019.16.pdf" "2019_16.pdf"
    echo   OK: 2019.16.pdf ^> 2019_16.pdf
) else (
    echo   BULUNAMADI: genelge\2019.16.pdf
)
if exist "%ROK%\genelge\2018.38.pdf" (
    ren "%ROK%\genelge\2018.38.pdf" "2018_38.pdf"
    echo   OK: 2018.38.pdf ^> 2018_38.pdf
) else (
    echo   BULUNAMADI: genelge\2018.38.pdf
)
if exist "%ROK%\genelge\2018.37.pdf" (
    ren "%ROK%\genelge\2018.37.pdf" "2018_37.pdf"
    echo   OK: 2018.37.pdf ^> 2018_37.pdf
) else (
    echo   BULUNAMADI: genelge\2018.37.pdf
)
if exist "%ROK%\genelge\2015.25.pdf" (
    ren "%ROK%\genelge\2015.25.pdf" "2015_25.pdf"
    echo   OK: 2015.25.pdf ^> 2015_25.pdf
) else (
    echo   BULUNAMADI: genelge\2015.25.pdf
)
if exist "%ROK%\genelge\2017.31.pdf" (
    ren "%ROK%\genelge\2017.31.pdf" "2017_31.pdf"
    echo   OK: 2017.31.pdf ^> 2017_31.pdf
) else (
    echo   BULUNAMADI: genelge\2017.31.pdf
)
if exist "%ROK%\genelge\2010.117.pdf" (
    ren "%ROK%\genelge\2010.117.pdf" "2010_117.pdf"
    echo   OK: 2010.117.pdf ^> 2010_117.pdf
) else (
    echo   BULUNAMADI: genelge\2010.117.pdf
)
if exist "%ROK%\genelge\2017.25.pdf" (
    ren "%ROK%\genelge\2017.25.pdf" "2017_25.pdf"
    echo   OK: 2017.25.pdf ^> 2017_25.pdf
) else (
    echo   BULUNAMADI: genelge\2017.25.pdf
)
if exist "%ROK%\genelge\2016.15.pdf" (
    ren "%ROK%\genelge\2016.15.pdf" "2016_15.pdf"
    echo   OK: 2016.15.pdf ^> 2016_15.pdf
) else (
    echo   BULUNAMADI: genelge\2016.15.pdf
)
if exist "%ROK%\genelge\2016.6.pdf" (
    ren "%ROK%\genelge\2016.6.pdf" "2016_6.pdf"
    echo   OK: 2016.6.pdf ^> 2016_6.pdf
) else (
    echo   BULUNAMADI: genelge\2016.6.pdf
)
if exist "%ROK%\genelge\2015.13.pdf" (
    ren "%ROK%\genelge\2015.13.pdf" "2015_13.pdf"
    echo   OK: 2015.13.pdf ^> 2015_13.pdf
) else (
    echo   BULUNAMADI: genelge\2015.13.pdf
)
if exist "%ROK%\genelge\2015.2.pdf" (
    ren "%ROK%\genelge\2015.2.pdf" "2015_2.pdf"
    echo   OK: 2015.2.pdf ^> 2015_2.pdf
) else (
    echo   BULUNAMADI: genelge\2015.2.pdf
)
if exist "%ROK%\genelge\2014.28.pdf" (
    ren "%ROK%\genelge\2014.28.pdf" "2014_28.pdf"
    echo   OK: 2014.28.pdf ^> 2014_28.pdf
) else (
    echo   BULUNAMADI: genelge\2014.28.pdf
)
if exist "%ROK%\genelge\2014.20.pdf" (
    ren "%ROK%\genelge\2014.20.pdf" "2014_20.pdf"
    echo   OK: 2014.20.pdf ^> 2014_20.pdf
) else (
    echo   BULUNAMADI: genelge\2014.20.pdf
)
if exist "%ROK%\genelge\2014.10.pdf" (
    ren "%ROK%\genelge\2014.10.pdf" "2014_10.pdf"
    echo   OK: 2014.10.pdf ^> 2014_10.pdf
) else (
    echo   BULUNAMADI: genelge\2014.10.pdf
)
if exist "%ROK%\genelge\2013.37.pdf" (
    ren "%ROK%\genelge\2013.37.pdf" "2013_37.pdf"
    echo   OK: 2013.37.pdf ^> 2013_37.pdf
) else (
    echo   BULUNAMADI: genelge\2013.37.pdf
)
if exist "%ROK%\genelge\2013.34.pdf" (
    ren "%ROK%\genelge\2013.34.pdf" "2013_34.pdf"
    echo   OK: 2013.34.pdf ^> 2013_34.pdf
) else (
    echo   BULUNAMADI: genelge\2013.34.pdf
)
if exist "%ROK%\genelge\2018.8.pdf" (
    ren "%ROK%\genelge\2018.8.pdf" "2018_8.pdf"
    echo   OK: 2018.8.pdf ^> 2018_8.pdf
) else (
    echo   BULUNAMADI: genelge\2018.8.pdf
)
if exist "%ROK%\genelge\2012.19.pdf" (
    ren "%ROK%\genelge\2012.19.pdf" "2012_19.pdf"
    echo   OK: 2012.19.pdf ^> 2012_19.pdf
) else (
    echo   BULUNAMADI: genelge\2012.19.pdf
)
if exist "%ROK%\genelge\2011.53.pdf" (
    ren "%ROK%\genelge\2011.53.pdf" "2011_53.pdf"
    echo   OK: 2011.53.pdf ^> 2011_53.pdf
) else (
    echo   BULUNAMADI: genelge\2011.53.pdf
)
if exist "%ROK%\genelge\2011.46.pdf" (
    ren "%ROK%\genelge\2011.46.pdf" "2011_46.pdf"
    echo   OK: 2011.46.pdf ^> 2011_46.pdf
) else (
    echo   BULUNAMADI: genelge\2011.46.pdf
)
if exist "%ROK%\genelge\2011.48.pdf" (
    ren "%ROK%\genelge\2011.48.pdf" "2011_48.pdf"
    echo   OK: 2011.48.pdf ^> 2011_48.pdf
) else (
    echo   BULUNAMADI: genelge\2011.48.pdf
)
if exist "%ROK%\genelge\2011.32.pdf" (
    ren "%ROK%\genelge\2011.32.pdf" "2011_32.pdf"
    echo   OK: 2011.32.pdf ^> 2011_32.pdf
) else (
    echo   BULUNAMADI: genelge\2011.32.pdf
)
if exist "%ROK%\genelge\2011.33.pdf" (
    ren "%ROK%\genelge\2011.33.pdf" "2011_33.pdf"
    echo   OK: 2011.33.pdf ^> 2011_33.pdf
) else (
    echo   BULUNAMADI: genelge\2011.33.pdf
)
if exist "%ROK%\genelge\2011.19.pdf" (
    ren "%ROK%\genelge\2011.19.pdf" "2011_19.pdf"
    echo   OK: 2011.19.pdf ^> 2011_19.pdf
) else (
    echo   BULUNAMADI: genelge\2011.19.pdf
)
if exist "%ROK%\genelge\2011.9.pdf" (
    ren "%ROK%\genelge\2011.9.pdf" "2011_9.pdf"
    echo   OK: 2011.9.pdf ^> 2011_9.pdf
) else (
    echo   BULUNAMADI: genelge\2011.9.pdf
)
if exist "%ROK%\genelge\2010.52.pdf" (
    ren "%ROK%\genelge\2010.52.pdf" "2010_52.pdf"
    echo   OK: 2010.52.pdf ^> 2010_52.pdf
) else (
    echo   BULUNAMADI: genelge\2010.52.pdf
)
if exist "%ROK%\genelge\2008.86.pdf" (
    ren "%ROK%\genelge\2008.86.pdf" "2008_86.pdf"
    echo   OK: 2008.86.pdf ^> 2008_86.pdf
) else (
    echo   BULUNAMADI: genelge\2008.86.pdf
)
if exist "%ROK%\genelge\2008.22.pdf" (
    ren "%ROK%\genelge\2008.22.pdf" "2008_22.pdf"
    echo   OK: 2008.22.pdf ^> 2008_22.pdf
) else (
    echo   BULUNAMADI: genelge\2008.22.pdf
)
if exist "%ROK%\genelge\2013.5.pdf" (
    ren "%ROK%\genelge\2013.5.pdf" "2013_5.pdf"
    echo   OK: 2013.5.pdf ^> 2013_5.pdf
) else (
    echo   BULUNAMADI: genelge\2013.5.pdf
)
if exist "%ROK%\genelge\2020.20.pdf" (
    ren "%ROK%\genelge\2020.20.pdf" "2020_20.pdf"
    echo   OK: 2020.20.pdf ^> 2020_20.pdf
) else (
    echo   BULUNAMADI: genelge\2020.20.pdf
)
if exist "%ROK%\usul-esaslar\430.pdf" (
    ren "%ROK%\usul-esaslar\430.pdf" "2016_430.pdf"
    echo   OK: 430.pdf ^> 2016_430.pdf
) else (
    echo   BULUNAMADI: usul-esaslar\430.pdf
)
if exist "%ROK%\usul-esaslar\62.pdf" (
    ren "%ROK%\usul-esaslar\62.pdf" "2016_62.pdf"
    echo   OK: 62.pdf ^> 2016_62.pdf
) else (
    echo   BULUNAMADI: usul-esaslar\62.pdf
)
if exist "%ROK%\usul-esaslar\56.pdf" (
    ren "%ROK%\usul-esaslar\56.pdf" "2016_56.pdf"
    echo   OK: 56.pdf ^> 2016_56.pdf
) else (
    echo   BULUNAMADI: usul-esaslar\56.pdf
)
if exist "%ROK%\usul-esaslar\SOSYAL_GUVENLIK_KURUMU_DIS_FATURA_INCELEME_USUL_VE_ESASLARI.pdf" (
    ren "%ROK%\usul-esaslar\SOSYAL_GUVENLIK_KURUMU_DIS_FATURA_INCELEME_USUL_VE_ESASLARI.pdf" "SOSYAL_GUVENLIK_KURUMU_DIS_FATURA.pdf"
    echo   OK: SOSYAL_GUVENLIK_KURUMU_DIS_FATURA_INCELEME_USUL_VE_ESASLARI.pdf ^> SOSYAL_GUVENLIK_KURUMU_DIS_FATURA.pdf
) else (
    echo   BULUNAMADI: usul-esaslar\SOSYAL_GUVENLIK_KURUMU_DIS_FATURA_INCELEME_USUL_VE_ESASLARI.pdf
)
if exist "%ROK%\usul-esaslar\SOSYAL_GUVENLIK_KURUMU_KONUT_TAHSIS_ISLEMLERI_ILE_ILGILI_USUL_VE_ESASLAR.pdf" (
    ren "%ROK%\usul-esaslar\SOSYAL_GUVENLIK_KURUMU_KONUT_TAHSIS_ISLEMLERI_ILE_ILGILI_USUL_VE_ESASLAR.pdf" "SOSYAL_GUVENLIK_KURUMU_KONUT_TAHSIS.pdf"
    echo   OK: SOSYAL_GUVENLIK_KURUMU_KONUT_TAHSIS_ISLEMLERI_ILE_ILGILI_USUL_VE_ESASLAR.pdf ^> SOSYAL_GUVENLIK_KURUMU_KONUT_TAHSIS.pdf
) else (
    echo   BULUNAMADI: usul-esaslar\SOSYAL_GUVENLIK_KURUMU_KONUT_TAHSIS_ISLEMLERI_ILE_ILGILI_USUL_VE_ESASLAR.pdf
)
if exist "%ROK%\usul-esaslar\SOSYAL_GUVENLIK_KURUMU_PERSONEL_YEMEK_HIZMETLERININ_SUNULMASINA_ILISKIN_USUL_VE_.pdf" (
    ren "%ROK%\usul-esaslar\SOSYAL_GUVENLIK_KURUMU_PERSONEL_YEMEK_HIZMETLERININ_SUNULMASINA_ILISKIN_USUL_VE_.pdf" "SOSYAL_GUVENLIK_KURUMU_PERSONEL_YEMEK.pdf"
    echo   OK: SOSYAL_GUVENLIK_KURUMU_PERSONEL_YEMEK_HIZMETLERININ_SUNULMASINA_ILISKIN_USUL_VE_.pdf ^> SOSYAL_GUVENLIK_KURUMU_PERSONEL_YEMEK.pdf
) else (
    echo   BULUNAMADI: usul-esaslar\SOSYAL_GUVENLIK_KURUMU_PERSONEL_YEMEK_HIZMETLERININ_SUNULMASINA_ILISKIN_USUL_VE_.pdf
)
if exist "%ROK%\usul-esaslar\448.pdf" (
    ren "%ROK%\usul-esaslar\448.pdf" "2015_448.pdf"
    echo   OK: 448.pdf ^> 2015_448.pdf
) else (
    echo   BULUNAMADI: usul-esaslar\448.pdf
)
if exist "%ROK%\usul-esaslar\SOSYAL_GUVENLIK_KURUMU_KURUM_SAGLIK_KURULLARININ_GOREV_YETKI_CALISMA_USUL_VE_ESA.pdf" (
    ren "%ROK%\usul-esaslar\SOSYAL_GUVENLIK_KURUMU_KURUM_SAGLIK_KURULLARININ_GOREV_YETKI_CALISMA_USUL_VE_ESA.pdf" "SOSYAL_GUVENLIK_KURUMU_KURUM_SAGLIK.pdf"
    echo   OK: SOSYAL_GUVENLIK_KURUMU_KURUM_SAGLIK_KURULLARININ_GOREV_YETKI_CALISMA_USUL_VE_ESA.pdf ^> SOSYAL_GUVENLIK_KURUMU_KURUM_SAGLIK.pdf
) else (
    echo   BULUNAMADI: usul-esaslar\SOSYAL_GUVENLIK_KURUMU_KURUM_SAGLIK_KURULLARININ_GOREV_YETKI_CALISMA_USUL_VE_ESA.pdf
)
if exist "%ROK%\usul-esaslar\SAGLIK_HIZMET_SUNUCULARININ_ODEMELERININ_DURDURULMASINA_ILISKIN_USUL_VE_ESASLAR.pdf" (
    ren "%ROK%\usul-esaslar\SAGLIK_HIZMET_SUNUCULARININ_ODEMELERININ_DURDURULMASINA_ILISKIN_USUL_VE_ESASLAR.pdf" "SAGLIK_HIZMET_SUNUCULARININ_ODEMELERININ_DURDURULMASINA.pdf"
    echo   OK: SAGLIK_HIZMET_SUNUCULARININ_ODEMELERININ_DURDURULMASINA_ILISKIN_USUL_VE_ESASLAR.pdf ^> SAGLIK_HIZMET_SUNUCULARININ_ODEMELERININ_DURDURULMASINA.pdf
) else (
    echo   BULUNAMADI: usul-esaslar\SAGLIK_HIZMET_SUNUCULARININ_ODEMELERININ_DURDURULMASINA_ILISKIN_USUL_VE_ESASLAR.pdf
)
if exist "%ROK%\teblig\SOSYAL_GUVENLIK_KURUMU_SAGLIK_UYGULAMA_TEBLIGI.pdf" (
    ren "%ROK%\teblig\SOSYAL_GUVENLIK_KURUMU_SAGLIK_UYGULAMA_TEBLIGI.pdf" "SOSYAL_GUVENLIK_KURUMU_SAGLIK_UYGULAMA.pdf"
    echo   OK: SOSYAL_GUVENLIK_KURUMU_SAGLIK_UYGULAMA_TEBLIGI.pdf ^> SOSYAL_GUVENLIK_KURUMU_SAGLIK_UYGULAMA.pdf
) else (
    echo   BULUNAMADI: teblig\SOSYAL_GUVENLIK_KURUMU_SAGLIK_UYGULAMA_TEBLIGI.pdf
)
if exist "%ROK%\teblig\EV_HIZMETLERINDE_5510_SAYILI_KANUNUN_EK_9_UNCU_MADDESI_KAPSAMINDA_SIGORTALI_CALI.pdf" (
    ren "%ROK%\teblig\EV_HIZMETLERINDE_5510_SAYILI_KANUNUN_EK_9_UNCU_MADDESI_KAPSAMINDA_SIGORTALI_CALI.pdf" "EV_HIZMETLERINDE_5510_SAYILI_KANUNUN.pdf"
    echo   OK: EV_HIZMETLERINDE_5510_SAYILI_KANUNUN_EK_9_UNCU_MADDESI_KAPSAMINDA_SIGORTALI_CALI.pdf ^> EV_HIZMETLERINDE_5510_SAYILI_KANUNUN.pdf
) else (
    echo   BULUNAMADI: teblig\EV_HIZMETLERINDE_5510_SAYILI_KANUNUN_EK_9_UNCU_MADDESI_KAPSAMINDA_SIGORTALI_CALI.pdf
)
if exist "%ROK%\teblig\TARIMSAL_FAALIYETTE_BULUNANLARIN_PRIM_BORCLARININ_SATTIKLARI_TARIMSAL_URUN_BEDEL.pdf" (
    ren "%ROK%\teblig\TARIMSAL_FAALIYETTE_BULUNANLARIN_PRIM_BORCLARININ_SATTIKLARI_TARIMSAL_URUN_BEDEL.pdf" "TARIMSAL_FAALIYETTE_BULUNANLARIN_PRIM_BORCLARININ.pdf"
    echo   OK: TARIMSAL_FAALIYETTE_BULUNANLARIN_PRIM_BORCLARININ_SATTIKLARI_TARIMSAL_URUN_BEDEL.pdf ^> TARIMSAL_FAALIYETTE_BULUNANLARIN_PRIM_BORCLARININ.pdf
) else (
    echo   BULUNAMADI: teblig\TARIMSAL_FAALIYETTE_BULUNANLARIN_PRIM_BORCLARININ_SATTIKLARI_TARIMSAL_URUN_BEDEL.pdf
)
if exist "%ROK%\teblig\SOSYAL_GUVENLIK_KURUMU_ASGARI_ISCILIK_TESPIT_KOMISYONUNCA_BELIRLENEN_CESITLI_ISK.pdf" (
    ren "%ROK%\teblig\SOSYAL_GUVENLIK_KURUMU_ASGARI_ISCILIK_TESPIT_KOMISYONUNCA_BELIRLENEN_CESITLI_ISK.pdf" "SOSYAL_GUVENLIK_KURUMU_ASGARI_ISCILIK.pdf"
    echo   OK: SOSYAL_GUVENLIK_KURUMU_ASGARI_ISCILIK_TESPIT_KOMISYONUNCA_BELIRLENEN_CESITLI_ISK.pdf ^> SOSYAL_GUVENLIK_KURUMU_ASGARI_ISCILIK.pdf
) else (
    echo   BULUNAMADI: teblig\SOSYAL_GUVENLIK_KURUMU_ASGARI_ISCILIK_TESPIT_KOMISYONUNCA_BELIRLENEN_CESITLI_ISK.pdf
)
if exist "%ROK%\teblig\GELIR_AYLIK_ODEME_VE_YOKLAMA_ISLEMLERI_HAKKINDA_TEBLIG.pdf" (
    ren "%ROK%\teblig\GELIR_AYLIK_ODEME_VE_YOKLAMA_ISLEMLERI_HAKKINDA_TEBLIG.pdf" "GELIRAYLIK_ODEME_VE_YOKLAMA_ISLEMLERI.pdf"
    echo   OK: GELIR_AYLIK_ODEME_VE_YOKLAMA_ISLEMLERI_HAKKINDA_TEBLIG.pdf ^> GELIRAYLIK_ODEME_VE_YOKLAMA_ISLEMLERI.pdf
) else (
    echo   BULUNAMADI: teblig\GELIR_AYLIK_ODEME_VE_YOKLAMA_ISLEMLERI_HAKKINDA_TEBLIG.pdf
)
if exist "%ROK%\yonerge\SOSYAL_GUVENLIK_KURUMU_ON_MALI_KONTROL_ISLEMLERI_YONERGESI.pdf" (
    ren "%ROK%\yonerge\SOSYAL_GUVENLIK_KURUMU_ON_MALI_KONTROL_ISLEMLERI_YONERGESI.pdf" "SOSYAL_GUVENLIK_KURUMU_ON_MALI.pdf"
    echo   OK: SOSYAL_GUVENLIK_KURUMU_ON_MALI_KONTROL_ISLEMLERI_YONERGESI.pdf ^> SOSYAL_GUVENLIK_KURUMU_ON_MALI.pdf
) else (
    echo   BULUNAMADI: yonerge\SOSYAL_GUVENLIK_KURUMU_ON_MALI_KONTROL_ISLEMLERI_YONERGESI.pdf
)
if exist "%ROK%\yonerge\SOSYAL_GUVENLIK_KURUMU_RESMI_YAZISMA_KURALLARI_YONERGESI.pdf" (
    ren "%ROK%\yonerge\SOSYAL_GUVENLIK_KURUMU_RESMI_YAZISMA_KURALLARI_YONERGESI.pdf" "SOSYAL_GUVENLIK_KURUMU_RESMI_YAZISMA.pdf"
    echo   OK: SOSYAL_GUVENLIK_KURUMU_RESMI_YAZISMA_KURALLARI_YONERGESI.pdf ^> SOSYAL_GUVENLIK_KURUMU_RESMI_YAZISMA.pdf
) else (
    echo   BULUNAMADI: yonerge\SOSYAL_GUVENLIK_KURUMU_RESMI_YAZISMA_KURALLARI_YONERGESI.pdf
)
if exist "%ROK%\yonerge\SOSYAL_GUVENLIK_KURUMU_PERSONEL_KIMLIK_KARTI_YONERGESI.pdf" (
    ren "%ROK%\yonerge\SOSYAL_GUVENLIK_KURUMU_PERSONEL_KIMLIK_KARTI_YONERGESI.pdf" "SOSYAL_GUVENLIK_KURUMU_PERSONEL_KIMLIK.pdf"
    echo   OK: SOSYAL_GUVENLIK_KURUMU_PERSONEL_KIMLIK_KARTI_YONERGESI.pdf ^> SOSYAL_GUVENLIK_KURUMU_PERSONEL_KIMLIK.pdf
) else (
    echo   BULUNAMADI: yonerge\SOSYAL_GUVENLIK_KURUMU_PERSONEL_KIMLIK_KARTI_YONERGESI.pdf
)
if exist "%ROK%\yonerge\SOSYAL_GUVENLIK_KURUMU_SAGLIK_HIZMETLERI_FIYATLANDIRMA_ALT_KOMISYONU_CALISMA_YON.pdf" (
    ren "%ROK%\yonerge\SOSYAL_GUVENLIK_KURUMU_SAGLIK_HIZMETLERI_FIYATLANDIRMA_ALT_KOMISYONU_CALISMA_YON.pdf" "SOSYAL_GUVENLIK_KURUMU_SAGLIK_HIZMETLERI.pdf"
    echo   OK: SOSYAL_GUVENLIK_KURUMU_SAGLIK_HIZMETLERI_FIYATLANDIRMA_ALT_KOMISYONU_CALISMA_YON.pdf ^> SOSYAL_GUVENLIK_KURUMU_SAGLIK_HIZMETLERI.pdf
) else (
    echo   BULUNAMADI: yonerge\SOSYAL_GUVENLIK_KURUMU_SAGLIK_HIZMETLERI_FIYATLANDIRMA_ALT_KOMISYONU_CALISMA_YON.pdf
)
if exist "%ROK%\yonerge\SOSYAL_GUVENLIK_KURUMU_ON_ODEME_USUL_VE_ESASLARI_HAKKINDA_YONERGE.pdf" (
    ren "%ROK%\yonerge\SOSYAL_GUVENLIK_KURUMU_ON_ODEME_USUL_VE_ESASLARI_HAKKINDA_YONERGE.pdf" "SOSYAL_GUVENLIK_KURUMU_ON_ODEME.pdf"
    echo   OK: SOSYAL_GUVENLIK_KURUMU_ON_ODEME_USUL_VE_ESASLARI_HAKKINDA_YONERGE.pdf ^> SOSYAL_GUVENLIK_KURUMU_ON_ODEME.pdf
) else (
    echo   BULUNAMADI: yonerge\SOSYAL_GUVENLIK_KURUMU_ON_ODEME_USUL_VE_ESASLARI_HAKKINDA_YONERGE.pdf
)
if exist "%ROK%\yonerge\SOSYAL_GUVENLIK_KURUMU_HIZMET_ICI_EGITIM_YONETMELIGI.pdf" (
    ren "%ROK%\yonerge\SOSYAL_GUVENLIK_KURUMU_HIZMET_ICI_EGITIM_YONETMELIGI.pdf" "SOSYAL_GUVENLIK_KURUMU_HIZMET_ICI.pdf"
    echo   OK: SOSYAL_GUVENLIK_KURUMU_HIZMET_ICI_EGITIM_YONETMELIGI.pdf ^> SOSYAL_GUVENLIK_KURUMU_HIZMET_ICI.pdf
) else (
    echo   BULUNAMADI: yonerge\SOSYAL_GUVENLIK_KURUMU_HIZMET_ICI_EGITIM_YONETMELIGI.pdf
)
if exist "%ROK%\ic-emir\11.pdf" (
    ren "%ROK%\ic-emir\11.pdf" "2016_2.pdf"
    echo   OK: 11.pdf ^> 2016_2.pdf
) else (
    echo   BULUNAMADI: ic-emir\11.pdf
)
if exist "%ROK%\ic-emir\1.pdf" (
    ren "%ROK%\ic-emir\1.pdf" "2016_1.pdf"
    echo   OK: 1.pdf ^> 2016_1.pdf
) else (
    echo   BULUNAMADI: ic-emir\1.pdf
)
if exist "%ROK%\ic-emir\11.pdf" (
    ren "%ROK%\ic-emir\11.pdf" "2015_11.pdf"
    echo   OK: 11.pdf ^> 2015_11.pdf
) else (
    echo   BULUNAMADI: ic-emir\11.pdf
)
if exist "%ROK%\ic-emir\9.pdf" (
    ren "%ROK%\ic-emir\9.pdf" "2015_9.pdf"
    echo   OK: 9.pdf ^> 2015_9.pdf
) else (
    echo   BULUNAMADI: ic-emir\9.pdf
)
if exist "%ROK%\talimat\SOSYAL_GUVENLIK_KURUMU_EVRAK_ISLEMLERI_HAKKINDA_TALIMAT.pdf" (
    ren "%ROK%\talimat\SOSYAL_GUVENLIK_KURUMU_EVRAK_ISLEMLERI_HAKKINDA_TALIMAT.pdf" "SOSYAL_GUVENLIK_KURUMU_EVRAK_ISLEMLERI.pdf"
    echo   OK: SOSYAL_GUVENLIK_KURUMU_EVRAK_ISLEMLERI_HAKKINDA_TALIMAT.pdf ^> SOSYAL_GUVENLIK_KURUMU_EVRAK_ISLEMLERI.pdf
) else (
    echo   BULUNAMADI: talimat\SOSYAL_GUVENLIK_KURUMU_EVRAK_ISLEMLERI_HAKKINDA_TALIMAT.pdf
)
if exist "%ROK%\genel-yazi\5510_SAYILI_KANUNUN_21_INCI_MADDESININ_BESINCI_FIKRASI_UYGULAMASI.pdf" (
    ren "%ROK%\genel-yazi\5510_SAYILI_KANUNUN_21_INCI_MADDESININ_BESINCI_FIKRASI_UYGULAMASI.pdf" "5510_SAYILI_KANUNUN_21_INCI.pdf"
    echo   OK: 5510_SAYILI_KANUNUN_21_INCI_MADDESININ_BESINCI_FIKRASI_UYGULAMASI.pdf ^> 5510_SAYILI_KANUNUN_21_INCI.pdf
) else (
    echo   BULUNAMADI: genel-yazi\5510_SAYILI_KANUNUN_21_INCI_MADDESININ_BESINCI_FIKRASI_UYGULAMASI.pdf
)
if exist "%ROK%\genel-yazi\GECICI_IS_ILISKISI_MEVZUATINA_AYKIRI_ISLEM_YAPILMASI.pdf" (
    ren "%ROK%\genel-yazi\GECICI_IS_ILISKISI_MEVZUATINA_AYKIRI_ISLEM_YAPILMASI.pdf" "GECICI_IS_ILISKISI_MEVZUATINA_AYKIRI.pdf"
    echo   OK: GECICI_IS_ILISKISI_MEVZUATINA_AYKIRI_ISLEM_YAPILMASI.pdf ^> GECICI_IS_ILISKISI_MEVZUATINA_AYKIRI.pdf
) else (
    echo   BULUNAMADI: genel-yazi\GECICI_IS_ILISKISI_MEVZUATINA_AYKIRI_ISLEM_YAPILMASI.pdf
)
if exist "%ROK%\genel-yazi\EK_9_KAPSAMINDAKI_EV_HIZMETLERI_ISYERLERININ_KOLAY_ISVERENLIK_KAPSAMINA_ALINMASI.pdf" (
    ren "%ROK%\genel-yazi\EK_9_KAPSAMINDAKI_EV_HIZMETLERI_ISYERLERININ_KOLAY_ISVERENLIK_KAPSAMINA_ALINMASI.pdf" "EK9_KAPSAMINDAKI_EV_HIZMETLERI_ISYERLERININ.pdf"
    echo   OK: EK_9_KAPSAMINDAKI_EV_HIZMETLERI_ISYERLERININ_KOLAY_ISVERENLIK_KAPSAMINA_ALINMASI.pdf ^> EK9_KAPSAMINDAKI_EV_HIZMETLERI_ISYERLERININ.pdf
) else (
    echo   BULUNAMADI: genel-yazi\EK_9_KAPSAMINDAKI_EV_HIZMETLERI_ISYERLERININ_KOLAY_ISVERENLIK_KAPSAMINA_ALINMASI.pdf
)
if exist "%ROK%\genel-yazi\IS_KAZASINA_UGRAYAN_SIGORTALILAR_ICIN_DUZENLENEN_EK_APHB_DE_FAYDALANILAN_ASGARI_.pdf" (
    ren "%ROK%\genel-yazi\IS_KAZASINA_UGRAYAN_SIGORTALILAR_ICIN_DUZENLENEN_EK_APHB_DE_FAYDALANILAN_ASGARI_.pdf" "IS_KAZASINA_UGRAYAN_SIGORTALILAR_ICIN.pdf"
    echo   OK: IS_KAZASINA_UGRAYAN_SIGORTALILAR_ICIN_DUZENLENEN_EK_APHB_DE_FAYDALANILAN_ASGARI_.pdf ^> IS_KAZASINA_UGRAYAN_SIGORTALILAR_ICIN.pdf
) else (
    echo   BULUNAMADI: genel-yazi\IS_KAZASINA_UGRAYAN_SIGORTALILAR_ICIN_DUZENLENEN_EK_APHB_DE_FAYDALANILAN_ASGARI_.pdf
)

echo.
echo TAMAMLANDI!
pause