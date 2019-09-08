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

package com.github.warden.formula.function;

import com.github.warden.enums.FormulaType;
import com.github.warden.formula.Formula;
import com.github.warden.formula.function.mathematics.Abs;
import com.github.warden.formula.number.NumberFormula;
import com.github.warden.processor.Parser;
import org.junit.Assert;
import org.junit.Test;

public class FunctionTest {

    @Test
    public void absTest() {
        double arg = -12;
        String line = "test(" + arg + ")";
        try {
            Formula formula = Parser.parse(line);
            Assert.assertEquals(formula.getFormulaType(), FormulaType.FUNCTION);
            ((FunctionFormula) formula).setImplementation(new Abs());
            formula = formula.calculate();
            Assert.assertEquals(formula.getFormulaType(), FormulaType.NUMBER_DOUBLE);
            Assert.assertEquals(((NumberFormula) formula).getValue(), Math.abs(arg), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
