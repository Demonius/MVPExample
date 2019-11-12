package by.lukashenko.mvp.example.view

interface IAddActivity {

    fun setErrorInputLayout(stateError: Boolean)
    fun setErrorInputAge(stateError: Boolean)
    fun closeActivityWithSuccess()
}