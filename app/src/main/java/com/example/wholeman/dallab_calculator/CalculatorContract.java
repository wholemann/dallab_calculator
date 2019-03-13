package com.example.wholeman.dallab_calculator;

public interface CalculatorContract {

    interface View {
        void showResult(String result);
        void showToast(String message);
    }

    interface ForwardDisplayInteractionToPresenter {
    }

    interface ForwardInputInteractionToPresenter {
        void onDeleteClick();
        void onNumberClick(int number);
        void onOperatorClick(String operator);
        void onDecimalClick();
        void onEqualsClick();
    }
}
