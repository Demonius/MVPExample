package by.lukashenko.mvp.example.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.lukashenko.mvp.example.R
import by.lukashenko.mvp.example.model.db.User
import by.lukashenko.mvp.example.presenter.IPresenterMainActivity
import by.lukashenko.mvp.example.presenter.PresenterMainActivity
import by.lukashenko.mvp.example.view.adapter.UserListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.view_toolbar.*

class MainActivity : AppCompatActivity(), IMainActivity, View.OnClickListener {


    companion object {
        private const val REQUEST_CODE = 555
    }

    private lateinit var adapterUser: UserListAdapter
    private lateinit var presenter: IPresenterMainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        presenter = PresenterMainActivity(this)
        initView()
        presenter.getAllUser()
    }

    private fun initView() {
        fab.setOnClickListener(this)
        adapterUser = UserListAdapter()
        vpListUser.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = adapterUser
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_clear -> actionClearButton()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun actionClearButton(): Boolean {
        presenter.clearData()
        return true
    }

    override fun onClick(view: View?) {
        view?.let {
            when (view.id) {
                R.id.fab -> showAddView()
            }
        }
    }

    private fun showAddView() {
        val intent = Intent(this, AddActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE -> setResultData(resultCode, data)
        }
    }

    private fun setResultData(resultCode: Int, data: Intent?) {
        when (resultCode) {
            Activity.RESULT_OK -> presenter.getAllUser()
        }
    }

    override fun showListUser(listUser: List<User>) {
        adapterUser.addAllData(listUser)
    }
}
