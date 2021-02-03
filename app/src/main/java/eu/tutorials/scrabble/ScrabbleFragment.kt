package eu.tutorials.scrabble

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import eu.tutorials.scrabble.databinding.FragmentScrabbleBinding
import eu.tutorials.scrabble.model.ScrabbleViewModel

class ScrabbleFragment : Fragment() {

    private lateinit var binding: FragmentScrabbleBinding
    private val viewModel: ScrabbleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScrabbleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            scrabbleFragment = this@ScrabbleFragment
            scrabbleViewModel = viewModel
        }
    }

    fun onSkip() {
        viewModel.nextWord()
        setError(false)
    }

    fun onSubmit() {
        setError(!viewModel.isWordCorrect(binding.textInputEditText.text.toString()))
    }

    private fun setError(result: Boolean){
        if(result) {
            binding.textInputLayout.isErrorEnabled = true
            binding.textInputLayout.error = "Try again!"
        }
        else {
            binding.textInputLayout.isErrorEnabled = false
            binding.textInputEditText.text = null
        }
    }
}