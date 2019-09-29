package ru.tcivinskaya.main;

import ru.tcivinskaya.controller.Controller;
import ru.tcivinskaya.model.Model;
import ru.tcivinskaya.view.View;

public class Main {
    public static void main(String[] args) {
        runTemperatureApplication();
    }

    private static void runTemperatureApplication() {
        Controller controller = new Controller(new Model(), new View());

        controller.initController();
    }
}
