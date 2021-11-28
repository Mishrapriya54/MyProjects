
package com.priyam.guessnumberrestservice.data;

import com.priyam.guessnumberrestservice.CommandLineRunner;
import com.priyam.guessnumberrestservice.model.Games;
import com.priyam.guessnumberrestservice.model.Rounds;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.jupiter.api.Test;


//@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommandLineRunner.class)
public class GameRoundDaoDbTest {
    
    @Autowired
    GameDao gameDao;

    @Autowired
    GameRoundDao gameRoundDao;
    
    public GameRoundDaoDbTest() {
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
     * Test of createRound method, of class GameRoundDaoDb.
     */
    @Test
    public void testCreateRound() {
        
        Games game = new Games();
        game.setAnswer(4566);
        game.setGameStatus("In Progress");
        game = gameDao.add(game);
        Games fromDao = gameDao.findById(game.getGameId());
        assertEquals(game, fromDao, "Check if newly added game got created?");
        
        Rounds round=new Rounds();
        round.setUserGuess("8989");
        round.setResult("e:0:p:0");
         //LocalDateTime rightNow = LocalDateTime.now();
         round.setTime(LocalDateTime.parse("2021-09-27T09:44:20"));
       
        round.setGameId(game.getGameId());
        round.setAnswer(game.getAnswer());
        Rounds fromRoundDao=gameRoundDao.createRound(round, game.getGameId());
        gameRoundDao.findRoundById(game.getGameId());
       assertEquals(round, fromRoundDao, "Check if newly added game got created with the new round?");
   }

    /**
     * Test of evaluateGuess method, of class GameRoundDaoDb.
     */
    @Test
    public void testEvaluateGuess() {
        
        
    }

    /**
     * Test of getAllRounds method, of class GameRoundDaoDb.
     */
    @Test
    public void testGetAllRounds() {
        
        Games Game = new Games();
        Game.setAnswer(1000);
        Game.setGameStatus("Finished");
        Game = gameDao.add(Game);
        
         
        Rounds firstRound=new Rounds();
        firstRound.setUserGuess("8989");
        firstRound.setResult("e:0:p:0");
         //LocalDateTime rightNow = LocalDateTime.now();
         firstRound.setTime(LocalDateTime.parse("2021-09-27T09:44:20"));
       firstRound.setGameId(Game.getGameId());
        firstRound.setAnswer(Game.getAnswer());
        Rounds firstRoundFromDao=gameRoundDao.createRound(firstRound,Game.getGameId());
        gameRoundDao.findRoundById(Game.getGameId());
       assertEquals(firstRound, firstRoundFromDao, "Check if newly added game got created with the new round?");

        
        Rounds secondRound=new Rounds();
        secondRound.setUserGuess("1000");
        secondRound.setResult("e:1:p:0");
       
         secondRound.setTime(LocalDateTime.parse("2021-09-27T10:44:20"));
     secondRound.setGameId(Game.getGameId());
       secondRound.setAnswer(Game.getAnswer());
        Rounds secondRoundFromDao=gameRoundDao.createRound(secondRound,Game.getGameId());
        gameRoundDao.findRoundById(Game.getGameId());
       assertEquals(secondRound, secondRoundFromDao, "Check if newly added game got created with the new round?");
List<Rounds> retrievedList=gameRoundDao.getAllRounds(Game.getGameId());
       
       assertNotNull(retrievedList, "Checking if list is empty");
        //check if list size is have same number of data added.
        assertEquals(2, retrievedList.size(), "Checking if size is equal to the number of rounds added.");
        assertFalse((retrievedList.size() != 2), "Checking if size is equal to the data added.");
        //checking if list has firstRound
        assertTrue(retrievedList.contains(firstRoundFromDao),"checking if it contains first round");

        //checking if list has secondGame
        assertTrue(retrievedList.contains(secondRoundFromDao), "checking if it contains second round");
        
    }

    /**
     * Test of update method, of class GameRoundDaoDb.
     */
    @Test
    public void testUpdate() {
    }

    /**
     * Test of deleteById method, of class GameRoundDaoDb.
     */
    @Test
    public void testDeleteById() {
    }

    /**
     * Test of findRoundById method, of class GameRoundDaoDb.
     */
    @Test
    public void testFindRoundById() {
    }

    /**
     * Test of getAll method, of class GameRoundDaoDb.
     */
    @Test
    public void testGetAll() {
    }

    /**
     * Test of findById method, of class GameRoundDaoDb.
     */
    @Test
    public void testFindById() {
    }
    
}
