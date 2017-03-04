package com.xpanxion.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.xpanxion.exceptions.InvalidPropertyException;

public class Configuration {

    private static Configuration config;

    private Properties prop;
    // private HashMap<String, String> urlMap;
    private Properties systemProps;
    // private Properties defaultProps;

    // TODO: Write a private default constructor
    public static Configuration getInstance() {
        if (config == null) {
            config = new Configuration();
        }
        return config;
    }

    private Properties getProp() {
        if (prop == null) {
            prop = readPropFile(Constants.CONFIG_FILE);
        }
        return prop;
    }

    private Properties readPropFile(String fileName) {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            // System.out.println(new File("bob.txt").getAbsolutePath());
            input = new FileInputStream(new File(Res.getResource(fileName).toURI()));
            prop.load(input);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                input.close();
            } catch (Throwable t) {
                t.printStackTrace(System.out);
            }
        }
        return prop;
    }

    private String get(String propName) {
        // check is system is having property
        if (systemProps == null) {
            systemProps = System.getProperties();
        }
        if (systemProps.get(propName) != null) {
            return systemProps.get(propName).toString();
        }
        String value = getProp().getProperty(propName);
        if (value != null) {
            return value;
        }
        throw new InvalidPropertyException("Property value not found for : " + propName);

    }

    private final boolean remote = Boolean.parseBoolean(get("selenium.remote"));
    private final String browsers = get("selenium.browsers");
    // private final String mobiles = getProp().getProperty("selenium.mobiles");

    private final String platforms = get("mobile.platforms");
    // private final String mobiles = getProp().getProperty("selenium.mobiles");

    // public HashMap<String, String> getUrlMap() {
    // return urlMap;
    // }
    //
    public boolean isRemote() {
        return remote;
    }

    // public String getSeleniumGridUrl() {
    // return seleniumGridUrl;
    // }
    //
    // public boolean isDemo() {
    // return demo;
    // }
    //
    // public String getHost() {
    // return host;
    // }
    //
    // public boolean isUseSsl() {
    // return useSsl;
    // }
    // public String getMobileDevice() {
    // return mobileDevice;
    // }
    //
    // public int getMobileWidth() {
    // return mobileWidth;
    // }
    //
    // public int getMobileHeight() {
    // return mobileHeight;
    // }
    // public String getChromeWebdriver() {
    // return chromeWebdriver;
    // }
    //
    // public String getIeWebdriver() {
    // return ieWebdriver;
    // }
    // public String getConfluenceUser() {
    // return confluenceUser;
    // }
    //
    // public String getConfluencePassword() {
    // return confluencePassword;
    // }
    //
    // public String getExecutionEnvironment() {
    // return executionEnvironment;
    // }
    //
    // public String getNewLine() {
    // return newLine;
    // }
    //
    //
    // public int getPageTimeoutMillis() {
    // return pageTimeoutMillis;
    // }
    //
    // public boolean isSetBrowsermobProxy() {
    // return setBrowsermobProxy;
    // }
    //
    public String getBrowsers() {
        return browsers;
    }

    //
    // public String getMobiles() {
    // return mobiles;
    // }
    public String getDevice() {
        return get(PropKeys.DEVICE_NAME);
    }

    public long getElementTimeoutMillis() {
        return Long.parseLong(get(PropKeys.ELEMENT_TIMEOUT));
    }

    public static interface PropKeys {

        String DEVICE_NAME = "deviceName";
        String HUB_URL = "selenium.hub_url";
        String DEBUG_MODE = "project.debug_mode";
        String AUT_SERVER_URL = "aut.server";
        String AUT_USE_SSL = "aut.useSSL";
        String ELEMENT_TIMEOUT = "selenium.element_timeout";
        String PAGE_TIMEOUT = "selenium.page_timeout";
        String BROWSERS = "selenium.browsers";
        String MOBILES = "selenium.mobiles";
        String CAP_APP_PATH = "capability.app";
        String APPIUM_NODE_PATH = "appium.node.path";
        String APPIUM_JS_PATH = "appium.js.path";
        String APPIUM_LOG_PATH = "appium.log.path";
        String APPIUM_LOG_LEVEL = "appium.log.level";
        String MULTI_BROWSER = "selenium.multibrowser";
        String TEST_TYPE = "xpanxion.testtype";
		String GROWL_ENABLED = "xpanxion.growl.enabled";

    }

    public String getHubUrl() {

        return get(PropKeys.HUB_URL);
    }

    public String getBrowserName() {
        // TODO: remove this methods
        return "";
    }

    public String getAppPath() {
        return get(PropKeys.CAP_APP_PATH);
    }

    //
    // public int getPageTimeoutMillis() {
    // return pageTimeoutMillis;
    // }
    //
    // public boolean isSetBrowsermobProxy() {
    // return setBrowsermobProxy;
    // }
    //
    public String getPlatforms() {
        return platforms;
    }

    public String getAppiumNodePath() {
        return get(PropKeys.APPIUM_NODE_PATH);
    }

    public String getAppiumJsFilePath() {
        return get(PropKeys.APPIUM_JS_PATH);
    }

    public String getAppiumLogFilePath() {
        return get(PropKeys.APPIUM_LOG_PATH);
    }

    public String getAppiumLogLevel() {
        return get(PropKeys.APPIUM_LOG_LEVEL);
    }

    public boolean isMultiBrowser() {
        return Boolean.getBoolean(get(PropKeys.MULTI_BROWSER));
    }

    public DesiredCapabilities getCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        for (String key : prop.stringPropertyNames()) {
            if (key.toLowerCase().startsWith(Constants.CAPABILITY_PROP_PREFIX)) {
                capabilities.setCapability(key.substring(Constants.CAPABILITY_PROP_PREFIX.length() + 1), get(key));
            }
        }
        for (String key : systemProps.stringPropertyNames()) {
            if (key.toLowerCase().startsWith(Constants.CAPABILITY_PROP_PREFIX)) {
                capabilities.setCapability(key.substring(Constants.CAPABILITY_PROP_PREFIX.length() + 1), get(key));
            }
        }
        return capabilities;
    }

    public String getTestType() {
        return get(PropKeys.TEST_TYPE);
    }
    public boolean isMobileNativeApp(){
    	return (!getTestType().equalsIgnoreCase("web")) && !platforms.toLowerCase().contains("web");
    }
    public boolean isGrowlEnabled(){
    	return Boolean.parseBoolean(get(PropKeys.GROWL_ENABLED));
    }
}
