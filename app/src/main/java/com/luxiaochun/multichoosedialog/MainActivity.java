package com.luxiaochun.multichoosedialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.luxiaochun.multiselectiondialog.DialogType;
import com.luxiaochun.multiselectiondialog.SelectDialogManager;
import com.luxiaochun.multiselectiondialog.base.Node;
import com.luxiaochun.multiselectiondialog.listener.OnDialogListener;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private Button normal1;
    private Button normal4;
    private Button normal6;
    private SelectDialogManager singleManager;
    private SelectDialogManager multiManager;
    private SelectDialogManager orderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        intiDialog();
        initClick();
    }

    private void intiDialog() {
        singleManager = new SelectDialogManager
                .Builder()
                .setContext(MainActivity.this)
                .setTitle("证件类型")
                .setDatas(CardTypeEnum.getDatas())
                .setOnClickListener(new OnDialogListener() {
                    @Override
                    public void onConfirm(List<Node> list) {
                        Toast.makeText(MainActivity.this, list.get(0).getName(), Toast.LENGTH_SHORT).show();
                    }
                })
                .build();
        multiManager = new SelectDialogManager
                .Builder()
                .setContext(MainActivity.this)
                .setTitle("证件类型")
                .setDatas(CardTypeEnum.getDatas())
                .setType(DialogType.SINGLEDEGREE_MULTICHOOSE)
                .setOnClickListener(new OnDialogListener() {
                    @Override
                    public void onConfirm(List<Node> list) {
                        StringBuilder sb = new StringBuilder();
                        for (Node node : list) {
                            sb.append(node.getName() + ",");
                        }
                        Toast.makeText(MainActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                .build();
        orderManager = new SelectDialogManager
                .Builder()
                .setContext(MainActivity.this)
                .setTitle("证件类型")
                .setDatas(CardTypeEnum.getDatas())
                .setType(DialogType.SINGLEDEGREE_ORDER)
                .setOnClickListener(new OnDialogListener() {
                    @Override
                    public void onConfirm(List<Node> list) {
                        StringBuilder sb = new StringBuilder();
                        for (Node node : list) {
                            sb.append(node.getName() + ",");
                        }
                        Toast.makeText(MainActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                .build();
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
                singleManager.show();
            }
        });

        normal4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                multiManager.show();
            }
        });

        normal6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderManager.show();
            }
        });
    }
}
