package eu.tutorials.scrabble.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import eu.tutorials.scrabble.data.allWordsList

class ScrabbleViewModel() : ViewModel() {
    private var _numOfWord = MutableLiveData(0)
    val numOfWord: LiveData<Int> = _numOfWord

    private var _score = MutableLiveData(0)
    val score: LiveData<Int> = _score

    private var _word = MutableLiveData<String>()
    val word: LiveData<String> = _word

    private val wordsList = allWordsList
    private lateinit var currentWord: String

    init{
        Log.d("ScrabbleFragment", "ScrabbleViewModel created!")
        nextWord()
    }

    fun nextWord() : Boolean {
        if(numOfWord.value!! <= 9) {
            _numOfWord.value = numOfWord.value?.inc()
            scrambleWord()
            return true
        }
        return false
    }

    fun isWordCorrect(inWord: String) : Boolean{
        if(inWord.equals(currentWord, ignoreCase = true)) {
            _score.value = score.value?.inc()
            nextWord()
            return true
        }
        return false
    }

    private fun scrambleWord() {
        currentWord = wordsList.random()
        val tempWord = currentWord.toCharArray()
        tempWord.shuffle()
        _word.value = String(tempWord)
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("ScrabbleFragment", "ScrabbleViewModel destroyed!")
    }
}