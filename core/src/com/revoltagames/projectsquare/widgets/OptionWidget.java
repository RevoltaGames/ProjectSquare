package com.revoltagames.projectsquare.widgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.revoltagames.projectsquare.Entities.Option;
import com.revoltagames.projectsquare.Entities.TextFrame;
import com.revoltagames.projectsquare.Managers.ColorManager;
import com.revoltagames.projectsquare.ProjectSquare;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caenrique93 on 4/7/16.
 */
public class OptionWidget {
    private final String text;
    private Option options;
    private final float x;
    private final float y;
    private final float height;
    private final float width;

    private TextFrame frame;
    private List<TextFrame> optionFrames;
    private final Color apagado = ColorManager.GREY;
    private final Color encendido = ColorManager.NGREEN;

    public OptionWidget(float x, float y, String text, List<String> options, int option) {
        this.x = x;
        this.y = y;
        this.width = ProjectSquare.WIDTH * 8 / 10;
        this.height = ProjectSquare.HEIGTH / 16;
        this.options = new Option(options);
        this.options.setOption(option);
        this.text = text;

        frame = new TextFrame(x, y, this.width, this.height, "Dificulty", ColorManager.ColorClock);
        frame.setTextCentered(false);

        float optionWidthGap = this.width/(2*this.options.getNumOptions());
        float gap = optionWidthGap / 10;
        float optionWidth = optionWidthGap - gap;
        float optionX = this.x + optionWidth/2;

        optionFrames = new ArrayList<TextFrame>();
        for(int i=0; i<this.options.getNumOptions(); i++) {
            TextFrame textframe = new TextFrame(optionX + optionWidthGap*i, y, optionWidth, 2*this.height/3, options.get(i), this.apagado);
            textframe.setFont(ProjectSquare.resManager.getFont("whiteSmallFont.ttf"));
            optionFrames.add(textframe);
        }
        optionFrames.get(option).setColor(this.encendido);
    }

    public void draw(ShapeRenderer renderer, SpriteBatch spriteRenderer) {
        frame.draw(renderer,spriteRenderer);
        for (TextFrame t: optionFrames) t.draw(renderer, spriteRenderer);
    }

    public int getOption() {
        return this.options.getSelectedOption();
    }

    public void touched(float x, float y) {
        for(int i=0; i<optionFrames.size(); i++) {
            if (optionFrames.get(i).touched(x, y)) {
                this.optionFrames.get(options.getSelectedOption()).setColor(this.apagado);
                this.optionFrames.get(i).setColor(this.encendido);
                this.options.setOption(i);
            }
        }
    }

    public boolean isTouched(int x, int y) {
        return frame.touched(x,y);
    }
}
