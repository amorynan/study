package com.company.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Point {
    class Point3 {
        int x;
        int y;
        int z;
        Point3(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static void main(String[] args) throws IOException {

        }

        private static int maxPoints(Point3[] points) {
            if (points.length <= 2) {
                return points.length;
            }

            Map<Integer, Map<Integer, Map<Integer, Integer>>> map = new HashMap<>();
            int res = 0;
            for (int i = 0; i < points.length; i++) {
                int overlap = 0, count = 0;
                for (int j = i + 1; j < points.length; j++) {
                    int dx = points[i].x - points[j].x;
                    int dy = points[i].y - points[j].y;
                    int dz = points[i].z - points[j].z;
                    if (dx == 0 && dy == 0 && dz == 0) {
                        overlap++;
                        continue;
                    }

                    int gcd = gcd(dx, dy, dz);
                    dx /= gcd;
                    dy /= gcd;
                    dz /= gcd;

                    if (map.containsKey(dx)) {
                        if (map.get(dx).containsKey(dy)) {
                            if (map.get(dx).get(dy).containsKey(dz)) {
                                map.get(dx).get(dy).put(dz, map.get(dx).get(dy).get(dz) + 1);
                            } else {
                                map.get(dx).get(dy).put(dz, 1);
                            }
                        } else {
                            Map<Integer, Integer> m = new HashMap<>();
                            m.put(dz, 1);
                            map.get(dx).put(dy, m);
                        }
                    } else {
                        Map<Integer, Integer> m1 = new HashMap<>();
                        m1.put(dz, 1);
                        Map<Integer, Map<Integer, Integer>> m2 = new HashMap<>();
                        m2.put(dy, m1);
                        map.put(dx, m2);
                    }
                    count = Math.max(count, map.get(dx).get(dy).get(dz));
                }
                res = Math.max(res, count + 1 + overlap);
                map.clear();
            }
            return res;
        }

        private static int gcd(int a, int b, int c) {
            return gcd(a, gcd(b, c));
        }

        private static int gcd(int a, int b) {
            return b == 0 ? a : gcd(b, a % b);
        }
}

