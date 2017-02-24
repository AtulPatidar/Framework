package com.xpanxion.keywords;

import com.xpanxion.data.Employee;
import com.xpanxion.pages.OrthogonalDataPage;
import java.util.List;

public interface Keywords {
    public List<Employee> getEmployeeDetails(OrthogonalDataPage page);
    
}
