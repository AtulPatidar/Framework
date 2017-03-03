/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xpanxion.dataproviders;

/**
 *
 * @author AE08464
 */
import com.xpanxion.datadriver.Excel;
import com.xpanxion.datadriver.ExcelContext;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DataProviderBase {

    public static String[][] getDataBySheetName(String sheetName) {

        ExcelContext context = new ExcelContext();
        context.setExcelFile("Data_Sheet.xlsx");
        context.setSheetName(sheetName);
        Excel excelObj = new Excel(context);
        String[][] testData = excelObj.getData();
        return testData;

    }

    public static String[][] getDataByFileName(String fileName, String sheetName) {

        ExcelContext context = new ExcelContext();
        context.setExcelFile(fileName);
        context.setSheetName(sheetName);
        Excel excelObj = new Excel(context);
        String[][] testData = excelObj.getData();
        JOptionPane.showInputDialog(testData);
        return testData;

    }

    /**
     * function to read excel sheet data into a list of specified POJO
     *
     *
     * @param fileName
     * @param sheetName
     * @param classType
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     *
     * Note: column names in Excel sheet needs to be in Pascal case
     */
    public static <T> List<T> readexcelAsPojoList(String fileName, String sheetName, Class<T> classType)
            throws InstantiationException, IllegalAccessException {

        ExcelContext context = new ExcelContext();
        context.setExcelFile(fileName);
        context.setSheetName(sheetName);
        Excel excelObj = new Excel(context);

        // Field[] fields = classType.getDeclaredFields();
        Method[] methods = classType.getDeclaredMethods();

        List<T> objList = new ArrayList<T>();

        for (int i = 0; i < excelObj.getData().length; ++i) {

            T clsInstance = (T) classType.newInstance();

            for (Method method : methods) {

                if (method.getName().contains("set")) {

                    try {

                        if (method.getParameterTypes()[0].equals(Integer.TYPE)) {
                            Double dblVal = Double
                                    .parseDouble(excelObj.getColumnDataByRowIndex(i, method.getName().substring(3)));
                            method.invoke(clsInstance, dblVal.intValue());
                        } else if (method.getParameterTypes()[0].equals(Boolean.TYPE)) {

                            method.invoke(clsInstance, Boolean
                                    .parseBoolean(excelObj.getColumnDataByRowIndex(i, method.getName().substring(3))));
                        } else if (method.getParameterTypes()[0].equals(Double.TYPE)) {

                            Double dblVal = Double
                                    .parseDouble(excelObj.getColumnDataByRowIndex(i, method.getName().substring(3)));
                            method.invoke(clsInstance, dblVal);
                        } else if (method.getParameterTypes()[0].equals(Float.TYPE)) {

                            Double dblVal = Double
                                    .parseDouble(excelObj.getColumnDataByRowIndex(i, method.getName().substring(3)));
                            method.invoke(clsInstance, dblVal.floatValue());
                        } else {
                            method.invoke(clsInstance, excelObj.getColumnDataByRowIndex(i, method.getName().substring(3)));
                        }

                        /*
                         * method.invoke(clsInstance,excelObj.getColumnDataByRowIndex(i,method.getName().substring(3)));
                         */
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }

            }
            objList.add(clsInstance);
        }

        return objList;
    }

    public static <T> Object[][] readSheet(String workbook, String sheetName, Class<T> classType ){
        List<T> objLst = new ArrayList<>();
        try {
            objLst = readexcelAsPojoList(
                    workbook,
                    sheetName,
                    classType);

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        Object[][] data = new Object[objLst.size()][1];
        int i = 0;
        for (T t : objLst) {
            data[i][0] = t;
            i++;
        }
        return data;
    }

    public static <T> Object[][] readSheetIntoOneRow(String workbook, String sheetName, Class<T> classType ){
        List<T> objLst = new ArrayList<>();
        try {
            objLst = readexcelAsPojoList(
                    workbook,
                    sheetName,
                    classType);

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        //put all the rows in one row
        Object[][] data = new Object[1][1];
        data[0][0] = objLst;
        return data;
    }

    /*public static void main(String[] args) {
     try {
     List<CommonRequestJavaApi> objList = readexcelAsPojoList("data_java_api_validation.xlsx", "javaAPI",
     CommonRequestJavaApi.class);
     System.out.println();
     } catch (InstantiationException | IllegalAccessException e) {
     e.printStackTrace();
     }
     }*/
}
