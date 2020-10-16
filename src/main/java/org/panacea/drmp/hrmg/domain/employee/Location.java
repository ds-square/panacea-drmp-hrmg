
package org.panacea.drmp.hrmg.domain.employee;

import lombok.Data;

import java.util.Objects;

@Data
@SuppressWarnings("unused")
public class Location {

    private String building;
    private String corridor;
    private String floor;
    private String id;
    private String room;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(building, location.building) &&
                Objects.equals(corridor, location.corridor) &&
                Objects.equals(floor, location.floor) &&
                Objects.equals(room, location.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(building, corridor, floor, room);
    }
}
