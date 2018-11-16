package ru.egoncharovsky.wordstart.external.translate;

import android.os.AsyncTask;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

public class GoogleTranslator {

    public void example() {
        // Instantiates a client
//        Translate translate = TranslateOptions.getDefaultInstance().getService();
//
//        // The text to translate
//        String text = "Hello, world!";
//
//        // Translates some text into Russian
//        Translation translation =
//                translate.translate(
//                        text,
//                        TranslateOption.sourceLanguage("en"),
//                        TranslateOption.targetLanguage("ru"));
//
//
//        System.out.printf("Text: %s%n", text);
//        System.out.printf("Translation: %s%n", translation.getTranslatedText());

        TranslateTask translate = new TranslateTask();
        String text = "Hello, world!";
        System.out.printf("Text: %s%n", text);
        translate.execute(text );
    }

    private class TranslateTask extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {

            String text = params[0]; //text to translate
            Translate translate = TranslateOptions.getDefaultInstance().getService();
            Translation translation =
                    translate.translate(
                            text,
                            TranslateOption.sourceLanguage("en"),
                            TranslateOption.targetLanguage("ru"));



            return translation.getTranslatedText();
        }

        //this method will run after doInBackground execution
        @Override
        protected void onPostExecute(String result) {
            System.out.printf("Translation: %s%n", result);
        }


    }
}
