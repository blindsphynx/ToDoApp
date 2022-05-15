package toDoApp.backend

import org.json.*
import java.io.File

class NotesController {
    var listOfNotes: MutableList<Note> = mutableListOf<Note>()

    init {
        val response = this.javaClass.classLoader.getResource("database.json")?.readText()
        if (response != null) {
            val jsonObject = JSONTokener(response).nextValue() as JSONObject
            val jsonArray = jsonObject.getJSONArray("note")

            for (i in 0 until jsonArray.length()) {
                // кушаем uid
                val uid = jsonArray.getJSONObject(i).getInt("uid")

                // кушаем datestamp
                val datestamp = jsonArray.getJSONObject(i).getString("datestamp")

                // кушаем text
                var text = jsonArray.getJSONObject(i).getString("text")

                // кушаем status
                var status = jsonArray.getJSONObject(i).getBoolean("status")

                listOfNotes.add(Note(uid, datestamp, text, status))
            }
        } else {
            println("Database not found\nEmpty array")
        }
    }

    fun addNote(date: String, text: String) {
        val uid: Int = if (listOfNotes.isEmpty()) {
            0
        } else {
            listOfNotes.last().uid + 1
        }
        listOfNotes.add(Note(uid, date, text, false))
        save()
    }

    fun deleteNote(id: Int) {
        if (listOfNotes.isNotEmpty()) {
            for (note in listOfNotes) {
                if (note.uid == id) {
                    listOfNotes.remove(note)
                }
            }
        }
        save()
    }

    fun editText(id: Int, newText: String) {
        if (listOfNotes.isNotEmpty()) {
            for (note in listOfNotes) {
                if (note.uid == id) {
                    note.text = newText
                }
            }
        }
        save()
    }

    fun editStatus(id: Int, newStatus: Boolean) {
        if (listOfNotes.isNotEmpty()) {
            for (note in listOfNotes) {
                if (note.uid == id) {
                    note.status = newStatus
                }
            }
        }
        save()
    }

    private fun save() {
        val filepath = this.javaClass.classLoader.getResource("database.json")?.path
        if (filepath != null) {
            File(filepath).writeText(this.toString())
        } else {
            println("File not found")
        }
    }

    override fun toString(): String {
        return "{\"note\": $listOfNotes}"
    }
}