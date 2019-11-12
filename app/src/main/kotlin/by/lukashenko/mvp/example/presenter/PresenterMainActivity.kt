/*
 * Copyright (c) 2019.
 * It's added by Lukashenko Dmitriy
 */

package by.lukashenko.mvp.example.presenter

import by.lukashenko.mvp.example.model.db.User
import by.lukashenko.mvp.example.repository.IUserRepository
import by.lukashenko.mvp.example.repository.UserRepository
import by.lukashenko.mvp.example.view.IMainActivity

class PresenterMainActivity(
    private val view: IMainActivity
) : IPresenterMainActivity {
    private var listUser: List<User> = ArrayList()
    private val repository: IUserRepository = UserRepository()

    override fun getAllUser() {
        listUser = repository.getAllUser()
        showAllData()
    }

    override fun clearData() {
        listUser = emptyList()
        repository.deleteAllUser()
        showAllData()
    }

    private fun showAllData() {
        view.showListUser(listUser)
    }
}