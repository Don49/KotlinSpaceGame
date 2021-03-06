package com.example.rocketspacegame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.rocketspacegame.databinding.FragmentFirstBlankBinding


// This Fragment is the initial Fragment and gets the users name and display


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstBlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */


class FirstBlankFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding : FragmentFirstBlankBinding
    private lateinit var navController : NavController
    lateinit var myViewModel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBlankBinding.inflate(inflater, container , false)
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        //Moving Data with ViewModel -------------------------------------------
        myViewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
        var myModel = myViewModel.myLiveModel.value

        binding.startButton.setOnClickListener {
            if (myModel != null) {
                myModel.name= binding.enterNameBox.text.toString()
                myModel.classId= binding.enterNameBox2.text.toString()
            }
        //Navigating to next fragment -------------------------------------------
            navController = findNavController()
            navController.navigate(R.id.action_firstBlankFragment_to_infoBlankFragment)
        }



    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstBlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstBlankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}