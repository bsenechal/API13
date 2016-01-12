package com.utc.api13.commun.entities;

import java.util.ArrayList;
import java.util.List;

public final class ChessboardEntity extends ADataEntity {

    private static final long serialVersionUID = -2495824863031165639L;
    private static final int MIN_COLUMN = 1;
    private static final int MAX_COLUMN = 8;
    private static final int MIN_LINE = 1;
    private static final int MAX_LINE = 8;

    private static List<PositionEntity> cases;

    public ChessboardEntity() {
        super();
        cases = new ArrayList<PositionEntity>();

        for (int x = MIN_COLUMN; x < MAX_COLUMN; x++) {
            for (int y = MIN_LINE; y < MAX_LINE; y++) {
                cases.add(new PositionEntity(x, y));
            }
        }
    }

    /**
     * @return the cases
     */
    public static List<PositionEntity> getCases() {
        return cases;
    }
    
    public static boolean isCaseOnChessboard(final PositionEntity position) {
        for (PositionEntity positionCase : cases){
            if (positionCase.equals(position)){
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
}
