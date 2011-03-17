package com.ucai.ui;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * 航班查询的tab主程序<br>
 * 该类包括了三种航班的查询方法，分别为：单程、往返、联程<br>
 * 由于是tab类，所以相应的具体查询功能在包括的类中。
 * @author lin
 *
 */
public class SearchTab extends TabActivity {
	/**
	 * 航班查询的tab主入口方法<br>
	 * 通过tab加载了单程、往返、联程的查询功能，他们分别是SearchSimple、SearchBackAndForth、SearchConnect
	 * @see SearchSimple
	 * @see SearchBackAndForth
	 * @see SearchConnect
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
