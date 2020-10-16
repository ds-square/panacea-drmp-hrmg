
package org.panacea.drmp.hrmg.domain.employee;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class Policy {

    private Boolean directed;
    private String relationType;
    private String sourceId;
    private String targetId;

}
