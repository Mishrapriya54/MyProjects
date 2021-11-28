package com.priyam.guessnumberrestservice.data;

import com.priyam.guessnumberrestservice.model.Games;
import com.priyam.guessnumberrestservice.model.Rounds;
import java.util.List;

public interface GameRoundDao {

    List<Rounds> getAllRounds(int id);

    Games findById(int id);

    boolean update(Rounds round);

    void deleteById(int id);

    public Rounds createRound(Rounds round, int gameId);

    public String evaluateGuess(String guess, int gameId);

    public Rounds findRoundById(int gameid);

    public List<Rounds> getAll();

}
