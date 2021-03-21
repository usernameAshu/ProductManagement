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

package labs.pm.app;

import labs.pm.data.Drink;
import labs.pm.data.Food;
import labs.pm.data.Product;
import labs.pm.data.Rating;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;

/**
 * @author MOHANTY
 */
public class Shop {

    public static final String COUNTRY_TIME_ZONE = "IST";
    public static final LocalDate LOCAL_DATE = LocalDate
            .now(ZoneId.of(ZoneId.SHORT_IDS.get(COUNTRY_TIME_ZONE)))
            .plusMonths(6L);

    public static void main(String[] args) {
        Product product1 = new Food(1, "Coffee", BigDecimal.valueOf(15), LOCAL_DATE);
        Product product2 = new Drink(2, "Cookie", BigDecimal.valueOf(50), Rating.FOUR_STAR);
        Product product3 = new Food(3, "Ice-tea", BigDecimal.valueOf(30),LOCAL_DATE);

        Product product3_1 = product3.applyRating(Rating.TWO_STAR);
        Product product1_1 = product1.applyRating(Rating.ONE_STAR);

        /*System.out.println("id"+"\t"+"name"+"\t"+"Ini-Prc"+"\t"+"Final-Prc"+"\t"+"Disc"+"\t"+"tax"+"\t"+"\t"
                +"ratings"+"\t\t"+"caution");
        displayProduct(product1);
        displayProduct(product2);
        displayProduct(product3);
        displayProduct(product4);
        displayProduct(product3_1);
        displayProduct(product1_1);
*/
        LocalDate bestBefore = LocalDate
                .now(ZoneId.of(ZoneId.SHORT_IDS.get(COUNTRY_TIME_ZONE)))
                .plusMonths(6L);
        Product food1 = new Food(1, "Rice", BigDecimal.valueOf(20.50), bestBefore);
        Product food2 = new Food(1, "Rice", BigDecimal.valueOf(20.50), bestBefore);
        Product drink = new Drink(1, "Mojito", BigDecimal.valueOf(120.50), Rating.FOUR_STAR);
        boolean foodTest = Objects.equals(food1, food2); //true
        drink.toString(); //Drink: 1 Mojito 120.5 FOUR_STAR NOT_AVAILABLE}
    }

}
