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

package com.github.furion.formula.function;

import com.github.furion.exception.ExceptionUtils;
import com.github.furion.exception.FormulaException;
import com.github.furion.formula.Formula;
import com.github.furion.formula.number.NumberFormula;

public abstract class AbstractFunction implements Function {

    protected void assertArgCount(Formula[] args, int count) throws FormulaException {
        if (args == null && count != 0) {
            throw new FormulaException(ExceptionUtils.NO_ARGS_IN_FUNCTION, getClass().getSimpleName());
        }

        if (args.length != count) {
            throw new FormulaException(ExceptionUtils.ASSERT_ARG_COUNT, getClass().getSimpleName());
        }
    }

    protected double toDouble(Formula arg) throws FormulaException {
        if (arg.isCalculable()) {
            arg = arg.calculate();
        }
        if (arg instanceof NumberFormula) {
            return ((NumberFormula) arg).getValue();
        }
        throw new FormulaException(ExceptionUtils.INVALID_ARG_TYPE_FOR_FUNCTION, getClass().getSimpleName());
    }
}
