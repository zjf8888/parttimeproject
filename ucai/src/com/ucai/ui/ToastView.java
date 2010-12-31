package com.ucai.ui;

import com.alipay.AlixId;
import com.alipay.Api;
import com.alipay.BaseHelper;
import com.alipay.MobileSecurePayHelper;
import com.alipay.MobileSecurePayer;
import com.ucai.R;
import com.ucai.po.ReturnPo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ToastView extends Activity {
	private TextView info;
	private Button button;
	private Button back;
	private Button yes;
	private Button no;
	private RelativeLayout wait;
	private RelativeLayout had;
	private String url = "http://www.ecook.cn/accuracy/tradeServlet?forderid=";
	private static String TAG = "ToastView";
	private ProgressDialog mProgress = null;
	private String signInfo;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.toastview);
		final ReturnPo rpo = (ReturnPo) getIntent().getExtras().get("rpo");
		wait = (RelativeLayout) findViewById(R.id.wait);
		had = (RelativeLayout) findViewById(R.id.had);
		final boolean hadAlipay = checkAlipayHad();
		if (hadAlipay) {
			MobileSecurePayHelper mspHelper = new MobileSecurePayHelper();
			mspHelper.detectMobile_sp(this);
			Api api=new Api();
			signInfo=api.getSingInfo(rpo.getForderId());
			System.out.println(signInfo);
		}
		info = (TextView) findViewById(R.id.info);
		if (rpo.getCode().equals("1")) {
			info.setText("订票成功，pnr码为:" + rpo.getPnr());
		} else {
			info.setText(rpo.getInfo());
		}
		final String forderid = rpo.getForderId();
		url = url + forderid;
		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				info.setText("支付成功?");
				wait.setVisibility(View.GONE);
				had.setVisibility(View.VISIBLE);
				if (hadAlipay) {										
					pay(signInfo);
				} else {
					Uri uri = Uri.parse(url);
					Intent intent = new Intent(Intent.ACTION_VIEW, uri);
					startActivity(intent);
				}
			}
		});
		back = (Button) findViewById(R.id.back);
		back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		yes = (Button) findViewById(R.id.yes);
		yes.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(ToastView.this, OrderView.class);
				i.putExtra("forderid", forderid);
				startActivity(i);
			}
		});
		no = (Button) findViewById(R.id.no);
		no.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				wait.setVisibility(View.VISIBLE);
				had.setVisibility(View.GONE);
			}
		});
	}

	private boolean checkAlipayHad() {
		WifiManager wm = (WifiManager) this
				.getSystemService(Context.WIFI_SERVICE);
		if (wm.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {
			return true;
		}
		return false;
	}

	public static class AlixOnCancelListener implements
			DialogInterface.OnCancelListener {
		Activity mcontext;

		public AlixOnCancelListener(Activity context) {
			mcontext = context;
		}

		@Override
		public void onCancel(DialogInterface dialog) {
			mcontext.onKeyDown(KeyEvent.KEYCODE_BACK, null);
		}
	}

	private void pay(String signInfo) {
		// start the pay.
		MobileSecurePayer msp = new MobileSecurePayer();
		boolean bRet = msp.pay(signInfo, mHandler, AlixId.RQF_PAY, this);

		if (bRet) {
			// show the progress bar to indicate that we have started paying.
			closeProgress();
			mProgress = BaseHelper
					.showProgress(this, null, "正在支付", false, true);
		}
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			try {
				String strRet = (String) msg.obj;

				switch (msg.what) {
				case AlixId.RQF_PAY: {
					//
					closeProgress();

					BaseHelper.log(TAG, strRet);

					try {
						String memo = "memo=";
						int imemoStart = strRet.indexOf("memo=");
						imemoStart += memo.length();
						int imemoEnd = strRet.indexOf(";result=");
						memo = strRet.substring(imemoStart, imemoEnd);

						BaseHelper.showDialog(ToastView.this, "提示", memo,
								R.drawable.infoicon);
					} catch (Exception e) {
						e.printStackTrace();

						BaseHelper.showDialog(ToastView.this, "提示", strRet,
								R.drawable.infoicon);
					}
				}
					break;
				}

				super.handleMessage(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	//
	// close the progress bar
	void closeProgress() {
		try {
			if (mProgress != null) {
				mProgress.dismiss();
				mProgress = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
