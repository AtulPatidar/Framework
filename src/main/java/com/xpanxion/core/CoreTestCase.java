package com.xpanxion.core;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Calendar;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.xpanxion.base.DriverFactory;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

public class CoreTestCase {

	private final static Logger LOG = Logger.getLogger(CoreTestCase.class);

	@BeforeMethod
	public void setup(Object[] testArgs) {
		Configuration config = new Configuration();
		if (testArgs != null && testArgs.length > 0 && BrowserTypes.class.isAssignableFrom(testArgs[0].getClass())) {
			log().debug("Starting Browser Instance - "+(BrowserTypes) testArgs[0]);
			DriverFactory.registerInstance(((BrowserTypes) testArgs[0]).getDriverInstance());
		} else {
			if (config.getTestType().equalsIgnoreCase(TestTypes.WEB.name())) {
				if (!config.getBrowsers().contains(",")) {
					if (config.getBrowsers().equalsIgnoreCase("ALL")) {
						log().debug("Starting Browser Instance - "+BrowserTypes.values()[0]);
						DriverFactory.registerInstance(BrowserTypes.values()[0].getDriverInstance());
					} else {
						log().debug("Starting Browser Instance - "+config.getBrowsers());
						DriverFactory.registerInstance(BrowserTypes.valueOf(config.getBrowsers()).getDriverInstance());
					}
				} else {
					log().debug("Starting Browser Instance - "+ config.getBrowsers().split(",")[0]);
					DriverFactory.registerInstance(
							BrowserTypes.valueOf(config.getBrowsers().split(",")[0]).getDriverInstance());
				}
			}
		}
	}

	@AfterMethod
	public void tearDown(ITestResult itr) throws IOException, URISyntaxException {
		try {
			if (DriverFactory.getDriverInstance() == null) {
				
			} else {
				if (itr.isSuccess()) {
					log().info("Test Case: "+itr.getMethod().getMethodName()+" - PASSED");
				} else {
					log().info("Test Case: "+itr.getMethod().getMethodName()+" - FAILED");
					Configuration config = new Configuration();
					if (!config.isMobileNativeApp() && config.isGrowlEnabled()) {
						growlNotify(DriverFactory.getDriverInstance(), (Throwable) itr.getThrowable(),
								itr.getMethod().getMethodName());
					}
					log().debug("Capturing Screenshot...");
					Screenshot screenshot = new AShot().takeScreenshot(DriverFactory.getDriverInstance());
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					File file = new File(System.getProperty("user.dir") + "");
					File fileScr = new File(file.getAbsolutePath() + "/target/surefire-reports/screenshots/"
							+ itr.getMethod().getMethodName() + "_" + itr.getStartMillis() + ".png");
					ImageIO.write(screenshot.getImage(), "PNG", baos);
					FileUtils.writeByteArrayToFile(fileScr, baos.toByteArray());
					log().debug("Screenshot saved at: "+fileScr.getAbsolutePath());
				}
				log().debug("Closing browser / session... ");
				DriverFactory.getDriverInstance().quit();
			}
		} catch (Throwable e) {
			log().error("Error in @AfterMethod, Error message: "+e.getLocalizedMessage());
			e.printStackTrace(System.err);
		}
	}

	public static Logger log() {
		return LOG;
	}

	public void growlNotify(WebDriver driver, Throwable error, String testCaseName) {

		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			// Check for jQuery on the page, add it if need be
			js.executeScript("if (!window.jQuery) { " + "var jquery = document.createElement('script');"
					+ "jquery.type = 'text/javascript';jquery.src = 'https://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js';"
					+ "document.getElementsByTagName('head')[0].appendChild(jquery); }");
			handledSleep(2);
			// Use jQuery to add jquery-growl to the page
			js.executeScript("$.getScript('https://cdnjs.cloudflare.com/ajax/libs/notify/0.4.2/notify.js')");
			handledSleep(2);
			// Add the style
			String styleScript = FileUtils
					.readFileToString(new File(Res.getResource("scripts/style_script.txt").toURI()), "UTF-8");
			js.executeScript(styleScript);
			handledSleep(2);
			String errorMessage = "$.notify({ title: 'Automation Alert !', errorMessage: \""
					+ StringEscapeUtils.escapeJava(error.getLocalizedMessage().trim())
					+ "\" } , { style: 'bootstrap', className: 'error' });";
			js.executeScript(errorMessage);
		} catch (IOException | URISyntaxException e) {
			System.out.println(e);
			log().error(testCaseName, e);
		}
	}

	private void handledSleep(int sleepInSeconds) {
		log().debug("Waiting for: "+sleepInSeconds+" seconds");
		Calendar cal = Calendar.getInstance();
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.SECOND, sleepInSeconds);
		while (cal1.after(cal)) {
			cal = Calendar.getInstance();
		}
	}
}
