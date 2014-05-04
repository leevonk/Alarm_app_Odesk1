package com.meshcron.alarm_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.alarm_app.R;

public class Rel_tags extends Activity {
	RadioGroup grp;
	RadioButton sel;
	Button cont_rel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_rel_tags);
		grp=(RadioGroup)findViewById(R.id.radioGroup1);
		cont_rel=(Button)findViewById(R.id.cont_rel);
		cont_rel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 int selectedId = grp.getCheckedRadioButtonId();

		            // find the radiobutton by returned id
		                sel = (RadioButton) findViewById(selectedId);

		            Toast.makeText(Rel_tags.this,
		                sel.getText(), Toast.LENGTH_SHORT).show();
		            SharedPreferences preferences = getSharedPreferences("rel_val", Context.MODE_PRIVATE);
					Editor editor = preferences.edit();
					editor.putString("tag",sel.getText().toString());
					editor.commit();
					Intent slp=new Intent(Rel_tags.this,Other_notes.class);
					startActivity(slp);
			}
		});
	}
}