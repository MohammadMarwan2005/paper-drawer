package com.alaishat.mohammad.papersdrawerv3.features.possible_papers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alaishat.mohammad.javalibrary.backend.AllPossibleCasesSolution
import com.alaishat.mohammad.javalibrary.backend.BoxTree
import com.alaishat.mohammad.javalibrary.backend.DataPaper
import com.alaishat.mohammad.papersdrawerv3.common.EventDelegate
import com.alaishat.mohammad.papersdrawerv3.common.EventViewModel
import com.alaishat.mohammad.papersdrawerv3.common.StateDelegate
import com.alaishat.mohammad.papersdrawerv3.common.StateViewModel
import com.alaishat.mohammad.papersdrawerv3.features.draw_papers.ShowMessageEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Mohammad Al-Aishat on Apr/29/2025.
 * PaperDrawer Project.
 */
@HiltViewModel
class PossiblePapersViewModel @Inject constructor(
    private val stateDelegate: StateDelegate<PossiblePapersUIState>,
    private val eventDelegate: EventDelegate<ShowMessageEvent>,
) : ViewModel(), StateViewModel<PossiblePapersUIState> by stateDelegate,
    EventViewModel<ShowMessageEvent> by eventDelegate {

    companion object {
        private val sampleList = listOf(
            DataPaper('A', 25, 25),
            DataPaper('B', 75, 175),
            DataPaper('C', 75, 30),
        )
    }

    init {
        stateDelegate.setDefaultState(PossiblePapersUIState(emptyList(), emptyList()))
        viewModelScope.launch {
            emitPapers(
                sampleList
            )
        }
    }

    fun addDataPaper(charString: String, heightString: String, widthString: String) {
        viewModelScope.launch {
            stateDelegate.updateState { it.copy(isLoading = true) }
            try {
                val dataPaper = DataPaper(
                    charString[0],
                    heightString.toInt(),
                    widthString.toInt()
                )
                val newPapers = state.value.papers.toMutableList().apply { add(dataPaper) }
                emitPapers(newPapers)
            } catch (e: Exception) {
                e.printStackTrace()
                eventDelegate.sendEvent(
                    viewModelScope,
                    ShowMessageEvent("Enter valid values please...")
                )
                stateDelegate.updateState { it.copy(isLoading = false) }
            }
        }
    }

    fun removeDataPaper(index: Int) {
        viewModelScope.launch {
            val newPapers = state.value.papers.toMutableList().apply { removeAt(index) }
            emitPapers(newPapers)
        }
    }

    private suspend fun emitPapers(papers: List<DataPaper>) {
        val boxTrees = getAllPossibleRectangleBoxTrees(papers)
        stateDelegate.updateState {
            PossiblePapersUIState(
                papers = papers,
                boxTrees = boxTrees
            )
        }
    }

    private suspend fun getAllPossibleRectangleBoxTrees(papers: List<DataPaper>): List<BoxTree> {
        if (papers.isEmpty()) return emptyList()
        return withContext(Dispatchers.Default) {
            AllPossibleCasesSolution.getAllPossibleBoxTrees(papers)
                .sortedBy { it.root.exportNode().length }.filter { it.isRectangle }
        }
    }
}

data class PossiblePapersUIState(
    val papers: List<DataPaper>,
    val boxTrees: List<BoxTree>,
    val isLoading: Boolean = false,
)
