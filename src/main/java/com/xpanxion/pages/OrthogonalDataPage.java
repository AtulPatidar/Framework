package com.xpanxion.pages;

import com.xpanxion.base.WebPageBase;
import com.xpanxion.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class OrthogonalDataPage extends WebPageBase {

    public OrthogonalDataPage(WebDriver driver) {
		super(driver);
	
	}

	By table = By.id("example");
    By nextBtn = By.id("example_next");

    public List<WebElement> getRows() {
//        return WaitUtils.waitForElement(driver, table)
//                .findElement(By.tagName("tbody"))
//                .findElements(By.tagName("tr"));
    	return waitForElement(table).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
    }

    public List<WebElement> getColumns(WebElement rowElement) {
    	return rowElement.findElements(By.tagName("td"));
    	
    }

    public OrthogonalDataPage clickNext() {
        WebElement nextBtnElement = WaitUtils.waitForElement(driver, nextBtn);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,600);");
        if (nextBtnElement.isEnabled()) {
            nextBtnElement.click();
        }
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-600);");
        return new OrthogonalDataPage(driver);
    }

    public boolean isNextDisabled() {
        String classAttrib = WaitUtils.waitForElement(driver, nextBtn).getAttribute("class");
        return classAttrib.contains("disabled");
    }
}
