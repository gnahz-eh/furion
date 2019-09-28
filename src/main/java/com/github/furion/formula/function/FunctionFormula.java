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
import com.github.furion.exception.ExceptionUtils;
import com.github.furion.exception.FormulaException;
import com.github.furion.formula.Formula;

public class FunctionFormula extends Formula {

    private String functionName;
    private Formula[] args;
    private Function implementation;

    public FunctionFormula(String functionName, Formula[] args) {
        super(FormulaType.FUNCTION, true);
        this.functionName = functionName;
        this.args = args;
    }

    @Override
    public Formula calculate() throws FormulaException {
        if (implementation == null) {
            throw new FormulaException(ExceptionUtils.IMPLEMENTATION_OF_FUNCTION_FORMULA_IS_NULL);
        }
        return implementation.calculate(args);
    }

    @Override
    public void verify() throws FormulaException {
        if (functionName == null) {
            throw new FormulaException(ExceptionUtils.NAME_OF_FUNCTION_FORMULA_IS_NULL);
        }
        for (Formula formula : args) {
            formula.verify();
        }
    }

    public Function getImplementation() {
        return implementation;
    }

    public void setImplementation(Function implementation) {
        this.implementation = implementation;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public Formula[] getArgs() {
        return args;
    }

    public void setArgs(Formula[] args) {
        this.args = args;
    }
}
