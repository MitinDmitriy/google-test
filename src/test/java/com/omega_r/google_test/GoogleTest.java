package com.omega_r.google_test;

import static com.omega_r.google_test.Parameters.downloadImagesCount;
import static com.omega_r.google_test.Parameters.minimalWidth;
import static com.omega_r.google_test.Parameters.searchQuery;

import java.io.IOException;
import org.junit.Test;

public class GoogleTest {

    @Test
    public void downloadGoogleImages() throws IOException {
        GooglePage googlePage = new GooglePage();
        ImageDownloader imageDownloader = new ImageDownloader();

        googlePage.openGooglePage();
        googlePage.enterQuery(searchQuery);
        googlePage.openImagesSearch();
        googlePage.openFirstImage();

        int imagesDownload = 0;
        while (imagesDownload < downloadImagesCount) {
            String imageResolution = googlePage.getCurrentImageResolution();
            int imageWidth = Integer.parseInt(imageResolution.split("\\s+")[0]);

            if (imageWidth >= minimalWidth) {
                String url = googlePage.getCurrentImageSrc();
                imageDownloader.downloadImage(url);
                imagesDownload++;
            }

            googlePage.getNextImage();
        }
    }
}
