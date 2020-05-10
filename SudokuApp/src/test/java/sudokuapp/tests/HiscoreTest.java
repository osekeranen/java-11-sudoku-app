package sudokuapp.tests;

import java.util.ArrayList;
import java.util.Collections;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import sudokuapp.logic.Difficulty;
import sudokuapp.logic.Hiscore;
import sudokuapp.logic.ScoreCounter;

public class HiscoreTest {

    @Before
    public void setUp() {
    }

    @Test
    public void hiscoresSortedInDescendingOrder() {
        ArrayList<Hiscore> hiscores = new ArrayList<>();
        hiscores.add(new Hiscore(Difficulty.BEGINNER, 1650, "Matti"));
        hiscores.add(new Hiscore(Difficulty.BEGINNER, 2250, "Maija"));
        hiscores.add(new Hiscore(Difficulty.BEGINNER, 1800, "Mikko"));
        hiscores.add(new Hiscore(Difficulty.BEGINNER, 3300, "Miina"));
        hiscores.add(new Hiscore(Difficulty.BEGINNER, 750, "Uolevi"));
        Collections.sort(hiscores);
        
        assertEquals("Miina", hiscores.get(0).getName());
    }
    
    @Test
    public void hiscoresWorkWithScoreCounter() {
        ScoreCounter counter = new ScoreCounter();
        Hiscore score = new Hiscore(Difficulty.INTERMEDIATE, counter.getScore(), "Untamo");
        assertEquals(3600, score.getScore());
    }
}
