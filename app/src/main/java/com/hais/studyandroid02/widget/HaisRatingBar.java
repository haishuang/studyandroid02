package com.hais.studyandroid02.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.hais.studyandroid02.ui.R;

/**
 * Created by Huang hai-sen on 2016/3/21 16:48.
 * 1.定义一些自定义属性attr中
 */
public class HaisRatingBar extends View {
    /**
     * 正常的
     */
    private static final int NORMAL = 0;
    /**
     * 小型的
     */
    private static final int SAMLL = 1;
    /**
     * 背景图片
     */
    private Bitmap backgroundImg;
    /**
     * 显示点亮的星星
     */
    private Bitmap starLightImg;
    /**
     * 星星总数
     */
    private int starNums = 6;
    /**
     * 选中的数量
     */
    private float ratingNums = 0;
    /**
     * 每个星星间隔距离
     * 单位：px
     */
    private int space;
    /**
     * 是否只是指示器
     */
    private boolean mIndicator;
    /**
     * 类型（即大图还是小图）
     */
    private int mType;
    /**
     * 背景图片宽度
     */
    private int bgWidth;
    /**
     * 背景图片高度
     */
    private int bgHeight;
    /**
     * 绘制 X方向的开始位置
     */
    private int drawStartX;


    public interface OnRatingBarChangeListener {
        void onRatingChanged(HaisRatingBar ratingBar, float rating, boolean fromUser);
    }

    private OnRatingBarChangeListener mOnRatingBarChangeListener;

    public HaisRatingBar(Context context) {
        this(context, null);
    }

    public HaisRatingBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HaisRatingBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.HaisRatingBar, defStyleAttr, 0);
        final boolean indicator = a.getBoolean(R.styleable.HaisRatingBar_hindicator, false);
        final float rating = a.getFloat(R.styleable.HaisRatingBar_hrating, 0);
        final int type = a.getInt(R.styleable.HaisRatingBar_htype, NORMAL);
        final int statNums = a.getInt(R.styleable.HaisRatingBar_hstartnums, 5);
        final int space = a.getInt(R.styleable.HaisRatingBar_space, 5);
        final int background = a.getResourceId(R.styleable.HaisRatingBar_starBackground, 0);
        final int startLight = a.getResourceId(R.styleable.HaisRatingBar_starLight, 0);
        a.recycle();
        //初始化数据
        setmIndicator(indicator);
        setRatingNums(rating);
        setmType(type);
        setStarNums(statNums);
        setSpace(dpToPx(context, space));
        Resources res = getResources();

        if (background != 0 && startLight != 0) {//为了防止出现不良效果，背景和指示星星需要一起设置才起作用.否则使用默认
            this.backgroundImg = BitmapFactory.decodeResource(res, background);
            this.starLightImg = BitmapFactory.decodeResource(res, startLight);
        }else {
            init(res);
        }

        bgWidth = backgroundImg.getWidth();
        bgHeight = backgroundImg.getHeight();
    }

    private void init(Resources res) {
        //设置资源图片
        if (mType == SAMLL) {
            backgroundImg = BitmapFactory.decodeResource(res, R.drawable.life_seller_star_samll_darkl);
            starLightImg = BitmapFactory.decodeResource(res, R.drawable.life_seller_star_small_light);
        } else {
            backgroundImg = BitmapFactory.decodeResource(res, R.drawable.life_seller_star_normal_dark);
            starLightImg = BitmapFactory.decodeResource(res, R.drawable.life_seller_star_normal_light);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (backgroundImg != null) {
            final int width = (bgWidth + space) * starNums;
            final int height = bgHeight;
            int w = resolveSizeAndState(width, widthMeasureSpec, 0);
            int h = resolveSizeAndState(height, heightMeasureSpec, 0);

            setMeasuredDimension(w, h);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < starNums; i++) {
            float remainNums = ratingNums - i;
            //每个星星开始绘制的位置
            drawStartX = (bgWidth + space) * i;
            if ((i + 1) < ratingNums) {//当前画的星星是否在被选中的范围内,在则画亮的星星
                canvas.drawBitmap(starLightImg, drawStartX, 0, null);
            } else {
                if (remainNums > 0 && remainNums <= 1) {//画完后剩余半个不足一个的
                    //目标图片的宽和高
                    int ratingWidth = starLightImg.getWidth();
                    int ratingHeight = starLightImg.getHeight();

                    //需要画的宽度
                    int tw = (int) (ratingWidth * remainNums);

                    //需要补充的背景
                    int bgw = ratingWidth - tw;

                    if (tw > 0) {//绘制选中的部分
                        Bitmap b = Bitmap.createBitmap(starLightImg, 0, 0, tw, ratingHeight);
                        canvas.drawBitmap(b, drawStartX, 0, null);
                    }

                    if (bgw > 0) {//绘制背景
                        Bitmap b = Bitmap.createBitmap(backgroundImg, tw, 0, bgw, ratingHeight);
                        canvas.drawBitmap(b, drawStartX + tw, 0, null);
                    }
                } else {//如果还有需要画的背景继续画
                    canvas.drawBitmap(backgroundImg, drawStartX, 0, null);
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {//对手势做出监听
        if (ismIndicator())//如果是指示器，则不对点击做出响应
            return false;
        //获取手势动作
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_MOVE:
                //获取当前触点的位置
                int positon = (int) getRelativePosition(event.getX());
                //得到当前的星星数量
                int start = positon + 1;
                //改变显示
                if (start != ratingNums) {
                    setRating(start, true);
                }
                break;
            default:
                break;
        }
        return true;
    }

    private float getRelativePosition(float x) {
        float position = x / (bgWidth + space);
        position = Math.max(position, 0);
        return Math.min(position, starNums - 1);
    }

    void setRating(float rating, boolean fromUser) {
        if (rating > starNums) {
            this.ratingNums = starNums;
        }
        this.ratingNums = rating;
        invalidate();
        dispatchRatingChange(fromUser);
    }

    /**
     *
     * @param listener
     */
    public void setOnRatingBarChangeListener(OnRatingBarChangeListener listener) {
        mOnRatingBarChangeListener = listener;
    }

    /**
     *
     * @return
     */
    public OnRatingBarChangeListener getOnRatingBarChangeListener() {
        return mOnRatingBarChangeListener;
    }

    void dispatchRatingChange(boolean fromUser) {
        if (mOnRatingBarChangeListener != null) {
            mOnRatingBarChangeListener.onRatingChanged(this, getRatingNums(), fromUser);
        }
    }

    public void setStarNums(int startNums) {
        this.starNums = startNums;
    }

    public float getRatingNums() {
        return ratingNums;
    }

    public void setRatingNums(float ratingNums) {
        setRating(ratingNums, false);
    }

    public boolean ismIndicator() {
        return mIndicator;
    }
    public void setmIndicator(boolean mIndicator) {
        this.mIndicator = mIndicator;
    }
    public void setmType(int mType) {
        this.mType = mType;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    /**
     * dp-->px
     *
     * @param context
     * @param dp
     * @return
     */
    private int dpToPx(Context context, int dp) {
        return (int) (context.getResources().getDisplayMetrics().density * dp + 0.5f);
    }
}
