package mobile.nftf.ui

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.redbible.baseview.activity.BaseDataBindingActivity
import com.redbible.baseview.recycler.BaseDataBindingRecyclerViewAdapter
import com.redbible.baseview.recycler.replaceAll
import mobile.nftf.ActivityNavigator.Companion.KEY_DATA
import mobile.nftf.R
import mobile.nftf.databinding.ImgItem2Binding
import mobile.nftf.databinding.ImgItem3Binding
import mobile.nftf.databinding.SecondActivityBinding
import mobile.nftf.ext.loadUrl
import mobile.nftf.model.Item

class SecondActivity : BaseDataBindingActivity<SecondActivityBinding>(R.layout.second_activity) {
    lateinit var data: String

    override fun setupProperties(bundle: Bundle?) {
        bundle?.run {
            data = getString(KEY_DATA)
        }
    }

    override fun SecondActivityBinding.onBind() {
//        initFindViewRecycler(rv)
        initDatabinding(rv)

        rv.replaceAll(
            listOf(
                Item(),
                Item(),
                Item(),
                Item(),
                Item(),
                Item(),
                Item(),
                Item(),
                Item()
            )
        )
    }

    fun initDatabinding(rv: RecyclerView) {
        rv.adapter = BaseDataBindingRecyclerViewAdapter<Item>()
            .setItemViewType { it, position, isLast ->
                when {
                    position == 0 -> 0
                    isLast -> 1
                    else -> 2
                }
            }
//            .setItemViewTypeHeadTail()
            .addViewType(
                BaseDataBindingRecyclerViewAdapter.MultiViewType<Item, ImgItem2Binding>(R.layout.img_item2) {
                    //                    img.loadUrl(it.thumbnail)
                    img.loadUrl(
                        "https://ssl.pstatic.net/tveta/libs/1259/1259872/be9291d99a38625a9722_20191002160658234.png"
                    )
                }
            ).addViewType(
                BaseDataBindingRecyclerViewAdapter.MultiViewType<Item, ImgItem3Binding>(R.layout.img_item3) {
                    img.loadUrl(
                        "https://s.pstatic.net/shopping.phinf/20190930_29/dd1289a8-090c-456a-95f9-72d54d1568b6.jpg"
                    )
                }
            ).addViewType(
                BaseDataBindingRecyclerViewAdapter.MultiViewType<Item, ImgItem3Binding>(R.layout.img_item3) {
                    img.loadUrl(
                        "https://ssl.pstatic.net/tveta/libs/1253/1253163/20f4a157d0ed2f1ab5f2_20190927110557229_1.jpg"
                    )
                }
            )
    }
}