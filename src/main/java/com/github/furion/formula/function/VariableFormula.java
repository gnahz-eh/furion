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

import com.github.furion.enums.FormulaType;
import com.github.furion.exception.FormulaException;
import com.github.furion.formula.Formula;
import com.github.furion.utils.ExceptionUtils;

public class VariableFormula extends Formula {

    private String variableName;
    private Formula actualValue;

    public VariableFormula(String variableName) {
        super(FormulaType.VARIABLE, true);
        this.variableName = variableName;
    }

    @Override
    public Formula calculate() throws FormulaException {
        if (actualValue == null) {
            return this;
        } else {
            if (!actualValue.isCalculable()) {
                return actualValue;
            }
            return actualValue.calculate();
        }
    }

    @Override
    public void verify() throws FormulaException {
        if (variableName == null) {
            throw new FormulaException(ExceptionUtils.NAME_OF_VARIABLE_IS_NULL);
        }
        if (actualValue != null) {
            actualValue.verify();
        }
    }

    private void assertFormulaCalculable(Formula formula, String message) throws FormulaException {
        if (!formula.isCalculable()) {
            throw new FormulaException(ExceptionUtils.FORMULA_IS_NOT_CALCULABLE, message);
        }
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public Formula getActualValue() {
        return actualValue;
    }

    public void setActualValue(Formula actualValue) {
        this.actualValue = actualValue;
    }
}
