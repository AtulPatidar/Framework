package com.xpanxion.core;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.xpanxion.base.DriverFactory;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
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
            DriverFactory.registerInstance(((BrowserTypes) testArgs[0]).getDriverInstance());
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
            if (itr.isSuccess()) {
            } else {
                Screenshot screenshot = new AShot()
                        .takeScreenshot(DriverFactory.getDriverInstance());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                File file = new File(System.getProperty("user.dir") + "");
                File fileScr = new File(file.getAbsolutePath() + "/target/surefire-reports/screenshots/" + itr.getMethod().getMethodName() + ".png");
                ImageIO.write(screenshot.getImage(), "PNG", baos);
                FileUtils.writeByteArrayToFile(fileScr, baos.toByteArray());
            }
            DriverFactory.getDriverInstance().close();
        }
    }

    public static Logger log() {
        return LOG;
    }
}
