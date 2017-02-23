package com.xpanxion.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
public class Configuration {
    
    private static Properties prop;
    private static HashMap<String, String> urlMap;

    private static Properties getProp() {

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
    
    public static final Boolean REMOTE = new Boolean(getProp().getProperty("selenium.remote"));
    public static final BrowserTypesNew BROWSER = BrowserTypesNew.valueOf(getProp().getProperty("selenium.browser"));
    public static final String SELENIUM_GRID_URL = getProp().getProperty("selenium.gridurl");
    public static final Boolean DEMO = new Boolean(getProp().getProperty("project.demo"));

    public static final String HOST = getProp().getProperty("aut.server");
    public static final Boolean USE_SSL = new Boolean(getProp().getProperty("aut.useSSL"));

    public static final String MOBILE_DEVICE = "Galaxy S4";
    public static final int MOBILE_WIDTH = 200;
    public static final int MOBILE_HEIGHT = 600;


    public static final String CHROME_WEBDRIVER = getProp().getProperty("webdriver.chrome.driver");
    public static final String IE_WEBDRIVER = getProp().getProperty("webdriver.ie.driver");

    public static final String CONFLUENCE_USER = getProp().getProperty("confluence.user");
    public static final String CONFLUENCE_PASSWORD = getProp().getProperty("confluence.password");

    public static final String EXECUTION_ENVIRONMENT = getProp().getProperty("execution.environment");
    public static final String NEW_LINE = System.lineSeparator();

    public static final int ELEMENT_TIMEOUT_MILLIS = Integer.parseInt(getProp().getProperty("selenium.elementtimeout"));
    public static final int PAGE_TIMEOUT_MILLIS = Integer.parseInt(getProp().getProperty("selenium.pagetimeout"));
    public static final boolean SET_BROWSERMOB_PROXY = true;
    
}
