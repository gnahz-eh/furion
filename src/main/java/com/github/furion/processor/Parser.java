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

package com.github.furion.processor;


import com.github.furion.enums.TokenType;
import com.github.furion.exception.ExceptionUtils;
import com.github.furion.exception.FormulaException;
import com.github.furion.formula.BracketFormula;
import com.github.furion.formula.DyadicFormula;
import com.github.furion.formula.Formula;
import com.github.furion.formula.arithmetic.*;
import com.github.furion.formula.function.FunctionFormula;
import com.github.furion.formula.function.VariableFormula;
import com.github.furion.formula.logical.*;
import com.github.furion.formula.number.DoubleFormula;
import com.github.furion.formula.number.IntegerFormula;
import com.github.furion.formula.string.StringFormula;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            parseToken(token, tokenizer);
        }
    }

    private void parseToken(Token token, Tokenizer tokenizer) throws FormulaException, IOException {
        switch (token.getTokenType()) {
            case PLUS:
            case MINUS:
            case MULTIPLY:
            case DIVIDE:
            case POWER:
            case EQUAL:
            case NOT_EQUAL:
            case GREATER_THAN:
            case LESS_THAN:
            case GREATER_THAN_OR_EQUAL_TO:
            case LESS_THAN_OR_EQUAL_TO:
                parseOperator(token);
                break;
            case DOUBLE:
            case INTEGER:
            case STRING:
            case VARIABLE:
                parseValue(token);
                break;
            case OPEN_BRACKET:
                parseBracket(tokenizer);
                break;
            case FUNCTION:
                parseFunction(token, tokenizer);
                break;
            default:
                throw new FormulaException(ExceptionUtils.UNEXPECTED_TOKEN_TYPE_FOUND, token.getValue());
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
            case POWER:
                parseMultiplyDivide(new Power(null, null));
                break;
            case EQUAL:
                current = new Equal(current, null);
                break;
            case NOT_EQUAL:
                current = new NotEqual(current, null);
                break;
            case GREATER_THAN:
                current = new GreaterThan(current, null);
                break;
            case LESS_THAN:
                current = new LessThan(current, null);
                break;
            case GREATER_THAN_OR_EQUAL_TO:
                current = new GreaterThanOrEqualTo(current, null);
                break;
            case LESS_THAN_OR_EQUAL_TO:
                current = new LessThanOrEqualTo(current, null);
                break;
            default:
                throw new FormulaException(ExceptionUtils.UNEXPECTED_OPERATOR_TYPE_FOUND);
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
            case STRING:
                realValue = new StringFormula(token.getValue());
                break;
            case VARIABLE:
                realValue = new VariableFormula(token.getValue());
                break;
        }
        processValue(realValue);
    }

    private void parseMultiplyDivide(Formula formula) throws FormulaException {
        if (current == null) {
            throw new FormulaException(ExceptionUtils.MISSING_DIVIDEND_OR_MULTIPLICAND);
        }
        Formula curr = current;
        Formula previous = null;
        while (curr != null) {
            if (curr instanceof Addition || curr instanceof Subtraction) {
                previous = curr;
                curr = ((DyadicFormula) curr).getRhs();
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

    private void parseBracket(Tokenizer tokenizer) throws FormulaException, IOException {
        Formula temporaryStorage = current;
        current = null;
        Token token = null;
        while ((token = tokenizer.tokenizeNext()) != null) {
            if (token.getTokenType().equals(TokenType.CLOSE_BRACKET)) {
                Formula bracketFormula = current;
                current = temporaryStorage;
                processValue(new BracketFormula(bracketFormula));
                break;
            } else {
                parseToken(token, tokenizer);
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
                    throw new FormulaException(ExceptionUtils.UNEXPECTED_FORMULA_FOUND);
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
        throw new FormulaException(ExceptionUtils.UNEXPECTED_TOKEN_FOUND);
    }

    private void parseFunction(Token token, Tokenizer tokenizer) throws FormulaException, IOException {
        Formula temporaryStorage = current;
        current = null;
        Token next = null;
        List<Formula> args = new ArrayList<>();
        while ((next = tokenizer.tokenizeNext()) != null) {
            if (next.getTokenType().equals(TokenType.COMMA)) {
                if (current == null) {
                    throw new FormulaException(ExceptionUtils.MISS_ARG_BEFORE_COMMA);
                } else {
                    args.add(current);
                }
                current = null;
            } else if (next.getTokenType().equals(TokenType.CLOSE_BRACKET)) {
                if (current != null) {
                    args.add(current);
                }
                current = temporaryStorage;
                break;
            } else {
                parseToken(next, tokenizer);
            }
        }
        FunctionFormula functionFormula =
                new FunctionFormula(token.getValue(), (Formula[]) args.toArray(new Formula[0]));
        processValue(functionFormula);
    }

    public Formula getCurrent() {
        return current;
    }
}
