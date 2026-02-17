# USTADIM Mevzuat - Klasör ve Dosya Yeniden Adlandırma Scripti
# C:\Users\CREATOR\Dropbox\PC\Desktop\MEVZUAT klasöründe çalıştır
# PowerShell olarak çalıştır

$kök = "C:\Users\CREATOR\Dropbox\PC\Desktop\MEVZUAT"

# === ADIM 1: Klasörleri yeniden adlandır ===
Write-Host "Klasörler yeniden adlandırılıyor..."
if (Test-Path "$kök\cumhurbaşkanlığı kararnamesi") { Rename-Item "$kök\cumhurbaşkanlığı kararnamesi" "cbk"; Write-Host "  ✓ cumhurbaşkanlığı kararnamesi → cbk" }
if (Test-Path "$kök\yönetmelik") { Rename-Item "$kök\yönetmelik" "yonetmelik"; Write-Host "  ✓ yönetmelik → yonetmelik" }
if (Test-Path "$kök\tüzük") { Rename-Item "$kök\tüzük" "tuzuk"; Write-Host "  ✓ tüzük → tuzuk" }
if (Test-Path "$kök\usul ve esaslar") { Rename-Item "$kök\usul ve esaslar" "usul-esaslar"; Write-Host "  ✓ usul ve esaslar → usul-esaslar" }
if (Test-Path "$kök\tebliğ") { Rename-Item "$kök\tebliğ" "teblig"; Write-Host "  ✓ tebliğ → teblig" }
if (Test-Path "$kök\yönerge") { Rename-Item "$kök\yönerge" "yonerge"; Write-Host "  ✓ yönerge → yonerge" }
if (Test-Path "$kök\iç emir") { Rename-Item "$kök\iç emir" "ic-emir"; Write-Host "  ✓ iç emir → ic-emir" }
if (Test-Path "$kök\genel yazı-talimat") { Rename-Item "$kök\genel yazı-talimat" "genel-yazi"; Write-Host "  ✓ genel yazı-talimat → genel-yazi" }

# === ADIM 2: PDF dosyalarını yeniden adlandır ===
Write-Host "PDF dosyaları yeniden adlandırılıyor..."

