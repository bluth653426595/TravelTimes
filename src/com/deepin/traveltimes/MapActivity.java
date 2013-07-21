package com.deepin.traveltimes;

import java.util.concurrent.TimeUnit;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.baidu.location.LocationClient;
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
<<<<<<< HEAD
	BMapManager mBMapMan = null;
	MapView mMapView = null;
	
	public LocationClient mLocationClient = null;
=======
	private BMapManager mBMapMan = null;
	private MapView mMapView = null;
	private MapController mMapController = null;

	private GeoPoint currentPosition = null;
	GeoPoint[] poliLinePoints = null;

	private Handler mHandler = null;

	private ImageView currentPositionMarker;
>>>>>>> origin/develop

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBMapMan = new BMapManager(getApplication());
		mBMapMan.init("99D4F50C2453175CB601D6015F6FCEBFFA067C9F", null);
		setContentView(R.layout.activity_map);

		mHandler = new MotionHandler();
		currentPositionMarker = new ImageView(this);
		currentPositionMarker.setImageResource(R.drawable.marker3);

		mMapView = (MapView) findViewById(R.id.bmapsView);
		mMapController = mMapView.getController();

		setupPoliLinePoints();
		drawPoliLines(poliLinePoints);

		mMapController.setCenter(poliLinePoints[0]);// ���õ�ͼ���ĵ�
		mMapController.setZoom(15);// ���õ�ͼzoom����

		MapView.LayoutParams params = new MapView.LayoutParams(100, 100,
				poliLinePoints[0], MapView.LayoutParams.BOTTOM_CENTER);
		mMapView.addView(currentPositionMarker, params);

		new MotionThread(mHandler).start();
	}

	private void setupPoliLinePoints() {
		poliLinePoints = new GeoPoint[5];
		poliLinePoints[0] = MapUtils.toGeoPoint(30.4445, 114.4258);
		poliLinePoints[1] = MapUtils.toGeoPoint(30.44015, 114.42149);
		poliLinePoints[2] = MapUtils.toGeoPoint(30.44915, 114.42095);
		poliLinePoints[3] = MapUtils.toGeoPoint(30.4651, 114.4203);
		poliLinePoints[4] = MapUtils.toGeoPoint(30.48540, 114.41042);
	}

	private void P2P(GeoPoint start, GeoPoint end) {
		Point startPoint = mMapView.getProjection().toPixels(start, null);
		Point endPoint = mMapView.getProjection().toPixels(end, null);

		TranslateAnimation ta = new TranslateAnimation(0, endPoint.x
				- startPoint.x, 0, endPoint.y - startPoint.y);

		ta.setAnimationListener(new MotionAnimationListener(this, mMapView,
				end, this.currentPositionMarker));
		ta.setFillBefore(true);
		ta.setFillEnabled(true);
		ta.setDuration(2000);
		ta.setInterpolator(new LinearInterpolator());
		currentPositionMarker.startAnimation(ta);
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

	@SuppressLint("HandlerLeak")
	class MotionThread extends Thread {
		private Handler handler = null;

		public MotionThread(Handler mHandler) {
			this.handler = mHandler;
		}

		@Override
		public void run() {
			for (int i = 0; i < poliLinePoints.length - 1; i++) {
				try {
					TimeUnit.MILLISECONDS.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				handler.sendEmptyMessage(i);

				synchronized (MapActivity.this) {
					try {
						MapActivity.this.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}
	}

	class MotionHandler extends Handler {
		@SuppressLint("HandlerLeak")
		@Override
		public void handleMessage(Message msg) {
			P2P(poliLinePoints[msg.what], poliLinePoints[msg.what + 1]);
		}
	}
}
