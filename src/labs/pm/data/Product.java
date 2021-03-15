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
import java.math.RoundingMode;

/**
 * @author MOHANTY
 */
public class Product {
    private int id;
    private String name;
    private BigDecimal price;
    private BigDecimal tax;
    private BigDecimal discount;

    public static final BigDecimal DISCOUNT_RATE = BigDecimal.valueOf(0.10);

    public Product() {
        this.id = 0;
        this.name = null;
        this.price = BigDecimal.ZERO;
        this.discount = BigDecimal.ZERO;
        this.tax = BigDecimal.ZERO;
    }

    /**
     * Resuse of constructor through this()
     * @param id
     * @param name
     * @param price
     */
    public Product(int id, String name,BigDecimal price) {
        this(id,name);
        this.price = price;
    }

    public Product(int id,String name) {
        this.id = id;
        this.name = name;
        this.tax = BigDecimal.ONE;
    }
    /**
     * @<code>Vaargs</code> implementation
     * @param values
     */
    public void setFiscalDetails(double... values) {
        switch(values.length) {
            case 3:
                tax = BigDecimal.valueOf(values[2]);
            case 2:
                discount = BigDecimal.valueOf(values[1]);
            case 1:
                price = BigDecimal.valueOf(values[0]);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount() {
        return price
                .multiply(DISCOUNT_RATE)
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name='" + name + '\'' + ", price=" + price + ", discount=" + getDiscount()
                + ", tax="+tax +'}';
    }
}
