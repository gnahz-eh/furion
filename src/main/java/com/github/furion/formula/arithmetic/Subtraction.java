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
import com.github.furion.utils.ExceptionUtils;

public class Subtraction extends ArithmeticFormula {

    public Subtraction(Formula lhs, Formula rhs) {
        super(FormulaType.SUBTRACTION, lhs, rhs);
        if (lhs == null) {
            this.lhs = new DoubleFormula(0.0);
        }
    }

    @Override
    public void verify() throws FormulaException {
        if (lhs != null) {
            lhs.verify();
        }
        if (rhs == null) {
            throw new FormulaException(ExceptionUtils.MISSING_RIGHT_HAND_SIDE);
        }
        rhs.verify();
    }

    @Override
    public Formula calculate(double lhs, double rhs) throws FormulaException {
        return new DoubleFormula(lhs - rhs);
    }
}
