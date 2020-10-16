
package org.panacea.drmp.hrmg.domain.employee;

import lombok.Data;

import java.util.List;

@Data
@SuppressWarnings("unused")
public class ReachabilityInventory {

    private String environment;
    private String fileType;
    private String snapshotId;
    private String snapshotTime;
    private List<SourceEmployee> sourceEmployees;

    public ReachabilityInventory(String environment, String snapshotId, String snapshotTime, List<SourceEmployee> sourceEmployees) {
        this.environment = environment;
        this.fileType = "HumanReachabilityInventory";
        this.snapshotId = snapshotId;
        this.snapshotTime = snapshotTime;
        this.sourceEmployees = sourceEmployees;
    }
}
