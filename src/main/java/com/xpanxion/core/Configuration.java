package com.xpanxion.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class Configuration {

    private Properties prop;
    private HashMap<String, String> urlMap;

    private Properties getProp() {

        if (prop == null) {
            prop = new Properties();
            InputStream input = null;

            try {
                //System.out.println(new File("bob.txt").getAbsolutePath());
                input = new FileInputStream(new File(Res.getResource("system.properties").toURI()));
                prop.load(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return prop;
    }

    private final boolean remote = false;
    private final String seleniumGridUrl = getProp().getProperty("selenium.gridurl"); 
    private final boolean demo = Boolean.valueOf(getProp().getProperty("project.demo"));
    private final String browsers = getProp().getProperty("selenium.browsers");
    private final String mobiles = getProp().getProperty("selenium.mobiles");

    private final String host = getProp().getProperty("aut.server"); 
    private final boolean useSsl = Boolean.valueOf(getProp().getProperty("aut.useSSL")); 

    private final String mobileDevice = "Galaxy S4"; 
    private final int mobileWidth = 200; 
    private final int mobileHeight = 600; 

    private final String chromeWebdriver = getProp().getProperty("webdriver.chrome.driver"); 
    private final String ieWebdriver = getProp().getProperty("webdriver.ie.driver"); 

    private final String confluenceUser = getProp().getProperty("confluence.user"); 
    private final String confluencePassword = getProp().getProperty("confluence.password"); 

    private final String executionEnvironment = getProp().getProperty("execution.environment"); 
    private final String newLine = System.lineSeparator(); 

    private final int elementTimeoutMillis = Integer.parseInt(getProp().getProperty("selenium.elementtimeout")); 
    private final int pageTimeoutMillis = Integer.parseInt(getProp().getProperty("selenium.pagetimeout")); 
    private final boolean setBrowsermobProxy = true; 

    public HashMap<String, String> getUrlMap() {
        return urlMap;
    }

    public boolean isRemote() {
        return remote;
    }

    public String getSeleniumGridUrl() {
        return seleniumGridUrl;
    }

    public boolean isDemo() {
        return demo;
    }

    public String getHost() {
        return host;
    }

    public boolean isUseSsl() {
        return useSsl;
    }

    public String getMobileDevice() {
        return mobileDevice;
    }

    public int getMobileWidth() {
        return mobileWidth;
    }

    public int getMobileHeight() {
        return mobileHeight;
    }

    public String getChromeWebdriver() {
        return chromeWebdriver;
    }

    public String getIeWebdriver() {
        return ieWebdriver;
    }

    public String getConfluenceUser() {
        return confluenceUser;
    }

    public String getConfluencePassword() {
        return confluencePassword;
    }

    public String getExecutionEnvironment() {
        return executionEnvironment;
    }

    public String getNewLine() {
        return newLine;
    }

    public int getElementTimeoutMillis() {
        return elementTimeoutMillis;
    }

    public int getPageTimeoutMillis() {
        return pageTimeoutMillis;
    }

    public boolean isSetBrowsermobProxy() {
        return setBrowsermobProxy;
    }

    public String getBrowsers() {
        return browsers;
    }

    public String getMobiles() {
        return mobiles;
    }
    
    
}
