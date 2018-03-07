package com.example.admin.locationtobt;

/**
 * Created by admin on 2018/3/5.
 */
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.Poi;
import com.baidu.location.LocationClientOption;
import java.util.List;
import android.util.Log;
import android.widget.TextView;
import android.app.Activity;

class MyLocationListener extends BDAbstractLocationListener{

    public void onReceiveLocation(BDLocation location) {
        //Gps gcj02Gps=CoordTran.bd09_To_Gcj02(location.getLatitude(),location.getLongitude());
        Gps wgsGps=CoordTran.gcj_To_Gps84(location.getLatitude(),location.getLongitude());
        //获取定位结果
        StringBuffer sb = new StringBuffer(256);

        sb.append("time : ");
        sb.append(location.getTime());    //获取定位时间

        sb.append("error code : ");
        sb.append(location.getLocType());    //获取类型类型

        sb.append("latitude : ");
        sb.append(wgsGps.lat);    //获取纬度信息

        sb.append("lontitude : ");
        sb.append(wgsGps.lon);    //获取经度信息

        sb.append("radius : ");
        sb.append(location.getRadius());    //获取定位精准度


        if (location.getLocType() == BDLocation.TypeGpsLocation){

            // GPS定位结果
            sb.append("speed : ");
            sb.append(location.getSpeed());    // 单位：公里每小时

            sb.append("satellite : ");
            sb.append(location.getSatelliteNumber());    //获取卫星数

            sb.append("height : ");
            sb.append(location.getAltitude());    //获取海拔高度信息，单位米

            sb.append("direction : ");
            sb.append(location.getDirection());    //获取方向信息，单位度

            sb.append("addr : ");
            sb.append(location.getAddrStr());    //获取地址信息

            sb.append("describe : ");
            sb.append("gps定位成功");


        } else if (location.getLocType() == BDLocation.TypeNetWorkLocation){

            // 网络定位结果
            sb.append("addr : ");
            sb.append(location.getAddrStr());    //获取地址信息

            sb.append("operationers : ");
            sb.append(location.getOperators());    //获取运营商信息

            sb.append("describe : ");
            sb.append("网络定位成功");

        } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {

            // 离线定位结果
            sb.append("describe : ");
            sb.append("离线定位成功，离线定位结果也是有效的");

        } else if (location.getLocType() == BDLocation.TypeServerError) {
            sb.append("describe : ");
            sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");

        } else if (location.getLocType() == BDLocation.TypeNetWorkException) {

            sb.append("describe : ");
            sb.append("网络不同导致定位失败，请检查网络是否通畅");

        } else if (location.getLocType() == BDLocation.TypeCriteriaException) {

            sb.append("describe : ");
            sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");


        }
        sb.append("locationdescribe : ");
        sb.append(location.getLocationDescribe());    //位置语义化信息
        List<Poi> list = location.getPoiList();    // POI数据
        if (list != null) {
            sb.append("poilist size = : ");
            sb.append(list.size());
            for (Poi p : list) {
                sb.append("poi= : ");
                sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
            }
        }
        //TextView  mFortuneText = (TextView) findViewById(R.id.fortuneText);
        WriteAndRead.getString(sb.toString());
        Log.i("BaiduLocationApiDem", sb.toString());
    }
     /**
      * 回调定位诊断信息，开发者可以根据相关信息解决定位遇到的一些问题
      * 自动回调，相同的diagnosticType只会回调一次
      *
      * @param locType           当前定位类型
      * @param diagnosticType    诊断类型（1~9）
      * @param diagnosticMessage 具体的诊断信息释义
      */
     public void onLocDiagnosticMessage(int locType, int diagnosticType, String diagnosticMessage) {

         if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_BETTER_OPEN_GPS) {

             //建议打开GPS

         } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_BETTER_OPEN_WIFI) {

             //建议打开wifi，不必连接，这样有助于提高网络定位精度！

         } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_CHECK_LOC_PERMISSION) {

             //定位权限受限，建议提示用户授予APP定位权限！

         } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_CHECK_NET) {

             //网络异常造成定位失败，建议用户确认网络状态是否异常！

         } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_CLOSE_FLYMODE) {

             //手机飞行模式造成定位失败，建议用户关闭飞行模式后再重试定位！

         } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_INSERT_SIMCARD_OR_OPEN_WIFI) {

             //无法获取任何定位依据，建议用户打开wifi或者插入sim卡重试！

         } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_OPEN_PHONE_LOC_SWITCH) {

             //无法获取有效定位依据，建议用户打开手机设置里的定位开关后重试！

         } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_SERVER_FAIL) {

             //百度定位服务端定位失败
             //建议反馈location.getLocationID()和大体定位时间到loc-bugs@baidu.com

         } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_FAIL_UNKNOWN) {

             //无法获取有效定位依据，但无法确定具体原因
             //建议检查是否有安全软件屏蔽相关定位权限
             //或调用重新启动后重试！

         }
     }

 }
