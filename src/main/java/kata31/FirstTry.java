package kata31;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//TODO
public class FirstTry {

    public static List<Point> closestPair(List<Point> points) {
        points.sort(Comparator.comparingDouble(o -> o.x));
        return Arrays.asList();
    }

    public static double calcDistanceBetweenPair(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.y - p1.y, 2) + Math.pow(p1.x - p2.x, 2));
    }

    public static List<Point> divideAndConquer(List<Point> points, double mid) {
        List<Point> leftPart = new ArrayList<>();
        List<Point> rightPart = new ArrayList<>();
        for (Point point : points) {
            if (point.x < mid) leftPart.add(point);
            else rightPart.add(point);
        }
        if (leftPart.size() > 3) {
            //TODO
            //divideAndConquer(leftPart, )
        }
        if (leftPart.size() <= 3 || rightPart.size() <= 3) {
            List<List<Point>> pointsPairs = new ArrayList<>();

            List<Point> leftPair = closestPair(leftPart);
            List<Point> rightPair = closestPair(leftPart);

            double leftSize = leftPair.size() > 1 ?
                    calcDistanceBetweenPair(leftPair.get(0), leftPair.get(1)) : Double.MAX_VALUE;
            double rightSize = rightPair.size() > 1 ?
                    calcDistanceBetweenPair(rightPair.get(0), rightPair.get(1)) : Double.MAX_VALUE;

            List<Point> inMinRange = new ArrayList<>();
            for (Point point : leftPart) {
                if (mid - point.x < Math.min(leftSize, rightSize)) inMinRange.add(point);
            }
            for (Point point : rightPart) {
                if (point.x - mid < Math.min(leftSize, rightSize)) inMinRange.add(point);
            }

            inMinRange = inMinRange.stream()
                    .sorted((Comparator.comparingDouble(o -> o.y)))
                    .collect(Collectors.toList());

            double middleSize = Double.MAX_VALUE;
            List<Point> closestMiddlePair = new ArrayList<>();
            for (int i = 0; i < inMinRange.size() - 1; i++) {
                Point p1 = inMinRange.get(i);
                Point p2 = inMinRange.get(i + 1);
                if (calcDistanceBetweenPair(p1, p2) <= middleSize) {
                    closestMiddlePair = new ArrayList<>();
                    closestMiddlePair.add(p1);
                    closestMiddlePair.add(p2);
                }
            }

            pointsPairs.add(leftPair);
            pointsPairs.add(rightPair);
            pointsPairs.add(closestMiddlePair);

            return pointsPairs.stream()
                    .filter(el -> el.size() > 1)
                    .min(Comparator.comparingDouble(o -> calcDistanceBetweenPair(o.get(0), o.get(1))))
                    .orElse(null);
        } else {

            return new ArrayList<>();
        }
    }

    private List<Point> findPairWithMinDistance(List<Point> points) {
        double distance = Double.MAX_VALUE;
        if (points.size() == 1) {
            return points;
        }
        List<Point> result = null;
        for (int i = 0; i < points.size() - 1; i++) {
            for (int j = i + 1; j < points.size(); j++) {
                if (calcDistanceBetweenPair(points.get(i), points.get(j)) < distance) {
                    distance = calcDistanceBetweenPair(points.get(i), points.get(j));
                    result = new ArrayList<>();
                    result.add(points.get(i));
                    result.add(points.get(i + 1));
                }
            }
        }
        return result;
    }

    private static double findMid(List<Point> points) {
        switch (points.size()) {
            case 0 -> {
                return 0;
            }
            case 1 -> {
                return points.get(0).x - 1;
            }
            case 2 -> {
                return (points.get(1).x - points.get(0).y) / 2;
            }
            default -> {
                int midIndex = points.size() / 2;
                if (points.size() % 2 != 0) {
                    //TODO
                    if (points.get(midIndex - 1).x != points.get(midIndex).x
                            && points.get(midIndex + 1).x != points.get(midIndex).x) {
                        //return points.get(midIndex);
                    }
                } else {
                    if (points.get(midIndex - 1).x != points.get(midIndex).x) {
                        return (points.get(midIndex).x - points.get(midIndex - 1).x) / 2;
                    } else {
                        if (points.get(0) == points.get(points.size() - 1)) {
                            return points.get(0).x - 1;
                        }
                        if (points.get(0).x == points.get(midIndex).x) {
                            return (points.stream()
                                    .filter(el -> Double.compare(el.x, points.get(midIndex).x) != 0)
                                    .findFirst()
                                    .orElseGet(Point::new).x - points.get(midIndex).x) / 2;
                        }
                    }
                    //TODO
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(calcDistanceBetweenPair(new Point(3, 2), new Point(2, 3)));
    }
}
