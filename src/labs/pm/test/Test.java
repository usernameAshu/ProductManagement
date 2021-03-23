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

import labs.pm.data.Drink;
import labs.pm.data.Food;
import labs.pm.data.Product;
import labs.pm.data.ProductManager;
import labs.pm.data.Rating;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;

public class Test {

    public static final String countryTimeZone = "IST";

    public static void main(String[] args) throws CloneNotSupportedException {

        LocalDate bestBefore = LocalDate
                .now(ZoneId.of(ZoneId.SHORT_IDS.get(countryTimeZone)))
                .plusMonths(6L);
        /**
         * Testing Object class method: toString()
         */
        Product food = ProductManager.createProduct(1, "Cookie", BigDecimal.valueOf(10), Rating.FIVE_STAR);
        String msg = food.toString();
        //System.out.println(msg);

        /**
         * Testing Object class method: equals()
         */
        Product food1 = ProductManager.createProduct(1, "Cookie", BigDecimal.valueOf(10), Rating.FIVE_STAR);
        Product food2 = ProductManager.createProduct(1, "Cookie", BigDecimal.valueOf(10), Rating.FIVE_STAR);
        /*System.out.println("food1 == food2 :" + (food1 == food2));
        System.out.println("food1.equals(food2) :" + food1.equals(food2));*/

        /**
         * String Pool  String Object
         */
        String str1 = "Ashutosh";
        String str2 = "Ashutosh";
        //        System.out.println("str1 == str2  :"+ (str1 == str2));
        //        System.out.println("str1.equals(str2)  :"+str1.equals(str2));

        String obj1 = new String("Ashutosh");
        String obj2 = new String("Ashutosh");
        //        System.out.println("obj1 == obj2  :"+ (obj1 == obj2));
        //        System.out.println("obj1.equals(obj2)  :"+obj1.equals(obj2));
        boolean strObj = str1 == obj1; //false
        boolean strObjVal = str1.equals(obj1); //true

        /**
         * Object class method: hashCode()
         */
        int hash1 = obj1.hashCode();
        int hash2 = obj2.hashCode();
        //System.out.println((hash1 == hash2) + "\n" + hash1 + " " + hash2);

        Car carName = CarFactory.produceCar("Tata");
        String name = carName.carName(); //Tata Hexa

        Comparator<Drink> drinkComparator = (d1, d2) -> d1
                .getName()
                .compareTo(d2.getName());
        Food bigMac = (Food)ProductManager.createProduct(1, "Mac burger", BigDecimal.valueOf(450), Rating.FIVE_STAR,
                bestBefore);
        Food bigMacFree = (Food)bigMac.clone();
        boolean b = bigMac == bigMacFree;
        boolean equals = bigMac.equals(bigMacFree);
    }
}

class AppTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Car car = CarFactory.produceCar("Nissan");
        Nissan nissanCar = (Nissan)car;
        Nissan nissanClone = (Nissan)nissanCar.clone();

        car = CarFactory.produceCar("Tata");
        Tata tataCar = (Tata)car;
        Tata tataClone = (Tata)tataCar.clone();

        nissanCar.toString();
        nissanClone.toString();
        Rating.values();
    }
}

abstract class Car implements Cloneable{
    public abstract String carName();

    public abstract int topSpeed();

    protected abstract int carMakingCost();

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

/**
 * Factory Design Pattern
 */
class CarFactory {
    public static Car produceCar(String carBrand) {
        switch (carBrand) {
            case "Nissan":
                return new Nissan();
            case "Tata":
                return new Tata();
            default:
                return null;
        }
    }
}

class Nissan extends Car {
    @Override
    public String carName() {
        return "Nissan Micra";
    }

    @Override
    public int topSpeed() {
        return 100;
    }

    @Override
    protected int carMakingCost() {
        return 100;
    }

    public String countryOfOrigin() {
        return "Japan";
    }

    /*@Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }*/
}

class Tata extends Car implements Vehicle {
    @Override
    public String carName() {
        return "Tata Hexa";
    }

    @Override
    public int topSpeed() {
        return 200;
    }

    @Override
    protected int carMakingCost() {
        return 50;
    }

    @Override
    public boolean hasWheels() {
        return false;
    }

    @Override
    public boolean is4wheelDrive() {
        return false;
    }

    String founderName() {
        return "JK Tata";
    }

    /*@Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }*/
}