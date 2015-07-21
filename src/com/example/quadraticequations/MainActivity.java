package com.example.quadraticequations;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView txtSolution;
	EditText txt_a, txt_b, txt_c;
	double a,b,c,d,sqrt_d,x1,x2;
	final int DIALOG_EXIT = 1;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txt_a = (EditText)findViewById(R.id.a);
		txt_b = (EditText)findViewById(R.id.b);
		txt_c = (EditText)findViewById(R.id.c);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
			builder.setTitle(getString(R.string.app_name))
					.setMessage(getString(R.string.author))
					.setIcon(R.drawable.ic_launcher)
					.setCancelable(false)
					.setPositiveButton("Œ ",
							new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();
						}
					});
			AlertDialog alert = builder.create();
			alert.show();
			//			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onClick(View v){

		txtSolution = (TextView) findViewById(R.id.txt_solution);
		StringBuilder sb = new StringBuilder();
	
		try {
			//Toast.makeText(this, txt_a.getText(), Toast.LENGTH_SHORT).show();
			a = Double.parseDouble(txt_a.getText().toString());
			b = Double.parseDouble(txt_b.getText().toString());
			c = Double.parseDouble(txt_c.getText().toString());
			
			if (a == 0) {
				if (b == 0) {
					if (c == 0) {
						sb.append(getString(R.string.any_number));
					} else {
						sb.append(getString(R.string.no_solution));
					}
				} else {
					sb.append("x = -c/b\n");
					sb.append("x = " + convert(-c) + ")/" + convert(b) + " =" + replace(-c/b) + "\n");
				}
			} else {
				d = b * b - 4 * a *c;
				sqrt_d = Math.sqrt(d);
//				sqrt_d = (double) Math.round(Math.sqrt(d)*1000)/1000;
				
				if (d < 0.0) {
					sb.append("D = b\u00B2 - 4ac\n");
					sb.append("D = " + convert(b) + "\u00B2 - 4 * " + convert(a) + " * " + convert(c)  + " = " + replace(d) + "\n");
					sb.append(getString(R.string.no_solution));
				} else if (sqrt_d == 0) {
					x1 = (-b)/(2*a);
//					x1 = (double) Math.round(x1*1000)/1000;
					sb.append("D = b\u00B2 - 4ac\n");
					sb.append("D = " + convert(b) + "\u00B2 - 4 * " + convert(a) + " * " + convert(c)  + " = " + replace(d) + "\n");
					sb.append("\u221A(" + replace(d) + ") = " + replace(sqrt_d) +"\n");
					sb.append("x = " + convert(-b) + "/(2*" + convert(a) + " = " + replace(x1) + "\n");
				} else{
					x1 = (-b - sqrt_d)/(2*a);
//					x1 = (double) Math.round(x1*1000)/1000;
					x2 = (-b + sqrt_d)/(2*a);
//					x2 = (double) Math.round(x2*1000)/1000;
					sb.append("D = b\u00B2 - 4ac\n");
					sb.append("D = " + convert(b) + "\u00B2 - 4 * " + convert(a) + " * " + convert(c)  + " = " + replace(d) + "\n");
					sb.append("\u221A(" + replace(d) + ") = " + replace(sqrt_d) +"\n");
					sb.append("x1 = (-b - \u221A(D))/(2*a)\n");
					sb.append("x1 = (" + convert(-b) + " - " + replace(sqrt_d) + ")/(2*" + convert(a) + ") = " + replace(x1) + "\n");
					sb.append("x2 = (-b + \u221A(D))/(2*a)\n");
					sb.append("x2 = (" + convert(-b) + " + " + replace(sqrt_d) + ")/(2*" + convert(a) + ") = " + replace(x2) + "\n");
				}

			}
			
		} catch (Exception e) {
			sb.append(getString(R.string.incorrect_conditions));
		}
		
		
			
			txtSolution.setText(sb);
			
		
		
	}
	
	private String convert(double d){
		String s;
		if (d < 0) {
			s = "(" + d + ")";
		} else {
			s = "" + d;
		}
		return s.replace(".0", "");
	}

	private String replace(double d){
		String s;
		s = "" + d;
		return s.replace(".0", "");
	}
	
	
}
