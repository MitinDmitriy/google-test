package com.omega_r.google_test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.omega_r.google_test.Parameters.googleUrl;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class GooglePage {

    public SelenideElement inputSearch = $(By.name("q"));

    public SelenideElement buttonImages = $(By.xpath
        ("//*[@id = \"top_nav\"]//*[contains(text(),\"Картинки\")]"));

    public ElementsCollection searchedImages = $$(By.xpath("//*[@id = \"islrg\"]//img"));

    public SelenideElement spanImageResolution = $(By.xpath
        ("//*[@class=\"tvh9oe BIB1wf\"]//*[@class = \"VSIspc\"]"));

    public SelenideElement buttonNextImage = $(By.xpath
        ("//*[@class=\"tvh9oe BIB1wf\"]//*[@jsaction= \"click:trigger.DHJvyd\"]"));

    public SelenideElement currentImage = $(By.xpath
        ("//*[@class=\"tvh9oe BIB1wf\"]//*[@class=\"n3VNCb\"]"));

    public SelenideElement loadStatus = $(By.xpath("//*[@class=\"tvh9oe BIB1wf\"]//*[@class=\"k7O2sd\"]"));

    public void openGooglePage() {
        Selenide.open(googleUrl);
    }

    public void waitUntilElementPresented(SelenideElement element) {
        element.shouldBe(Condition.exist);
    }

    public void enterQuery(String query) {
        inputSearch.setValue(query).pressEnter();
    }

    public void openImagesSearch() {
        buttonImages.click();
    }

    public void openFirstImage() {
        searchedImages.get(0).click();
    }

    public String getCurrentImageResolution() {
        Selenide.actions().moveToElement(currentImage).perform();
        waitUntilElementPresented(currentImage);
        return spanImageResolution.getText();
    }

    public String getCurrentImageSrc() {
        waitUntilElementPresented(currentImage);
        loadStatus.waitUntil(Condition.hidden, 5000);
        return currentImage.getAttribute("src");
    }

    public void getNextImage() {
        buttonNextImage.click();
    }
}
