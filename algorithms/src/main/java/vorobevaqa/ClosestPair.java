package vorobevaqa;

import java.util.ArrayList;
import java.util.List;

class Point {

    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class ClosestPair {

    // Функция вычисления расстояния между двумя точками
    private static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    // Функция для поиска ближайшей пары точек
    private static double closestPair(List<Point> points) {
        // Сортируем точки по координате x
        points.sort((p1, p2) -> Integer.compare(p1.x, p2.x));

        return closestPairRecursive(points, 0, points.size() - 1);
    }

    // Рекурсивная функция для поиска ближайшей пары
    private static double closestPairRecursive(List<Point> points, int left, int right) {
        // Если точек мало, используем bruteForce
        if (right - left <= 3) {
            return bruteForce(points, left, right);
        }

        int mid = (left + right) / 2;
        Point midPoint = points.get(mid);

        // Рекурсивно находим минимальное расстояние в левой и правой частях
        double dLift = closestPairRecursive(points, left, mid);
        double dRight = closestPairRecursive(points, mid + 1, right);
        double d = Math.min(dLift, dRight);

        // Создаём список точек, которые находятся на расстоянии меньше d от средней линии
        List<Point> strip = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (Math.abs(points.get(i).x - midPoint.x) < d) {
                strip.add(points.get(i));
            }
        }
        // Находим минимальное расстояние в полосе
        return Math.min(d, stripClosest(strip, d));
    }

    // Brute-force метод для поиска минимального расстояния между точками
    private static double bruteForce(List<Point> points, int left, int right) {
        double minDist = Double.POSITIVE_INFINITY;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                double dist = distance(points.get(i), points.get(j));
                if (dist < minDist) {
                    minDist = dist;
                }
            }
        }
        return minDist;
    }

    // Метод для поиска минимального расстояния в полосе
    private static double stripClosest(List<Point> strip, double d) {
        double minDist = d;
        // Сортируем точки по координате y
        strip.sort((p1, p2) -> Integer.compare(p1.x, p2.x));

        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < minDist; j++) {
                double dist = distance(strip.get(i), strip.get(j));
                if (dist < minDist) {
                    minDist = dist;
                }
            }
        }
        return minDist;
    }
}