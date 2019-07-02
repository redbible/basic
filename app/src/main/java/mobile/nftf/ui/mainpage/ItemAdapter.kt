package mobile.nftf.ui.mainpage

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import mobile.nftf.R
import mobile.nftf.common.ui.recycler.PaginationRecyclerViewAdapter
import mobile.nftf.common.ui.recyclerview.BaseViewHolder
import mobile.nftf.model.Item
import mobile.nftf.repository.impl.InterfaceCache

class ItemAdapter(provider: DataProvider<Item>, val interfaceCache: InterfaceCache) :
    PaginationRecyclerViewAdapter<Item>(provider) {

    override fun createRecyclableViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Item> {
        return ViewHolder(parent)
    }

    inner class ViewHolder(parent: ViewGroup) : BaseViewHolder<Item>(R.layout.img_item, parent) {

        private lateinit var img: ImageView

        override fun setupView(itemView: View) {
            super.setupView(itemView)

            img = itemView.findViewById(R.id.img)
        }

        override fun onDataBind(data: Item) {
            Glide.with(img)
                .load(data.thumbnail)
                .into(img)

            img.setOnClickListener { interfaceCache.toggleItem(data) }
        }
    }
}