package by.lukashenko.mvp.example.view

import by.lukashenko.mvp.example.model.db.User

interface IMainActivity {
    fun showListUser(listUser: List<User>)
}