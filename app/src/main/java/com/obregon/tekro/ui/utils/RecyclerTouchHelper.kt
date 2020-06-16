package com.obregon.tekro.ui.utils

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder


interface ItemTouchHelperAdapter {
    fun onItemMove(fromPosition: Int, toPosition: Int)
    fun onItemDismiss(position: Int)
}

class RecyclerTouchHelperCallback(adapter: ItemTouchHelperAdapter) : ItemTouchHelper.Callback() {
    private val itemTouchHelperAdapter: ItemTouchHelperAdapter = adapter
    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: ViewHolder): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(
            dragFlags,
            swipeFlags
        )
    }

    override fun onMove(
        recyclerView: RecyclerView, viewHolder: ViewHolder,
        target: ViewHolder
    ): Boolean {
        itemTouchHelperAdapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
        itemTouchHelperAdapter.onItemDismiss(viewHolder.adapterPosition)
    }

}