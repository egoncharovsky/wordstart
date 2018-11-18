package ru.egoncharovsky.wordstart.ui.pack;

import ru.egoncharovsky.wordstart.domain.card.CardPack;

public class CardPacksDictionaryModel {



    public class Item {
        private final CardPack pack;

        public Item(CardPack pack) {
            this.pack = pack;
        }

        public String getName() {
            return pack.getName();
        }

        public int getSize() {
            return pack.getCards().size();
        }

        public String getSubText() {
            return "";
        }
    }
}
