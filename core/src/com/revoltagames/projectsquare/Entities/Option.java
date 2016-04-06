package com.revoltagames.projectsquare.Entities;

import java.util.List;

public class Option {
    private final List<String> options;
    private int currentOption;

    public Option(List<String> options) {
        this.options = options;
        this.currentOption = 0;
    }

    public int getSelectedOption() {
        return this.currentOption;
    }

    public void setOption(int option) {
        this.currentOption = option;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getNumOptions() {
        return this.options.size();
    }
}
