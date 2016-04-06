package com.revoltagames.projectsquare.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.MusicLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader.FreeTypeFontLoaderParameter;

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
    public static final String GAMEOVER = "Music/gameover.mp3";
    public static final String LIFEUP = "Music/lifeup.mp3";
    public static final String MENU = "Music/menuloop2.mp3";
    public static final String INTROGAME = "Music/AreUReady.mp3";
    public static final String INTROMENU = "Music/menuinicio.mp3";

    public static final String SPLASH = "Images/splash4.png";

    public static final String B_EXIT = "Images/exit.png";
    public static final String B_SCORES = "Images/highscore.png";
    public static final String B_SETTINGS = "Images/settings.png";
    public static final String B_VOLUME_ON = "Images/soundon.png";
    public static final String B_VOLUME_OFF = "Images/soundoff.png";
    public static final String TITTLE = "Images/tittle.png";
    public static final String TOUCH = "Images/touchtoplay.png";
    public static final String SHADOW = "Images/sombra.png";

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

        manager.setLoader(FreeTypeFontGenerator.class,
                new FreeTypeFontGeneratorLoader(resolver));
        manager.setLoader(BitmapFont.class, ".ttf",
                new FreetypeFontLoader(resolver));
        manager.setLoader(Music.class,
                new MusicLoader(resolver));

        // Cargamos Splash
        manager.load(SPLASH, Texture.class);
        manager.finishLoading();

        manager.load(B_EXIT, Texture.class);
        manager.load(B_SCORES, Texture.class);
        manager.load(B_SETTINGS, Texture.class);
        manager.load(B_VOLUME_OFF, Texture.class);
        manager.load(B_VOLUME_ON, Texture.class);
        manager.load(TITTLE, Texture.class);
        manager.load(TOUCH, Texture.class);
        manager.load(SHADOW, Texture.class);

        FreeTypeFontLoaderParameter size1Params =
                new FreeTypeFontLoaderParameter();
        size1Params.fontFileName = "Fonts/dinBlack.ttf";
        size1Params.fontParameters.size = Gdx.graphics.getHeight()/8;
        size1Params.fontParameters.color = Color.LIGHT_GRAY;
        manager.load("size1.ttf", BitmapFont.class, size1Params);

        FreeTypeFontLoaderParameter size2Params =
                new FreeTypeFontLoaderParameter();
        size2Params.fontFileName = "Fonts/dinBlack.ttf";
        size2Params.fontParameters.size = Gdx.graphics.getHeight()/12;
        size2Params.fontParameters.color = Color.LIGHT_GRAY;
        manager.load("size2.ttf", BitmapFont.class, size2Params);

        FreeTypeFontLoaderParameter size3Params =
                new FreeTypeFontLoaderParameter();
        size3Params.fontFileName = "Fonts/forcedSquare.ttf";
        size3Params.fontParameters.size = Gdx.graphics.getHeight()/20;
        size3Params.fontParameters.color = ColorManager.MID_GREY;
        manager.load("size3.ttf", BitmapFont.class, size3Params);


        FreeTypeFontLoaderParameter size4Params =
                new FreeTypeFontLoaderParameter();
        size4Params.fontFileName = "Fonts/forcedSquare.ttf";
        size4Params.fontParameters.size = Gdx.graphics.getHeight()/7;
        size4Params.fontParameters.color = ColorManager.MID_GREY;
        manager.load("square.ttf", BitmapFont.class, size4Params);

        musica[0] = "Music/gamesong.mp3";

        manager.load(musica[0], Music.class);
        manager.load(MOVE, Music.class);
        manager.load(LIFEUP, Music.class);
        manager.load(INTROGAME, Music.class);
        manager.load(INTROMENU, Music.class);
        manager.load(GAMEOVER, Music.class);
        manager.load(MENU, Music.class);

    }

    public Texture getSplashScreen() {
        return manager.get(SPLASH, Texture.class);
    }

    public Music getSound(String move) {
        return manager.get(move, Music.class);
    }

    public Music getMusic(int id) {
        if(manager.isLoaded(musica[id]))
            return manager.get(musica[id], Music.class);
        return null;
    }

    public BitmapFont getFont(int size) {
        BitmapFont bitFont;
        switch(size) {
            case 1:
                bitFont = manager.get("size1.ttf", BitmapFont.class);
                break;
            case 2:
                bitFont = manager.get("size2.ttf", BitmapFont.class);
                break;
            case 3:
                bitFont = manager.get("size3.ttf", BitmapFont.class);
                break;
            case 4:
                bitFont = manager.get("square.ttf", BitmapFont.class);
                break;
            default:
                bitFont = manager.get("size1.ttf", BitmapFont.class);
        }
        //bitFont.setColor(color);
        return  bitFont;
    }

    public Texture getImage(String id) {
        return manager.get(id, Texture.class);
    }

    public boolean update() {
        return manager.update();
    }

}

