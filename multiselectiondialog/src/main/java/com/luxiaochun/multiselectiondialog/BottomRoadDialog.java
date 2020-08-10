package com.luxiaochun.multiselectiondialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.luxiaochun.multiselectiondialog.base.RoadNode;
import com.luxiaochun.multiselectiondialog.utils.ScreenUtil;

import java.util.ArrayList;
import java.util.List;

public class BottomRoadDialog extends Dialog {

	private List<RoadNode> firstList;
	private List<RoadNode> secondList = new ArrayList();
	private List<RoadNode> thirtList = new ArrayList();
	private RoadAdapter dialogAdapter;
	private OnRoadSelectListener mListener;
	private Activity mActivity;
	private int currentLevel = 1;
	private Button dialog_cancel;
	private Button dialog_confirm;

	private LinearLayout firstRoad;
	private LinearLayout secondRoad;
	private LinearLayout dailogDayu2;
	private LinearLayout thirdRoad;
	private TextView firstRoadTv;
	private TextView secondRoadTv;
	private TextView thirdRoadTv;
	private View firstRoadLine1;
	private View secondRoadLine1;
	private View thirdRoadLine1;
	private View firstRoadLine2;
	private View secondRoadLine2;
	private View thirdRoadLine2;

	public interface OnRoadSelectListener {
		void onRoadClick(RoadNode roadNode);

		void onFirstPick(RoadNode roadNode);

		void onSecondPick(RoadNode roadNode);
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setSecondList(List<RoadNode> secondList) {
		this.secondList = secondList;
		change2Level2();
	}

	public void setThirtList(List<RoadNode> thirtList) {
		this.thirtList = thirtList;
		change2Level3();
	}

	public BottomRoadDialog(Activity activity, List<RoadNode> names,
			OnRoadSelectListener listener) {
		super(activity, R.style.dialog_full);
		mActivity = activity;
		mListener = listener;
		firstList = names;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view = getLayoutInflater().inflate(R.layout.luxiaochun_bottom_dialog,
				null);
		int needHeight = (int) (ScreenUtil.getScreenHeigth(mActivity) * 0.48);
		setContentView(view, new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, needHeight));

		// 设置window的高度和位置
		Window window = getWindow();
		window.setGravity(Gravity.BOTTOM);
		window.setWindowAnimations(R.style.main_menu_animstyle);
		window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, needHeight);
		setCanceledOnTouchOutside(false);

