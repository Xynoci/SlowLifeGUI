import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

/**
 * Created by Makone_Xia on 11/11/16.
 */
public class MainPanelTest {
    private final int SIZE = 15;
    private MainPanel mockPanel;
    private Cell[][] mockCells;
    private boolean[][] mockbackup;

    @Before
    public void setUp() throws Exception {
        mockPanel = new MainPanel(SIZE);
        mockCells = mockPanel.getCells();
    }

    @Test
    public void runContinuousRUnchanged() throws Exception {
        int expected = mockPanel._r;
        new Thread(() -> mockPanel.runContinuous()).start();
        mockPanel.stop();
        assertEquals(expected, mockPanel._r);
    }

    @Test
    public void runContinuousCellSizeUnchanged() throws Exception {
        new Thread(() -> mockPanel.runContinuous()).start();
        mockPanel.stop();
        assertEquals(SIZE, mockPanel.getCellsSize());
    }

    @Test
    public void runContinuousCellUpdateUnchanged() {
        //Arrange:
        //Create MainPanel Object and set cell size as 2
        MainPanel mp = new MainPanel(2);

        Cell[][] mc = new Cell[2][2];
        mc[0][0] = new Cell();
        mc[0][1] = new Cell();
        mc[1][0] = new Cell();
        mc[1][1] = new Cell();
        mc[0][0].setAlive(true);
        mc[1][1].setAlive(true);
        mp.setCells(mc);
        new Thread(mp::runContinuous).start();
        try {
            Thread.sleep(100);
            mp.stop();
        } catch (InterruptedException ignored) { }
        Assert.assertFalse(mc[0][0].getAlive());
        Assert.assertFalse(mc[1][1].getAlive());
    }

    @Test
    public void getNumNeighborsThreeAlive() throws Exception {
        int expected = 8;
        Method method = MainPanel.class.getDeclaredMethod("getNumNeighbors", int.class, int.class);
        method.setAccessible(true);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mockCells[i][j].setAlive(true);
            }
        }
        assertEquals(expected, (int) method.invoke(mockPanel, 1, 1));
    }

    @Test
    public void backupUnchangedWhenInitWithAllFalse() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                mockCells[i][j] = new Cell();
                mockCells[i][j].setAlive(false);
            }
        }
        mockPanel.run();
        mockPanel.undo();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Assert.assertFalse(mockCells[i][j].getAlive());
            }
        }
    }

}