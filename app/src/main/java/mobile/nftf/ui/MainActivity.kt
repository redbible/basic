package mobile.nftf.ui

import android.widget.ImageView
import coinone.co.kr.official.common.ui.activity.BaseDataBindingActivity
import coinone.co.kr.official.common.ui.recyclerview.SimpleRecyclerViewAdapter
import com.bumptech.glide.Glide
import mobile.nftf.R
import mobile.nftf.databinding.MainActivityBinding
import mobile.nftf.util.Loading
import mobile.nftf.viewmodel.MainViewModel
import org.koin.android.ext.android.inject

class MainActivity : BaseDataBindingActivity<MainActivityBinding>(R.layout.main_activity) {
    private val viewModel by inject<MainViewModel>()

    override fun MainActivityBinding.onBind() {
        vm = viewModel

        rv.adapter = SimpleRecyclerViewAdapter<String>(R.layout.img_item) { view, data ->
            view.findViewById<ImageView>(R.id.img).run {
                Glide.with(this)
                    .load(data)
                    .into(this)
            }
        }

        Loading.show(this@MainActivity)
        viewModel.fetchUrls()
            .subscribe { Loading.dissmiss() }
    }
}
