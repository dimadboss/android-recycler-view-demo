package app.atomofiron.recyclerview

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun FragmentManager.replaceFragment(
    container: Int,
    fragment: Fragment,
    needBackstack: Boolean = false,
    needAnimation: Boolean = false
) {
    /** Получаем экзмепляр FragmentTransaction */
    beginTransaction().apply {

        /** Смотрим на флажок в параметрах, если он true, то добавляем анимацию переходов */
        if (needAnimation) {
            setCustomAnimations(Animation.scaleFadeAnimation())
        }

        /**
         * Включение этого флага гарантирует, что при одновременном выполнении
         * нескольких транзакций любые промежуточные фрагменты
         * (т.е. те, которые добавляются, а затем немедленно заменяются)
         * не претерпевают изменений жизненного цикла или не выполняют
         * свою анимацию или переходы.
         */
        setReorderingAllowed(true)

        /** Заменяем текущий фрагмент на новый */
        replace(container, fragment)

        /** Смотрим на флажок в параметрах, если он true, то добавляем наш фрагмент в backstack */
        if (needBackstack) {
            addToBackStack(fragment::class.java.name)
        }

    }.commit()
}

fun FragmentManager.addFragment(container: Int, fragment: Fragment) {
    /** Получаем экзмепляр FragmentTransaction */
    beginTransaction()
        /** Добавляем новый фрагмент */
        .add(container, fragment)
        .commit()
}

private fun FragmentTransaction.setCustomAnimations(anim: Animation) {
    setCustomAnimations(anim.enter, anim.exit, anim.popEnter, anim.popExit)
}