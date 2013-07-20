package com.deepin.traveltimes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * @author ycl
 * 
 */
public class ImgAll extends ListActivity {
	// private List<String> data = new ArrayList<String>();
	private List<Map<String, Object>> mData;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mData = getData();
		SimpleAdapter adapter = new SimpleAdapter(this,getData(),R.layout.imgall,
				new String[]{"info","img"},
				new int[]{R.id.info,R.id.img});
		setListAdapter(adapter);
	}

	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("info", "democoffee,�ٶ��Ʊ�̴�������ĺܾ��ʣ�����Ŷ~~");
		map.put("img", R.drawable.imgtest1);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("info", "��֮�ȵĹ�����Χ��ĺܺ�ѽ~~~");
		map.put("img", R.drawable.imgtest2);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("info", "��֮�ȵ��¹�˾Ŷ~~  �߶˴����ϵ���~~~");
		map.put("img", R.drawable.imgtest4);
		list.add(map);
		
		return list;
	}
	
	
	// ListView ��ĳ�ѡ�к���߼�
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		showInfo();
	}
	
	/**
	 * listview�е�����������Ի���
	 */
	public void showInfo(){
		new AlertDialog.Builder(this)
		.setTitle("ʱ������")
		.setMessage("����ʱ������֣��û�����峺......")
		.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		})
		.show();
		
	}
	
	
	
	
}
