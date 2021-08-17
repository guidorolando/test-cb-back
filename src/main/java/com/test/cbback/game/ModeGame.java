package com.test.cbback.game;

import com.test.cbback.controller.request.RegistryGame;
import com.test.cbback.model.Game;

public interface ModeGame {

    public Game start();

    public void end();

    public boolean join(RegistryGame registryGame);
}
