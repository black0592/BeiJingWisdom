package com.android.beijingwisdom.fragment;

import java.util.ArrayList;
import java.util.List;

import com.android.beijingwisdom.R;
import com.android.beijingwisdom.base.BasePager;
import com.android.beijingwisdom.base.imp.Govaffiairs_Pager;
import com.android.beijingwisdom.base.imp.HomePager;
import com.android.beijingwisdom.base.imp.Newscenter_Pager;
import com.android.beijingwisdom.base.imp.Setting_Pager;
import com.android.beijingwisdom.base.imp.SmartService_Pager;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainMenuFragment extends BaseFragment {
	public static final String TAG = "MainMenuFragment";
	private ViewPager vp_pages;
	private List<BasePager> mpagerlist;
	private RadioGroup rg_guide_title;
	
	@Override
	public View initView() {
		
		View view = View.inflate(mActivity,R.layout.fragment_home_main ,null);
		vp_pages = (ViewPager) view.findViewById(R.id.vp_pages);
		rg_guide_title = (RadioGroup) view.findViewById(R.id.rg_guide_title);
		return view;
	}

	@Override
	public void initData() {
		mpagerlist = new ArrayList<BasePager>();
		
		mpagerlist.add(new HomePager(mActivity));
		mpagerlist.add(new Newscenter_Pager(mActivity));
		mpagerlist.add(new SmartService_Pager(mActivity));
		mpagerlist.add(new Govaffiairs_Pager(mActivity));
		mpagerlist.add(new Setting_Pager(mActivity));
		System.out.println("page���������");
		vp_pages.setAdapter(new PagesAdapter());
		rg_guide_title.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rb_tab_main://��ҳ
					vp_pages.setCurrentItem(0);
					break;

				case R.id.rb_tab_news://��������
					vp_pages.setCurrentItem(1);
					break;
				case R.id.rb_tab_smartservice://�ǻ۷���
					vp_pages.setCurrentItem(2);	
						break;
				case R.id.rb_tab_govaffairs://����
					vp_pages.setCurrentItem(3);
					break;
				case R.id.rb_tab_setting://����
					vp_pages.setCurrentItem(4);
					break;
					
				}
			}
		});
		vp_pages.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				//initviewĬ�ϻ������һ��ҳ�棬Ϊ��ʡ�û��������û������Ӧҳ���ڽ��м���
				BasePager pager = mpagerlist.get(position);
				pager.initData();
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	class PagesAdapter extends PagerAdapter{
		
		@Override
		public int getCount() {
			return mpagerlist.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			
			return view == obj;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			Log.i(TAG, "instantiateItem");
			
			BasePager bpager = mpagerlist.get(position);
			View view = bpager.curView;//��ȡ��ǰҳ�����Ĳ���
			container.addView(view);
			return view;
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}
}
