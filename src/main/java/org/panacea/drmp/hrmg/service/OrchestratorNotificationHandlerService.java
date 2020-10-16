package org.panacea.drmp.hrmg.service;

import org.panacea.drmp.hrmg.controller.APIPostNotifyData;
import org.panacea.drmp.hrmg.domain.notifications.DataNotification;
import org.panacea.drmp.hrmg.exception.HRMGException;

public interface OrchestratorNotificationHandlerService {
    APIPostNotifyData.DataNotificationResponse perform(DataNotification dataNotification) throws HRMGException;
}
