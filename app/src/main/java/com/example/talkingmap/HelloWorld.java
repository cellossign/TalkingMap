package com.example.talkingmap;


import java.util.Locale;

        import android.app.Activity;
        import android.os.Bundle;
        import android.speech.tts.TextToSpeech;
        import android.util.Log;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

public class HelloWorld extends Activity implements
        TextToSpeech.OnInitListener {

    private Button mButton;
    private TextToSpeech mTTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTTS = new TextToSpeech(this, this);

        mButton = (Button) findViewById(R.id.button1);

        mButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String text = "А Васька слушает да ест";
                mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }

    @Override
    public void onInit(int status) {
        // TODO Auto-generated method stub
        if (status == TextToSpeech.SUCCESS) {

            Locale locale = new Locale("ru");

            int result = mTTS.setLanguage(locale);
            //int result = mTTS.setLanguage(Locale.getDefault());

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "Извините, этот язык не поддерживается");
            } else {
                mButton.setEnabled(true);
            }

        } else {
            Log.e("TTS", "Ошибка!");
        }

    }


    @Override
    public void onDestroy() {
        // Don't forget to shutdown mTTS!
        if (mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }
        super.onDestroy();
    }
}




//import javax.speech.Engine;
//import android.speech.tts.TextToSpeech;
//import java.io.InputStream;
//import java.speech.recognition;
//import java.io.FileReader;
//import java.util.Locale;
//public class SpeechRecognitionHelloWorld extends ResultAdapter {
//    Audio audio = Audio.getInstance();
//    InputStream sound = audio.getAudio("I am a bus", Language.ENGLISH);
//    audio.play(sound);
//}