package eu.tutorials.scrabble

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
        Log.d("ScrabbleFragment", "ScrabbleFragment created/re-created!")
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
        setError(false)
        if(!viewModel.nextWord()) {
            Toast.makeText(requireContext(), "Game completed", Toast.LENGTH_SHORT).show()
        }
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

    override fun onDetach() {
        super.onDetach()
        Log.d("ScrabbleFragment", "ScrabbleFragment destroyed!")
    }
}