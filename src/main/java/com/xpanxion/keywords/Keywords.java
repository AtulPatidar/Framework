package com.xpanxion.keywords;

import com.xpanxion.data.Employee;
import com.xpanxion.pages.OrthogonalDataPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Keywords {

    public List<Employee> getEmployeeDetails(WebDriver driver, OrthogonalDataPage page) {
        List<Employee> employees = new ArrayList<>();
        while (!page.isNextDisabled(driver)) {
            List<WebElement> rowElements = page.getRows(driver);
            for (WebElement element : rowElements) {
                List<WebElement> columnElement = page.getColumns(element);
                Employee employee = getEmployeeRecord(columnElement);
                employees.add(employee);
            }
            page = page.clickNext(driver);
        }
        List<WebElement> rowElements = page.getRows(driver);
        for (WebElement element : rowElements) {
            List<WebElement> columnElement = page.getColumns(element);
            Employee employee = getEmployeeRecord(columnElement);
            employees.add(employee);
        }
        return employees;
    }

    private Employee getEmployeeRecord(List<WebElement> columnElement) {
        Employee employee = new Employee();
        employee.setName(columnElement.get(0).getText());
        employee.setPosition(columnElement.get(1).getText());
        employee.setOffice(columnElement.get(2).getText());
        employee.setExtension(columnElement.get(3).getText());
        employee.setStartDate(columnElement.get(4).getText());
        float salary = Float.parseFloat(columnElement.get(5).getText()
                .replace("$", "")
                .replace(",", ""));
        employee.setSalary(salary);
        return employee;
    }
}
