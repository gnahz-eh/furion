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

package com.github.furion.formula;

import com.github.furion.enums.FormulaType;
import com.github.furion.formula.logical.BooleanFormula;
import com.github.furion.processor.Parser;
import org.junit.Assert;
import org.junit.Test;


public class ComparisonFormulaTest {

    @Test
    public void equalTest() {
        String line = "3=3";
        try {
            Formula formula = Parser.parse(line);
            Assert.assertEquals(formula.getFormulaType(), FormulaType.EQUAL);
            Assert.assertEquals(formula.calculate(), BooleanFormula.TRUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void notEqualTest() {
        String line = "5<>3";
        try {
            Formula formula = Parser.parse(line);
            Assert.assertEquals(formula.getFormulaType(), FormulaType.NOT_EQUAL);
            Assert.assertEquals(formula.calculate(), BooleanFormula.TRUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void greaterThanOrEqualToTest() {
        String line = "5>=3";
        try {
            Formula formula = Parser.parse(line);
            Assert.assertEquals(formula.getFormulaType(), FormulaType.GREATER_THAN_OR_EQUAL_TO);
            Assert.assertEquals(formula.calculate(), BooleanFormula.TRUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void lessThanOrEqualToTest() {
        String line = "3<=5";
        try {
            Formula formula = Parser.parse(line);
            Assert.assertEquals(formula.getFormulaType(), FormulaType.LESS_THAN_OR_EQUAL_TO);
            Assert.assertEquals(formula.calculate(), BooleanFormula.TRUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void lessThanTest() {
        String line = "3<5";
        try {
            Formula formula = Parser.parse(line);
            Assert.assertEquals(formula.getFormulaType(), FormulaType.LESS_THAN);
            Assert.assertEquals(formula.calculate(), BooleanFormula.TRUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void greaterThanTest() {
        String line = "5>3";
        try {
            Formula formula = Parser.parse(line);
            Assert.assertEquals(formula.getFormulaType(), FormulaType.GREATER_THAN);
            Assert.assertEquals(formula.calculate(), BooleanFormula.TRUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
