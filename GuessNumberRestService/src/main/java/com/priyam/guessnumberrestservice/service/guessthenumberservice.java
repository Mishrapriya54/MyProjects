package com.priyam.guessnumberrestservice.service;

import com.priyam.guessnumberrestservice.model.Games;
import com.priyam.guessnumberrestservice.model.Rounds;
import java.util.List;

public interface guessthenumberservice {

    public Games findById(int gameId);

    public List<Rounds> getAllRounds(int gameId);

    public Rounds createRound(Rounds round, int gameId);

    public String evaluateGuess(String guess, int id);

    public Games createGame(Games newGame);

    public List<Games> getAllGames();

    public List<Rounds> getAllRounds();

    public void update(Games game);

}
