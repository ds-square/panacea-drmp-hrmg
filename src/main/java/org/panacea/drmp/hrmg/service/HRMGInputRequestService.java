package org.panacea.drmp.hrmg.service;

import org.panacea.drmp.hrmg.domain.employee.EmployeeInventory;
import org.panacea.drmp.hrmg.domain.employee.PolicyInventory;

public interface HRMGInputRequestService {

    EmployeeInventory performEmployeeInventoryRequest(String snapshotId);

    PolicyInventory performHumanPolicyInventoryRequest(String snapshotId);
}
