
package org.panacea.drmp.hrmg.domain.employee;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@SuppressWarnings("unused")
public class SourceEmployee {

    private String id;
    private Set<ReachedEmployee> reachedEmployee;

    public SourceEmployee(String id) {
        this.id = id;
        this.reachedEmployee = new HashSet<>();
    }


    public void addReachedEmploye(ReachedEmployee re) {
        this.reachedEmployee.add(re);
    }

}
