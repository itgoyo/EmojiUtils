package kotlinandroid.net.emojiutils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import kotlinandroid.net.library.ContainsEmojiEditText;
import kotlinandroid.net.library.EmojiTools;

public class MainActivity extends AppCompatActivity {
    private TextView mTv_before,mTv_after;
    private ContainsEmojiEditText mEmoji_et;
    private EditText mEt_input;
    private Button mBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mEt_input= (EditText) findViewById(R.id.et_input);
        mTv_before= (TextView) findViewById(R.id.before);
        mTv_after= (TextView) findViewById(R.id.after);
        mEmoji_et= (ContainsEmojiEditText) findViewById(R.id.emoji_et);
        mBt= (Button) findViewById(R.id.bt);



        mBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputText = mEt_input.getText().toString();

                mTv_before.setText(inputText);

                String filterEmoji = EmojiTools.filterEmoji(inputText);

                mTv_after.setText(filterEmoji);

            }
        });




    }
}
