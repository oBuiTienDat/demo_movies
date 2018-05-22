package com.gotasoft.movies.home.detail

import com.gotasoft.movies.data.Detail

/**
 * Created by dattien on 10/7/17.
 */
class DetailContract {
    interface View {
        fun showDetail(detail: Detail)
        fun showError()
        fun onBack()
        fun onShare()
        fun onAdd()
        fun onPlay()
    }
}
