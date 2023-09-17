package YourSSU_Backend.Assignment.global.exception

data class ErrorResponse(
    val time: String,
    val status: String,
    val message: String,
    val requestURI: String
)