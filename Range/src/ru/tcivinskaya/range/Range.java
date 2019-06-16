package ru.tcivinskaya.range;

class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getLength() {
        return Math.abs(to - from);
    }

    public boolean isInside(double userNumber) {
        double measurementError = 1.0e-10;
        return (((userNumber - from >= -measurementError) && (to - userNumber >= -measurementError)) || ((userNumber - to >= -measurementError) && (from - userNumber >= -measurementError)));
    }

    public static void main(String[] args) {
        double firstNumber = 1;
        double lastNumber = 2.5;

        Range range = new Range(firstNumber, lastNumber);

        System.out.printf("Длина интервала от %f до %f равна %f.%n", firstNumber, lastNumber, range.getLength());

        double userNumber = 0;

        if (range.isInside(userNumber)) {
            System.out.printf("Число %f принадлежит диапазону от %f до %f.", userNumber, firstNumber, lastNumber);
        } else {
            System.out.printf("Число %f не принадлежит диапазону от %f до %f.", userNumber, firstNumber, lastNumber);
        }
    }
}
