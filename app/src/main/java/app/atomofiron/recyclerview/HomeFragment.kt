package app.atomofiron.recyclerview

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
/*import com.renat.base.replaceFragment
import com.renat.cicerone.FirstFragmentCicerone
import com.renat.jetpack.navigation.ContainerFragment
import com.renat.transaction.FirstFragmentTransaction*/

class HomeFragment : Fragment(R.layout.fragment_home) {

    companion object {
        /** Получаем экземпляр нашего фрагмента */
        fun newInstance() = HomeFragment()
    }

    private lateinit var fragmentTransaction: Button
    private lateinit var jetpackNavigation: Button
    private lateinit var masterDetailFlowBtn: Button
    private lateinit var cicerone: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** Инициализируем наши кнопки */
        fragmentTransaction = view.findViewById(R.id.fragment_transaction)
        jetpackNavigation = view.findViewById(R.id.jetpack_navigation)
        //masterDetailFlowBtn = view.findViewById(R.id.master_detail_flow)
        cicerone = view.findViewById(R.id.cicerone)

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