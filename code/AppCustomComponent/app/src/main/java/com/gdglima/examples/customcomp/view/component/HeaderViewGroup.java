package com.gdglima.examples.customcomp.view.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.gdglima.examples.customcomp.R;

/**
 * Created by emedinaa on 21/04/15.
 */
public class HeaderViewGroup extends RelativeLayout
{

    private Context context;

    public HeaderViewGroup(Context context) {
        super(context);

        init(context);
    }

    public HeaderViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HeaderViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {

        this.context= context;

        RelativeLayout header = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.header_layout, null);
        LinearLayout menu = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.menu_layout, null);

        RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams layoutParams1=new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        //textviewparams5.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(menu,layoutParams1);
        addView(header,layoutParams);


        if(!isInEditMode())
        {

        }
    }


}
