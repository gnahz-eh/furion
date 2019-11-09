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
import com.github.furion.formula.DyadicFormula;
import com.github.furion.formula.Formula;
import com.github.furion.formula.number.NumberFormula;
import com.github.furion.utils.ExceptionUtils;

public abstract class ComparisonFormula extends DyadicFormula {

    public ComparisonFormula(FormulaType formulaType, Formula lhs, Formula rhs) {
        super(formulaType, lhs, rhs);
    }

    protected double compare() throws FormulaException {
        Formula left = lhs.calculate();
        assertNumberFormula(left, "lhs");
        Formula right = rhs.calculate();
        assertNumberFormula(right, "rhs");

        if (left instanceof NumberFormula && right instanceof NumberFormula) {
            return ((NumberFormula) left).getValue() - ((NumberFormula) right).getValue();
        }
        return 0;
    }

    private void assertNumberFormula(Formula formula, String message) throws FormulaException {
        if (formula == null) {
            throw new FormulaException(ExceptionUtils.NULL_FORMULA, message);
        }

        if (!(formula instanceof NumberFormula)) {
            throw new FormulaException(ExceptionUtils.UNEXPECTED_FORMULA_FOUND, "Should be NumberFormula");
        }
    }
}
