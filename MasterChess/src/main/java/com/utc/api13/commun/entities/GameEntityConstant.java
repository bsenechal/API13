/**
 * 
 */
package com.utc.api13.commun.entities;

/**
 * @author Benoît
 *
 */
public class GameEntityConstant {

    private static final int[] INITIAL_COLUMNS_BISHOP = { 3, 6 };

    private static final int[] INITIAL_COLUMNS_KNIGHT = { 2, 7 };

    private static final int[] INITIAL_COLUMNS_ROOK = { 1, 8 };

    private static final int[] INITIAL_COLUMNS_PAWN = { 1, 2, 3, 4, 5, 6, 7, 8 };

    private GameEntityConstant() {

    }

    /**
     * @return the initialColumnsBishop
     */
    public static final int[] getInitialColumnsBishop() {
        return INITIAL_COLUMNS_BISHOP;
    }

    /**
     * @return the initialColumnsKnight
     */
    public static final int[] getInitialColumnsKnight() {
        return INITIAL_COLUMNS_KNIGHT;
    }

    /**
     * @return the initialColumnsRook
     */
    public static final int[] getInitialColumnsRook() {
        return INITIAL_COLUMNS_ROOK;
    }

    /**
     * @return the initialColumnsPawn
     */
    public static final int[] getInitialColumnsPawn() {
        return INITIAL_COLUMNS_PAWN;
    }
}
