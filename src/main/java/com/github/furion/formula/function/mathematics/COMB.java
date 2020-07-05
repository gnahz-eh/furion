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

package com.github.furion.formula.function.mathematics;

import com.github.furion.exception.FormulaException;
import com.github.furion.formula.Formula;

import java.math.BigInteger;
import java.util.List;

public class COMB extends MISOFunction {

    @Override
    public Formula calculate(Formula[] args) throws FormulaException {
        assertArgCount(args, 2);
        return super.calculate(args);
    }

    @Override
    public double calculate(List<Double> args) {
        int N = args.get(0).intValue();
        int K = args.get(1).intValue();
        return factorial(N)
                .divide(factorial(K).multiply(factorial(N - K)))
                .doubleValue();
    }

    private BigInteger factorial(int value) {
        BigInteger n = BigInteger.ONE;
        for (int i = 2; i <= value; i++) {
            n = n.multiply(BigInteger.valueOf(i));
        }
        return n;
    }
}
