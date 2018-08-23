package com.luxiaochun.multichoosedialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.luxiaochun.multiselectiondialog.DialogType;
import com.luxiaochun.multiselectiondialog.MultiSelectionDialogManager;
import com.luxiaochun.multiselectiondialog.base.Node;
import com.luxiaochun.multiselectiondialog.listener.OnItemClickListener;

public class MainActivity extends AppCompatActivity {
    private Button normal1;
    private Button normal2;
    private Button normal3;
    private Button normal4;
    private Button normal5;
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
        normal2 = findViewById(R.id.normal2);
        normal3 = findViewById(R.id.normal3);
        normal4 = findViewById(R.id.normal4);
        normal5 = findViewById(R.id.normal5);
        normal6 = findViewById(R.id.normal6);
    }

    private void initClick() {
        normal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MultiSelectionDialogManager
                        .Builder()
                        //当前Activity
                        .setActivity(MainActivity.this)
                        .setTitle("证件类型")
                        .setCanceledOnTouchOutside(true)
                        .setDatas(CardTypeEnum.getDatas())
                        .setType(DialogType.SINGLE)
                        .setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onClick(Node node, int position) {
                            }
                        })
                        .build().show();
            }
        });
        normal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MultiSelectionDialogManager
                        .Builder()
                        //当前Activity
                        .setActivity(MainActivity.this)
                        .setTitle("证件类型")
                        .setCanceledOnTouchOutside(true)
                        .setDatas(CardTypeEnum.getDatas())
                        .setType(DialogType.SINGLE_BOTTOM)
                        .setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onClick(Node node, int position) {
                            }
                        })
                        .build().show();
            }
        });
        normal3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        normal4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        normal5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        normal6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
