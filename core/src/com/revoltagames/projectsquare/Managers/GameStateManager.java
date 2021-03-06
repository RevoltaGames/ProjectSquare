package com.revoltagames.projectsquare.Managers;

import com.revoltagames.projectsquare.GameStates.GameState;
import com.revoltagames.projectsquare.ProjectSquare;

import java.util.Stack;

/**
 * Created by caenrique93 on 17/02/15.
 */
public class GameStateManager {
    private Stack<GameState> states;
    public ProjectSquare projectSquare;

    public GameStateManager(ProjectSquare ps) {
        states = new Stack<GameState>();
        projectSquare = ps;
    }

    public void push(GameState state) {
        states.push(state);
    }

    public void pop() {
        states.peek().dispose();
        states.pop();
    }

    public void setState(GameState state) {
        if(!states.empty()) this.pop();
        states.push(state);
    }

    public void update(float dt) {
        states.peek().update(dt);
    }

    public void draw() {
        states.peek().draw();
    }
}
