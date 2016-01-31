
package com.utc.api13.commun.entities;

public final class ChessboardEntity extends ADataEntity {

    private static final long serialVersionUID = -2495824863031165639L;
    private static final int MIN_COLUMN = 1;
    private static final int MAX_COLUMN = 8;
    private static final int MIN_LINE = 1;
    private static final int MAX_LINE = 8;

    public static boolean isCaseOnChessboard(final PositionEntity position) {
        if (position.getPositionX() >= MIN_COLUMN && position.getPositionX() <= MAX_COLUMN
                && position.getPositionY() >= MIN_LINE && position.getPositionY() <= MAX_LINE) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
