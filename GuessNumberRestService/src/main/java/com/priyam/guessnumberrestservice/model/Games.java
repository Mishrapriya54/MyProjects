package com.priyam.guessnumberrestservice.model;

import java.util.Objects;

public class Games {

    private int GameId;

    private int answer;
    private String GameStatus;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.GameId;
        hash = 67 * hash + this.answer;
        hash = 67 * hash + Objects.hashCode(this.GameStatus);
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
        final Games other = (Games) obj;
        if (this.GameId != other.GameId) {
            return false;
        }
        if (this.answer != other.answer) {
            return false;
        }
        if (!Objects.equals(this.GameStatus, other.GameStatus)) {
            return false;
        }
        return true;
    }

    public int getGameId() {
        return GameId;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public void setGameId(int GameId) {
        this.GameId = GameId;
    }

    public String getGameStatus() {
        return GameStatus;
    }

    public void setGameStatus(String GameStatus) {
        this.GameStatus = GameStatus;
    }

}
