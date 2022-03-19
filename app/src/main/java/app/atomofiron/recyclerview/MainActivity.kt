package app.atomofiron.recyclerview

import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.atomofiron.recyclerview.databinding.ActivityMainBinding
import app.atomofiron.recyclerview.ultimate.UltimateAdapter
import app.atomofiron.recyclerview.ultimate.api.UltimateItemListenerImpl
import app.atomofiron.recyclerview.ultimate.utils.CatDecoration
import app.atomofiron.recyclerview.ultimate.utils.UltimateItemAnimator

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var dividerItemDecoration: RecyclerView.ItemDecoration
    private lateinit var catDecoration: RecyclerView.ItemDecoration
    private lateinit var defaultItemAnimator: RecyclerView.ItemAnimator
    private val ultimateItemAnimator = UltimateItemAnimator(lifecycle)

    private enum class ListType {
        /* LIST_VIEW, EASY, MEDIUM,*/ ULTIMATE,
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
        //viewModel = ViewModelProvider(this)[MainViewModel::class]

        setSupportActionBar(viewBinding.mainToolbar)
        // configListVew()
        applyInsets()
        switchList()

        subscribeToData()

        Toast.makeText(this@MainActivity, "To remove item please use long tap", Toast.LENGTH_LONG).show()
    }

    private fun subscribeToData() {
        /*viewModel.viewModelScope.launch {
            viewModel.listItems.collect { items ->
                ultimateAdapter.setItems(items)
            }
        }*/
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
        //viewBinding.mainList.isVisible = false
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
        //val listView = viewBinding.mainList
        ViewCompat.setOnApplyWindowInsetsListener(viewBinding.root) { _, insets ->
            val cat = recyclerView.resources.getDimensionPixelSize(R.dimen.cat_part)
            val padding = when (listType) {
                ListType.ULTIMATE -> cat
            }
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            recyclerView.updatePaddingRelative(top = padding, bottom = systemBars.bottom + padding)
            //listView.updatePaddingRelative(top = padding, bottom = systemBars.bottom + padding)
            insets
        }
    }

    private fun RecyclerView.clearItemDecorations() {
        while (itemDecorationCount > 0) {
            removeItemDecorationAt(0)
        }
    }

    private fun toUltimateList() {
        viewBinding.mainRecycler.run {
            addItemDecoration(CatDecoration(context))
            itemAnimator = ultimateItemAnimator
            layoutManager = LinearLayoutManager(context)//StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

            val adapter = UltimateAdapter()
            adapter.setListener(UltimateItemListenerImpl(adapter))
            this.adapter = adapter
        }
    }
}