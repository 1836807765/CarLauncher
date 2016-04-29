package com.tchip.carlauncher.model;

import java.util.Calendar;

import com.tchip.carlauncher.MyApp;
import com.tchip.carlauncher.util.HintUtil;
import com.tchip.carlauncher.util.MyLog;
import com.tchip.carlauncher.util.SettingUtil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TimeTickReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// 获取时间
		Calendar calendar = Calendar.getInstance();
		int minute = calendar.get(Calendar.MINUTE);
		if (minute == 0) {
			int year = calendar.get(Calendar.YEAR);
			MyLog.v("[TimeTickReceiver]Year:" + year);

			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			if (MyApp.isAccOn) { // ACC_ON
				if (year >= 2016) {
					if (1 == SettingUtil.getAccStatus()) { // 再次确认
						startSpeak(context, "整点报时:" + hour + "点整");
					}
				}
			} else { // ACC_OFF
				if (hour == 3) { // 凌晨3点重启机器
					context.sendBroadcast(new Intent(
							"tchip.intent.action.ACTION_REBOOT"));
				}
			}
		}
	}

	private void startSpeak(Context context, String content) {
		HintUtil.speakVoice(context, content);
	}

}
