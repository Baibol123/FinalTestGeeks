package com.example.finaltestgeeks.ui.Fragment

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.finaltestgeeks.Model.Notes
import com.example.finaltestgeeks.R
import com.example.finaltestgeeks.ViewModel.NotesViewModel
import com.example.finaltestgeeks.databinding.FragmentCreateNotesBinding
import java.util.Date


class CreateNotesFragment : Fragment() {
    private lateinit var binding: FragmentCreateNotesBinding
    private var priority: String = "1"
    private val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateNotesBinding.inflate(inflater, container, false)
        setupUI()
        return binding.root
    }

    private fun setupUI() {
        binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
        binding.pGreen.setOnClickListener { updatePriority("1") }
        binding.pYellow.setOnClickListener { updatePriority("2") }
        binding.pRed.setOnClickListener { updatePriority("3") }
        binding.btnSaveNotes.setOnClickListener { createNotes() }
    }

    private fun updatePriority(selectedPriority: String) {
        priority = selectedPriority
        binding.pGreen.setImageResource(if (priority == "1") R.drawable.ic_baseline_done_24 else 0)
        binding.pYellow.setImageResource(if (priority == "2") R.drawable.ic_baseline_done_24 else 0)
        binding.pRed.setImageResource(if (priority == "3") R.drawable.ic_baseline_done_24 else 0)
    }

    private fun createNotes() {
        val title = binding.editTitle.text.toString()
        val subtitle = binding.editSubTitle.text.toString()
        val notes = binding.editNotes.text.toString()
        val notesDate = DateFormat.format("d MMMM, yyyy", Date().time).toString()
        val data = Notes(null, title, subtitle, notes, notesDate, priority)
        viewModel.addNotes(data)
        Toast.makeText(requireContext(), "Примечание сохранено", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(requireView()).navigate(R.id.action_createNotesFragment_to_homeFragment)
    }
}
