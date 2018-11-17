package ru.egoncharovsky.wordstart.ui;

public interface ModelView<ModelType> {

    void init(ModelType model);

    void update(ModelType model);

}
