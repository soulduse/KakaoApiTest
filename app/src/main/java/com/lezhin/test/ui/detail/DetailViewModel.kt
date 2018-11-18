package com.lezhin.test.ui.detail

import com.lezhin.test.ui.base.BaseViewModel

class DetailViewModel: BaseViewModel<DetailNavigation>() {

    fun goToLink() {
        getNavigator()?.goToLink()
    }

    fun toolbarCancel() {
        getNavigator()?.toolbarCancel()
    }
}
