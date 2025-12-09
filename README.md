# 🖲️ Furion - Mathematical Expression Parser and Evaluator

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java](https://img.shields.io/badge/Java-8+-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.0+-blue.svg)](https://maven.apache.org/)
[![Maintenance](https://img.shields.io/badge/Maintained%3F-yes-green.svg)](https://github.com/gnahZ-eH/Furion/graphs/commit-activity)

Furion is a powerful and flexible Java library for parsing and evaluating mathematical expressions. It supports arithmetic operations, mathematical functions, logical comparisons, string operations, and variable substitution with a clean, extensible architecture.

## ✨ Features

### 🔢 Arithmetic Operations
- **Basic Operations**: Addition (`+`), Subtraction (`-`), Multiplication (`*`), Division (`/`)
- **Advanced Operations**: Power operations (`^`), Parentheses for grouping
- **Number Types**: Support for both integers and floating-point numbers (including scientific notation)

### 📐 Mathematical Functions
Comprehensive collection of mathematical functions:
- **Trigonometric**: `SIN`, `COS`, `TAN`, `ASIN`, `ACOS`, `ATAN`
- **Logarithmic**: `LOG`, `LOG10`, `EXP`
- **Statistical**: `AVE` (average), `SUM`, `MIN`, `MAX`, `GEOM` (geometric mean)
- **Utility**: `ABS` (absolute value), `SQRT` (square root), `CEIL`, `FLOOR`
- **Number Theory**: `FACT` (factorial), `COMB` (combinations), `EVEN`, `ODD`, `SIGN`
- **Conversion**: `DEG` (degrees), `RAD` (radians)

### 🔍 Logical Operations
- **Comparison Operators**: `=`, `!=`, `>`, `<`, `>=`, `<=`
- **Boolean Results**: Returns boolean values for logical expressions

### 📝 String Support
- String literal support with comparison operations
- String formula evaluation

### 🔄 Variable Support
- Variable substitution in expressions
- Dynamic variable binding at runtime

### 🏗️ Architecture Highlights
- **Modular Design**: Clean separation of concerns with distinct packages for different formula types
- **Extensible**: Easy to add new mathematical functions and operators
- **Type Safety**: Strong typing with formula type enumeration
- **Error Handling**: Comprehensive exception handling with meaningful error messages
- **Caching**: Built-in caching system for improved performance

## 🚀 Quick Start

### Prerequisites
- Java 8 or higher
- Maven 3.0 or higher

### Installation

Clone the repository:
```bash
git clone https://github.com/gnahZ-eH/Furion.git
cd Furion
```

Build the project:
```bash
mvn compile
```

Run tests:
```bash
mvn test
```

### Basic Usage

#### Simple Arithmetic
```java
import com.github.furion.processor.Parser;
import com.github.furion.formula.Formula;
import com.github.furion.formula.number.DoubleFormula;

// Parse and evaluate a simple expression
Formula result = Parser.parse("2 + 3 * 4");
result = result.calculate();
double value = ((DoubleFormula) result).getValue(); // Returns 14.0
```

#### Complex Expressions with Functions
```java
// Mathematical functions
Formula result = Parser.parse("SIN(30) + COS(60) * SQRT(16)");
result = result.calculate();
double value = ((DoubleFormula) result).getValue();

// Statistical functions
Formula average = Parser.parse("AVE(10, 20, 30, 40, 50)");
average = average.calculate();
double avgValue = ((DoubleFormula) average).getValue(); // Returns 30.0
```

#### Variable Substitution
```java
import java.util.HashMap;
import java.util.Map;

// Define variables
Map<String, String> variables = new HashMap<>();
variables.put("x", "10");
variables.put("y", "5");

// Parse expression with variables
Formula result = Parser.parseWithVariable("x * 2 + y", variables);
result = result.calculate();
double value = ((DoubleFormula) result).getValue(); // Returns 25.0
```

#### Logical Comparisons
```java
// Comparison operations
Formula comparison = Parser.parse("10 > 5");
comparison = comparison.calculate();
// Returns BooleanFormula with true value

// Complex logical expressions
Formula complex = Parser.parse("(10 + 5) >= (3 * 5)");
complex = complex.calculate();
```

#### Scientific Notation
```java
// Scientific notation support
Formula scientific = Parser.parse("-1.3E1 / 3");
scientific = scientific.calculate();
double value = ((DoubleFormula) scientific).getValue(); // Returns -4.333...
```

## 🏗️ Architecture

Furion follows a modular architecture with clear separation of concerns:

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Input String  │───▶│   Tokenizer     │───▶│     Parser      │
│  "2 + 3 * 4"    │    │   Lexical       │    │   Syntactic     │
│                 │    │   Analysis      │    │   Analysis      │
└─────────────────┘    └─────────────────┘    └─────────────────┘
                                                        │
                                                        ▼
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│     Result      │◀───│   Calculator    │◀───│  Formula Tree   │
│   DoubleFormula │    │   Expression    │    │   AST Node      │
│   value: 14.0   │    │   Evaluation    │    │   Hierarchy     │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

## 📁 Project Structure

```
src/main/java/com/github/furion/
├── enums/                  # Enumeration types
│   ├── FormulaType.java   # Types of formulas
│   └── TokenType.java     # Types of tokens
├── exception/             # Exception handling
│   └── FormulaException.java
├── formula/               # Core formula implementations
│   ├── arithmetic/        # Arithmetic operations (+, -, *, /, ^)
│   ├── function/          # Mathematical functions
│   │   └── mathematics/   # Mathematical function implementations
│   ├── logical/           # Logical comparison operations
│   ├── number/            # Number representations
│   └── string/            # String operations
├── processor/             # Expression parsing and tokenization
│   ├── Parser.java        # Main expression parser
│   ├── Tokenizer.java     # Tokenizes input strings
│   └── TokenReader.java   # Token reading utilities
└── utils/                 # Utility classes
    ├── Cache.java         # Caching system
    ├── ExceptionUtils.java # Exception utilities
    ├── FunctionUtils.java  # Function utilities
    └── StringUtils.java    # String utilities
```

## 📊 Supported Mathematical Functions

| Function | Description | Example |
|----------|-------------|---------|
| `ABS(x)` | Absolute value | `ABS(-5)` → `5` |
| `SQRT(x)` | Square root | `SQRT(16)` → `4` |
| `SIN(x)` | Sine | `SIN(90)` → `1` |
| `COS(x)` | Cosine | `COS(0)` → `1` |
| `TAN(x)` | Tangent | `TAN(45)` → `1` |
| `LOG(x)` | Natural logarithm | `LOG(E)` → `1` |
| `LOG10(x)` | Base-10 logarithm | `LOG10(100)` → `2` |
| `EXP(x)` | e^x | `EXP(1)` → `2.718...` |
| `FACT(x)` | Factorial | `FACT(5)` → `120` |
| `AVE(...)` | Average | `AVE(1,2,3,4,5)` → `3` |
| `SUM(...)` | Sum | `SUM(1,2,3,4,5)` → `15` |
| `MIN(...)` | Minimum | `MIN(1,2,3)` → `1` |
| `MAX(...)` | Maximum | `MAX(1,2,3)` → `3` |
| `CEIL(x)` | Ceiling | `CEIL(4.2)` → `5` |
| `FLOOR(x)` | Floor | `FLOOR(4.8)` → `4` |

## ⚠️ Error Handling

Furion provides comprehensive error handling through the `FormulaException` class. Common error scenarios include:

- **Syntax Errors**: Malformed expressions
- **Type Mismatches**: Invalid operations between incompatible types
- **Missing Operands**: Incomplete expressions
- **Function Errors**: Invalid function arguments or unknown functions

```java
try {
    Formula result = Parser.parse("2 + ");  // Incomplete expression
    result = result.calculate();
} catch (FormulaException e) {
    System.err.println("Parse error: " + e.getMessage());
}
```

## 🧪 Testing

The project includes comprehensive unit tests covering:
- Individual arithmetic operations
- Mathematical function implementations
- Logical operations
- String operations
- Variable substitution
- Error conditions

Run the test suite:
```bash
mvn test
```

Generate test coverage report:
```bash
mvn jacoco:report
```

### Core Components

| Component | Responsibility | Key Classes |
|-----------|---------------|--------------|
| **Tokenizer** | Breaks input into tokens | `Tokenizer`, `Token`, `TokenType` |
| **Parser** | Builds expression tree | `Parser`, `TokenReader` |
| **Formula Tree** | Represents parsed expression | `Formula`, `DyadicFormula`, `BracketFormula` |
| **Operators** | Arithmetic operations | `Addition`, `Subtraction`, `Multiplication`, `Division`, `Power` |
| **Functions** | Mathematical functions | `SIN`, `COS`, `ABS`, `SQRT`, `AVE`, `SUM` |
| **Comparisons** | Logical operations | `Equal`, `GreaterThan`, `LessThan` |
| **Types** | Data representations | `DoubleFormula`, `IntegerFormula`, `StringFormula`, `BooleanFormula` |
| **Utils** | Support services | `Cache`, `FunctionUtils`, `ExceptionUtils` |

### Processing Flow

1. **Tokenization**: Input string is broken down into meaningful tokens (numbers, operators, functions, parentheses)
2. **Parsing**: Tokens are organized into an Abstract Syntax Tree (AST) respecting operator precedence
3. **Validation**: Expression tree is validated for syntax errors and type compatibility
4. **Evaluation**: Tree is traversed and calculated recursively from leaves to root
5. **Result**: Final computed value is returned as appropriate Formula type

### Extension Points

- **New Operators**: Extend `ArithmeticFormula` or `DyadicFormula`
- **New Functions**: Implement `AbstractFunction` (SISO or MISO)
- **New Types**: Extend `Formula` base class
- **Custom Logic**: Override calculation methods in formula classes

## 🤝 Contributing

Contributions, issues and feature requests are welcome!

### Development Setup
1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Ensure all tests pass
6. Submit a pull request

## 📝 License

This project is [MIT](LICENSE) licensed.

---

*Furion provides a robust foundation for mathematical expression evaluation in Java applications, with a clean API and extensive feature set suitable for both simple calculations and complex mathematical operations.*
