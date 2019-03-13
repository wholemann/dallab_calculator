package com.example.wholeman.dallab_calculator;

public class CalculatorPresenter implements CalculatorContract.ForwardDisplayInteractionToPresenter,
        CalculatorContract.ForwardInputInteractionToPresenter, Calculation.CalculationResult {

    private CalculatorContract.View calculateResult;
    private Calculation calculation;

    public CalculatorPresenter(CalculatorContract.View calculateResult) {
        this.calculateResult = calculateResult;
        calculation = new Calculation();
        calculation.setCalculationResultListener(this);
    }

    @Override
    public void onDeleteClick() {
        calculation.deleteExpression();
    }

    @Override
    public void onNumberClick(int number) {
        calculation.appendNumber(Integer.toString(number));
    }

    @Override
    public void onOperatorClick(String operator) {
        calculation.appendOperator(operator);
    }

    @Override
    public void onDecimalClick() {
        calculation.appendDecimal();
    }

    @Override
    public void onEqualsClick() {
        calculation.performEvaluation();
    }

    @Override
    public void onExpressionChange(String result, Boolean successful) {
        if (successful){
            calculateResult.showResult(result);
        } else {
            calculateResult.showToast(result);
        }
    }
}
