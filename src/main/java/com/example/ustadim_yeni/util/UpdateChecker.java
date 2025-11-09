package com.example.ustadim_yeni.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UpdateChecker {

    private static final String GITHUB_RAW_URL = "https://raw.githubusercontent.com/tugrulsimsekk/USTADIM-Data/main/";
    private static final String LOCAL_RESOURCES = "src/main/resources/";

    public static boolean checkAndUpdateFiles() {
        try {
            String[] files = {"cezalar.json", "cezalar6331.json", "asgariucret.json"};
            boolean updated = false;

            for (String filename : files) {
                if (downloadFileIfNewer(filename)) {
                    updated = true;
                    System.out.println("✅ " + filename + " güncellendi!");
                }
            }

            return updated;

        } catch (Exception e) {
            System.out.println("⚠️ Güncelleme kontrolü sırasında hata: " + e.getMessage());
            return false;
        }
    }

    private static boolean downloadFileIfNewer(String filename) throws Exception {
        String githubUrl = GITHUB_RAW_URL + filename;
        String localPath = LOCAL_RESOURCES + filename;

        // GitHub'dan dosyayı indir
        String remoteContent = downloadFile(githubUrl);

        if (remoteContent == null || remoteContent.isEmpty()) {
            return false;
        }

        // Yerel dosya varsa, içeriği karşılaştır
        File localFile = new File(localPath);
        if (localFile.exists()) {
            String localContent = new String(Files.readAllBytes(Paths.get(localPath)));
            if (localContent.equals(remoteContent)) {
                System.out.println("ℹ️ " + filename + " zaten güncel.");
                return false;
            }
        }

        // Yeni dosyayı kaydet
        Files.write(Paths.get(localPath), remoteContent.getBytes());
        return true;
    }

    private static String downloadFile(String urlString) throws Exception {
        URL url = new URL(urlString);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder content = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }

        reader.close();
        return content.toString();
    }
}