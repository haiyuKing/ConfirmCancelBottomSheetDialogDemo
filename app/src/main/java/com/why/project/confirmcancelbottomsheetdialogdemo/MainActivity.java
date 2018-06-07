package com.why.project.confirmcancelbottomsheetdialogdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.why.project.confirmcancelbottomsheetdialogdemo.confirmcancel.ConfirmCancelBottomSheetDialog;

public class MainActivity extends AppCompatActivity {

	private Button btn_open;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initViews();
		initEvents();
	}

	private void initViews() {
		btn_open = findViewById(R.id.btn_open);
	}

	private void initEvents() {
		btn_open.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				ConfirmCancelBottomSheetDialog delDialog = new ConfirmCancelBottomSheetDialog(MainActivity.this, new ConfirmCancelBottomSheetDialog.DialogSetListener() {
					@Override
					public void setDialog(TextView title, TextView leftBtn, TextView rightBtn) {
						title.setText("是否删除？");
					}
				});

				delDialog.setDialogClickListener(new ConfirmCancelBottomSheetDialog.DialogClickListener() {
					@Override
					public void leftClickListener() {
						Toast.makeText(MainActivity.this,"取消",Toast.LENGTH_SHORT).show();
					}

					@Override
					public void rightClickListener() {
						Toast.makeText(MainActivity.this,"确认",Toast.LENGTH_SHORT).show();
					}
				});

				delDialog.show();
			}
		});
	}
}
