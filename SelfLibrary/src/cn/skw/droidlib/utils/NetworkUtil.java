package cn.skw.droidlib.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {

    /**
     * 某种网络是否打开
     *
     * @desc 可以用判断wifi,bt,3G,以太网等各种安卓支持的网络是否打开
     * 1) 判断wifi是否打开: isNetworkOpen(ConnectivityManager.TYPE_WIFI);
     * 2) 判断bt是否打开: isNetworkOpen(ConnectivityManager.TYPE_BLUETOOTH);
     * 3) 判断3G是否打开: isNetworkOpen(ConnectivityManager.TYPE_MOBILE);
     * 4) 判断以太网是否打开: isNetworkOpen(ConnectivityManager.TYPE_ETHERNET);
     *
     * @return 网络打开则返回true；否则，返回false。
     */
    public static boolean isNetworkOpen(Context context, int type) {
        ConnectivityManager connManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nwInfo = connManager.getNetworkInfo(type);

        return nwInfo.isAvailable();
    }

    /**
     * 某种网络是否连接
     *
     * @desc 可以用判断wifi,3G,以太网等各种安卓支持的网络是否连上网络(打开，并且能上网)
     * 1) 判断wifi是否连接: isNetworkConnected(ConnectivityManager.TYPE_WIFI);
     * 2) 判断bt是否连接: isNetworkConnected(ConnectivityManager.TYPE_BLUETOOTH);
     * 3) 判断3G是否连接: isNetworkConnected(ConnectivityManager.TYPE_MOBILE);
     * 4) 判断以太网是否连接: isNetworkConnected(ConnectivityManager.TYPE_ETHERNET);
     *
     * @return 网络是连接的则返回true；否则，返回false。
     */
    public static boolean isNetworkConnected(Context context, int type) {
        ConnectivityManager connManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nwInfo = connManager.getNetworkInfo(type);

        return nwInfo.isConnected();
    }
}
