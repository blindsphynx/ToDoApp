package toDoApp

import toDoApp.backend.*

fun main() {
    var note = Note(1, "21-09-2019", "хехе", true)
    // println(note.status)
    // println(note.text)
    // println(note.toString())
    var notesController = NotesController()
    println(notesController.listOfNotes[1])
}
