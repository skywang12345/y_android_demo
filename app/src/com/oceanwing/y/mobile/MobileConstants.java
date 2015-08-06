package com.oceanwing.y.mobile;

public class MobileConstants {

    // startActivity 或 startActivityForResult的请求码
    // 防止Fragment和Activity中的请求码冲突
    public static final int REQUEST_MOBILE_FIRST_VISIT = 0x101;

    // 获取国家的电话区号
    public static final int REQUEST_ACCOUNT_IDENTIFY_COUNTRY = 0x201;
    // 获取相片: 拍照
    public static final int REQUEST_ACCOUNT_PHOTO_FROM_CAMERA = 0x202;
    // 获取相片: gallery
    public static final int REQUEST_ACCOUNT_PHOTO_FROM_GALLERY = 0x203;

    // 获取搜索地址的坐标
    public static final int REQUEST_SEARCH_POSITION = 0x301;
}
