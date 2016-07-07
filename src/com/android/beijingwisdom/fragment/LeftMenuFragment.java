package com.android.beijingwisdom.fragment;

import java.util.ArrayList;

import com.android.beijingwisdom.R;
import com.android.beijingwisdom.activity.HomeActivity;
import com.android.beijingwisdom.base.imp.Newscenter_Pager;
import com.android.beijingwisdom.domain.NewsMenu.NewsLeftMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
/**
 * ��ҳ��activity
 * @author feng
 *
 */
public class LeftMenuFragment extends BaseFragment {
	private ArrayList<NewsLeftMenu> newsLeftitems;
	@ViewInject(R.id.lv_leftmenu_items)
	private ListView lv_leftmenu_items;
	private int currentPos;
	LeftMenuAdapter adapter;
	
	@Override
	public View initView() {
		adapter = new LeftMenuAdapter();
		View view = View.inflate(mActivity,R.layout.fragment_home_left ,null);
		//lv_leftmenu_items = (ListView) view.findViewById(R.id.lv_leftmenu_items);
		ViewUtils.inject(this, view);
		lv_leftmenu_items.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				currentPos = position;//����ѡ�е�λ��
				adapter.notifyDataSetChanged();//֪ͨѡ��������
				//��������
				switchLeft();
				//������framelayout����
				setCurrentDetail(position);
			}

			
			
		});
		return view;
	}
	//���������
	public void switchLeft() {
		HomeActivity mainUI = (HomeActivity) mActivity;
		mainUI.getSlidingMenu().toggle();
	}
	//��ȡ��ǰ�������������ҳ
	public void setCurrentDetail(int position){
		//��ȡ�������Ķ���
		HomeActivity mainUI = (HomeActivity) mActivity;
		//��ȡMainMenuFragment
		MainMenuFragment mainFragment = (MainMenuFragment) mainUI.getMenuFragment(mainUI.TAG_MAIN_MENU);
		//��ȡ�������Ķ���,����Ϊ1��
		Newscenter_Pager newscenter = (Newscenter_Pager) mainFragment.getBasePager(1);
		//����������������ҳ
		newscenter.setCurrentDetailPager(position);
	}
	
	@Override
	public void initData() {
		
	}
	class LeftMenuAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return newsLeftitems.size();
		}

		@Override
		public NewsLeftMenu getItem(int position) {
			return newsLeftitems.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(mActivity, R.layout.item_list_menu_left, null);
			TextView  item = (TextView) view.findViewById(R.id.tv_menu_left);
			item.setText(getItem(position).title);
			//Ĭ��ѡ�е�һ��ͼƬ������ʾ��ɫ
			if(currentPos == position){
				item.setEnabled(true);
			}else{
				item.setEnabled(false);//����Ĭ����ʾ��ɫ
			}
			
			return view;
		}
		
	}
	public void setMenuData(ArrayList<NewsLeftMenu> data) {
		currentPos = 0;
		newsLeftitems = data;
		lv_leftmenu_items.setAdapter(adapter);
	}

}
