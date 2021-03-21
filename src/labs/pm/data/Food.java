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
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author MOHANTY
 */
public class Food extends Product {

    private LocalDate bestBefore;

    public Food(int id, String name, BigDecimal price, LocalDate bestBefore) {
        super(id, name, price);
        this.bestBefore = bestBefore;

    }

    @Override
    protected BigDecimal calculateDiscount() {
        return bestBefore.equals(LocalDate.now())?super.calculateDiscount():BigDecimal.ZERO;
    }

    @Override
    public String toString() {
        return "Food Class:  " + this.id + " " + this.name + " " + this.price + " " + this.bestBefore;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && Objects.equals(this.getClass(), obj.getClass())) {
//        if((obj instanceof Food)) {
            Food other = (Food)obj;
            return (this.id == other.id) && Objects.equals(this.name, other.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }
}
