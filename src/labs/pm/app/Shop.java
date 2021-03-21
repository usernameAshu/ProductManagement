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
import labs.pm.data.ProductManager;
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
    public static final LocalDate BEST_BEFORE = LocalDate
            .now(ZoneId.of(ZoneId.SHORT_IDS.get(COUNTRY_TIME_ZONE)))
            .plusMonths(6L);

    public static void main(String[] args) {
        Product coffeeDrink = ProductManager.createProduct(1, "Coffee", BigDecimal.valueOf(15), Rating.FIVE_STAR);
        Product cookieFood =
                ProductManager.createProduct(2, "Cookie", BigDecimal.valueOf(50), Rating.FOUR_STAR, BEST_BEFORE);
        Product iceTea = ProductManager.createProduct(3, "Ice-tea", BigDecimal.valueOf(30), Rating.THREE_STAR);

        Product iceTeaRating = iceTea.applyRating(Rating.TWO_STAR);
        Product cookieFoodRating = cookieFood.applyRating(Rating.ONE_STAR);

        LocalDate bestBefore = LocalDate
                .now(ZoneId.of(ZoneId.SHORT_IDS.get(COUNTRY_TIME_ZONE)))
                .plusMonths(6L);
        Product rice1 =
                ProductManager.createProduct(1, "Rice", BigDecimal.valueOf(20.50), Rating.FIVE_STAR, BEST_BEFORE);
        Product rice2 =
                ProductManager.createProduct(1, "Rice", BigDecimal.valueOf(20.50), Rating.FIVE_STAR, BEST_BEFORE);
        Product mojito = ProductManager.createProduct(1, "Mojito", BigDecimal.valueOf(120.50), Rating.FOUR_STAR);
        boolean foodTest = Objects.equals(rice1, rice2); //true
        mojito.toString();
        Product mojitoRating = mojito.applyRating(Rating.TWO_STAR);
        mojitoRating.toString();

        coffeeDrink.toString();
        cookieFood.toString();
        cookieFoodRating.toString();
        iceTea.toString();
        iceTeaRating.toString();
    }

}
