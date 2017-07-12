package kotlinandroid.net.library;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by itgoyo on 2017/7/5.
 * 了解更多  https://github.com/itgoyo
 * 更新时间 2017/7/5
 * 更新描述 ${TODO}
 */

@SuppressLint("AppCompatCustomView")
public class ContainsEmojiEditText extends EditText {
    //输入表情前的光标位置
    private int     cursorPos;
    //输入表情前EditText中的文本
    private String  inputAfterText;
    //是否重置了EditText的内容
    private boolean resetText;

    private Context      mContext;
    private CharSequence input;

    public ContainsEmojiEditText(Context context) {
        super(context);
        this.mContext = context;
        initEditText();
    }

    public ContainsEmojiEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initEditText();
    }

    public ContainsEmojiEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initEditText();
    }

    // 初始化edittext 控件
    private void initEditText() {
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int before, int count) {
                if (!resetText) {
                    cursorPos = getSelectionEnd();
                    // 这里用s.toString()而不直接用s是因为如果用s，
                    // 那么，inputAfterText和s在内存中指向的是同一个地址，s改变了，
                    // inputAfterText也就改变了，那么表情过滤就失败了
                    inputAfterText= s.toString();

                    Log.i("itgoyo","beforeTextChanged--> "+inputAfterText);
                    Log.i("itgoyo","cursorPos--> "+cursorPos);
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!resetText) {
                    if (count >= 1) {//表情符号的字符长度最小为2
                        if(count-before==20){
                            input = s.toString().subSequence(cursorPos,  count);
                        }else {
                            input = s.toString().subSequence(cursorPos, cursorPos + count);
                        }
                        if (EmojiTools.containsEmoji(input.toString())) {
                            resetText = true;
                            //是表情符号就将文本还原为输入表情符号之前的内容
                            Toast.makeText(mContext,"不支持输入该字符",Toast.LENGTH_SHORT).show();
                            setText(inputAfterText);
                            CharSequence text = getText();
                            if (text instanceof Spannable) {
                                Spannable spanText = (Spannable) text;
                                Selection.setSelection(spanText, text.length());
                            }
                        }
                    }
                } else {
                    resetText = false;
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }




}
