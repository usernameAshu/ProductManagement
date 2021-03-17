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

import labs.pm.data.Product;
import labs.pm.data.Rating;
import labs.pm.util.Condition;

import java.math.BigDecimal;

/**
 * @author MOHANTY
 */
public class Shop {

    public static void main(String[] args) {
        Product product1 = new Product(1, "Tea  ", BigDecimal.valueOf(16), BigDecimal.valueOf(0.05), Condition.HOT,
                Rating.THREE_STAR);

        Product product2 = new Product(2, "Coffee");

        Product product3 = new Product();

        System.out.println("id"+"\t"+"name"+"\t"+"Ini-Prc"+"\t"+"Final-Prc"+"\t"+"Disc"+"\t"+"tax"+"\t"+"\t"+"caution"+"\t"+"ratings");
        displayProduct(product1);
        displayProduct(product2);
        displayProduct(product3);
    }

    private static void displayProduct(Product product1) {
        System.out.println(
                product1.getId() + "\t" + product1.getName() + "\t" + product1.getPrice() + "\t"
                        + product1.getTotalPrice() + "\t" + product1.getDiscountAmount() + "\t" + product1.getTaxAmount() +"\t"
                        + product1.getCondition().getCaution() + "\t" + product1.getRating().getStars());
    }
}
