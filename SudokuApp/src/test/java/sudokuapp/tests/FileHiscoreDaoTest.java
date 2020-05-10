package sudokuapp.tests;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import sudokuapp.dao.FileHiscoreDao;
import sudokuapp.logic.Difficulty;
import sudokuapp.logic.Hiscore;

public class FileHiscoreDaoTest {
    FileHiscoreDao hiscoreDao;
    String hiscoreFile;
    
    @Before
    public void setUp() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("config.properties"));
            hiscoreFile = properties.getProperty("hiscoreTestFile");

            hiscoreDao = new FileHiscoreDao(hiscoreFile);
            hiscoreDao.add(new Hiscore(Difficulty.INTERMEDIATE, 4000, "Yrjänä"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @After
    public void tearDown() {
        File file = new File(hiscoreFile);
        file.delete();
    }
    
    @Test
    public void daoCanReadExcistingFile() {
        try {
            FileHiscoreDao newHiscoreDao = new FileHiscoreDao(hiscoreFile);
            ArrayList<Hiscore> hiscores = newHiscoreDao.getAll();
            Collections.sort(hiscores);
            assertEquals("Yrjänä", hiscores.get(0).getName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void addingNewHiscoreAddsTotalByOne() {
        int sizeAtBeginning = hiscoreDao.getAll().size();
        
        try {
            hiscoreDao.add(new Hiscore(Difficulty.ADVANCED, 1600, "Ulla"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        assertEquals(sizeAtBeginning + 1, hiscoreDao.getAll().size());
    }
}
