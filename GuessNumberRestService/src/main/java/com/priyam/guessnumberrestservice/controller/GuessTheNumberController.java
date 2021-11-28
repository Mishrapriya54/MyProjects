package com.priyam.guessnumberrestservice.controller;

import com.priyam.guessnumberrestservice.model.Games;
import com.priyam.guessnumberrestservice.model.Rounds;
import com.priyam.guessnumberrestservice.service.guessthenumberservice;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/GuessTheNumber")
public class GuessTheNumberController {

    private final guessthenumberservice numberService;

    @Autowired
    public GuessTheNumberController(guessthenumberservice service) {
        this.numberService = service;
    }
 

    @GetMapping("/round/{id}")
    public ResponseEntity<Rounds> findRoundById(@PathVariable int id) {

        List<Rounds> result = numberService.getAllRounds(id);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("/game")
    public List<Games> getAllGames() {
        List<Games> games = numberService.getAllGames();
        return games;
    }

    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public int beginGame(@RequestBody UserInput input) {
        Games newGame = input.getNewGameInfo();
        Games createdGame = numberService.createGame(newGame);
        return createdGame.getGameId();
    }
    
    
    
    

    @GetMapping("game/{id}")
    public ResponseEntity<Games> findById(@PathVariable int id) {
        Games result = numberService.findById(id);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/guess")
    public Rounds evaluateGuess(@RequestBody UserInput input) {
        Rounds round = input.getNewRoundInfo();
        numberService.createRound(round, input.getGameId());
        return round;
    }
}
