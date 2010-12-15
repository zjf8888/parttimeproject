package com.ucai.ui;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class SearchTab extends TabActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final TabHost tabHost = getTabHost();

		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("单程")
				.setContent(new Intent(this, SearchSimple.class)));

		tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("往返")
				.setContent(new Intent(this, SearchSimple.class)));

		// This tab sets the intent flag so that it is recreated each time
		// the tab is clicked.
		tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("联程")
				.setContent(new Intent(this, SearchSimple.class)));
	}
}
