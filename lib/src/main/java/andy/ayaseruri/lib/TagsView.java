package andy.ayaseruri.lib;

/**
 * Created by ayaseruri on 15/8/13.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ayaseruri on 15/8/2.
 */
public class TagsView extends LinearLayout {

    private int textPaddingLeft, textPaddingRight, textPaddingTop, textPaddingBottom, tagsSpace, textSize;
    private int leftMargin, rightMargin, maxLength, textColor;
    private int tagsBg, maxLines, lineSpace;

    private Context mContext;

    public TagsView(Context context) {
        super(context);
    }

    public TagsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        this.setOrientation(VERTICAL);
        this.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TagsView);
        textPaddingLeft = a.getDimensionPixelSize(R.styleable.TagsView_textPaddingLeft, 0);
        textPaddingRight = a.getDimensionPixelSize(R.styleable.TagsView_textPaddingRight, 0);
        textPaddingTop = a.getDimensionPixelSize(R.styleable.TagsView_textPaddingTop, 0);
        textPaddingBottom = a.getDimensionPixelSize(R.styleable.TagsView_textPaddingBottom, 0);
        textSize = a.getDimensionPixelSize(R.styleable.TagsView_textSize, 0);
        textColor = a.getColor(R.styleable.TagsView_textColor, getResources().getColor(android.R.color.black));
        tagsSpace = a.getDimensionPixelSize(R.styleable.TagsView_tagsSpace, 0);
        tagsBg = a.getResourceId(R.styleable.TagsView_tagsBg, R.drawable.tags_bg);
        leftMargin = a.getDimensionPixelSize(R.styleable.TagsView_leftMargin, 0);
        rightMargin = a.getDimensionPixelSize(R.styleable.TagsView_rightMargin, 0);
        maxLines = a.getInt(R.styleable.TagsView_maxlines, 1);

        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        maxLength = a.getDimensionPixelSize(R.styleable.TagsView_maxLength, dm.widthPixels);
        lineSpace = a.getDimensionPixelSize(R.styleable.TagsView_lineSpace, 0);
        a.recycle();
    }

    public void init(ArrayList<String> tagStrings, final ITagOnClick iTagOnClick){
        this.removeAllViews();
        float rowLength = leftMargin + rightMargin;
        LinearLayout row = creatLinearLayout();
        int line = 0;

        int tagStringsSize = tagStrings.size();
        for(int i=0; i< tagStringsSize; i++){
            final TextView tag = new TextView(mContext);
            tag.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            tag.setTextColor(textColor);
            tag.setMaxLines(1);
            tag.setEllipsize(TextUtils.TruncateAt.END);
            tag.setMinimumWidth(0);
            tag.setMinimumHeight(0);
            tag.setIncludeFontPadding(false);
            tag.setPadding(textPaddingLeft, textPaddingTop, textPaddingRight, textPaddingBottom);
            tag.setText(tagStrings.get(i));
            tag.setBackgroundResource(tagsBg);
            tag.setTag(i);
            if(null != iTagOnClick){
                tag.setClickable(true);
                tag.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        iTagOnClick.onClick((int)tag.getTag());
                    }
                });
            }

            LayoutParams btnParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            btnParams.setMargins(0, 0, tagsSpace, 0);
            tag.setLayoutParams(btnParams);

            rowLength = rowLength + tag.getPaint().measureText(tagStrings.get(i)) + tagsSpace;
            if(rowLength < maxLength){
                row.addView(tag);
            }else {
                if(line < maxLines){
                    this.addView(row);
                    line++;
                    rowLength = leftMargin + rightMargin + tag.getPaint().measureText(tagStrings.get(i)) + tagsSpace;
                    row = creatLinearLayout();
                    row.addView(tag);
                }else {
                   return;
                }
            }
        }
        if(0 != row.getChildCount() && line < maxLines){
            this.addView(row);
        }
    }

    private LinearLayout creatLinearLayout(){
        LinearLayout linearLayout = new LinearLayout(mContext);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.leftMargin = leftMargin;
        layoutParams.rightMargin = rightMargin;
        layoutParams.bottomMargin = lineSpace;
        linearLayout.setLayoutParams(layoutParams);
        return linearLayout;
    }

    public interface ITagOnClick{
        void onClick(int postion);
    }
}

