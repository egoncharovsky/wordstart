package ru.egoncharovsky.wordstart.ui.pack

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.RelativeLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.card_pack_select.*
import ru.egoncharovsky.wordstart.R
import ru.egoncharovsky.wordstart.domain.card.LearningCard
import ru.egoncharovsky.wordstart.repository.LearningCardRepository
import ru.egoncharovsky.wordstart.ui.*

class CardPackSelectCardsActivity : BaseActivity() {

    companion object {
        const val REQUEST_SELECT_CARDS = 1
        const val CARD_IDS = "[LearningCard.id]"
    }

    override fun contentViewId(): Int = R.layout.card_pack_select

    private val cardRepo = LearningCardRepository

    private val selectedCards = mutableSetOf<Long>()
    private val mode = object : ActionModeCallback(this, R.menu.menu_select) {
        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean = when (item!!.itemId) {
            R.id.action_delete -> {
                returnResult(mapOf(
                        CARD_IDS to selectedCards.toLongArray()
                ))
                true
            }
            else -> false
        }

        override fun onDestroyActionMode(mode: ActionMode) {
            super.onDestroyActionMode(mode)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        card_pack_select_cards.layoutManager = LinearLayoutManager(this)

        card_pack_select_cards.addOnItemTouchListener(object : RecyclerItemClickListener(this, card_pack_select_cards) {
            override fun onItemClick(view: View?, position: Int) {
                val card = (card_pack_select_cards.adapter as CardAdapter).get(position)

                if (selectedCards.contains(card.id()))
                    selectedCards.remove(card.id())
                else
                    selectedCards.add(card.id()!!)

                card_pack_select_cards.adapter.notifyDataSetChanged()
            }

            override fun onItemLongClick(view: View?, position: Int) {}
        })

        mode.startActionMode()
    }

    override fun onStart() {
        super.onStart()

        getExtra<LongArray>(CARD_IDS)?.map { selectedCards.add(it) }

        val cards = cardRepo.getAll().toList().sortedByDescending { selectedCards.contains(it.id()) }
        card_pack_select_cards.adapter = CardAdapter(cards)
    }

    inner class CardAdapter(val items: List<LearningCard>) : RecyclerView.Adapter<CardAdapter.LearningCardView>() {

        inner class LearningCardView(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val layout: RelativeLayout = itemView.findViewById(R.id.list_item_card_layout)
            val mainText: TextView = itemView.findViewById(R.id.list_item_card_main_text)
            val subText: TextView = itemView.findViewById(R.id.list_item_card_sub_text)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LearningCardView = LearningCardView(
                LayoutInflater.from(parent.context).inflate(R.layout.list_item_card, parent, false)
        )

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(holder: LearningCardView, position: Int) {
            val card = items[position]

            holder.mainText.text = card.front.value
            holder.subText.text = card.back.value

            holder.layout.setBackgroundColor(ContextCompat.getColor(this@CardPackSelectCardsActivity,
                    when (selectedCards.contains(card.id())) {
                        true -> R.color.list_item_selected_state
                        false -> R.color.list_item_normal_state
                    }))
        }

        fun get(position: Int): LearningCard {
            return items[position]
        }
    }
}