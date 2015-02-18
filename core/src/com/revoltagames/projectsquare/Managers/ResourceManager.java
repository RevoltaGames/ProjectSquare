package com.revoltagames.projectsquare.Managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by caenrique93 on 18/02/15.
 * Esta clase maneja todos los recursos del juego, desde imagenes,
 * atlases de texturas, sonidos, música, tipos de letras o efectos
 * particulas.
 */
public class ResourceManager extends AssetManager {
    public static final String RETRY_BUTTON = "retry-button.png";
    private static final String MENU_BUTTON = "menu-button.png";
    private static final String RG_LOGO = "RevoltaGames-logo.png";

    /**
     * Para añadir un nuevo recurso:
     *
     * manager.load("<direccion del recurso>", Clase del recurso)
     * ej : manager.load"retry-button.png", Texture.class);
     */
    public ResourceManager() {
        super();
        this.load(RETRY_BUTTON, Texture.class);
        this.load(MENU_BUTTON, Texture.class);
        this.load(RG_LOGO, Texture.class);
    }

    public Texture getTexture(String id) {
        if(this.isLoaded(id)) return this.get(id, Texture.class);
        return null;
    }
}

