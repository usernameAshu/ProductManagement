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
public abstract class Product implements Rateable<Product>{
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
    Product(int id, String name, BigDecimal price) {
        this(id, name, price, Rating.NOT_RATED);
    }

    Product(int id, String name, BigDecimal price, Rating rating) {
        this.id = id;
        this.name = name;
        this.price = price;
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

    @Override
    public Rating getRating() {
        return rating;
    }

    public LocalDate getBestBefore() {
        return LocalDate.now();
    }

    public BigDecimal getDiscount() {
        discountAmount = price
                .multiply(DISCOUNT_RATE)
                .setScale(2, RoundingMode.HALF_UP);
        return discountAmount;
    }

    protected BigDecimal getTax() {
        taxAmount = price
                .multiply(TAX_RATE)
                .setScale(2, RoundingMode.HALF_UP);
        return taxAmount;
    }


    @Override
    public String toString() {
        return getId() + " " + getName() + " " + getPrice() + " " + getDiscount() + " " + getRating() + " "
                + getBestBefore() + " ";
    }

    @Override
    public int hashCode() {
        int hash= 5;
        hash = 61*hash + this.getId();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(this ==obj) {
            return true;
        }
        if(obj instanceof Product) {
            final Product other = (Product)obj;
            return this.getId() == other.getId();
        }
        return false;
    }
}
