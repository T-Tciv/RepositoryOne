package ru.tcivinskaya.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public double getTo() {
        return to;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double userNumber) {
        return ((userNumber >= from) && (to >= userNumber));
    }

    public static double getRangeLength(Range range) {
        return range.getTo() - range.getFrom();
    }

    public static Range getIntersection(Range firstRange, Range secondRange) {
        double firstFrom = firstRange.getFrom();
        double firstTo = firstRange.getTo();
        double secondFrom = secondRange.getFrom();
        double secondTo = secondRange.getTo();

        if ((firstTo < secondFrom) || (secondTo < firstFrom)) {
            return null;
        }

        double from = (firstFrom > secondFrom) ? firstFrom : secondFrom;
        double to = (firstTo < secondTo) ? firstTo : secondTo;

        return new Range(from, to);
    }

    public static Range[] getUnion(Range firstRange, Range secondRange) {
        double firstFrom = firstRange.getFrom();
        double firstTo = firstRange.getTo();
        double secondFrom = secondRange.getFrom();
        double secondTo = secondRange.getTo();

        if ((firstTo < secondFrom) || (secondTo < firstFrom)) {
            return new Range[]{firstRange, secondRange};
        }

        double from = (firstFrom < secondFrom) ? firstFrom : secondFrom;
        double to = (firstTo > secondTo) ? firstTo : secondTo;

        return new Range[]{new Range(from, to)};
    }

    public static Range[] getDifference(Range firstRange, Range secondRange) {
        double firstFrom = firstRange.getFrom();
        double firstTo = firstRange.getTo();
        double secondFrom = secondRange.getFrom();
        double secondTo = secondRange.getTo();

        if ((firstTo < secondFrom) || (secondTo < firstFrom)) {
            return new Range[]{firstRange};
        }

        if (firstFrom < secondFrom) {
            if (firstTo <= secondTo) {
                return new Range[]{new Range(firstFrom, secondFrom)};
            } else {
                return new Range[]{new Range(firstFrom, secondFrom), new Range(secondTo, firstTo)};
            }
        } else if (firstTo > secondTo) {
            return new Range[]{new Range(secondTo, firstTo)};
        }

        return new Range[]{};
    }
}
