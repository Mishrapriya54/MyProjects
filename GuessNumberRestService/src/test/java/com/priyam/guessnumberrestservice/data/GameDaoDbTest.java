package com.priyam.guessnumberrestservice.data;

import com.priyam.guessnumberrestservice.CommandLineRunner;
import com.priyam.guessnumberrestservice.model.Games;
import com.priyam.guessnumberrestservice.model.Rounds;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommandLineRunner.class)
public class GameDaoDbTest {

    @Autowired
    GameDao gameDao;

    @Autowired
    GameRoundDao gameRoundDao;

    public GameDaoDbTest() {
    }

    @BeforeEach
    public void setUp() {
        List<Rounds> round = gameRoundDao.getAll();
        round.forEach(rounds -> {
            gameRoundDao.deleteById(rounds.getGameId());
        });
        List<Games> game = gameDao.getAll();
        game.forEach(games -> {
            gameDao.deleteById(games.getGameId());
        });
    }

    /**
     * Test of add method, of class GameDaoDb.
     */
    @Test
    public void testAdd() {
        Games game = new Games();
        game.setAnswer(4566);
        game.setGameStatus("In Progress");
        game = gameDao.add(game);
        Games fromDao = gameDao.findById(game.getGameId());
        assertEquals(game, fromDao, "Check if newly added game got created?");
    }

    /**
     * Test of getAll method, of class GameDaoDb.
     */
    @Test
    public void testGetAll() {
        Games firstGame = new Games();
        firstGame.setAnswer(1000);
        firstGame.setGameStatus("Finished");
        firstGame = gameDao.add(firstGame);

        Games secondGame = new Games();
        secondGame.setAnswer(8888);
        secondGame.setGameStatus("In Progress");
        secondGame = gameDao.add(secondGame);

        List<Games> addedGames = gameDao.getAll();

        assertNotNull(addedGames, "Checking if list is empty");
        //check if list size is have same number of data added.
        assertEquals(2, addedGames.size(), "Checking if size is equal to the data added.");
        assertFalse((addedGames.size() != 2), "Checking if size is equal to the data added.");
        //checking if list has firstGame
        assertTrue(gameDao.getAll().contains(firstGame), "checking if it contains first game");

        //checking if list has secondGame
        assertTrue(gameDao.getAll().contains(secondGame), "checking if it contains second game");
    }

    /**
     * Test of findById method, of class GameDaoDb.
     */
    @Test
    public void testFindById() {

        Games game = new Games();
        game.setAnswer(3000);
        game.setGameStatus("In Progress");
        game = gameDao.add(game);
        Games fromDao = gameDao.findById(game.getGameId());
        assertEquals(game, fromDao, "Check if newly added game got created?");
        assertEquals(fromDao.getGameId(), game.getGameId(), "checking if it contains second game");
    }

    /**
     * Test of update method, of class GameDaoDb.
     */
    @Test
    public void testUpdate() {

        Games game = new Games();
        game.setGameStatus("In Prrogress");
        game.setAnswer(1234);
        game = gameDao.add(game);
        Games retrievedGame = gameDao.findById(game.getGameId());
        assertEquals(game, retrievedGame);


        game.setGameStatus("Finished");
        gameDao.update(game);
        assertNotEquals(game, retrievedGame);

        Games fromDao = gameDao.findById(game.getGameId());

        assertEquals(game, fromDao);
    }

    /**
     * Test of deleteById method, of class GameDaoDb.
     */
    @Test
    public void testDeleteById() {
        Games game = new Games();
        game.setAnswer(2000);
        game.setGameStatus("In Progress");
        game = gameDao.add(game);
        Games fromDao = gameDao.findById(game.getGameId());
        assertEquals(game, fromDao, "Check if newly added game got created?");
        assertEquals(fromDao.getGameId(), game.getGameId(), "check if created game and game retrieved have same Id's");

        Rounds round = new Rounds();
        round.setUserGuess("8989");
        round.setResult("e:0:p:0");
        round.setTime(LocalDateTime.parse("2021-09-27T09:44:20"));
        round.setGameId(game.getGameId());
        round.setAnswer(2000);

        gameDao.deleteById(game.getGameId());
        fromDao = gameDao.findById(game.getGameId());
        assertNull(fromDao);

    }
}
