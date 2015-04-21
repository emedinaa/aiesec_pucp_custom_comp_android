package com.gdglima.examples.customcomp.view.component;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

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

        if(!isInEditMode())
        {

        }
    }


}
