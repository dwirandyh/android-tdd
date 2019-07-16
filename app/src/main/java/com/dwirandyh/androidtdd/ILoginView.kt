package com.dwirandyh.androidtdd

interface ILoginView {
    fun showErrorMessageForUsernamePassword()

    fun showErrorMessageForMaxLoginAttempt()

    fun showLoginSuccessMessage()
}