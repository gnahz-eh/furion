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

import java.io.IOException;
import java.io.Reader;

public class TokenReader extends Reader {

    private int peek = -1;
    private Reader reader;

    public TokenReader(Reader reader) {
        this.reader = reader;
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        if (peek != -1) {
            int read = 1;
            cbuf[off] = (char) peek;
            if (len > 1) {
                read = reader.read(cbuf, off + 1, len - 1);
            }
            if (read != -1) {
                read += 1;
            }
            peek = -1;
            return read;
        }
        return reader.read(cbuf, off, len);
    }

    public int peek() throws IOException {
        if (peek != -1)
            return peek;
        else
            return peek = read();
    }

    public char skipBlank() throws IOException {
        if (peek != -1 && !Character.isWhitespace(peek)) {
            char c = (char) peek;
            peek = -1;
            return c;
        } else {
            while (true) {
                char c = (char) reader.read();
                if (!Character.isWhitespace(c)) {
                    return c;
                }
            }
        }
    }
}
