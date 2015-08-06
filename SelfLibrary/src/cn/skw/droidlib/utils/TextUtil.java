package cn.skw.droidlib.utils;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

/**
 * 文本的工具类
 *
 * @author skywang
 * @e-mail kuiwu-wang@163.com
 */
public class TextUtil {

    public static boolean isEmpty(String text) {
        return text==null || text.length()==0;
    }

    /** 
     * 检测邮箱地址是否合法
     */
    public static boolean isEmailValidate(String email) {
        if (isEmpty(email)) {
            return false;
        }

        Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
    }   

    /** 
     * 检测手机号码是否合法
     */
    public static boolean isMobilePhoneValidate(String number) {
        return (!isEmpty(number)) && Pattern.matches("\\d+", number);
    }

    /** 
     * 检测密码码是否合法
     */
    public static boolean isPasswordValidate(String password) {
        return (!isEmpty(password)) && Pattern.matches("\\S{5,}", password);
    }   

    /**
     * 设定文本为指定颜色
     */
    public static CharSequence toColorText(CharSequence text, int color) {
        ForegroundColorSpan fgcspan = new ForegroundColorSpan(color);
        SpannableStringBuilder ss = new SpannableStringBuilder(text);
        ss.setSpan(fgcspan, 0, text.length(), 0);

        return ss;
    }

    /** 
     * 获取信用卡日期(月/念)
     *
     * @param date (信用卡)日期(月年各占两位) 例如, 0617表示2017年6月
     * @return 成功的话，返回"月/年"组成的数组。例如，parseYearMonth("0617")返回的数组是{6, 17}
     *         失败的话，返回null
     */
    public static int[] parseYearMonth(String date) {
        if (null==date || !Pattern.matches("\\d{4}", date)) {
            return null;  
        }

        int month = 0;
        int year = 0;
        try {
            month = Integer.parseInt(date.substring(0, 2));
            year = 2000+Integer.parseInt(date.substring(2, 4));
            Calendar cal = Calendar.getInstance();
            int currYear = cal.get(Calendar.YEAR)%100;
            int currMonth = cal.get(Calendar.MONTH) + 1;
            if (month<1 || month>12 || year<currYear || (year==currYear && month<=currMonth)) {
                return null;
            }   
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }   

        return new int[]{month, year};
    }   
}
