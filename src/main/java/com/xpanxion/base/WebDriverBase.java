package com.xpanxion.base;


import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import com.xpanxion.core.BrowserTypesNew;
import com.xpanxion.core.Configuration;
public abstract class WebDriverBase {

    private static HashMap<Long, WebDriver> map = new HashMap<Long, WebDriver>();
    private static HashMap<Long, BrowserTypesNew> browserMap = new HashMap<Long, BrowserTypesNew>();
    private static final long waitMillis = 180000;
    protected static final long implicitWaitMillis = 180000;

    public static WebDriver getDriverInstance() {
        WebDriver toReturn = map.get(Thread.currentThread().getId());
        if (toReturn == null) {
            loadWebDriver();
        }
        return map.get(Thread.currentThread().getId());
    }

    public void abort() {
        getDriverInstance().close();
        getDriverInstance().quit();
        map.clear();
        webDriver = null;
    }

    public static BrowserTypesNew getBrowserType() {
        return browserMap.get(Thread.currentThread().getId());

    }

  
    public void waitForPagetoLoad(int timeOut) {
        if (timeOut > 0) {
            getDriverInstance().manage().timeouts()
                    .pageLoadTimeout(timeOut, TimeUnit.SECONDS);
        }
    }

    /**
     * This method builds profiles for the specific browser that is sent in. It builds the correct
     * profile and launches that browser from the WebDriverBase WebDriver.
     *
     * @return The WebDriver with the appropriate browser is returned.
     */
    static WebDriver webDriver = null;

    public static void loadWebDriver() {
        BrowserTypesNew browserType = Configuration.BROWSER;
        webDriver = null;

        try {
            DesiredCapabilities capabilities;
            switch (browserType) {
                case IE:
                    System.setProperty("webdriver.ie.driver",
                            Configuration.IE_WEBDRIVER);
                    capabilities = DesiredCapabilities.internetExplorer();
                    capabilities.setPlatform(Platform.WINDOWS);
                    capabilities
                            .setCapability(
                                    InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
                                    true);
                    capabilities.setCapability(
                            InternetExplorerDriver.INITIAL_BROWSER_URL,
                            "about:blank");
                    capabilities.setCapability(
                            InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
                    capabilities.setCapability(
                            InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
                    capabilities.setCapability(
                            InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING,
                            false);
                    capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS,
                            true);
                    if (Configuration.REMOTE) {
                        webDriver = new RemoteWebDriver(new URL(
                                Configuration.SELENIUM_GRID_URL), capabilities);
                    } else {
                        InternetExplorerDriverService service = InternetExplorerDriverService
                                .createDefaultService();
                        webDriver = new InternetExplorerDriver(service,
                                capabilities);
                    }
                    break;

                case FIREFOX:
                    ProfilesIni profilesIni = new ProfilesIni();
                    FirefoxProfile firefoxProfile = profilesIni
                            .getProfile("default");
                    capabilities = DesiredCapabilities.firefox();
                    capabilities.setBrowserName("firefox");
                    capabilities.setPlatform(Platform.ANY);
                    if (Configuration.REMOTE) {
                        webDriver = new RemoteWebDriver(new URL(
                                Configuration.SELENIUM_GRID_URL), capabilities);
                    } else {
                        webDriver = new FirefoxDriver(firefoxProfile);
                    }
                    break;
                case CHROME:
                    System.getenv();
                    System.setProperty("webdriver.chrome.driver",
                            Configuration.CHROME_WEBDRIVER);
                    capabilities = DesiredCapabilities.chrome();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("test-type", "start-maximized",
                            "no-default-browser-check", "disable-popup-blocking");
                    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                    capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS,
                            true);
                    capabilities.setPlatform(Platform.ANY);
                    if (Configuration.REMOTE) {
                        webDriver = new RemoteWebDriver(new URL(
                                Configuration.SELENIUM_GRID_URL), capabilities);
                    } else {
                        webDriver = new ChromeDriver(capabilities);
                    }
                    break;

                case SAFARI:
                    capabilities = new DesiredCapabilities();
                    capabilities.setPlatform(Platform.MAC);
                    if (Configuration.REMOTE) {
                        webDriver = new RemoteWebDriver(new URL(
                                Configuration.SELENIUM_GRID_URL), capabilities);
                    } else {
                        webDriver = new SafariDriver(capabilities);
                    }
                    break;

                case MOBILE:
                    System.setProperty("webdriver.chrome.driver",
                            Configuration.CHROME_WEBDRIVER);

                    Map<String, String> mobileEmulation = new HashMap<String, String>();
                    mobileEmulation.put("deviceName", Configuration.MOBILE_DEVICE);

                    Map<String, Object> chromeOptions = new HashMap<String, Object>();
                    chromeOptions.put("mobileEmulation", mobileEmulation);

                    capabilities = DesiredCapabilities.chrome();
                    capabilities.setCapability(ChromeOptions.CAPABILITY,
                            chromeOptions);

                    webDriver = new ChromeDriver(capabilities);
                    break;

                default:
                    throw new RuntimeException("Browser type not supported");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts()
                .implicitlyWait(implicitWaitMillis, TimeUnit.MILLISECONDS
                );
        if (browserType.equals(BrowserTypesNew.MOBILE)) {
            webDriver
                    .manage()
                    .window()
                    .setSize(
                            new Dimension(Configuration.MOBILE_WIDTH,
                                    Configuration.MOBILE_HEIGHT));
        }

        webDriver.manage().deleteAllCookies();

        map.put(Thread.currentThread().getId(), webDriver);
        browserMap.put(Thread.currentThread().getId(), browserType);

    }

}
