package com.shenzhongjia.testmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;

import com.shenzhongjia.timesmoke.adapter.StatusExpandAdapter;
import com.shenzhongjia.timesmoke.entity.ChildStatusEntity;
import com.shenzhongjia.timesmoke.entity.GroupStatusEntity;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends Activity {

	private ExpandableListView expandlistView;
	private StatusExpandAdapter statusAdapter;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;
		expandlistView = (ExpandableListView) findViewById(R.id.expandlist);
		initExpandListView();
	}

	/**
	 * 初始化可拓展列表
	 */
	private void initExpandListView() {
		statusAdapter = new StatusExpandAdapter(context, getListData());
		expandlistView.setAdapter(statusAdapter);
		expandlistView.setGroupIndicator(null); // 去掉默认带的箭头
		expandlistView.setSelection(0);// 设置默认选中项
		
		// 遍历所有group,将所有项设置成默认展开
		int groupCount = expandlistView.getCount();
		for (int i = 0; i < groupCount; i++) {
			expandlistView.expandGroup(i);
		}

		expandlistView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				// TODO Auto-generated method stub
				expandlistView.collapseGroup(groupPosition);
				//expandlistView.expandGroup(groupPosition);
				return false;
			}
		});
	}

	@Override
	public void onBackPressed() 
	{
		
		//暂停应用，保存当前Activity的状态
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.addCategory(Intent.CATEGORY_HOME);
		startActivity(intent);
	}
	
	private List<GroupStatusEntity> getListData() {
		List<GroupStatusEntity> groupList;
		String[] strArray = new String[] { "10月22日", "10月23日", "10月25日" };
		String[][] childTimeArray = new String[][] {
				{ "俯卧撑十次", "仰卧起坐二十次", "浏览CSDN", "刷OJ水题" },
				{ "卖肉松饼","上单天使","么么哒" },
				{ "睡觉~","爱生活~","仰卧起坐二十次","水电费" } };
		groupList = new ArrayList<GroupStatusEntity>();
		for (int i = 0; i < strArray.length; i++) {
			GroupStatusEntity groupStatusEntity = new GroupStatusEntity();
			groupStatusEntity.setGroupName(strArray[i]);

			List<ChildStatusEntity> childList = new ArrayList<ChildStatusEntity>();

			for (int j = 0; j < childTimeArray[i].length; j++) {
				ChildStatusEntity childStatusEntity = new ChildStatusEntity();
				childStatusEntity.setCompleteTime(childTimeArray[i][j]);
				childStatusEntity.setIsfinished(true);
				childList.add(childStatusEntity);
			}

			groupStatusEntity.setChildList(childList);
			groupList.add(groupStatusEntity);
		}
		return groupList;
	}
}