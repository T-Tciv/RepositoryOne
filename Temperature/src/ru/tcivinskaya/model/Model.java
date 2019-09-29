package ru.tcivinskaya.model;

import ru.tcivinskaya.main.Units;

public class Model {
    private double initialTemperature;
    private Units initialUnit;
    private Units finalUnit;

    public void setInitialTemperature(double initialTemperature) {
        this.initialTemperature = initialTemperature;
    }

    public void setInitialUnit(Units initialUnit) {
        this.initialUnit = initialUnit;
    }

    public void setFinalUnit(Units finalUnit) {
        this.finalUnit = finalUnit;
    }

    public double getFinalTemperature() {
        double finalTemperature = initialTemperature;

        if (finalUnit != initialUnit) {
            if (initialUnit == Units.KELVIN) {
                finalTemperature = initialTemperature - 273.15;
            } else if (initialUnit == Units.FAHRENHEIT) {
                finalTemperature = (initialTemperature - 32) * 5.0 / 9;
            }

            if (finalUnit == Units.KELVIN) {
                finalTemperature += 273.15;
            } else if (finalUnit == Units.FAHRENHEIT) {
                finalTemperature = 9.0 / 5 * finalTemperature + 32;
            }
        }

        return finalTemperature;
    }
}
