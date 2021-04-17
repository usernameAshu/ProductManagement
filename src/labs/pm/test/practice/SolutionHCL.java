/*
 * Copyright 2021, Ashutosh Mohanty
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
 * to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of
 * the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 * THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

package labs.pm.test.practice;

import java.util.Scanner;
import java.util.stream.Stream;

public class SolutionHCL {
    private static int i, j, arrIndex;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine());
        String[] firstLine = new String[t];
        String[] secondLine = new String[t];
        int index = 0;
        while (t > 0) {
            firstLine[index] = sc.nextLine();
            secondLine[index] = sc.nextLine();
            index++;
            t--;
        }
        t = firstLine.length;
        index = 0;
        while (t > 0) {
            String lengthAndSize = firstLine[index];
            String s = secondLine[index];
            boolean vaildationFlag = false;

            int[] s1 = Stream
                    .of(lengthAndSize.split(" "))
                    .mapToInt(str -> Integer.parseInt(str))
                    .toArray();

            int n = s1[0];
            int h = s1[1];

            if (t >= 1 && t <= 1000) {
                if ((n >= 1 && n <= 100) && (n == s.length())) {
                    if (h >= 1 && h <= 100) {
                        vaildationFlag = true;
                    }
                }
            }

            if (!vaildationFlag) {
                return;
            }

            /**
             * t
             * n h
             * s
             */
            //create a square matrix of n
            char[][] mat = new char[h][h];
            char[] chars = s.toCharArray();
            for (int i1 = 0; i1 < h; i1++) {
                for (int j1 = 0; j1 < h; j1++) {
                    mat[i1][j1] = ' ';
                }
            }

            //first face
            i = h - 1;
            j = 0;
            arrIndex = 0;
            while (i >= 0) {
                insertData(s, mat, chars);
                i--;
            }
            //second face
            i = 0;
            j = 0;
            while (j < h) {
                insertData(s, mat, chars);
                j++;
            }
            //3rd face
            i = 0;
            j = h - 1;
            while (i < h) {
                insertData(s, mat, chars);
                i++;
            }
            //4th face
            i = h - 1;
            j = h - 1;
            while (j >= 0) {
                insertData(s, mat, chars);
                j--;
            }

            for (int i1 = 0; i1 < h; i1++) {
                for (int j1 = 0; j1 < h; j1++) {
                    System.out.print(mat[i1][j1]);
                    System.out.print(' ');
                }
                System.out.println();
            }
            t--;
            index++;
        }
    }

    private static void insertData(String s, char[][] mat, char[] chars) {
        if (arrIndex == s.length()) {
            arrIndex = 0;
        }
        if (mat[i][j] == ' ') {
            mat[i][j] = chars[arrIndex];
            arrIndex++;
        }
    }

}