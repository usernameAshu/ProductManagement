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
import java.util.Comparator;
import java.util.function.Predicate;

/**
 * @author MOHANTY
 */
public class Shop {

    public static final String COUNTRY_TIME_ZONE = "IST";
    public static final LocalDate BEST_BEFORE = LocalDate
            .now(ZoneId.of(ZoneId.SHORT_IDS.get(COUNTRY_TIME_ZONE)))
            .plusMonths(6L);

    public static void main(String[] args) {

        ProductManager productManager = new ProductManager("en-US");

        /*Product coffeeDrink = productManager.createProduct(1, "Coffee", BigDecimal.valueOf(15), Rating.ONE_STAR);
        productManager.printProductReport(coffeeDrink);
        productManager.reviewProduct(coffeeDrink,Rating.FOUR_STAR,"Nice cup of Coffee");
        productManager.reviewProduct(coffeeDrink,Rating.ONE_STAR,"Coffee was cold");
        productManager.reviewProduct(coffeeDrink,Rating.TWO_STAR,"Such a nice cuppa");
        productManager.reviewProduct(coffeeDrink,Rating.FOUR_STAR,"Such a nice cuppa");
        productManager.reviewProduct(coffeeDrink,Rating.ONE_STAR,"Such a nice cuppa");
        productManager.reviewProduct(coffeeDrink,Rating.FOUR_STAR,"Such a nice cuppa");
        productManager.reviewProduct(coffeeDrink,Rating.THREE_STAR,"Such a nice cuppa");
        productManager.reviewProduct(coffeeDrink,Rating.ONE_STAR,"Such a nice cuppa");
        productManager.printProductReport(coffeeDrink);*/


        productManager.createProduct(1, "Coffee", BigDecimal.valueOf(15), Rating.ONE_STAR);
        productManager.reviewProduct(1, Rating.FOUR_STAR, "Nice cup of Coffee");
        productManager.reviewProduct(1, Rating.FIVE_STAR, "Coffee was cold");
        productManager.reviewProduct(1, Rating.FIVE_STAR, "Such a nice cuppa");
        productManager.reviewProduct(1, Rating.FOUR_STAR, "Such a nice cuppa");
        productManager.reviewProduct(40, Rating.FIVE_STAR, "Such a nice cuppa");
        productManager.reviewProduct(1, Rating.FOUR_STAR, "Such a nice cuppa");
        productManager.reviewProduct(1, Rating.FIVE_STAR, "Such a nice cuppa");
        productManager.reviewProduct(1, Rating.FIVE_STAR, "Such a nice cuppa");
//        productManager.printProductReport(1);

//        productManager.changeLocale("fr-FR");

        Product cookieFood = productManager.createProduct(2, "Cookie", BigDecimal.valueOf(50), Rating.FOUR_STAR, BEST_BEFORE);
        productManager.reviewProduct(cookieFood, Rating.FOUR_STAR, "Nice cup of Coffee");
        productManager.reviewProduct(cookieFood, Rating.ONE_STAR, "Coffee was cold");
        productManager.reviewProduct(cookieFood, Rating.TWO_STAR, "Such a nice cuppa");
        productManager.reviewProduct(cookieFood, Rating.FOUR_STAR, "Such a nice cuppa");
        productManager.reviewProduct(cookieFood, Rating.FIVE_STAR, "Such a nice cuppa");
//        productManager.printProductReport(cookieFood);

        Product iceTea = productManager.createProduct(3, "Ice-tea", BigDecimal.valueOf(30), Rating.THREE_STAR);
        productManager.reviewProduct(iceTea, Rating.ONE_STAR, "Nice cup of Coffee");
        productManager.reviewProduct(iceTea, Rating.ONE_STAR, "Coffee was cold");
        productManager.reviewProduct(iceTea, Rating.FIVE_STAR, "Such a nice cuppa");
        productManager.reviewProduct(iceTea, Rating.ONE_STAR, "Such a nice cuppa");
//        productManager.printProductReport(iceTea);


        Product kitKatShake = productManager.createProduct(4, "KitKat Shake", BigDecimal.valueOf(30), Rating.THREE_STAR);
        productManager.reviewProduct(4, Rating.FIVE_STAR, "Nice cup of Shake");
        productManager.reviewProduct(4, Rating.FIVE_STAR, "Shake was cold");
        productManager.reviewProduct(4, Rating.FIVE_STAR, "Such a nice Shake");
        productManager.reviewProduct(99, Rating.ONE_STAR, "Such a nice Shake");
//        productManager.printProductReport(kitKatShake);

        //List products according to ratings
        Comparator<Product> ratingCompare = (p1, p2) -> p2.getRating().ordinal() - p1.getRating().ordinal();

        //List products according to Price
        Comparator<Product> priceCompare = (p1, p2) -> p2.getPrice().compareTo(p1.getPrice());

        Predicate<Product> priceFilter = p1 -> p1.getPrice().compareTo(BigDecimal.valueOf(100))<0;
        //Double comparison
        productManager.printProducts(priceFilter,priceCompare.thenComparing(ratingCompare));
        productManager.changeLocale("fr-FR");
        productManager.printProducts(priceFilter,ratingCompare.thenComparing(priceCompare).reversed());

        productManager.getDiscounts().forEach((product,discounts) -> System.out.println(product +" : " +discounts));
//
//        Product iceTeaRating = iceTea.applyRating(Rating.TWO_STAR);
//        Product cookieFoodRating = cookieFood.applyRating(Rating.ONE_STAR);
//
//        Product rice1 =
//                productManager.createProduct(1, "Rice", BigDecimal.valueOf(20.50), Rating.FIVE_STAR, BEST_BEFORE);
//        Product rice2 =
//                productManager.createProduct(1, "Rice", BigDecimal.valueOf(20.50), Rating.FIVE_STAR, BEST_BEFORE);
//        Product mojito = productManager.createProduct(1, "Mojito", BigDecimal.valueOf(120.50), Rating.FOUR_STAR);
//
//        boolean foodTest = Objects.equals(rice1, rice2); //true
//        mojito.toString();
//        Product mojitoRating = mojito.applyRating(Rating.TWO_STAR);

    }

}