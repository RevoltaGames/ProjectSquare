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
    public static final String MUSIC1 = "Music/music1.wav";
    public static final String MUSIC2 = "Music/music2.wav";
    public static final String MUSIC3 = "Music/music3.wav";
    public static final String MUSIC4 = "Music/music4.wav";
    public static final String MUSIC5 = "Music/music5.wav";
    public static final String MUSIC6 = "Music/music6.wav";
    public static final String MUSIC7 = "Music/music7.wav";
    public static final String MOVE = "Music/move.wav";
    public static final String GAMEOVER = "Music/gameover.wav";
    public static final String MENU = "Music/menu.wav";

    private static String[] musica = new String[8];


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
        this.load(MUSIC1, Music.class);
        this.load(MUSIC2, Music.class);
        this.load(MUSIC3, Music.class);
        this.load(MOVE, Music.class);
        this.load(GAMEOVER, Music.class);
        this.load(MENU, Music.class);
        musica[0]=MUSIC;
        musica[1]=MUSIC1;
        musica[2]=MUSIC2;
        musica[3]=MUSIC3;
        musica[4]=MUSIC;
        musica[5]=MUSIC1;
        musica[6]=MUSIC2;
        musica[7]=MUSIC3;
    }

    public Texture getTexture(String id) {
        if(this.isLoaded(id)) return this.get(id, Texture.class);
        return null;
    }

    public Music getMusic(String id) {
        if(this.isLoaded(id)) return this.get(id, Music.class);
        return null;
    }
    public Music getMusic(int id) {

        if(this.isLoaded(musica[id])) return this.get(musica[id], Music.class);
        return null;
    }
}

