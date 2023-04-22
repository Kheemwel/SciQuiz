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
import com.CrimsonKnightBlood.SciQuiz.Quiz.MCQuestions;
import com.CrimsonKnightBlood.SciQuiz.Quiz.MCChemistryChoices;
import com.CrimsonKnightBlood.SciQuiz.Quiz.MCAnswers;
public class ChemistryMC extends Activity
{
	SharedPreferences sp, ap;
	SharedPreferences.Editor editor;
	MediaPlayer correctMP, wrongMP, endMP;

	LinearLayout lltitle, llitem;
	Button bttn1, bttn2, bttn3, bttn4;
	TextView title, score, item, question;

	private MCQuestions Q = new MCQuestions();
	private MCChemistryChoices C = new MCChemistryChoices();
	private MCAnswers A = new MCAnswers();

	private int QL = Q.ChemistryQuestion.length;
	private int ql = QL + 1;
	private int scr = 0;
	private int ni = 1;
	private int rndm = 0;
	private int index;
	private int timeout = 1000;
	boolean activated;
	int color;

	private String answer;

	Integer arr[] = new Integer[QL];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.multiplechoice_quiz);

		bttn1 = (Button) findViewById(R.id.mcbutton1);
		bttn2 = (Button) findViewById(R.id.mcbutton2);
		bttn3 = (Button) findViewById(R.id.mcbutton3);
		bttn4 = (Button) findViewById(R.id.mcbutton4);

		title = (TextView) findViewById(R.id.mctitle);
		score = (TextView) findViewById(R.id.mcscore);
		item = (TextView) findViewById(R.id.mcitem);
		question = (TextView) findViewById(R.id.mcquestion);
		lltitle = (LinearLayout) findViewById(R.id.multiplechoicequizLinearLayout1);
		llitem = (LinearLayout) findViewById(R.id.multiplechoicequizLinearLayout2);
		
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

		title.setText("Chemistry");
		score.setText("" + scr);
		item.setText(ni + "/" + QL);

		for(int i = 0; i < QL; i++) {
			arr[i] = i;
		}

		Collections.shuffle(Arrays.asList(arr));

		Index(arr[rndm]);
	    UpdateQuestion(index);

		bttn1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (ni != QL) {
						if (bttn1.getText() == answer) {
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
							bttn3.setEnabled(false);
							bttn4.setEnabled(false);
						} else {
							WrongSound();
							bttn1.setBackgroundResource(R.drawable.wrong_redbutton);
							if(bttn2.getText() == answer) {
								bttn2.setBackgroundResource(R.drawable.correct_greenbutton);
							} else if(bttn3.getText() == answer) {
								bttn3.setBackgroundResource(R.drawable.correct_greenbutton);
							} else {
								bttn4.setBackgroundResource(R.drawable.correct_greenbutton);
							}

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
							bttn3.setEnabled(false);
							bttn4.setEnabled(false);
						}
					} else {
						if (bttn1.getText() == answer) {
							CorrectSound();
							bttn1.setBackgroundResource(R.drawable.correct_greenbutton);
							scr++;
							score.setText("" + scr);
						} else {
							WrongSound();
							bttn1.setBackgroundResource(R.drawable.wrong_redbutton);
							if(bttn2.getText() == answer) {
								bttn2.setBackgroundResource(R.drawable.correct_greenbutton);
							} else if(bttn3.getText() == answer) {
								bttn3.setBackgroundResource(R.drawable.correct_greenbutton);
							} else {
								bttn4.setBackgroundResource(R.drawable.correct_greenbutton);
							}
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
						if (bttn2.getText() == answer) {
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
							bttn3.setEnabled(false);
							bttn4.setEnabled(false);
						} else {
							WrongSound();
							bttn2.setBackgroundResource(R.drawable.wrong_redbutton);
							if(bttn3.getText() == answer) {
								bttn3.setBackgroundResource(R.drawable.correct_greenbutton);
							} else if(bttn4.getText() == answer) {
								bttn4.setBackgroundResource(R.drawable.correct_greenbutton);
							} else {
								bttn1.setBackgroundResource(R.drawable.correct_greenbutton);
							}

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
							bttn3.setEnabled(false);
							bttn4.setEnabled(false);
						}
					} else {
						if (bttn2.getText() == answer) {
							CorrectSound();
							bttn2.setBackgroundResource(R.drawable.correct_greenbutton);
							scr++;
							score.setText("" + scr);
						} else {
							WrongSound();
							bttn2.setBackgroundResource(R.drawable.wrong_redbutton);
							if(bttn3.getText() == answer) {
								bttn3.setBackgroundResource(R.drawable.correct_greenbutton);
							} else if(bttn4.getText() == answer) {
								bttn4.setBackgroundResource(R.drawable.correct_greenbutton);
							} else {
								bttn1.setBackgroundResource(R.drawable.correct_greenbutton);
							}
						}
						SAVE();
						END();
					}
				}
			});

		bttn3.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (ni != QL) {
						if (bttn3.getText() == answer) {
							CorrectSound();
							bttn3.setBackgroundResource(R.drawable.correct_greenbutton);

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
							bttn3.setEnabled(false);
							bttn4.setEnabled(false);
						} else {
							WrongSound();
							bttn3.setBackgroundResource(R.drawable.wrong_redbutton);
							if(bttn4.getText() == answer) {
								bttn4.setBackgroundResource(R.drawable.correct_greenbutton);
							} else if (bttn1.getText() == answer) {
								bttn1.setBackgroundResource(R.drawable.correct_greenbutton);
							} else {
								bttn2.setBackgroundResource(R.drawable.correct_greenbutton);
							}

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
							bttn3.setEnabled(false);
							bttn4.setEnabled(false);
						}
					} else {
						if (bttn3.getText() == answer) {
							CorrectSound();
							bttn3.setBackgroundResource(R.drawable.correct_greenbutton);
							scr++;
							score.setText("" + scr);
						} else {
							WrongSound();
							bttn3.setBackgroundResource(R.drawable.wrong_redbutton);
							if(bttn4.getText() == answer) {
								bttn4.setBackgroundResource(R.drawable.correct_greenbutton);
							} else if (bttn1.getText() == answer) {
								bttn1.setBackgroundResource(R.drawable.correct_greenbutton);
							} else {
								bttn2.setBackgroundResource(R.drawable.correct_greenbutton);
							}
						}
						SAVE();
						END();
					}
				}
			});

		bttn4.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (ni != QL) {
						if (bttn4.getText() == answer) {
							CorrectSound();
							bttn4.setBackgroundResource(R.drawable.correct_greenbutton);

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
							bttn3.setEnabled(false);
							bttn4.setEnabled(false);
						} else {
							WrongSound();
							bttn4.setBackgroundResource(R.drawable.wrong_redbutton);
							if(bttn1.getText() == answer) {
								bttn1.setBackgroundResource(R.drawable.correct_greenbutton);
							} else if (bttn2.getText() == answer) {
								bttn2.setBackgroundResource(R.drawable.correct_greenbutton);
							} else {
								bttn3.setBackgroundResource(R.drawable.correct_greenbutton);
							}

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
							bttn3.setEnabled(false);
							bttn4.setEnabled(false);
						}
					} else {
						if (bttn4.getText() == answer) {
							CorrectSound();
							bttn4.setBackgroundResource(R.drawable.correct_greenbutton);
							scr++;
							score.setText("" + scr);
						} else {
							WrongSound();
							bttn4.setBackgroundResource(R.drawable.wrong_redbutton);
							if(bttn1.getText() == answer) {
								bttn1.setBackgroundResource(R.drawable.correct_greenbutton);
							} else if (bttn2.getText() == answer) {
								bttn2.setBackgroundResource(R.drawable.correct_greenbutton);
							} else {
								bttn3.setBackgroundResource(R.drawable.correct_greenbutton);
							}
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
		if(scr > sp.getInt("chemist_mcscore", 0)) {
			editor.putInt("chemist_mcscore", scr);
			editor.commit();
		}
	}

	private void Index(int indx) {
		index = indx;
	}

	private void UpdateQuestion(int num) {
		question.setText(Q.getChemistryQuestion(num));
		bttn1.setText(C.getChoice1(num));
		bttn2.setText(C.getChoice2(num));
		bttn3.setText(C.getChoice3(num));
		bttn4.setText(C.getChoice4(num));
		answer = A.getChemistryAnswer(num);

		bttn1.setBackgroundResource(R.drawable.button_style);
		bttn2.setBackgroundResource(R.drawable.button_style);
		bttn3.setBackgroundResource(R.drawable.button_style);
		bttn4.setBackgroundResource(R.drawable.button_style);

		bttn1.setEnabled(true);
		bttn2.setEnabled(true);
		bttn3.setEnabled(true);
		bttn4.setEnabled(true);
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
					startActivity(new Intent(getApplicationContext(), ChemistryMC.class));
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
