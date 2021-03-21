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

import labs.pm.util.Condition;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 * @author MOHANTY
 * class is made Immutable
 * There are no setter method provided
 * Instance Variables can only be accessed through constructor
 */
public abstract class Product {
    private int id;
    private String name;
    private BigDecimal price;
    private Rating rating;
    private Condition condition;

    private BigDecimal tax;
    private BigDecimal discount;
    private BigDecimal discountAmount;
    private BigDecimal taxAmount;
    public static final BigDecimal DISCOUNT_RATE = BigDecimal.valueOf(0.10);
    public static final BigDecimal TAX_RATE = BigDecimal.valueOf(0.15);

    /**
     * Instance initializer
     */ {
        this.discountAmount = BigDecimal.ZERO;
        this.taxAmount = BigDecimal.ZERO;
    }

    /**
     * Constructor chaining
     */
    public Product() {
        this(0, "no-name", BigDecimal.ZERO);
    }

    public Product(int id, String name, BigDecimal price) {
        this(id, name, price, Rating.NOT_RATED);
    }

    public Product(int id, String name, BigDecimal price, Rating rating) {
        this(id, name, price, rating, Condition.NOT_AVAILABLE);
    }

    public Product(int id, String name, BigDecimal price, Rating rating, Condition condition) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.condition = condition;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getDiscountRate() {
        return DISCOUNT_RATE;
    }

    public BigDecimal getTaxRate() {
        return TAX_RATE;
    }

    public Condition getCondition() {
        return condition;
    }

    public Rating getRating() {
        return rating;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public LocalDate getBestBefore() {
        return LocalDate.now();
    }

    protected BigDecimal calculateDiscount() {
        discountAmount = price
                .multiply(DISCOUNT_RATE)
                .setScale(2, RoundingMode.HALF_UP);
        return discountAmount;
    }

    protected BigDecimal calculateTax() {
        taxAmount = price
                .multiply(TAX_RATE)
                .setScale(2, RoundingMode.HALF_UP);
        return taxAmount;
    }

    public BigDecimal getTotalPrice() {
        this.price = price.subtract(calculateDiscount());
        this.price = price.add(calculateTax());
        return price;
    }

    /**
     * @param values
     * @<code>Vaargs</code> implementation
     */
    private void setFiscalDetails(double... values) {
        switch (values.length) {
            case 3:
                tax = BigDecimal.valueOf(values[2]);
            case 2:
                discount = BigDecimal.valueOf(values[1]);
            case 1:
                price = BigDecimal.valueOf(values[0]);
        }
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name='" + name + '\'' + ", initial Price=" + getPrice() + ", Selling price="
                + getTotalPrice() + ", discount=" + getDiscountAmount() + ", tax=" + getTaxAmount() + "\nCustomer Note:"
                + condition.getCaution() + "\nRating= " + rating.getStars() + '}';
    }

    /**
     * Method to return Product object with Ratings
     *
     * @param rating
     * @return Product object
     */
    public abstract Product applyRating(Rating rating);
}
