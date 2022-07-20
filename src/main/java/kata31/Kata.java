package kata31;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Kata {

    public static List<Point> closestPair(List<Point> points) {
        points.sort(Comparator.comparingDouble(o -> o.x));
        List<Point> sortedY = new ArrayList<>(points);
        sortedY.sort(Comparator.comparingDouble(o -> o.y));
        return findClosest(points, sortedY);
    }

    private static List<Point> findClosest(List<Point> sortedX, List<Point> sortedY) {
        if (sortedX.size() <= 3) {
            return findPairWithMinDistance(sortedX);
        }

        int mid = sortedX.size() / 2;
        Point midPoint = sortedX.get(mid);

        List<Point> leftPartX = new ArrayList<>();
        List<Point> rightPartX = new ArrayList<>();

        for (Point point : sortedX) {
            if (point.x < midPoint.x || point.x == midPoint.x && point.y < midPoint.y) leftPartX.add(point);
            else rightPartX.add(point);
        }

        List<Point> leftPartY = new ArrayList<>(leftPartX);
        leftPartY.sort(Comparator.comparingDouble(o -> o.y));
        List<Point> rightPartY = new ArrayList<>(rightPartX);
        rightPartY.sort(Comparator.comparingDouble(o -> o.y));

        List<Point> closestLeft = findClosest(leftPartX, leftPartY);
        List<Point> closestRight = findClosest(rightPartX, rightPartY);
        List<Point> minBetweenLR = calcDistanceBetweenPair(closestLeft) - calcDistanceBetweenPair(closestRight)
                > 0 ? closestRight : closestLeft;
        double minDistanceLR = calcDistanceBetweenPair(minBetweenLR);

        List<Point> inMinRange = new ArrayList<>();
        for (Point point : sortedY) {
            if (Math.abs(point.x - midPoint.x) < minDistanceLR) inMinRange.add(point);
        }

        List<Point> closestInMid = findClosestInMid(inMinRange, minDistanceLR);
        if (closestInMid.size() == 0) return minBetweenLR;
        return Double.compare(calcDistanceBetweenPair(closestInMid), minDistanceLR) > 0
                ? minBetweenLR : closestInMid;
    }

    private static double calcDistanceBetweenPair(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.y - p1.y, 2) + Math.pow(p1.x - p2.x, 2));
    }

    private static double calcDistanceBetweenPair(List<Point> points) {
        return calcDistanceBetweenPair(points.get(0), points.get(1));
    }

    private static List<Point> findPairWithMinDistance(List<Point> points) {
        double distance = Double.MAX_VALUE;
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

    private static List<Point> findClosestInMid(List<Point> midPoints, double minLR) {
        List<Point> closestPoints = new ArrayList<>();
        for (int i = 0; i < midPoints.size(); ++i)
            for (int j = i + 1; j < midPoints.size() && (midPoints.get(j).y - midPoints.get(i).y) < minLR; ++j)
                if (calcDistanceBetweenPair(midPoints.get(i), midPoints.get(j)) < minLR) {
                    minLR = calcDistanceBetweenPair(midPoints.get(i), midPoints.get(j));
                    closestPoints = new ArrayList<>();
                    closestPoints.add(midPoints.get(i));
                    closestPoints.add(midPoints.get(j));
                }
        return closestPoints;
    }
}
