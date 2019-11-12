package by.lukashenko.mvp.example.repository

import by.lukashenko.mvp.example.model.db.User

interface IUserRepository {
    fun getAllUser(): List<User>
    fun deleteAllUser()
    fun addUser(user: User)
}