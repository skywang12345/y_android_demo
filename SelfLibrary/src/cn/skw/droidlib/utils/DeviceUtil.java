package cn.skw.droidlib.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

import org.apache.http.conn.util.InetAddressUtils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;

public class DeviceUtil {

	private final static String DEVICE_INFO_PREFERENCE_PATH = "DEVICE_INFO_PREFERENCE_PATH";
	private final static String UUID_PREFERENCE_KEY = "UUID_PREFRENCE_KEY";
	private final static String REMOTE_IP_PREFERENCE_KEY = "REMOTE_IP_PREFERENCE_KEY";
	
	private final static String LOG_TAG = "device_log";
	private final static String DEVICE_TYPE = "android";
	
	/**
	 * @return 设备类型
	 */
	public static String getDeviceType() {
		return DEVICE_TYPE;
	}
	
	/**
	 * @return 设备IP
	 */
	public static String getDeviceIp() {
		return getIPAddress(true);
	}
	
	/**
     * Get IP address from first non-localhost interface
     * @param ipv4  true=return ipv4, false=return ipv6
     * @return  address or empty string
     */
    public static String getIPAddress(boolean useIPv4) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress().toUpperCase();
                        boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr); 
                        if (useIPv4) {
                            if (isIPv4) 
                                return sAddr;
                        } else {
                            if (!isIPv4) {
                                int delim = sAddr.indexOf('%'); // drop ip6 port suffix
                                return delim<0 ? sAddr : sAddr.substring(0, delim);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getSSID(Context context) {
    	WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    	WifiInfo wifiInfo = wifiManager.getConnectionInfo();
    	return wifiInfo.getSSID();
    }

	/**
	 *  @return 设备品牌
	 */
	public static String getDeviceBrand() {
		return Build.BRAND;
	}

	/**
	 *  @return 设备型号
	 */
	public static String getDeviceModel() {
		return Build.MODEL;
	}

	public static String getDeviceProduct() {
		return Build.PRODUCT;
	}

	/**
	 *  @return 设备生产商
	 */
	public static String getDeviceManufacturer() {
		return Build.MANUFACTURER;
	}

	/**
	 *  @return 设备CPU类型
	 */
	public static String getDeviceCPU() {
		String result = Build.CPU_ABI+" "+getMaxCpuFreq();
		return result;
	}

	/**
	 *  @return 设备MAC地址
	 */
	public static String getDeviceMac(Context context) {
		WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifi.getConnectionInfo();
		return info.getMacAddress();
	}
	
	/**
	 *  @return 设备内存大小
	 */
	public static int getDeviceMemorySize() {
		long mTotal;
		String path = "/proc/meminfo";
		String content = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path), 8);
			String line;
			if ((line = br.readLine()) != null) {
				content = line;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		// beginIndex
		int begin = content.indexOf(':');
		// endIndex
		int end = content.indexOf('k');
		// 截取字符串信息

		content = content.substring(begin + 1, end).trim();
		mTotal = Integer.parseInt(content);

		return (int)mTotal;
	}
	
	/**
	 *  @return 设备磁盘大小
	 */
	public static long getDeviceDiskSize() {
		return getTotalExternalMemorySize() + getTotalInternalMemorySize();
	}
	
	/**
	 *  @return 设备用户名
	 */
	public static String getDeviceUserName() {
		return Build.USER;
	}
	
	/**
	 *  @return 软件release版本
	 */
	public static String getVersionRelease() {
		return Build.VERSION.RELEASE;
	}
	
	/**
	 *  @return 软件版本号
	 */
	public static String getVersionCode(Context context) {
		PackageInfo pInfo;
		try {
			pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			return String.valueOf(pInfo.versionCode);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return null;
	}
	
	/**
	 *  @return 软件版本名称
	 */
	public static String getVersionName(Context context) {
		PackageInfo pInfo;
		try {
			pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			return pInfo.versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return null;
	}
	
	/**
	 *  @return 设备系统版本
	 */
	public static String getDeviceSystemVersion() {
		return String.valueOf(Build.VERSION.SDK_INT);
	}
	
	public static String getDeviceUUID(Context context) {
		SharedPreferences spf = context.getSharedPreferences(DEVICE_INFO_PREFERENCE_PATH, Context.MODE_PRIVATE);
		return spf.getString(UUID_PREFERENCE_KEY, "");
	}
	
	public static void setDeviceUUID(Context context, String uuid) {
		SharedPreferences spf = context.getSharedPreferences(DEVICE_INFO_PREFERENCE_PATH, Context.MODE_PRIVATE);
		Editor editor = spf.edit();
		editor.putString(UUID_PREFERENCE_KEY, uuid);
		editor.commit();
	}

	public static String getDeviceRemoteIp(Context context) {
		SharedPreferences spf = context.getSharedPreferences(DEVICE_INFO_PREFERENCE_PATH, Context.MODE_PRIVATE);
		return spf.getString(REMOTE_IP_PREFERENCE_KEY, "");
	}
	
	public static void setDeviceRemoteIp(Context context, String remoteIp) {
		SharedPreferences spf = context.getSharedPreferences(DEVICE_INFO_PREFERENCE_PATH, Context.MODE_PRIVATE);
		Editor editor = spf.edit();
		editor.putString(REMOTE_IP_PREFERENCE_KEY, remoteIp);
		editor.commit();
	}
	
	/**
	 * 获取SDCARD总的存储空间
	 * 
	 * @return
	 */
	private static long getTotalExternalMemorySize() {
		if (externalMemoryAvailable()) {
			File path = Environment.getExternalStorageDirectory();
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSize();
			long totalBlocks = stat.getBlockCount();
			return totalBlocks * blockSize;
		} else {
			return 0;
		}
	}

	/**
	 * 获取手机内部总的存储空间
	 * 
	 * @return
	 */
	private static long getTotalInternalMemorySize() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long totalBlocks = stat.getBlockCount();
		return totalBlocks * blockSize;
	}

	/**
	 * SDCARD是否存
	 */
	public static boolean externalMemoryAvailable() {
		return android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
	}
	
	/**
	 * 获取设备唯一标识符
	 */
	public static String getDeviceToken(Context context) {
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE); 
		String tmDevice = "" + tm.getDeviceId();
		String deviceToken = Md5Util.makeMd5Sum(tmDevice.getBytes());
		return deviceToken;
	}
	
	/**
	 * cpu最大频率
	 * @return
	 */
	public static String getMaxCpuFreq() {
		String result = "";
		ProcessBuilder cmd;
		try {
			String[] args = { "/system/bin/cat",
					"/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq" };
			cmd = new ProcessBuilder(args);
			Process process = cmd.start();
			InputStream in = process.getInputStream();
			byte[] re = new byte[24];
			while (in.read(re) != -1) {
				result = result + new String(re);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			result = "N/A";
		}
		
		try
		{
			float khz = Float.parseFloat(result.trim());
			float ghz = khz / 1024 / 1024;
			return ghz + "GHz";
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return "0GHz";
	}
}
