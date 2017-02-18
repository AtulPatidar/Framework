package com.xpanxion.pages;

import com.xpanxion.utils.WaitUtils;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrthogonalDataPage {

    By table = By.id("example");
    By nextBtn = By.id("example_next");

    public List<WebElement> getRows(WebDriver driver) {
        return WaitUtils.waitForElement(driver, table)
                .findElement(By.tagName("tbody"))
                .findElements(By.tagName("tr"));
    }

    public List<WebElement> getColumns(WebElement rowElement) {
        return rowElement.findElements(By.tagName("td"));
    }

    public OrthogonalDataPage clickNext(WebDriver driver) {
        WebElement nextBtnElement = WaitUtils.waitForElement(driver, nextBtn);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,600);");
        if (nextBtnElement.isEnabled()) {
            nextBtnElement.click();
        }
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-600);");
        return new OrthogonalDataPage();
    }

    public boolean isNextDisabled(WebDriver driver) {
        String classAttrib = WaitUtils.waitForElement(driver, nextBtn).getAttribute("class");
        return classAttrib.contains("disabled");
    }
}
