package com.deepin.traveltimes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
		SimpleAdapter adapter = new SimpleAdapter(this, getData(),
				R.layout.timesline, new String[] { "img", "info", "img_time",
						"text_time", "text_coordinates", "img_like",
						"img_share" }, new int[] { R.id.img, R.id.info,
						R.id.img_time, R.id.text_time, R.id.text_coordinates,
						R.id.img_like, R.id.img_share });
		setListAdapter(adapter);

	}
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String[] infos = {
				"democoffee,�ٶ��Ʊ�̴�������ĺܾ��ʣ�����Ŷ~~",
				"��Ϊһ���ˣ����������ĵط�����ս������ô��ܲ��ҡ���Ϊһ�����ߣ��������ְҵ������������һ��ս�������������У�����������ˡ�",
				"��Ȼ��һֱ˵������Ϊ���Ϊս�ؼ��߲ų�Ϊ���ߵģ����ǵ������Ϊ��ʵ����ȴ���ò�������",
				"��û���̣��Ҿ��Ѿ����Լ����װ���˾޴��ѹ��������һ·�ϲ��벻����",
				"�Ҳ��룬ÿ��ս�ؼ������ж��ǲ������ġ�һ����д�����������ǵİ�����¡�",
				"��Щ���ڰ�������й���Ԫ�ض����Է������������������Լ�ȥ���������Ǹγ���ϡ�",
				"ÿ���˵������������������һ��ʱ�⡪������������������ʵ��������ȥ�Ժ�ȴ�������ƻã�ÿÿ���䶼��̫����Ŀ�����޷������Ƿ�Ϊ�ξ���",
				"���е�ÿһ��˲�䣬�����Ժ��������Ͷ��������ͶӰ��������Ϊ���㡣",
				"�̶����죬Զ����������������������Žе�����������������������ɾ���������ƺ����Ҵ������ܼ������������ö����Ǽ�������˳�����",
				"���ǳ������ǿ̹ǡ��Ҳ�֪���Ƿ���������ã�������У�����Ҳһ���Ȳ�������ɣ�",
				};
		int[] imgs = {
				R.drawable.img6,
				R.drawable.img4,
				R.drawable.img9,
				R.drawable.img5,
				R.drawable.img2,
				R.drawable.img7,
				R.drawable.img1,
				R.drawable.img8,
				R.drawable.img3,
				R.drawable.img10,
				};
		int img_times =R.drawable.img_time;
		int[] img_likes = {
				R.drawable.img_like1,
				R.drawable.img_like2,
				R.drawable.img_like3,
				R.drawable.img_like4,
				R.drawable.img_like5,
				R.drawable.img_like6,
				R.drawable.img_like7,
				R.drawable.img_like8,
				R.drawable.img_like9,
				R.drawable.img_like10,
				};
		int[] img_shares = { 
				R.drawable.img_share1,
				R.drawable.img_share2,
				R.drawable.img_share3,
				R.drawable.img_share4,
				R.drawable.img_share5,
				R.drawable.img_share6,
				R.drawable.img_share7,
				R.drawable.img_share8,
				R.drawable.img_share9,
				R.drawable.img_share10,
				};
		String[] text_times = {
				"11:13",
				"14:33",
				"21:43",
				"11:23",
				"17:13",
				"20:3",
				"23:30",
				"9:2",
				"10:4",
				"11:13",
				};
		String[] text_coordinateses = {
				"����  �人",
				"����  �人",
				"����  �˲�",
				"����  �˲�",
				"����  Т��",
				"����  Т��",
				"����  ����",
				"����  ����",
				"����  ����",
				"����  ����",
				};

		int num = 10;

		for (int i = 0; i < num; i++) {
			Map<String, Object> map;
			map = new HashMap<String, Object>();
			map.put("info", infos[i]);
			map.put("img", imgs[i]);
			map.put("img_time", img_times);
			map.put("img_like", img_likes[i]);
			map.put("img_share", img_shares[i]);
			map.put("text_time", text_times[i]);
			map.put("text_coordinates", text_coordinateses[i]);
			list.add(map);
		}

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
	public void showInfo() {
		new AlertDialog.Builder(this).setTitle("ʱ������")
				.setMessage("����ʱ������֣��û�����峺......")
				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				}).show();

	}

}
