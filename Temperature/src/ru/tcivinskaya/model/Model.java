package ru.tcivinskaya.model;

public class Model {
    private double temperature;
    private Units initialUnit;
    private Units finalUnit;

    public Model(double temperature, Units initialUnit, Units finalUnit) {
        this.temperature = temperature;
        this.initialUnit = initialUnit;
        this.finalUnit = finalUnit;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public Units getInitialUnit() {
        return initialUnit;
    }

    public void setInitialUnit(Units initialUnit) {
        this.initialUnit = initialUnit;
    }

    public void setFinalUnit(Units finalUnit) {
        this.finalUnit = finalUnit;
    }

    public Units getFinalUnit() {
        return finalUnit;
    }

    public void changeUnit() {
        if (initialUnit == Units.CELSIUM) {

        }
    }
}
