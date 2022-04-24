package app.atomofiron.recyclerview

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.atomofiron.recyclerview.databinding.ActivityMainBinding
import app.atomofiron.recyclerview.ultimate.UltimateAdapter
import app.atomofiron.recyclerview.ultimate.api.UltimateItemListenerImpl
import app.atomofiron.recyclerview.ultimate.data.StringItem
import app.atomofiron.recyclerview.ultimate.utils.CatDecoration
import app.atomofiron.recyclerview.ultimate.utils.UltimateItemAnimator
import app.atomofiron.recyclerview.utils.DataItem
import com.google.android.material.textfield.TextInputEditText

/*import com.renat.base.replaceFragment
import com.renat.cicerone.FirstFragmentCicerone
import com.renat.jetpack.navigation.ContainerFragment
import com.renat.transaction.FirstFragmentTransaction*/

class HomeFragment : Fragment(R.layout.activity_main) {

    companion object {
        /** Получаем экземпляр нашего фрагмента */
        fun newInstance() = HomeFragment()
    }

    private lateinit var fragmentTransaction: Button
    private lateinit var jetpackNavigation: Button
    private lateinit var masterDetailFlowBtn: Button
    private lateinit var cicerone: Button


    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var dividerItemDecoration: RecyclerView.ItemDecoration
    private lateinit var catDecoration: RecyclerView.ItemDecoration
    private lateinit var defaultItemAnimator: RecyclerView.ItemAnimator
    private val ultimateItemAnimator = UltimateItemAnimator(lifecycle)

    private enum class ListType {
        ULTIMATE,
    }

    private var listType = ListType.ULTIMATE

    private fun onCreate(view: View) {

        //WindowCompat.setDecorFitsSystemWindows(window, false)
        //window.navigationBarColor = 0x01000000
        viewBinding = ActivityMainBinding.bind(view.findViewById(R.id.main_root))

        dividerItemDecoration = DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL)
        catDecoration = CatDecoration(view.context)
        defaultItemAnimator = viewBinding.mainRecycler.itemAnimator!!

        //view.setSupportActionBar(viewBinding.mainToolbar)

        applyInsets()
        switchList(view)

        Toast.makeText(view.context, "To remove item please use long tap", Toast.LENGTH_LONG)
            .show()
    }



    private fun switchList(view: View) {
        viewBinding.mainRecycler.isVisible = true
        viewBinding.mainRecycler.clearItemDecorations()
        when (listType) {
            ListType.ULTIMATE -> toUltimateList(view)
        }
        viewBinding.root.requestApplyInsets()
    }

    // обработка инсетов это отдельная большая тема
    private fun applyInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(viewBinding.mainAppbar) { appbar, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            appbar.updatePaddingRelative(top = systemBars.top)
            insets
        }
        val recyclerView = viewBinding.mainRecycler
        ViewCompat.setOnApplyWindowInsetsListener(viewBinding.root) { _, insets ->
            val cat = recyclerView.resources.getDimensionPixelSize(R.dimen.cat_part)
            val padding = when (listType) {
                ListType.ULTIMATE -> cat
            }
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            recyclerView.updatePaddingRelative(top = padding, bottom = systemBars.bottom + padding)
            insets
        }
    }

    private fun RecyclerView.clearItemDecorations() {
        while (itemDecorationCount > 0) {
            removeItemDecorationAt(0)
        }
    }

    private fun toUltimateList(view: View) {
        val input: TextInputEditText = view.findViewById(R.id.text_input_edit)
        val button: Button = view.findViewById(R.id.button_add)
        viewBinding.mainRecycler.run {
            addItemDecoration(CatDecoration(context))
            itemAnimator = ultimateItemAnimator
            layoutManager = LinearLayoutManager(context)

            val adapter = UltimateAdapter()
            adapter.setListener(UltimateItemListenerImpl(adapter))
            this.adapter = adapter

            button.setOnClickListener {
                if (!input.text.isNullOrEmpty()) {
                    (this.adapter as UltimateAdapter).insertItem(
                        0,
                        StringItem(input.text.toString(), false, "")
                    )
                    input.setText("")
                }
            }
        }
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onCreate(view)
        /** Инициализируем наши кнопки */
    /*    fragmentTransaction = view.findViewById(R.id.fragment_transaction)
        jetpackNavigation = view.findViewById(R.id.jetpack_navigation)
        //masterDetailFlowBtn = view.findViewById(R.id.master_detail_flow)
        cicerone = view.findViewById(R.id.cicerone)*/

        chooseSample()
    }

    private fun chooseSample() {
       /* fragmentTransaction.setOnClickListener {
            /** Заменяем наш текущий фрагмент на новый, с сохранением стека */
            parentFragmentManager.replaceFragment(
                container = com.renat.base.R.id.fragment_container,
                fragment = FirstFragmentTransaction.newInstance(),
                needBackstack = true
            )
        }

        jetpackNavigation.setOnClickListener {
            /** Заменяем наш текущий фрагмент на новый, с сохранением стека */
            parentFragmentManager.replaceFragment(
                container = com.renat.base.R.id.fragment_container,
                fragment = ContainerFragment.newInstance(),
                needBackstack = true
            )
        }

        cicerone.setOnClickListener {
            /** Заменяем наш текущий фрагмент на новый, с сохранением стека */
            parentFragmentManager.replaceFragment(
                container = com.renat.base.R.id.fragment_container,
                fragment = FirstFragmentCicerone(),
                needBackstack = true
            )
        }*/
    }
}