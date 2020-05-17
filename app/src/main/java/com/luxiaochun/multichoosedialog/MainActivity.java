package com.luxiaochun.multichoosedialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.luxiaochun.multiselectiondialog.DialogType;
import com.luxiaochun.multiselectiondialog.SelectDialogManager;
import com.luxiaochun.multiselectiondialog.listener.OnDialogListener;


public class MainActivity extends AppCompatActivity {
    private Button normal1;
    private Button normal4;
    private Button normal6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initClick();
    }

    private void initView() {
        normal1 = findViewById(R.id.normal1);
        normal4 = findViewById(R.id.normal4);
        normal6 = findViewById(R.id.normal6);
    }

    private void initClick() {
        normal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SelectDialogManager
                        .Builder()
                        .setTitle("证件类型")
                        .setDatas(CardTypeEnum.getDatas())
                        .setOnClickListener(new OnDialogListener() {
                            @Override
                            public void onConfirm(Object list) {

                            }
                        })
                        .build().show();
            }
        });

        normal4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SelectDialogManager
                        .Builder()
                        .setTitle("证件类型")
                        .setDatas(CardTypeEnum.getDatas())
                        .setType(DialogType.SINGLEDEGREE_MULTICHOOSE)
                        .setOnClickListener(new OnDialogListener() {
                            @Override
                            public void onConfirm(Object list) {

                            }
                        })
                        .build().show();
            }
        });

        normal6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SelectDialogManager
                        .Builder()
                        .setTitle("证件类型")
                        .setDatas(CardTypeEnum.getDatas())
                        .setType(DialogType.SINGLEDEGREE_ORDER)
                        .setOnClickListener(new OnDialogListener() {
                            @Override
                            public void onConfirm(Object list) {

                            }
                        })
                        .build().show();
            }
        });
    }
}
