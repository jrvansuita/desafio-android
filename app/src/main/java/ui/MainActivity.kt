package ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.TheApplication
import com.picpay.desafio.android.UserListAdapter
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.di.ViewModelFactory
import com.picpay.desafio.android.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MainViewModel by viewModels {
        (applicationContext as TheApplication).appComponent.viewModelsFactory()
    }

    //private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (applicationContext as TheApplication).appComponent.inject(viewModel)

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

        viewModel.users.observe(this) {
            toggleRecyclerVisualization(it.isNotEmpty())
            adapter.users = it
        }
    }

    private fun toggleRecyclerVisualization(show: Boolean) {
        binding.userListProgressBar.visibility = View.VISIBLE.takeIf { !show } ?: View.GONE
        binding.recyclerView.visibility = View.VISIBLE.takeIf { show } ?: View.GONE
    }
}
