package org.panacea.drmp.hrmg.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.panacea.drmp.hrmg.HRMGenerator;
import org.panacea.drmp.hrmg.controller.APIPostNotifyData;
import org.panacea.drmp.hrmg.domain.notifications.DataNotification;
import org.panacea.drmp.hrmg.exception.HRMGException;
import org.panacea.drmp.hrmg.service.OrchestratorNotificationHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrchestratorNotificationHandlerServiceImpl implements OrchestratorNotificationHandlerService {

    public static final String INVALID_NOTIFICATION_ERR_MSG = "Invalid Data Notification Body.";

    @Autowired
    HRMGenerator hrmGenerator;

    @Override
    public APIPostNotifyData.DataNotificationResponse perform(DataNotification notification) throws HRMGException {
//        log.info("Received Data Notification from Orchestrator: {}", notification);
        log.info("[HRMG] Received Notification from Orchestrator");
        try {
            if (notification.getEnvironment() == null) {
                throw new HRMGException("No environment defined for notification.");
            }
            hrmGenerator.generateHRM(notification);

            return new APIPostNotifyData.DataNotificationResponse(notification.getEnvironment(), notification.getSnapshotId(), notification.getSnapshotTime());
        } catch (HRMGException e) {
            log.error("HRMGException occurred: ", e);
            throw new HRMGException(INVALID_NOTIFICATION_ERR_MSG, e);
        }
    }
}
