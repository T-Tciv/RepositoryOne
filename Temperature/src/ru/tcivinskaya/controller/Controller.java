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
        view.getInitialUnitsList().addActionListener(x -> setInitialUnit());

        view.getFinalUnitsList().addActionListener(x -> setFinalUnit());

        view.getInitialTemperatureTextField().addActionListener(x -> setInitialTemperature());

        view.getButton().addActionListener(x -> getFinalTemperature());
    }

    private void setInitialUnit() {
        model.setInitialUnit(view.getInitialUnit());

    }

    private void setFinalUnit() {
        model.setFinalUnit(view.getFinalUnit());
    }

    private void setInitialTemperature() {
        String inputLine = view.getInitialTemperatureTextField().getText();

        if (!isNumber(inputLine)) {
            throw new IllegalArgumentException("Температурой может быть только число");
        }

        model.setInitialTemperature(Double.parseDouble(inputLine));
    }

    private static boolean isNumber(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void getFinalTemperature() {
        view.setFinalTemperature(model.getFinalTemperature());
        view.showTemperature();
    }
}
