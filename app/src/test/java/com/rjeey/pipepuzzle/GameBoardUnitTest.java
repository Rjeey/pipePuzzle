package com.rjeey.pipepuzzle;

import com.rjeey.pipepuzzle.logic.GameBoard;
import com.rjeey.pipepuzzle.logic.Pipe;
import com.rjeey.pipepuzzle.logic.Point;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameBoardUnitTest {

    static final int BOARD_SIZE = 8;
    static GameBoard _board;

    @BeforeClass
    public static void init() {
        _board = new GameBoard(BOARD_SIZE);
    }

    @Before
    public void resetBoard()
    {
        _board.resetGame();
    }

    @Test
    public void addPipe()
    {
        boolean result = _board.addPipeToBoard(new Point(0,0), Pipe.PipeType.HORIZONTAL);
        assertTrue("failed to add pipe",result);
    }

    @Test
    public void testFlow()
    {
        for (int j = 0; j < 3; j++)
        {
            //create path
            Pipe firstPipe = new Pipe(new Point(0,6), Pipe.PipeType.START_RIGHT);
            firstPipe.setFlowDirection(Pipe.Directions.RIGHT);
            _board.injectFirstPipe(firstPipe);
            boolean result = _board.addPipeToBoard(new Point(1,6), Pipe.PipeType.BOTTOM_LEFT);
            assertTrue("failed to add pipe (1,6)",result);
            result = _board.addPipeToBoard(new Point(1,5), Pipe.PipeType.VERTICAL);
            assertTrue("failed to add pipe (1,5)",result);
            result = _board.addPipeToBoard(new Point(1,4), Pipe.PipeType.VERTICAL);
            assertTrue("failed to add pipe (1,4)",result);
            result = _board.addPipeToBoard(new Point(1,3), Pipe.PipeType.TOP_RIGHT);
            assertTrue("failed to add pipe (1,3)",result);
            result = _board.addPipeToBoard(new Point(2,3), Pipe.PipeType.HORIZONTAL);
            assertTrue("failed to add pipe (2,3)",result);
            result = _board.addPipeToBoard(new Point(3,3), Pipe.PipeType.HORIZONTAL);
            assertTrue("failed to add pipe (3,3)",result);
            result = _board.addPipeToBoard(new Point(4,3), Pipe.PipeType.CROSS);
            assertTrue("failed to add pipe (3,3)",result);
            result = _board.addPipeToBoard(new Point(5,3), Pipe.PipeType.CROSS);
            assertTrue("failed to add pipe (3,3)",result);
            result = _board.addPipeToBoard(new Point(6,3), Pipe.PipeType.HORIZONTAL);
            assertTrue("failed to add pipe (3,3)",result);
            //start flow in pipes
            _board.startGame();

            for (int i = 0; i < 10; i++)
            {
                _board.notifyPipeIsFull();
            }

            _board .resetGame();
        }
    }

    @Test
    public void testJunctionFlow()
    {
        //create path
        Pipe firstPipe = new Pipe(new Point(0,6), Pipe.PipeType.START_RIGHT);
        firstPipe.setFlowDirection(Pipe.Directions.RIGHT);
        _board.injectFirstPipe(firstPipe);
        boolean result = _board.addPipeToBoard(new Point(1,6), Pipe.PipeType.BOTTOM_LEFT);
        assertTrue("failed to add pipe (1,6)",result);
        result = _board.addPipeToBoard(new Point(1,5), Pipe.PipeType.VERTICAL);
        assertTrue("failed to add pipe (1,5)",result);
        result = _board.addPipeToBoard(new Point(1,4), Pipe.PipeType.VERTICAL);
        assertTrue("failed to add pipe (1,4)",result);
        result = _board.addPipeToBoard(new Point(1,3), Pipe.PipeType.TOP_RIGHT);
        assertTrue("failed to add pipe (1,3)",result);
        result = _board.addPipeToBoard(new Point(2,3), Pipe.PipeType.HORIZONTAL);
        assertTrue("failed to add pipe (2,3)",result);
        result = _board.addPipeToBoard(new Point(3,3), Pipe.PipeType.HORIZONTAL);
        assertTrue("failed to add pipe (3,3)",result);
        result = _board.addPipeToBoard(new Point(4,3), Pipe.PipeType.CROSS);
        assertTrue("failed to add pipe (4,3)",result);
        result = _board.addPipeToBoard(new Point(5,3), Pipe.PipeType.TOP_LEFT);
        assertTrue("failed to add pipe (5,3)",result);
        result = _board.addPipeToBoard(new Point(5,4), Pipe.PipeType.BOTTOM_LEFT);
        assertTrue("failed to add pipe (5,4)",result);
        result = _board.addPipeToBoard(new Point(4,4), Pipe.PipeType.BOTTOM_RIGHT);
        assertTrue("failed to add pipe (4,4)",result);
        //start flow in pipes
        _board.startGame();
        for (int i = 0; i < 15; i++)
        {
            _board.notifyPipeIsFull();
        }
    }
}