# ORGANİZE SANAYİ BÖLGELERİ KANUNU
if (Test-Path "$kök\kanun\4562.pdf") { Rename-Item "$kök\kanun\4562.pdf" "4562_2.pdf"; Write-Host "  ✓ 4562.pdf → 4562_2.pdf" } else { Write-Host "  ⚠ Bulunamadı: kanun\4562.pdf" }
# 65 YAŞINI DOLDURMUŞ MUHTAÇ, GÜÇSÜZ VE KİMSESİZ TÜR
if (Test-Path "$kök\kanun\2022.pdf") { Rename-Item "$kök\kanun\2022.pdf" "2022_2.pdf"; Write-Host "  ✓ 2022.pdf → 2022_2.pdf" } else { Write-Host "  ⚠ Bulunamadı: kanun\2022.pdf" }
# DEVLET MEMURLARINA VERİLECEK HASTALIK RAPORLARI  İ
if (Test-Path "$kök\yonetmelik\20112226.pdf") { Rename-Item "$kök\yonetmelik\20112226.pdf" "20112226_2.pdf"; Write-Host "  ✓ 20112226.pdf → 20112226_2.pdf" } else { Write-Host "  ⚠ Bulunamadı: yonetmelik\20112226.pdf" }
# SOSYAL GÜVENLİK KURUMU HİZMET İÇİ EĞİTİM YÖNETMELİ
if (Test-Path "$kök\yonetmelik\SOSYAL_GUVENLIK_KURUMU_HIZMET_ICI_EGITIM_YONETMELIGI.pdf") { Rename-Item "$kök\yonetmelik\SOSYAL_GUVENLIK_KURUMU_HIZMET_ICI_EGITIM_YONETMELIGI.pdf" "191.pdf"; Write-Host "  ✓ SOSYAL_GUVENLIK_KURUMU_HIZMET_ICI_EGITIM_YONETMELIGI.pdf → 191.pdf" } else { Write-Host "  ⚠ Bulunamadı: yonetmelik\SOSYAL_GUVENLIK_KURUMU_HIZMET_ICI_EGITIM_YONETMELIGI.pdf" }
# SOSYAL GÜVENLİK KURUMU MERKEZ TEŞKİLATI ÇALIŞMA YÖ
if (Test-Path "$kök\yonetmelik\424.pdf") { Rename-Item "$kök\yonetmelik\424.pdf" "2016_424.pdf"; Write-Host "  ✓ 424.pdf → 2016_424.pdf" } else { Write-Host "  ⚠ Bulunamadı: yonetmelik\424.pdf" }
# İŞÇİ SAĞLIĞI VE İŞ GÜVENLİĞİ TÜZÜĞÜ
if (Test-Path "$kök\tuzuk\ISCI_SAGLIGI_VE_IS_GUVENLIGI_TUZUGU.pdf") { Rename-Item "$kök\tuzuk\ISCI_SAGLIGI_VE_IS_GUVENLIGI_TUZUGU.pdf" "ISCI_SAGLIGI_VE_IS_GUVENLIGI.pdf"; Write-Host "  ✓ ISCI_SAGLIGI_VE_IS_GUVENLIGI_TUZUGU.pdf → ISCI_SAGLIGI_VE_IS_GUVENLIGI.pdf" } else { Write-Host "  ⚠ Bulunamadı: tuzuk\ISCI_SAGLIGI_VE_IS_GUVENLIGI_TUZUGU.pdf" }
# YAPI İŞLERİNDE İŞÇİ SAĞLIĞI VE İŞ GÜVENLİĞİ TÜZÜĞÜ
if (Test-Path "$kök\tuzuk\YAPI_ISLERINDE_ISCI_SAGLIGI_VE_IS_GUVENLIGI_TUZUGU.pdf") { Rename-Item "$kök\tuzuk\YAPI_ISLERINDE_ISCI_SAGLIGI_VE_IS_GUVENLIGI_TUZUGU.pdf" "YAPI_ISLERINDE_ISCI_SAGLIGI_VE.pdf"; Write-Host "  ✓ YAPI_ISLERINDE_ISCI_SAGLIGI_VE_IS_GUVENLIGI_TUZUGU.pdf → YAPI_ISLERINDE_ISCI_SAGLIGI_VE.pdf" } else { Write-Host "  ⚠ Bulunamadı: tuzuk\YAPI_ISLERINDE_ISCI_SAGLIGI_VE_IS_GUVENLIGI_TUZUGU.pdf" }
# SİGORTALILIK İŞLEMLERİ HAKKINDA GENELGE
if (Test-Path "$kök\genelge\2013.11.pdf") { Rename-Item "$kök\genelge\2013.11.pdf" "2013_11.pdf"; Write-Host "  ✓ 2013.11.pdf → 2013_11.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2013.11.pdf" }
# İKİ PUAN İNDİRİM KONULU GENELGE
if (Test-Path "$kök\genelge\2026.1.pdf") { Rename-Item "$kök\genelge\2026.1.pdf" "2026_1.pdf"; Write-Host "  ✓ 2026.1.pdf → 2026_1.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2026.1.pdf" }
# Prime Esas Kazançların Alt ve Üst Sınırları ile Ba
if (Test-Path "$kök\genelge\2026.2.pdf") { Rename-Item "$kök\genelge\2026.2.pdf" "2026_2.pdf"; Write-Host "  ✓ 2026.2.pdf → 2026_2.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2026.2.pdf" }
# Asgari Ücret Desteği
if (Test-Path "$kök\genelge\2025.9.pdf") { Rename-Item "$kök\genelge\2025.9.pdf" "2025_9.pdf"; Write-Host "  ✓ 2025.9.pdf → 2025_9.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2025.9.pdf" }
# Yemek Bedeli
if (Test-Path "$kök\genelge\2024.17.pdf") { Rename-Item "$kök\genelge\2024.17.pdf" "2024_17.pdf"; Write-Host "  ✓ 2024.17.pdf → 2024_17.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2024.17.pdf" }
# Dört Puanlık İndirim
if (Test-Path "$kök\genelge\2025.6.pdf") { Rename-Item "$kök\genelge\2025.6.pdf" "2025_6.pdf"; Write-Host "  ✓ 2025.6.pdf → 2025_6.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2025.6.pdf" }
# Sağlık Uygulaması Bulunan Sözleşmeli Ülkelerde İka
if (Test-Path "$kök\genelge\2024.13.pdf") { Rename-Item "$kök\genelge\2024.13.pdf" "2024_13.pdf"; Write-Host "  ✓ 2024.13.pdf → 2024_13.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2024.13.pdf" }
# Tecil İşlemlerine İlişkin Değişiklikler
if (Test-Path "$kök\genelge\2024.8.pdf") { Rename-Item "$kök\genelge\2024.8.pdf" "2024_8.pdf"; Write-Host "  ✓ 2024.8.pdf → 2024_8.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2024.8.pdf" }
# İlişiksizlik belgesi
if (Test-Path "$kök\genelge\2011.13.pdf") { Rename-Item "$kök\genelge\2011.13.pdf" "2011_13.pdf"; Write-Host "  ✓ 2011.13.pdf → 2011_13.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2011.13.pdf" }
# Koronavirüs (COVID-19)
if (Test-Path "$kök\genelge\2020.12.pdf") { Rename-Item "$kök\genelge\2020.12.pdf" "2020_12.pdf"; Write-Host "  ✓ 2020.12.pdf → 2020_12.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2020.12.pdf" }
# İdari Para Cezası Genelgesi
if (Test-Path "$kök\genelge\2020.8.pdf") { Rename-Item "$kök\genelge\2020.8.pdf" "2020_8.pdf"; Write-Host "  ✓ 2020.8.pdf → 2020_8.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2020.8.pdf" }
# GSS TESCİL VE PRİM İŞLEMLERİ
if (Test-Path "$kök\genelge\2019.17.pdf") { Rename-Item "$kök\genelge\2019.17.pdf" "2019_17.pdf"; Write-Host "  ✓ 2019.17.pdf → 2019_17.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2019.17.pdf" }
# Yurt Dışı Borçlanma İşlemleri
if (Test-Path "$kök\genelge\2019.16.pdf") { Rename-Item "$kök\genelge\2019.16.pdf" "2019_16.pdf"; Write-Host "  ✓ 2019.16.pdf → 2019_16.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2019.16.pdf" }
# EMEKLİLİK İŞLEMLERİ
if (Test-Path "$kök\genelge\2018.38.pdf") { Rename-Item "$kök\genelge\2018.38.pdf" "2018_38.pdf"; Write-Host "  ✓ 2018.38.pdf → 2018_38.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2018.38.pdf" }
# HARCAMA YETKİSİ
if (Test-Path "$kök\genelge\2018.37.pdf") { Rename-Item "$kök\genelge\2018.37.pdf" "2018_37.pdf"; Write-Host "  ✓ 2018.37.pdf → 2018_37.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2018.37.pdf" }
# Kayıt dışı istihdam ile mücadele
if (Test-Path "$kök\genelge\2015.25.pdf") { Rename-Item "$kök\genelge\2015.25.pdf" "2015_25.pdf"; Write-Host "  ✓ 2015.25.pdf → 2015_25.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2015.25.pdf" }
# UÇAKLA SEYAHAT
if (Test-Path "$kök\genelge\2017.31.pdf") { Rename-Item "$kök\genelge\2017.31.pdf" "2017_31.pdf"; Write-Host "  ✓ 2017.31.pdf → 2017_31.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2017.31.pdf" }
# İsteğe Bağlı Sigorta İşlemleri
if (Test-Path "$kök\genelge\2010.117.pdf") { Rename-Item "$kök\genelge\2010.117.pdf" "2010_117.pdf"; Write-Host "  ✓ 2010.117.pdf → 2010_117.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2010.117.pdf" }
# Yersiz Ödemelerin Tahsili
if (Test-Path "$kök\genelge\2017.25.pdf") { Rename-Item "$kök\genelge\2017.25.pdf" "2017_25.pdf"; Write-Host "  ✓ 2017.25.pdf → 2017_25.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2017.25.pdf" }
# KURUM PERSONELİNİN SORUMLULUĞU
if (Test-Path "$kök\genelge\2016.15.pdf") { Rename-Item "$kök\genelge\2016.15.pdf" "2016_15.pdf"; Write-Host "  ✓ 2016.15.pdf → 2016_15.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2016.15.pdf" }
# Lise/dengi öğrenim ve yükseköğrenim mezunu öğrenci
if (Test-Path "$kök\genelge\2016.6.pdf") { Rename-Item "$kök\genelge\2016.6.pdf" "2016_6.pdf"; Write-Host "  ✓ 2016.6.pdf → 2016_6.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2016.6.pdf" }
# 4/b Kapsamındaki Sigortalılığın Durdurulması
if (Test-Path "$kök\genelge\2015.13.pdf") { Rename-Item "$kök\genelge\2015.13.pdf" "2015_13.pdf"; Write-Host "  ✓ 2015.13.pdf → 2015_13.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2015.13.pdf" }
# Yurtdışı Sahte Hizmet Belgeleri
if (Test-Path "$kök\genelge\2015.2.pdf") { Rename-Item "$kök\genelge\2015.2.pdf" "2015_2.pdf"; Write-Host "  ✓ 2015.2.pdf → 2015_2.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2015.2.pdf" }
# Geçersiz sigortalılık statüsünde yatan primlerin g
if (Test-Path "$kök\genelge\2014.28.pdf") { Rename-Item "$kök\genelge\2014.28.pdf" "2014_28.pdf"; Write-Host "  ✓ 2014.28.pdf → 2014_28.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2014.28.pdf" }
# Adli Vaka ve Trafik Kazası İşlemleri
if (Test-Path "$kök\genelge\2014.20.pdf") { Rename-Item "$kök\genelge\2014.20.pdf" "2014_20.pdf"; Write-Host "  ✓ 2014.20.pdf → 2014_20.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2014.20.pdf" }
# Fizik Tedavi ve Rehabilitasyon Uygulamaları
if (Test-Path "$kök\genelge\2014.10.pdf") { Rename-Item "$kök\genelge\2014.10.pdf" "2014_10.pdf"; Write-Host "  ✓ 2014.10.pdf → 2014_10.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2014.10.pdf" }
# Hasta Tarafından Temin Edilen Tıbbi Malzeme Bedell
if (Test-Path "$kök\genelge\2013.37.pdf") { Rename-Item "$kök\genelge\2013.37.pdf" "2013_37.pdf"; Write-Host "  ✓ 2013.37.pdf → 2013_37.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2013.37.pdf" }
# Maluliyet tespiti işlemleri
if (Test-Path "$kök\genelge\2013.34.pdf") { Rename-Item "$kök\genelge\2013.34.pdf" "2013_34.pdf"; Write-Host "  ✓ 2013.34.pdf → 2013_34.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2013.34.pdf" }
# SGDP Yapılandırma
if (Test-Path "$kök\genelge\2018.8.pdf") { Rename-Item "$kök\genelge\2018.8.pdf" "2018_8.pdf"; Write-Host "  ✓ 2018.8.pdf → 2018_8.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2018.8.pdf" }
# Disiplin Amirleri tarafından verilen cezaların ilg
if (Test-Path "$kök\genelge\2012.19.pdf") { Rename-Item "$kök\genelge\2012.19.pdf" "2012_19.pdf"; Write-Host "  ✓ 2012.19.pdf → 2012_19.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2012.19.pdf" }
# İcra takip haciz ve satış işlemleri
if (Test-Path "$kök\genelge\2011.53.pdf") { Rename-Item "$kök\genelge\2011.53.pdf" "2011_53.pdf"; Write-Host "  ✓ 2011.53.pdf → 2011_53.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2011.53.pdf" }
# Yurt dışı tedavi işlemleri
if (Test-Path "$kök\genelge\2011.46.pdf") { Rename-Item "$kök\genelge\2011.46.pdf" "2011_46.pdf"; Write-Host "  ✓ 2011.46.pdf → 2011_46.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2011.46.pdf" }
# Yurtdışı borçlanma ve tahsis işlemleri
if (Test-Path "$kök\genelge\2011.48.pdf") { Rename-Item "$kök\genelge\2011.48.pdf" "2011_48.pdf"; Write-Host "  ✓ 2011.48.pdf → 2011_48.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2011.48.pdf" }
# Emeklilik Belgeleri, Emeklilik İkramiyesi
if (Test-Path "$kök\genelge\2011.32.pdf") { Rename-Item "$kök\genelge\2011.32.pdf" "2011_32.pdf"; Write-Host "  ✓ 2011.32.pdf → 2011_32.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2011.32.pdf" }
# Aktüerya ve Fon Yönetimi İşlemleri
if (Test-Path "$kök\genelge\2011.33.pdf") { Rename-Item "$kök\genelge\2011.33.pdf" "2011_33.pdf"; Write-Host "  ✓ 2011.33.pdf → 2011_33.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2011.33.pdf" }
# 5434 sayılı Kanun ve 5510 sayılı Kanunun 4 (l) (c)
if (Test-Path "$kök\genelge\2011.19.pdf") { Rename-Item "$kök\genelge\2011.19.pdf" "2011_19.pdf"; Write-Host "  ✓ 2011.19.pdf → 2011_19.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2011.19.pdf" }
# Yurtdışı sigortalılarının malullük aylıklarında il
if (Test-Path "$kök\genelge\2011.9.pdf") { Rename-Item "$kök\genelge\2011.9.pdf" "2011_9.pdf"; Write-Host "  ✓ 2011.9.pdf → 2011_9.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2011.9.pdf" }
# Ödeme İşlemleri
if (Test-Path "$kök\genelge\2010.52.pdf") { Rename-Item "$kök\genelge\2010.52.pdf" "2010_52.pdf"; Write-Host "  ✓ 2010.52.pdf → 2010_52.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2010.52.pdf" }
# Genel Sağlık Sigortası Uygulamaları
if (Test-Path "$kök\genelge\2008.86.pdf") { Rename-Item "$kök\genelge\2008.86.pdf" "2008_86.pdf"; Write-Host "  ✓ 2008.86.pdf → 2008_86.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2008.86.pdf" }
# Rehberlik ve Teftiş Başkanlığına Yetki Devri
if (Test-Path "$kök\genelge\2008.22.pdf") { Rename-Item "$kök\genelge\2008.22.pdf" "2008_22.pdf"; Write-Host "  ✓ 2008.22.pdf → 2008_22.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2008.22.pdf" }
# Sosyal Güvenlik Denetmenleri Denetim Standartları
if (Test-Path "$kök\genelge\2013.5.pdf") { Rename-Item "$kök\genelge\2013.5.pdf" "2013_5.pdf"; Write-Host "  ✓ 2013.5.pdf → 2013_5.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2013.5.pdf" }
# İşveren İşlemleri Genelgesi
if (Test-Path "$kök\genelge\2020.20.pdf") { Rename-Item "$kök\genelge\2020.20.pdf" "2020_20.pdf"; Write-Host "  ✓ 2020.20.pdf → 2020_20.pdf" } else { Write-Host "  ⚠ Bulunamadı: genelge\2020.20.pdf" }
# Sosyal Güvenlik Kurumu hukuk müşavirliği çalışma u
if (Test-Path "$kök\usul-esaslar\430.pdf") { Rename-Item "$kök\usul-esaslar\430.pdf" "2016_430.pdf"; Write-Host "  ✓ 430.pdf → 2016_430.pdf" } else { Write-Host "  ⚠ Bulunamadı: usul-esaslar\430.pdf" }
# Sosyal Güvenlik Kurumu işyerinde psikolojik tacizi
if (Test-Path "$kök\usul-esaslar\62.pdf") { Rename-Item "$kök\usul-esaslar\62.pdf" "2016_62.pdf"; Write-Host "  ✓ 62.pdf → 2016_62.pdf" } else { Write-Host "  ⚠ Bulunamadı: usul-esaslar\62.pdf" }
# SOSYAL GÜVENLİK KURUMU İLAÇ GERİ ÖDEME BAŞVURULARI
if (Test-Path "$kök\usul-esaslar\56.pdf") { Rename-Item "$kök\usul-esaslar\56.pdf" "2016_56.pdf"; Write-Host "  ✓ 56.pdf → 2016_56.pdf" } else { Write-Host "  ⚠ Bulunamadı: usul-esaslar\56.pdf" }
# SOSYAL GÜVENLİK KURUMU diş fatura inceleme usul ve
if (Test-Path "$kök\usul-esaslar\SOSYAL_GUVENLIK_KURUMU_DIS_FATURA_INCELEME_USUL_VE_ESASLARI.pdf") { Rename-Item "$kök\usul-esaslar\SOSYAL_GUVENLIK_KURUMU_DIS_FATURA_INCELEME_USUL_VE_ESASLARI.pdf" "SOSYAL_GUVENLIK_KURUMU_DIS_FATURA.pdf"; Write-Host "  ✓ SOSYAL_GUVENLIK_KURUMU_DIS_FATURA_INCELEME_USUL_VE_ESASLARI.pdf → SOSYAL_GUVENLIK_KURUMU_DIS_FATURA.pdf" } else { Write-Host "  ⚠ Bulunamadı: usul-esaslar\SOSYAL_GUVENLIK_KURUMU_DIS_FATURA_INCELEME_USUL_VE_ESASLARI.pdf" }
# sOSYAL GÜVENLİK KURUMU KONUT TAHSİS İŞLEMLERİ İLE 
if (Test-Path "$kök\usul-esaslar\SOSYAL_GUVENLIK_KURUMU_KONUT_TAHSIS_ISLEMLERI_ILE_ILGILI_USUL_VE_ESASLAR.pdf") { Rename-Item "$kök\usul-esaslar\SOSYAL_GUVENLIK_KURUMU_KONUT_TAHSIS_ISLEMLERI_ILE_ILGILI_USUL_VE_ESASLAR.pdf" "SOSYAL_GUVENLIK_KURUMU_KONUT_TAHSIS.pdf"; Write-Host "  ✓ SOSYAL_GUVENLIK_KURUMU_KONUT_TAHSIS_ISLEMLERI_ILE_ILGILI_USUL_VE_ESASLAR.pdf → SOSYAL_GUVENLIK_KURUMU_KONUT_TAHSIS.pdf" } else { Write-Host "  ⚠ Bulunamadı: usul-esaslar\SOSYAL_GUVENLIK_KURUMU_KONUT_TAHSIS_ISLEMLERI_ILE_ILGILI_USUL_VE_ESASLAR.pdf" }
# SOSYAL GÜVENLİK KURUMU PERSONEL YEMEK HİZMETLERİNİ
if (Test-Path "$kök\usul-esaslar\SOSYAL_GUVENLIK_KURUMU_PERSONEL_YEMEK_HIZMETLERININ_SUNULMASINA_ILISKIN_USUL_VE_.pdf") { Rename-Item "$kök\usul-esaslar\SOSYAL_GUVENLIK_KURUMU_PERSONEL_YEMEK_HIZMETLERININ_SUNULMASINA_ILISKIN_USUL_VE_.pdf" "SOSYAL_GUVENLIK_KURUMU_PERSONEL_YEMEK.pdf"; Write-Host "  ✓ SOSYAL_GUVENLIK_KURUMU_PERSONEL_YEMEK_HIZMETLERININ_SUNULMASINA_ILISKIN_USUL_VE_.pdf → SOSYAL_GUVENLIK_KURUMU_PERSONEL_YEMEK.pdf" } else { Write-Host "  ⚠ Bulunamadı: usul-esaslar\SOSYAL_GUVENLIK_KURUMU_PERSONEL_YEMEK_HIZMETLERININ_SUNULMASINA_ILISKIN_USUL_VE_.pdf" }
# SOSYAL GÜVENLİK KURUMU DİSİPLİN SORUŞTURMASI VE Dİ
if (Test-Path "$kök\usul-esaslar\448.pdf") { Rename-Item "$kök\usul-esaslar\448.pdf" "2015_448.pdf"; Write-Host "  ✓ 448.pdf → 2015_448.pdf" } else { Write-Host "  ⚠ Bulunamadı: usul-esaslar\448.pdf" }
# SOSYAL GüVENLIK KURUMU KURUM SAğLIK KURULLARININ G
if (Test-Path "$kök\usul-esaslar\SOSYAL_GUVENLIK_KURUMU_KURUM_SAGLIK_KURULLARININ_GOREV_YETKI_CALISMA_USUL_VE_ESA.pdf") { Rename-Item "$kök\usul-esaslar\SOSYAL_GUVENLIK_KURUMU_KURUM_SAGLIK_KURULLARININ_GOREV_YETKI_CALISMA_USUL_VE_ESA.pdf" "SOSYAL_GUVENLIK_KURUMU_KURUM_SAGLIK.pdf"; Write-Host "  ✓ SOSYAL_GUVENLIK_KURUMU_KURUM_SAGLIK_KURULLARININ_GOREV_YETKI_CALISMA_USUL_VE_ESA.pdf → SOSYAL_GUVENLIK_KURUMU_KURUM_SAGLIK.pdf" } else { Write-Host "  ⚠ Bulunamadı: usul-esaslar\SOSYAL_GUVENLIK_KURUMU_KURUM_SAGLIK_KURULLARININ_GOREV_YETKI_CALISMA_USUL_VE_ESA.pdf" }
# SAĞLIK HİZMET SUNUCULARININ ÖDEMELERİNİN DURDURULM
if (Test-Path "$kök\usul-esaslar\SAGLIK_HIZMET_SUNUCULARININ_ODEMELERININ_DURDURULMASINA_ILISKIN_USUL_VE_ESASLAR.pdf") { Rename-Item "$kök\usul-esaslar\SAGLIK_HIZMET_SUNUCULARININ_ODEMELERININ_DURDURULMASINA_ILISKIN_USUL_VE_ESASLAR.pdf" "SAGLIK_HIZMET_SUNUCULARININ_ODEMELERININ_DURDURULMASINA.pdf"; Write-Host "  ✓ SAGLIK_HIZMET_SUNUCULARININ_ODEMELERININ_DURDURULMASINA_ILISKIN_USUL_VE_ESASLAR.pdf → SAGLIK_HIZMET_SUNUCULARININ_ODEMELERININ_DURDURULMASINA.pdf" } else { Write-Host "  ⚠ Bulunamadı: usul-esaslar\SAGLIK_HIZMET_SUNUCULARININ_ODEMELERININ_DURDURULMASINA_ILISKIN_USUL_VE_ESASLAR.pdf" }
# SOSYAL GÜVENLİK KURUMU SAĞLIK UYGULAMA TEBLİĞİ
if (Test-Path "$kök\teblig\SOSYAL_GUVENLIK_KURUMU_SAGLIK_UYGULAMA_TEBLIGI.pdf") { Rename-Item "$kök\teblig\SOSYAL_GUVENLIK_KURUMU_SAGLIK_UYGULAMA_TEBLIGI.pdf" "SOSYAL_GUVENLIK_KURUMU_SAGLIK_UYGULAMA.pdf"; Write-Host "  ✓ SOSYAL_GUVENLIK_KURUMU_SAGLIK_UYGULAMA_TEBLIGI.pdf → SOSYAL_GUVENLIK_KURUMU_SAGLIK_UYGULAMA.pdf" } else { Write-Host "  ⚠ Bulunamadı: teblig\SOSYAL_GUVENLIK_KURUMU_SAGLIK_UYGULAMA_TEBLIGI.pdf" }
# EV HİZMETLERİNDE 5510 SAYILI KANUNUN EK 9 UNCU MAD
if (Test-Path "$kök\teblig\EV_HIZMETLERINDE_5510_SAYILI_KANUNUN_EK_9_UNCU_MADDESI_KAPSAMINDA_SIGORTALI_CALI.pdf") { Rename-Item "$kök\teblig\EV_HIZMETLERINDE_5510_SAYILI_KANUNUN_EK_9_UNCU_MADDESI_KAPSAMINDA_SIGORTALI_CALI.pdf" "EV_HIZMETLERINDE_5510_SAYILI_KANUNUN.pdf"; Write-Host "  ✓ EV_HIZMETLERINDE_5510_SAYILI_KANUNUN_EK_9_UNCU_MADDESI_KAPSAMINDA_SIGORTALI_CALI.pdf → EV_HIZMETLERINDE_5510_SAYILI_KANUNUN.pdf" } else { Write-Host "  ⚠ Bulunamadı: teblig\EV_HIZMETLERINDE_5510_SAYILI_KANUNUN_EK_9_UNCU_MADDESI_KAPSAMINDA_SIGORTALI_CALI.pdf" }
# TARIMSAL FAALİYETTE BULUNANLARIN PRİM BORÇLARININ 
if (Test-Path "$kök\teblig\TARIMSAL_FAALIYETTE_BULUNANLARIN_PRIM_BORCLARININ_SATTIKLARI_TARIMSAL_URUN_BEDEL.pdf") { Rename-Item "$kök\teblig\TARIMSAL_FAALIYETTE_BULUNANLARIN_PRIM_BORCLARININ_SATTIKLARI_TARIMSAL_URUN_BEDEL.pdf" "TARIMSAL_FAALIYETTE_BULUNANLARIN_PRIM_BORCLARININ.pdf"; Write-Host "  ✓ TARIMSAL_FAALIYETTE_BULUNANLARIN_PRIM_BORCLARININ_SATTIKLARI_TARIMSAL_URUN_BEDEL.pdf → TARIMSAL_FAALIYETTE_BULUNANLARIN_PRIM_BORCLARININ.pdf" } else { Write-Host "  ⚠ Bulunamadı: teblig\TARIMSAL_FAALIYETTE_BULUNANLARIN_PRIM_BORCLARININ_SATTIKLARI_TARIMSAL_URUN_BEDEL.pdf" }
# SOSYAL GÜVENLİK KURUMU ASGARİ İŞÇİLİK TESPİT KOMİS
if (Test-Path "$kök\teblig\SOSYAL_GUVENLIK_KURUMU_ASGARI_ISCILIK_TESPIT_KOMISYONUNCA_BELIRLENEN_CESITLI_ISK.pdf") { Rename-Item "$kök\teblig\SOSYAL_GUVENLIK_KURUMU_ASGARI_ISCILIK_TESPIT_KOMISYONUNCA_BELIRLENEN_CESITLI_ISK.pdf" "SOSYAL_GUVENLIK_KURUMU_ASGARI_ISCILIK.pdf"; Write-Host "  ✓ SOSYAL_GUVENLIK_KURUMU_ASGARI_ISCILIK_TESPIT_KOMISYONUNCA_BELIRLENEN_CESITLI_ISK.pdf → SOSYAL_GUVENLIK_KURUMU_ASGARI_ISCILIK.pdf" } else { Write-Host "  ⚠ Bulunamadı: teblig\SOSYAL_GUVENLIK_KURUMU_ASGARI_ISCILIK_TESPIT_KOMISYONUNCA_BELIRLENEN_CESITLI_ISK.pdf" }
# GELİR/AYLIK ÖDEME VE YOKLAMA İŞLEMLERİ HAKKINDA TE
if (Test-Path "$kök\teblig\GELIR_AYLIK_ODEME_VE_YOKLAMA_ISLEMLERI_HAKKINDA_TEBLIG.pdf") { Rename-Item "$kök\teblig\GELIR_AYLIK_ODEME_VE_YOKLAMA_ISLEMLERI_HAKKINDA_TEBLIG.pdf" "GELIRAYLIK_ODEME_VE_YOKLAMA_ISLEMLERI.pdf"; Write-Host "  ✓ GELIR_AYLIK_ODEME_VE_YOKLAMA_ISLEMLERI_HAKKINDA_TEBLIG.pdf → GELIRAYLIK_ODEME_VE_YOKLAMA_ISLEMLERI.pdf" } else { Write-Host "  ⚠ Bulunamadı: teblig\GELIR_AYLIK_ODEME_VE_YOKLAMA_ISLEMLERI_HAKKINDA_TEBLIG.pdf" }
# Sosyal Güvenlik Kurumu Ön Mali Kontrol İşlemleri Y
if (Test-Path "$kök\yonerge\SOSYAL_GUVENLIK_KURUMU_ON_MALI_KONTROL_ISLEMLERI_YONERGESI.pdf") { Rename-Item "$kök\yonerge\SOSYAL_GUVENLIK_KURUMU_ON_MALI_KONTROL_ISLEMLERI_YONERGESI.pdf" "SOSYAL_GUVENLIK_KURUMU_ON_MALI.pdf"; Write-Host "  ✓ SOSYAL_GUVENLIK_KURUMU_ON_MALI_KONTROL_ISLEMLERI_YONERGESI.pdf → SOSYAL_GUVENLIK_KURUMU_ON_MALI.pdf" } else { Write-Host "  ⚠ Bulunamadı: yonerge\SOSYAL_GUVENLIK_KURUMU_ON_MALI_KONTROL_ISLEMLERI_YONERGESI.pdf" }
# SOSYAL GÜVENLİK KURUMU RESMİ YAZIŞMA KURALLARI YÖN
if (Test-Path "$kök\yonerge\SOSYAL_GUVENLIK_KURUMU_RESMI_YAZISMA_KURALLARI_YONERGESI.pdf") { Rename-Item "$kök\yonerge\SOSYAL_GUVENLIK_KURUMU_RESMI_YAZISMA_KURALLARI_YONERGESI.pdf" "SOSYAL_GUVENLIK_KURUMU_RESMI_YAZISMA.pdf"; Write-Host "  ✓ SOSYAL_GUVENLIK_KURUMU_RESMI_YAZISMA_KURALLARI_YONERGESI.pdf → SOSYAL_GUVENLIK_KURUMU_RESMI_YAZISMA.pdf" } else { Write-Host "  ⚠ Bulunamadı: yonerge\SOSYAL_GUVENLIK_KURUMU_RESMI_YAZISMA_KURALLARI_YONERGESI.pdf" }
# SOSYAL GÜVENLİK KURUMU PERSONEL KİMLİK KARTI YÖNER
if (Test-Path "$kök\yonerge\SOSYAL_GUVENLIK_KURUMU_PERSONEL_KIMLIK_KARTI_YONERGESI.pdf") { Rename-Item "$kök\yonerge\SOSYAL_GUVENLIK_KURUMU_PERSONEL_KIMLIK_KARTI_YONERGESI.pdf" "SOSYAL_GUVENLIK_KURUMU_PERSONEL_KIMLIK.pdf"; Write-Host "  ✓ SOSYAL_GUVENLIK_KURUMU_PERSONEL_KIMLIK_KARTI_YONERGESI.pdf → SOSYAL_GUVENLIK_KURUMU_PERSONEL_KIMLIK.pdf" } else { Write-Host "  ⚠ Bulunamadı: yonerge\SOSYAL_GUVENLIK_KURUMU_PERSONEL_KIMLIK_KARTI_YONERGESI.pdf" }
# SOSYAL GÜVENLİK KURUMU SAĞLIK HİZMETLERİ FİYATLAND
if (Test-Path "$kök\yonerge\SOSYAL_GUVENLIK_KURUMU_SAGLIK_HIZMETLERI_FIYATLANDIRMA_ALT_KOMISYONU_CALISMA_YON.pdf") { Rename-Item "$kök\yonerge\SOSYAL_GUVENLIK_KURUMU_SAGLIK_HIZMETLERI_FIYATLANDIRMA_ALT_KOMISYONU_CALISMA_YON.pdf" "SOSYAL_GUVENLIK_KURUMU_SAGLIK_HIZMETLERI.pdf"; Write-Host "  ✓ SOSYAL_GUVENLIK_KURUMU_SAGLIK_HIZMETLERI_FIYATLANDIRMA_ALT_KOMISYONU_CALISMA_YON.pdf → SOSYAL_GUVENLIK_KURUMU_SAGLIK_HIZMETLERI.pdf" } else { Write-Host "  ⚠ Bulunamadı: yonerge\SOSYAL_GUVENLIK_KURUMU_SAGLIK_HIZMETLERI_FIYATLANDIRMA_ALT_KOMISYONU_CALISMA_YON.pdf" }
# SOSYAL GÜVENLİK KURUMU ÖN ÖDEME USUL VE ESASLARI H
if (Test-Path "$kök\yonerge\SOSYAL_GUVENLIK_KURUMU_ON_ODEME_USUL_VE_ESASLARI_HAKKINDA_YONERGE.pdf") { Rename-Item "$kök\yonerge\SOSYAL_GUVENLIK_KURUMU_ON_ODEME_USUL_VE_ESASLARI_HAKKINDA_YONERGE.pdf" "SOSYAL_GUVENLIK_KURUMU_ON_ODEME.pdf"; Write-Host "  ✓ SOSYAL_GUVENLIK_KURUMU_ON_ODEME_USUL_VE_ESASLARI_HAKKINDA_YONERGE.pdf → SOSYAL_GUVENLIK_KURUMU_ON_ODEME.pdf" } else { Write-Host "  ⚠ Bulunamadı: yonerge\SOSYAL_GUVENLIK_KURUMU_ON_ODEME_USUL_VE_ESASLARI_HAKKINDA_YONERGE.pdf" }
# SOSYAL GÜVENLİK KURUMU HİZMET İÇİ EĞİTİM YÖNETMELİ
if (Test-Path "$kök\yonerge\SOSYAL_GUVENLIK_KURUMU_HIZMET_ICI_EGITIM_YONETMELIGI.pdf") { Rename-Item "$kök\yonerge\SOSYAL_GUVENLIK_KURUMU_HIZMET_ICI_EGITIM_YONETMELIGI.pdf" "SOSYAL_GUVENLIK_KURUMU_HIZMET_ICI.pdf"; Write-Host "  ✓ SOSYAL_GUVENLIK_KURUMU_HIZMET_ICI_EGITIM_YONETMELIGI.pdf → SOSYAL_GUVENLIK_KURUMU_HIZMET_ICI.pdf" } else { Write-Host "  ⚠ Bulunamadı: yonerge\SOSYAL_GUVENLIK_KURUMU_HIZMET_ICI_EGITIM_YONETMELIGI.pdf" }
# GÖREV DAĞILIMI
if (Test-Path "$kök\ic-emir\11.pdf") { Rename-Item "$kök\ic-emir\11.pdf" "2016_2.pdf"; Write-Host "  ✓ 11.pdf → 2016_2.pdf" } else { Write-Host "  ⚠ Bulunamadı: ic-emir\11.pdf" }
# RAPORLARIN DEĞERLENDİRİLMESİ VE YETKİ DEVRİ
if (Test-Path "$kök\ic-emir\1.pdf") { Rename-Item "$kök\ic-emir\1.pdf" "2016_1.pdf"; Write-Host "  ✓ 1.pdf → 2016_1.pdf" } else { Write-Host "  ⚠ Bulunamadı: ic-emir\1.pdf" }
# GÖREV DAĞILIMI
if (Test-Path "$kök\ic-emir\11.pdf") { Rename-Item "$kök\ic-emir\11.pdf" "2015_11.pdf"; Write-Host "  ✓ 11.pdf → 2015_11.pdf" } else { Write-Host "  ⚠ Bulunamadı: ic-emir\11.pdf" }
# YETKİ DEVRİ
if (Test-Path "$kök\ic-emir\9.pdf") { Rename-Item "$kök\ic-emir\9.pdf" "2015_9.pdf"; Write-Host "  ✓ 9.pdf → 2015_9.pdf" } else { Write-Host "  ⚠ Bulunamadı: ic-emir\9.pdf" }
# SOSYAL GÜVENLİK KURUMU EVRAK İŞLEMLERİ HAKKINDA TA
if (Test-Path "$kök\talimat\SOSYAL_GUVENLIK_KURUMU_EVRAK_ISLEMLERI_HAKKINDA_TALIMAT.pdf") { Rename-Item "$kök\talimat\SOSYAL_GUVENLIK_KURUMU_EVRAK_ISLEMLERI_HAKKINDA_TALIMAT.pdf" "SOSYAL_GUVENLIK_KURUMU_EVRAK_ISLEMLERI.pdf"; Write-Host "  ✓ SOSYAL_GUVENLIK_KURUMU_EVRAK_ISLEMLERI_HAKKINDA_TALIMAT.pdf → SOSYAL_GUVENLIK_KURUMU_EVRAK_ISLEMLERI.pdf" } else { Write-Host "  ⚠ Bulunamadı: talimat\SOSYAL_GUVENLIK_KURUMU_EVRAK_ISLEMLERI_HAKKINDA_TALIMAT.pdf" }
# 5510 sayılı Kanunun 21 inci maddesinin beşinci fık
if (Test-Path "$kök\genel-yazi\5510_SAYILI_KANUNUN_21_INCI_MADDESININ_BESINCI_FIKRASI_UYGULAMASI.pdf") { Rename-Item "$kök\genel-yazi\5510_SAYILI_KANUNUN_21_INCI_MADDESININ_BESINCI_FIKRASI_UYGULAMASI.pdf" "5510_SAYILI_KANUNUN_21_INCI.pdf"; Write-Host "  ✓ 5510_SAYILI_KANUNUN_21_INCI_MADDESININ_BESINCI_FIKRASI_UYGULAMASI.pdf → 5510_SAYILI_KANUNUN_21_INCI.pdf" } else { Write-Host "  ⚠ Bulunamadı: genel-yazi\5510_SAYILI_KANUNUN_21_INCI_MADDESININ_BESINCI_FIKRASI_UYGULAMASI.pdf" }
# Geçici İş İlişkisi Mevzuatına Aykırı İşlem Yapılma
if (Test-Path "$kök\genel-yazi\GECICI_IS_ILISKISI_MEVZUATINA_AYKIRI_ISLEM_YAPILMASI.pdf") { Rename-Item "$kök\genel-yazi\GECICI_IS_ILISKISI_MEVZUATINA_AYKIRI_ISLEM_YAPILMASI.pdf" "GECICI_IS_ILISKISI_MEVZUATINA_AYKIRI.pdf"; Write-Host "  ✓ GECICI_IS_ILISKISI_MEVZUATINA_AYKIRI_ISLEM_YAPILMASI.pdf → GECICI_IS_ILISKISI_MEVZUATINA_AYKIRI.pdf" } else { Write-Host "  ⚠ Bulunamadı: genel-yazi\GECICI_IS_ILISKISI_MEVZUATINA_AYKIRI_ISLEM_YAPILMASI.pdf" }
# Ek-9 Kapsamındaki Ev Hizmetleri İşyerlerinin Kolay
if (Test-Path "$kök\genel-yazi\EK_9_KAPSAMINDAKI_EV_HIZMETLERI_ISYERLERININ_KOLAY_ISVERENLIK_KAPSAMINA_ALINMASI.pdf") { Rename-Item "$kök\genel-yazi\EK_9_KAPSAMINDAKI_EV_HIZMETLERI_ISYERLERININ_KOLAY_ISVERENLIK_KAPSAMINA_ALINMASI.pdf" "EK9_KAPSAMINDAKI_EV_HIZMETLERI_ISYERLERININ.pdf"; Write-Host "  ✓ EK_9_KAPSAMINDAKI_EV_HIZMETLERI_ISYERLERININ_KOLAY_ISVERENLIK_KAPSAMINA_ALINMASI.pdf → EK9_KAPSAMINDAKI_EV_HIZMETLERI_ISYERLERININ.pdf" } else { Write-Host "  ⚠ Bulunamadı: genel-yazi\EK_9_KAPSAMINDAKI_EV_HIZMETLERI_ISYERLERININ_KOLAY_ISVERENLIK_KAPSAMINA_ALINMASI.pdf" }
# İŞ KAZASINA UĞRAYAN SİGORTALILAR İÇİN DÜZENLENEN E
if (Test-Path "$kök\genel-yazi\IS_KAZASINA_UGRAYAN_SIGORTALILAR_ICIN_DUZENLENEN_EK_APHB_DE_FAYDALANILAN_ASGARI_.pdf") { Rename-Item "$kök\genel-yazi\IS_KAZASINA_UGRAYAN_SIGORTALILAR_ICIN_DUZENLENEN_EK_APHB_DE_FAYDALANILAN_ASGARI_.pdf" "IS_KAZASINA_UGRAYAN_SIGORTALILAR_ICIN.pdf"; Write-Host "  ✓ IS_KAZASINA_UGRAYAN_SIGORTALILAR_ICIN_DUZENLENEN_EK_APHB_DE_FAYDALANILAN_ASGARI_.pdf → IS_KAZASINA_UGRAYAN_SIGORTALILAR_ICIN.pdf" } else { Write-Host "  ⚠ Bulunamadı: genel-yazi\IS_KAZASINA_UGRAYAN_SIGORTALILAR_ICIN_DUZENLENEN_EK_APHB_DE_FAYDALANILAN_ASGARI_.pdf" }

Write-Host ""
Write-Host "✅ Tamamlandı!"