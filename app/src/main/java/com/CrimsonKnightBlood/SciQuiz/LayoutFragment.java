package com.CrimsonKnightBlood.SciQuiz;

import android.os.Bundle;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class LayoutFragment extends Fragment {
	// Store instance variables
	private String TXT;
	private int ID;

	// newInstance constructor for creating fragment with arguments
	public static LayoutFragment newInstance(int id, String txt) {
		LayoutFragment fragmentFirst = new LayoutFragment();
		Bundle args = new Bundle();
		args.putInt("someInt", id);
		args.putString("someTitle", txt);
		fragmentFirst.setArguments(args);
		return fragmentFirst;
	}

	// Store instance variables based on arguments passed
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ID = getArguments().getInt("someInt", 0);
		TXT = getArguments().getString("someTitle");
	}

	// Inflate the view for the fragment based on layout XML
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.layout_fragment, container, false);
		ImageView img = (ImageView) v.findViewById(R.id.fragment_image);
		TextView tv = (TextView) v.findViewById(R.id.fragment_text);
		img.setImageResource(ID);
		tv.setText(TXT);
		return v;
	}
}
