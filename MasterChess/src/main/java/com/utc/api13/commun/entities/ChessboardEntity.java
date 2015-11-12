package com.utc.api13.commun.entities;

import java.util.List;

public class ChessboardEntity extends DataEntity {
 
    private static final long serialVersionUID = -2495824863031165639L;
    private List<CaseEntity> cases;

    /**
     * @return the cases
     */
    public List<CaseEntity> getCases() {
        return cases;
    }

    /**
     * @param cases the cases to set
     */
    public void setCases(final List<CaseEntity> cases) {
        this.cases = cases;
    }
}
