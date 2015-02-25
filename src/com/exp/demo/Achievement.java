package com.exp.demo;

import cn.bmob.v3.BmobObject;

public class Achievement extends BmobObject {
		private String id;
		private String name;
		private String description;
		private  String  type;
		private int imageId;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public int getImageId() {
			return imageId;
		}
		public void setImageId(int imageId) {
			this.imageId = imageId;
		}
}
