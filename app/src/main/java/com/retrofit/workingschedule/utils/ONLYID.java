package com.retrofit.workingschedule.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static android.text.TextUtils.isEmpty;

/**
 * Created by SEED_DEV2_USER on 2016/10/14.
 */

public class ONLYID {
    private static String SimSerialNumber;

    public static String onlyId(Context context) {
        /**
         * 获取DEVICE_ID
         */
        String m_szImei = getIMEI(context);
        /**
         * 获取ANDROID_ID
         */
        String m_szAndroidID = getAndroidId(context);

        /**
         *
         */
        String m_szWLANMAC = getMac(context);

        String m_szLongID = m_szImei + m_szAndroidID + m_szWLANMAC;

        // compute md5
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        m.update(m_szLongID.getBytes(), 0, m_szLongID.length());
        // get md5 bytes
        byte p_md5Data[] = m.digest();
        // create a hex string
        String m_szUniqueID = new String();
        for (int i = 0; i < p_md5Data.length; i++) {
            int b = (0xFF & p_md5Data[i]);
            // if it is a single digit, make sure it have 0 in front (proper padding)
            if (b <= 0xF) {
                m_szUniqueID += "0";
            }
            // add number to string
            m_szUniqueID += Integer.toHexString(b);
        }
        // hex string to uppercase
        m_szUniqueID = m_szUniqueID.toUpperCase();
//        Log.d("############----4--",m_szUniqueID);
        return m_szUniqueID;
    }

    public static String getIMEI(Context context) {
        String imei;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            imei = telephonyManager.getDeviceId();
        } catch (Exception e) {
            imei = "";
        }
        return imei;
    }

    /**
     * @param context
     * @return 可以使用手机Wifi或蓝牙的MAC地址作为设备标识。
     * 硬件限制：并不是所有的设备都有Wifi和蓝牙硬件，硬件不存在自然也就得不到这一信息。
     * 添加权限：ACCESS_WIFI_STATE
     * 获取到的，不是一个真实的地址，而且这个地址能轻易地被伪造。wLan不必打开，就可读取些值。
     */
    public static String getMac(Context context) {
        //wifi mac地址
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        String wifiMac = info.getMacAddress();
        if (!isEmpty(wifiMac)) {
        }
        return wifiMac;
    }

    /**
     * SIM ID – 手机SIM卡唯一标识
     * 装有SIM卡的Android 2.3设备，可以通过下面的方法获取到Sim Serial Number，
     * 对于CDMA设备，返回的是一个空值。
     *
     * @param context
     * @return
     */
    public static String getSimId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

             SimSerialNumber = tm.getSimSerialNumber();
        }
        return SimSerialNumber;
    }

    /**
     * @param context
     * @return ANDROIDID - 安卓ID
     * 在设备首次启动时，系统会随机生成一个64位的数字，并把这个数字以16进制字符串的形式保存下来，这个16进制的字符串就是ANDROID_ID，
     * 当设备被wipe后该值会被重置。
     * 厂商定制系统的Bug：不同的设备可能会产生相同的ANDROID_ID：9774d56d682e549c。
     * 厂商定制系统的Bug：有些设备返回的值为null。
     * 设备差异：对于CDMA设备，ANDROID_ID和TelephonyManager.getDeviceId() 返回相同的值。
     * 它在Android <=2.1 or Android >=2.3的版本是可靠、稳定的，但在2.2的版本并不是100%可靠的。
     * 通常被认为不可信，因为它有时为null。开发文档中说明了：这个ID会改变如果进行了出厂设置。并且，如果某个Andorid手机被Root过的话，这个ID也可以被任意改变。
     */
    public static String getAndroidId(Context context) {
        String ANDROID_ID = Settings.System.getString(context.getContentResolver(), Settings.System.ANDROID_ID);
        return ANDROID_ID;
    }
}
