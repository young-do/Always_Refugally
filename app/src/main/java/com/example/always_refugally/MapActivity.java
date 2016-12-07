package com.example.always_refugally;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.always_refugally.DBCLASS.Product;
import com.example.always_refugally.DBCLASS.Store;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapReverseGeoCoder;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;
import java.util.List;

import static com.example.always_refugally.R.id.button2;
import static com.example.always_refugally.R.id.button3;
import static com.example.always_refugally.R.id.button4;
import static com.example.always_refugally.R.id.button6;
import static com.example.always_refugally.R.id.texttext;


public class MapActivity extends FragmentActivity
        implements MapView.OpenAPIKeyAuthenticationResultListener, MapView.MapViewEventListener, MapView.CurrentLocationEventListener, MapReverseGeoCoder.ReverseGeoCodingResultListener {
    private MapReverseGeoCoder mReverseGeoCoder = null;
    public static final MapPoint DEFAULT_MARKER_POINT1 = MapPoint.mapPointWithGeoCoord(37.29814, 126.972169);
    public static final MapPoint DEFAULT_MARKER_POINT2 = MapPoint.mapPointWithGeoCoord(37.2988528, 126.9727401);
    public static final MapPoint DEFAULT_MARKER_POINT3 = MapPoint.mapPointWithGeoCoord(37.29722, 126.971374);
    public static final MapPoint DEFAULT_MARKER_POINT4 = MapPoint.mapPointWithGeoCoord(37.2966112, 126.9699802);
    public static final MapPoint DEFAULT_MARKER_POINT5 = MapPoint.mapPointWithGeoCoord(37.2980882, 126.9709222);
    public static final MapPoint DEFAULT_MARKER_POINT6 = MapPoint.mapPointWithGeoCoord(37.2994298, 126.9711171);
    private static final MapPoint DEFAULT_MARKER_POINT7 = MapPoint.mapPointWithGeoCoord(37.2974830, 126.9740030);
    private static final MapPoint DEFAULT_MARKER_POINT8 = MapPoint.mapPointWithGeoCoord(37.2967683, 126.9685671);
    private static final MapPoint DEFAULT_MARKER_POINT9 = MapPoint.mapPointWithGeoCoord(37.2965161, 126.9677528);
    private static final MapPoint DEFAULT_MARKER_POINT10 = MapPoint.mapPointWithGeoCoord(37.2957289, 126.9681724);
    private static final MapPoint DEFAULT_MARKER_POINT11 = MapPoint.mapPointWithGeoCoord(37.2945510,126.9789260);
    private static final MapPoint DEFAULT_MARKER_POINT12 = MapPoint.mapPointWithGeoCoord(37.2951880,126.9818300);
    private static final MapPoint DEFAULT_MARKER_POINT13 = MapPoint.mapPointWithGeoCoord(37.2953090,126.9834160);
    private static final MapPoint DEFAULT_MARKER_POINT14 = MapPoint.mapPointWithGeoCoord(37.2965820,126.9831210);
    private static final MapPoint DEFAULT_MARKER_POINT15 = MapPoint.mapPointWithGeoCoord(37.2970740,126.9820950);
    private static final MapPoint DEFAULT_MARKER_POINT16 = MapPoint.mapPointWithGeoCoord(37.2990720,126.9800170);
    private static final MapPoint DEFAULT_MARKER_POINT17 = MapPoint.mapPointWithGeoCoord(37.3000620,126.9793800);
    private static final MapPoint DEFAULT_MARKER_POINT18 = MapPoint.mapPointWithGeoCoord(37.2911120,126.9810980);
    private static final MapPoint DEFAULT_MARKER_POINT19 = MapPoint.mapPointWithGeoCoord(37.2901280,126.9775870);
    private static final MapPoint DEFAULT_MARKER_POINT20 = MapPoint.mapPointWithGeoCoord(37.2896500,126.9775670);
    private static final MapPoint DEFAULT_MARKER_POINT21 = MapPoint.mapPointWithGeoCoord(37.2989660,126.9677020);
    private static final MapPoint DEFAULT_MARKER_POINT22 = MapPoint.mapPointWithGeoCoord(37.2946830,126.9854790);
    private static final MapPoint DEFAULT_MARKER_POINT23 = MapPoint.mapPointWithGeoCoord(37.3028390,126.9726860);
    private static final MapPoint DEFAULT_MARKER_POINT24 = MapPoint.mapPointWithGeoCoord(37.2983670,126.9720650);

    public static MapPoint CurrPoint = MapPoint.mapPointWithGeoCoord(0,0);

    private MapPOIItem mDefaultMarker;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Intent intent = getIntent();
        ArrayList<Store> sl = (ArrayList)intent.getSerializableExtra("store");
        for (Store s : sl)
        {
            s.setdis(distanceBetweenLoc(s.getLat(),s.getLon()));
            Log.d("맵nyan", "onResponse: " + s.getName());
            Log.d("맵nyan", "onResponse: " + s.getLat());
            Log.d("맵nyan", "onResponse: " + s.getLon());
            Log.d("맵nyan", "onResponse: " + s.getTotal());

            List<Product> pl = s.getProduct();
            for (Product p : pl)
            {
                Log.d("맵nyan", "onResponse: " + p.getName());
                Log.d("맵nyan", "onResponse: " + p.getPid());
                Log.d("맵nyan", "onResponse: " + p.getPrice());
            }
        }

//다음이 제공하는 MapView객체 생성 및 API Key 설정
        final MapView mapView = new MapView(this);
        mapView.setDaumMapApiKey("1581af30c6260a7eba804e18e5319ddb");
        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);
        mapView.setCurrentLocationEventListener(this);
        Button btn = (Button) findViewById(button2);
        Button btn2 = (Button) findViewById(button3);
        Button btn3 = (Button) findViewById(button4);
        Button btn4 = (Button) findViewById(button6);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mReverseGeoCoder = new MapReverseGeoCoder("1581af30c6260a7eba804e18e5319ddb", mapView.getMapCenterPoint(), MapActivity.this, MapActivity.this);
                mReverseGeoCoder.startFindingAddress();
                CurrPoint=mapView.getMapCenterPoint();
                mapView.setCurrentLocationRadius(700); // meter
                mapView.setCurrentLocationRadiusFillColor(Color.argb(77, 255, 255, 0));
                mapView.setCurrentLocationRadiusStrokeColor(Color.argb(77, 255, 165, 0));
                mapView.setZoomLevel(3, true);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MapPoint CMP = CurrPoint;
                StringBuffer result =new StringBuffer("daummaps://route?sp=");
                result.append(CMP.getMapPointGeoCoord().latitude);
                result.append(",");
                result.append(CMP.getMapPointGeoCoord().longitude);
                result.append("&ep=");
                result.append(DEFAULT_MARKER_POINT1.getMapPointGeoCoord().latitude);
                result.append(",");
                result.append(DEFAULT_MARKER_POINT1.getMapPointGeoCoord().longitude);
                result.append("&by=FOOT");
                Uri uri = Uri.parse(result.toString());
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(uri);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDefaultMarker1(mapView);
                createDefaultMarker2(mapView);
                createDefaultMarker3(mapView);
                createDefaultMarker4(mapView);
                createDefaultMarker5(mapView);
                createDefaultMarker6(mapView);
                createDefaultMarker7(mapView);
                createDefaultMarker8(mapView);
                createDefaultMarker9(mapView);
                createDefaultMarker10(mapView);
                createDefaultMarker11(mapView);
                createDefaultMarker12(mapView);
                createDefaultMarker13(mapView);
                createDefaultMarker14(mapView);
                createDefaultMarker15(mapView);
                createDefaultMarker16(mapView);
                createDefaultMarker17(mapView);
                createDefaultMarker19(mapView);
                createDefaultMarker20(mapView);
                createDefaultMarker21(mapView);
                createDefaultMarker23(mapView);
                createDefaultMarker24(mapView);
                String s;
                s=Integer.toString(distanceBetween(DEFAULT_MARKER_POINT1));
                Toast.makeText(MapActivity.this, s, Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onMapViewInitialized(MapView mapView) {

    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {

    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onDaumMapOpenAPIKeyAuthenticationResult(MapView mapView, int i, String s) {

    }

    @Override
    public void onReverseGeoCoderFoundAddress(MapReverseGeoCoder mapReverseGeoCoder, String s) {
        onFinishReverseGeoCoding(s);

    }

    @Override
    public void onReverseGeoCoderFailedToFindAddress(MapReverseGeoCoder mapReverseGeoCoder) {
        onFinishReverseGeoCoding("Fail");
    }

    @Override
    public void onCurrentLocationUpdate(MapView mapView, MapPoint mapPoint, float v) {

    }

    @Override
    public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float v) {

    }

    @Override
    public void onCurrentLocationUpdateFailed(MapView mapView) {

    }

    @Override
    public void onCurrentLocationUpdateCancelled(MapView mapView) {

    }

    private void onFinishReverseGeoCoding(String result) {
        TextView localtext = (TextView) findViewById(texttext);
        Toast.makeText(MapActivity.this, "현재 위치 : " + result, Toast.LENGTH_SHORT).show();
        localtext.setText(result);
        Intent i = new Intent(MapActivity.this, MainActivity.class);
        i.putExtra("my_address", result);
        startActivity(i);
    }

        private void createDefaultMarker1 (MapView mapView){
            mDefaultMarker = new MapPOIItem();
            String name = "GS25 수원성대점";
            mDefaultMarker.setItemName(name);
            mDefaultMarker.setTag(1);
            mDefaultMarker.setMapPoint(DEFAULT_MARKER_POINT1);
            mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
            mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

            mapView.addPOIItem(mDefaultMarker);
        }

    private void createDefaultMarker2(MapView mapView) {
        mDefaultMarker = new MapPOIItem();
        String name = "세븐일레븐 수원성대점";
        mDefaultMarker.setItemName(name);
        mDefaultMarker.setTag(2);
        mDefaultMarker.setMapPoint(DEFAULT_MARKER_POINT2);
        mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(mDefaultMarker);
    }

    private void createDefaultMarker3(MapView mapView) {
        mDefaultMarker = new MapPOIItem();
        String name = "CU 수원성균관점";
        mDefaultMarker.setItemName(name);
        mDefaultMarker.setTag(3);
        mDefaultMarker.setMapPoint(DEFAULT_MARKER_POINT3);
        mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(mDefaultMarker);
    }

    private void createDefaultMarker4(MapView mapView) {
        mDefaultMarker = new MapPOIItem();
        String name = "CU 율전삼성점";
        mDefaultMarker.setItemName(name);
        mDefaultMarker.setTag(4);
        mDefaultMarker.setMapPoint(DEFAULT_MARKER_POINT4);
        mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(mDefaultMarker);
    }

    private void createDefaultMarker5(MapView mapView) {
        mDefaultMarker = new MapPOIItem();
        String name = "세븐일레븐 율전삼성점";
        mDefaultMarker.setItemName(name);
        mDefaultMarker.setTag(5);
        mDefaultMarker.setMapPoint(DEFAULT_MARKER_POINT5);
        mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(mDefaultMarker);
    }

    private void createDefaultMarker6(MapView mapView) {
        mDefaultMarker = new MapPOIItem();
        String name = "GS25 수원천천점";
        mDefaultMarker.setItemName(name);
        mDefaultMarker.setTag(6);
        mDefaultMarker.setMapPoint(DEFAULT_MARKER_POINT6);
        mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(mDefaultMarker);
    }

    private void createDefaultMarker7(MapView mapView) {
        mDefaultMarker = new MapPOIItem();
        String name = "세븐일레븐 수원성대후문점";
        mDefaultMarker.setItemName(name);
        mDefaultMarker.setTag(7);
        mDefaultMarker.setMapPoint(DEFAULT_MARKER_POINT7);
        mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(mDefaultMarker);
    }

    private void createDefaultMarker8(MapView mapView) {
        mDefaultMarker = new MapPOIItem();
        String name = "CU 율전베스트점";
        mDefaultMarker.setItemName(name);
        mDefaultMarker.setTag(8);
        mDefaultMarker.setMapPoint(DEFAULT_MARKER_POINT8);
        mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(mDefaultMarker);
    }

    private void createDefaultMarker9(MapView mapView) {
        mDefaultMarker = new MapPOIItem();
        String name = "GS25 율전성대점";
        mDefaultMarker.setItemName(name);
        mDefaultMarker.setTag(9);
        mDefaultMarker.setMapPoint(DEFAULT_MARKER_POINT9);
        mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(mDefaultMarker);
    }

    private void createDefaultMarker10(MapView mapView) {
        mDefaultMarker = new MapPOIItem();
        String name = "세븐일레븐 수원율전2점";
        mDefaultMarker.setItemName(name);
        mDefaultMarker.setTag(10);
        mDefaultMarker.setMapPoint(DEFAULT_MARKER_POINT10);
        mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(mDefaultMarker);
    }

    private void createDefaultMarker11(MapView mapView) {
        mDefaultMarker = new MapPOIItem();
        String name = "GS25 수원천천점";
        mDefaultMarker.setItemName(name);
        mDefaultMarker.setTag(11);
        mDefaultMarker.setMapPoint(DEFAULT_MARKER_POINT11);
        mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(mDefaultMarker);
    }

    private void createDefaultMarker12(MapView mapView) {
        mDefaultMarker = new MapPOIItem();
        String name = "CU SK천천주유소점";
        mDefaultMarker.setItemName(name);
        mDefaultMarker.setTag(12);
        mDefaultMarker.setMapPoint(DEFAULT_MARKER_POINT12);
        mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(mDefaultMarker);
    }

    private void createDefaultMarker13(MapView mapView) {
        mDefaultMarker = new MapPOIItem();
        String name = "CU 천천대일점";
        mDefaultMarker.setItemName(name);
        mDefaultMarker.setTag(13);
        mDefaultMarker.setMapPoint(DEFAULT_MARKER_POINT13);
        mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(mDefaultMarker);
    }

    private void createDefaultMarker14(MapView mapView) {
        mDefaultMarker = new MapPOIItem();
        String name = "세븐일레븐 천천해피니점";
        mDefaultMarker.setItemName(name);
        mDefaultMarker.setTag(14);
        mDefaultMarker.setMapPoint(DEFAULT_MARKER_POINT14);
        mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(mDefaultMarker);
    }

    private void createDefaultMarker15(MapView mapView) {
        mDefaultMarker = new MapPOIItem();
        String name = "GS25 수원베스트점";
        mDefaultMarker.setItemName(name);
        mDefaultMarker.setTag(15);
        mDefaultMarker.setMapPoint(DEFAULT_MARKER_POINT15);
        mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(mDefaultMarker);
    }

    private void createDefaultMarker16(MapView mapView) {
        mDefaultMarker = new MapPOIItem();
        String name = "GS25 장안천천점";
        mDefaultMarker.setItemName(name);
        mDefaultMarker.setTag(16);
        mDefaultMarker.setMapPoint(DEFAULT_MARKER_POINT16);
        mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(mDefaultMarker);
    }

    private void createDefaultMarker17(MapView mapView) {
        mDefaultMarker = new MapPOIItem();
        String name = "GS25 천천한누리점";
        mDefaultMarker.setItemName(name);
        mDefaultMarker.setTag(17);
        mDefaultMarker.setMapPoint(DEFAULT_MARKER_POINT17);
        mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(mDefaultMarker);
    }

    private void createDefaultMarker18(MapView mapView) {
        mDefaultMarker = new MapPOIItem();
        String name = "세븐일레븐 수원천천대우점";
        mDefaultMarker.setItemName(name);
        mDefaultMarker.setTag(18);
        mDefaultMarker.setMapPoint(DEFAULT_MARKER_POINT18);
        mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(mDefaultMarker);
    }

    private void createDefaultMarker19(MapView mapView) {
        mDefaultMarker = new MapPOIItem();
        String name = "세븐일레븐 천천원룸점";
        mDefaultMarker.setItemName(name);
        mDefaultMarker.setTag(19);
        mDefaultMarker.setMapPoint(DEFAULT_MARKER_POINT19);
        mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(mDefaultMarker);
    }

    private void createDefaultMarker20(MapView mapView) {
        mDefaultMarker = new MapPOIItem();
        String name = "GS25 천천원룸점";
        mDefaultMarker.setItemName(name);
        mDefaultMarker.setTag(20);
        mDefaultMarker.setMapPoint(DEFAULT_MARKER_POINT20);
        mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(mDefaultMarker);
    }

    private void createDefaultMarker21(MapView mapView) {
        mDefaultMarker = new MapPOIItem();
        String name = "미니스톱 율전스카이점";
        mDefaultMarker.setItemName(name);
        mDefaultMarker.setTag(21);
        mDefaultMarker.setMapPoint(DEFAULT_MARKER_POINT21);
        mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(mDefaultMarker);
    }

    private void createDefaultMarker22(MapView mapView) {
        mDefaultMarker = new MapPOIItem();
        String name = "위드미 수원한화점";
        mDefaultMarker.setItemName(name);
        mDefaultMarker.setTag(22);
        mDefaultMarker.setMapPoint(DEFAULT_MARKER_POINT22);
        mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(mDefaultMarker);
    }

    private void createDefaultMarker23(MapView mapView) {
        mDefaultMarker = new MapPOIItem();
        String name = "위드미 수원율전점";
        mDefaultMarker.setItemName(name);
        mDefaultMarker.setTag(23);
        mDefaultMarker.setMapPoint(DEFAULT_MARKER_POINT23);
        mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(mDefaultMarker);
    }

    private void createDefaultMarker24(MapView mapView) {
        mDefaultMarker = new MapPOIItem();
        String name = "365PLUS 수원성대점";
        mDefaultMarker.setItemName(name);
        mDefaultMarker.setTag(24);
        mDefaultMarker.setMapPoint(DEFAULT_MARKER_POINT24);
        mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(mDefaultMarker);
    }

    public int distanceBetween(MapPoint MP) {
        MapPoint CMP = CurrPoint;
        double y = (CMP.getMapPointGeoCoord().latitude - MP.getMapPointGeoCoord().latitude) * 110988.09668599277;
        double x = (CMP.getMapPointGeoCoord().longitude - MP.getMapPointGeoCoord().longitude) * 88359.11356077534;

        return (int)Math.sqrt(y * y + x * x);
    }

    public int distanceBetweenLoc(double lat, double lon) {
        MapPoint CMP = CurrPoint;
        double y = (CMP.getMapPointGeoCoord().latitude - lat) * 110988.09668599277;
        double x = (CMP.getMapPointGeoCoord().longitude - lon) * 88359.11356077534;

        return (int)Math.sqrt(y * y + x * x);
    }
}
