package ru.egoncharovsky.wordstart.ui.pack

import android.app.Activity
import android.content.Intent
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
import ru.egoncharovsky.wordstart.repository.LearningCardRepository
import ru.egoncharovsky.wordstart.ui.*

class EditCardPackActivity : BaseActivity() {
    companion object {
        const val CARD_PACK_ID: String = "cardPack.id"
    }

    private val cardPackRepo = CardPackRepository
    private val cardRepo = LearningCardRepository
    private val validation = AwesomeValidation(ValidationStyle.BASIC)

    private var editStarted = false

    override fun contentViewId(): Int = R.layout.edit_card_pack

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        validation.addValidation(input_edit_pack_name, { it.isNotBlank() }, resources.getString(R.string.edit_pack_validation_err_name))
        list_edit_pack_cards.layoutManager = LinearLayoutManager(this)
        button_edit_pack_cancel.setOnClickListener { finish() }

    }

    override fun onStart() {
        super.onStart()

        if (!editStarted) {
            getExtra<Long>(CARD_PACK_ID)?.let { id ->
                val cardPack = cardPackRepo.get(id)

                input_edit_pack_name.setText(cardPack.name)

                list_edit_pack_cards.adapter = CardAdapter(cardPack.cards.toList())

                button_edit_pack_save.setOnClickListener {
                    if (validation.validate()) {
                        cardPackRepo.update(CardPack(
                                cardPack.id,
                                input_edit_pack_name.input(),
                                (list_edit_pack_cards.adapter as CardAdapter).items.toSet()
                        ))
                        finish()
                    }
                }
                button_edit_pack_select_cards.setOnClickListener {
                    requestToActivity(CardPackSelectCardsActivity::class.java, CardPackSelectCardsActivity.REQUEST_SELECT_CARDS,
                            mapOf(CardPackSelectCardsActivity.CARD_IDS to
                                    (list_edit_pack_cards.adapter as CardAdapter).items.map { it.id()!! }.toLongArray()))
                }
            } ?: run {
                button_edit_pack_save.setOnClickListener {
                    if (validation.validate()) {
                        cardPackRepo.create(CardPack(
                                null,
                                input_edit_pack_name.input(),
                                (list_edit_pack_cards.adapter as CardAdapter).items.toSet()
                        ))
                        finish()
                    }
                }

                button_edit_pack_select_cards.setOnClickListener {
                    switchActivityTo(CardPackSelectCardsActivity::class.java)
                }
            }
            editStarted = true
        }

        text_edit_pack_cards_total.text = resources.getString(R.string.edit_pack_cards_total,
                (list_edit_pack_cards.adapter as CardAdapter).items.size)
    }

    override fun onDestroy() {
        super.onDestroy()
        editStarted = false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            CardPackSelectCardsActivity.REQUEST_SELECT_CARDS -> when (resultCode) {
                Activity.RESULT_OK -> {
                    val selectedCards = data!!.getExtra<LongArray>(CardPackSelectCardsActivity.CARD_IDS)!!.map { cardRepo.get(it) }
                    list_edit_pack_cards.adapter = CardAdapter(selectedCards)
                }
            }
        }
    }

    class CardAdapter(val items: List<LearningCard>) : RecyclerView.Adapter<CardAdapter.LearningCardView>() {

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