package com.testinium.page;

import com.testinium.methods.Methods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class LoginPage {
    Methods methods;
    Logger logger = LogManager.getLogger(LoginPage.class);
    FluentWait<WebDriver> wait;

    public LoginPage(){
        methods = new Methods();
    }


    public void login(){
        methods.click(By.cssSelector(".menu-top-button.login>a"));
        methods.sendKeys(By.id("login-email"),"ecem.turacik@testinium.com");
        methods.sendKeys(By.id("login-password"),"abc12345");
        methods.click(By.cssSelector(".ky-btn.ky-btn-orange.w-100.ky-login-btn"));
        Assert.assertTrue(methods.isElementVisible(By.cssSelector(".section")));
        logger.info("Giriş başarılı gerçekleşti.");
    }
}
