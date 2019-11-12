/*
 * Copyright (c) 2019.
 * It's added by Lukashenko Dmitriy
 */

package by.lukashenko.mvp.example.view

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import by.lukashenko.mvp.example.R
import by.lukashenko.mvp.example.presenter.IPresenterAddactivity
import by.lukashenko.mvp.example.presenter.PresenterAddActivity
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity(), IAddActivity {


    private lateinit var presenter: IPresenterAddactivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        presenter = PresenterAddActivity(this)
        initView()
    }

    private fun initView() {
        etEnterName.apply {
            addTextChangedListener(InputUserTextWatcher(this))
        }
        etEnterAge.apply {
            addTextChangedListener(InputUserTextWatcher(this))
        }

        btnAddUser.setOnClickListener { presenter.addUser() }
    }


    override fun setErrorInputLayout(stateError: Boolean) {
        layoutEnterName.apply {
            isErrorEnabled = stateError
            error = getString(R.string.error_input_name)
        }
    }

    override fun setErrorInputAge(stateError: Boolean) {
        layoutEnterAge.apply {
            isErrorEnabled = stateError
            error = getString(R.string.error_input_age)
        }
    }

    private inner class InputUserTextWatcher(private val view: TextInputEditText) : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            when (view.id) {
                R.id.etEnterName -> presenter.setName(s.toString())
                R.id.etEnterAge -> presenter.setAge(s.toString())
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    }

    override fun closeActivityWithSuccess() {
        setResult(Activity.RESULT_OK)
        finish()
    }
}
