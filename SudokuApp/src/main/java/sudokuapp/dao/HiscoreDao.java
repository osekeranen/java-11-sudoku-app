package sudokuapp.dao;

import java.util.List;
import sudokuapp.domain.Hiscore;

/**
 * Interface for storing hiscores
 */
public interface HiscoreDao {
    /**
     * Method for getting a list of all hiscores
     * 
     * @return list of hiscores
     */
    List<Hiscore> getAll();
}
