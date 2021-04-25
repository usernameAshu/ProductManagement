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

package labs.pm.test.practice.exam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

abstract class Top {
    {
        System.out.print("A");
    }

    public Top() {
        System.out.print("B");
    }

    public Top(String s) {
        System.out.print("C");
    }
}

class Middle extends Top {

    {
        System.out.print("D");
    }

    public Middle() {
        System.out.print("E");
    }

    public Middle(String s) {
        System.out.print("F");
    }

}

public class Bottom extends Middle {

    {
        System.out.print("G");
    }

    public Bottom() {
        System.out.print("H");
    }

    public Bottom(String s) {
        System.out.print("I");
    }

    public static void main(String[] args) {
        new Bottom();
        double d = 0_12.90;
        System.out.println("\n" + d);
    }

}

class Parent {
    Parent() {
        System.out.println("P");
    }
}

class Child extends Parent {
    Child() {
        System.out.println("C");
    }
}

class Kid {
    Kid() {
        System.out.println("K");
    }

    public static void main(String[] args) {
        new Child();

        Stream<String> mini = Stream.of("mini", "louie");
        Optional<String> louie = mini
                .filter(n -> n.equals("louie"))
                .findFirst();

        louie.orElse("null");

        Map<Integer, String> map = new HashMap<>();
        map.forEach((k, v) -> System.out.println(k + ":" + v));

        Button b = new Button("Click me");
        b.onClick(() -> "You click on me!");
        System.out.println(LocalDateTime.now());
    }
}

class Button {
    String title;

    public Button(String title) {
        this.title = title;
    }

    public void onClick(Supplier s) {
        System.out.println(s.get());
        Math.random();

    }

}

class Change {
    public static void changeIt(int num, String str2, StringBuilder sbr) {
        num = 5;
        str2 += "b";
        sbr.append("d");
    }

    public static void main(String[] args) {
        Boolean a = Boolean.valueOf(true);
        int num = 1;
        String str = "a";
        StringBuilder sbr = new StringBuilder("c");
        changeIt(num, str, sbr);
        System.out.println("num="+num);
        System.out.println("str="+str);
        System.out.println("builder="+sbr+"*");
        
    }
}

class Switch2 {
    final static short x = 2;
    final static int y = 0;

    public static void main(String[] args) {
        for(int z=0;z<4;z++) {
            switch(z) {
                case x:
                    System.out.print("0 ");
                case x-1:
                    System.out.print("1 ");
                    break;
                default:
                    System.out.print("def ");
                case x-2:
                    System.out.print("2 ");
            }
        }
    }
}

class Sorter {
    public static void main(String[] args) {
        ArrayList<String> sa = new ArrayList<>();
        sa.add("a"); sa.add("b"); sa.add("c");
        String[]  sb = new String[3];
        for(int z=1; z<sa.size();z++)
            sb[z] = sa.get(z);

        for(String str:sb)
            System.out.print(str+" ");
    }
}