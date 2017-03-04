/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xpanxion.xpert.pages;

import java.util.List;

import org.openqa.selenium.By;

import com.xpanxion.base.WebPageBase;

public class CusineOptionsPopUp extends WebPageBase {

    By listCheckboxCusineOptions = By.cssSelector(".customCheckbox");
    By listCusineOptions = By.cssSelector(".left");

    By buttonSave = By.id("saveBtn");
    By buttonCancel = By.id("cancelBtn");

    public void selectCusine(List<String> options) {
        if (!options.isEmpty()) {
            for (String option : options) {
                for (int i = 0; i < waitForElementsBy(listCusineOptions).size(); i++) {
                    if (waitForElementsBy(listCusineOptions).get(i).getText().trim().equalsIgnoreCase(option)) {
                        clickButton(waitForElementsBy(listCheckboxCusineOptions).get(i));
                    }
                }
            }
        }
    }

    public <T extends WebPageBase> T submitOptions(Class<T> clazz) {
        try {
            clickButton(waitForElement(buttonSave));
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new AssertionError("Unable to create an instance of the class mentioned");
        }
    }

}
