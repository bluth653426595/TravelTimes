package com.deepin.traveltimes;

import android.app.Activity;
import android.os.Bundle;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.Geometry;
import com.baidu.mapapi.map.Graphic;
import com.baidu.mapapi.map.GraphicsOverlay;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Symbol;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.deepin.traveltimes.util.MapUtils;

public class MapActivity extends Activity {
	private BMapManager mBMapMan = null;
	private MapView mMapView = null;
	private MapController mMapController = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBMapMan = new BMapManager(getApplication());
		mBMapMan.init("99D4F50C2453175CB601D6015F6FCEBFFA067C9F", null);
		setContentView(R.layout.activity_map);

		mMapView = (MapView) findViewById(R.id.bmapsView);
		mMapView.setBuiltInZoomControls(true);
		// �����������õ����ſؼ�
		mMapController = mMapView.getController();
		// �õ�mMapView�Ŀ���Ȩ,�����������ƺ�����ƽ�ƺ�����
		GeoPoint point = new GeoPoint((int) (30.47657 * 1E6),
				(int) (114.40931 * 1E6));

		mMapController.setCenter(point);// ���õ�ͼ���ĵ�
		mMapController.setZoom(15);// ���õ�ͼzoom����

		GeoPoint[] poliLinePoints = new GeoPoint[5];
		poliLinePoints[0] = MapUtils.toGeoPoint(30.4445, 114.4258);
		poliLinePoints[1] = MapUtils.toGeoPoint(30.44015, 114.42149);
		poliLinePoints[2] = MapUtils.toGeoPoint(30.44915, 114.42095);
		poliLinePoints[3] = MapUtils.toGeoPoint(30.4651, 114.4203);
		poliLinePoints[4] = MapUtils.toGeoPoint(30.48540, 114.41042);

		drawPoliLines(poliLinePoints);
	}

	protected void drawPoliLines(GeoPoint[] points) {
		/*
		 * ��ʾ����
		 */
		Geometry poliLineGeometry = new Geometry();
		poliLineGeometry.setPolyLine(points);

		Symbol poliLineSymbol = new Symbol();// ������ʽ
		Symbol.Color poliLineColor = poliLineSymbol.new Color();// ������ɫ
		poliLineColor.red = 255;// ������ɫ�ĺ�ɫ����
		poliLineColor.green = 0;// ������ɫ����ɫ����
		poliLineColor.blue = 0;// ������ɫ����ɫ����
		poliLineColor.alpha = 126;// ������ɫ��alphaֵ
		poliLineSymbol.setLineSymbol(poliLineColor, 5);

		Graphic palaceGraphic = new Graphic(poliLineGeometry, poliLineSymbol);

		GraphicsOverlay mGraphicsOverlay = new GraphicsOverlay(mMapView);
		long poliLineID = mGraphicsOverlay.setData(palaceGraphic);

		/*
		 * ��ʾ�۵�
		 */
		long[] pointsIDs = new long[points.length];
		int index = 0;
		for (GeoPoint geoPoint : points) {
			Geometry pointGeometry = new Geometry();
			pointGeometry.setPoint(geoPoint, 10);

			Symbol pointSymbol = new Symbol();// ������ʽ
			Symbol.Color pointColor = pointSymbol.new Color();// ������ɫ
			pointColor.red = 0;// ������ɫ�ĺ�ɫ����
			pointColor.green = 255;// ������ɫ����ɫ����
			pointColor.blue = 0;// ������ɫ����ɫ����
			pointColor.alpha = 126;// ������ɫ��alphaֵ
			pointSymbol.setPointSymbol(pointColor);

			Graphic pointGraphic = new Graphic(pointGeometry, pointSymbol);
			pointsIDs[index] = mGraphicsOverlay.setData(pointGraphic);
			index++;
		}

		mMapView.getOverlays().add(mGraphicsOverlay);
		mMapView.refresh();
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
