package ru.egoncharovsky.wordstart.ui;

import ru.egoncharovsky.wordstart.R;

public interface EnumString<E extends Enum> {

    E getValue();

    /**
     * @return {@link R.string}
     */
    int getString();
}
