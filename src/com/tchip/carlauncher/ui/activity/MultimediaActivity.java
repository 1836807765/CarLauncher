package com.tchip.carlauncher.ui.activity;

import com.tchip.carlauncher.R;
import com.tchip.carlauncher.view.ButtonFloat;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class MultimediaActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_multimedia);
		initLayout();
	}

	private void initLayout() {
		// 图片
		LinearLayout layoutImage = (LinearLayout) findViewById(R.id.layoutImage);
		layoutImage.setOnClickListener(new MyOnClickListener());
		
		// 人脸检测
		LinearLayout layoutFaceDetect = (LinearLayout) findViewById(R.id.layoutFaceDetect);
		layoutFaceDetect.setOnClickListener(new MyOnClickListener());

		// 音乐
		LinearLayout layoutMusic = (LinearLayout) findViewById(R.id.layoutMusic);
		layoutMusic.setOnClickListener(new MyOnClickListener());

		// 视频
		LinearLayout layoutVideo = (LinearLayout) findViewById(R.id.layoutVideo);
		layoutVideo.setOnClickListener(new MyOnClickListener());

		ButtonFloat btnToMainFromMultimedia = (ButtonFloat) findViewById(R.id.btnToMainFromMultimedia);
		btnToMainFromMultimedia.setDrawableIcon(getResources().getDrawable(
				R.drawable.icon_arrow_up));
		btnToMainFromMultimedia.setOnClickListener(new MyOnClickListener());

	}

	class MyOnClickListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.layoutImage:
				ComponentName componentImage = new ComponentName(
						"com.android.gallery3d",
						"com.android.gallery3d.app.GalleryActivity");
				Intent intentImage = new Intent();
				intentImage.setComponent(componentImage);
				startActivity(intentImage);
				break;
			case R.id.layoutFaceDetect:
				Intent intentFaceDetect = new Intent(getApplicationContext(),
						FaceDetectActivity.class);
				startActivity(intentFaceDetect);
				break;
			case R.id.layoutMusic:
				Intent intentMusic = new Intent(getApplicationContext(),
						MusicMainContentActivity.class);
				startActivity(intentMusic);
				break;
			case R.id.layoutVideo:
				ComponentName componentVideo = new ComponentName(
						"com.mediatek.videoplayer",
						"com.mediatek.videoplayer.MovieListActivity");
				Intent intentVideo = new Intent();
				intentVideo.setComponent(componentVideo);
				startActivity(intentVideo);
				break;
			case R.id.btnToMainFromMultimedia:
				backToMain();
				break;
			}

		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			backToMain();
			return true;
		} else
			return super.onKeyDown(keyCode, event);
	}

	private void backToMain() {
		finish();
		overridePendingTransition(R.anim.zms_translate_down_out,
				R.anim.zms_translate_down_in);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		View decorView = getWindow().getDecorView();
		decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
	}
}