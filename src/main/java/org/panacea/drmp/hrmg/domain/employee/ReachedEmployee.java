
package org.panacea.drmp.hrmg.domain.employee;

import lombok.Data;

import java.util.Objects;

@Data
@SuppressWarnings("unused")
public class ReachedEmployee {

    private String reachedId;
    private String linkType;

    public ReachedEmployee(String reachedId, String linkType) {
        this.reachedId = reachedId;
        this.linkType = linkType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReachedEmployee that = (ReachedEmployee) o;
        return Objects.equals(linkType, that.linkType) &&
                Objects.equals(reachedId, that.reachedId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(linkType, reachedId);
    }
}
