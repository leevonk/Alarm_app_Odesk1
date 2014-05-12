package com.meshcron.alarm_app;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alarm_app.R;

public class Rel_tags extends Activity {
	RadioGroup grp;
	RadioButton sel;
	Button cont_rel;
	ListView listView;
	boolean[] checkArray;
	ArrayList<String> checkedItem = new ArrayList<String>();
	private final String TAG = "Action Activity"; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_rel_tags);
		//grp=(RadioGroup)findViewById(R.id.radioGroup1);
		cont_rel=(Button)findViewById(R.id.cont_rel);
		listView = (ListView)findViewById(R.id.listView1);
		ArrayList<String> set = new ArrayList<String>();
		set.add("Alcohol");
		set.add("Caffein");
		set.add("Sleeping Pill");
		set.add("Exercise");
		set.add("Light");
		set.add("Nicotin");
		set.add("Noise");
		set.add("Pain");
		set.add("Partner");
		set.add("Temprature");
		CustomAdapter actionAdapter = new CustomAdapter(Rel_tags.this,set);
		listView.setAdapter(actionAdapter);
		cont_rel.setOnClickListener(new OnClickListener() { 
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				SharedPreferences preferences = getSharedPreferences("rel_val", Context.MODE_PRIVATE);
//				Editor editor = preferences.edit();
//				editor.putString("tag",sel.getText().toString());
//				editor.commit();
				Log.i(TAG,"Selected Data:- "+ checkedItem.toString());
				Intent slp=new Intent(Rel_tags.this,Other_notes.class);
				startActivity(slp);
			}
		});
	}
	private class CustomAdapter extends ArrayAdapter<String>{
		private Context c;
		private ArrayList<String> itemList = null;
		class ViewHolder{
			public CheckBox checkBox;
			public TextView textView;
		}
	
		public CustomAdapter(Context context,List<String> objects) {
			super(context, 0, objects);
			c = context;
			itemList = (ArrayList<String>) objects;
			checkArray = new boolean[objects.size()];
		}
		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View v = convertView;
			final ViewHolder holder; 
			if(v == null){
				LayoutInflater li = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = li.inflate(R.layout.action_item, null);
				holder = new ViewHolder();
				holder.checkBox = (CheckBox) v.findViewById(R.id.itemCheckBox);
				holder.checkBox.setTag(new Integer(position));
				v.setTag(holder);
				//				View v = li.inflate(resource, null);
				//				holder.checkBox = (CheckBox) v.findViewById(12);
			}else{
				holder = (ViewHolder)v.getTag();
			}
			
			if(checkArray[position]){
				holder.checkBox.setChecked(true);	
			}else {
				holder.checkBox.setChecked(false);
			}
			holder.checkBox.setText(itemList.get(position));
			holder.checkBox.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					int i = (Integer)v.getTag();
					if(holder.checkBox.isChecked()){
						checkArray[position] = true;
						checkedItem.add(itemList.get(position));
					}else{
						checkArray[position] = false;
						checkedItem.remove(itemList.get(position));
					}
				}
			});

			return v;
		}
		
		
		
		
	}
}