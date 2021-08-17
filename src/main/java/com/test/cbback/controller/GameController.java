package com.test.cbback.controller;

import com.test.cbback.controller.request.RegistryGame;
import com.test.cbback.game.FreeGame;
import com.test.cbback.model.Game;
import com.test.cbback.model.Bet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "game")
public class GameController {

    @Autowired
    private FreeGame freeGame;

    @GetMapping(value = "start")
    public Game startGame() {
        return this.freeGame.start();
    }

    @PostMapping(value = "registry")
    public boolean registry(@RequestBody RegistryGame registryGame) {
        return freeGame.join(registryGame);
    }
}
