/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xpanxion.interfaces;

import java.io.File;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

/**
 *
 * @author AE08464
 */
public interface WebInterface {
    public WebElement waitForElement(By by);
    public List<WebElement> waitForElementsBy(final By by);
    
    public void moveToElement(By by);
    
    public void selectItemByValue(WebElement element, String itemToSelect);
    public void selectItemByText(WebElement element, String text);
    public void selectItemByIndex(WebElement element, int index);
    
    public void clickButton(WebElement element);
    public boolean hasElement(By by);
    public boolean hasNoElementAsExpected(By by);
    public String getRandomString();
    public String getRandomNumeric();
    public JavascriptExecutor getJavaScriptExecutor();
    public WebElement waitForElementGone(By by);
    public void clickElementWithJavascript(WebElement element);
    public void handledSleep(int sleepInSeconds);
    public void waitTillMultipleTabOpens();
    public boolean verifyElementSelected(WebElement element, boolean selected);
    public void switchToLastTab();
    public void switchToFirstTab();
    public void closeTab();
    public void scrollToElementAndClick(By by);
    public boolean isFileOpened(File file);
    public String getTextFromElement(By by);
    public void selectRadioButtonByValue(By radioGroup, String ValueToSelect);
}
