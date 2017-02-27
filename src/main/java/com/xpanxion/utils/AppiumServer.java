package com.xpanxion.utils;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.xpanxion.core.Configuration;
import com.xpanxion.core.CoreTestCase;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.ServerArgument;

public class AppiumServer {
	private static AppiumDriverLocalService service;
	private static final Configuration CONFIG = Configuration.getInstance();
	private static final Logger LOGGER = Logger.getLogger(CoreTestCase.class);

	public static void startServer(String hubUrl, DesiredCapabilities caps) {
		LOGGER.info("Is Server already running: " + isServerRunning(hubUrl));

		try {
			if (!isServerRunning(hubUrl)) {
				URL url = null;

				url = new URL(hubUrl);

				LOGGER.info("Starting Appium server via Local Service..");
				service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
						
						// APPIUM_HOME
						// .usingDriverExecutable(new File("c:/Program Files
						// (x86)/Appium/node.exe"))
						.usingDriverExecutable(new File(CONFIG.getAppiumNodePath()))
						.withAppiumJS(new File(CONFIG.getAppiumJsFilePath()))
						// new File("C:\\Program Files
						// (x86)\\Appium\\node_modules\\appium\\lib\\server\\main.js"))
						.withLogFile(new File(CONFIG.getAppiumLogFilePath()))
						.withArgument(new ServerLogLevelArgument(), CONFIG.getAppiumLogLevel())
						.withIPAddress(url.getHost()).usingPort(url.getPort())
				// .withCapabilities(caps)
				// .withStartUpTimeOut(20, TimeUnit.SECONDS)
				);
				LOGGER.info("starting server " + Calendar.getInstance().getTimeInMillis());
				// service.addOutPutStream(System.out);
				service.start();
				LOGGER.info("started " + Calendar.getInstance().getTimeInMillis());
			} else {
				LOGGER.info("Server already running on: " + hubUrl);
			}
		} catch (MalformedURLException e) {
			LOGGER.error("Invalid Appium hub URL: " + hubUrl);
		} catch (Throwable e) {

			e.printStackTrace();
		}
	}

	public static void stopServer() {
		if (service != null) {
			service.stop();
			LOGGER.info("Stopped " + Calendar.getInstance().getTimeInMillis());
		}
	}

	public static URL getHubUrl() {
		return service.getUrl();
	}

	private static boolean isServerRunning(String hubUrl) {

		HttpURLConnection openConnection;
		try {
			URL url = new URL(hubUrl + "/status");
			openConnection = (HttpURLConnection) url.openConnection();
			openConnection.connect();
			return openConnection.getResponseMessage().equalsIgnoreCase("ok");
		} catch (java.net.ConnectException ex) {
			return false;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
}

class ServerLogLevelArgument implements ServerArgument {
	@Override
	public String getArgument() {
		return "--log-level ";
	}
}
