package com.ucai.ui;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * 航班查询的tab主程序
 * @author lin
 *
 */
public class SearchTab extends TabActivity {
	/**
	 * 程序入口
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final TabHost tabHost = getTabHost();

		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("单程")
				.setContent(new Intent(this, SearchSimple.class)));

		tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("往返")
				.setContent(new Intent(this, SearchBackAndForth.class)));

		tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("联程")
				.setContent(new Intent(this, SearchConnect.class)));
	}
}
