package com.priyam.guessnumberrestservice.data;

import java.util.List;
import com.priyam.guessnumberrestservice.model.Games;

public interface GameDao {

    Games add(Games game);

    List<Games> getAll();

    Games findById(int id);

    void update(Games game);

    void deleteById(int id);
}
