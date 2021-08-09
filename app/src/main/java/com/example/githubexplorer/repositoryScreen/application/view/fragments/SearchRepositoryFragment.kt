@file:Suppress("DEPRECATION")
package com.example.githubexplorer.fragments.repositoryActivityFragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.githubexplorer.R
import com.example.githubexplorer.repositoryScreen.application.view.adapter.ListOfRepositoriesAdapter
import com.example.githubexplorer.repositoryScreen.application.viewmodel.RepositoriesViewModel
import com.example.githubexplorer.repositoryScreen.domain.model.RepositoryDetails
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.*

//Fragment to display the list of devices
class SearchRepositoryFragment : Fragment() {
    //Views
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var searchEditText: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var fab: FloatingActionButton

    private var repositoriesViewModel: RepositoriesViewModel? = null
    private var mAdapter: ListOfRepositoriesAdapter? = null
    private val searchTempList = ArrayList<RepositoryDetails>()
    private var repositoriesList = ArrayList<RepositoryDetails>()
    private var finalSelectedRepositoriesList = ArrayList<RepositoryDetails>()

    /**
     * Inflating fragment layout
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_repositories, container, false)
    }

    /**
     * Initializing fragment elements
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideKeyboard()
        initViews()
        initViewModel()
        initClickListener()
        observeDataForRepositoriesList()
        initRecyclerView()
        initSearchTextWatcher()
    }

    /**
     * Functions to hide the keyboard
     */
    private fun hideKeyboard() {
        // Check if no view has focus
        val view = requireActivity().currentFocus
        if (view != null) {
            val inputManager =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(
                view.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }

    /**
     * This function initialize views
     */
    private fun initViews() {
        mRecyclerView =requireActivity().findViewById(R.id.recycler_view)
        searchEditText = requireActivity().findViewById(R.id.search_repositories_edit_text)
        progressBar = requireActivity().findViewById(R.id.progress_bar)
        fab = requireActivity().findViewById(R.id.fab)
    }

    /**
     * init view model and fire-base call to get data of all the active devices
     */
    private fun initViewModel() {
        repositoriesViewModel = ViewModelProviders.of(this)
            .get(RepositoriesViewModel::class.java)
    }

    /**
     * observe data for all the devices and notify data set changed
     */
    @SuppressLint("ShowToast")
    private fun observeDataForRepositoriesList() {
        repositoriesViewModel?.getRepositorySuccessResponse()?.observe(viewLifecycleOwner, Observer {
            Log.d("REPOSITORIESSUCCESS", it.toString())
            progressBar.visibility = View.GONE
            repositoriesList.clear()
            searchTempList.clear()
            repositoriesList.addAll(it.items!!)
            searchTempList.addAll(it.items)
            mAdapter?.notifyDataSetChanged()
        })
        repositoriesViewModel?.getRepositoryFailureResponse()?.observe(viewLifecycleOwner, Observer {
            Log.d("REPOSITORIESFAILURE", it.toString())
            progressBar.visibility = View.GONE
            Toast.makeText(requireContext(),"Failure error - ${it.errorMessage}",Toast.LENGTH_SHORT)
        })
        repositoriesViewModel?.getRepositoryNetworkFailureResponse()?.observe(viewLifecycleOwner, Observer {
            Log.d("REPOSITORIESNETFAILURE", it.toString())
            progressBar.visibility = View.GONE
            Toast.makeText(requireContext(),"Network failure error - $it",Toast.LENGTH_SHORT)
        })

        repositoriesViewModel?.insertRepositorySuccessResponse()?.observe(viewLifecycleOwner, Observer {
            Log.d("REPOSITORIESSUCCESS", it.toString())
            Toast.makeText(requireContext(),"Added repositories to watchList",Toast.LENGTH_SHORT)
        })
        repositoriesViewModel?.insertRepositoryFailureResponse()?.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(),"Couldn't add repositories to watchlist. Failure error - ${it.errorMessage}",Toast.LENGTH_SHORT)
        })
        repositoriesViewModel?.insertRepositoryNetworkFailureResponse()?.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(),"Couldn't add repositories to watchlist. Network failure error - $it",Toast.LENGTH_SHORT)
        })
    }

    /**
     * init recycler view and implement listener
     */
    @SuppressLint("MissingPermission")
    private fun initRecyclerView() {
        mAdapter = ListOfRepositoriesAdapter(
            requireActivity(),
            repositoriesList,
            object : ListOfRepositoriesAdapter.RepositoryListListener {
                override fun onClick(selectedRepositoriesList: ArrayList<RepositoryDetails>) {
                    for (item in selectedRepositoriesList) {
                        if (!selectedRepositoriesList.contains(item)) {
                            selectedRepositoriesList.add(item)
                        }
                    }
                    finalSelectedRepositoriesList = selectedRepositoriesList
                    if (selectedRepositoriesList.isNotEmpty())
                        fab.visibility = View.VISIBLE
                    else
                        fab.visibility = View.GONE
                    Log.d("KRISHNAAAA",selectedRepositoriesList.toString())
                }
            })
        mRecyclerView.adapter = mAdapter
    }

    /**
     * initialize search text watcher
     */
    private fun initSearchTextWatcher() {
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                //No-operation
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("KRSIHNA@",s.toString())
                searchDataModel(s.toString())            }
        })

    }

    /**
     * @param searchText text to be searched
     * searched device data model
     */
    @SuppressLint("DefaultLocale")
    fun searchDataModel(searchText: String) {
        val searchedRepositoryDataModelList = ArrayList<RepositoryDetails>()
        if (searchText.length>0){
            progressBar.visibility = View.VISIBLE
            repositoriesViewModel?.fetchRepositoryDetails(searchText)
            for (dataModel in searchTempList) {
                if (dataModel.name!!.contains(searchText)) {
                    searchedRepositoryDataModelList.add(dataModel)
                }
            }
            repositoriesList = searchedRepositoryDataModelList
        }
        else{
            progressBar.visibility = View.GONE
            searchedRepositoryDataModelList.clear()
        }
        mAdapter?.searchedList(searchedRepositoryDataModelList)
    }

    private fun initClickListener(){
        fab.setOnClickListener {
//           GlobalScope.launch(Dispatchers.IO) {
//               repositoriesViewModel?.insertRepositoryDetails(finalSelectedRepositoriesList)
//           }
        }
    }
}