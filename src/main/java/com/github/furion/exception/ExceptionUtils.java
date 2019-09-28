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

package com.github.furion.exception;

import java.util.HashMap;
import java.util.Map;

public class ExceptionUtils {

    public static final int MISSING_LEFT_HAND_SIDE = 1;
    public static final int MISSING_RIGHT_HAND_SIDE = 2;
    public static final int DIVISOR_IS_ZERO = 3;
    public static final int UNEXPECTED_TOKEN_TYPE_FOUND = 4;
    public static final int UNEXPECTED_OPERATOR_TYPE_FOUND = 5;
    public static final int MISSING_DIVIDEND_OR_MULTIPLICAND = 6;
    public static final int UNEXPECTED_FORMULA_FOUND = 7;
    public static final int UNEXPECTED_TOKEN_FOUND = 8;
    public static final int INVALID_TOKEN = 9;
    public static final int IMPLEMENTATION_OF_FUNCTION_FORMULA_IS_NULL = 10;
    public static final int NAME_OF_FUNCTION_FORMULA_IS_NULL = 11;
    public static final int MISS_ARG_BEFORE_COMMA = 12;
    public static final int NO_ARGS_IN_FUNCTION = 13;
    public static final int ASSERT_ARG_COUNT = 14;
    public static final int INVALID_ARG_TYPE_FOR_FUNCTION = 15;
    public static final int NULL_FORMULA = 16;
    public static final int FORMULA_IS_NOT_CALCULABLE = 17;
    public static final int NAME_OF_VARIABLE_IS_NULL = 18;


    public static final Map<Integer, String> exceptionMap = new HashMap<Integer, String>();

    static {
        exceptionMap.put(MISSING_LEFT_HAND_SIDE, "Missing left hand side");
        exceptionMap.put(MISSING_RIGHT_HAND_SIDE, "Miss right hand side");
        exceptionMap.put(DIVISOR_IS_ZERO, "Divisor is zero");
        exceptionMap.put(UNEXPECTED_TOKEN_TYPE_FOUND, "Unexpected token type found");
        exceptionMap.put(UNEXPECTED_OPERATOR_TYPE_FOUND, "Unexpected operator type found");
        exceptionMap.put(MISSING_DIVIDEND_OR_MULTIPLICAND, "Missing dividend or multiplicand");
        exceptionMap.put(UNEXPECTED_FORMULA_FOUND, "Unexpected formula found");
        exceptionMap.put(UNEXPECTED_TOKEN_FOUND, "Unexpected formula found");
        exceptionMap.put(INVALID_TOKEN, "Invalid token");
        exceptionMap.put(IMPLEMENTATION_OF_FUNCTION_FORMULA_IS_NULL, "Implementation of FunctionFormula is null");
        exceptionMap.put(NAME_OF_FUNCTION_FORMULA_IS_NULL, "Name of function formula is null");
        exceptionMap.put(MISS_ARG_BEFORE_COMMA, "Missing arg before comma");
        exceptionMap.put(NO_ARGS_IN_FUNCTION, "No args in function");
        exceptionMap.put(ASSERT_ARG_COUNT, "Number of args in function is not equal to count");
        exceptionMap.put(INVALID_ARG_TYPE_FOR_FUNCTION, "Invalid argument type for function");
        exceptionMap.put(NULL_FORMULA, "Null Formula");
        exceptionMap.put(FORMULA_IS_NOT_CALCULABLE, "Formula is not calculable");
        exceptionMap.put(NAME_OF_VARIABLE_IS_NULL, "Name of variable formula is null");
    }
}
