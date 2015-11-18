package com.utc.api13.commun.entities;

import java.util.List;

public class ChessboardEntity extends ADataEntity {
 
    private static final long serialVersionUID = -2495824863031165639L;
    private List<PositionEntity> cases;

    /**
     * @return the cases
     */
    public List<PositionEntity> getCases() {
        return cases;
    }

    /**
     * @param cases the cases to set
     */
    public void setCases(final List<PositionEntity> cases) {
        this.cases = cases;
    }
}
