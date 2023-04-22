package com.CrimsonKnightBlood.SciQuiz.Topic;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.Context;

import android.media.MediaPlayer;

import android.view.View;

import android.widget.LinearLayout;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collections;
import java.util.Arrays;

import com.CrimsonKnightBlood.SciQuiz.R;
import com.CrimsonKnightBlood.SciQuiz.Quiz.TFQuestions;
import com.CrimsonKnightBlood.SciQuiz.Quiz.TFAnswers;
public class EarthScienceTF extends Activity
{
	SharedPreferences sp, ap;
	SharedPreferences.Editor editor;
	MediaPlayer correctMP, wrongMP, endMP;

	LinearLayout lltitle, llitem;
	Button bttn1, bttn2;
	TextView title, score, item, question;

	private TFQuestions Q = new TFQuestions();
	private TFAnswers A = new TFAnswers();

	private int QL = Q.EarthScienceQuestion.length;
	private int ql = QL + 1;
	private int scr = 0;
	private int ni = 1;
	private int rndm = 0;
	private int index;
	private int timeout = 1000;
	private boolean answer;
	boolean activated;
	int color;

	Integer arr[] = new Integer[QL];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trueorfalse_quiz);

		bttn1 = (Button) findViewById(R.id.tfbutton1);
		bttn2 = (Button) findViewById(R.id.tfbutton2);

		title = (TextView) findViewById(R.id.tftitle);
		score = (TextView) findViewById(R.id.tfscore);
		item = (TextView) findViewById(R.id.tfitem);
		question = (TextView) findViewById(R.id.tfquestion);
		lltitle = (LinearLayout) findViewById(R.id.trueorfalsequizLinearLayout1);
		llitem = (LinearLayout) findViewById(R.id.trueorfalsequizLinearLayout2);
		
		sp = getSharedPreferences("com.CrimsonKnightBlood.SciQuiz.sharedpreferences", Context.MODE_PRIVATE);
		ap = getSharedPreferences("app_preferences", Context.MODE_PRIVATE);
		editor = sp.edit();
		activated = ap.getBoolean("activate_sound", true);
		color = ap.getInt("color_scheme", 0);
		ColorChange(color);

		correctMP = MediaPlayer.create(this, R.raw.correct);
		wrongMP = MediaPlayer.create(this, R.raw.wrong);
		endMP = MediaPlayer.create(this, R.raw.congratulation);

		correctMP.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
				public void onCompletion(MediaPlayer mp) {
					correctMP.release();
					correctMP = MediaPlayer.create(getApplication(), R.raw.correct);
				}
			});

		wrongMP.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
				public void onCompletion(MediaPlayer mp) {
					wrongMP.release();
					wrongMP = MediaPlayer.create(getApplication(), R.raw.wrong);
				}
			});

		endMP.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
				public void onCompletion(MediaPlayer mp) {
					endMP.release();
					endMP = MediaPlayer.create(getApplication(), R.raw.congratulation);
				}
			});

		title.setText("Earth Science");
		score.setText("" + scr);
		item.setText(ni + "/" + QL);

		for(int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}

		Collections.shuffle(Arrays.asList(arr));

		Index(arr[rndm]);
		UpdateQuestion(index);

		bttn1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (ni != QL) {
						if (answer == true) {
							CorrectSound();
							bttn1.setBackgroundResource(R.drawable.correct_greenbutton);

							scr++;
							score.setText("" + scr);
							ni++;

							new Handler().postDelayed(new Runnable() {
									@Override
									public void run() {
										if (ni < ql) {
											item.setText(ni + "/" + QL);
											rndm++;
											Index(arr[rndm]);
											UpdateQuestion(index);
										}
									}
								}, timeout);

							bttn1.setEnabled(false);
							bttn2.setEnabled(false);
						} else {
							WrongSound();
							bttn1.setBackgroundResource(R.drawable.wrong_redbutton);
							bttn2.setBackgroundResource(R.drawable.correct_greenbutton);

							ni++;

							new Handler().postDelayed(new Runnable() {
									@Override
									public void run() {
										if (ni < ql) {
											item.setText(ni + "/" + QL);
											rndm++;
											Index(arr[rndm]);
											UpdateQuestion(index);
										}
									}
								}, timeout);

							bttn1.setEnabled(false);
							bttn2.setEnabled(false);
						}
					} else {
						if (answer == true) {
							CorrectSound();
							bttn1.setBackgroundResource(R.drawable.correct_greenbutton);
							scr++;
							score.setText("" + scr);
						} else {
							WrongSound();
							bttn1.setBackgroundResource(R.drawable.wrong_redbutton);
							bttn2.setBackgroundResource(R.drawable.correct_greenbutton);
						}
						SAVE();
						END();
					}
				}
			});

		bttn2.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (ni != QL) {
						if (answer == false) {
							CorrectSound();
							bttn2.setBackgroundResource(R.drawable.correct_greenbutton);

							scr++;
							score.setText("" + scr);
							ni++;

							new Handler().postDelayed(new Runnable() {
									@Override
									public void run() {
										if (ni < ql) {
											item.setText(ni + "/" + QL);
											rndm++;
											Index(arr[rndm]);
											UpdateQuestion(index);
										}
									}
								}, timeout);

							bttn1.setEnabled(false);
							bttn2.setEnabled(false);
						} else {
							WrongSound();
							bttn2.setBackgroundResource(R.drawable.wrong_redbutton);
							bttn1.setBackgroundResource(R.drawable.correct_greenbutton);

							ni++;

							new Handler().postDelayed(new Runnable() {
									@Override
									public void run() {
										if (ni < ql) {
											item.setText(ni + "/" + QL);
											rndm++;
											Index(arr[rndm]);
											UpdateQuestion(index);
										}
									}
								}, timeout);

							bttn1.setEnabled(false);
							bttn2.setEnabled(false);
						}
					} else {
						if (answer = false) {
							CorrectSound();
							bttn2.setBackgroundResource(R.drawable.correct_greenbutton);
							scr++;
							score.setText("" + scr);
						} else {
							WrongSound();
							bttn2.setBackgroundResource(R.drawable.wrong_redbutton);
							bttn1.setBackgroundResource(R.drawable.correct_greenbutton);
						}
						SAVE();
						END();
					}
				}
			});

	}
	
	public void CorrectSound() {
		if(activated) {
			if(correctMP.isPlaying()) {
				correctMP.stop();
				correctMP = MediaPlayer.create(this, R.raw.correct);
				correctMP.start();
			} else {
				correctMP.start();
			}
		}
	}

	public void WrongSound() {
		if(activated) {
			if(wrongMP.isPlaying()) {
				wrongMP.stop();
				wrongMP = MediaPlayer.create(this, R.raw.wrong);
				wrongMP.start();
			} else {
				wrongMP.start();
			}
		}
	}

	public void EndSound() {
		if(activated) {
			if(endMP.isPlaying()) {
				endMP.stop();
				endMP = MediaPlayer.create(this, R.raw.congratulation);
				endMP.start();
			} else {
				endMP.start();
			}
		}
	}
	
	public void ColorChange(int clr) {
		switch(clr) {
			case 0:
				lltitle.setBackgroundResource(R.color.blue_500);
				llitem.setBackgroundResource(R.color.lightblue_500);
				break;
			case 1:
				lltitle.setBackgroundResource(R.color.green_500);
				llitem.setBackgroundResource(R.color.lightgreen_500);
				break;
			case 2:
				lltitle.setBackgroundResource(R.color.purple_500);
				llitem.setBackgroundResource(R.color.pink_500);
				break;
			case 3:
				lltitle.setBackgroundResource(R.color.orange_500);
				llitem.setBackgroundResource(R.color.amber_500);
				break;
		}
	}
	
	public void BackButton(View v) {
		EXIT();
	}

	private void SAVE() {
		if(scr > sp.getInt("earth_tfscore", 0)) {
			editor.putInt("earth_tfscore", scr);
			editor.commit();
		}
	}

	private void Index(int indx) {
		index = indx;
	}

	private void UpdateQuestion(int num) {
		question.setText(Q.getEarthScienceQuestion(num));
		answer = A.getEarthScienceAnswer(num);
		
		bttn1.setBackgroundResource(R.drawable.button_style);
		bttn2.setBackgroundResource(R.drawable.button_style);

		bttn1.setEnabled(true);
		bttn2.setEnabled(true);
	}

	private void END() {
		AlertDialog.Builder build = new AlertDialog.Builder(this);
		build
			.setCancelable(false)
			.setTitle("Congratulations!!!")
			.setMessage("Your Score is " + scr + " points")
			.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface di, int i) {
					startActivity(new Intent(getApplicationContext(), EarthScienceTF.class));
					finish();
				}
			})
			.setNegativeButton("Quit", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface di, int i) {
					finish();
				}
			})
			.create().show();
		EndSound();
	}

	private void EXIT() {
		AlertDialog.Builder build = new AlertDialog.Builder(this);
		build
			.setCancelable(false)
			.setTitle("Hmmm...😐")
			.setMessage("The quiz is not yet done. Are you sure you want to exit?")
			.setPositiveButton("YES", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface di, int i) {
					SAVE();
					finish();
				}
			})
			.setNegativeButton("NO", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface di, int i) {
					di.dismiss();
				}
			})
			.create().show();
	}

	@Override
	public void onBackPressed() {
		// TODO: Implement this method
		EXIT();
	}

	@Override
	protected void onPause()
	{
		// TODO: Implement this method
		super.onPause();
		SAVE();
	}

}
