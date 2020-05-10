package sudokuapp.tests;

import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import sudokuapp.logic.ScoreCounter;

public class ScoreCounterTest {
    ScoreCounter counter;

    @Before
    public void setUp() {
    }

    @Test
    public void counterChecksTime() {
        counter = new ScoreCounter();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        assertEquals(3599, counter.getScore());
    }

    @Test
    public void failedCheckSubstractsPoints() {
        counter = new ScoreCounter();
        counter.addFailedCheck();
        assertEquals(3500, counter.getScore());
    }

    @Test
    public void pointsCannotBeNegative() {
        counter = new ScoreCounter();

        for (int i = 0; i < 40; i++) {
            counter.addFailedCheck();
        }
        
        assertEquals(0, counter.getScore());
    }
}
