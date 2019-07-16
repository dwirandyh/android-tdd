package com.dwirandyh.androidtdd.login


import com.dwirandyh.androidtdd.ILoginView
import com.dwirandyh.androidtdd.LoginPresenter
import org.junit.Assert
import org.junit.Before
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations



class LoginPresenterTest {

    lateinit var loginPresenter: LoginPresenter

    @Mock
    lateinit var iLoginView: ILoginView

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        loginPresenter = LoginPresenter(iLoginView)
    }

    @Test
    fun checkIfLoginAttempIsExceed() {
        Assert.assertEquals(1, loginPresenter.incrementLoginAttemp())
        Assert.assertEquals(2, loginPresenter.incrementLoginAttemp())
        Assert.assertEquals(3, loginPresenter.incrementLoginAttemp())
        Assert.assertTrue(loginPresenter.isLoginAttempExceeded())
    }

    @Test
    fun checkIfLoginAttempIsNotExceeded(){
        Assert.assertFalse(loginPresenter.isLoginAttempExceeded())
    }

    @Test
    fun checkEmailAndPasswordIsCorrect(){
        loginPresenter.doLogin("dwi", "randy")
        verify(iLoginView).showLoginSuccessMessage()
    }

    @Test
    fun checkEmailAndPasswordIsIncorrect(){
        loginPresenter.doLogin("abc", "def")
        verify(iLoginView).showErrorMessageForUsernamePassword()
    }

    @Test
    fun checkIfLoginAttemptIsExceed(){
        loginPresenter.doLogin("abc", "def")
        loginPresenter.doLogin("abc", "def")
        loginPresenter.doLogin("abc", "def")
        loginPresenter.doLogin("abc", "def")
        verify(iLoginView).showErrorMessageForMaxLoginAttempt()
    }
}