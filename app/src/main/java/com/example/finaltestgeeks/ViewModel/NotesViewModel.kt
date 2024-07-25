package com.example.finaltestgeeks.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.finaltestgeeks.Database.NotesDatabase
import com.example.finaltestgeeks.Model.Notes
import com.example.finaltestgeeks.Respository.NotesRepository

class NotesViewModel(application: Application):AndroidViewModel(application) {

    val responsitory:NotesRepository
    init{
        val dao =NotesDatabase.getDatabaseInstance(application).myNotesDao()
        responsitory=NotesRepository(dao)
    }
    fun addNotes(notes: Notes)
    {
        responsitory.insertNotes(notes)
    }
    fun getNotes():LiveData<List<Notes>> = responsitory.getallNotes()

    fun getHighNotes():LiveData<List<Notes>> =  responsitory.getHighNotes()

    fun getMediumNotes():LiveData<List<Notes>> =  responsitory.getMediumNotes()

    fun getLowNotes():LiveData<List<Notes>> =  responsitory.getLowNotes()

    fun deleteNotes(id:Int){
        responsitory.deleteNotes(id)
    }
    fun updateNotes(notes: Notes){
        responsitory.updateNotes(notes)
    }
}