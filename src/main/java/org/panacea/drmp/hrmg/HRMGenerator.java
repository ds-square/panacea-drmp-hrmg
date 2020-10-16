package org.panacea.drmp.hrmg;

import lombok.extern.slf4j.Slf4j;
import org.panacea.drmp.hrmg.domain.employee.*;
import org.panacea.drmp.hrmg.domain.notifications.DataNotification;
import org.panacea.drmp.hrmg.exception.HRMGException;
import org.panacea.drmp.hrmg.service.HRMGInputRequestService;
import org.panacea.drmp.hrmg.service.HRMGOutputPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

@Slf4j
@Component
public class HRMGenerator {

    @Autowired
    HRMGInputRequestService hrmgInputRequestService;

    @Autowired
    HRMGOutputPostService hrmgOutputPostService;

    private EmployeeInventory employeeInventory;
    private PolicyInventory policyInventory;
    private HashMap<Location, HashSet<String>> locationMap = new HashMap<>();

    public void generateHRM(DataNotification notification) {
        try {
            this.getInput(notification.getSnapshotId());
        } catch (HRMGException e) {
            log.error(e.getMessage());
        }
//        locationMap.put("zona1", new HashSet<String>());
//        log.info(locationMap.toString());
//        locationMap.get("zona1").add("prova");
//        log.info(locationMap.toString());
//        locationMap.get("zona1").add("prova2");
//        log.info(locationMap.toString());
        for (Employee e : employeeInventory.getEmployees()) {
            for (Location l : e.getLocations()) {
                if (locationMap.get(l) == null) {
                    locationMap.put(l, new HashSet<>());
                }
                locationMap.get(l).add(e.getId());
            }
        }
        HashMap<String, SourceEmployee> sourceEmployees = new HashMap<>();
        for (HashSet<String> proximityList : locationMap.values()) {
            for (String e : proximityList) {
                SourceEmployee currentEmployee = new SourceEmployee(e);
                for (String r : proximityList) {
                    if (e.equals(r)) {
                        continue;
                    }
                    ReachedEmployee reachedEmployee = new ReachedEmployee(r, "PROXIMITY");
                    currentEmployee.addReachedEmploye(reachedEmployee);
                }
                sourceEmployees.put(e, currentEmployee);
            }
        }
        for (Policy p : policyInventory.getHumanPolicies()) {
            String sourceId = p.getSourceId();
            String targetId = p.getTargetId();
            String relationType = p.getRelationType();
            //TODO Improve relationship management using relationType
            SourceEmployee source1 = sourceEmployees.get(sourceId);
            if (source1 == null) {
                source1 = new SourceEmployee(sourceId);
                sourceEmployees.put(sourceId, source1);
            }
            ReachedEmployee reached1 = new ReachedEmployee(targetId, "COWORKING");
            source1.addReachedEmploye(reached1);

            SourceEmployee source2 = sourceEmployees.get(targetId);
            if (source2 == null) {
                source2 = new SourceEmployee(targetId);
                sourceEmployees.put(targetId, source2);
            }
            ReachedEmployee reached2 = new ReachedEmployee(sourceId, "COWORKING");
            source2.addReachedEmploye(reached2);
        }

        ReachabilityInventory inventory = new ReachabilityInventory(notification.getEnvironment(), notification.getSnapshotId(), notification.getSnapshotTime(), new ArrayList(sourceEmployees.values()));
        hrmgOutputPostService.postHumanReachabilityInventory(inventory);
    }

    private void getInput(String version) {
        // get input data from REST service
        this.employeeInventory = hrmgInputRequestService.performEmployeeInventoryRequest(version);
        log.info("[HRMG] GET employeeInventory from http://172.16.100.131:8102/human/employeeInventory");
        this.policyInventory = hrmgInputRequestService.performHumanPolicyInventoryRequest(version);
        log.info("[HRMG] GET humanPolicyInventory from http://172.16.100.131:8102/human/humanPolicyInventory");
//
//        log.info(employeeInventory.toString());
//        log.info(policyInventory.toString());
    }
}
