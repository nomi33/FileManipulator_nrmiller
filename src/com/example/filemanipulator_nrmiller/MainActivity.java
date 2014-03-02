package com.example.filemanipulator_nrmiller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void ShowOnClick(View view) {
		TextView label = (TextView) findViewById(R.id.textView1);
		try {
			label.setText(this.onShow());
		} catch (IOException e1) {
			// 
			e1.printStackTrace();
		}
	}

	public void ReplaceOnClick(View view) {
		EditText e = (EditText)findViewById(R.id.EditBox);
		try {
			this.onReplace(e.getText().toString());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void AppendOnClick(View view) {
		EditText e = (EditText) findViewById(R.id.EditBox);
		String s = "";
		s = this.onAppend(e.getText().toString());
		try {
			this.onReplace(s);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	public boolean onReplace(String s) throws IOException {
		File f = new File(Environment.getExternalStorageDirectory().getPath(),
				"test.txt");
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileWriter fw;
		BufferedWriter bw;

		fw = new FileWriter(f.getAbsoluteFile());
		bw = new BufferedWriter(fw);
		bw.write(s);
		bw.close();
		fw.close();
		return true;
	}

	public String onAppend(String s) {
		try {
			s += this.onShow();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	
	}

	public String onShow() throws IOException {
		String s = "";
		File f = new File(Environment.getExternalStorageDirectory().getPath(),
				"test.txt");
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileReader fr;
		BufferedReader br;

		fr = new FileReader(f.getAbsoluteFile());
		br = new BufferedReader(fr);
		s = br.readLine();
		br.close();
		fr.close();

		return s;
	}
}
