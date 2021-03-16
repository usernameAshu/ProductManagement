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

/**
 * @author MOHANTY
 * class is made Immutable
 * There are no setter method provided
 * Instance Variables can only be accessed through constructor
 */
public class Product {
    private int id;
    private String name;
    private BigDecimal price;
    private BigDecimal tax;
    private BigDecimal discount;
    private Condition condition;
    private Rating rating;
    public static final BigDecimal DISCOUNT_RATE = BigDecimal.valueOf(0.10);

    /**
     * Instance initializer
     */
    {
        this.price = BigDecimal.valueOf(20);
        this.tax = BigDecimal.valueOf(0.10);
        this.condition = Condition.WARM;
        this.rating = Rating.NOT_RATED;
    }
    /**
     * Resuse of constructor through this()
     *
     * @param id
     * @param name
     */
    public Product(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Product(int id, String name, BigDecimal price, BigDecimal tax, Condition condition, Rating rating) {
        this(id, name);
        this.price = price;
        this.tax = tax;
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

    public BigDecimal getDiscount() {
        return this.DISCOUNT_RATE;
    }

    public BigDecimal getTaxRate() {
        return tax;
    }

    public BigDecimal calculateDiscount() {
        return price
                .multiply(DISCOUNT_RATE)
                .setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateTax() {
        return price
                .multiply(tax)
                .setScale(2, RoundingMode.HALF_UP);
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
                + getTotalPrice() + ", discount=" + getDiscount() + ", tax=" + calculateTax() + "\nCustomer Note:" +condition.getCaution()
                + "\nRating= "+ rating.getStars()+ '}';
    }
}
