package com.priyam.guessnumberrestservice.service;

import com.priyam.guessnumberrestservice.data.GameDao;
import com.priyam.guessnumberrestservice.data.GameRoundDao;
import com.priyam.guessnumberrestservice.model.Games;
import com.priyam.guessnumberrestservice.model.Rounds;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public class guessthenumberserviceimpl implements guessthenumberservice {

    private final GameDao gameDao;
    private final GameRoundDao roundDao;

    @Autowired
    public guessthenumberserviceimpl(GameDao game, GameRoundDao round) {
        this.gameDao = game;
        this.roundDao = round;
    }

    public guessthenumberserviceimpl() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Games> getAllGames() {
        List<Games> games = gameDao.getAll();
        return games;
    }

    @Override
    public Games createGame(Games newGame) {
        newGame.setAnswer(getAnswer());
        newGame = gameDao.add(newGame);
        return newGame;
    }

    @Override
    public Games findById(int gameId) {
        return gameDao.findById(gameId);
    }

    @Override
    public List<Rounds> getAllRounds(int id) {
        List<Rounds> allRounds = roundDao.getAllRounds(id);
        return allRounds;
    }

    @Override
    public String evaluateGuess(String guess, int id) {
        roundDao.evaluateGuess(guess, id);
        String evaluatedRound = roundDao.evaluateGuess(guess, id);
        return evaluatedRound;
    }

    @Override
    public Rounds createRound(Rounds newRound, int gameId) {

        Games game = findById(gameId);
        newRound.setAnswer(game.getAnswer());
        newRound.setGameId(gameId);
        Rounds successRound = roundDao.createRound(newRound, gameId);

        return successRound;
    }

    public int getAnswer() {
        Random rnd = new Random();
        int answer = rnd.nextInt(9999);
        return answer;
    }

    @Override
    public List<Rounds> getAllRounds() {
        List<Rounds> rounds = roundDao.getAll();
        return rounds;
    }

    @Override
    public void update(Games game) {
        gameDao.update(game);
    }

}
