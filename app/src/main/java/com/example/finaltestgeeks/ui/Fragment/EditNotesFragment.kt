package com.example.finaltestgeeks.ui.Fragment

import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.finaltestgeeks.Model.Notes
import com.example.finaltestgeeks.R
import com.example.finaltestgeeks.ViewModel.NotesViewModel
import com.example.finaltestgeeks.databinding.FragmentEditNotesBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.Date

class EditNotesFragment : Fragment() {

    private val args by navArgs<EditNotesFragmentArgs>()
    private lateinit var binding: FragmentEditNotesBinding
    private val viewModel: NotesViewModel by viewModels()
    private var priority: String = "1"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditNotesBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        setupUI()
        return binding.root
    }

    private fun setupUI() {
        with(binding) {
            editTitle.setText(args.data.title)
            editSubTitle.setText(args.data.subTitle)
            editNotes.setText(args.data.notes)
            setPriority(args.data.priority)

            pGreen.setOnClickListener { setPriority("1") }
            pYellow.setOnClickListener { setPriority("2") }
            pRed.setOnClickListener { setPriority("3") }

            btnEditSaveNotes.setOnClickListener { updateNotes() }
        }
    }

    private fun setPriority(selectedPriority: String) {
        priority = selectedPriority
        with(binding) {
            pGreen.setImageResource(if (priority == "1") R.drawable.ic_baseline_done_24 else 0)
            pYellow.setImageResource(if (priority == "2") R.drawable.ic_baseline_done_24 else 0)
            pRed.setImageResource(if (priority == "3") R.drawable.ic_baseline_done_24 else 0)
        }
    }

    private fun updateNotes() {
        val title = binding.editTitle.text.toString()
        val subtitle = binding.editSubTitle.text.toString()
        val notes = binding.editNotes.text.toString()
        val notesDate = DateFormat.format("d MMMM, yyyy", Date().time).toString()
        val updatedNote = Notes(args.data.id, title, subtitle, notes, notesDate, priority)

        viewModel.updateNotes(updatedNote)
        Toast.makeText(requireContext(), "Обновленные примечания", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(requireView()).navigate(R.id.action_editNotesFragment_to_homeFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.menu_delete) {
            showDeleteConfirmation()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    private fun showDeleteConfirmation() {
        val bottomSheet = BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
        bottomSheet.setContentView(R.layout.dialog_delete)

        bottomSheet.findViewById<TextView>(R.id.dialog_yes)?.setOnClickListener {
            viewModel.deleteNotes(args.data.id!!)
            bottomSheet.dismiss()
            Toast.makeText(requireContext(), "Примечание удалено", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(requireView()).navigate(R.id.action_editNotesFragment_to_homeFragment)
        }
        bottomSheet.findViewById<TextView>(R.id.dialog_no)?.setOnClickListener {
            bottomSheet.dismiss()
        }
        bottomSheet.show()
    }
}
