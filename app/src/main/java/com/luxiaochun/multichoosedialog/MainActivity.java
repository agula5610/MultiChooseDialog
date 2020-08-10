package com.luxiaochun.multichoosedialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.luxiaochun.multiselectiondialog.BottomRoadDialog;
import com.luxiaochun.multiselectiondialog.DialogType;
import com.luxiaochun.multiselectiondialog.InfoTabDialog;
import com.luxiaochun.multiselectiondialog.SelectDialogManager;
import com.luxiaochun.multiselectiondialog.base.Node;
import com.luxiaochun.multiselectiondialog.base.RoadNode;
import com.luxiaochun.multiselectiondialog.listener.OnDialogListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private Button normal1;
    private Button normal4;
    private Button normal6;
    private Button bottomDialog;
    private Button infoDialog;
    private SelectDialogManager singleManager;
    private SelectDialogManager multiManager;
    private SelectDialogManager orderManager;
    private BottomRoadDialog dialog;

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
                .setTitleColor(R.color.colorAccent)
                .setThemeColor(R.color.colorAccent)
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
                .setTitleColor(R.color.colorAccent)
                .setThemeColor(R.color.colorAccent)
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
                .setTitleColor(R.color.colorAccent)
                .setThemeColor(R.color.colorAccent)
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
        bottomDialog = findViewById(R.id.bottomDialog);
        infoDialog = findViewById(R.id.infoDialog);
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
        bottomDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<RoadNode> names = new ArrayList();
                RoadNode beaNode = new RoadNode();
                beaNode.setName("海陵区");
                names.add(beaNode);
                RoadNode beaNode1 = new RoadNode();
                beaNode1.setName("医药高新区");
                names.add(beaNode1);
                showDialog(names, new BottomRoadDialog.OnRoadSelectListener() {

                    @Override
                    public void onRoadClick(RoadNode roadNode) {
                        if (roadNode != null) {
                            Toast.makeText(MainActivity.this,
                                    roadNode.getName(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFirstPick(RoadNode roadNode) {
                        // 此处请求接口获取二级目录然后设置到dialog中
                        // ////////////////二级假数据
                        List<RoadNode> secondList = new ArrayList();
                        RoadNode beaNode1 = new RoadNode();
                        beaNode1.setName("青年路（凤凰路--青年路通扬运河桥）");
                        secondList.add(beaNode1);
                        RoadNode beaNode2 = new RoadNode();
                        beaNode2.setName("海陵路（凤凰路--净音寺桥）");
                        secondList.add(beaNode2);
                        RoadNode beaNode3 = new RoadNode();
                        beaNode3.setName("鼓楼路（凤凰路--文昌桥）");
                        secondList.add(beaNode3);
                        RoadNode beaNode4 = new RoadNode();
                        beaNode4.setName("引凤路（凤凰路--福龙桥）");
                        secondList.add(beaNode4);
                        RoadNode beaNode5 = new RoadNode();
                        beaNode5.setName("东风路（凤凰路--东风路通扬运河桥）");
                        secondList.add(beaNode5);
                        RoadNode beaNode6 = new RoadNode();
                        beaNode6.setName("春晖路（凤凰路--济川路）");
                        secondList.add(beaNode6);
                        if (dialog != null) {
                            dialog.setSecondList(secondList);
                        }
                    }

                    @Override
                    public void onSecondPick(RoadNode roadNode) {
                        // 此处请求接口获取三级目录
                        // ////////////////三级假数据
                        List<RoadNode> thirtList = new ArrayList();
                        RoadNode beaNode7 = new RoadNode();
                        beaNode7.setName("通扬运河桥-济川路");
                        thirtList.add(beaNode7);
                        RoadNode beaNode8 = new RoadNode();
                        beaNode8.setName("济川路-永泰路");
                        thirtList.add(beaNode8);
                        RoadNode beaNode9 = new RoadNode();
                        beaNode9.setName("永泰路-梅兰路");
                        thirtList.add(beaNode9);
                        RoadNode beaNode10 = new RoadNode();
                        beaNode10.setName("梅兰路-永辉路");
                        thirtList.add(beaNode10);
                        RoadNode beaNode11 = new RoadNode();
                        beaNode11.setName("永辉路-凤凰路");
                        thirtList.add(beaNode11);
                        if (dialog != null) {
                            dialog.setThirtList(thirtList);
                        }
                    }
                });
            }
        });
        infoDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoTabDialog dialog = new InfoTabDialog(MainActivity.this, 2, null);
                dialog.show();
            }
        });
    }

    private BottomRoadDialog showDialog(List<RoadNode> names,
                                        BottomRoadDialog.OnRoadSelectListener listener) {
        dialog = new BottomRoadDialog(this, names, listener);
        if (!this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }
}
