package com.lxj.mytimepicker;

import java.util.Calendar;

import net.simonvt.datepicker.DatePicker;
import net.simonvt.datepicker.DatePicker.OnDateChangedListener;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * 日期选择对话框
 * @author MeiQin
 *
 */
public class DatePickerDialogActivity extends Activity {

	private TextView mDateDisplay;
	private Button mCancelBut;
    private int mYear;
    private int mMonth;
    private int mDay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.setContentView(R.layout.global_popwin_datepicker);
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.width = LayoutParams.FILL_PARENT;
		lp.gravity = Gravity.BOTTOM;
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		getWindow().setAttributes(lp);
		initViews();
	}

	private void initViews() {
		mDateDisplay = (TextView) findViewById(R.id.tv_showdate);
		mCancelBut = (Button) findViewById(R.id.cancel_but);
		mCancelBut.setText("确定");
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        updateDisplay();
        
        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);

        boolean showCalendar = false;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            showCalendar = getResources().getConfiguration().smallestScreenWidthDp >= 600;
        } else {
            showCalendar =
                    (getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_XLARGE;
        }

        datePicker.setCalendarViewShown(showCalendar);
        datePicker.init(mYear, mMonth, mDay, mDateChangedListener);
        mCancelBut.setOnClickListener(mClickListener);
    }
	
	private void updateDisplay() {
        mDateDisplay.setText(new StringBuilder()
        		.append(mYear).append("年")
                .append(mMonth + 1).append("月")
                .append(mDay).append("日"));
    }

    private OnDateChangedListener mDateChangedListener = new OnDateChangedListener() {

		@Override
		public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            updateDisplay();
		}
    };
    
    private View.OnClickListener mClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
    		Bundle bundle = new Bundle();
    		bundle.putInt("year", mYear);
    		bundle.putInt("monthOfYear", mMonth);
    		bundle.putInt("dayOfMonth", mDay);
    		intent.putExtras(bundle);
    		setResult(5, intent);
    		DatePickerDialogActivity.this.finish();
		}
	};

	@Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && isOutOfBounds(DatePickerDialogActivity.this, event)) {
        	this.finish();
            return true;
        }  
        return super.onTouchEvent(event);
    }  
  
    private boolean isOutOfBounds(Activity context, MotionEvent event) {
        final int x = (int) event.getX();
        final int y = (int) event.getY();
        final int slop = ViewConfiguration.get(context).getScaledWindowTouchSlop();
        final View decorView = context.getWindow().getDecorView();
        return (x < -slop) || (y < -slop)|| (x > (decorView.getWidth() + slop))|| (y > (decorView.getHeight() + slop));
    }
}
