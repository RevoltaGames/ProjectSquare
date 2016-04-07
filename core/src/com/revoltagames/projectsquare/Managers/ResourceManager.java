package com.revoltagames.projectsquare.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.MusicLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader.FreeTypeFontLoaderParameter;

/**
 * Esta clase maneja todos los recursos del juego, desde imagenes,
 * atlases de texturas, sonidos, música, tipos de letras o efectos
 * particulas.
 */
public class ResourceManager {

    AssetManager manager;
    FileHandleResolver resolver;

    public static final String MOVE = "Music/move.wav";
    public static final String GAMEOVER = "Music/gameover.mp3";
    public static final String LIFEUP = "Music/lifeup.mp3";
    public static final String LIFELOST = "Music/lifelost.mp3";
    public static final String MENU = "Music/menuloop2.mp3";
    public static final String INTROGAME = "Music/AreUReady.mp3";
    public static final String INTROMENU = "Music/menuinicio.mp3";

    public static final String SPLASH = "Images/splash4.png";

    public static final String B_EXIT = "Images/exit.png";
    public static final String B_SCORES = "Images/highscore.png";
    public static final String B_SETTINGS = "Images/settings.png";
    public static final String B_VOLUME_ON = "Images/soundon.png";
    public static final String B_VOLUME_OFF = "Images/soundoff.png";

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

        FreeTypeFontLoaderParameter sizeParams;

        sizeParams = new FreeTypeFontLoaderParameter();
        sizeParams.fontFileName = "Fonts/forcedSquare.ttf";
        sizeParams.fontParameters.size = Gdx.graphics.getHeight()/20;
        sizeParams.fontParameters.color = ColorManager.WHITE;
        manager.load("whiteSmallFont.ttf", BitmapFont.class, sizeParams);

        sizeParams = new FreeTypeFontLoaderParameter();
        sizeParams.fontFileName = "Fonts/forcedSquare.ttf";
        sizeParams.fontParameters.size = Gdx.graphics.getHeight()/5;
        sizeParams.fontParameters.color = ColorManager.MID_GREY;
        manager.load("greyGiantFont.ttf", BitmapFont.class, sizeParams);

        sizeParams = new FreeTypeFontLoaderParameter();
        sizeParams.fontFileName = "Fonts/forcedSquare.ttf";
        sizeParams.fontParameters.size = Gdx.graphics.getHeight()/20;
        sizeParams.fontParameters.color = ColorManager.MID_GREY;
        manager.load("greySmallFont.ttf", BitmapFont.class, sizeParams);

        sizeParams = new FreeTypeFontLoaderParameter();
        sizeParams.fontFileName = "Fonts/forcedSquare.ttf";
        sizeParams.fontParameters.size = Gdx.graphics.getHeight()/7;
        sizeParams.fontParameters.color = ColorManager.MID_GREY;
        manager.load("greyBigFont.ttf", BitmapFont.class, sizeParams);

        sizeParams = new FreeTypeFontLoaderParameter();
        sizeParams.fontFileName = "Fonts/forcedSquare.ttf";
        sizeParams.fontParameters.size = Gdx.graphics.getHeight()/7;
        sizeParams.fontParameters.color = ColorManager.NRED;
        manager.load("redBigFont.ttf", BitmapFont.class, sizeParams);

        sizeParams = new FreeTypeFontLoaderParameter();
        sizeParams.fontFileName = "Fonts/forcedSquare.ttf";
        sizeParams.fontParameters.size = Gdx.graphics.getHeight()/12;
        sizeParams.fontParameters.color = ColorManager.NRED;
        manager.load("redMediumFont.ttf", BitmapFont.class, sizeParams);

        sizeParams = new FreeTypeFontLoaderParameter();
        sizeParams.fontFileName = "Fonts/forcedSquare.ttf";
        sizeParams.fontParameters.size = Gdx.graphics.getHeight()/12;
        sizeParams.fontParameters.color = ColorManager.NBLUE;
        manager.load("blueMediumFont.ttf", BitmapFont.class, sizeParams);

        sizeParams = new FreeTypeFontLoaderParameter();
        sizeParams.fontFileName = "Fonts/forcedSquare.ttf";
        sizeParams.fontParameters.size = Gdx.graphics.getHeight()/30;
        sizeParams.fontParameters.color = ColorManager.GREY;
        manager.load("greyTinyFont.ttf", BitmapFont.class, sizeParams);

        musica[0] = "Music/gamesong.mp3";

        manager.load(musica[0], Music.class);
        manager.load(MOVE, Music.class);
        manager.load(LIFEUP, Music.class);
        manager.load(LIFELOST, Music.class);
        manager.load(INTROGAME, Music.class);
        manager.load(INTROMENU, Music.class);
        manager.load(GAMEOVER, Music.class);
        manager.load(MENU, Music.class);

    }

    /**
     * @return la textura de la pantalla de inicio
     */
    public Texture getSplashScreen() {
        return manager.get(SPLASH, Texture.class);
    }

    /**
     * Método para obtener efectos de sonido.
     * @param move
     * @return
     */
    public Music getSound(String move) {
        return manager.get(move, Music.class);
    }

    /**
     * Método para obtener música del gestor de recursos.
     * @param id
     * @return
     */
    public Music getMusic(int id) {
        if(manager.isLoaded(musica[id]))
            return manager.get(musica[id], Music.class);
        return null;
    }

    /**
     * Método para obtener las tipografías.
     * @param keyword
     * @return
     */
    public BitmapFont getFont(String keyword) {
        return manager.get(keyword, BitmapFont.class);
    }

    /**
     * Método para obtener las imágenes
     * @param id
     * @return
     */
    public Texture getImage(String id) {
        return manager.get(id, Texture.class);
    }

    /**
     * Método llama al update() del manager para saber si ha terminado de cargar los recursos o no.
     * @return true si ha terminado de cargar los recursos y false en caso contrario.
     */
    public boolean update() {
        return manager.update();
    }

}

