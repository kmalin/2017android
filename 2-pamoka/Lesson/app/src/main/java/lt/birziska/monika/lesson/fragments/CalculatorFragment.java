package lt.birziska.monika.lesson.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import lt.birziska.monika.lesson.R;

public class CalculatorFragment extends Fragment {

    EditText firstInput;
    EditText secondInput;
    TextView resultTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.calculator_fragment, container, false);

        //set result text view
        resultTextView = (TextView) rootView.findViewById(R.id.resultTextView);
        //set inputs
        firstInput = (EditText) rootView.findViewById(R.id.firstInput);
        secondInput = (EditText) rootView.findViewById(R.id.secondInput);

        //set button listeners
        Button addBtn =  (Button)rootView.findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new AddButtonClick());
        Button subtractBtn =  (Button)rootView.findViewById(R.id.subtractBtn);
        subtractBtn.setOnClickListener(new SubtractButtonClick());
        Button multiplyBtn =  (Button)rootView.findViewById(R.id.multiplyBtn);
        multiplyBtn.setOnClickListener(new MultiplyButtonClick());
        Button divideBtn =  (Button)rootView.findViewById(R.id.divideBtn);
        divideBtn.setOnClickListener(new DivideButtonClick());

        return rootView;
    }

    public void hideKeyboard(){
        //hide keyboard
        Activity activity = getActivity();
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    class AddButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            hideKeyboard();

            String firstValue = firstInput.getText().toString();
            String secondValue = secondInput.getText().toString();

            //fill in the implementation here
            //to set the value of the TextView component use resultTextView.setText method
        }
    }

    class SubtractButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            hideKeyboard();

            String firstValue = firstInput.getText().toString();
            String secondValue = secondInput.getText().toString();

            //fill in the implementation here
            //to set the value of the TextView component use resultTextView.setText method
        }
    }

    class MultiplyButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String firstValue = firstInput.getText().toString();
            String secondValue = secondInput.getText().toString();

            //fill in the implementation here
            //to set the value of the TextView component use resultTextView.setText method
        }
    }

    class DivideButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            hideKeyboard();

            String firstValue = firstInput.getText().toString();
            String secondValue = secondInput.getText().toString();

            //fill in the implementation here
            //to set the value of the TextView component use resultTextView.setText method
        }
    }
}