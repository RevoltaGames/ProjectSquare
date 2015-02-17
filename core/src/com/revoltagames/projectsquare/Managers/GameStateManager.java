package com.revoltagames.projectsquare.Managers;

import com.revoltagames.projectsquare.GameStates.GameState;
import java.util.Stack;

/**
 * Created by caenrique93 on 17/02/15.
 */
public class GameStateManager {
    private Stack<GameState> states;

    public GameStateManager(GameState firstState) {
        states = new Stack<GameState>();
        setState(firstState);
    }

    public void setState(GameState state) {
        states.push(state);
    }

    public void update(float dt) {
        States.peek().update(dt);
    }

    public void draw() {
        States.peek().draw();
    }
}
