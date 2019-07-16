package com.dwirandyh.androidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), ILoginView {

    private lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initPresenter()
        initVIew()
    }

    fun initVIew(){
        btn_login.setOnClickListener {
            loginPresenter.doLogin(edit_email.text.toString(), edit_password.text.toString())
        }
    }

    fun initPresenter(){
        loginPresenter = LoginPresenter(this)
    }

    override fun showErrorMessageForUsernamePassword() {
        Snackbar.make(edit_password, "Please check your email or password.", Snackbar.LENGTH_LONG).show()
    }

    override fun showErrorMessageForMaxLoginAttempt() {
        Snackbar.make(btn_login, "You have exceeded MAX attempt", Snackbar.LENGTH_LONG).show()
    }

    override fun showLoginSuccessMessage() {
        Snackbar.make(btn_login, "Login Successful", Snackbar.LENGTH_LONG).show()
    }
}
