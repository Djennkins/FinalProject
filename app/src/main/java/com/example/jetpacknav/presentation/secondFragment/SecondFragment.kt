package com.example.jetpacknav.presentation.secondFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.example.jetpacknav.databinding.FragmentSecondBinding

class SecondFragment() : Fragment() {

    private var _binding : FragmentSecondBinding? = null
    private val binding get() = _binding!!

    lateinit var secondViewModel : SecondViewModel

    companion object{
        const val name = "name"
        const val desc = "desc"
        const val fullDesc = "fullDesk"
        const val url = "url"
        const val fact = "fact"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        secondViewModel = ViewModelProvider(this).get(SecondViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(view).load(arguments?.getString(url)).fitCenter().into(binding.tvPhoto)

        secondViewModel.getFact()

        //viewLifecycleOwner реагує на життєві цикли фрагменту
        secondViewModel.fact.observe(viewLifecycleOwner){
            binding.tvFact.text = it
        }

        secondViewModel.isLoading.observe(viewLifecycleOwner){
            if(it == true){
                binding.tvName.visibility = View.INVISIBLE
                binding.tvDescription.visibility = View.INVISIBLE
                binding.tvFullDesc.visibility = View.INVISIBLE
                binding.tvFact.visibility = View.INVISIBLE
                binding.progressBar.visibility = View.VISIBLE
            }else if( it == false){
                binding.tvName.visibility = View.VISIBLE
                binding.tvDescription.visibility = View.VISIBLE
                binding.tvFullDesc.visibility = View.VISIBLE
                binding.tvFact.visibility = View.VISIBLE
                binding.progressBar.visibility = View.INVISIBLE
            }
        }

        binding.tvName.text = arguments?.getString(name)
        binding.tvDescription.text = arguments?.getString(desc)
        binding.tvFullDesc.text = arguments?.getString(fullDesc)
        binding.tvFact.text = arguments?.getString(fact)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}