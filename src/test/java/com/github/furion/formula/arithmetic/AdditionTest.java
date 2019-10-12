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

package com.github.furion.formula.arithmetic;

import com.github.furion.enums.FormulaType;
import com.github.furion.exception.FormulaException;
import com.github.furion.formula.Formula;
import com.github.furion.formula.number.DoubleFormula;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdditionTest {

    private Addition addition;

    @BeforeEach
    void init() {
        addition = new Addition(null, null);
    }

    @Test
    @DisplayName("Test of method calculate(double lhs, double rhs)")
    void calculateTest() throws FormulaException {
        double a = 1;
        double b = 2;
        Formula result = addition.calculate(a, b);
        assertEquals(result.getFormulaType(), FormulaType.NUMBER_DOUBLE);
        assertEquals(((DoubleFormula) result).getValue(), a + b);
    }

    @Test
    @DisplayName("Test of method calculate()")
    void calculateWithNoArgsTest() throws Exception {
        double a = 3, b = 5;
        Formula lhs = new DoubleFormula(a);
        Formula rhs = new DoubleFormula(b);
        addition = new Addition(lhs, rhs);
        Formula result = addition.calculate();
        assertEquals(result.getFormulaType(), FormulaType.NUMBER_DOUBLE);
        assertEquals(((DoubleFormula) result).getValue(), a + b);
    }
}