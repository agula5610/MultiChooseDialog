package com.luxiaochun.multiselectiondialog.base;


public class RoadNode {
	private String name;
	private boolean check =false;
	private int level;
	private RoadBean bean;

	public RoadBean getBean() {
		return bean;
	}

	public void setBean(RoadBean bean) {
		this.bean = bean;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}


	private class RoadBean {
		private String name;
		private String id;
		private String xy;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getXy() {
			return xy;
		}
		public void setXy(String xy) {
			this.xy = xy;
		}
	}
}
