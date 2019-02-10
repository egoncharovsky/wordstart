package ru.egoncharovsky.wordstart.ui.pack

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.RelativeLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.card_packs.*
import ru.egoncharovsky.wordstart.R
import ru.egoncharovsky.wordstart.domain.card.CardPack
import ru.egoncharovsky.wordstart.repository.CardPackRepository
import ru.egoncharovsky.wordstart.ui.ActionModeCallback
import ru.egoncharovsky.wordstart.ui.BaseActivity
import ru.egoncharovsky.wordstart.ui.RecyclerItemClickListener
import ru.egoncharovsky.wordstart.ui.switchActivityTo

class CardPacksActivity : BaseActivity() {

    override fun contentViewId(): Int = R.layout.card_packs

    private val cardPackRepo = CardPackRepository

    private val selected = mutableSetOf<CardPack>()

    private val mode = object : ActionModeCallback(this, R.menu.menu_multi_select) {
        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean = when (item!!.itemId) {
            R.id.action_delete -> {
                selected.forEach { cardPackRepo.delete(it.id()!!) }
                list_card_packs.adapter = CardPackAdapter(cardPackRepo.getAll().toList())
                finishActionMode()
                true
            }
            else -> false
        }

        override fun onDestroyActionMode(mode: ActionMode) {
            super.onDestroyActionMode(mode)
            selected.clear()
            list_card_packs.adapter.notifyDataSetChanged()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        list_card_packs.layoutManager = LinearLayoutManager(this)
        list_card_packs.addOnItemTouchListener(object : RecyclerItemClickListener(this, list_card_packs) {
            override fun onItemClick(view: View?, position: Int) {
                if (!mode.started()) {
                    switchActivityTo(EditCardPackActivity::class.java, false,
                            mapOf(EditCardPackActivity.CARD_PACK_ID to (list_card_packs.adapter as CardPackAdapter).get(position).id()!!))
                } else {
                    toggleSelect(position)
                }
            }

            override fun onItemLongClick(view: View?, position: Int) {
                if (!mode.started()) {
                    mode.startActionMode()
                }
                toggleSelect(position)
            }
        })
    }

    override fun onStart() {
        super.onStart()

        list_card_packs.adapter = CardPackAdapter(cardPackRepo.getAll().toList())
    }

    private fun toggleSelect(position: Int) {
        val pack = (list_card_packs.adapter as CardPackAdapter).get(position)
        when (selected.contains(pack)) {
            true -> selected.remove(pack)
            false -> selected.add(pack)
        }

        list_card_packs.adapter.notifyDataSetChanged()
    }

    inner class CardPackAdapter(private val items: List<CardPack>) : RecyclerView.Adapter<CardPackAdapter.CardPackView>() {

        inner class CardPackView(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val layout: RelativeLayout = itemView.findViewById(R.id.list_item_card_pack_layout)
            val name: TextView = itemView.findViewById(R.id.list_item_card_pack_name)
            val cardsCount: TextView = itemView.findViewById(R.id.list_item_card_pack_count_cards)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardPackView = CardPackView(
                LayoutInflater.from(parent.context).inflate(R.layout.list_item_card_pack, parent, false)
        )

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(holder: CardPackView, position: Int) {
            val cardPack = items[position]

            holder.name.text = cardPack.name
            holder.cardsCount.text = cardPack.cards.size.toString()

            holder.layout.setBackgroundColor(ContextCompat.getColor(this@CardPacksActivity,
                    when (selected.contains(cardPack)) {
                        true -> R.color.list_item_selected_state
                        false -> R.color.list_item_normal_state
                    }))
        }

        fun get(position: Int): CardPack {
            return items[position]
        }
    }
}
