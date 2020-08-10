package com.luxiaochun.multiselectiondialog;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luxiaochun.multiselectiondialog.base.RoadNode;
import com.luxiaochun.multiselectiondialog.utils.ScreenUtil;


public class InfoTabDialog extends Dialog {

	private Activity mActivity;
	private int currentLevel = 1;
	private RoadNode bean;
	private TextView dialog_roader_zlz;
	private ImageButton dialog_roader_cancel;
	private LinearLayout firstRoaderLl;
	private View firstRoaderBottomLine;
	private LinearLayout secondRoaderLl;
	private View secondRoaderBottomLine;
	// 一级路长特有
	private LinearLayout firstClassRoader;
	private TextView dialog_roader_person;// 街道人员
	private TextView dialog_roader_chengguan;// 街道城管
	private TextView dialog_roader_zhongdui;// 高新区城管执法中队
	// 二级路长特有
	private LinearLayout secondClassRoader;
	private TextView dialog_roader_community;// 社区人员
	private TextView dialog_roader_digitcg;// 数字城管
	private TextView dialog_roader_weisheng;// 环境卫生

	public InfoTabDialog(Context context) {
		super(context);
	}

	public InfoTabDialog(Activity activity, int currentLevel, RoadNode bean) {
		super(activity, R.style.dialog_full);
		mActivity = activity;
		this.currentLevel = currentLevel;
		this.bean = bean;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view = getLayoutInflater().inflate(R.layout.info_tab_dialog, null);

		int needWidth = (int) (ScreenUtil.getScreenWidth(mActivity) * 0.86);

		setContentView(view, new ViewGroup.LayoutParams(needWidth,
				ViewGroup.LayoutParams.WRAP_CONTENT));

		// 设置window的高度和位置
		Window window = getWindow();
		window.setGravity(Gravity.CENTER);
		window.setWindowAnimations(R.style.main_menu_animstyle);
		window.setLayout(needWidth, WindowManager.LayoutParams.WRAP_CONTENT);
		setCanceledOnTouchOutside(false);

		initView();
		initData();
	}

	private void initView() {
		dialog_roader_zlz =  findViewById(R.id.dialog_roader_zlz);
		dialog_roader_cancel =  findViewById(R.id.dialog_roader_cancel);
		firstRoaderLl =  findViewById(R.id.firstRoaderLl);
		firstRoaderBottomLine =  findViewById(R.id.firstRoaderBottomLine);
		firstRoaderLl.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				change2Roader1();
			}
		});
		secondRoaderLl =  findViewById(R.id.secondRoaderLl);
		secondRoaderLl.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				change2Roader2();
			}
		});
		secondRoaderBottomLine =  findViewById(R.id.secondRoaderBottomLine);
		dialog_roader_cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		//一级
		firstClassRoader = findViewById(R.id.firstClassRoader);
		dialog_roader_person= findViewById(R.id.dialog_roader_person);
		dialog_roader_chengguan= findViewById(R.id.dialog_roader_chengguan);
		dialog_roader_zhongdui= findViewById(R.id.dialog_roader_zhongdui);
		//二级
		secondClassRoader = findViewById(R.id.secondClassRoader);
		dialog_roader_community= findViewById(R.id.dialog_roader_community);
		dialog_roader_digitcg= findViewById(R.id.dialog_roader_digitcg);
		dialog_roader_weisheng= findViewById(R.id.dialog_roader_weisheng);

		String str = "李俊表   <font color='#1d82d1'>13705261818</font>"
				+ "<br/>陈小平   <font color='#1d82d1'>15751152861</font>";
		dialog_roader_zlz.setTextSize(16);
		dialog_roader_zlz.setText(Html.fromHtml(str));
	}

	private void initData() {
		// 数据初始化
		if (currentLevel == 1) {
			change2Roader1();
		} else if (currentLevel == 2) {
			change2Roader2();
		}
	}

	private void change2Roader1() {
		firstRoaderBottomLine.setVisibility(View.GONE);
		if (currentLevel == 1){
			secondRoaderLl.setVisibility(View.GONE);
		} else if (currentLevel == 2){
			firstRoaderLl.setBackgroundColor(getContext().getResources().getColor(
					R.color.dialog_title_bgc));
			secondRoaderLl.setBackgroundColor(getContext().getResources().getColor(
					R.color.white));
			secondRoaderBottomLine.setVisibility(View.VISIBLE);
		}
	}

	private void change2Roader2() {
		secondRoaderLl.setVisibility(View.VISIBLE);
		firstRoaderBottomLine.setVisibility(View.VISIBLE);
		firstRoaderLl.setBackgroundColor(getContext().getResources().getColor(
				R.color.white));
		secondRoaderLl.setBackgroundColor(getContext().getResources().getColor(
				R.color.dialog_title_bgc));
		secondRoaderBottomLine.setVisibility(View.GONE);
	}
}