package com.xpanxion.core;

public class ConfigProperties {

    private static final String DEFAULTREMOTE = "false";
    private boolean remote = false;

    private static final String DEFAULTDATALOCATION = "TestData";
    private String dataLocation = null;

    private static final String DEFAULTGLOBALDATALOCATION = "GlobalData";
    private String globalDataLocation = null;

    private static final String DEFAULTQAURL = "";
    private String qaUrl = null;

    private static final String DEFAULTUATURL = "";
    private String uatUrl = null;

    private static final String DEFAULTPRODURL = "";
    private String prodUrl = null;

    private static final String DEFAULTBASEURI = "";
    private String baseUri = null;

    private static final String DEFAULTNODEURL = "";
    private String nodeUrl = null;

    private static final int DEFAULTMAXRETRYCOUNT = 0;
    private String maxRetryCount = null;

    private static final String DEFAULTDEVICE = "Google Nexus 7";
    private String device = null;

    private static final String DEFAULTVIDEO = "false";
    private boolean video = false;

    private static final String DEFAULTENVDATA = null;
    private String envData = null;

    private static final String DEFAULTCOUNTRY = null;
    private String country = null;

    public static final String CLOUDUSERNAME = "";
    private String cloudUserName = "";

    public static final String AUTOMATE_KEY = "";
    private String cloudAutomationKey = "";

    public static final String DEFAULTBROWSER = "";
    private BrowserTypes browser = BrowserTypes.CHROME;

    public static final String DEFAULTBROWSERNAME = "";
    private String browserName = "";

    public ConfigProperties() {
        //Browser is now handled in the xmlWriter
        //browser = BrowserTypes.valueOf(System.getProperty("browser",DEFAULTBROWSER));
        browserName = System.getProperty("browserName", DEFAULTBROWSERNAME);
        remote = System.getProperty("remote", DEFAULTREMOTE).equals("true");
        dataLocation = System.getProperty("dataLocation", DEFAULTDATALOCATION);
        globalDataLocation = System.getProperty("globalDataLocation", DEFAULTGLOBALDATALOCATION);
        qaUrl = System.getProperty("qaUrl", DEFAULTQAURL);
        uatUrl = System.getProperty("autUrl", DEFAULTUATURL);
        prodUrl = System.getProperty("prodUrl", DEFAULTPRODURL);
        baseUri = System.getProperty("baseUri", DEFAULTBASEURI);
        nodeUrl = System.getProperty("nodeUrl", DEFAULTNODEURL);
        envData = System.getProperty("envData", DEFAULTENVDATA);
        maxRetryCount = System.getProperty("retryCount", String.valueOf((DEFAULTMAXRETRYCOUNT)));
        device = System.getProperty("device", DEFAULTDEVICE);
        video = System.getProperty("video", DEFAULTVIDEO).equals("true");
        country = System.getProperty("country", DEFAULTCOUNTRY);
        cloudUserName = System.getProperty("cloudUserName", CLOUDUSERNAME);
        cloudAutomationKey = System.getProperty("key", AUTOMATE_KEY);
    }

    public boolean isRemote() {
        return remote;
    }

    public String getDataLocation() {
        return dataLocation;
    }

    public String getGlobalDataLocation() {
        return globalDataLocation;
    }

    public String getQaUrl() {
        return qaUrl;
    }

    public String getUatUrl() {
        return uatUrl;
    }

    public String getProdUrl() {
        return prodUrl;
    }

    public String getBaseUri() {
        return baseUri;
    }

    public String getNodeUrl() {
        return nodeUrl;
    }

    public int getRetryCount() {
        return Integer.parseInt(maxRetryCount);
    }

    public String getDevice() {
        return device;
    }

    public boolean isVideo() {
        return video;
    }

    public String getEnvData() {
        return envData;
    }

    public String getCountry() {
        return country;
    }

    public String getCloudUserName() {
        return cloudUserName;
    }

    public String getCloudAutomationKey() {
        return cloudAutomationKey;
    }

    public BrowserTypes getBrowser() {
        return browser;
    }

    public String getBrowserName() {
        return browserName;
    }
}
