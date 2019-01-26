package ru.egoncharovsky.wordstart.ui.pack

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.card_packs.*

import ru.egoncharovsky.wordstart.R
import ru.egoncharovsky.wordstart.domain.card.CardPack
import ru.egoncharovsky.wordstart.domain.card.LearningCard
import ru.egoncharovsky.wordstart.domain.word.Language
import ru.egoncharovsky.wordstart.domain.word.Phrase
import ru.egoncharovsky.wordstart.ui.KBaseActivity

class CardPacksActivity : KBaseActivity() {
    override fun contentViewId(): Int = R.layout.card_packs

    val cards = listOf(
            CardPack("pack1", setOf(
                    LearningCard(Phrase("word", Language.EN), Phrase("слово", Language.RU)),
                    LearningCard(Phrase("word2", Language.EN), Phrase("слово2", Language.RU))
            ))
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        list_card_packs.adapter = CardPackAdapter(cards)
        list_card_packs.layoutManager = LinearLayoutManager(this)
    }

    class CardPackAdapter(private val items: List<CardPack>) : RecyclerView.Adapter<CardPackAdapter.CardPackView>() {

        class CardPackView(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var name: TextView = itemView.findViewById(R.id.list_item_card_pack_name)
            var cardsCount: TextView = itemView.findViewById(R.id.list_item_card_pack_count_cards)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardPackView = CardPackView(
                LayoutInflater.from(parent.context).inflate(R.layout.list_item_card_pack, parent, false)
        )

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(holder: CardPackView, position: Int) {
            val cardPack = items[position]

            holder.name.text = cardPack.name
            holder.cardsCount.text = cardPack.cards.size.toString()
        }

    }
}
