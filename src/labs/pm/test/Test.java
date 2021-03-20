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

package labs.pm.test;

import labs.pm.data.Food;
import labs.pm.data.Product;

import java.math.BigDecimal;

public class Test {
    public static void main(String[] args) {
        /**
         * Testing Object class method: toString()
         */
        Product food = new Food(1, "Burger", BigDecimal.valueOf(20.50));
        String msg = food.toString();
        //System.out.println(msg);

        /**
         * Testing Object class method: equals()
         */
        Product food1 = new Food(1, "Cookie", BigDecimal.valueOf(10));
        Product food2 = new Food(1, "Cookie", BigDecimal.valueOf(10));
        /*System.out.println("food1 == food2 :" + (food1 == food2));
        System.out.println("food1.equals(food2) :" + food1.equals(food2));*/

        /**
         * String Pool
         */
        String str1 = "Ashutosh";
        String str2 = "Ashutosh";
        //        System.out.println("str1 == str2  :"+ (str1 == str2));
        //        System.out.println("str1.equals(str2)  :"+str1.equals(str2));

        /**
         * String Object
         */
        String obj1 = new String("Ashutosh");
        String obj2 = new String("Ashutosh");
        //        System.out.println("obj1 == obj2  :"+ (obj1 == obj2));
        //        System.out.println("obj1.equals(obj2)  :"+obj1.equals(obj2));

        /**
         * Object class method: hashCode()
         */
        int hash1 = obj1.hashCode();
        int hash2 = obj2.hashCode();
        System.out.println((hash1 == hash2) + "\n" + hash1 + " " + hash2);
    }
}
