package eu.tutorials.scrabble.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import eu.tutorials.scrabble.data.allWordsList

class ScrabbleViewModel() : ViewModel() {
    private var _numOfWord = MutableLiveData<Int>()
    val numOfWord: LiveData<Int> get() = _numOfWord

    private var _score = MutableLiveData<Int>()
    val score: LiveData<Int> get() = _score

    private var _word = MutableLiveData<String>()
    val word: LiveData<String> get() = _word

    private val wordsList = allWordsList
    private lateinit var currentWord: String
    private var count = 0

    init{
        _numOfWord.value = wordsList.size
        _score.value = 0
        nextWord()
    }

    fun nextWord() {
        _numOfWord.value = ++count
        scrambleWord()
    }

    fun isWordCorrect(inWord: String) : Boolean{
        if(inWord.equals(currentWord, ignoreCase = true)) {
            _score.value = _score.value?.inc()
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
}