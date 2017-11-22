package com.tzl.android_collection.common.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tianzl on 2017/11/14.
 */

public class UserInfo implements Parcelable {

	private String iconUrl;
	private String name;
	private long lendTime;
	private int loginState;
	

	@Override
	public String toString() {
		return "UserInfo [iconUrl=" + iconUrl + ", name=" + name
				+ ", lendTime=" + lendTime + ", loginState=" + loginState + "]";
	}


	public UserInfo() {
	}


	public UserInfo(String iconUrl, String name, long lendTime) {
		this.iconUrl = iconUrl;
		this.name = name;
		this.lendTime = lendTime;
	}
	

	public UserInfo(String iconUrl, String name, long lendTime, int loginState) {
		super();
		this.iconUrl = iconUrl;
		this.name = name;
		this.lendTime = lendTime;
		this.loginState = loginState;
	}


	public String getIconUrl() {

		return iconUrl;
	}

	public void setLoginState(int loginState) {
		this.loginState = loginState;
	}
	
	public int getLoginState() {
		return loginState;
	}

	public long getLendTime() {
		return lendTime;
	}

	public void setLendTime(long lendTime) {
		this.lendTime = lendTime;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserInfo(String iconUrl, String name) {

		this.iconUrl = iconUrl;
		this.name = name;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.iconUrl);
		dest.writeString(this.name);
		dest.writeLong(this.lendTime);
		dest.writeInt(this.loginState);
	}

	protected UserInfo(Parcel in) {
		this.iconUrl = in.readString();
		this.name = in.readString();
		this.lendTime = in.readLong();
		this.loginState = in.readInt();
	}

	public static final Parcelable.Creator<UserInfo> CREATOR = new Parcelable.Creator<UserInfo>() {
		@Override
		public UserInfo createFromParcel(Parcel source) {
			return new UserInfo(source);
		}

		@Override
		public UserInfo[] newArray(int size) {
			return new UserInfo[size];
		}
	};
}
