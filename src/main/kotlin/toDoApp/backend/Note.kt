package toDoApp.backend

class Note (var uid: Int, val datestamp: String, var text: String, var status: Boolean) {

    override fun toString(): String {
        return "{\"uid\": $uid, \"datestamp\": \"$datestamp\", \"text\": \"$text\", \"status\": $status}"
    }
}
