package org.panacea.drmp.hrmg.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.panacea.drmp.hrmg.domain.employee.ReachabilityInventory;
import org.panacea.drmp.hrmg.service.HRMGOutputPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;

@Slf4j
@Service
public class HRMGOutputPostServiceImpl implements HRMGOutputPostService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${reachabilityInventory.endpoint}")
    private String reachabilityInventoryURL;

    @Value("${reachabilityInventory.fn}")
    private String reachabilityInventoryFn;


    @Override
    public void postHumanReachabilityInventory(ReachabilityInventory inventory) {

//        // convert repr to file
//        String tempFilePath = "/tmp/" + reachabilityInventoryFn;
//        File file = new File(tempFilePath);
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, inventory);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        MultiValueMap<String, Object> body
//                = new LinkedMultiValueMap<>();
//        body.add("file", new FileSystemResource(tempFilePath));
//
        HttpEntity<ReachabilityInventory> requestEntity
                = new HttpEntity<>(inventory);
//
        String endPointUrl = reachabilityInventoryURL; // + '/' + inventory.getSnapshotId() + '/';
//
//        log.info("[HRMG] POST ReachabilityInventory to " + endPointUrl);
        log.info("[HRMG] POST ReachabilityInventory to http://172.16.100.131:8102/human/humanReachabilityInventory");
        ResponseEntity<String> response = null;
        RestTemplate restTemplate = new RestTemplate();
        try {
            response = restTemplate
                    .postForEntity(endPointUrl, requestEntity, String.class);
        } catch (HttpClientErrorException e) {
            System.out.println("Response from storage service: " + response);
            byte[] bytes = e.getResponseBodyAsByteArray();

            //Convert byte[] to String
            String s = new String(bytes);

            log.error(s);
            e.printStackTrace();

        }


    }

//    @Override
//    public void postHumanReachabilityInventory(ReachabilityInventory inventory){
//        final var restTemplate = new RestTemplate();
//        final var username = "admin";
//        final var password = "pass";
//        final var url = "https://someapi.net/rest/api/2/issue/";
//        var headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBasicAuth(username, password);
//        //header will look like 'Authorization: Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==' ('username:password' with Base64)
//        var fieldsFromInputObject = new FieldsFromInputObject();
//        fieldsFromInputObject.setSummary("REST ye merry gentlemen.");
//        //here set another fields
//        var inputObject = new InputObject();
//        inputObject.setFields(fields);
//        var request = new HttpEntity<>(inputObject, headers);
//        var response = restTemplate.postForObject(url, request, String.class);
//    }
}
