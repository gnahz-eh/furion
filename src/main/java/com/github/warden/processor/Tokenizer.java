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


import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class Tokenizer {
    private TokenReader tokenReader;
    private int lastCharacter;

    public Tokenizer(BufferedReader bufferedReader) {
        this.tokenReader = new TokenReader(bufferedReader);
    }

    public Tokenizer(Reader reader) {
        this(new BufferedReader(reader));
    }

    public Tokenizer(String s) {
        this(new StringReader(s));
    }

    public Token tokenizeNext() throws IOException {
        if (lastCharacter == 0 || Character.isWhitespace(lastCharacter)) {
            lastCharacter = tokenReader.skipBlank();
        }
        return tokenize();
    }

    private Token tokenize() throws IOException {
        if (Character.isDigit(lastCharacter)) {
            return tokenizeNumber();
        }
        switch (lastCharacter) {
            case '+':
                lastCharacter = 0;
                return Token.PLUS;
            case '-':
                lastCharacter = 0;
                return Token.MINUS;
            case '*':
                lastCharacter = 0;
                return Token.MULTIPLY;
            case '/':
                lastCharacter = 0;
                return Token.DIVIDE;
            case '(':
                lastCharacter = 0;
                return Token.OPEN_BRACKET;
            case ')':
                lastCharacter = 0;
                return Token.CLOSE_BRACKET;
            case '^':
                lastCharacter = 0;
                return Token.POWER;
            case -1:
            case 0xffff:
                return null;
        }
        if (!Character.isJavaIdentifierStart(lastCharacter)) {
            throw new IOException("INVALID TOKEN: " + lastCharacter);
        }
        return null;
    }


    private Token tokenizeNumber() throws IOException {
        boolean isDecimal = false;
        StringBuilder sb = new StringBuilder();
        sb.append((char) lastCharacter);
        lastCharacter = tokenReader.read();
        while (Character.isDigit(lastCharacter) || lastCharacter == '.') {
            isDecimal = (lastCharacter == '.');
            sb.append((char) lastCharacter);
            lastCharacter = tokenReader.read();
        }

        if (lastCharacter == 'e' || lastCharacter == 'E') {
            sb.append((char) lastCharacter);
            lastCharacter = tokenReader.read();
            if (lastCharacter == '+' || lastCharacter == '-') {
                sb.append((char) lastCharacter);
                lastCharacter = tokenReader.read();
            }
            while (Character.isDigit(lastCharacter)) {
                sb.append((char) lastCharacter);
                lastCharacter = tokenReader.read();
            }
        }
        String value = sb.toString();
        if (isDecimal) {
            return new NumericalToken(Double.parseDouble(value));
        } else {
            try {
                return new NumericalToken(Integer.parseInt(value));
            } catch (NumberFormatException e) {
                return new NumericalToken(Double.parseDouble(value));
            }
        }
    }
}
