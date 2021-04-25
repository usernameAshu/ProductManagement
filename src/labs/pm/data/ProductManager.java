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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProductManager {

    private Map<Product, List<Review>> products = new HashMap<>();
    private static Map<String, ResourceFormatter> formatters =
            Map.of("en-GB", new ResourceFormatter(Locale.UK), "en-US", new ResourceFormatter(Locale.US), "fr-FR",
                    new ResourceFormatter(Locale.FRANCE), "ru-RU", new ResourceFormatter(new Locale("ru", "RU")),
                    "zh-CN", new ResourceFormatter(Locale.CHINESE));

    private ResourceFormatter formatter;

    public ProductManager(Locale locale) {
        this(locale.toLanguageTag());
    }

    public ProductManager(String languageTag) {
        changeLocale(languageTag);
    }

    public void changeLocale(String languageTag) {
        formatter = formatters.getOrDefault(languageTag, formatters.get("en-GB"));
    }

    public Set<String> getSupportedLocales() {
        return formatters.keySet();
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

    public Product findProduct(int id) {
        //        Product result = null;
        //        for (Product product : products.keySet()) {
        //            if (product.getId() == id) {
        //                result = product;
        //                break;
        //            }
        //        }
        //        return result;
        return products
                .keySet()
                .stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseGet(() -> null);

    }

    public Product reviewProduct(int id, Rating rating, String comment) {
        return reviewProduct(findProduct(id), rating, comment);
    }

    public Product reviewProduct(Product product, Rating rating, String comment) {

        List<Review> reviews = products.get(product);
        products.remove(product, reviews);
        reviews.add(new Review(rating, comment));

        //        int sum = 0;
        //        for (Review review : reviews) {
        //            sum += review
        //                    .getRating()
        //                    .ordinal();
        //        }
        //        Rating avgRating = Rateable.convert(Math.round((float)sum / reviews.size()));
        //        product = product.applyRating(avgRating);

        product = product.applyRating(
                Rateable.convert(
                        (int) Math.round(
                                reviews.stream()
                                        .mapToInt(review -> review.getRating().ordinal())
                                        .average()
                                        .orElse(0))));

        products.put(product, reviews);
        return product;
    }

    public void printProductReport(int id) {
        printProductReport(findProduct(id));
    }

    public void printProductReport(Product product) {
        List<Review> reviews = products.get(product);
        StringBuilder txt = new StringBuilder();

        txt.append(formatter.formatProduct(product) + "\n");
        Collections.sort(reviews);
//        for (Review review : reviews) {
//            txt.append(formatter.formatReviews(review));
//            txt.append("\n");
//        }
//        if (reviews.isEmpty()) {
//            txt.append(formatter.getText("no.reviews"));
//            txt.append("\n");
//        }
        if (reviews.isEmpty()) {
            txt.append(formatter.getText("no.reviews") + "\n");
        } else {
            txt.append(
            reviews.stream()
                    .map(review -> formatter.formatReviews(review) +"\n")
                    .collect(Collectors.joining()));
        }
        System.out.println(txt);

    }

    public void printProducts(Predicate<Product> filter, Comparator<Product> sorter) {
//        List<Product> productList = new ArrayList<>(products.keySet());
//        productList.sort(sorter);
        StringBuilder txt = new StringBuilder();
        products.keySet()
                .stream()
                .sorted(sorter)
                .filter(filter)
                .forEach(product -> {
                    txt.append(formatter.formatProduct(product) + "\n");
                });
        System.out.println(txt);
    }

    public Map<String,String> getDiscounts() {
        return  products.keySet()
                .stream()
                .collect(
                        Collectors.groupingBy(
                                product -> product.getRating().getStars(),
                                Collectors.collectingAndThen(
                                        Collectors.summingDouble(product -> product.getDiscount().doubleValue()),
                                        discount -> formatter.moneyFormat.format(discount)
                                )
                        )
                );
    }


    private static class ResourceFormatter {
        private Locale locale;
        private ResourceBundle resources;
        private DateTimeFormatter dateFormat;
        private NumberFormat moneyFormat;

        private ResourceFormatter(Locale locale) {
            this.locale = locale;
            this.resources = ResourceBundle.getBundle("labs.pm.data.resources", locale);
            this.dateFormat = DateTimeFormatter
                    .ofLocalizedDate(FormatStyle.SHORT)
                    .localizedBy(locale);
            this.moneyFormat = NumberFormat.getCurrencyInstance(locale);
        }

        /**
         * Format the Product for printing in the report
         *
         * @param product
         * @return
         */
        private String formatProduct(Product product) {
            return MessageFormat.format(resources.getString("product"), product.getName(),
                    moneyFormat.format(product.getPrice()), product
                            .getRating()
                            .getStars(), dateFormat.format(product.getBestBefore()));
        }

        /**
         * Format the reviews to be appended in the Product print report
         *
         * @param review
         * @return
         */
        private String formatReviews(Review review) {
            return MessageFormat.format(resources.getString("review"), review
                    .getRating()
                    .getStars(), review.getComment());
        }

        /**
         * Get any text from the resource bundle
         *
         * @param key
         * @return
         */
        private String getText(String key) {
            return resources.getString(key);
        }
    }

}
