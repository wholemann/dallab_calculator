package com.example.wholeman.dallab_calculator;

import org.javia.arity.Symbols;
import org.javia.arity.SyntaxException;

public class Calculation {

    private final Symbols symbols;
    private CalculationResult calcutaionResult;
    private static String currentExpression;

    public Calculation() {
        symbols = new Symbols();
    }

    interface CalculationResult {
        void onExpressionChange(String result, Boolean successful);
    }

    public void setCalculationResultListener(CalculationResult calculationResult ) {
        this.calcutaionResult = calculationResult;
        currentExpression = "";
    }

    public void deleteExpression() {

        if(currentExpression.equals("")) {
            calcutaionResult.onExpressionChange("Invalid expression", false);
        }

        currentExpression = "";
        calcutaionResult.onExpressionChange(currentExpression, true);

    }

    public void appendNumber(String number) {
        if (currentExpression.startsWith("0") && number.equals(0)){
            calcutaionResult.onExpressionChange("Invalid expression", false);

        } else {
            if (currentExpression.length() <= 16) {
                currentExpression += number;
                calcutaionResult.onExpressionChange(currentExpression, true);

            } else {
                calcutaionResult.onExpressionChange("Expression too long", false);
            }
        }

    }

    public void appendOperator(String operator) {
        if (validateExpression(currentExpression)) {
            currentExpression += operator;
            calcutaionResult.onExpressionChange(currentExpression, true);
        }
    }

    public void appendDecimal() {
        if (validateExpression(currentExpression)) {
            currentExpression += ".";
            calcutaionResult.onExpressionChange(currentExpression, true);
        }
    }

    public void performEvaluation() {
        if (validateExpression(currentExpression)) {
            try {
                Double result = symbols.eval(currentExpression);
                currentExpression = Double.toString(result);
                calcutaionResult.onExpressionChange(currentExpression, true);
            } catch (SyntaxException e) {
                calcutaionResult.onExpressionChange("Invalid expression", false);
                e.printStackTrace();
            }
        }
    }

    public boolean validateExpression(String expression) {
        if (expression.endsWith("*") || expression.endsWith("/") || expression.endsWith("+") || expression.endsWith("-")) {
            calcutaionResult.onExpressionChange("Invalid expression", false);
            return false;

        } else if (expression.equals("")) {
            calcutaionResult.onExpressionChange("Empty expression", false);
            return false;

        } else if (expression.length() > 16) {
            calcutaionResult.onExpressionChange("Expression too long", false);
            return false;

        } else
            return true;
    }

}
