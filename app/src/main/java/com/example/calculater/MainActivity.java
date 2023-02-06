package com.example.calculater;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import javax.script.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, add, sub, div, mul, clear, equal, point, sq;
    private TextView tv, ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initAction();

    }

    private void initAction() {
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        add.setOnClickListener(this);
        sub.setOnClickListener(this);
        div.setOnClickListener(this);
        mul.setOnClickListener(this);
        point.setOnClickListener(this);
        sq.setOnClickListener(this);

        clear.setOnClickListener(v -> {
            tv.setText("0");
            ans.setText("");
        });

        equal.setOnClickListener(this);


    }

    private void initViews() {
        btn0 = findViewById(R.id.button_0);
        btn1 = findViewById(R.id.button_1);
        btn2 = findViewById(R.id.button_2);
        btn3 = findViewById(R.id.button_3);
        btn4 = findViewById(R.id.button_4);
        btn5 = findViewById(R.id.button_5);
        btn6 = findViewById(R.id.button_6);
        btn7 = findViewById(R.id.button_7);
        btn8 = findViewById(R.id.button_8);
        btn9 = findViewById(R.id.button_9);
        point = findViewById(R.id.button_point);

        add = findViewById(R.id.button_add);
        sub = findViewById(R.id.button_sub);
        div = findViewById(R.id.div);
        mul = findViewById(R.id.button_mul);
        sq = findViewById(R.id.button_power);

        clear = findViewById(R.id.button_clear);
        equal = findViewById(R.id.button_equal);

        tv = findViewById(R.id.result_text_view);
        ans = findViewById(R.id.ans);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_equal: {
                String s = ans.getText().toString() + tv.getText().toString();
                String s2 = s.replace("×", "*");
                s2 = s2.replace("÷", "/");
                try {
                    ScriptEngineManager mgr = new ScriptEngineManager();
                    ScriptEngine engine = mgr.getEngineByName("rhino");
                    Object a = engine.eval(s2);
                    String r = s + "=" + a;
                    tv.setText(r);
                    ans.setText("");
                } catch (ScriptException e) {
                    e.printStackTrace();
                }
            }
            break;
            case R.id.button_sub:
            case R.id.button_add:
            case R.id.button_mul:
            case R.id.div: {
                if (tv.getText().toString().equals("0"))
                    return;
                String s = ans.getText().toString()
                        + tv.getText().toString()
                        + ((Button) v).getText().toString();
                tv.setText("");
                ans.setText(s);
            }
            break;
            case R.id.button_power:
                if (tv.getText().toString().equals("0"))
                    return;
                String s = ans.getText().toString()
                        + "("+tv.getText().toString()+"^2)";
                tv.setText("");
                ans.setText(s);
                break;
            default: {
                s = (tv.getText().toString().equals("0") ? "" : tv.getText().toString())
                        + ((Button) v).getText().toString();
                tv.setText(s);
            }
            break;
        }
    }
}