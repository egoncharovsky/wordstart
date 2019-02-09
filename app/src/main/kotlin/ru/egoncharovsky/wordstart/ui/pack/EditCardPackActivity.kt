package ru.egoncharovsky.wordstart.ui.pack

import android.os.Bundle
import kotlinx.android.synthetic.main.edit_card_pack.*
import ru.egoncharovsky.wordstart.R
import ru.egoncharovsky.wordstart.repository.CardPackRepository
import ru.egoncharovsky.wordstart.ui.KBaseActivity
import ru.egoncharovsky.wordstart.ui.getExtra

class EditCardPackActivity : KBaseActivity() {
    companion object {
        const val CARD_PACK_ID: String = "cardPack.name"
    }

    private val cardPackRepo = CardPackRepository

    override fun contentViewId(): Int = R.layout.edit_card_pack

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getExtra<String>(CARD_PACK_ID)?.let {
            val cardPack = cardPackRepo.get(it)

            input_edit_pack_name.setText(cardPack.name)
            text_edit_pack_cards_total.text = resources.getString(R.string.edit_pack_cards_total, cardPack.cards.size)

        }
    }
}