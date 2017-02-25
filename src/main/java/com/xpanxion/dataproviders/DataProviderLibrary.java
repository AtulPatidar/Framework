package com.xpanxion.dataproviders;

import com.xpanxion.core.BrowserTypes;
import com.xpanxion.core.Configuration;
import com.xpanxion.core.MobileTypes;
import org.testng.annotations.DataProvider;

public class DataProviderLibrary {

    @DataProvider(name = "generic", parallel = true)
    public static Object[][] getBrowsersForWeb() {
        return injectBrowserInstancesToDataProviders(null);
    }

    @DataProvider(name = "generic_mobile", parallel = true)
    public static Object[][] getBrowsers() {
        return injectMobileInstancesToDataProviders(null);
    }

    @DataProvider(name = "sampleData", parallel = true)
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
        String[] browsers = config.getBrowsers().split(",");
        MobileTypes[] browsersToBeSelected = selectMobilesBasedOnConfiguration(browsers);
        if (data == null) {
            Object[][] updatedData = new Object[browsersToBeSelected.length][0];
            int i = 0;
            for (MobileTypes types : browsersToBeSelected) {
                updatedData[i][0] = types;
                i++;
            }
            return updatedData;
        } else {
            Object[][] updatedData = blendMobileTypesWithData(browsersToBeSelected, data);
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

    private static MobileTypes[] selectMobilesBasedOnConfiguration(String[] browsers) {
        MobileTypes[] types = new MobileTypes[browsers.length];
        int i = 0;
        for (String browser : browsers) {
            if (browser.equalsIgnoreCase("ALL")) {
                return MobileTypes.values();
            } else {
                types[i] = MobileTypes.valueOf(browser.toUpperCase());
                i++;
            }
        }
        return types;
    }
}
