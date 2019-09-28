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

public class Token {

    private TokenType tokenType;
    private String value;

    public Token(TokenType tokenType, String value) {
        this.tokenType = tokenType;
        this.value = value;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static final Token PLUS = new Token(TokenType.PLUS, "+");
    public static final Token MINUS = new Token(TokenType.MINUS, "-");
    public static final Token MULTIPLY = new Token(TokenType.MULTIPLY, "*");
    public static final Token DIVIDE = new Token(TokenType.DIVIDE, "/");
    public static final Token OPEN_BRACKET = new Token(TokenType.OPEN_BRACKET, "(");
    public static final Token CLOSE_BRACKET = new Token(TokenType.CLOSE_BRACKET, ")");
    public static final Token POWER = new Token(TokenType.POWER, "^");
    public static final Token EQUAL = new Token(TokenType.EQUAL, "=");
    public static final Token NOT_EQUAL = new Token(TokenType.NOT_EQUAL, "<>");
    public static final Token GREATER_THAN = new Token(TokenType.GREATER_THAN, ">");
    public static final Token LESS_THAN = new Token(TokenType.LESS_THAN, "<");
    public static final Token GREATER_THAN_OR_EQUAL_TO = new Token(TokenType.GREATER_THAN_OR_EQUAL_TO, ">=");
    public static final Token LESS_THAN_OR_EQUAL_TO = new Token(TokenType.LESS_THAN_OR_EQUAL_TO, "<=");
    public static final Token COMMA = new Token(TokenType.COMMA, ",");
}
