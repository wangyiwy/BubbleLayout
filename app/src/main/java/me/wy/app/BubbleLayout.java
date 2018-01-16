package me.wy.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;

/**
 * Created by WangYi
 *
 * @Date : 2018/1/16
 * @Desc : 气泡布局
 */
public class BubbleLayout extends FrameLayout {
    public static final int LEFT = 1;
    public static final int TOP = 2;
    public static final int RIGHT = 3;
    public static final int BOTTOM = 4;

    @IntDef({LEFT, TOP, RIGHT, BOTTOM})
    private @interface Direction {
    }

    /**
     * 圆角大小
     */
    private int mRadius;

    /**
     * 三角形的方向
     */
    @Direction
    private int mDirection;

    /**
     * 三角形的底边中心点
     */
    private Point mDatumPoint;

    /**
     * 三角形位置偏移量(默认居中)
     */
    private int mOffset;

    private Paint mBorderPaint;

    private Path mPath;

    private RectF mRect;

    public BubbleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BubbleLayout);
        //背景颜色
        int backGroundColor = ta.getColor(R.styleable.BubbleLayout_background_color, Color.WHITE);
        //阴影颜色
        int shadowColor = ta.getColor(R.styleable.BubbleLayout_shadow_color,
                Color.parseColor("#999999"));
        int defShadowSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                4, getResources().getDisplayMetrics());
        //阴影尺寸
        int shadowSize = ta.getDimensionPixelSize(R.styleable.BubbleLayout_shadow_size, defShadowSize);
        mRadius = ta.getDimensionPixelSize(R.styleable.BubbleLayout_radius, 0);
        //三角形方向
        mDirection = ta.getInt(R.styleable.BubbleLayout_direction, BOTTOM);
        mOffset = ta.getDimensionPixelOffset(R.styleable.BubbleLayout_offset, 0);
        ta.recycle();

        mBorderPaint = new Paint();
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setColor(backGroundColor);
        mBorderPaint.setShadowLayer(shadowSize, 0, 0, shadowColor);

        mPath = new Path();
        mRect = new RectF();
        mDatumPoint = new Point();

        setWillNotDraw(false);
        //关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mDatumPoint.x > 0 && mDatumPoint.y > 0)
            switch (mDirection) {
                case LEFT:
                    drawLeftTriangle(canvas);
                    break;
                case TOP:
                    drawTopTriangle(canvas);
                    break;
                case RIGHT:
                    drawRightTriangle(canvas);
                    break;
                case BOTTOM:
                    drawBottomTriangle(canvas);
                    break;
            }
    }

    private void drawLeftTriangle(Canvas canvas) {
        int triangularLength = getPaddingLeft();
        if (triangularLength == 0) {
            return;
        }

        mPath.addRoundRect(mRect, mRadius, mRadius, Path.Direction.CCW);
        mPath.moveTo(mDatumPoint.x, mDatumPoint.y - triangularLength / 2);
        mPath.lineTo(mDatumPoint.x - triangularLength / 2, mDatumPoint.y);
        mPath.lineTo(mDatumPoint.x, mDatumPoint.y + triangularLength / 2);
        mPath.close();
        canvas.drawPath(mPath, mBorderPaint);
    }

    private void drawTopTriangle(Canvas canvas) {
        int triangularLength = getPaddingTop();
        if (triangularLength == 0) {
            return;
        }

        mPath.addRoundRect(mRect, mRadius, mRadius, Path.Direction.CCW);
        mPath.moveTo(mDatumPoint.x + triangularLength / 2, mDatumPoint.y);
        mPath.lineTo(mDatumPoint.x, mDatumPoint.y - triangularLength / 2);
        mPath.lineTo(mDatumPoint.x - triangularLength / 2, mDatumPoint.y);
        mPath.close();
        canvas.drawPath(mPath, mBorderPaint);
    }

    private void drawRightTriangle(Canvas canvas) {
        int triangularLength = getPaddingRight();
        if (triangularLength == 0) {
            return;
        }

        mPath.addRoundRect(mRect, mRadius, mRadius, Path.Direction.CCW);
        mPath.moveTo(mDatumPoint.x, mDatumPoint.y - triangularLength / 2);
        mPath.lineTo(mDatumPoint.x + triangularLength / 2, mDatumPoint.y);
        mPath.lineTo(mDatumPoint.x, mDatumPoint.y + triangularLength / 2);
        mPath.close();
        canvas.drawPath(mPath, mBorderPaint);
    }

    private void drawBottomTriangle(Canvas canvas) {
        int triangularLength = getPaddingBottom();
        if (triangularLength == 0) {
            return;
        }

        mPath.addRoundRect(mRect, mRadius, mRadius, Path.Direction.CCW);
        mPath.moveTo(mDatumPoint.x + triangularLength / 2, mDatumPoint.y);
        mPath.lineTo(mDatumPoint.x, mDatumPoint.y + triangularLength / 2);
        mPath.lineTo(mDatumPoint.x - triangularLength / 2, mDatumPoint.y);
        mPath.close();
        canvas.drawPath(mPath, mBorderPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mRect.left = getPaddingLeft();
        mRect.top = getPaddingTop();
        mRect.right = w - getPaddingRight();
        mRect.bottom = h - getPaddingBottom();

        switch (mDirection) {
            case LEFT:
                mDatumPoint.x = getPaddingLeft();
                mDatumPoint.y = h / 2;
                break;
            case TOP:
                mDatumPoint.x = w / 2;
                mDatumPoint.y = getPaddingTop();
                break;
            case RIGHT:
                mDatumPoint.x = w - getPaddingRight();
                mDatumPoint.y = h / 2;
                break;
            case BOTTOM:
                mDatumPoint.x = w / 2;
                mDatumPoint.y = h - getPaddingBottom();
                break;
        }

        if (mOffset != 0) {
            applyOffset();
        }
    }

    /**
     * 设置三角形偏移位置
     *
     * @param offset 偏移量
     */
    public void setTriangleOffset(int offset) {
        this.mOffset = offset;
        applyOffset();
        invalidate();
    }

    private void applyOffset() {
        switch (mDirection) {
            case LEFT:
            case RIGHT:
                mDatumPoint.y += mOffset;
                break;
            case TOP:
            case BOTTOM:
                mDatumPoint.x += mOffset;
                break;
        }
    }
}
