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

package com.github.warden.processor;


import com.github.warden.exception.FormulaException;
import com.github.warden.formula.DyadicFormula;
import com.github.warden.formula.Formula;
import com.github.warden.formula.arithmetic.Addition;
import com.github.warden.formula.arithmetic.Division;
import com.github.warden.formula.arithmetic.Multiplication;
import com.github.warden.formula.arithmetic.Subtraction;
import com.github.warden.formula.number.DoubleFormula;
import com.github.warden.formula.number.IntegerFormula;

import java.io.IOException;

public class Parser {
    private Formula current;

    public static Formula parse(String s) throws FormulaException, IOException {
        Parser parser = new Parser();
        parser.parse(new Tokenizer(s));
        return parser.getCurrent();
    }

    public void parse(Tokenizer tokenizer) throws FormulaException, IOException {
        Token token = null;
        while ((token = tokenizer.tokenizeNext()) != null) {
            parseToken(token);
        }
    }

    private void parseToken(Token token) throws FormulaException, IOException {
        switch (token.getTokenType()) {
            case PLUS:
            case MINUS:
            case MULTIPLY:
            case DIVIDE:
                parseOperator(token);
                break;
            case DOUBLE:
            case INTEGER:
                parseValue(token);
                break;
            default:
                throw new FormulaException("UNEXPECTED " + token.getTokenType() + " FOUND");
        }
    }

    private void parseOperator(Token token) throws FormulaException {
        switch (token.getTokenType()) {
            case PLUS:
                Formula lhs = current;
                current = new Addition(lhs, null);
                break;
            case MINUS:
                lhs = current;
                current = new Subtraction(lhs, null);
                break;
            case MULTIPLY:
                parseMultiplyDivide(new Multiplication(null, null));
                break;
            case DIVIDE:
                parseMultiplyDivide(new Division(null, null));
                break;
            default:
                throw new FormulaException("UNEXPECTED OPERATOR TYPE: " + token.getTokenType());
        }
    }

    private void parseValue(Token token) throws FormulaException {
        Formula realValue = null;
        switch (token.getTokenType()) {
            case INTEGER:
                realValue = new IntegerFormula(((NumericalToken) token).getIntegerValue());
                break;
            case DOUBLE:
                realValue = new DoubleFormula(((NumericalToken) token).getDoubleValue());
                break;
        }
        processValue(realValue);
    }

    private void parseMultiplyDivide(Formula formula) throws FormulaException {
        if (current == null) {
            throw new FormulaException("UNEXPECTED NULL TOKEN");
        }
        Formula curr = current;
        Formula previous = null;
        while (curr != null) {
            if (curr instanceof Addition || curr instanceof  Subtraction) {
                previous = curr;
                curr = ((DyadicFormula) current).getRhs();
            } else {
                if (previous == null) {
                    ((DyadicFormula) formula).setLhs(current);
                    current = formula;
                    return;
                } else {
                    Formula previousRHS = ((DyadicFormula) previous).getRhs();
                    ((DyadicFormula) formula).setLhs(previousRHS);
                    ((DyadicFormula) previous).setRhs(formula);
                    return;
                }
            }
        }
    }

    private void processValue(Formula realValue) throws FormulaException {
        if (current == null) {
            current = realValue;
            return;
        } else {
            Formula curr = current;
            do {
                if (!(curr instanceof DyadicFormula)) {
                    throw new FormulaException("UNEXPECTED FORMULA FOUND (NOT DYADIC_FORMULA)");
                }
                Formula currentRHS = ((DyadicFormula) curr).getRhs();
                if (currentRHS == null) {
                    ((DyadicFormula) curr).setRhs(realValue);
                    return;
                } else {
                    curr = currentRHS;
                }
            } while (curr != null);
        }
        throw new FormulaException("UNEXPECTED TOKEN FOUND");
    }

    public Formula getCurrent() {
        return current;
    }
}
