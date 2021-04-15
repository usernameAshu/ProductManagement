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

package labs.pm.data;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class ProductManager {

    private Locale locale;
    private ResourceBundle resources;
    private DateTimeFormatter dateFormat;
    private NumberFormat moneyFormat;

    private Map<Product, List<Review>> products = new HashMap<>();

    public ProductManager(Locale locale) {
        this.locale = locale;
        this.resources = ResourceBundle.getBundle("labs.pm.data.resources", locale);
        this.dateFormat = DateTimeFormatter
                .ofLocalizedDate(FormatStyle.SHORT)
                .localizedBy(locale);
        this.moneyFormat = NumberFormat.getCurrencyInstance(locale);
    }

    public Product createProduct(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore) {
        Product product = new Food(id, name, price, bestBefore, rating);
        products.putIfAbsent(product, new ArrayList<>());
        return product;
    }

    public Product createProduct(int id, String name, BigDecimal price, Rating rating) {
        Product product = new Drink(id, name, price, rating);
        products.putIfAbsent(product, new ArrayList<>());
        return product;
    }

    public Product reviewProduct(Product product, Rating rating, String comment) {

        List<Review> reviews = products.get(product);
        products.remove(product, reviews);
        reviews.add(new Review(rating, comment));

        int sum = 0;
        for (Review review : reviews) {
            sum += review
                    .getRating()
                    .ordinal();
        }

        Rating avgRating = Rateable.convert(Math.round((float)sum / reviews.size()));
        product = product.applyRating(avgRating);
        products.put(product, reviews);
        return product;
    }

    public void printProductReport(Product product) {
        List<Review> reviews = products.get(product);
        StringBuilder prodtxt = new StringBuilder();
        StringBuilder reviewtxt = new StringBuilder();

        prodtxt.append(MessageFormat.format(resources.getString("product"), product.getName(),
                moneyFormat.format(product.getPrice()), product
                        .getRating()
                        .getStars(), dateFormat.format(product.getBestBefore())));

        for (Review eachReview : reviews) {
            reviewtxt.append(MessageFormat.format(resources.getString("review"), eachReview
                    .getRating()
                    .getStars(), eachReview.getComment()));
            reviewtxt.append("\n");
        }
        if (reviews.isEmpty()) {
            prodtxt.append("\n");
            prodtxt.append(resources.getString("no.reviews"));
        }
        System.out.println(prodtxt);
        System.out.println(reviewtxt);
    }
}
