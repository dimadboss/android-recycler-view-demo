package app.atomofiron.recyclerview

data class Animation(val enter: Int, val exit: Int, val popEnter: Int, val popExit: Int) {
    companion object {
        fun scaleFadeAnimation() = Animation(
            R.anim.screen_scale_fade_enter,
            0,
            0,
            R.anim.screen_scale_fade_exit,
        )
    }
}