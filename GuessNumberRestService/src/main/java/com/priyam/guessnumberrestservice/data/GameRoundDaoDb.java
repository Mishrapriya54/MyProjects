package com.priyam.guessnumberrestservice.data;

import com.priyam.guessnumberrestservice.data.GameDaoDb.GameMapper;
import com.priyam.guessnumberrestservice.model.Games;
import com.priyam.guessnumberrestservice.model.Rounds;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
public class GameRoundDaoDb implements GameRoundDao {

    private static final List<Rounds> rounds = new ArrayList<>();

    private JdbcTemplate jdbc;
    private final String SELECT_ALL_ROUNDS = "Select * from gameround",
            INSERT_ROUND = "INSERT INTO gameround(UserGuess,Result,Time,GameId,answer) values(?,?,?,?,?)",
            SELECT_ROUND_BY_ID = "Select * from gameround WHERE RoundId=?",
            SELECT_GAME_BY_ID = "SELECT * FROM game WHERE gameId=?",
            UPDATE_GAMESTATUS = "UPDATE game SET GameStatus = ? WHERE GameId = ?",
            SELECT_ROUNDS_BY_GAME_ID = "SELECT r.RoundId,r.UserGuess,r.Result,r.Time,r.GameId,g.GameStatus from gameround r left join game g on r.GameId=g.GameId where r.GameId=? order by r.Time",
            SELECT_ALLROUNDS_BY_GAME_ID="SELECT * from gameround where GameId=?";
    @Autowired
    public GameRoundDaoDb(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    GameRoundDaoDb() {

    }

    @Override
    @Transactional
    public Rounds createRound(Rounds round, int gameId) {
        String result = evaluateGuess(round.getUserGuess(), gameId);
        round.setResult(result);
        jdbc.update(INSERT_ROUND, round.getUserGuess(), round.getResult(), round.getTime().toString(), gameId, round.getAnswer());

        int roundId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        round.setRoundId(roundId);

        return round;
    }

    @Override
    public String evaluateGuess(String UserGuess, int id) {
        Games runningGameId = findById(id);
        int answer = runningGameId.getAnswer();
        String answerString = Integer.toString(answer);
        int e = 0, p = 0;
        if (answerString.equals(UserGuess)) {
            e++;

            runningGameId.setGameStatus("Finished");
            jdbc.update(UPDATE_GAMESTATUS, "Finished", runningGameId.getGameId());
        } else {
            for (int i = 0; i < 4; i++) {
                if (answerString.charAt(i) == UserGuess.charAt(i)) {
                    p++;
                }

            }
        }
        String result = "e:" + e + ":" + "p" + ":" + p;
        return result;
    }

    @Override
    @Transactional
    public List<Rounds> getAllRounds(int id) {

        RoundMapper roundMap = new RoundMapper();
        //GameDaoDb.InProgressGameMapper gm = new GameDaoDb.InProgressGameMapper();
        return jdbc.query(SELECT_ALLROUNDS_BY_GAME_ID, roundMap, id);
    }

    @Override
    public boolean update(Rounds round) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteById(int id) {
        final String DELETE_GAME_FROM_ROUND = "DELETE FROM gameround "
                + "WHERE gameid = ?";
        jdbc.update(DELETE_GAME_FROM_ROUND, id);
    }

    @Override
    public Rounds findRoundById(int gameId) {
       
        try {
            Rounds returnedRound = jdbc.queryForObject(SELECT_ROUND_BY_ID, new RoundMapper(),gameId);
            return returnedRound;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Rounds> getAll() {
        RoundMapper roundmap = new RoundMapper();
        return jdbc.query(SELECT_ALL_ROUNDS, roundmap);
    }

    private static final class RoundMapper implements RowMapper<Rounds> {

        @Override
        public Rounds mapRow(ResultSet rs, int index) throws SQLException {
            Rounds rm = new Rounds();
            rm.setRoundId(rs.getInt("RoundId"));
            rm.setUserGuess(rs.getString("UserGuess"));
            rm.setResult(rs.getString("Result"));
            String str = rs.getString("Time");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

            rm.setTime(dateTime);
           
            rm.setGameId(rs.getInt("GameId"));
             rm.setAnswer(rs.getInt("Answer"));
            return rm;
        }
    }

    @Override
    @Transactional
    public Games findById(int id) {

        try {
            return jdbc.queryForObject(SELECT_GAME_BY_ID, new GameMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }
}
