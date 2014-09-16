package jp.clockup.mybars;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends Activity {
	ProgressBar mProgressBar;
	TextView mTextMain;
	TextView mTextSecondary;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// ウィジェット取得
		mProgressBar   = (ProgressBar)findViewById(R.id.progressBar1);
		mTextMain      = (TextView)findViewById(R.id.textMain);
		mTextSecondary = (TextView)findViewById(R.id.textSecondary);
		
		// SeekBarイベント
		SeekBar seek = (SeekBar)findViewById(R.id.seekBar1);
		seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override public void onStopTrackingTouch(SeekBar seekBar) { }
			@Override public void onStartTrackingTouch(SeekBar seekBar) { }
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				setProgressMain(progress);
			}
		});
		SeekBar seek2 = (SeekBar)findViewById(R.id.seekBar2);
		seek2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override public void onStopTrackingTouch(SeekBar seekBar) { }
			@Override public void onStartTrackingTouch(SeekBar seekBar) { }
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				setProgressSecondary(progress);
			}
		});
		
		// RatingBarイベント
		RatingBar rating = (RatingBar)findViewById(R.id.ratingBar1);
		rating.setNumStars(4);
		rating.setStepSize(1.0f);
		rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
				TextView text = (TextView)findViewById(R.id.textRating);
				text.setText(String.format("Rating: %f", rating));
				setProgressMain((int)(rating * 10));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// メインのプログレス値を変更
	public void buttonMainMinus(View button){
		setProgressMain(mProgressBar.getProgress() - 10);
	}
	public void buttonMainPlus(View button){
		setProgressMain(mProgressBar.getProgress() + 10);
	}
	
	// セカンドのプログレス値を変更
	public void buttonSecondaryMinus(View button){
		setProgressSecondary(mProgressBar.getSecondaryProgress() - 10);
	}
	public void buttonSecondaryPlus(View button){
		setProgressSecondary(mProgressBar.getSecondaryProgress() + 10);
	}
	
	// プログレスバー値設定用メソッド
	private void setProgressMain(int p){
		mProgressBar.setProgress(p);
		mTextMain.setText(String.format("Main: %d", p));
		SeekBar seek = (SeekBar)findViewById(R.id.seekBar1);
		seek.setProgress(p);
	}
	private void setProgressSecondary(int p){
		mProgressBar.setSecondaryProgress(p);
		mTextSecondary.setText(String.format("Secondary: %d", p));
		SeekBar seek = (SeekBar)findViewById(R.id.seekBar2);
		seek.setProgress(p);
	}
}
