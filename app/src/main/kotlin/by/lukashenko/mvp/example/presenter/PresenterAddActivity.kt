/*
 * Copyright (c) 2019.
 * It's added by Lukashenko Dmitriy
 */

package by.lukashenko.mvp.example.presenter

import by.lukashenko.mvp.example.model.db.User
import by.lukashenko.mvp.example.repository.IUserRepository
import by.lukashenko.mvp.example.repository.UserRepository
import by.lukashenko.mvp.example.view.IAddActivity

class PresenterAddActivity(private val view: IAddActivity) : IPresenterAddactivity {

    companion object {
        private const val ZERO_AGE = 0
    }

    private val user = User()
    private var stateErrorInputName = false
    private var stateErrorInputAge = false
    private val repository: IUserRepository = UserRepository()
    override fun setName(name: String) {
        user.name = name
        checkName()
    }

    private fun checkName() {
        stateErrorInputName = user.name.isNotEmpty()
    }

    override fun setAge(age: String) {
        user.age = if (age.isNotEmpty()) age.toInt() else ZERO_AGE
        checkAge()
    }

    private fun checkAge() {
        stateErrorInputAge = user.age > ZERO_AGE
    }

    override fun addUser() {

        if (!stateErrorInputName) {
            view.setErrorInputLayout(!stateErrorInputName)
            return
        }

        if (!stateErrorInputAge) {
            view.setErrorInputAge(!stateErrorInputAge)
            return
        }

        if (stateErrorInputName && stateErrorInputAge) {
            repository.addUser(user)

        }
    }
}