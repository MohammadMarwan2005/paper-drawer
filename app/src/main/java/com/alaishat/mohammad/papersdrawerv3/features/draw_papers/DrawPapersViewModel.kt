package com.alaishat.mohammad.papersdrawerv3.features.draw_papers

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alaishat.mohammad.javalibrary.backend.BoxTree
import com.alaishat.mohammad.papersdrawerv3.common.EventDelegate
import com.alaishat.mohammad.papersdrawerv3.common.EventViewModel
import com.alaishat.mohammad.papersdrawerv3.common.StateDelegate
import com.alaishat.mohammad.papersdrawerv3.common.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Mohammad Al-Aishat on Apr/24/2025.
 * PaperDrawer Project.
 */
@HiltViewModel
class DrawPapersViewModel @Inject constructor(
    private val stateDelegate: StateDelegate<DrawPapersUIState>,
    private val eventDelegate: EventDelegate<ShowMessageEvent>
) : ViewModel(), StateViewModel<DrawPapersUIState> by stateDelegate,
    EventViewModel<ShowMessageEvent> by eventDelegate {

    var textFieldStateValue = mutableStateOf(BoxTree.stringSample)
    val lastValue = mutableStateOf("")

    init {
        stateDelegate.setDefaultState(DrawPapersUIState())
        addNewPaper()
    }

    fun addNewPaper() {
        Log.d("current textField Value: ", textFieldStateValue.value)
        viewModelScope.launch {
            try {
                val paper = BoxTree(textFieldStateValue.value.toCharArray().toList())
                stateDelegate.updateState {
                    it.copy(it.papers + paper)
                }
                eventDelegate.sendEvent(
                    viewModelScope,
                    ShowMessageEvent(if (paper.isRectangle) "You Added a Full Rectangle Shape!" else "You Added a Non Rectangle Shape!")
                )
                clearTextFieldValue()
            } catch (e: Exception) {
                e.printStackTrace()
                eventDelegate.sendEvent(
                    viewModelScope,
                    ShowMessageEvent("Refactor your input, please…")
                )
            }
        }
    }

    fun removePaper(index: Int) {
        stateDelegate.updateState {
            it.copy(it.papers.toMutableList().apply { removeAt(index) }.toList())
        }
    }

    fun rotatePaper(index: Int) {
        val rotatedRoot = state.value.papers[index].rotate().asAnotherInstance()
        val rotated = BoxTree(rotatedRoot)
        stateDelegate.updateState {
            it.copy(
                it.papers.toMutableList().apply { this[index] = rotated }.toList()
            )
        }
    }

    fun loadLastValue() {
        if (textFieldStateValue.value == lastValue.value)
            eventDelegate.sendEvent(
                viewModelScope,
                ShowMessageEvent("No Old Values!")
            )
        else textFieldStateValue.value = lastValue.value
    }

    fun clearTextFieldValue() {
        lastValue.value = textFieldStateValue.value
        textFieldStateValue.value = ""
    }
}

data class DrawPapersUIState(val papers: List<BoxTree> = emptyList())
data class ShowMessageEvent(val message: String)
