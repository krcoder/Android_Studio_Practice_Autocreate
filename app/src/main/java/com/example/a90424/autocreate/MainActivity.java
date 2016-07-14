package com.example.a90424.autocreate;

import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.CharacterStyle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher, RadioGroup.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {

    Button btn1,btn2;
    TextView text,text_pass;
    RadioButton rad1,rad2,rad3;
    RadioGroup group1;
    SeekBar seek;
    SoundPool sp;
    Switch sw;

    int radio=0;
    int progre=6;
    int swch=1;
    int sound_typing;
    String pass="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        text = (TextView) findViewById(R.id.textView);
        text_pass = (TextView)findViewById(R.id.textView2);
        btn1 = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        group1 = (RadioGroup)findViewById(R.id.radioGroup1);
        group1.setOnCheckedChangeListener(this);
        seek = (SeekBar)findViewById(R.id.seekBar);
        seek.setOnSeekBarChangeListener(this);
        sp = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        sp.load(this,R.raw.typing,1);
        sound_typing = sp.load(this, R.raw.typing,0);
        sw = (Switch) findViewById(R.id.switch1);
        sw.setChecked(true);
        //sw.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) this);

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    swch=1;
                }else{
                    swch=0;
                }

            }
        });

    }





    public void getCurrentSeek()
    {
        int max = seek.getMax();
        int value = seek.getProgress();
        text.setText("Password length : "+value+"  (Max="+max+")");
        progre = seek.getProgress();

    }


    public void onCheckedChanged(RadioGroup arg0, int arg1) {
        if(arg0 == group1)
        {
            if(arg1 == R.id.radioButton)
            {
                radio=0;
                return;
            }
            else if(arg1 == R.id.radioButton2)
            {
                radio=1;
                return;
            }

            else if(arg1 == R.id.radioButton3)
            {
                radio=2;
                return;
            }
        }
    }


    public void onClick(View view){
        if(view == btn1){
            sp.play(sound_typing,1,1,0,0,1);
            passMake();
            text_pass.setText(pass);
            pass="";

            Toast.makeText(MainActivity.this, "비밀번호가 생성되었습니다", Toast.LENGTH_SHORT).show();

        }

        else if(view == btn2){
            text_pass.setText("");
            pass="";

            Toast.makeText(MainActivity.this, "초기화 합니다", Toast.LENGTH_SHORT).show();

        }
    }


    public void passMake(){
        int a,b,c,i;

        switch (radio){
            case 0:
                for(i=0;i<progre;i++){
                    a = (int)(Math.random()*10);
                    pass += Integer.toString(a);
                }

                break;

            case 1:
                if(swch==1){
                    for(i=0;i<progre;i++) {
                        b = (int) (Math.random() * 2);
                        if (b == 0) {
                            a = (int) (Math.random() * 10);
                            pass += Integer.toString(a);
                        } else if (b == 1) {
                            a = (int) (Math.random() * 26 + 65);
                            pass += Character.toString((char) a);
                        }
                    }
                }
                else if (swch==0){
                    for(i=0;i<progre;i++) {
                        b = (int) (Math.random() * 2);
                        if (b == 0) {
                            a = (int) (Math.random() * 10);
                            pass += Integer.toString(a);
                        } else if (b == 1) {
                            a = (int) (Math.random() * 26 + 97);
                            pass += Character.toString((char) a);
                        }
                    }
                }
                break;

            case 2:
                if (swch==1) {
                    for (i = 0; i < progre; i++) {
                        b = (int) (Math.random() * 3);
                        if (b == 0) {
                            a = (int) (Math.random() * 10);
                            pass += Integer.toString(a);
                        } else if (b == 1) {
                            a = (int) (Math.random() * 27 + 65);
                            pass += Character.toString((char) a);
                        } else if (b == 2) {
                            a = (int) (Math.random() * 10 + 33);
                            pass += Character.toString((char) a);
                        }
                    }
                }
                else if (swch ==0){
                    for (i = 0; i < progre; i++) {
                        b = (int) (Math.random() * 3);
                        if (b == 0) {
                            a = (int) (Math.random() * 10);
                            pass += Integer.toString(a);
                        } else if (b == 1) {
                            a = (int) (Math.random() * 27 + 97);
                            pass += Character.toString((char) a);
                        } else if (b == 2) {
                            a = (int) (Math.random() * 10 + 33);
                            pass += Character.toString((char) a);
                        }
                    }
                }

                break;

        }
    }




    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        text.setText(charSequence + " "+i+ " "+i1+" "+i2);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        getCurrentSeek();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
