package com.xpanxion.tests;

import com.xpanxion.core.BrowserTypes;
import com.xpanxion.core.CoreTestCase;
import com.xpanxion.keywords.Keywords;
import com.xpanxion.pages.OrthogonalDataPage;
import com.xpanxion.data.Employee;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class DataTablesTest extends CoreTestCase {

    @Test(dataProvider = "generic")
    public void dataTableTest(BrowserTypes type) {
        log().info("Executing the test");
        WebDriver driver = type.getDriverInstance();
        driver.get("https://www.datatables.net/examples/ajax/orthogonal-data.html");
        OrthogonalDataPage page = new OrthogonalDataPage();
        Keywords keywords = new Keywords();
        List<Employee> employees = keywords.getEmployeeDetails(driver, page);
        Comparator<Employee> c = new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return Float.compare(o1.getSalary(), o2.getSalary());
            }
        };
        Collections.sort(employees, c);
        Employee highestSalariedPerson = employees.get(employees.size() - 1);
        Employee lowestSalariedPerson = employees.get(0);
        log().info("\nThe highest salaried person is: \nName: "
                + highestSalariedPerson.getName() + "\nPosition: "
                + highestSalariedPerson.getPosition() + "\nOffice: "
                + highestSalariedPerson.getOffice() + "\nExtension: "
                + highestSalariedPerson.getExtension()+ "\nStart Date: "
                + highestSalariedPerson.getStartDate() + "\nSalary: "
                + highestSalariedPerson.getSalary()+"\n======================");
        log().info("\nThe lowest salaried person is: \nName: "
                + lowestSalariedPerson.getName() + "\nPosition: "
                + lowestSalariedPerson.getPosition() + "\nOffice: "
                + lowestSalariedPerson.getOffice() + "\nExtension: "
                + lowestSalariedPerson.getExtension()+ "\nStart Date: "
                + lowestSalariedPerson.getStartDate() + "\nSalary: "
                + lowestSalariedPerson.getSalary());
    }
    
    
}
