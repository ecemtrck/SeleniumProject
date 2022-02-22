package com.testinium.methods;

import com.testinium.driver.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static java.time.Duration.*;

public class Methods {
    WebDriver driver;
    FluentWait<WebDriver> wait;
    Logger logger = LogManager.getLogger(Methods.class);
    JavascriptExecutor jsdriver;


    public Methods() {
        driver = BaseTest.driver;
        wait = new FluentWait<>(driver);
        wait.withTimeout(ofSeconds(30)).
                pollingEvery(ofMillis(1000)).
                ignoring(NoSuchElementException.class);


        jsdriver = (JavascriptExecutor) driver;

    }

    public WebElement findElement(By by) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public List<WebElement> findElements(By by) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        return driver.findElements(by);
    }

    public void click(By by) {
        findElement(by).click();
    }
    public void click(WebElement el) {
        el.click();
    }

    public void sendKeys(By by, String text) {
        findElement(by).sendKeys(text);
    }

    public boolean isElementVisible(By by) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            logger.info("True");
            return true;
        } catch (Exception e) {

            logger.info("False" + e.getMessage());
            return false;
        }
    }
    public void waitBySeconds(long seconds){
        try {
            Thread.sleep(seconds*1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void scrollWithAction(WebElement el){
        Actions actions = new Actions(driver);
        actions.moveToElement(el).build().perform();
    }
    public void scrollWithAction(By by){
        Actions actions = new Actions(driver);
        actions.moveToElement(findElement(by)).build().perform();
    }


    public void addFavorites() {


        List<WebElement> products = findElements(By.cssSelector(".add-to-favorites"));


        for (int i = 2; i < 6; i++) {


            WebElement el = products.get(i);
            scrollWithAction(el);
            products.get(i).click();
            waitBySeconds(1);
        }
    }

    public void favoritesCheck(){
        click(By.cssSelector(".menu.top.my-list"));
        click(By.linkText("Favorilerim"));
        Assert.assertTrue(isElementVisible(By.cssSelector(".limit")));
        logger.info("Ürün favorilere eklendi");
    }



}
