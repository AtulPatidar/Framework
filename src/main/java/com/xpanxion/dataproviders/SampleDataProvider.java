/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xpanxion.dataproviders;

import org.testng.annotations.DataProvider;

/**
 *
 * @author AE08464
 */
public class SampleDataProvider {
    public static final String VERIFY_TABLE_DATA = "VERIFY_TABLE_DATA";
    public static final String SAMPLE_DATA_FILE = "SampleDataFile.xlsx";
    
    @DataProvider(name = VERIFY_TABLE_DATA)
	public static String[][] verifyTable() {
		return DataProviderBase.getDataByFileName(SAMPLE_DATA_FILE, 
				VERIFY_TABLE_DATA);
	}
    
}
