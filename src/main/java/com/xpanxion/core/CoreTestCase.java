package com.xpanxion.core;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.xpanxion.base.DriverFactory;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;
import org.testng.ITestResult;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class CoreTestCase {
    private final static Logger LOG = Logger.getLogger(CoreTestCase.class);

    @BeforeMethod
    public void setup(Object[] testArgs) {
        Configuration config = new Configuration();
        if (testArgs != null && testArgs.length > 0 && BrowserTypes.class.isAssignableFrom(testArgs[0].getClass())) {
        } else {
            if (config.getTestType().equalsIgnoreCase(TestTypes.WEB.name())) {
                if (!config.getBrowsers().contains(",")) {
                    if (config.getBrowsers().equalsIgnoreCase("ALL")) {
                        DriverFactory.registerInstance(BrowserTypes.values()[0].getDriverInstance());
                    } else {
                        DriverFactory.registerInstance(BrowserTypes.valueOf(config.getBrowsers()).getDriverInstance());
                    }
                } else {
                    DriverFactory.registerInstance(BrowserTypes.valueOf(config.getBrowsers().split(",")[0]).getDriverInstance());
                }
            }
            //String browser = System.getProperty("selenium.browser");
        }
    }

    @AfterMethod
    public void tearDown(ITestResult itr) throws IOException, URISyntaxException {
        if (DriverFactory.getDriverInstance() == null) {
        } else {
            //Screenshot screenshot = new AShot()
              //      .takeScreenshot(DriverFactory.getDriverInstance());
            //ImageIO.write(screenshot.getImage(), "PNG", new File(Res.getResource(itr.getMethod().getMethodName()+".png").toURI()));
            DriverFactory.getDriverInstance().quit();
        }
    }

    public static Logger log() {
        return LOG;
    }
}
