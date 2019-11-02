package ru.tcivinskaya.controller;

import ru.tcivinskaya.model.Model;
import ru.tcivinskaya.view.View;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void initController() {
        view.setController(this);
    }

    public void getFinalTemperature() {

    }
}
