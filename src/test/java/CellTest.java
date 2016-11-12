import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.notification.RunListener;

import static org.junit.Assert.*;

public class CellTest {

    private Cell cell;

    @Before
    public void setUp(){
        this.cell = new Cell();
    }

    @Test
    public void aliveStringMatches() throws Exception {
        String expected = "X";
        cell.setAlive(true);
        Assert.assertEquals(expected,cell.toString());
    }

    @Test
    public void deadStringMatches() throws Exception {
        String expected = ".";
        cell.setAlive(false);
        Assert.assertEquals(expected,cell.toString());
    }

}