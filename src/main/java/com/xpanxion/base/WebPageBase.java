package com.xpanxion.base;

import com.google.common.base.Predicate;
import com.xpanxion.interfaces.WebInterface;
import java.io.File;
import org.openqa.selenium.JavascriptExecutor;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.xpanxion.core.Configuration;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author AE08464
 */
public class WebPageBase implements WebInterface {

    protected WebDriver driver;
    private Configuration config;

    public WebPageBase() {
        this.driver = DriverFactory.getDriverInstance();
        this.config = new Configuration();
    }

    @Override
    public WebElement waitForElement(By by) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS)
                .until(new Predicate<WebDriver>() {
                    @Override
                    public boolean apply(WebDriver d) {
                        return ((SearchContext) d).findElements(by).size() > 0;
                    }
                });
        return driver.findElement(by);
    }

    @Override
    public List<WebElement> waitForElementsBy(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.until(new Predicate<WebDriver>() {
            @Override
            public boolean apply(WebDriver t) {
                return t.findElements(by).size() > 0;
            }
        });
        return driver.findElements(by);
    }

    public List<WebElement> waitForElementsBy(WebElement element, By by) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.until((WebDriver t) -> element.findElements(by).size() > 0);
        return driver.findElements(by);
    }

    @Override
    public void moveToElement(By by) {
        Actions actions = new Actions(driver);
        actions.moveToElement(waitForElement(by)).build().perform();
    }

    @Override
    public void selectItemByValue(WebElement element, String itemToSelect) {
        getSelect(element).selectByValue(itemToSelect);
    }

    @Override
    public void selectItemByText(WebElement element, String text) {
        getSelect(element).selectByVisibleText(text);
    }

    @Override
    public void selectItemByIndex(WebElement element, int index) {
        getSelect(element).selectByIndex(index);
    }

    @Override
    public void clickButton(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean hasElement(By by) {
        return countElements(by) != 0;
    }

    @Override
    public boolean hasNoElementAsExpected(By by) {
        WebElement element;
        element = new WebDriverWait(driver, 5).until(ExpectedConditions
                .presenceOfElementLocated(by));
        return element == null || !element.isDisplayed();
    }

    @Override
    public String getRandomString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    @Override
    public String getRandomNumeric() {
        return RandomStringUtils.randomNumeric(3);
    }

    @Override
    public JavascriptExecutor getJavaScriptExecutor() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js;
    }

    @Override
    public WebElement waitForElementGone(By by) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.withTimeout(15, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .until(new Predicate<WebDriver>() {
                    @Override
                    public boolean apply(WebDriver t) {
                        return !hasElement(by);
                    }
                });
        return waitForElement(by);
    }

    @Override
    public void clickElementWithJavascript(WebElement element) {
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click()", element);
        }
    }

    @Override
    public void handledSleep(int sleepInSeconds) {
        Calendar cal = Calendar.getInstance();
        Calendar cal1 = Calendar.getInstance();
        cal1.add(Calendar.SECOND, sleepInSeconds);
        while (cal1.after(cal)) {
            cal = Calendar.getInstance();
        }
    }

    @Override
    public void waitTillMultipleTabOpens() {
        Set<String> allWindows = driver.getWindowHandles();
        while (allWindows.size() == 1) {
            allWindows = driver.getWindowHandles();
        }
    }

    @Override
    public boolean verifyElementSelected(WebElement element, boolean selected) {
        return (new WebDriverWait(driver, 5)).until(ExpectedConditions
                .elementSelectionStateToBe(element, selected));
    }

    @Override
    public void switchToLastTab() {
        List<String> browserTabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(browserTabs.get(browserTabs.size() - 1));
    }

    @Override
    public void switchToFirstTab() {
        Set<String> handles = driver.getWindowHandles();
        for (int i = handles.size(); i > 1; i--) {
            driver.switchTo().window(
                    handles.toArray(new String[handles.size()])[i - 1]);
            driver.close();
        }
        driver.switchTo().window(
                handles.toArray(new String[handles.size()])[0]);
    }

    @Override
    public void closeTab() {
        driver.close();
        List<String> browserTabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(browserTabs.get(browserTabs.size() - 1));
    }

    @Override
    public void scrollToElementAndClick(By by) {
        scrollToElement(by);
        waitForElement(by).click();
    }

    public void scrollToElement(By by) {
        int scrollBy = waitForElement(by).getLocation().y + 25;
        getJavaScriptExecutor().executeScript(
                "window.scrollBy(0," + scrollBy + ");");
    }

    @Override
    public boolean isFileOpened(File file) {
        boolean res = false;
        FileLock lock = null;
        try {
            FileChannel channel = new RandomAccessFile(file, "rw").getChannel();
            // Get an exclusive lock on the whole file
            lock = channel.lock();

            //The file is not already opened
            lock = channel.tryLock();
        } catch (OverlappingFileLockException | IOException e) {
            // File is open by someone else
            res = true;
        } finally {
            try {
                lock.release();
                lock.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return res;
    }

    @Override
    public String getTextFromElement(By by) {
        return waitForElement(by).getText();
    }

    @Override
    public void selectRadioButtonByValue(By radioGroup, String ValueToSelect) {
        // Find the radio group element
        List<WebElement> radioLabels = driver.findElements(
                radioGroup);
        for (int i = 0; i < radioLabels.size(); i++) {
            if (radioLabels.get(i).getText().trim()
                    .equalsIgnoreCase(ValueToSelect.trim())) {
                radioLabels.get(i).click();
                break;
            }
        }
    }

    public Select getSelect(WebElement element) {
        Select select = new Select(element);
        return select;
    }

    public int countElements(By by) {
        int result = 0;
        long currentWaitMillis = config.getElementTimeoutMillis();
        try {
            if (currentWaitMillis > 0) {
                driver.manage().timeouts()
                        .implicitlyWait(0, TimeUnit.MILLISECONDS);
            }
            result = driver.findElements(by).size();
        } finally {
            driver.manage().timeouts()
                    .implicitlyWait(currentWaitMillis, TimeUnit.MILLISECONDS);
        }
        return result;
    }
}
