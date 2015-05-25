package com.looip.crm.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.looip.crm.R;
import com.looip.crm.projectinfo.Activity_ListView;

public class Activity_Call extends Activity_ListView{
	private static final int DIALOG_TEXT_ENTRY = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		showDialog(DIALOG_TEXT_ENTRY);
	}
	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		switch (id) {
		case DIALOG_TEXT_ENTRY:
			// This example shows how to add a custom layout to an AlertDialog
			LayoutInflater factory =(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
			//  LayoutInflater factory = LayoutInflater.from(this);
			final View textEntryView = factory.inflate(R.layout.dialot_call, null);
			// AlertDialog ad=new AlertDialog.Builder(AlertDialogSamples.this)
			final Dialog ad=  new Dialog(this, R.style.Theme_CustomDialog2);
			ad.setContentView(textEntryView);
			TextView tv_call_sure=(TextView) textEntryView.findViewById(R.id.tv_call_sure);
			TextView tv_call_cancel=(TextView) textEntryView.findViewById(R.id.tv_call_cancel);
			tv_call_sure.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

				}
			});
			tv_call_cancel.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					ad.dismiss();
				}
			});
			return ad;
		}
		return null;
	}

}
