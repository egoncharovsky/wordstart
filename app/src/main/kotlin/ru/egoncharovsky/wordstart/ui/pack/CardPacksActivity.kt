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
import ru.egoncharovsky.wordstart.repository.CardPackRepository
import ru.egoncharovsky.wordstart.ui.KBaseActivity
import ru.egoncharovsky.wordstart.ui.RecyclerItemClickListener
import ru.egoncharovsky.wordstart.ui.switchActivityTo

class CardPacksActivity : KBaseActivity() {

    override fun contentViewId(): Int = R.layout.card_packs

    private val cardPackRepo = CardPackRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        list_card_packs.layoutManager = LinearLayoutManager(this)
        list_card_packs.addOnItemTouchListener(object : RecyclerItemClickListener(this, list_card_packs) {
            override fun onItemClick(view: View?, position: Int) {
                switchActivityTo(EditCardPackActivity::class.java, false,
                        mapOf(EditCardPackActivity.CARD_PACK_ID to (list_card_packs.adapter as CardPackAdapter).get(position).id()!!))
            }

            override fun onItemLongClick(view: View?, position: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    override fun onStart() {
        super.onStart()

        list_card_packs.adapter = CardPackAdapter(cardPackRepo.getAll().toList())
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

        fun get(position: Int): CardPack {
            return items[position]
        }
    }
}
