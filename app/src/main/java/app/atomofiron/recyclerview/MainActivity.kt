package app.atomofiron.recyclerview

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.*
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

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_container)

        /** Показываем наш базовый фрагмент */
        supportFragmentManager.addFragment(
            container = R.id.fragment_container,
            fragment = HomeFragment.newInstance()
        )
    }

    /*
    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var dividerItemDecoration: RecyclerView.ItemDecoration
    private lateinit var catDecoration: RecyclerView.ItemDecoration
    private lateinit var defaultItemAnimator: RecyclerView.ItemAnimator
    private val ultimateItemAnimator = UltimateItemAnimator(lifecycle)

    private enum class ListType {
        ULTIMATE,
    }

    private var listType = ListType.ULTIMATE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.navigationBarColor = 0x01000000
        viewBinding = ActivityMainBinding.bind(findViewById(R.id.main_root))

        dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        catDecoration = CatDecoration(this)
        defaultItemAnimator = viewBinding.mainRecycler.itemAnimator!!

        setSupportActionBar(viewBinding.mainToolbar)

        applyInsets()
        switchList()

        Toast.makeText(this@MainActivity, "To remove item please use long tap", Toast.LENGTH_LONG)
            .show()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        MenuInflater(this).inflate(R.menu.toobar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        listType = when (item.itemId) {
            R.id.menu_ultimate -> ListType.ULTIMATE
            else -> return false
        }.apply {
            if (this == listType) return false
        }
        switchList()
        return true
    }

    private fun switchList() {
        viewBinding.mainRecycler.isVisible = true
        viewBinding.mainRecycler.clearItemDecorations()
        when (listType) {
            ListType.ULTIMATE -> toUltimateList()
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

    private fun toUltimateList() {
        val input: TextInputEditText = findViewById(R.id.text_input_edit)
        val button: Button = findViewById(R.id.button_add)
        viewBinding.mainRecycler.run {
            addItemDecoration(CatDecoration(context))
            itemAnimator = ultimateItemAnimator
            layoutManager = LinearLayoutManager(context)

            val adapter = UltimateAdapter()
            adapter.setListener(UltimateItemListenerImpl(adapter))
            this.adapter = adapter

            button.setOnClickListener { view ->
                if (!input.text.isNullOrEmpty()) {
                    (this.adapter as UltimateAdapter).insertItem(
                        0,
                        StringItem(input.text.toString(), false, "")
                    )
                    input.setText("")
                }
            }
        }
    }*/
}