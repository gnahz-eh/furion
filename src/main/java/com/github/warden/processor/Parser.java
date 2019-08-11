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
import com.github.warden.formula.Formula;
import com.github.warden.formula.arithmetic.Addition;
import com.github.warden.formula.arithmetic.Division;
import com.github.warden.formula.arithmetic.Multiplication;
import com.github.warden.formula.arithmetic.Subtraction;

import java.io.IOException;

public class Parser {
    private Formula current;

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

        }
    }

    private void parseValue(Token token) throws FormulaException {

    }

    private void parseMultiplyDivide(Formula formula) throws FormulaException {
        if (current == null) {
            throw new FormulaException("UNEXPECTED NULL TOKEN");
        }
    }
}
