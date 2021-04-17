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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class solutionSOCGEN {

    public static void main(String[] args) {

        List<Integer> possibleClassList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        //number of test cases
        int t = Integer.parseInt(sc.nextLine());
        if (t >= 1 && t <= 5000) {
            while (t > 0) {
                //number of classes
                int n = Integer.parseInt(sc.nextLine());
                List<String> subjectTimes = new ArrayList<>();
                if (n >= 2 && n <= 100) {
                    while (n > 0) {
                        String input = sc.nextLine();
                        subjectTimes.add(input);
                        n--;
                    }
                    /*
                     * { { 10:20, 11:30}, {12:30 ,14:30  }, {13:30 ,16:30  } }
                     * {  {13:30 ,16:30  } , { 10:20, 11:30}, {12:30 ,14:30  }, {09:00, 10:00 } }
                     */
                    List<ClassTimes> classTimesList = new ArrayList<>();
                    subjectTimes
                            .stream()
                            .forEach(s -> {
                                String[] strings = s.split(" ");
                                Date startDate = null, endDate = null;
                                try {
                                    startDate = new SimpleDateFormat("HH:mm").parse(strings[1]);
                                    endDate = new SimpleDateFormat("HH:mm").parse(strings[2]);
                                } catch (ParseException ex) {
                                    ex.printStackTrace();
                                }
                                ClassTimes classTimes = new ClassTimes(startDate, endDate);
                                classTimesList.add(classTimes);
                            });

                    possibleClassList.add(findNumberOfPossibleClass(classTimesList));
                    t--;
                }
            }
            possibleClassList
                    .stream()
                    .forEach(possible -> System.out.println(possible));
        }
    }

    public static int findNumberOfPossibleClass(List<ClassTimes> classTimesList) {

        // sort the schedule in ascending order of start time
        Collections.sort(classTimesList);

        // remove the conflicts
        for (int i = 0; i < classTimesList.size(); i++) {
            if (classTimesList.get(i).startTime.compareTo(classTimesList.get(i).endTime) < 0) {
                if (i > 0 && classTimesList.get(i - 1).endTime.compareTo(classTimesList.get(i).startTime) > 0) {
                    classTimesList.remove(i);
                    i--;
                }
            }
        }
        return classTimesList.size();
    }
}

// Class Schedules
class ClassTimes implements Comparable<ClassTimes> {
    Date startTime;
    Date endTime;

    public ClassTimes(Date startTime, Date endTime) {
        super();
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public int compareTo(ClassTimes other) {
        if (this.startTime.getHours() == other.startTime.getHours())
            return 0;
        if (this.startTime.getHours() < other.startTime.getHours())
            return -1;
        return 1;
    }

    @Override
    public String toString() {
        return this.startTime + "->" + this.endTime;
    }
}
