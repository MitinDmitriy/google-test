package com.omega_r.google_test;

import static com.omega_r.google_test.Parameters.downloadImagesPath;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.UUID;

public class ImageDownloader {
    public void downloadImage(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        String fileName = url.getFile();
        createFile(downloadImagesPath);
        String destName = downloadImagesPath + UUID.randomUUID().toString() +
            fileName.substring(fileName.lastIndexOf("."));
        System.out.println(destName);

        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(destName);

        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }

        is.close();
        os.close();
    }

    public void createFile(String path) {
        File file = new File(path);
        boolean dirCreated = file.mkdirs();
    }
}