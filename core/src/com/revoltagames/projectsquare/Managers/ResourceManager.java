package com.revoltagames.projectsquare.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.MusicLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.revoltagames.projectsquare.ProjectSquare;

/**
 * Created by caenrique93 on 18/02/15.
 * Esta clase maneja todos los recursos del juego, desde imagenes,
 * atlases de texturas, sonidos, música, tipos de letras o efectos
 * particulas.
 */
public class ResourceManager {

    AssetManager manager;
    FileHandleResolver resolver;

    FreeTypeFontGenerator fontGenerator;

    public static final String MOVE = "Music/move.wav";
    public static final String GAMEOVER = "Music/gameover.wav";
    public static final String MENU = "Music/menu.wav";

    private final String[] musica = new String[8];

    /**
     * Para añadir un nuevo recurso:
     *
     * manager.load("<direccion del recurso>", Clase del recurso)
     * ej : manager.load"retry-button.png", Texture.class);
     */
    public ResourceManager() {
        manager = new AssetManager();
        resolver = new InternalFileHandleResolver();
        manager.setLoader(Music.class,
                new MusicLoader(resolver));

        fontGenerator = new FreeTypeFontGenerator(
                Gdx.files.internal("Fonts/dinBlack.ttf")
        );

        for(int i=0;i<musica.length;i++) {
            musica[i] = "Music/music" + i%4 + ".wav";
            System.out.println(musica[i]);
        }

        manager.load(musica[0], Music.class);
        manager.load(musica[1], Music.class);
        manager.load(musica[2], Music.class);
        manager.load(musica[3], Music.class);
        manager.load(musica[4], Music.class);
        manager.load(musica[5], Music.class);
        manager.load(musica[6], Music.class);
        manager.load(musica[7], Music.class);
        manager.load(MOVE, Music.class);
        manager.load(GAMEOVER, Music.class);
        manager.load(MENU, Music.class);
    }

    public Music getSound(String move) {
        return manager.get(move, Music.class);
    }

    public Music getMusic(int id) {
        if(manager.isLoaded(musica[id]))
            return manager.get(musica[id], Music.class);
        return null;
    }

    public BitmapFont getFont(int size, Color color) {
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = ((ProjectSquare.HEIGTH/100)*size);
        parameter.color = color;
        return fontGenerator.generateFont(parameter);
    }

    public boolean update() {
        return manager.update();
    }

}

