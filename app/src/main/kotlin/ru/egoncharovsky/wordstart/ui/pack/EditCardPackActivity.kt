package ru.egoncharovsky.wordstart.ui.pack

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import kotlinx.android.synthetic.main.edit_card_pack.*
import ru.egoncharovsky.wordstart.R
import ru.egoncharovsky.wordstart.domain.card.CardPack
import ru.egoncharovsky.wordstart.domain.card.LearningCard
import ru.egoncharovsky.wordstart.repository.CardPackRepository
import ru.egoncharovsky.wordstart.ui.KBaseActivity
import ru.egoncharovsky.wordstart.ui.getExtra
import ru.egoncharovsky.wordstart.ui.input

class EditCardPackActivity : KBaseActivity() {
    companion object {
        const val CARD_PACK_ID: String = "cardPack.id"
    }

    private val cardPackRepo = CardPackRepository
    private val validation = AwesomeValidation(ValidationStyle.BASIC)

    override fun contentViewId(): Int = R.layout.edit_card_pack

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        validation.addValidation(input_edit_pack_name, { it.isNotBlank() }, resources.getString(R.string.edit_pack_validation_err_name))
        list_edit_pack_cards.layoutManager = LinearLayoutManager(this)
        button_edit_pack_cancel.setOnClickListener { finish() }
    }

    override fun onStart() {
        super.onStart()

        getExtra<Long>(CARD_PACK_ID)?.let {
            val cardPack = cardPackRepo.get(it)

            input_edit_pack_name.setText(cardPack.name)
            text_edit_pack_cards_total.text = resources.getString(R.string.edit_pack_cards_total, cardPack.cards.size)

            list_edit_pack_cards.adapter = CardPackAdapter(cardPack.cards.toList())

            button_edit_pack_save.setOnClickListener {
                if (validation.validate()) {
                    cardPackRepo.update(CardPack(
                            cardPack.id,
                            input_edit_pack_name.input(),
                            (list_edit_pack_cards.adapter as CardPackAdapter).items.toSet()
                    ))
                    finish()
                }
            }
        } ?: run {
            button_edit_pack_save.setOnClickListener {
                if (validation.validate()) {
                    cardPackRepo.update(CardPack(
                            null,
                            input_edit_pack_name.input(),
                            (list_edit_pack_cards.adapter as CardPackAdapter).items.toSet()
                    ))
                    finish()
                }
            }
        }
    }

    class CardPackAdapter(val items: List<LearningCard>) : RecyclerView.Adapter<CardPackAdapter.LearningCardView>() {

        class LearningCardView(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var mainText: TextView = itemView.findViewById(R.id.list_item_card_main_text)
            var subText: TextView = itemView.findViewById(R.id.list_item_card_sub_text)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LearningCardView = LearningCardView(
                LayoutInflater.from(parent.context).inflate(R.layout.list_item_card, parent, false)
        )

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(holder: LearningCardView, position: Int) {
            val card = items[position]

            holder.mainText.text = card.front.value
            holder.subText.text = card.back.value
        }

        fun get(position: Int): LearningCard {
            return items[position]
        }
    }
}