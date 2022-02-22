package com.testinium.page;

import com.testinium.methods.Methods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class ProductPage {
    Methods methods;
    Logger logger = LogManager.getLogger(ProductPage.class);

    public ProductPage() {
        methods = new Methods();
    }

    public void selectProduct() {

        methods.sendKeys(By.id("search-input"), "Oyuncak");
        methods.click(By.cssSelector(".common-sprite.button-search"));
        methods.waitBySeconds(3);
        List<WebElement> products = methods.findElements(By.cssSelector(".product-details"));
        WebElement el = products.get(7);
        methods.scrollWithAction(el);
        methods.addFavorites();
        methods.favoritesCheck();
        methods.click(By.cssSelector(".logo-icon"));

    }

}
