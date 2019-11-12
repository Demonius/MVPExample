/*
 * Copyright (c) 2019.
 * It's added by Lukashenko Dmitriy
 */

package by.lukashenko.mvp.example.repository

import by.lukashenko.mvp.example.model.db.User
import com.activeandroid.ActiveAndroid
import com.activeandroid.query.Select

class UserRepository() : IUserRepository {

    override fun getAllUser(): List<User> {
        val listUser: List<User>
        ActiveAndroid.beginTransaction()
        try {
            listUser = Select().from(User::class.java).execute()
            ActiveAndroid.setTransactionSuccessful()
        } finally {
            ActiveAndroid.endTransaction()
        }
        return listUser
    }

    override fun deleteAllUser() {
        ActiveAndroid.beginTransaction()
        try {
            getAllUser().forEach { user ->
                user.delete()
            }
            ActiveAndroid.setTransactionSuccessful()
        } finally {
            ActiveAndroid.endTransaction()
        }

    }

    override fun addUser(user: User) {
        ActiveAndroid.beginTransaction()
        try {
            user.save()
            ActiveAndroid.setTransactionSuccessful()
        } finally {
            ActiveAndroid.endTransaction()
        }
    }
}