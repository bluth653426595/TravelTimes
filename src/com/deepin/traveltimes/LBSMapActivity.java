package com.deepin.traveltimes;

import android.app.Activity;
import android.os.Bundle;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.platform.comapi.basestruct.GeoPoint;

public class LBSMapActivity extends Activity {
	BMapManager mBMapMan = null;
	MapView mMapView = null;
	String APPKEY = "2AFF269BA2653E6378E2B01EFD63CEA278B0B39A";

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mBMapMan = new BMapManager(getApplication());
		mBMapMan.init(APPKEY, null);
		// ע�⣺��������setContentViewǰ��ʼ��BMapManager���󣬷���ᱨ��
		setContentView(R.layout.activity_main);
		mMapView = (MapView) findViewById(R.id.bmapsView);
		mMapView.setBuiltInZoomControls(true);
		// �����������õ����ſؼ�
		MapController mMapController = mMapView.getController();
		// �õ�mMapView�Ŀ���Ȩ,�����������ƺ�����ƽ�ƺ�����
		// 106.551619, 29.567227
		GeoPoint point = new GeoPoint((int) (106.551619 * 1E6),
				(int) (29.567227 * 1E6));
		// �ø����ľ�γ�ȹ���һ��GeoPoint����λ��΢�� (�� * 1E6)
		mMapController.setCenter(point);// ���õ�ͼ���ĵ�
		mMapController.setZoom(12);// ���õ�ͼzoom����

	}

	@Override
	protected void onDestroy() {
		mMapView.destroy();
		if (mBMapMan != null) {
			mBMapMan.destroy();
			mBMapMan = null;
		}
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		mMapView.onPause();
		if (mBMapMan != null) {
			mBMapMan.stop();
		}
		super.onPause();
	}

	@Override
	protected void onResume() {
		mMapView.onResume();
		if (mBMapMan != null) {
			mBMapMan.start();
		}
		super.onResume();
	}

}
