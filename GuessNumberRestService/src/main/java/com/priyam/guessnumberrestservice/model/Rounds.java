package com.priyam.guessnumberrestservice.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Rounds {

    private int RoundId;
    private String UserGuess;
    private int answer;
    private String Result;
    private LocalDateTime Time;
    private int GameId;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.RoundId;
        hash = 67 * hash + Objects.hashCode(this.UserGuess);
        hash = 67 * hash + this.answer;
        hash = 67 * hash + Objects.hashCode(this.Result);
        hash = 67 * hash + Objects.hashCode(this.Time);
        hash = 67 * hash + this.GameId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rounds other = (Rounds) obj;
        if (this.RoundId != other.RoundId) {
            return false;
        }
        if (this.answer != other.answer) {
            return false;
        }
        if (this.GameId != other.GameId) {
            return false;
        }
        if (!Objects.equals(this.UserGuess, other.UserGuess)) {
            return false;
        }
        if (!Objects.equals(this.Result, other.Result)) {
            return false;
        }
        if (!Objects.equals(this.Time, other.Time)) {
            return false;
        }
        return true;
    }

    public int getGameId() {
        return GameId;
    }

    public void setGameId(int GameId) {
        this.GameId = GameId;
    }

    public int getRoundId() {
        return RoundId;
    }

    public void setRoundId(int RoundId) {
        this.RoundId = RoundId;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public String getUserGuess() {
        return UserGuess;
    }

    public void setUserGuess(String UserGuess) {
        this.UserGuess = UserGuess;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String Result) {
        this.Result = Result;
    }

    public LocalDateTime getTime() {
        return Time;
    }

    public void setTime(LocalDateTime Time) {
        this.Time = Time;
    }

}
