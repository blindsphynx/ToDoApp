package toDoApp.backend

class Note (uid: Int, datestamp: String, text: String, status: Boolean) {
    val uid: Int = uid
    val datestamp: String = datestamp
    var text: String = text
    var status: Boolean = status

    override fun toString(): String {
        return "{\"uid\": $uid, \"datestamp\": \"$datestamp\", \"text\": \"$text\", \"status\": $status}"
    }
}