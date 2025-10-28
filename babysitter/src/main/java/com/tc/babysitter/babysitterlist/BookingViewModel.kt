import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.tc.babysitter.babysitterlist.Babysitter
import java.time.LocalDate

class BookingViewModel : ViewModel() {
    var selectedDate: LocalDate? by mutableStateOf(null)
    var depositTime: String? by mutableStateOf(null)
    var recoveryTime: String? by mutableStateOf(null)

    var selectedBabysitter: Babysitter? by mutableStateOf(null)  // <-- Add this

    fun getFormattedDateTime(): String {
        return if (selectedDate != null && depositTime != null && recoveryTime != null) {
            val dateStr = selectedDate!!.format(java.time.format.DateTimeFormatter.ofPattern("dd MMM"))
            "$dateStr - $depositTime/$recoveryTime"
        } else ""
    }

    fun clear() {
        selectedDate = null
        depositTime = null
        recoveryTime = null
        selectedBabysitter = null
    }

    fun setBookingData(babysitter: Babysitter) {
        selectedBabysitter = babysitter
    }
}
