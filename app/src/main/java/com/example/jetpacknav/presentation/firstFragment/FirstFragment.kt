package com.example.jetpacknav.presentation.firstFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpacknav.data.local.model.Animal
import com.example.jetpacknav.R
import com.example.jetpacknav.databinding.FragmentFirstBinding
import com.example.jetpacknav.presentation.secondFragment.SecondFragment


class FirstFragment : Fragment(), AnimalAdapter.Listener {

    private var _binding : FragmentFirstBinding? = null
    private val binding get() = _binding!!

    lateinit var firstViewModel : FirstViewModel

    lateinit var adapter: AnimalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = AnimalAdapter (this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        firstViewModel = ViewModelProvider(this).get(FirstViewModel :: class.java)
        firstViewModel.getCat()
        //viewLifecycleOwner реагує на життєві цикли фрагменту
        firstViewModel.catCollection.observe(viewLifecycleOwner){
            adapter.setList(it)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        binding.rV.layoutManager = layoutManager
        binding.rV.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(animal: Animal) {

        val bundle = bundleOf(
            SecondFragment.name to animal.name,
            SecondFragment.desc to animal.shortDescription,
            SecondFragment.fullDesc to animal.fullDesc,
            SecondFragment.url to animal.urlPhoto)
        findNavController().navigate(R.id.action_firstFragment_to_secondFragment, bundle)
    }

}

