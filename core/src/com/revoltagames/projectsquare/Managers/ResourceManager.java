package com.revoltagames.projectsquare.Managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Music;

/**
 * Created by caenrique93 on 18/02/15.
 * Esta clase maneja todos los recursos del juego, desde imagenes,
 * atlases de texturas, sonidos, música, tipos de letras o efectos
 * particulas.
 */
public class

        ResourceManager extends AssetManager {
    public static final String RETRY_BUTTON = "retry-button.png";
    private static final String MENU_BUTTON = "menu-button.png";
    private static final String RG_LOGO = "RevoltaGames-logo.png";
    public static final String MUSIC = "Music/music.wav";
    public static final String MOVE = "Music/move.wav";
    public static final String GAMEOVER = "Music/gameover.wav";
    public static final String MENU = "Music/menu.wav";


    /**
     * Para añadir un nuevo recurso:
     *
     * manager.load("<direccion del recurso>", Clase del recurso)
     * ej : manager.load"retry-button.png", Texture.class);
     */
    public ResourceManager() {
        super();
        /*this.load(RETRY_BUTTON, Texture.class);
        this.load(MENU_BUTTON, Texture.class);
        this.load(RG_LOGO, Texture.class);*/
        this.load(MUSIC, Music.class);
        this.load(MOVE, Music.class);
        this.load(GAMEOVER, Music.class);
        this.load(MENU, Music.class);
    }

    public Texture getTexture(String id) {
        if(this.isLoaded(id)) return this.get(id, Texture.class);
        return null;
    }

    public Music getMusic(String id) {
        if(this.isLoaded(id)) return this.get(id, Music.class);
        return null;
    }
}

