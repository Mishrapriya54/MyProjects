package com.priyam.guessnumberrestservice.data;

import com.priyam.guessnumberrestservice.model.Games;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Profile("database")
public class GameDaoDb implements GameDao {

    private JdbcTemplate jdbc;

    @Autowired
    public GameDaoDb(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public GameDaoDb() {
    }

    private final String SELECT_ALL_Games = "Select * from game",
            INSERT_GAME = "Insert into game (GameStatus,answer) Values (?,?)",
            SELECT_GAME_BY_ID = "SELECT * FROM game WHERE gameId=?",
            SELECT_GAME_BY_ID_IN_PROGRESS = "SELECT gameId,gameStatus FROM game WHERE gameId=?";

    @Override
    @Transactional
    public Games add(Games game) {

        jdbc.update(INSERT_GAME, game.getGameStatus(), game.getAnswer());

        int gameId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        game.setGameId(gameId);

        return game;
    }

    @Override
    @Transactional
    public List<Games> getAll() {
        GameMapper gamemap = new GameMapper();
        return jdbc.query(SELECT_ALL_Games, gamemap);

    }

    @Override
    @Transactional
    public Games findById(int gameId) {
        try {
            Games returnedGame = jdbc.queryForObject(SELECT_GAME_BY_ID, new GameMapper(), gameId);
            return returnedGame;
        } catch (DataAccessException ex) {
            return null;
        }
    }

//    
//     @Override
//    @Transactional
//    public Games findById(int gameId) {
//        try{
//          Games returnedGame= jdbc.queryForObject(SELECT_GAME_BY_ID, new GameMapper(),gameId);
//               if(returnedGame.isFinished()==false)
//               {
//               Games InProgressGame= jdbc.queryForObject(SELECT_GAME_BY_ID_IN_PROGRESS, new InProgressGameMapper(),gameId);
//               return InProgressGame;
//               }
//             return returnedGame;  
//        }catch(DataAccessException ex) {
//            return null;
//        }
//   }
//    
    @Override
    public void update(Games game) {
        final String UPDATE_GAME = "UPDATE game SET GameStatus = ?, answer = ? WHERE gameid = ?";
        jdbc.update(UPDATE_GAME,
                game.getGameStatus(),
                game.getAnswer(),
                game.getGameId());
    }

    @Override
    public void deleteById(int id) {
        final String DELETE_GAME_FROM_ROUND = "DELETE FROM gameround "
                + "WHERE gameid = ?";
        jdbc.update(DELETE_GAME_FROM_ROUND, id);

        final String DELETE_GAME = "DELETE FROM game WHERE gameid = ?";
        jdbc.update(DELETE_GAME, id);

    }

    public static final class GameMapper implements RowMapper<Games> {

        @Override
        public Games mapRow(ResultSet rs, int index) throws SQLException {
            Games rm = new Games();
            rm.setGameId(rs.getInt("GameId"));
            rm.setAnswer(rs.getInt("Answer"));
            rm.setGameStatus(rs.getString("GameStatus"));
            return rm;
        }
    }

    public static final class InProgressGameMapper implements RowMapper<Games> {

        @Override
        public Games mapRow(ResultSet rs, int index) throws SQLException {
            Games rm2 = new Games();
            rm2.setGameId(rs.getInt("GameId"));
            rm2.setGameStatus(rs.getString("GameStatus"));
            return rm2;
        }
    }

}
