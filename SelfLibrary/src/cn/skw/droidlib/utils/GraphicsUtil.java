package cn.skw.droidlib.utils;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.view.Gravity;

public class GraphicsUtil {

    /**
     * 文本的显示偏移信息
     *
     * @desc 包含的显示区域(width和height)，以及文本在该区域中的显示偏移(offsetX和offsetY)
     */
    public static class FontDisplayInfo {
        public int offsetX;         // X偏移
        public int offsetY;         // Y偏移
        public int width;           // 显示区域的宽
        public int height;          // 显示区域的高

        FontDisplayInfo(int offsetX, int offsetY, int width, int height) {
            this.offsetX = offsetX;
            this.offsetY = offsetY;
            this.width = width;
            this.height = height;
        }
    }

    /**
     * 在画布中显示文本
     *
     * @desc (1) 可以(根据gravity)自定义文本的位置
     *       (2) 可以(根据p)自定义文本的画布
     */
    public static void displayFontInCanvas(Canvas canvas, String text, int left, int top, int width, int height, Paint p, int gravity) {
        FontDisplayInfo fdi = calculateFontDisplayOffsetByPosition(text, width, height, p, gravity);
        int x = left + fdi.offsetX;
        int y = top + fdi.offsetY;
        canvas.drawText(text, x, y, p);
    }

    /**
     * 计算文本在区域(width和height)中的显示偏移
     *
     * @param text 文本
     * @param width 宽
     * @param height 高
     * @param p 笔刷
     * @param gravity 显示的方位
     */
    public static FontDisplayInfo calculateFontDisplayOffsetByPosition(String text, int width, int height, Paint p, int gravity) {
        FontMetrics metrics = p.getFontMetrics();                   // 获取文本的显示矩阵
        int fontWidth = (int)p.measureText(text);                   // 计算文本的显示x信息
        int fontHeight = (int)(metrics.descent - metrics.ascent);   // 计算文本的高度
        int offsetX=0, offsetY=0;

        // 计算x轴位置
        if (width > fontWidth) {
            switch (gravity & Gravity.HORIZONTAL_GRAVITY_MASK) {
                case Gravity.LEFT:
                    offsetX = 0;
                    break;
                case Gravity.RIGHT:
                    offsetX = width - fontWidth;
                    break;
                case Gravity.CENTER_HORIZONTAL:
                    offsetX = (width - fontWidth)/2;
                    break;
                default:
                    offsetX = (width - fontWidth)/2;
                    break;
            }
        }

        // 计算y轴位置
        if (height > fontHeight) {
            switch (gravity & Gravity.VERTICAL_GRAVITY_MASK) {
                case Gravity.TOP:
                    offsetY = fontHeight;
                    break;
                case Gravity.BOTTOM:
                    offsetY = height;
                    break;
                case Gravity.CENTER_VERTICAL:
                    offsetY = (height - fontHeight) / 2 - (int)metrics.top;
                    break;
                default:
                    offsetY = (height - fontHeight) / 2 - (int)metrics.top;
                    break;
            }
        }

        return new FontDisplayInfo(offsetX, offsetY, fontWidth, fontHeight);
    }
}
