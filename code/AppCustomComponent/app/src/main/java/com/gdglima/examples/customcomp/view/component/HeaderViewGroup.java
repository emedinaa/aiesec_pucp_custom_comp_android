package com.gdglima.examples.customcomp.view.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gdglima.examples.customcomp.R;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created by emedinaa on 21/04/15.
 */
public class HeaderViewGroup extends RelativeLayout
{

    private Context context;
    private MenuViewHolder menuViewHolder;
    private HeaderViewHolder headerViewHolder;
    private LinearLayout menu;
    private RelativeLayout header;
    private boolean state;
    private int menuHeight;
    private int headerHeight;
    private OnHeaderListener listener;
    private View current;

    private String title;
    private int color;

    public HeaderViewGroup(Context context) {
        super(context);

        init(context,null,0);
    }

    public HeaderViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public HeaderViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context,attrs,defStyleAttr);
    }

    private void init(Context context,AttributeSet attrs,int defStyle)
    {

        this.context= context;

        header = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.header_layout, null);
        menu = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.menu_layout, null);

        RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams layoutParams1=new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        //textviewparams5.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(menu,layoutParams1);
        addView(header,layoutParams);

        final TypedArray typedArray= getContext().obtainStyledAttributes(attrs,R.styleable.HeaderViewGroup,defStyle,0);

        title  = typedArray.getString(
                R.styleable.HeaderViewGroup_title_layout);
        color  = typedArray.getColor(R.styleable.HeaderViewGroup_header_background, 0x000000);

        //menu.setVisibility(View.GONE);
        //menu.setTranslationY(-menu.getHeight());

        header.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        {
            @Override
            public void onGlobalLayout()
            {
                if (Build.VERSION.SDK_INT < 16) {
                    header.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    header.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                headerHeight= header.getHeight();
                //header.setY(-headerHeight);
            }

        });
        menu.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        {
            @Override
            public void onGlobalLayout()
            {
                if (Build.VERSION.SDK_INT < 16)
                {
                    menu.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    menu.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                menuHeight= menu.getHeight();
                menu.setY(-menuHeight);
            }

        });
        if(!isInEditMode())
        {


        }
    }
    public  void app(OnHeaderListener onHeaderListener)
    {
        this.listener= onHeaderListener;

        menuViewHolder= new MenuViewHolder();
        headerViewHolder= new HeaderViewHolder();

       /*// RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)getLayoutParams();
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)getLayoutParams();
        params.height= 400;//headerHeight+menuHeight;
        setLayoutParams(params);*/

        menuViewHolder.rlayHeaderHome= (RelativeLayout)menu.findViewById(R.id.rlayHeaderHome);
        menuViewHolder.rlayHeaderExplore= (RelativeLayout)menu.findViewById(R.id.rlayHeaderExplore);
        menuViewHolder.rlayHeaderActivity= (RelativeLayout)menu.findViewById(R.id.rlayHeaderActivity);
        menuViewHolder.rlayHeaderProfile= (RelativeLayout)menu.findViewById(R.id.rlayHeaderProfile);
        headerViewHolder.tviTitle= (TextView)header.findViewById(R.id.tviTitle);

        headerViewHolder.tviTitle.setText(title);
        header.setBackgroundColor(color);

        menuViewHolder.rlayHeaderHome.setOnClickListener(menuListener);
        menuViewHolder.rlayHeaderExplore.setOnClickListener(menuListener);
        menuViewHolder.rlayHeaderActivity.setOnClickListener(menuListener);
        menuViewHolder.rlayHeaderProfile.setOnClickListener(menuListener);
        header.setOnClickListener(headerListener);

        this.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        {
            @Override
            public void onGlobalLayout()
            {
                if (Build.VERSION.SDK_INT < 16) {
                    getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                headerHeight= header.getHeight();
                menuHeight= menu.getHeight();

               // RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)getLayoutParams();
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)getLayoutParams();
                params.height= headerHeight+menuHeight;
                setLayoutParams(params);
            }

        });

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        //vHeaderBg = findViewById(R.id.vHeaderBg);
    }

    private OnClickListener headerListener= new OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.v("CONSOLE", " header ");

            /*if(state)
            {
                state=false;
            }else
            {
                state=true;
            }*/
            if(!state)
            {
                show();
            }else
            {
                hide();
            }
            //state= (state)?(false):(true);
        }
    };

    private void show() {
        ObjectAnimator traslateY = ObjectAnimator.ofFloat(menu, "translationY",menu.getY(), headerHeight);
        traslateY.setInterpolator(new AccelerateInterpolator(2f));
        traslateY.start();
        state=true;
    }

    private void hide() {

        ObjectAnimator traslateY = ObjectAnimator.ofFloat(menu, "translationY",menu.getY(), -menu.getHeight());
        traslateY.setInterpolator(new AccelerateInterpolator(2f));
        //traslateY.setInterpolator(new DecelerateInterpolator(2f));
        traslateY.start();
        state=false;
    }

    private OnClickListener menuListener= new OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.v("CONSOLE", " OnClickListener ");
            int position=0;
            if(current!=null)
            {
                current.setBackgroundColor(Color.TRANSPARENT);
            }

            switch (view.getId())
            {
                case R.id.rlayHeaderHome:
                    current= menuViewHolder.rlayHeaderHome;
                    position=0;
                    break;
                case R.id.rlayHeaderExplore:
                    current= menuViewHolder.rlayHeaderExplore;
                    position=1;
                    break;
                case R.id.rlayHeaderActivity:
                    current= menuViewHolder.rlayHeaderActivity;
                    position=2;
                    break;
                case R.id.rlayHeaderProfile:
                    current= menuViewHolder.rlayHeaderProfile;
                    position=3;
                    break;
            }
            current.setBackgroundColor(getResources().getColor(R.color.green40));
            listener.optionSelected(position);
            hide();
        }
    };

    public class MenuViewHolder
    {
        public RelativeLayout rlayHeaderHome;
        public RelativeLayout rlayHeaderExplore;
        public RelativeLayout rlayHeaderActivity;
        public RelativeLayout rlayHeaderProfile;
    }

    public  class HeaderViewHolder
    {
        public TextView tviTitle;
    }


}
