package com.dwirandyh.androidtdd

class LoginPresenter(var loginView: ILoginView) {

    private val MAX_LOGIN_ATTEMP = 3
    private var loginAttempt = 0

    fun incrementLoginAttemp(): Int {
        loginAttempt = loginAttempt + 1
        return loginAttempt
    }

    fun resetLoginAttemp(){
        loginAttempt = 0
    }

    fun isLoginAttempExceeded() : Boolean{
        return loginAttempt >= MAX_LOGIN_ATTEMP
    }

    fun doLogin(username: String, password: String){
        if (isLoginAttempExceeded()){
            loginView.showErrorMessageForMaxLoginAttempt()
            return
        }

        if (username.equals("dwi") && password.equals("randy")){
            loginView.showLoginSuccessMessage()
            resetLoginAttemp()
            return
        }

        incrementLoginAttemp()
        loginView.showErrorMessageForUsernamePassword()
    }
}