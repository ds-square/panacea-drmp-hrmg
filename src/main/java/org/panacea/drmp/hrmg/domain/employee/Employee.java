
package org.panacea.drmp.hrmg.domain.employee;

import lombok.Data;

import java.util.List;

@Data
@SuppressWarnings("unused")
public class Employee {

    private String employeeName;
    private String id;
    private List<Location> locations;
    private List<Role> roles;

}
