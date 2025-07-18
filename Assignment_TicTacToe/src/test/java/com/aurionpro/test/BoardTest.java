package com.aurionpro.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.aurionpro.model.Board;
import com.aurionpro.model.CellValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {

    private Board board;

    @BeforeEach
    void init() {
        board = new Board();
    }

    // all cells must be CellValue.EMPTY at init
    @Test
    void createdCellShouldBeEmpty() {
        for (int i = 0; i < 9; i++) {
            assertEquals(CellValue.EMPTY, board.getCell(i));
        }
    }

    // updateCell returns true or false based on if update was possible or not
    @Test
    void shouldBeAbleToMarkXorOToACell() {
        boolean resultX = board.updateCell(0, CellValue.X);
        boolean resultO = board.updateCell(1, CellValue.O);

        assertTrue(resultX);
        assertTrue(resultO);
        assertEquals(CellValue.X, board.getCell(0));
        assertEquals(CellValue.O, board.getCell(1));
    }

    @Test
    void shouldNotAllowOverwritingACell() {
        boolean firstMove = board.updateCell(0, CellValue.X);
        boolean secondMove = board.updateCell(0, CellValue.O); // should not overwrite and return false

        assertTrue(firstMove);
        assertFalse(secondMove);
        // not updated
        assertEquals(CellValue.X, board.getCell(0)); // still X
    }
}
