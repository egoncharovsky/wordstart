package ru.egoncharovsky.wordstart.domain.word;

import java.util.concurrent.*;

public class TranslationService {

    private Executor executor = Executors.newSingleThreadExecutor();

    private Translator translator;

    public TranslationService(Translator translator) {
        this.translator = translator;
    }

    public Translation translate(final Word word, final Language toLanguage) {
        FutureTask<Translation> remoteTranslateTask = new FutureTask<>(new Callable<Translation>() {
            @Override
            public Translation call() throws Exception {
                return translator.translate(word, toLanguage);
            }
        });

        executor.execute(remoteTranslateTask);
        try {
            return remoteTranslateTask.get();
        } catch (ExecutionException | InterruptedException e) {
            //todo
            throw new RuntimeException(e);
        }
    }
}
