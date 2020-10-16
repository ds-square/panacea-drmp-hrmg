
package org.panacea.drmp.hrmg.domain.employee;

import lombok.Data;

import java.util.List;

@Data
@SuppressWarnings("unused")
public class PolicyInventory {

    private String environment;
    private String fileType;
    private List<Policy> humanPolicies;
    private String snapshotId;
    private String snapshotTime;

}