		initView();
	}

	private void initView() {
		dialog_cancel =  findViewById(R.id.dialog_cancel);
		dialog_confirm =  findViewById(R.id.dialog_confirm);

		firstRoad =  findViewById(R.id.firstRoad);
		secondRoad =  findViewById(R.id.secondRoad);
		dailogDayu2 =  findViewById(R.id.dailogDayu2);
		thirdRoad =  findViewById(R.id.thirdRoad);
		firstRoadTv =  findViewById(R.id.firstRoadTv);
		secondRoadTv =  findViewById(R.id.secondRoadTv);
		thirdRoadTv =  findViewById(R.id.thirdRoadTv);
		firstRoadLine1 =  findViewById(R.id.firstRoadLine1);
		secondRoadLine1 =  findViewById(R.id.secondRoadLine1);
		thirdRoadLine1 =  findViewById(R.id.thirdRoadLine1);
		firstRoadLine2 =  findViewById(R.id.firstRoadLine2);
		secondRoadLine2 =  findViewById(R.id.secondRoadLine2);
		thirdRoadLine2 =  findViewById(R.id.thirdRoadLine2);

		dialogAdapter = new RoadAdapter(firstList);
		ListView listView =  findViewById(R.id.road_list);
		listView.setAdapter(dialogAdapter);

		dialog_cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		dialog_confirm.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				RoadNode roadNode = dialogAdapter.getCheckedNode();
				if (roadNode == null){
					Toast.makeText(mActivity, "请选择一条道路", Toast.LENGTH_SHORT).show();
				} else {
					mListener.onRoadClick(roadNode);
					dismiss();
				}
			}
		});

		firstRoad.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				change2Level1();
			}
		});
		secondRoad.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				change2Level2();
			}
		});
	}

	private void change2Level1() {
		currentLevel = 1;
		dialog_confirm.setVisibility(View.INVISIBLE);
		firstRoadTv.setTextColor(getContext().getResources().getColor(R.color.dialog_btn_bgc));
		firstRoadLine1.setVisibility(View.GONE);
		firstRoadLine2.setVisibility(View.VISIBLE);

		secondRoad.setVisibility(View.GONE);
		dailogDayu2.setVisibility(View.GONE);
		thirdRoad.setVisibility(View.GONE);
		if (dialogAdapter != null) {
			dialogAdapter.setSecondCheckedNode(null);
			dialogAdapter.setDatas(firstList);
		}
	}

	protected void change2Level2() {
		currentLevel = 2;
		dialog_confirm.setVisibility(View.VISIBLE);
		firstRoadTv.setTextColor(getContext().getResources().getColor(R.color.black));
		firstRoadLine1.setVisibility(View.VISIBLE);
		firstRoadLine2.setVisibility(View.GONE);

		secondRoad.setVisibility(View.VISIBLE);
		secondRoadTv
				.setTextColor(getContext().getResources().getColor(R.color.dialog_btn_bgc));
		secondRoadLine1.setVisibility(View.GONE);
		secondRoadLine2.setVisibility(View.VISIBLE);
		dailogDayu2.setVisibility(View.VISIBLE);

		thirdRoad.setVisibility(View.GONE);

		if (dialogAdapter != null) {
			dialogAdapter.setThirdCheckedNode(null);
			dialogAdapter.setDatas(secondList);
		}
	}

	protected void change2Level3() {
		currentLevel = 3;
		dialog_confirm.setVisibility(View.VISIBLE);
		firstRoadTv.setTextColor(getContext().getResources().getColor(R.color.black));
		firstRoadLine1.setVisibility(View.VISIBLE);
		firstRoadLine2.setVisibility(View.GONE);

		secondRoadTv.setTextColor(getContext().getResources().getColor(R.color.black));
		secondRoadLine1.setVisibility(View.VISIBLE);
		secondRoadLine2.setVisibility(View.GONE);

		thirdRoad.setVisibility(View.VISIBLE);
		thirdRoadTv.setTextColor(getContext().getResources().getColor(R.color.dialog_btn_bgc));
		thirdRoadLine1.setVisibility(View.GONE);
		thirdRoadLine2.setVisibility(View.VISIBLE);

		if (dialogAdapter != null) {
			dialogAdapter.setDatas(thirtList);
		}
	}

	private class RoadAdapter extends BaseAdapter {
		private List<RoadNode> datas;
		private Viewholder viewholder;
		private LayoutInflater layoutInflater;
		private RoadNode secondCheckedNode; // 记录二级选中的项目
		private RoadNode thirdCheckedNode; // 记录三级级选中的项目

		public void setSecondCheckedNode(RoadNode secondCheckedNode) {
			this.secondCheckedNode = secondCheckedNode;
		}

		public void setThirdCheckedNode(RoadNode thirdCheckedNode) {
			this.thirdCheckedNode = thirdCheckedNode;
		}

		public RoadAdapter(List<RoadNode> datas) {
			this.datas = datas;
			this.layoutInflater = mActivity.getLayoutInflater();
		}

		public void setDatas(List<RoadNode> datas) {
			this.datas = datas;
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return datas.size();
		}

		@Override
		public Object getItem(int position) {
			return datas.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (null == convertView) {
				viewholder = new Viewholder();
				convertView = layoutInflater.inflate(R.layout.bottom_dialog_item,
						null);
				viewholder.rb =  convertView
						.findViewById(R.id.dialog_radio_btn);
				viewholder.tv =  convertView
						.findViewById(R.id.dialog_road_tv);
				viewholder.subLl =  convertView
						.findViewById(R.id.dialog_road_sub);
				convertView.setTag(viewholder);
			} else {
				viewholder = (Viewholder) convertView.getTag();
			}
			final RoadNode node = datas.get(position);
			if (currentLevel == 1) {
				viewholder.rb.setVisibility(View.GONE);
				viewholder.subLl.setVisibility(View.VISIBLE);
			} else if (currentLevel == 2) {
				viewholder.rb.setVisibility(View.VISIBLE);
				viewholder.subLl.setVisibility(View.VISIBLE);
			} else if (currentLevel == 3) {
				viewholder.rb.setVisibility(View.VISIBLE);
				viewholder.subLl.setVisibility(View.GONE);
			}
			viewholder.tv.setText(node.getName());
			if (node.isCheck()) {
				viewholder.rb.setChecked(true);
			} else {
				viewholder.rb.setChecked(false);
			}
			viewholder.rb.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (currentLevel == 2) {
						setRadioChecked(node, secondCheckedNode);
						secondCheckedNode = node;
					} else if (currentLevel == 3) {
						setRadioChecked(node, thirdCheckedNode);
						thirdCheckedNode = node;
					}
				}
			});
			viewholder.subLl.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					if (currentLevel == 1) {
						secondRoadTv.setText(node.getName());
						if (mListener != null) {
							mListener.onFirstPick(node);
						}
					} else if (currentLevel == 2) {
						thirdRoadTv.setText(node.getName());
						if (mListener != null) {
							mListener.onSecondPick(node);
						}
					}
				}
			});
			return convertView;
		}

		public RoadNode getCheckedNode() {
			if (currentLevel == 2) {
				return secondCheckedNode;
			} else if (currentLevel == 3) {
				return thirdCheckedNode;
			}
			return null;
		}

		protected void setRadioChecked(final RoadNode node, RoadNode checkedNode) {
			if (checkedNode != null) {
				checkedNode.setCheck(false);
			}
			node.setCheck(true);
			notifyDataSetChanged();
		}
	}

	public class Viewholder {
		public RadioButton rb;
		public TextView tv;
		public LinearLayout subLl;

	}
}
