package com.why.project.confirmcancelbottomsheetdialogdemo.confirmcancel;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.why.project.confirmcancelbottomsheetdialogdemo.R;


/**
 * Used 底部弹出来的确认取消对话框【简约版的样式，只有标题、取消、确定两个按钮】
 */

public class ConfirmCancelBottomSheetDialog extends BottomSheetDialog {
	private static final String TAG = ConfirmCancelBottomSheetDialog.class.getSimpleName();

	private Context mContext;

	/**设置对话框内容和样式的监听器（标题、按钮样式，包括控制隐藏）*/
	private DialogSetListener mDialogSetListener;
	/**三个按钮的点击事件监听器*/
	private DialogClickListener mDialogClickListener;

	private TextView mTitle;
	private TextView mLeftBtn;
	private TextView mRightBtn;

	public ConfirmCancelBottomSheetDialog(@NonNull Context context, DialogSetListener dialogSetListener) {
		super(context);
		mContext = context;
		mDialogSetListener = dialogSetListener;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_bottomsheet_confirm_cancel);

		//可以变相实现底部外边距效果
		int screenHeight = getScreenHeight((Activity) mContext);
		int statusBarHeight = getStatusBarHeight(getContext());
		int dialogHeight = screenHeight;//
//		getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, dialogHeight == 0 ? ViewGroup.LayoutParams.MATCH_PARENT : dialogHeight);
		getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);//红米6pro适配
		//设置透明
		getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);

		initViews();
		initEvents();
	}

	private static int getScreenHeight(Activity activity) {
		DisplayMetrics displaymetrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		return displaymetrics.heightPixels;
	}

	private static int getStatusBarHeight(Context context) {
		int statusBarHeight = 0;
		Resources res = context.getResources();
		int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			statusBarHeight = res.getDimensionPixelSize(resourceId);
		}
		return statusBarHeight;
	}

	private void initViews() {

		mTitle = findViewById(R.id.confirm_title);
		mLeftBtn = findViewById(R.id.left_btn);
		mRightBtn = findViewById(R.id.right_btn);

		//==========================初始展现==========================
		if(mDialogSetListener != null){
			mDialogSetListener.setDialog(mTitle, mLeftBtn, mRightBtn);
		}

	}

	private void initEvents() {
		mLeftBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mDialogClickListener != null){
					mDialogClickListener.leftClickListener();
				}
				dismiss();
			}
		});

		mRightBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mDialogClickListener != null){
					mDialogClickListener.rightClickListener();
				}
				dismiss();
			}
		});
	}

	/**设置对话框内容和样式的监听器（标题、内容、按钮样式，包括控制隐藏）*/
	public static abstract interface DialogSetListener
	{
		/**设置标题、内容、按钮的文本以及按钮的显示隐藏
		 * @param title - 标题控件【默认“提示”】
		 * @param leftBtn - 左侧按钮控件【默认“确定”】
		 * @param rightBtn - 右侧按钮控件【默认“取消”】*/
		public abstract void setDialog(TextView title, TextView leftBtn, TextView rightBtn);
	}

	public void setDialogSetListener(DialogSetListener dialogSetListener) {
		mDialogSetListener = dialogSetListener;
	}

	/**三个按钮的点击事件监听器*/
	public static abstract interface DialogClickListener
	{
		/**取消按钮*/
		public abstract void leftClickListener();
		/**确定按钮*/
		public abstract void rightClickListener();
	}

	public void setDialogClickListener(DialogClickListener dialogClickListener) {
		mDialogClickListener = dialogClickListener;
	}
}
