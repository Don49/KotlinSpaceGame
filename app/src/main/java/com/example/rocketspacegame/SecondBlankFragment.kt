package com.example.rocketspacegame

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rocketspacegame.databinding.FragmentSecondBlankBinding

//This Fragment Houses the game

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var binding : FragmentSecondBlankBinding
private lateinit var gameView: mySurfaceView
lateinit var myViewModel: MyViewModel
//lateinit var sensorManager: SensorManager
var count: Int = 0
lateinit var myThread: Thread

/**
 * A simple [Fragment] subclass.
 * Use the [SecondBlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondBlankFragment : Fragment()  {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        //Inflate Binding

        binding = FragmentSecondBlankBinding.inflate(inflater, container , false)

        return binding.root

    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Getting data from model and displaying it -------------------------------------------
        myViewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
        var myModel = myViewModel.myLiveModel.value
        myViewModel.myLiveModel.observe(viewLifecycleOwner, Observer {
            if(myModel!=null)
            {
                binding.nameDisplay.text=  myModel.name
                binding.classIdDispaly.text=  myModel.classId
            }
        })


    }





    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SecondBlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SecondBlankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}