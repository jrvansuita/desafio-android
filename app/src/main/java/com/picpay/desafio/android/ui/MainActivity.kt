package com.picpay.desafio.android.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.TheApplication
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.di.ViewModelFactory
import com.picpay.desafio.android.ui.adapter.UserListAdapter
import com.picpay.desafio.android.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MainViewModel by viewModels {
        (applicationContext as TheApplication).appComponent.viewModelsFactory()
    }

    private lateinit var adapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
        loadUsers()
    }

    private fun setUpRecyclerView() {
        adapter = UserListAdapter().also {
            binding.recyclerView.apply {
                adapter = it
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    private fun loadUsers() {
        toggleRecyclerVisualization(false)

        viewModel.getUsers()

        viewModel.state.observe(this) {
            toggleRecyclerVisualization(true)

            when (it) {
                is MainViewModel.State.Success -> {
                    adapter.users = it.users
                }
                is MainViewModel.State.Error -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun toggleRecyclerVisualization(show: Boolean) {
        binding.userListProgressBar.visibility = View.VISIBLE.takeIf { !show } ?: View.GONE
        binding.recyclerView.visibility = View.VISIBLE.takeIf { show } ?: View.GONE
    }
}
