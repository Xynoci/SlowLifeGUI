import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
    public void rUnchanged() throws Exception{
        int expected = mockPanel._r;
        new Thread(() -> mockPanel.runContinuous()).start();
        mockPanel.stop();
        assertEquals(expected, mockPanel._r);
    }

    @Test
    public void cellSizeUnchanged() throws Exception {
        new Thread(() -> mockPanel.runContinuous()).start();
        mockPanel.stop();
        assertEquals(SIZE, mockPanel.getCellsSize());
    }



}