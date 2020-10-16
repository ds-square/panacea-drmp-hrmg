package org.panacea.drmp.hrmg.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.panacea.drmp.hrmg.domain.employee.EmployeeInventory;
import org.panacea.drmp.hrmg.domain.employee.PolicyInventory;
import org.panacea.drmp.hrmg.service.HRMGInputRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class HRMGInputRequestServiceImpl implements HRMGInputRequestService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${employeeInventory.endpoint}")
    private String employeeInventoryURL;

    @Value("${employeeInventory.fn}")
    private String employeeInventoryFn;

    @Value("${policyInventory.endpoint}")
    private String policyInventoryURL;

    @Value("${policyInventory.fn}")
    private String policyInventoryFn;


    @Override
    public EmployeeInventory performEmployeeInventoryRequest(String snapshotId) {

        ResponseEntity<EmployeeInventory> responseEntity = restTemplate.exchange(
                employeeInventoryURL + '/' + snapshotId, // + '/' + employeeInventoryFn,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<EmployeeInventory>() {
                });
        EmployeeInventory employeeInventory = responseEntity.getBody();

        return employeeInventory;
    }

    @Override
    public PolicyInventory performHumanPolicyInventoryRequest(String snapshotId) {

        ResponseEntity<PolicyInventory> responseEntity = restTemplate.exchange(
                policyInventoryURL + '/' + snapshotId, // + '/' + policyInventoryFn,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PolicyInventory>() {
                });
        PolicyInventory policyInventory = responseEntity.getBody();

        return policyInventory;
    }
}
