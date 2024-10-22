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
import com.github.furion.exception.FormulaException;
import com.github.furion.utils.ExceptionUtils;

public abstract class DyadicFormula extends Formula {

    protected Formula lhs;
    protected Formula rhs;

    public DyadicFormula(FormulaType formulaType, Formula lhs, Formula rhs) {
        super(formulaType, true);
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public void verify() throws FormulaException {
        if (lhs == null) {
            throw new FormulaException(ExceptionUtils.MISSING_LEFT_HAND_SIDE);
        }
        lhs.verify();
        if (rhs == null) {
            throw new FormulaException(ExceptionUtils.MISSING_RIGHT_HAND_SIDE);
        }
        rhs.verify();
    }

    public Formula getLhs() {
        return lhs;
    }

    public void setLhs(Formula lhs) {
        this.lhs = lhs;
    }

    public Formula getRhs() {
        return rhs;
    }

    public void setRhs(Formula rhs) {
        this.rhs = rhs;
    }
}
