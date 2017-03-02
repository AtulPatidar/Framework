package com.xpanxion.dataproviders;

import com.xpanxion.core.BrowserTypes;
import com.xpanxion.core.Configuration;
import com.xpanxion.core.MobileTypes;
import org.testng.annotations.DataProvider;

public class DataProviderLibrary {

    public static final String DP_GENERIC = "DP_GENERIC";
    public static final String DP_GENERIC_MOBILE_BROWSERS = "GENERIC_MOBILE";
    public static final String DP_SAMPLE_DATA = "SAMPLEDATA";
    public static final String DP_GENERIC_MOBILE_NATIVE = "MOBILE_NATIVE";
    public static final String VERIFY_TABLE_DATA = "VERIFY_TABLE_DATA";
    public static final String SAMPLE_DATA_FILE = "SampleDataFile.xlsx";

    @DataProvider(name = VERIFY_TABLE_DATA)
    public static Object[][] verifyTable() {
        return injectBrowserInstancesToDataProviders(DataProviderBase.getDataByFileName(SAMPLE_DATA_FILE,
                VERIFY_TABLE_DATA));
    }

    @DataProvider(name = DP_GENERIC, parallel = true)
    public static Object[][] getBrowsersForWeb() {
        return injectBrowserInstancesToDataProviders(null);
    }

    @DataProvider(name = DP_GENERIC_MOBILE_BROWSERS, parallel = false)
    public static Object[][] getBrowsersForMobile() {
        
    	return injectMobileInstancesToDataProviders(null);
    }

    @DataProvider(name = DP_GENERIC_MOBILE_NATIVE, parallel = false)
    public static Object[][] getNativePlatform() {
        return injectMobileInstancesToDataProviders(null);
    }

    @DataProvider(name = DP_SAMPLE_DATA, parallel = true)
    public static Object[][] getSampleData() {
        String[][] data = new String[][]{{"Rajesh", "Xpanxion"}, {"Vikas", "Xpanxion"}, {"CD", "Xpanxion"}, {"Devraj", "Xpanxion"}};
        Object[][] updatedData = injectBrowserInstancesToDataProviders(data);
        return updatedData;
    }

    private static Object[][] injectBrowserInstancesToDataProviders(String[][] data) {
        Configuration config = new Configuration();
        String[] browsers = config.getBrowsers().split(",");
        BrowserTypes[] browsersToBeSelected = selectBrowsersBasedOnConfiguration(browsers);
        if (data == null) {
            Object[][] updatedData = new Object[browsersToBeSelected.length][0];
            int i = 0;
            for (BrowserTypes types : browsersToBeSelected) {
                updatedData[i] = new Object[]{types};
                i++;
            }
            return updatedData;
        } else {
            Object[][] updatedData = blendBrowserTypesWithData(browsersToBeSelected, data);
            return updatedData;
        }
    }

    private static Object[][] injectMobileInstancesToDataProviders(String[][] data) {
        Configuration config = new Configuration();
        String[] platforms = config.getPlatforms().split(",");
        MobileTypes[] platformsToBeSelected = selectMobilesBasedOnConfiguration(platforms);
        if (data == null) {
            Object[][] updatedData = new Object[platformsToBeSelected.length][1];
            int i = 0;
            for (MobileTypes types : platformsToBeSelected) {
                updatedData[i][0] = types;
                i++;
            }
            return updatedData;
        } else {
            Object[][] updatedData = blendMobileTypesWithData(platformsToBeSelected, data);
            return updatedData;
        }
    }

    private static BrowserTypes[] selectBrowsersBasedOnConfiguration(String[] browsers) {
        BrowserTypes[] types = new BrowserTypes[browsers.length];
        int i = 0;
        for (String browser : browsers) {
            if (browser.equalsIgnoreCase("ALL")) {
                return BrowserTypes.values();
            } else {
                types[i] = BrowserTypes.valueOf(browser.toUpperCase());
                i++;
            }
        }
        return types;
    }

    private static Object[][] blendBrowserTypesWithData(BrowserTypes[] browsersToBeSelected, String[][] data) {
        int maxSize = browsersToBeSelected.length * data.length;
        Object[][] updatedData = new Object[maxSize][];
        int i = 0;
        for (String[] rows : data) {
            for (BrowserTypes browsers : browsersToBeSelected) {
                Object[] rowObj = new Object[rows.length + 1];
                rowObj[0] = browsers;
                int j = 1;
                for (String column : rows) {
                    rowObj[j] = column;
                    j++;
                }
                updatedData[i] = rowObj;
                i++;
            }
        }
        return updatedData;
    }

    private static Object[][] blendMobileTypesWithData(MobileTypes[] browsersToBeSelected, String[][] data) {
        int maxSize = browsersToBeSelected.length * data.length;
        Object[][] updatedData = new Object[maxSize][];
        int i = 0;
        for (String[] rows : data) {
            for (MobileTypes browsers : browsersToBeSelected) {
                Object[] rowObj = new Object[rows.length + 1];
                rowObj[0] = browsers;
                int j = 1;
                for (String column : rows) {
                    rowObj[j] = column;
                    j++;
                }
                updatedData[i] = rowObj;
                i++;
            }
        }
        return updatedData;
    }

    private static MobileTypes[] selectMobilesBasedOnConfiguration(String[] platforms) {
        MobileTypes[] types = new MobileTypes[platforms.length];
        int i = 0;
        for (String platform : platforms) {
            if (platform.equalsIgnoreCase("ALL")) {
                return MobileTypes.values();
            } else {
                types[i] = MobileTypes.valueOf(platform.toUpperCase());
                i++;
            }
        }
        return types;
    }
}
