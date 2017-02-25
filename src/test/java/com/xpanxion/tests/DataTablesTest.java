package com.xpanxion.tests;

import com.xpanxion.base.DriverFactory;
import com.xpanxion.core.BrowserTypes;
import com.xpanxion.core.CoreTestCase;
import com.xpanxion.keywords.WebFunctions;
import com.xpanxion.pages.OrthogonalDataPage;
import com.xpanxion.data.Employee;
import com.xpanxion.dataproviders.DataProviderLibrary;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class DataTablesTest extends CoreTestCase {

    @Test(dataProvider = "generic",dataProviderClass = DataProviderLibrary.class)
    public void dataTableTest(BrowserTypes type) {
        log().info("Executing the test");
        DriverFactory.registerInstance(type.getDriverInstance());
        WebDriver driver = DriverFactory.getDriverInstance();
        driver.get("https://www.datatables.net/examples/ajax/orthogonal-data.html");
        OrthogonalDataPage page = new OrthogonalDataPage();
        WebFunctions keywords = new WebFunctions();
        List<Employee> employees = keywords.getEmployeeDetails(page);
        Comparator<Employee> c = (Employee o1, Employee o2) -> Float.compare(o1.getSalary(), o2.getSalary());
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
