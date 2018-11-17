package ru.egoncharovsky.wordstart.domain.word;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TranslationService {

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    private Translator translator;

    public TranslationService(Translator translator) {
        this.translator = translator;
    }

    public Translation translate(final Word word, final Language toLanguage) {
        try {
            return executor.submit(new Callable<Translation>() {
                @Override
                public Translation call() {
                    return translator.translate(word, toLanguage);
                }
            }).get();
        } catch (ExecutionException | InterruptedException e) {
            //todo
            throw new RuntimeException(e);
        }
    }
}
