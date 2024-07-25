package com.example.finaltestgeeks.ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.finaltestgeeks.Model.Notes
import com.example.finaltestgeeks.R
import com.example.finaltestgeeks.ViewModel.NotesViewModel
import com.example.finaltestgeeks.databinding.FragmentHomeBinding
import com.example.finaltestgeeks.ui.Fragment.Adapter.NotesAdapter

class HomeFragment : Fragment() {


    lateinit var binding:FragmentHomeBinding
    private val viewModel:NotesViewModel by viewModels()
    private var oldMyNotes = arrayListOf<Notes>()
    lateinit var adapter: NotesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)

        setHasOptionsMenu(true)

        viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
            val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager
            adapter = NotesAdapter(requireContext(), notesList)
            oldMyNotes = notesList as ArrayList<Notes>
            binding.rcvAllNotes.adapter = adapter
        }

        binding.allNotes.setOnClickListener{
            viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
                val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager
                adapter = NotesAdapter(requireContext(), notesList)
                oldMyNotes = notesList as ArrayList<Notes>
                binding.rcvAllNotes.adapter = adapter
            }
        }
        binding.filterHigh.setOnClickListener{
            viewModel.getHighNotes().observe(viewLifecycleOwner) { notesList ->
                val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager
                adapter = NotesAdapter(requireContext(), notesList)
                oldMyNotes = notesList as ArrayList<Notes>
                binding.rcvAllNotes.adapter = adapter
            }
        }

        binding.filterMedium.setOnClickListener{
            viewModel.getMediumNotes().observe(viewLifecycleOwner) { notesList ->
                val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager
                adapter = NotesAdapter(requireContext(), notesList)
                oldMyNotes = notesList as ArrayList<Notes>
                binding.rcvAllNotes.adapter = adapter
            }
        }
        binding.filterLow.setOnClickListener{
            viewModel.getLowNotes().observe(viewLifecycleOwner) { notesList ->
                val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager
                adapter = NotesAdapter(requireContext(), notesList)
                oldMyNotes = notesList as ArrayList<Notes>
                binding.rcvAllNotes.adapter = adapter
            }
        }

        binding.btnAddNotes.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNotesFragment)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)

        val item =menu.findItem(R.id.app_bar_search)
        val searchView= item.actionView as SearchView
        searchView.queryHint="Введите ключевые слова ..."
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                NotesFilteting(p0.toString())
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }
    private fun NotesFilteting(p0:String){
        val newFilteredList= arrayListOf<Notes>()
        for (i in oldMyNotes){
            if (i.title.contains(p0!!) || i.subTitle.contains(p0!!)){
                newFilteredList.add(i)
            }
        }
        adapter.filtering(newFilteredList)
    }
}