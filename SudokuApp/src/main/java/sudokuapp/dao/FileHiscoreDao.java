package sudokuapp.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import sudokuapp.domain.Difficulty;
import sudokuapp.domain.Hiscore;

/**
 * Saving hiscores in a file
 */
public class FileHiscoreDao implements HiscoreDao {
    private ArrayList<Hiscore> hiscores;
    private String file;

    public FileHiscoreDao(String file) throws Exception {
        hiscores = new ArrayList<>();
        this.file = file;
        
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                String name = parts[0];
                int score = Integer.valueOf(parts[1]);
                Difficulty difficulty = Difficulty.valueOf(parts[2]);
                Hiscore hiscore = new Hiscore (difficulty, score, name);
                hiscores.add(hiscore);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }
    
    public void add(Hiscore hiscore) throws Exception {
        hiscores.add(hiscore);
        this.save();
    }
    
    private void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Hiscore hiscore : hiscores) {
                writer.write(hiscore.getName() + ";" + hiscore.getScore() + ";" + hiscore.getDifficulty().name() + "\n");
            }
        }
    }
    
    @Override
    public ArrayList<Hiscore> getAll() {
        return hiscores;
    }
    
}
