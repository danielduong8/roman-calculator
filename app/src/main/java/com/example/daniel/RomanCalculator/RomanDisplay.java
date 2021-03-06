package com.example.daniel.RomanCalculator;

import android.widget.TextView;

import java.util.Observable;

public class RomanDisplay extends Display {
    public RomanDisplay(TextView view) {
        super(view);
    }

    @Override
    public void update(Observable o, Object arg) {
        Expression exp = (Expression) o;
        if (exp.getResult() != null) {
            view.setText(exp.getResult().getRoman());
        }
        else {
            view.setText(exp.getCurrentRoman());
        }
    }
}
