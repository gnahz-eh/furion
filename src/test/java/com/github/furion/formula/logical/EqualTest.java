/*
 * MIT License
 *
 * Copyright (c) [2019] [He Zhang]
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.furion.formula.logical;

import com.github.furion.enums.FormulaType;
import com.github.furion.exception.FormulaException;
import com.github.furion.formula.Formula;
import com.github.furion.formula.number.IntegerFormula;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EqualTest {

    private Equal equal;

    @BeforeEach
    void init() {
        equal = new Equal(null, null);
    }

    @Test
    @DisplayName("Test of method calculate()")
    void calculate() throws FormulaException {
        double a = 6, b = 6, c = 7;
        Formula lhs = new IntegerFormula((int) a);
        Formula rhs = new IntegerFormula((int) b);
        Formula rhs2 = new IntegerFormula((int) c);
        equal.setLhs(lhs);
        equal.setRhs(rhs);
        Formula result = null;

        assertEquals(equal.getFormulaType(), FormulaType.EQUAL);
        result = equal.calculate();
        assertEquals(result.getFormulaType(), FormulaType.BOOLEAN);
        assertEquals(((BooleanFormula) result).getValue(), BooleanFormula.getInstance(a == b).getValue());

        equal.setRhs(rhs2);
        result = equal.calculate();
        assertEquals(result.getFormulaType(), FormulaType.BOOLEAN);
        assertEquals(((BooleanFormula) result).getValue(), BooleanFormula.getInstance(a == c).getValue());
    }
}