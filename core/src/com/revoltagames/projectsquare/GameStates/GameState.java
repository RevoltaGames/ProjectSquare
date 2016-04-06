package com.revoltagames.projectsquare.GameStates;

import com.revoltagames.projectsquare.Managers.GameStateManager;


/**
 * Estado del juego. Representa una pantalla como la pantalla de Play o la del menú.
 */
public abstract class GameState {

    protected GameStateManager gsm;

    protected GameState(GameStateManager gsm){
        this.gsm = gsm;
        init();
    }

    /**
     * Método de inicialización. La instanciación de atributos de la clase se hace aqui.
     */
    public abstract void init();

    /**
     * Método para actualizar el estado.
     * @param dt tiempo pasado desde la última actualizacion en ms.
     */
    public abstract void update(float dt);

    /**
     * Si el estado pinta objetos por pantalla, lo hace en este método.
     */
    public abstract void draw();

    /**
     * El input que necesite el estado lo hace aquí.
     */
    public abstract void handleInput();

    /**
     * Este método siempre se llama antes de desechar un estado.
     */
    public abstract void dispose();
}
